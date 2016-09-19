package xyz.springabc.web.taglib;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.Date;

/**
 * 用来显示多少时间前
 * 1分钟前
 * 1小时前
 * 1天前
 *
 * @author zonghua
 */
public class Flashback extends TagSupport {

    private static final long serialVersionUID = 1L;

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    private Date time;

    @Override
    public int doStartTag() throws JspException {
        JspWriter out = this.pageContext.getOut();
        try {
            out.print(FlashbackSupport.format(time));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return TagSupport.SKIP_BODY;
    }

    @Override
    public int doEndTag() throws JspException {
        return EVAL_PAGE;
    }

    @Override
    public void release() {
        super.release();
    }

    /**
     * EL function format date to long
     * ${my:formatDateToLong(dateTime)}
     *
     * @param date
     * @return
     */
    public static long formatDateToLong(Date date) {
        return date.getTime() / 1000;
    }

}
