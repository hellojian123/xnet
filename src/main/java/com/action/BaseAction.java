package com.action;

import com.dao.BaseDao;

public class BaseAction {
	//初始化Dao层
	protected BaseDao dao=null;
	public BaseAction(){
		dao=new BaseDao();
	}
}
