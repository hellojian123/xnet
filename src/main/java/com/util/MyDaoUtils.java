package com.util;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.nutz.dao.Sqls;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;

public class MyDaoUtils {
	
	/**根据typeid查询总数
	 * @param dao 就收nutdao
	 * @param tableName 要查询的表名字
	 * @param typeid 查询的where条件
	 */
	static Integer QueryTotalByTypeId(NutDao dao, String tableName,Integer typeid){
		Sql sql = Sqls.create("select count(*) c from "+tableName+" where typeid = @typeid");
		sql.params().set("typeid", typeid);
		sql.setCallback(new SqlCallback() {
			public Object invoke(java.sql.Connection conn, ResultSet rs, Sql sql)
					throws SQLException {
				// TODO Auto-generated method stub
				Integer total = null;
				if(rs.next()){
					total = rs.getInt("c");
				}
				
				return total;
			}
		});
		dao.execute(sql);
		return sql.getInt();
	}
	
	
	
}
