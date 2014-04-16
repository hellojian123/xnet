package com.util;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;

public class DeleteImgByHtml {
	
	/**
	 * 删除文章中的图片 返回删除失败的图片集合
	 */
	public static List<String> deleteArticleImgs(HttpSession session,String htmlStr) {
		List<String> faildImgSrcs=new ArrayList<String>();//统计删除失败的图片集合
		List<String> imgSrcs=getImgStr(htmlStr);//获取文章中的图片路径
		for (String imgSrc : imgSrcs) {
			String path=session.getServletContext().getContextPath();//项目名
			String projectPath=session.getServletContext().getRealPath("/");//项目路径
			String realPath=projectPath.substring(0, projectPath.length()-path.length());//项目所在的web容器路径
			String imgRealPath=realPath.substring(0, realPath.length()-1)+imgSrc;//图片路径
			String newImgSrc=imgRealPath.replace("/", File.separator).replace("\\", File.separator);//处理不同系统 文件夹路径分隔符不同的问题
			File file=new File(newImgSrc);
			if(file.exists()){
				file.delete();
			}else{
				faildImgSrcs.add(newImgSrc);
			}
		}
		return faildImgSrcs;
	}
	
	/**
	 * 从html代码中获取图片标签的src路径
	 */
	public static List<String> getImgStr(String htmlStr) {
		String regxpForImgTag = "<\\s*img\\s*(?:[^>]*)src\\s*=\\s*([^>]+)";
		String img = "";
		Pattern p_image;
		Matcher m_image;
		List<String> pics = new ArrayList<String>();
		p_image = Pattern.compile(regxpForImgTag,  
	            Pattern.CASE_INSENSITIVE | Pattern.MULTILINE);
		m_image = p_image.matcher(htmlStr);
		while (m_image.find()) {
			img = img + "," + m_image.group();
			Matcher m = p_image.matcher(img);
			while (m.find()) {
				String tempSrc= m.group(1);
				String src=tempSrc.substring(1, tempSrc.length()-10);
				pics.add(src);
			}
		}
		return pics;
	}
}






