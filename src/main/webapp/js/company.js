/*
 * 加载选择的数据信息
 * @param {Object} prefix
 */
function load(prefix){
	//文章分类
	var typeurl=prefix+"/admin/articletype/all";
	$.post(typeurl,null,function(data){
		var o = eval("("+data+")");
		$(o).each(function(){
			$("#typeid").append("<option value='"+this.id+"'>"+this.name+"</option>");
		});
	},'json');
	
	//文章详细模版信息
	var templateurl = prefix+"/admin/templete/bytype";
	$.post(templateurl,{type:2},function(data){
		var o = eval("("+data+")");
		$(o).each(function(){
			$("#styleid").append("<option value='"+this.id+"'>"+this.templeteName+"</option>");
		});
	},'json');
	
	//获取网站导航的数据信息
	var navurl = prefix+"/admin/navmodel/json";
	$.post(navurl,null,function(data){
		var o = eval("("+data+")");
		var str = process(o[0],'');
		$("#navid").append(str);
		
		//添加搜索框数据
		var s = processdd(o[0],'');
		$("#navid2").append(s);
		
	},'json');
}
/**
 * 设置导航数据显示的样式
 * @param {Object} o
 * @param str
 */
function process(o,str){
	var option="<option value='"+o.id+"'>"+str+o.text+"</option>";
	if(o.children){
		$(o.children).each(function(){
			option += process(this,str+"——");
		});
	}
	return option;
}


///////////////删除 修改//////////////////////////////////
/*
 * 根据id删除数据
 * @param {Object} id
 */
function delById(prefix,id){
	
	if(!window.confirm("你确定要删除这条数据吗？删除过后不可恢复")){
		return ;
	}
	
	var args = location.search;   
    var reg = new RegExp('[\?&]?currentPage=([^&]*)[&$]?', 'gi');   
    var chk = args.match(reg);   
   	var currentPage = RegExp.$1; 
   	
   	var arr = new Array();
   	arr[0] = $("#r"+id);
   	
	var url=prefix+"/admin/article/del";
	
	$.post(url,{"id":id,"currentPage":currentPage},function(data){
		
		var o = eval("("+data+")");
		
		processRemove(arr,o,prefix);
		
	},'json');
	
}
/*
 * 根据多个id删除数据
 * @param {Object} prefix
 */
function delByIds(prefix){
	var args = location.search;   
    var reg = new RegExp('[\?&]?currentPage=([^&]*)[&$]?', 'gi');   
    var chk = args.match(reg);   
   	var currentPage = RegExp.$1;
	var ids="";
	var i=0;
	var arr = new Array();
	$("input[type='checkbox']").each(function(){
		if($(this).attr("checked")){
			if($(this).val()!="on"&&this.name=='xidx'){
				ids += $(this).val()+",";
				arr[i] = $(this).parent().parent();
				i++;
			}
		}
	});
	var dot = ids.lastIndexOf(",");
	if(dot==-1){
		alert("请先选择要删除的数据！");
		return;
	}else{
		ids = ids.substring(0,dot);
	}
	var isCompanyShow=$("#selectIsCompanyShow").val();
	var companyName=$("#companyName").val();
	if(window.confirm("选中了"+i+"条数据，确认删除？")){
		location.href=prefix+"/admin/batchDeleteCompanyById?ids="+ids+"&currentPage="+currentPage+"&isCompanyShow="+isCompanyShow+"&companyName="+companyName;
	}
}

/**
 * 处理删除数据的界面显示
 * @param {Object} arr
 * @param {Object} o
 * @memberOf {TypeName} 
 */
function processRemove(arr,o,prefix){
	
	//在界面上删除那些选中的内容
			$(arr).each(function(){
				$(this).remove();
			});
			//获取剩下的所有tr
			var i=1;
			$("#datalist").find("tr").each(function(){
				//获取所有的td 并重新 给第二个td赋值
				var td = $(this).find("td").slice(1,2);
				$(td).html(i);
				i++;
			});
		
		if(o.length>0){
			for(var j=0;j<o.length;j++){		
				var a = o[j];
				var filter = new FilterHTML();
				var tr = "<tr id='r"+a.id+"'>" +
						"<td><input type='checkbox' value='"+a.id+"'></td>" +
						"<td>1</td>" +
						"<td>"+a.title.substring(0,10)+"</td>" +
						"<td>"+a.keywords.substring(0,8)+"</td>" +
						"<td>"+filter.substring(a.content,0,20)+"...</td>" +
						"<td>"+a.createDate+"</td>" +
						"<td>"+
							"<a href=\"javascript:delById('"+prefix+"',"+a.id+");\" title='删除文章分类信息'><img src='"+prefix+"/images/icons/cross.png' alt='删除文章分类信息' /></a>"+
							"<a href=\"javascript:edit('"+prefix+"',"+a.id+");\" title='修改文章分类信息'><img src='"+prefix+"/images/icons/hammer_screwdriver.png' alt='修改文章分类信息' /></a>"+
						"</td>"+
					"</tr>";
			
				$("#datalist").append(tr);
				i++;
			}
			
		}
}

/*
 * 修改文章信息
 * @param {Object} prefix
 * @param {Object} id
 */
function edit(prefix,id,editor){
	var url=prefix+"/admin/article/find";
	$.getJSON(url,{"id":id},function(data){
			data=$.parseJSON(data);
			$("#articleid").val(data.id);
			$("#title").val(data.title);
			editor.html(data.content);
			$("#keywords").val(data.keywords);
			$("#source").val(data.source);
			$("#articleType").val(data.typeid);
			var a = $('.content-box ul.content-box-tabs li a');
			$(a).parent().siblings().find("a").removeClass('current');
			$(a).addClass('current');
			$("#role").html("修改文章信息");
			$(".default-tab").removeClass('current');
			$("#tab1").hide();
			$("#tab2").show();
	});
	
}
