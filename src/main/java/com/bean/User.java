package com.bean;

import java.io.Serializable;
import java.util.Date;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Default;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

//使用Nutz做对象影射时属性与属性类型必须与数据库保持一致
/**
 * 企业用户信息
 * @author Administrator
 *
 */
@Table("t_user")
public class User implements Serializable{
	private static final long serialVersionUID = 8763446770756204524L;
	@Id
	private Integer id;
	
	@Column
	private Integer companyId;//公司id
	
	@Column
	private String  name;   //会员真实姓名

	@Column
	private String username;//用户名
	
	@Column
	private String password;//密码
	
	@Column
	private Date   birthday;//生日
	
	@Column
	private Integer postNum;//剩余发帖次数	
	
	/*  由于以下四个字段 与t_company表重复    故均未使用    */
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
	
	/*
	 * 上次登录时间				
	 */
	@Column
	private Date lastLoginTime;
	/*
	 * 本次登录时间
	 */
	@Column
	private Date currentLoginTime;
	/*
	 * 上次登录IP
	 */
	@Column
	private String lastLoginIp;
	/*
	 * 本次登录IP
	 */
	@Column
	private String currentLoginIp;
	
	/*
	 * 登录次数
	 */
	@Column
	@Default("0")
	private Integer loginNum;
	
	/*用户类型	0普通会员	1钻石VIP	2黄金VIP  3铂金VIP */
	@Column
	private Integer userType;
	
	/* mysql中0代表false  1代表true */
	
	/*会员审核是否通过    0未通过   1已通过*/
	@Column
	private Integer examine;
	
	/*注册日期*/
	@Column
	private Date registerDate;
	
	/*管理员类型*/
	/*用户类型	0非管理员	1管理员	2超级管理员 */
	@Column
	private Integer adminType;
	
	
	
	public Integer getAdminType() {
		return adminType;
	}
	public void setAdminType(Integer adminType) {
		this.adminType = adminType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Date getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	public String getLastLoginIp() {
		return lastLoginIp;
	}
	public void setLastLoginIp(String lastLoginIp) {
		this.lastLoginIp = lastLoginIp;
	}
	public int getUserType() {
		return userType;
	}
	public void setUserType(int userType) {
		this.userType = userType;
	}
	public Date getCurrentLoginTime() {
		return currentLoginTime;
	}
	public void setCurrentLoginTime(Date currentLoginTime) {
		this.currentLoginTime = currentLoginTime;
	}
	public String getCurrentLoginIp() {
		return currentLoginIp;
	}
	public void setCurrentLoginIp(String currentLoginIp) {
		this.currentLoginIp = currentLoginIp;
	}
	public int getLoginNum() {
		return loginNum;
	}
	public void setLoginNum(int loginNum) {
		this.loginNum = loginNum;
	}

	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	public Integer getExamine() {
		return examine;
	}
	public void setExamine(Integer examine) {
		this.examine = examine;
	}
	public void setLoginNum(Integer loginNum) {
		this.loginNum = loginNum;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public Integer getPostNum() {
		return postNum;
	}
	public void setPostNum(Integer postNum) {
		this.postNum = postNum;
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
	public Integer getCompanyId() {
		return companyId;
	}
	public void setCompanyId(Integer companyId) {
		this.companyId = companyId;
	}
}
