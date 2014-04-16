package com.util;
import javax.servlet.http.HttpSession;

import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.Mvcs;
import org.nutz.mvc.View;
import org.nutz.mvc.view.ServerRedirectView;

/**
 * 检查session中的指定属性，不存在则重定向到指定页面
 * @author Administrator
 *
 */
public class CheckSession implements ActionFilter {

    private String name;
    private String path;

    public CheckSession(String name, String path) {
        this.name = name;
        this.path = path;
    }
    
    public View match(ActionContext context) {
    	HttpSession session = Mvcs.getHttpSession(false);
    	if (session == null)
    		 return new ServerRedirectView(path);
        Object obj = session.getAttribute(name);
        if (null == obj)
            return new ServerRedirectView(path);
        return null;
    }
}