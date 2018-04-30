<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!doctype html>
<html>

<head>
<meta charset="UTF-8">
<title></title>
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<link href="<%=basePath%>css/mui.min.css" rel="stylesheet" />
<style>
.index_nav {
	background-color: #007aff;
	color: white;
	font-size: 15px;
}

h1, a {
	color: white;
}
</style>
</head>

<body>
	<header class="mui-bar mui-bar-nav index_nav">
		<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
		<h1 class="mui-title" style="color: white;">人员信息</h1>
	</header>
	<div class="mui-content" >
		<%-- <!-- 搜索框 -->
				<div
					style="background-color: #FFFFFF; width: 100%;" >
					<span class="mui-icon mui-icon-search mui-h5"
						style="width: 70px; margin-left: 40%; line-height: 30px;"><span
						class="mui-h5" style="margin: 0 0 0 2px;">搜索</span></span>
				</div>
				--%>
		
		<div class="mui-card" style="margin-bottom: 70px">
			<!--页眉，放置标题-->
			<div class="mui-card-header" style="padding:0px;">
				<%-- <!-- 搜索框 -->
				<div
					style="background-color: #FFFFFF; width: 100%;" >
					<span class="mui-icon mui-icon-search mui-h5"
						style="width: 70px; margin-left: 40%; line-height: 30px;"><span
						class="mui-h5" style="margin: 0 0 0 2px;">搜索</span></span>
				</div> --%>
				<div class="mui-input-row mui-search" style="width:100%;">
						    <input type="search" id="input_PoliceSearchText" oninput="List_Police_By_Page(1)" style="background-color: #FFFFFF;width: 100%;margin:0px;padding-left:50px;padding-right:50px;" class="mui-input-clear" placeholder="搜索" >
						</div>
			</div>
			<!--内容区-->
			<div class="mui-card-content">
				<ul class="mui-table-view " id="ul_police_list">

				</ul>
			</div>
			<!--页脚，放置补充信息或支持的操作-->
			<div class="mui-card-footer div_page_count">
				<ul class="mui-pagination" id="ul_page_count">
				</ul>
			</div>
		</div>

	</div>

	<!--------------------------------->
	<!--底部导航-->
	<nav class="mui-bar mui-bar-tab">
		<a class="mui-tab-item "
			onclick="window.location.href='<%=basePath%>user/User_mobile_index'"
			href="<%=basePath%>user/User_mobile_index"> <span
			class="mui-icon mui-icon-home"></span> <span class="mui-tab-label">首页</span>
		</a> <a class="mui-tab-item"
			onclick="window.location.href='<%=basePath%>user/User_mobile_police_one'"
			href='<%=basePath%>user/User_mobile_police_one'> <span
			class="mui-icon mui-icon-person"></span> <span class="mui-tab-label">人员</span>
			<a class="mui-tab-item"> <span class="mui-icon mui-icon-email"></span>
				<span class="mui-tab-label">统计</span>
		</a> <a class="mui-tab-item"> <span
				class="mui-icon mui-icon-chatboxes"></span> <span
				class="mui-tab-label">案件</span>
		</a>
	</nav>
	<script src="<%=basePath%>js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>js/User/mobile_police_one.js"></script>
	<script src="<%=basePath%>js/mui.min.js"></script>
	<script type="text/javascript">
		mui.init();
		List_Police_By_Page(1);
	</script>
</body>

</html>