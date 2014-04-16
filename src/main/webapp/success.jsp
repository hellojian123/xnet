<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>${msg}</title>
    <meta http-equiv="X-UA-Compatible" content="IE=7" />
    <link href="${ctx}/mall/images/logo/ico_1.ico" rel="SHORTCUT ICON">
    <link href="${ctx}/mall/css/Successcss.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/mall/css/index.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/mall/css/ShopCart.css" rel="stylesheet" type="text/css" />
    <link href="${ctx}/mall/css/Login.css" rel="stylesheet" type="text/css" />
    <script src="${ctx}/mall/js/jquery-1.8.3.min.js" type="text/javascript"></script>
    <script src="${ctx}/mall/js/ShopCart.js" type="text/javascript"></script>
    <script src="${ctx}/mall/js/Success.js" type="text/javascript"></script>
</head>
<body>
	  <div class="Shop_one">
         <div class="Shop_one_Z">
               <%-- <jsp:include page="/common/headTop.jsp" /> --%> 
         </div>
      </div>
      <div class="Shop_two">
         <div class="Shop_two_l">
             <img src="${ctx}/mall/images/logo/logo_2.png"  style="cursor: pointer" onclick="javascript:location.href='${ctx}/index'" />
         </div>
         <div class="Shop_two_r"></div>
      </div>
      <div class="Shop_three">
          <span>友情提示</span>
      </div>
        <div class="Success_Z">        
            <span class="Success_text_l"></span>
            <span class="strong">${msg}</span>
        </div>	  
	  <jsp:include page="/WEB-INF/scdn/bottom.jsp" />
</body>
</html>