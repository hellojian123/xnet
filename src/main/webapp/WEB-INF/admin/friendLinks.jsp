<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>密码修改</title>
		<%@include file="/common/common.jsp"%>
		<script type="text/javascript">
			var linkName="";
			var linkUrl="";
			function validate(){
					linkName=$("#linkName").val();
					linkUrl=$("#linkUrl").val();
					if(linkName==""){
						alert("链接名称不能为空！");
						return false;
					}
					if(linkUrl==""){
						alert("链接地址不能为空！");
						return false;
					}
					return true;
			}
			$(document).ready(function(){
				$(".deleteLink").click(function(){
					var sure=confirm("确认删除？");
					if(sure){
						var id=$(this).attr("id");
						
						$.post("${ctx}/admin/deleteLinkById",{id:id},function(data){
							if(data=="0"){
								alert("删除成功！");
								location.reload();
							}else{
								alert("删除失败！");
							}
						});
					}
					return false;
				});
				
				//保存链接
				$("#saveLink").click(function(){
					var tf=validate();
					if(tf){
						$.post("${ctx}/admin/saveLink",{linkName:linkName,linkUrl:linkUrl},function(data){
							if(data=="0"){
								alert("添加链接成功！");
								location.reload();
							}else{
								alert("添加链接失败！");
							}
						});
					}		
					return false;
				});
			});
			
		</script>
	</head>
<body style="background-image: none;">
			<div id="main-content" style="width: 100%;height: 100%;margin-left: 0px;">
				<div class="content-box">
					<div class="content-box-header">
						<h3>
							友情链接	
						</h3>
						<div class="clear"></div>
					</div>
					<div class="content-box-content">
						<div class="tab-content default-tab" id="tab1">
							<table>
								<thead>
									<tr>
										<th>
											序号
										</th>
										<th>
											链接名
										</th>
										<th>
											链接URL
										</th>
										<th>
											操作
										</th>
									</tr>

								</thead>
								<!-- 数据展示 -->
								<tbody id="datalist">
									<c:choose>
										<c:when test="${links eq null}">
											<tr>
												<td colspan="7">没有链接信息，请先添加</td>
											</tr>
										</c:when>
										<c:otherwise>
											<c:forEach var="link" items="${links}" varStatus="st">
												<tr <c:if test="${st.index%2==0}">class="alt-row"</c:if>>
													<td>${st.index+1}</td>
													<td>${link.linkName}</td>
													<td>${link.linkUrl}</td>
													<td>
														<a href="javascript:void(0)" class="deleteLink" id="${link.id}" title="删除文章">
															<img src="/ynml/images/icons/cross.png" alt="删除文章">
														</a>
													</td>
												</tr>
											</c:forEach>
										</c:otherwise>
									</c:choose>
								</tbody>
							</table>
						</div>
						<div style="background-color:#f3f3f3;margin-top:20px;text-align:center;width:280px;height:130px;padding-top:20px;margin-left:auto;margin-right:auto;border-radius:9px;">
							<p>链接名称：<input id="linkName" type="text"/></p>
							<p>链接地址：<input id="linkUrl" type="text"/></p>
							<input type="button" value="  查  询 " id="saveLink" class=" button">
						</div>
					</div>
				</div>
				<jsp:include page="bottom.jsp" />
			</div>
	</body>
</html>
