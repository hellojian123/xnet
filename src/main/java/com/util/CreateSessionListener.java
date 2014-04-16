package com.util;

import java.util.List;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import org.nutz.dao.Cnd;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.pager.Pager;
import com.bean.Article;
import com.bean.FriendLinks;
import com.bean.NewsTemplate;
import com.dao.Init;

public class CreateSessionListener implements HttpSessionListener{
	
	/**
	 * session创建时执行
	 */
	public void sessionCreated(HttpSessionEvent event) {
		NutDao dao = Init.dao;
		/*友情链接*/
		List<FriendLinks> friendLinks=dao.query(FriendLinks.class,Cnd.where("1", "=", "1"));
		/*4号广告区（左下角的一副图片）*/
		List<NewsTemplate> fourPoster=dao.query(NewsTemplate.class, Cnd.where("type", "=", 4));
		/*7号广告区（右边公共部分的4副广告）*/
		List<NewsTemplate> sevenPoster=dao.query(NewsTemplate.class, Cnd.where("type", "=", 7));
		
		
		/*查询供应信息总数*/
		Integer supplyNum = MyDaoUtils.QueryTotalByTypeId(dao, "t_article", 2);
		/*查询求购信息总数*/
		Integer demandNum = MyDaoUtils.QueryTotalByTypeId(dao, "t_article", 3);
		/*查询产品展示总数*/
		Integer productNum = MyDaoUtils.QueryTotalByTypeId(dao, "t_article", 7);
		/*查询行情走势总数*/
		Integer trendNum = MyDaoUtils.QueryTotalByTypeId(dao, "t_article", 9);
		/*查询展会信息总数*/
		Integer exhibitionNum = MyDaoUtils.QueryTotalByTypeId(dao, "t_article", 5);
		/*查询行业资讯总数*/
		Integer newsNum = MyDaoUtils.QueryTotalByTypeId(dao, "t_article", 4);
		
		//推荐图文
		Pager pager = dao.createPager(1, 4);
		List<Article> imgAndArticle = dao.query(Article.class, Cnd.where("typeid", "=", 7).or("typeid", "=", 8).desc("clickNum").desc("modifyDate"), pager);
		
		event.getSession().setAttribute("imgAndArticle", imgAndArticle);
		/*显示在公共部分的'按分类浏览区域'*/
		event.getSession().setAttribute("supplyNum", supplyNum);
		event.getSession().setAttribute("demandNum", demandNum);
		event.getSession().setAttribute("productNum", productNum);
		event.getSession().setAttribute("trendNum", trendNum);
		event.getSession().setAttribute("exhibitionNum", exhibitionNum);
		event.getSession().setAttribute("newsNum", newsNum);
		/*结束*/
		
		event.getSession().setAttribute("webName","中山灯饰网");
		event.getSession().setAttribute("friendLinks", friendLinks);
		event.getSession().setAttribute("fourPoster", fourPoster);
		event.getSession().setAttribute("sevenPoster", sevenPoster);
	}
	
	/**
	 * 销毁session时执行
	 */
	public void sessionDestroyed(HttpSessionEvent arg0) {
		
	}
}
