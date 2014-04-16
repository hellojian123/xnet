package com.dao;

import javax.sql.DataSource;

import org.nutz.dao.impl.NutDao;
import org.nutz.mvc.Mvcs;

public class Init {
	public static DataSource ds = null;
	public static NutDao dao = null;
	static{
		 ds	 = Mvcs.ctx.getDefaultIoc().get(DataSource.class,"datasource");
		 /*dao = new NutDao(ds);*/
		 dao = Mvcs.ctx.getDefaultIoc().get(NutDao.class,"dao");
	}
}