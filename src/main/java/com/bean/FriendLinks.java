package com.bean;

import java.io.Serializable;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

@Table("t_friendLinks")
public class FriendLinks implements Serializable{
	
	private static final long serialVersionUID = 5208538536010229487L;
	
	/**
	 * 链接编号
	 */
	@Id
	private Integer id;
	/*
	 * 链接名
	 */
	@Column
	private String linkName;
	
	/*
	 * 链接地址
	 */
	@Column
	private String linkUrl;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	
}
