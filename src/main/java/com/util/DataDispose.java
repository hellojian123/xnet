package com.util;

public class DataDispose {
	public static Integer getRightCurPage(Integer curPage){
		if(curPage==null||curPage<1){
			return 1;
		}
		return curPage;
	}
}
