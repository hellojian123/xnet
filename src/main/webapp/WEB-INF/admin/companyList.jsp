<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<style type="text/css">
			body{
				display: none;
			}
			iframe{
				height:1910px;width:100%;
			}
		</style>
		<title>文章管理</title>
		<%@include file="/common/common.jsp"%>
		<script type="text/javascript" src="${ctx}/js/page.js"></script>
		<script type="text/javascript" src="${ctx}/js/FilterHtml.js"></script>
		<script type="text/javascript" src="${ctx}/date/WdatePicker.js"></script>
		<script type="text/javascript" src="${ctx}/js/company.js"></script>
		<script charset="utf-8" src="${ctx}/editor/kindeditor.js"></script>
		<script charset="utf-8" src="${ctx}/editor/lang/zh_CN.js"></script>
		<script type="text/javascript" src="${ctx}/js/ajaxfileupload.js"></script>
		<script type="text/javascript">
		var editor;
		KindEditor.ready(function(K){
				editor = K.create('#introduce', {
					uploadJson : '${ctx}/editor/jsp/upload_json.jsp',
					fileManagerJson : '${ctx}/editor/jsp/file_manager_json.jsp',
					allowPreviewEmoticons : true,
					allowFileManager : true
				});
		});
		
		var companyId="";
		var companyName="";
		var name="";
		var address="";
		var email="";
		var telephone="";
		var homePhone="";
		var webSite="";
		var fax="";
		var staffNum="";
		var businessScope="";
		var introduce="";
		var imgUrlAddress="";
		function validate(){
				companyName=$("#enterpriseName").val();
				name=$("#name").val();
				address=$("#address").val();
				email=$("#email").val();
				telephone=$("#telephone").val();
				homePhone=$("#homePhone").val();
				webSite=$("#webSite").val();
				fax=$("#fax").val();
				staffNum=$("#staffNum").val();
				businessScope=$("#businessScope").val();
				introduce=editor.html();
				imgUrlAddress=$("#imgUrlAddress").attr("src");
				if($.trim(companyName)==""){
					alert("公司名不能为空！");
					return false;
				}
				if($.trim(name)==""){
					alert("联系人不能为空！");
					return false;
				}
				if($.trim(address)==""){
					alert("地址不能为空！");
					return false;
				}
				if($.trim(telephone)==""){
					alert("手机号不能为空！");
					return false;
				}
				
				if($.trim(businessScope)==""){
					alert("经营范围不能为空！");
					return false;
				}
				
				if($.trim(introduce)==""){
					alert("公司介绍不能为空！");
					return false;
				}
				return true;
		}
		
		function saveOrUpdateCompany(){
			if(validate()){
				companyId=$("#companyId").val();
				$.post("${ctx}/admin/saveOrUpdateCompany",{"company.id":companyId,"company.name":name,"company.companyName":companyName,"company.webSite":webSite,"company.address":address,"company.email":email,"company.telephone":telephone,"company.homePhone":homePhone,"company.fax":fax,"company.staffNum":staffNum,"company.introduce":introduce, "company.previewImg":imgUrlAddress,"company.businessScope":businessScope},function(data){
					if(data=="0"){
						alert("添加成功！");
					}else if(data=="1"){
						alert("添加失败！");
					}else if(data=="2"){
						alert("更新成功！");
					}else if(data=="3"){
						alert("更新失败！");
					}
					
					location.href="${ctx}/admin/goIntoCompanyList?currentPage=${currentPage}&isCompanyShow=${isCompanyShow}&companyName=${companyName}";
				});
			}
		}
		
			$(document).ready(function(){
				$(window.parent.document.getElementById("childIframe")).height(1500);
				$('.content-box .content-box-content div.tab-content').hide();
				$('ul.content-box-tabs li a.default-tab').addClass('current');
				$('.content-box-content div.default-tab').show(); 
				$('.content-box ul.content-box-tabs li a').click( // When a tab is clicked...
					function() { 
						$(this).parent().siblings().find("a").removeClass('current'); // Remove "current" class from all tabs
						$(this).addClass('current'); // Add class "current" to clicked tab
						var currentTab = $(this).attr('href'); // Set variable "currentTab" to the value of href of clicked tab
						$(currentTab).siblings().hide(); // Hide all content divs
						$(currentTab).show(); // Show the content div with the id equal to the id of clicked tab
						//$("#role").html("添加新文章");
						return false; 
					}
				);
				$('tbody tr:even').addClass("alt-row");
				$('.check-all').click(
					function(){
						$(this).parent().parent().parent().parent().find("input[type='checkbox']").attr('checked', $(this).is(':checked'));   
					}
				);
				
				$(".close").click(
					function () {
						$(this).parent().fadeTo(400, 0, function () { // Links with the class "close" will close parent
							$(this).slideUp(400);
						});
						return false;
					}
				);
				//给导航名赋值   使其选中
				$("#selectArticleType").val(${menuTypeId});
				
				$(".deleteCompany").click(function(){
					var companyId=$(this).attr("companyId");
					var companyName=$(this).attr("companyName");
			    	var sure=confirm("确认删除企业名称为'"+companyName+"'的数据？");
					if(sure){
						$.post("${ctx}/admin/deleteCompanyById",{companyId:companyId},function(data){
								if(data=="0"){
									alert("删除成功！");
									var isCompanyShow=$("#selectIsCompanyShow").val();
									var companyName=$("#companyName").val();
									location.href="${ctx}/admin/batchDeleteCompanyById?currentPage="+${currentPage}+"&isCompanyShow="+isCompanyShow+"&companyName="+companyName;
								}else{
									alert("删除失败！");
								}
						});
					}
					return false;
				});
				//企业是否通过
				$(".isCompanyShow").blur(function(){
				 	var id=$(this).attr("bianhao");
				 	var val=$(this).val();
				    $.post("${ctx}/admin/updateCompanyStatus",{id:id,val:val},function(data){
				    	if(data!="0"){
							alert("系统错误，更新失败！");
						}
				    });
				 });
				document.body.style.display="block";
			});	
		
			
			function edit(prefix,id){
				var url=prefix+"/admin/findCompanyById";
				$.getJSON(url,{"id":id},function(data){
						data=$.parseJSON(data);
						$("#companyId").val(id);
						$("#enterpriseName").val(data.companyName);
						$("#name").val(data.name);
						$("#address").val(data.address);
						$("#webSite").val(data.webSite);
						$("#email").val(data.email);
						$("#telephone").val(data.telephone);
						$("#homePhone").val(data.homePhone);
						$("#fax").val(data.fax);
						$("#staffNum").val(data.staffNum);
						$("#businessScope").val(data.businessScope);
						$("#introduce").val(data.introduce);
						$("#imgUrlAddress").attr("src",data.previewImg);
						editor.html(data.introduce);
						var a = $('.content-box ul.content-box-tabs li a');
						$(a).parent().siblings().find("a").removeClass('current');
						$(a).addClass('current');
						$("#role").html("更新企业信息");
						$(".default-tab").removeClass('current');
						$("#tab1").hide(); 
						$("#tab2").show();
				});
			}
			
		function uploadimage(file){
			$.ajaxFileUpload({
				url:'${ctx}/editor/jsp/upload_json.jsp',
				secureuri:false,
				fileElementId:"imgUrl",
				dataType: 'json',				
				success: function (data, status)
				{
					if(data.error==0){//成功
						$("#imgUrlAddress").attr("src",data.url);
						alert("上传成功！");
					}else{//失败
						alert(data.message);
					}
				},
				error: function (data, status, e)
				{
					alert(e);
				}
			});
			return false;
		}
			
			
		</script>
		<style type="text/css">
			label{
				float: left;
			}
		</style>
	</head>
	<body style="background-image: none;">
			<div id="main-content" style="width: 100%;height: 100%;margin-left: 0px;">
				<div class="content-box">
					<div class="content-box-header">
						<h3>
							企业管理
						</h3>
						<ul class="content-box-tabs">
							<li>
								<a href="#tab1" class="default-tab">企业列表</a>
							</li>
							<li>
								<a href="#tab2" id="role">添加企业</a>
							</li>
						</ul>

						<div class="clear"></div>

					</div>
					<div class="content-box-content">
						<div class="tab-content default-tab" id="tab1">
							<div class="notification attention png_bg">
								<a href="#" class="close">
									<img src="${ctx}/images/icons/cross_grey_small.png" title="Close this notification" alt="close" />
								</a>
								<div>						   
								<form action="${ctx}/admin/goIntoCompanyList" method="get">
									<input type="hidden" name="currentPage" value="${currentPage}">
									审核是否通过：
									<select id="selectIsCompanyShow" name="isCompanyShow">
											<option value="3" >全部</option>
											<option value="1" >已通过</option>
											<option value="0" >未通过</option>
									</select>
									<script>
										$("#selectIsCompanyShow").val(${isCompanyShow});
									</script>
									&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
									 企业名称：<input class="Medium-input" type="text" id="companyName" style="width:90px" name="companyName" size="8" value="${companyName}" />
										<input type="submit" value="查  询 " class="button"/>
								</form>
									
								</div>
							</div>

							<table>
								<thead>
									<tr>
										<th>
											<input class="check-all" type="checkbox" />
										</th>
										<th>
											序号
										</th>
										<th>
											企业名称
										</th>
										<th>
											联系人姓名
										</th>
										<th>
											审核是否通过
										</th>
										<th>
											创建时间
										</th>
										<th>
											操作
										</th>
									</tr>

								</thead>

								<tfoot>
									<tr>
										<td colspan="7">
											<div class="bulk-actions align-left">
												<a class="button" href="javascript:delByIds('${ctx}');">删除选定</a>
											</div>
											<!-- 分页信息 -->
											<div class="pagination"> 
												<c:if test="${pm.result ne null}">
													<script>
														var pg = new showPages('pg');
														pg.pageCount = ${pm.maxPage};
														pg.argName = 'currentPage';
														pg.printHtml();
													</script>
												</c:if>
											</div>
											
											<div class="clear"></div>
										</td>
									</tr>
								</tfoot>
								<!-- 数据展示 -->
								<tbody id="datalist">
									<c:choose>
										<c:when test="${pm.result eq null}">
											<tr>
												<td colspan="7">没有查找到企业信息！</td>
											</tr>
										</c:when>
										<c:otherwise>
											<c:forEach var="company" items="${pm.result}" varStatus="status">
												<tr id="r${article.id}">
													<td><input type="checkbox" name="xidx" id="companyId" value="${company.id}"/></td>
													<td>${status.index+1}</td>
													<td>${company.companyName}</td>
													<td>${company.name}</td>
													<td>
															<select class="isCompanyShow" bianhao="${company.id}">
																<option value="1" <c:if test="${company.isCompanyShow==1}">selected="selected"</c:if>>已通过</option>
																<option value="0" <c:if test="${company.isCompanyShow==0}">selected="selected"</c:if>>未通过</option>
															</select>
													</td>
													<td>
														<fmt:formatDate value="${company.createDate}" pattern="yyyy/MM/dd"/>
													</td>												
													<td>
														<%-- <a href="javascript:void(0)" class="deleteCompany" companyId="${company.id}" companyName="${company.companyName}" title="删除此信息">
															<img src="${ctx}/images/icons/cross.png" />
														</a> --%>
														<a href="javascript:edit('${ctx}',${company.id})" title="查看企业信息"><img src="${ctx}/images/icons/hammer_screwdriver.png" /></a>
													</td>
												</tr>
											</c:forEach>
										</c:otherwise>
									</c:choose>
								</tbody>
							</table>
						</div>
						<!-- End #tab1 -->

						<div class="tab-content" id="tab2">
								<fieldset>
								<form action="${ctx}/admin/article/add" method="post" id="mainform">
									<input type="hidden" value="" id="companyId"/>
									<p>
										<label style="color:red">
											企&nbsp;业&nbsp;名&nbsp;称：
										</label>
										<input class="text-input Medium-input" type="text" id="enterpriseName" />
										<span class="input-notification attention png_bg">企业名称为必填！
										</span>
										<br />
									</p>
									
									<p>
										<label style="color: red;">
											联系人姓名：
										</label>
										<input class="text-input Medium-input" type="text" id="name"   />
										<span class="input-notification attention png_bg">联系人姓名为必填！</span>
										<br />
									</p>
									
									
									<p>
										<label style="color: red;">
											地&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址：
										</label>
										<input class="text-input Medium-input" type="text" id="address"   />
										<span class="input-notification attention png_bg">地址为必填！</span>
										<br />
									</p>
									
									<p>
										<label>
											网&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址：
										</label>
										<input class="text-input Medium-input" type="text" id="webSite"/>
										<span class="input-notification attention png_bg">网址为必填！</span>
										<br />
									</p>
									
									<p>
										<label>
											邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱：
										</label>
										<input class="text-input Medium-input" type="text" id="email"   />
										<span class="input-notification information png_bg">联系人邮箱！</span>
										<br />
									</p>
									
									<p>
										<label style="color: red;">
											手&nbsp;&nbsp;&nbsp;&nbsp;机&nbsp;&nbsp;&nbsp;&nbsp;号：
										</label>
										<input class="text-input Medium-input" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" type="text" id="telephone"   />
										<span class="input-notification attention png_bg">手机号为必填！</span>
										<br />
									</p>
									
									<p>
										<label>
											座&nbsp;&nbsp;&nbsp;&nbsp;机&nbsp;&nbsp;&nbsp;&nbsp;号：
										</label>
										<input class="text-input Medium-input" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" type="text" id="homePhone"   />
										<span class="input-notification information png_bg">企业座机号！</span>
										<br />
									</p>
									
								    <p>
										<label>
											传&nbsp;&nbsp;&nbsp;&nbsp;真&nbsp;&nbsp;&nbsp;&nbsp;号：
										</label>
										<input class="text-input Medium-input" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" type="text" id="fax"   />
										<span class="input-notification information png_bg">企业传真号！</span>
										<br />
									</p>
									
								    <p>
										<label>
											员&nbsp;&nbsp;工&nbsp;&nbsp;人&nbsp;数：
										</label>
										<input class="text-input Medium-input" onkeyup="this.value=this.value.replace(/\D/g,'')" onafterpaste="this.value=this.value.replace(/\D/g,'')" type="text" id="staffNum"   />
										<span class="input-notification information png_bg">企业员工人数！</span>
										<br />
									</p>
									
									
								   <p>
										<label style="color: red;">
											经&nbsp;&nbsp;营&nbsp;&nbsp;范&nbsp;围：
										</label>
										<input class="text-input Medium-input" type="text" id="businessScope"   />
										<span class="input-notification attention png_bg">经营范围为必填！</span>
										<br />
									</p>
									
									<p>
										<label style="color: red;">
											公&nbsp;司&nbsp;介&nbsp;绍：
										</label>
											<textarea style="width:100%;height:400px" id="introduce">
												
											</textarea>
										<span class="input-notification attention png_bg">公司介绍为必填！</span>
										<br/>
									</p>
									<P>	
											<label style="color: red;">
												公&nbsp;司&nbsp;预&nbsp;览&nbsp;图：
											</label>
											<input style="width:137px;"  class="testInput" name="imgUrl" id="imgUrl" type="file" value='${poster.imgUrl}'/> 
											<input class="button setBtnWidth" type="button" value="上传" onclick="uploadimage()" style="width:50px;" id="uploadImg"/>
											<br/>
											<img src="" width="200px" height="200px" style="border:1px #555 solid;" id="imgUrlAddress" name="imgUrlAddress" />
									</P>
									
									<p>
											<input class="button" type="button" style="width:90px;margin-left:70px;" onclick="saveOrUpdateCompany()" value="保存"/>
									</p>
									</form>
								</fieldset>
								<div class="clear"></div>
						</div>
					</div>
				</div>
				<jsp:include page="bottom.jsp" />
			</div>
			<script type="text/javascript">
				load('${ctx}');
			</script>
	</body>
</html>
