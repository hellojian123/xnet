package com.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.nutz.dao.Cnd;
import org.nutz.ioc.Ioc;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.view.JspView;

import com.bean.NewsTemplate;

public class AdminPoster extends BaseAction {
	
	/**
	 * 进入广告管理页面
	 * @param area	区域编号
	 */
	@At("/admin/goInPosterManage")
	public View batchDeleteArticleById(@Param("type")Integer type,Ioc ioc,HttpServletRequest req,HttpServletResponse rep) throws IOException {
		List<NewsTemplate> posters= dao.search(NewsTemplate.class, Cnd.where("type", "=", type));
		req.setAttribute("posters", posters);
		req.setAttribute("type", type);
		return new JspView("admin.posterManage");
	}
}



















