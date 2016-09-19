package com.honey.util;

import org.slf4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by zonghua on 2016/9/19.
 */
public class SqlUtil {
    /**
     * 检查数据库是否创建
     *
     * @param applicationContext
     * @param logger
     * @param sqlPath
     * @return
     */
    public static boolean checkTables(ApplicationContext applicationContext, Logger logger, String sqlPath) {
        DataSource dataSource = (DataSource) applicationContext.getBean("dataSource");
        StringBuffer sqlBuffer = new StringBuffer();
        boolean isCreate = false;
        try {
            Connection connection = dataSource.getConnection();
            connection.setAutoCommit(false);

            // 尝试读取数据库
            try {
                Statement statement1 = connection.createStatement();
                isCreate = statement1.execute("select * from honey_type");//读取成功
                logger.info(" tables available " + isCreate);
            } catch (SQLException e) {
                logger.error("tables not available");//读取不成功
            }

            // 执行创建数据库
            if (!isCreate) {
                // 读取 sql 文件
                Resource resource = new ClassPathResource(sqlPath);
                File file = resource.getFile();
                InputStream urlStream = new FileInputStream(file);
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(urlStream, "UTF-8"));
                String temp = "";
                while ((temp = bufferedReader.readLine()) != null) {
                    sqlBuffer.append(temp);
                }
                bufferedReader.close();
                String sqls = sqlBuffer.toString();

                // 批处理
                logger.info("create table sql");
                Statement statement2 = connection.createStatement();
                String[] sqlLines = sqls.split(";");
                for (String line : sqlLines) {
                    logger.info(line);
                    statement2.addBatch(line);
                }
                int[] resultRows = statement2.executeBatch();
                logger.debug("execute " + resultRows.length + "rows");
            }
            connection.close();
        } catch (IOException e) {
            logger.error("no create tables sql file");
        } catch (SQLException e) {
            logger.error("create sql has error");
            logger.error(e.getSQLState());
            logger.error(e.getMessage());
            logger.error("please check or manual execute sql");
        } finally {
            logger.info("check tables over");
        }

        return isCreate;
    }
}
