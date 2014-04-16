var ioc = {
datasource:{
		type:"org.apache.commons.dbcp.BasicDataSource",
		events:{
			depose:"close"
		},
		fields:{
			driverClassName:"com.mysql.jdbc.Driver",
			url:"jdbc:mysql://127.0.0.1:3306/zsds?useUnicode=true&characterEncoding=utf-8",
			username:"root",
			password:"root"
		}
	},
	dao : {
		type : "org.nutz.dao.impl.NutDao",
		args : [{refer:"datasource"}]
	}
}