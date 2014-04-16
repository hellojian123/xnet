<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>密码修改</title>
		<%@include file="/common/common.jsp"%>
		<script type="text/javascript" src="${ctx}/js/jquery-1.9.1.min.js"></script>
		<script type="text/javascript">

				$(document).ready(function(){	
					var newp="";
					var newpwd="";
					var oldpwd="";
					function validate(){
					 	newp=$("#newp").val();
						newpwd=$("#newpwd").val();
						oldpwd=$("#oldpwd").val();
						if(oldpwd==""){
							document.getElementById("pwdo").innerHTML="旧密码不能为空";
							return false;
						}
						if(newp==""){
							document.getElementById("newpwdd").innerHTML="请输入新密码";
							return false;
						}
						if(newp!=newpwd){
							document.getElementById("newpd").innerHTML="两次密码输入不一致";
							return false;
						}
						return true;
					}
					$("#updatePwd").click(function(){
						if(validate()){
							var userId=$("#userId").val();
							$.post("${ctx}/admin/user/updatepwd",{userId:userId,oldpwd:oldpwd,newPwd:newpwd},function(data){
								if(data=="0"){
									alert("密码修改成功！");
									location.reload();
								}else{
									alert("旧密码输入错误！");
								}
							});
						}
					});
		});
		</script>
	</head>

	<body style="background-image: none;">
		<div id="main-content">
			<form id="dataform"  method="post">
				<input type="hidden" id="userId" value="${sessionScope.admin.id}" />
				<fieldset>
					<!-- Set class to "column-left" or "column-right" on fieldsets to divide the form into columns -->
					<p>
						<label>
							管理员登录名
						</label>
						<input class="text-input medium-input" type="text" id="small-input" name="user.username"  value="${sessionScope.admin.username}" readonly="readonly"/>
						<!-- Classes for input-notification: success, error, information, attention -->
						<br/>
					</p>
					<p>
						<label>
							旧密码
						</label>
						<input class="text-input medium-input" type="password" name="oldpwd" value="" id="oldpwd"/>
						<span class="input-notification information png_bg" id="pwdo">请输入旧密码</span>
					</p>
					<p>
						<label>
							新密码
						</label>
						<input class="text-input medium-input" type="password" name="user.password" id="newp"/>
						<span class="input-notification information png_bg" id="newpwdd">请输入新密码</span>
					</p>
					<p>
						<label>
							确认新密码
						</label>
						<input class="text-input medium-input" type="password" id="newpwd"/>
						<span class="input-notification information png_bg" id="newpd">请再次输入新密码</span>	
					</p>

					<p>
						<input class="button" type="button" value="修改" id="updatePwd" />
					</p>

				</fieldset>

				<div class="clear"></div>
				<!-- End .clear -->

			</form>

		</div>
		<!-- End #tab2 -->

		<!-- End .content-box -->
		<div class="clear"></div>
		<jsp:include page="bottom.jsp" />
	</body>
</html>
