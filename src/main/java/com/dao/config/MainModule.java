package com.dao.config;

import com.action.BaseAction;
import org.nutz.mvc.annotation.Encoding;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.IocBy;
import org.nutz.mvc.annotation.Modules;
import org.nutz.mvc.ioc.provider.JsonIocProvider;
import com.util.ValidationCode;

@Fail("jsp:404")
@IocBy(type=JsonIocProvider.class,args={"/com/dao/config/datasource.js",
	"*org.nutz.ioc.loader.annotation.AnnotationIocLoader", "com.action"
})
//nutz会扫描MainModule所在的package及直接声明的那些Module所在的package，  如果我有五个package下都存在子模块的话  那我在@Modules的value里至少得写五个子模块的class
@Modules(value = {ValidationCode.class,BaseAction.class},scanPackage = true)
@Encoding(input="UTF-8",output="UTF-8")
public class MainModule {

}