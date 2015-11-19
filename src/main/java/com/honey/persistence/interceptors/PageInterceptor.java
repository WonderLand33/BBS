package com.honey.persistence.interceptors;


import com.honey.entity.extend.PageResult;
import com.honey.persistence.dialect.Dialect;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Map;
import java.util.Properties;


@Intercepts({@Signature(type =StatementHandler.class, method = "prepare", args ={Connection.class})})
public class PageInterceptor implements Interceptor {

	  /**
	   * 数据库方言设置
	   */
	  private Dialect dialect;
	  
	  private static ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
	  private static ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
	  
	  private static Logger logger = LoggerFactory.getLogger(PageInterceptor.class);
	  
	   private static Field FIELD = null;
	   {
		   try {
			FIELD = BoundSql.class.getDeclaredField("metaParameters");
			FIELD.setAccessible(true);
		} catch (NoSuchFieldException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
	   public Object intercept(Invocation invocation) throws Throwable {
		    StatementHandler statementHandler = (StatementHandler)invocation.getTarget();
		    MetaObject metaStatementHandler = MetaObject.forObject(statementHandler,DEFAULT_OBJECT_FACTORY,DEFAULT_OBJECT_WRAPPER_FACTORY);
	        MappedStatement mappedStatement = (MappedStatement) 
	        metaStatementHandler.getValue("delegate.mappedStatement");
	        //  MappedStatement的sql
            BoundSql boundSql = (BoundSql) metaStatementHandler.getValue("delegate.boundSql");
            Object parameterObject = boundSql.getParameterObject();
         // Object parameterObject = metaStatementHandler.getValue("delegate.boundSql.parameterObject");  
            if (parameterObject != null) {         
                // 分页参数作为参数对象parameterObject的一个属性
                PageResult<?> page = null;
                if (parameterObject instanceof PageResult<?>)
                {
                	page = (PageResult<?>) parameterObject;
                } else if (parameterObject instanceof Map<?,?>)
                {
                	Collection<?> values = ((Map<?,?>) parameterObject).values();
                	for (Object obj : values)
                	{
                		if (obj != null
                				&& obj instanceof PageResult<?>)
                		{
                			page = (PageResult<?>) obj;
                			break;
                		}
                	}
                	
                } 
                if (page != null)
                {
                	String sql = boundSql.getSql();
	                Connection connection = (Connection) invocation.getArgs()[0];
	                // 重设分页参数里的总页数等
	                setPageParameter(sql, connection, mappedStatement, boundSql, page);
	                if (page.getTotalCount() == 0)
	                {
	                	return invocation.proceed();
	                }
                    // 重写sql
                    String pageSql = dialect.buildPageSql(sql, page);
                    metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);
                    // 采用物理分页后，就不需要mybatis的内存分页了，所以重置下面的两个参数
                    metaStatementHandler.setValue("delegate.rowBounds.offset", 
                    RowBounds.NO_ROW_OFFSET);
                    metaStatementHandler.setValue("delegate.rowBounds.limit", RowBounds.NO_ROW_LIMIT); 
                    
                }
            //}
            }
	        // 将执行权交给下一个拦截器
	        return invocation.proceed();
	    }

    public Object plugin(Object target) {
        // 当目标类是StatementHandler类型时，才包装目标类，否者直接返回目标本身,减少目标被代理的
        // 次数
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }
    /**
     * 从数据库里查询总的记录数并计算总页数，回写进分页参数<code>PageParameter</code>,这样调用 
     * 者就可用通过 分页参数<code>PageParameter</code>获得相关信息。
     * 
     * @param sql
     * @param connection
     * @param mappedStatement
     * @param boundSql
     * @param page
     */
    private void setPageParameter(String sql, Connection connection, MappedStatement mappedStatement,
            BoundSql boundSql, PageResult<?> page) {
        // 记录总记录数
        String countSql = dialect.getCountSql(sql);
        PreparedStatement countStmt = null;
        ResultSet rs = null;
        try {
            countStmt = connection.prepareStatement(countSql);
            BoundSql countBS = new BoundSql(mappedStatement.getConfiguration(), countSql,
                    boundSql.getParameterMappings(), boundSql.getParameterObject());
            FIELD.set(countBS, FIELD.get(boundSql));
            setParameters(countStmt, mappedStatement, countBS, boundSql.getParameterObject());
            rs = countStmt.executeQuery();
            int totalCount = 0;
            if (rs.next()) {
                totalCount = rs.getInt(1);
            }
            page.setTotalCount(totalCount);
            int totalPage = totalCount / page.getPageSize() + ((totalCount % page.getPageSize() == 0) ? 0 : 1);
            page.setTotalPage(totalPage);
        } catch (SQLException e) {
            logger.error("Ignore this exception", e);
        } catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
            try {
            	if (rs !=null){
            		rs.close();
            	}

            } catch (SQLException e) {
                logger.error("Ignore this exception", e);
            }
            try {
                countStmt.close();
            } catch (SQLException e) {
                logger.error("Ignore this exception", e);
            }
        }
    }

    /**
     * 对SQL参数(?)设值
     * 
     * @param ps
     * @param mappedStatement
     * @param boundSql
     * @param parameterObject
     * @throws SQLException
     */
    private void setParameters(PreparedStatement ps, MappedStatement mappedStatement, BoundSql boundSql,
            Object parameterObject) throws SQLException {
        ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, parameterObject, boundSql);
        parameterHandler.setParameters(ps);
    }

	public void setProperties(Properties arg0) {	
		
	}
	public Dialect getDialect() {
		return dialect;
	}

	public void setDialect(Dialect dialect) {
		this.dialect = dialect;
	}
}
