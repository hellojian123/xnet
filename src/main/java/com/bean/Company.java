package com.bean;

import java.io.Serializable;
import java.util.Date;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Default;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * 公司信息
 * @author Administrator
 *
 */ 
@Table("t_company")
public class Company implements Serializable {
	
	private static final long serialVersionUID = -6963695182432959132L;
	
	@Id
	private Integer id;
	
	/*对应的用户编号*/
	@Column
	private Integer userId;
	
	/*对应的用户名*/
	@Column
	private String userName;
	
	/*对应的联系人姓名*/
	@Column
	private String name;
	
	/*公司名*/
	@Column
	private String companyName;
	
	/*公司地址*/
	@Column
	private String address;
	
	/*邮箱*/
	@Column
	private String email;
	
	/*手机号*/
	@Column
	private String telephone;
	
	/*座机号*/
	@Column
	private String homePhone;
	
	/*传真号*/
	@Column
	private String fax;
	
	/*员工人数*/
	@Column
	@Default("0")
	private Integer staffNum;
	
	/*公司介绍*/
	@Column
	private String introduce;
	
	/*经营范围*/
	@Column
	private String businessScope;
	
	/*网址*/
	@Column
	private String webSite;
	
	/*信息创建时间*/
	@Column
	private Date createDate;
	
	/**
	 * 公司预览图
	 */
	@Column
	private String previewImg;
	
	//企业信息是否通过   默认值为0  表示未通过    值为1则是已通过
	@Column
	private Integer isCompanyShow;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public String getHomePhone() {
		return homePhone;
	}
	
	public void setHomePhone(String homePhone) {
		this.homePhone = homePhone;
	}
	
	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public Integer getStaffNum() {
		return staffNum;
	}

	public void setStaffNum(Integer staffNum) {
		this.staffNum = staffNum;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getBusinessScope() {
		return businessScope;
	}

	public void setBusinessScope(String businessScope) {
		this.businessScope = businessScope;
	}


	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Integer getIsCompanyShow() {
		return isCompanyShow;
	}

	public void setIsCompanyShow(Integer isCompanyShow) {
		this.isCompanyShow = isCompanyShow;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPreviewImg() {
		return previewImg;
	}

	public void setPreviewImg(String previewImg) {
		this.previewImg = previewImg;
	}

	public String getWebSite() {
		return webSite;
	}

	public void setWebSite(String webSite) {
		this.webSite = webSite;
	}
	
}
