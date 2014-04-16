<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
			<style type="text/css">
				.content-box-content{
					height:4200px;
				}
				.inside{
					width:300px;
				 	height:320px;
				 	margin-left:60px;
				}
				
				.inside{
					width:300px;
				 	height:320px;
				 	margin-left:60px;
				}
				.more{
					margin-left:120px;
				}
				.testInput{
					width:190px;
				 }
				 .newsContent{
				 		width:460px;
				 		height:340px;
						border:2px #E6E6E6 solid;
						margin-top:10px;
						margin-bottom:10px;
						margin-left:20px;
						float:left;
				 }
				.newsContent p{
					font-weight:bold;
				}
				.setBtnWidth{
					width:100px;
				}
				.inside img{
					height:100px;
					width:190px;
				}
				.imgPreview{
					height:230px;
					width:682px;
					text-align:center;
					float:left;
				}
				.imgPreview img{
					height:190px;
					width:200px;
					float:right;
				}
				.tupianword{
					font-size:15px;
					font-weight:bold;
					color:red;
					height:230px;
					width:80px;
					float:left;
					margin-top:12px;
				}
				.openPosterImg{
					font-size:10px;
					text-decoration:underline;
					color:#5BA300;
				}
				.openPosterImg:hover{
					color:#5BA300;
				}
			</style>
		<title>文章管理</title>
		<%@include file="/common/common.jsp"%>
		<script type="text/javascript" src="${ctx}/js/jquery.MetaData.js"></script>
		<script type="text/javascript" src="${ctx}/js/jquery.MultiFile.pack.js"></script>
		<script type="text/javascript" src="${ctx}/js/page.js"></script>
		<script type="text/javascript" src="${ctx}/js/article.js"></script>
		<script type="text/javascript" src="${ctx}/js/FilterHtml.js"></script>
		<script charset="utf-8" src="${ctx}/editor/kindeditor.js"></script>
		<script charset="utf-8" src="${ctx}/editor/lang/zh_CN.js"></script>
		<script type="text/javascript" src="${ctx}/js/ajaxfileupload.js"></script>
		
		<script type="text/javascript">
			$(document).ready(function(){
				
				$(window.parent.document.getElementById("childIframe")).height(4500);
				//alert(src);
				
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
						$("#articleid").val(0);
						$("#title").val('');
						$("#role").html("更新广告内容");
						return false; 
					}
				);
				
				$("#type").change(function(){
					var val=$(this).val();
					location.href='${ctx}/admin/goInPosterManage?type='+val;
				});
				$(".adStatus").change(function(){
					var v =	$(this).val();
					if(v == 0){
						$(this).parent().parent().children("p:first").find("img").attr("src","${ctx}/images/ad.jpg");
						$(this).parent().parent().children(":last").attr("upStatus","true");
					}
				});
				$(".upic").click(function(){
					$(this).parent().parent().children(":last").attr("upStatus","true");
				});
			});	
			function update(th){
				//var testInput = $(th).parent().find(".testInput");
				//获取新闻说明文字
				var title = $(th).parent().find("input[name=title]").val();
				//获取新闻预览图片
				var imgUrl = $(th).parent().find("input[name=imgUrlAddress]").val();
				
				//获取新闻图片所指向的链接
				var newsLink = $(th).parent().find("input[name=newsLink]").val();
				
				//获取新闻id
				var id = $(th).parent().find("input[name=id]").val();
				
				//获取新闻类型
				var type = $(th).parent().find("input[name=type]").val();
				var adStatus=$(th).parent().find("select[name=adStatus]").val();
				var upStatus=$(th).attr("upStatus");
				if(upStatus == "true"){
					$.post("${ctx}/admin/updateNews",{"nt.id":id,"nt.title":title,"nt.type":type,"nt.newsLink":newsLink,"nt.imgUrl":imgUrl,"nt.isStatus":adStatus},function(data){
					if(data=="0"){
						alert("更新成功！");
						/* $("#"+id).attr("src",imgUrl); */
						if(adStatus == 1){
							$("#"+id).attr("src",imgUrl);
						}
						$(th).attr("upStatus","false");
					}else if(data=="1"){
						alert("更新失败！");
					}
				});
				}else{
				alert("图片没有更新");
				}
				
			}
			
		function uploadimage(file){
			$.ajaxFileUpload({
				url:'${ctx}/editor/jsp/upload_json.jsp',
				secureuri:false,
				fileElementId:file+"",
				dataType: 'json',				
				success: function (data, status)
				{
					if(data.error==0){//成功
						$("#"+file+"Address").val(data.url);
						//alert($("#"+file+"Address").val());
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
	</head>
	<body style="background-image: none;">
			<!-- Wrapper for the radial gradient background -->
			<div id="main-content" style="width: 100%;height: 100%;margin-left: 0px;">
				<div class="content-box">
					<div class="content-box-header">
						<h3>更新广告</h3>
						<ul class="content-box-tabs">
							<li>
								<a href="#tab1" class="default-tab">
										<span style="color:red;font-weight:bold;">
											<c:if test="${type==1}">1号广告区</c:if>
											<c:if test="${type==2}">2号广告区</c:if>
											<c:if test="${type==3}">3号广告区</c:if>
											<c:if test="${type==4}">4号广告区</c:if>
											<c:if test="${type==5}">5号广告区</c:if>
											<c:if test="${type==6}">6号广告区</c:if>
											<c:if test="${type==7}">7号广告区</c:if>
											<c:if test="${type==8}">8号广告区</c:if>
										</span>
								</a>
							</li>
						</ul>
						<div class="clear"></div>
					</div>
					<div class="content-box-content">
						<div class="tab-content default-tab" id="tab1">
						<div style="margin-left:auto;margin-right:auto;font-weight:bold; font-size: 15px;color:#555;">
							
							当前广告区：
							<span style="color:red">
								<c:if test="${type==1}">1号广告区</c:if>
								<c:if test="${type==2}">2号广告区</c:if>
								<c:if test="${type==3}">3号广告区</c:if>
								<c:if test="${type==4}">4号广告区</c:if>
								<c:if test="${type==5}">5号广告区</c:if>
								<c:if test="${type==6}">6号广告区</c:if>
								<c:if test="${type==7}">7号广告区</c:if>
								<c:if test="${type==8}">8号广告区</c:if>
							</span>
							&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<span>
								请选择广告区：
							</span>
							<select id="type">
								<option value="1">1号广告区</option>
								<option value="2">2号广告区</option>
								<option value="3">3号广告区</option>
								<option value="4">4号广告区</option>
								<option value="5">5号广告区</option>
								<option value="6">6号广告区</option>
								<option value="7">7号广告区</option>
								<option value="8">8号广告区</option>
							</select>
							<script>
								$("#type").val(${type});
							</script>
							<a href="http://www.zgjczs.com/attached/image/20131016/20131016144440_110.jpg" class="openPosterImg" target="_blank">点我查看首页广告区域分布图</a>
						</div>
							<c:forEach items="${posters}" var="poster" varStatus="st">
								<div class="newsContent">
									<div class="inside">
												<br/>
												<span style="font-weight:bold;margin-top:200px;font-size:15px;color:red;">编号:
													<c:if test="${type==1}">A</c:if><c:if test="${type==2}">B</c:if><c:if test="${type==3}">C</c:if><c:if test="${type==4}">D</c:if><c:if test="${type==5}">E</c:if><c:if test="${type==6}">F</c:if><c:if test="${type==7}">G</c:if><c:if test="${type==8}">H</c:if>${st.index+1}
												</span>
												<p>
																		
													广告图片预览：
													<%-- <img src="${poster.imgUrl}" id="${poster.id}" alt="" /> --%>
													<img src="<c:if test="${poster.isStatus == 1}">
															${poster.imgUrl}
														</c:if>
														<c:if test="${poster.isStatus != 1}">
															${ctx}/images/ad.jpg
														</c:if>
														" id="${poster.id}" alt="" />
												</p>
												<p>
													广告说明文字：<input class="testInput" name="title" type="text" value='${poster.title}'/> 
												</p>
												<p>
													广告预览图片：
													<input style="width:137px;"  class="testInput" name="imgUrl" id="imgUrl${st.index}" type="file" value='${poster.imgUrl}'/> 
													<input type="hidden" id="imgUrl${st.index}Address" name="imgUrlAddress" value="${poster.imgUrl}"/>
													<input class="button setBtnWidth upic" type="button" value="上传" onclick="uploadimage('imgUrl${st.index}')" style="width:50px;" id="uploadImg${st.index}"/>
												</p>
												<p>
													广告图片链接：<input class="testInput" name="newsLink" type="text" value='${poster.newsLink}'/> 
												</p>
												<p>
													广告招商状态：<select id="adStatus" name="adStatus" class="adStatus">
														<c:if test="${poster.isStatus == 1}">
															<option value="0" >广告未招商</option>
															<option value="1" selected="selected">广告已招商</option>
														</c:if>
														<c:if test="${poster.isStatus == 0}">
															<option value="0" selected="selected">广告未招商</option>
															<option value="1" >广告已招商</option>
														</c:if>
													</select>
												</p>
												<input type="hidden" name="id" value="${poster.id}"/>
												<input type="hidden" name="type" value="${poster.type}"/>
												<input class="button setBtnWidth more" upStatus="false" type="button" value="更新" onclick="update(this)"/>
									</div>	
								</div>
							</c:forEach>
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
