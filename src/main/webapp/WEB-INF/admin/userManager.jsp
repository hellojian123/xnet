<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>管理员名单</title>
		<style type="text/css">
			body{
				display: none;
			}
		</style>
		<%@include file="/common/common.jsp"%>
		<script type="text/javascript" src="${ctx}/js/page.js"></script>
		<script charset="utf-8" src="${ctx}/editor/kindeditor-min.js"></script>
		<script charset="utf-8" src="${ctx}/editor/lang/zh_CN.js"></script>
		<script type="text/javascript" src="${ctx}/date/WdatePicker.js"></script>
		<script type="text/javascript">
			$(document).ready(function(){
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
						$("#articletypeid").val(0);
						$("#name").val('');
						$("#role").html("添加注册用户");
						return false; 
					}
				);
			document.body.style.display="block";
			
			$(".updateUserType").change(function(){
					var id=$(this).attr("bianhao");
					var typeValue=$(this).val();
					$.post("${ctx}/admin/user/updateUserTypeById",{id:id,adminType:typeValue},function(data){
						if(data=="1"){//更新失败
							alert("更新失败！");
						}
					});
					return false;
			});
		});
				function delById(userId,username){
					var sure=confirm("确认删除用户名为"+username+"的用户吗？企业列表中也将被删除！");
					if(sure){
						$.post("${ctx}/admin/user/deleteUserById",{userId:userId},function(data){
							if(data=="0"){
								alert("删除成功！");
								location.reload();
							}else{
								alert("删除失败！");
							}
						});
					}
					return false;
				};
				
		var userid="";
		var name="";
		var username="";
		var birthday="";
		var email="";
		var address="";
		var userType="";
		function validate(){
				name=$("#name").val();
				username=$("#username").val();
				if($.trim(name)==""){
					alert("姓名不能为空！");
					return false;
				}
				if($.trim(username)==""){
					alert("用户名不能为空！");
					return false;
				}
				birthday=$("#birthday").val();
				email=$("#email").val();
				address=$("#address").val();
				userType=$("#userType").val();
				return true;
		}
		
		function saveOrUpdateUser(){
			if(validate()){
				userid=$("#userid").val();
				$.post("${ctx}/admin/saveOrUpdateUser",{"user.id":userid,"user.name":name,"user.username":username,"user.birthday":birthday,"user.email":email,"user.address":address,"user.adminType":userType},function(data){
					if(data=="0"){
						alert("添加成功,用户的默认密码是： 123456！");
					}else if(data=="1"){
						alert("添加失败！");
					}else if(data=="2"){
						alert("更新成功！");
					}else if(data=="3"){
						alert("更新失败！");
					}
					location.href="${ctx}/admin/userManagerUI?currentPage=${currentPage}";
				});

			}
		}
		

		</script>
		
	</head>
	<body style="background-image: none;">
			<div id="main-content" style="width: 100%;height: 100%;margin-left: 0px;">
				<div class="content-box">
					<!-- Start Content Box -->

					<div class="content-box-header">

						<h3>
							用户信息
						</h3>

						<ul class="content-box-tabs">
							<li>
								<a href="#tab1" class="default-tab">用户列表</a>
							</li>
							<li>
								<a href="#tab2">新增用户</a>
							</li>
						</ul>

						<div class="clear"></div>

					</div>
					<!-- End .content-box-header -->

					<div class="content-box-content">

						<div class="tab-content default-tab" id="tab1">
							<!-- This is the target div. id must match the href of this div's tab -->

							<div class="notification attention png_bg">
								<a href="#" class="close"><img
										src="${ctx}/images/icons/cross_grey_small.png"
										title="Close this notification" alt="close" />
								</a>
								<div>
									以下是所有用户的信息
								</div>
							</div>

							<table>

								<thead>
									<tr>
										<th>
											序号
										</th>
										<th>
											用户名
										</th>
										<th>
											真实姓名
										</th>
										<th>
											用户级别
										</th>					
										<th>
											注册日期
										</th>
										<th>
											操作
										</th>
									</tr>
								</thead>

								<tfoot>
									<tr>
										<td colspan="4">
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
												<td colspan="5">没有用户信息，请先添加</td>
											</tr>
										</c:when>
										<c:otherwise>
											<c:forEach var="user" items="${pm.result}" varStatus="status">
												<tr id="${user.id}">
													<td>${status.index+1}</td>
													<td>${user.username}</td>
													<td>${user.name}</td>
													<td>
														<c:if test="${sessionScope.admin.adminType==2}">
															<c:if test="${user.adminType==2}">
																	<span style="color:red;font-weight:bold;">超级管理员</span>
															</c:if>
															<c:if test="${user.adminType!=2}">
																<select class="updateUserType" bianhao="${user.id}">
																	<option  value="1" <c:if test="${user.adminType==1}">selected="selected"</c:if>>管理员</option>
																	<option  value="0" <c:if test="${user.adminType==0}">selected="selected"</c:if>>普通用户</option>
																</select>
															</c:if>
														</c:if>
													</td>
													
													<td>
															<fmt:formatDate value="${user.registerDate}" pattern="yyyy/MM/dd"/>
													</td>
													
													<td>
														<c:if test="${user.adminType!=2}">
															<a href="javascript:delById(${user.id},'${user.username}');" title="删除用户信息">
																<img src="${ctx}/images/icons/cross.png" alt="删除用户信息" />
															</a>
														</c:if>
													</td>
												</tr>
											</c:forEach>
										</c:otherwise>
									</c:choose>
									
								</tbody>
							</table>

						</div>
						
						<div class="tab-content" id="tab2">
								<fieldset>
								<form action="${ctx}/admin/article/add" method="post" id="mainform">
									<input type="hidden" value="" id="articleid"/>
									<p>
										<label style="color: red;">
											姓名：
										</label>
										<input class="text-input Medium-input" type="text" id="name" name="name" />

										<span class="input-notification attention png_bg">姓名为必填</span>
										<br />
									</p>
									<p>
										<label style="color: red;">
											用户名：
										</label>
										<input class="text-input Medium-input" type="text" id="username" name="username" />
										<span class="input-notification information png_bg">用户名为必填</span>
										<br />
									</p>
									<p>
										<label>
											生日：
										</label>
										<input class="text-input Medium-input" type="text" onClick="WdatePicker()" id="birthday" name="birthday" />
										<span class="input-notification information png_bg">请输入用户出生日期</span>
										<br/>
									</p>
									<p>
										<label>
											Email：
										</label>
										<input class="text-input Medium-input" type="text" id="email" name="email" />
										<span class="input-notification information png_bg">请输入Email</span>
										<br/>
									</p>
									<label>
											地址：
										</label>
										<input class="text-input Medium-input" type="text" id="address" name="address" />
										<span class="input-notification information png_bg">请输入用户居住地址</span>
										<br/>
									<p>
										<label>
											用户类型：
										</label>
										<select id="userType">
											<option value="0">普通用户</option>
											<option value="1">管理员</option>
										</select>
										<span class="input-notification attention png_bg">不同的用户身份在后台的操作权限是不一样的</span>
										<br/>
									</p>
									<p>
											<input class="button" type="button" style="width:90px;margin-left:70px;" onclick="saveOrUpdateUser()" value="保存"/>
									</p>
									</form>
								</fieldset>
								<div class="clear"></div>
						</div>

						</div>
					</div>
				</div>
					<jsp:include page="bottom.jsp" />
			</div>
	</body>
</html>
