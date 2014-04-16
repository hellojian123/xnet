<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>风靡网络后台管理系统</title>
		<%@include file="/common/common.jsp"%>
		<script type="text/javascript" src="${ctx}/js/simpla.jquery.configuration.js"></script>
		<script>
			function load(url){
				$("#main-content").html("");
				var iframe = document.createElement("iframe");
				iframe.src = url;
				iframe.setAttribute('frameborder', '0', 0);
				iframe.setAttribute('scrolling', 'no', 0);
				iframe.setAttribute('id','childIframe');
				$("#main-content").html(iframe);
			}
			
			
			
		</script>
		<style type="text/css">
			iframe{
				height:910px;width:100%;
			}
		</style>
	</head>
	<body>
	<div id="body-wrapper">
		<div id="sidebar">
			<div id="sidebar-wrapper">
			<h1 id="sidebar-title"><a href="#">Cms Admin</a></h1>
			<a href="#"><img id="logo" src="${ctx}/images/logo.png" alt="Simpla Admin logo" /></a>
			<div id="profile-links">
				您好, <a href="#" title="编辑你的资料">${sessionScope.admin.username}</a><br />
				<br />
				<a href="${cxt}/" title="点击查看网站首页信息">【网站】</a> | <a href="${ctx}/admin/logout" title="注销">注销</a>
			</div>     
			<ul id="main-nav">
				<li>
					<a href="#" class="nav-top-item">
						首页管理
					</a>
					<ul>
							<li><a href="javascript:load('${ctx}/admin/goInPosterManage?type=1')">广告管理</a></li>
					</ul>
				</li>
				<li>
					<a href="#" class="nav-top-item">
						企业管理
					</a>
					<ul>
							<li><a href="javascript:load('${ctx}/admin/userVipManagerUI?currentPage=1&isCompanyShow=0')">会员管理</a></li>
							<li><a href="javascript:load('${ctx}/admin/goIntoCompanyList?currentPage=1&isCompanyShow=3')">企业列表</a></li>
					</ul>
				</li>
				<li>
					<a href="#" class="nav-top-item">
						内容管理
					</a>
					<ul>
							<li><a href="javascript:load('${ctx}/admin/article?currentPage=1')">文章管理</a></li>
							<li><a href="javascript:load('${ctx}/admin/friendLinksUI')">友情链接管理</a></li>
					</ul>
				</li>
				<li>
					<a href="#" class="nav-top-item">
						我的资料
					</a>
					<ul>
							<li><a href="javascript:load('${ctx}/admin/user/updatepwdUI')">修改密码</a></li>
					</ul>
				</li>
				<c:if test="${admin.adminType==2}">
					<li>
						<a href="#" class="nav-top-item">
							系统设置
						</a>
						<ul>
							<li><a href="javascript:load('${ctx}/admin/userManagerUI?curPage=1');">用户列表</a></li>
						</ul>
					</li>    
				</c:if>
			</ul>
		</div>
		</div>
		
		<!-- 内容显示区域 -->		
		<div id="main-content">
			<noscript>
				<div class="notification error png_bg">
					<div>
						你的浏览器不支持JavaScript,请打开你的浏览器的脚本支持。或者下载firfox\ie7+\chrome等浏览器
					</div>
				</div>
			</noscript>
			
			<h2>欢迎${sessionScope.admin.username}使用风靡网络后台管理系统</h2>
			<p id="page-intro">您的身份是:
				<c:if test="${sessionScope.admin.adminType==1}">
					普通管理员
				</c:if>
				<c:if test="${sessionScope.admin.adminType==2}">
					超级管理员
				</c:if>
			</p>
			
			<ul class="shortcut-buttons-set">
				
				<li><a class="shortcut-button" href="javascript:load('${ctx}/admin/updateNewsAndPoster')"><span>
					<img src="${ctx}/images/icons/image_add_48.png" alt="icon" /><br />
					更新首页图片
				</span></a></li>
				
				<li><a class="shortcut-button" href="javascript:load('${ctx}/admin/article')"><span>
					<img src="${ctx}/images/icons/pencil_48.png" alt="icon" /><br />
					发布文章
				</span></a></li>
				
				<li><a class="shortcut-button" href="javascript:load('${ctx}/admin/user/updatepwdUI')"><span>
					<img src="${ctx}/images/icons/paper_content_pencil_48.png" alt="icon" /><br />
					修改密码
				</span></a></li>
				
			</ul>
			
			<div class="clear"></div>
			<div class="content-box">
				<div class="content-box-header">				
					<h3>我的资料</h3>
					<div class="clear"></div>
				</div>
				<div class="content-box-content">
					<div class="tab-content default-tab" id="tab1">
						<form action="#" method="post">
							<fieldset>
								<p>
									<label>上次登录时间：</label>
									<input class="text-input medium-input" type="text" value='<fmt:formatDate value="${sessionScope.admin.lastLoginTime}" pattern="yyyy/MM/dd HH:mm:ss"/>' readonly="readonly"/> 
									<span class="input-notification information png_bg">这里记录了你上次登录的时间，如有不同请及时修改你的密码</span>
									<!-- Classes for input-notification: success, error, information, attention -->
									<br />
								</p>
								<p>
									<label>上次登录IP：</label>
									<input class="text-input medium-input" type="text" value="${sessionScope.admin.lastLoginIp}" readonly="readonly"/> 
									<span class="input-notification information png_bg">上次登录的IP地址信息</span>
								</p>
								<p>
									<label>登录次数</label>
									<input class="text-input medium-input" type="text" value="${sessionScope.admin.loginNum}" readonly="readonly"/>
									<span class="input-notification information png_bg">记录了你总共登录的次数</span>
								</p>
							</fieldset>
							<div class="clear"></div>
							
						</form>
					</div>
				</div>
			</div>
			<jsp:include page="bottom.jsp" />
			</div>
		</div>
	</body>
</html>