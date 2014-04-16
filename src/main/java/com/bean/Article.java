package com.bean;

import java.io.Serializable;
import java.util.Date;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Default;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("t_article")
public class Article implements Serializable {

	private static final long serialVersionUID = -3053664411602554289L;
	/*
	 * 数据库ID
	 */
	@Id
	private Integer id;
	
	/**
	 * 国家类型
	 */
	@Column
	private Integer countryType; //1为国内资讯  2为国外资讯
	/*
	 * 发布此文章的userId
	 */
	@Column
	private Integer userId;
	
	/*
	 * 发布此文章的userName
	 */
	@Column
	private String userName;
	
	
	/**
	 * 发布此文章的用户类型
	 */
	/*用户类型	0普通会员  1铂金VIP  2黄金VIP 3钻石VIP */
	@Column
	private Integer userType;
	
	/*
	 * 文章标题
	 */
	@Column
	private String title;
	
	/*
	 * 文章内容
	 */
	@Column
	private String content;
	
	/**
	 * 文章预览图url
	 */
	@Column
	private String previewImg;//景观欣赏  供应信息   求购信息享有此字段
	
	/**
	 * 文章所属分类名字
	 */
	@Column
	private String parentTitle;//文章所属分类名字
	
	/*
	 * 文章关键字
	 */
	@Column
	private String keywords;
	
	/*
	 * 文章来源
	 */
	@Column
	private String source;
	/*
	 * 文章作者
	 */
	@Column
	private String author;
	/*
	 * 修改日期
	 */
	@Column
	private Date modifyDate;
	/*
	 * 点击率
	 */
	@Column
	@Default("0")
	private Integer clickNum;
	
	
	/*-------------以下字段为供求信息特有----------------*/
	/*联系人姓名*/
	@Column
	private String name;
	
	/*公司名*/
	@Column
	private String companyName;
	
	/*公司地址*/
	@Column
	private String address;
	
	/*网址*/
	@Column
	private String website;
	
	/*联系人电话*/
	@Column
	private String phone;
	
	/*邮箱*/
	@Column
	private String email;
	/*---------------------结束-----------------------*/
	
	/*
	 * 文章类型id
	 */
	@Column
	private int typeid;// 1家居装饰     2供应信息    3求购信息      4行业资讯     5展会信息     7产品展示     8精品推荐     9行情走势   10灯饰精品   11成功故事  
	public Integer getId() {        
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public Date getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	public int getTypeid() {
		return typeid;
	}
	public void setTypeid(int typeid) {
		this.typeid = typeid;
	}
	public String getKeywords() {
		return keywords;
	}
	public void setKeywords(String keywords) {
		this.keywords = keywords;
	}
	
	public String getParentTitle() {
		return parentTitle;
	}
	public void setParentTitle(String parentTitle) {
		this.parentTitle = parentTitle;
	}
	public String getPreviewImg() {
		return previewImg;
	}
	public void setPreviewImg(String previewImg) {
		this.previewImg = previewImg;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getUserType() {
		return userType;
	}
	public void setUserType(Integer userType) {
		this.userType = userType;
	}
	public Integer getClickNum() {
		return clickNum;
	}
	public void setClickNum(Integer clickNum) {
		this.clickNum = clickNum;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getCountryType() {
		return countryType;
	}
	public void setCountryType(Integer countryType) {
		this.countryType = countryType;
	}
	
	
	
}
