<%@page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>风靡网络后台管理系统</title>
		<%@include file="/common/common.jsp"%>
	</head>

	<body id="login">

		<div id="login-wrapper" class="png_bg">
			<div id="login-top">

				<h1>
					Nws Cms Admin
				</h1>
				<!-- Logo (221px width) -->
				<img id="logo" src="${ctx}/images/logo.png"
					alt="Simpla Admin logo" />
			</div>
			<!-- End #logn-top -->

			<div id="login-content">

				<form action="${ctx}/admin/login" method="post">

					<div class="notification information png_bg">
						<div>
							<c:choose>
								<c:when test="${error eq null}">
									请输入用户名和密码后登录
								</c:when>
								<c:otherwise>
									${error}
								</c:otherwise>
							</c:choose>	
						</div>
					</div>

					<p>
						<label>
							Username
						</label>
						<input class="text-input" type="text" name="username"/>
					</p>
					<div class="clear"></div>
					<p>
						<label>
							Password
						</label>
						<input class="text-input" type="password" name="password"/>
					</p>
					<div class="clear"></div>
					<div style="width:100%;height:30px;text-align:center;">
						<input class="button" type="submit" value="登录" style="float:left;width:80px;margin-left:130px;"/>
					</div>
				</form>
			</div>
		</div>
	</body>
</html>