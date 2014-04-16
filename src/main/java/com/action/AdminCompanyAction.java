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
import org.nutz.mvc.view.JspView;
import com.bean.Company;
import com.bean.User;
import com.google.gson.Gson;
import com.util.CheckSession;
import com.util.PageModel;
import com.util.SystemContext;


public class AdminCompanyAction extends BaseAction{
	
	/*获取当前登录的管理员对象*/
	private User getAdminName(HttpServletRequest req){
		return ((User)(req.getSession().getAttribute("admin")));
	}
	
	/*根据Id删除企业信息,*/
	@At("/admin/deleteCompanyById")
	@Ok("raw")
	@Filters(@By(type=CheckSession.class, args={"admin", "/goAdmin.jsp"}))
	public String deleteCompanyById(@Param("companyId")Integer companyId,Ioc ioc,HttpServletRequest req){
		Company company=dao.find(companyId, Company.class);
		String path=req.getSession().getServletContext().getRealPath(company.getPreviewImg());
		File file=new File(path);
		if(file.exists()){
			file.delete();
		}
		if(dao.delById(companyId,Company.class)){
			return "0";//删除成功
		}else{
			return "1";//删除失败
		}
	}
	
	/**
	 * 进入公司列表
	 */
	@At("/admin/goIntoCompanyList")
	@Filters(@By(type=CheckSession.class, args={"admin", "/goAdmin.jsp"}))
	public View goIntoCompanyList(@Param("currentPage")Integer currentPage,@Param("isCompanyShow")Integer isCompanyShow,@Param("companyName")String companyName, Ioc ioc , HttpServletRequest req){
		if(currentPage==null||currentPage==0){currentPage=1;}
		int pageSize=SystemContext.PAGE_SIZE;//每页显示十条数据 
		int count=0;//满足条件的数据总数
		Cnd cnd=Cnd.where("1", "=", "1");
		if(companyName!=null){
			cnd.and("companyName", "like", "%"+companyName+"%");
		}
		if(isCompanyShow!=3){
			cnd.and("isCompanyShow", "=", isCompanyShow);
		}
		cnd.desc("createDate").desc("isCompanyShow");
		count = dao.searchCount(Company.class,cnd);//获取总文章数
		int maxPage = dao.maxPageSize(count, pageSize);
		if(currentPage>maxPage){
			currentPage=maxPage;
		}
		List<Company> companys =  dao.searchByPage(Company.class,cnd, currentPage, pageSize);
		PageModel<Company> pm = new PageModel<Company>(companys, maxPage);
		req.setAttribute("pm", pm);
		req.setAttribute("currentPage", currentPage);
		req.setAttribute("isCompanyShow", isCompanyShow);
		req.setAttribute("companyName", companyName);
		return new JspView("admin.companyList");
	}
	
	/**
	 * 更改企业审核状态
	 */
	@At("/admin/updateCompanyStatus")
	@Ok("raw")
	@Filters(@By(type=CheckSession.class, args={"admin", "/index.jsp"}))
	public String updateStatus(@Param("id")Integer id,@Param("val")Integer val,Ioc ioc,HttpServletRequest req){
		Company company=new Company();
		company.setId(id);
		company.setIsCompanyShow(val);
		if(dao.update(company)){
			return "0";
		}else{
			return "1";
		}
	}
	
	/**
	 * 通过公司id返回公司json对象
	 */
	@At("/admin/findCompanyById")
	@Ok("json")
	@Filters(@By(type=CheckSession.class, args={"admin", "/index.jsp"}))
	public String findCompanyById(@Param("id")Integer id,Ioc ioc,HttpServletRequest req){
		Company company=dao.find(id,Company.class);
		Gson gson=new Gson();
		String companyStr=gson.toJson(company);
		return companyStr;
	}
	
	/**
	 * 根据id号批量删除企业信息
	 * @param ids
	 * @param currentPage
	 * @param isCompanyShow
	 * @param companyName
	 */
	@At("/admin/batchDeleteCompanyById")
	public View batchDeleteArticleById(@Param("ids")String ids,@Param("currentPage")Integer currentPage,@Param("isCompanyShow")Integer isCompanyShow,@Param("companyName")String companyName,Ioc ioc,
			HttpServletRequest req,HttpServletResponse rep) throws IOException {
		
		dao.deleteByIds(Company.class, ids);
		return goIntoCompanyList(currentPage,isCompanyShow,companyName,ioc,req);
	}
	
	/**
	 * 保存或更新企业信息
	 */
	@At("/admin/saveOrUpdateCompany")
	@Ok("raw")
	@Filters(@By(type=CheckSession.class, args={"admin", "/goAdmin.jsp"}))
	public String saveOrUpdateCompany(@Param("::company.")Company company,Ioc ioc,HttpServletRequest req){
		
		if(company.getId()==null){//保存
			company.setUserId(getAdminName(req).getId());
			company.setUserName(getAdminName(req).getUsername());
			company.setCreateDate(new Date());
			if(dao.save(company)!=null){
				return "0";//保存成功
			}else{
				return "1";//保存失败
			}
		}else{
			if(dao.update(company)){
				return "2";//更新成功
			}else{
				return "3";//更新失败
			}
		}
	}
	
}