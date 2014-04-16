package com.action;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.nutz.dao.Cnd;
import org.nutz.ioc.Ioc;
import org.nutz.mvc.View;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.By;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;
import com.util.CheckSession;
import org.nutz.mvc.view.JspView;
import com.bean.Article;
import com.bean.Company;
import com.bean.FriendLinks;
import com.bean.NewsTemplate;
import com.bean.Picture;
import com.bean.User;
import com.google.gson.Gson;
import com.util.DeleteImgByHtml;
import com.util.MD5;
import com.util.PageModel;
import com.util.SystemContext;

public class AdminAction extends BaseAction {
	/**
	 * 获取管理员的username
	 */
	private String getAdminName(HttpServletRequest req){
		
		return ((User)(req.getSession().getAttribute("admin"))).getUsername();
	}
	
	
	/**
	 * 获取admin对象
	 * @param req
	 * @return
	 */
	private User getAdminObj(HttpServletRequest req){	
		return ((User)(req.getSession().getAttribute("admin")));
	}
	
	/**
	 * 进入管理员登陆页面
	 */
	@At("/admin")
	public View manageIndex(Ioc ioc , HttpServletRequest req){
		return new JspView("admin.login");
	}
	
	/**
	 * 管理员登陆处理
	 * @param username 用户名
	 * @param password 密码
	 */
	@At("/admin/login")
	public View login(@Param("username") String username,@Param("password") String password,Ioc ioc,HttpServletRequest req){
		
		if(username==null||password==null){
			return new JspView("admin.login");
		}
		password=new MD5().getMD5ofStr(password);
		User user=dao.findByCondition(User.class, Cnd.where("username", "=", username).and("password", "=", password));
		if(user!=null){
			if(user.getAdminType()==0){
				req.setAttribute("error", "您只是普通用户,没有权限进入后台管理系统");
				return new JspView("admin.login");
			}
			user.setLoginNum(user.getLoginNum()+1);
			user.setLastLoginTime(user.getCurrentLoginTime());
			user.setCurrentLoginTime(new Date());
			user.setLastLoginIp(user.getCurrentLoginIp());
			user.setCurrentLoginIp(req.getRemoteAddr());
			dao.update(user);
			req.getSession().setAttribute("admin", user);
			return new JspView("admin.index");
		}else{
			req.setAttribute("error", "用户名或者密码错误！");
			return new JspView("admin.login");
		}
	}
	
	/**
	 * 根据id修改密码
	 * 
	 * @return
	 */
	@At("/admin/user/updatepwdUI")
	@Filters(@By(type=CheckSession.class, args={"admin", "/goAdmin.jsp"}))
	public View toupdatepwd(){
		return new JspView("admin.updatepwd");
	}
	
	/**
	 * 根据id修改密码
	 * @Ok("raw")意为ajax返回数据
	 */
	@At("/admin/user/updatepwd")
	@Ok("raw")
	@Filters(@By(type=CheckSession.class, args={"admin", "/goAdmin.jsp"}))
	public String updatepwd(@Param("userId")Integer userId,@Param("newPwd")String newPwd,@Param("oldpwd") String oldpwd, Ioc ioc,HttpServletRequest req){
		User userTemp=dao.find(userId, User.class);
		oldpwd=new MD5().getMD5ofStr(oldpwd);
		if(!userTemp.getPassword().equals(oldpwd)){
			return "1";
		}
		newPwd=new MD5().getMD5ofStr(newPwd);
		userTemp.setPassword(newPwd);
		dao.update(userTemp);
		return "0";
	}
	
	/**
	 * 分页查询用户（只有超级管理员能进行此操作）
	 */
	@At("/admin/userManagerUI")
	@Filters(@By(type=CheckSession.class, args={"admin", "/goAdmin.jsp"}))
	public View findAll(@Param("currentPage") Integer currentPage,Ioc ioc,HttpServletRequest request){
		if(currentPage==null){currentPage=1;}
		int pageSize=SystemContext.PAGE_SIZE;//每页显示十条数据
		int count = dao.searchCount(User.class);//获取总用户数
		int maxPage = dao.maxPageSize(count, pageSize);
		if(currentPage>maxPage){
			currentPage=maxPage;
		}
		List<User> users =  dao.searchByPage(User.class,currentPage, pageSize,"adminType");
		PageModel<User> pm = new PageModel<User>(users, maxPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pm", pm);
		return new JspView("admin.userManager");
	}
	
	
	/**
	 * 会员管理
	 * @param currentPage
	 * @param ioc
	 * @param request
	 * @return
	 */
	@At("/admin/userVipManagerUI")
	@Filters(@By(type=CheckSession.class, args={"admin", "/goAdmin.jsp"}))
	public View userVipManagerUI(@Param("currentPage") Integer currentPage,Ioc ioc,HttpServletRequest request){
		if(currentPage==null){currentPage=1;}
		int pageSize=SystemContext.PAGE_SIZE;//每页显示十条数据
		int count = dao.searchCount(User.class);//获取总用户数
		int maxPage = dao.maxPageSize(count, pageSize);
		if(currentPage>maxPage){
			currentPage=maxPage;
		}//,"adminType"
		Cnd cnd=Cnd.where("1", "=", "1");
		cnd.desc("adminType").desc("examine");
		List<User> users =  dao.searchByPage(User.class,cnd,currentPage, pageSize);
		PageModel<User> pm = new PageModel<User>(users, maxPage);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("pm", pm);
		return new JspView("admin.userVipManager");
	}
	
	
	@At("/admin/saveOrUpdateUser")
	@Ok("raw")
	@Filters(@By(type=CheckSession.class, args={"admin", "/goAdmin.jsp"}))
	public String saveOrUpdateUser(@Param("::user.")User user,Ioc ioc,HttpServletRequest req){
		if(user.getId()==null){//保存
			user.setUserType(0);//普通会员
			user.setExamine(1);//已通过     由管理员添加的用户默认都是已通过
			user.setLoginNum(0);//默认登录次数为0
			user.setPassword(new MD5().getMD5ofStr("123456"));
			user.setRegisterDate(new Date());
			if(dao.save(user)!=null){
				return "0";//保存成功
			}else{
				return "1";//保存失败
			}
		}else{
			if(dao.update(user)){
				return "2";//更新成功
			}else{
				return "3";//更新失败
			}
		}
	}
	
	/**
	 * 根据用户id删除用户，企业列表中信息也将被删除
	 * @param userId 
	 */
	@At("/admin/user/deleteUserById")
	@Ok("raw")
	@Filters(@By(type=CheckSession.class, args={"admin", "/goAdmin.jsp"}))
	public String deleteUserById(@Param("userId")Integer userId,Ioc ioc,HttpServletRequest req){
		Company company=dao.find(dao.find(userId, User.class).getCompanyId(), Company.class);
		DeleteImgByHtml.deleteArticleImgs(req.getSession(), company.getIntroduce());//删除公司详情中的图片文字
		String path=req.getSession().getServletContext().getRealPath(company.getPreviewImg());//获取公司预览图地址
		File file=new File(path);
		if(file.exists()){//删除公司预览图
			file.delete();
		}
		dao.delById(company.getId(), Company.class);
		dao.delById(userId, Picture.class);
		if(dao.delById(userId, User.class)){
			return "0";
		}else{
			return "1";
		}
	}
	
	/**
	 * 根绝用户id更新用户类型
	 * @param id
	 */
	@At("/admin/user/updateUserTypeById")
	@Ok("raw")
	@Filters(@By(type=CheckSession.class, args={"admin", "/goAdmin.jsp"}))
	public String updateUserTypeById(@Param("id")Integer id,@Param("adminType")Integer adminType,Ioc ioc,HttpServletRequest req){
		User user=new User();
		user.setId(id);
		user.setAdminType(adminType);
		if(dao.update(user)){
			return "0";
		}else{
			return "1";
		}
	}
	
	/**
	 * 修改用户的会员类型
	 */
	@At("/admin/user/userVipManager")
	@Ok("raw")
	@Filters(@By(type=CheckSession.class, args={"admin", "/index.jsp"}))
	public String userVipManager(@Param("id")Integer id,@Param("userType")Integer userType,Ioc ioc,HttpServletRequest req){
		User user=new User();
		user.setId(id);
		user.setUserType(userType);
		if(dao.update(user)){
			return "0";
		}else{
			return "1";
		}
	}
	
	/**
	 * 更新用户审核状态
	 */
	@At("/admin/user/updateExamineStatus")
	@Ok("raw")
	@Filters(@By(type=CheckSession.class, args={"admin", "/index.jsp"}))
	public String updateExamineStatus(@Param("id")Integer id,@Param("examine")Integer examine,Ioc ioc,HttpServletRequest req){
		User user=new User();
		user.setId(id);
		user.setExamine(examine);
		if(dao.update(user)){
			return "0";
		}else{
			return "1";
		}
	}
	
	/**
	 * 更新剩余发帖次数
	 */
	@At("/admin/user/updatePostNum")
	@Ok("raw")
	@Filters(@By(type=CheckSession.class, args={"admin", "/index.jsp"}))
	public String updatePostNum(@Param("id")Integer id,@Param("val")Integer val,Ioc ioc,HttpServletRequest req){
		User user=new User();
		user.setId(id);
		user.setPostNum(val);
		if(dao.update(user)){
			return "0";
		}else{
			return "1";
		}
	}
	
	/**
	 * 分页查询文章
	 * @param currentPage 当前页
	 * @param ioc
	 * @param req
	 * @return
	 */
	@At("/admin/article")
	@Filters(@By(type=CheckSession.class, args={"admin", "/goAdmin.jsp"}))
	public View queryArticleByCondition(@Param("currentPage")Integer currentPage,@Param("selectArticleType")Integer menuTypeId,@Param("date")String date,Ioc ioc,HttpServletRequest req) {
		if(currentPage==null||currentPage==0){currentPage=1;}
		if(menuTypeId==null){menuTypeId=0;}
		if(date==null){date="";}
		int pageSize=SystemContext.PAGE_SIZE;//每页显示十条数据 
		int count=0;//满足条件的数据总数
		Cnd cnd=null;
		if(menuTypeId==0){//用户选择的导航名是不限
			if(date.equals("")){//没有填写日期就直接查
				cnd=Cnd.where("1", "=", "1");
			}else{//用户选择的导航名是不限同时还选择了查询日期
				cnd=Cnd.where("modifyDate", "=", date);
			}
		}else{//用户选择了导航吗名
			if(date.equals("")){//用户选择了导航名但是没有选择日期
				cnd=Cnd.where("typeid", "=", menuTypeId);
			}else{//用户选择了导航名同时还选择了日期
				cnd=Cnd.where("typeid", "=", menuTypeId).and("modifyDate", "=", date);
			}
		}
		cnd.desc("typeid").desc("modifyDate");
		count = dao.searchCount(Article.class,cnd);//获取总文章数
		int maxPage = dao.maxPageSize(count, pageSize);
		if(currentPage>maxPage){
			currentPage=maxPage;
		}
		List<Article> articles =  dao.searchByPage(Article.class,cnd, currentPage, pageSize);
		PageModel<Article> pm = new PageModel<Article>(articles, maxPage);
		req.setAttribute("pm", pm);
		req.setAttribute("menuTypeId", menuTypeId);
		req.setAttribute("date", date);
		req.setAttribute("currentPage", currentPage);
		return new JspView("admin.article");
	}
	
	
	/**
	 *  更新首页新闻图片和广告
	 * @param ioc
	 * @param req
	 * @return
	 */
	@At("/admin/updateNewsAndPoster")
	@Filters(@By(type=CheckSession.class, args={"admin", "/goAdmin.jsp"}))
	public View addNewsAndPoster(Ioc ioc,
			HttpServletRequest req) {
		//为首页大图
		List<NewsTemplate> nts=dao.search(NewsTemplate.class, Cnd.where("type", "=", "1"));
		//为左边新闻图片
		List<NewsTemplate> pts=dao.search(NewsTemplate.class, Cnd.where("type", "=", "2"));		
		//为横向滚动图
		List<NewsTemplate> trundles=dao.search(NewsTemplate.class, Cnd.where("type", "=", "3"));
		req.setAttribute("nts", nts);
		req.setAttribute("pts", pts);
		req.setAttribute("trundles", trundles);
		return new JspView("admin.updateNewsAndPoster");
	}
	
	@At("/admin/updateNews")
	@Ok("raw")
	public String updateNews(@Param("::nt.")NewsTemplate nt, Ioc ioc,HttpServletRequest req){
		
			NewsTemplate newsTemplate = dao.find(nt.getId(), NewsTemplate.class);
			String path=req.getSession().getServletContext().getContextPath();//项目名
			String pathPic = req.getSession().getServletContext().getRealPath("/");
			String realyPic=pathPic.substring(0, pathPic.length()-(path.length()+1))+newsTemplate.getImgUrl();
			String newImgSrc=realyPic.replace("/", File.separator).replace("\\", File.separator);//处理不同系统 文件夹路径分隔符不同的问题
			File file=new File(newImgSrc);
			if(file.exists()){
				file.delete();
			}
		if(dao.update(nt)){
			return "0";
		}else{
			return "1";
		}
	}
	
	/**
	 * 查询所有文章
	 * @param ioc
	 * @param req
	 * @return
	 */
	@At("/admin/queryAllArticle")
	@Filters(@By(type=CheckSession.class, args={"admin", "/goAdmin.jsp"}))
	public View queryAllArticle(Ioc ioc,HttpServletRequest req){
		return queryArticleByCondition(1,0,null,ioc,req);
	}
	
	/**
	 * 保存或更新文章
	 * @param article 文章实体
	 * @return
	 */
	@At("/admin/article/saveOrUpdateArticle")
	@Ok("raw")
	@Filters(@By(type=CheckSession.class, args={"admin", "/goAdmin.jsp"}))
	public String saveOrUpdateArticle(@Param("::article.")Article article,Ioc ioc,HttpServletRequest req){
		article.setAuthor(getAdminName(req));
		article.setModifyDate(new Date());
		article.setUserId(getAdminObj(req).getId());
		article.setUserName(getAdminObj(req).getUsername());
		article.setUserType(getAdminObj(req).getUserType());
		if(article.getId()==null){//保存
			if(dao.save(article)!=null){
				return "0";//保存成功
			}else{
				return "1";//保存失败
			}
		}else{
			if(dao.update(article)){
				return "2";//更新成功
			}else{
				return "3";//更新失败
			}
		}
	}
	
	/**
	 * 根据Id删除文章
	 */
	@At("/admin/article/deleteArticleById")
	@Ok("raw")
	@Filters(@By(type=CheckSession.class, args={"admin", "/goAdmin.jsp"}))
	public String deleteArticleById(@Param("articleId")Integer articleId,Ioc ioc,HttpServletRequest req){
		Article article = dao.find(articleId,Article.class);
		if(article != null){
			DeleteImgByHtml.deleteArticleImgs(req.getSession(),article.getContent());
		}
		if(dao.delById(articleId,Article.class)){
			return "0";//删除成功
		}else{
			return "1";//删除失败
		}
	}
	
	/**
	 * 友情链接管理
	 */
	@At("/admin/friendLinksUI")
	@Filters(@By(type=CheckSession.class, args={"admin", "/goAdmin.jsp"}))
	public View friendLinksUI(Ioc ioc,HttpServletRequest req){
		List<FriendLinks> links =dao.search(FriendLinks.class, Cnd.where("1", "=", "1"));
		req.setAttribute("links", links);
		return new JspView("admin.friendLinks");
	}
	
	/**
	 * 根据编号删除链接
	 */
	@At("/admin/deleteLinkById")
	@Ok("raw")
	@Filters(@By(type=CheckSession.class, args={"admin", "/goAdmin.jsp"}))
	public String deleteLinkById(@Param("id")Integer id, Ioc ioc) {
		if(dao.delById(id,FriendLinks.class)){
			return "0";
		}else{
			return "1";
		}
	}
	
	/**
	 * 保存链接
	 * @return
	 */
	@At("/admin/saveLink")
	@Ok("raw")
	@Filters(@By(type=CheckSession.class, args={"admin", "/goAdmin.jsp"}))
	public String saveLink(@Param("linkName")String linkName,@Param("linkUrl")String linkUrl, Ioc ioc) {
		try{
			FriendLinks link=new FriendLinks();
			link.setLinkName(linkName);
			link.setLinkUrl(linkUrl);
			if(dao.save(link)!=null){
				return "0";
			}else{
				return "1";
			}
		}catch(Exception e){
			e.printStackTrace();
			return "";
		}
	}
	
	/**
	 * 进入测试页面
	 * @param ioc
	 * @param req
	 * @return
	 */
	@At("/admin/test")
	@Filters(@By(type=CheckSession.class, args={"admin", "/goAdmin.jsp"}))
	public View testPage(Ioc ioc,HttpServletRequest req){
		return new JspView("admin.test");
	}
	
	/**
	 * 根据id查找数据
	 * @return
	 */
	@At("/admin/article/find")
	@Ok("json")
	public String getArticleById(@Param("id") int id, Ioc ioc) {
		Article article = dao.find(id, Article.class);
		Gson gson=new Gson();
		return gson.toJson(article);
	}
	
	/**
	 * 根据id号批量删除文章
	 * @param ids 文章id
	 * @throws java.io.IOException
	 */
	@At("/admin/article/batchDeleteArticleById")
	public View batchDeleteArticleById(@Param("ids")String ids,@Param("currentPage")Integer currentPage,@Param("menuTypeId")Integer menuTypeId,@Param("date")String date,Ioc ioc,
			HttpServletRequest req,HttpServletResponse rep) throws IOException {
		dao.deleteByIds(Article.class, ids);
		return queryArticleByCondition(currentPage,menuTypeId,date,ioc,req);
	}
	
	/**
	 * 向session中存放一个值
	 * @param name 属性名
	 * @param value 属性值
	 */
	@At("/admin/saveValueToSession")
	public void saveValueToSession(@Param("name")String name,@Param("value")String value,HttpServletRequest req){
		req.getSession().setAttribute(name, value);
	}
	
	/**
	 * 删除session中指定name的值
	 * @param name
	 * @param req
	 */
	@At("/admin/removeValueByName")
	public void removeValueByName(@Param("name")String name,HttpServletRequest req){
		req.getSession().removeAttribute(name);
	}
	
	/**
	 * 注销用户
	 */
	@At("/admin/logout")
	@Ok("redirect:/admin/login")
	public void logout(HttpServletRequest req){
		req.getSession().invalidate();
	}
}
