package com.bean;

import java.io.Serializable;
import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Default;
import org.nutz.dao.entity.annotation.Id;
import org.nutz.dao.entity.annotation.Table;

/**
 * 广告模板
 * @author Administrator
 *
 */
@Table("t_newsAndPoster")
public class NewsTemplate implements Serializable {
	private static final long serialVersionUID = 8104366207698775954L;
	@Id
	private int id;
	@Column
	private String title;//图片标题
	@Column
	private String imgUrl;//图片地址
	@Column
	private String newsLink;//图片所指向的链接
	@Column
	private int type;//1 为1号广告区,2 为2号广告区,3 为3号广告区,4 为4号广告区,5 为5号广告区,6为6号广告区,7为7号广告区,8为文章详情和文章列表右下角的  广告位
	@Column
	private Integer isStatus;	//广告招商   1： 已经招商，0：未招商
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getNewsLink() {
		return newsLink;
	}
	public void setNewsLink(String newsLink) {
		this.newsLink = newsLink;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public void setIsStatus(int isStatus) {
		this.isStatus = isStatus;
	}
	public int getIsStatus() {
		return isStatus;
	}
}
