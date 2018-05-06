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
<<<<<<< HEAD
<title>手机端—人员列表</title>
=======
<title></title>
>>>>>>> origin/lyqqer
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
<<<<<<< HEAD
	<!-- 添加人员 -->
			<div class="mui-icon mui-icon-plusempty" id="div_police_add" style="position: fixed;top: 10px;right: 20px; font-weight:bold;color: white;z-index: 9999999;" onclick="window.location.href='<%=basePath%>user/User_mobile_police_add'"></div>
	<header class="mui-bar mui-bar-nav index_nav">
		<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left" onclick="window.location.href='<%=basePath%>user/User_mobile_index'"></a>
		<h1 class="mui-title" style="color: white;">人员信息</h1>
	
=======
	<header class="mui-bar mui-bar-nav index_nav">
		<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
		<h1 class="mui-title" style="color: white;">人员信息</h1>
>>>>>>> origin/lyqqer
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
		
<<<<<<< HEAD
		<!-- <div class="mui-card" style="margin-bottom: 70px"> -->
		<div class="mui-card" style="margin:0 0 50px 0">
=======
		<div class="mui-card" style="margin-bottom: 70px">
>>>>>>> origin/lyqqer
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
<<<<<<< HEAD
			
=======
>>>>>>> origin/lyqqer
		</div>

	</div>

	<!--------------------------------->
<<<<<<< HEAD
<!--------------------------------->
	<!--底部导航-->
	<nav class="mui-bar mui-bar-tab">
				<a class="mui-tab-item " onclick="window.location.href='<%=basePath%>user/User_mobile_index'"> <span
			class="mui-icon mui-icon-home"></span> <span class="mui-tab-label">首页</span>
		</a> <a class="mui-tab-item" onclick="window.location.href='<%=basePath%>user/User_mobile_police_one'"> <span class="mui-icon mui-icon-person"></span>
			<span class="mui-tab-label" >人员</span>
		</a> <a class="mui-tab-item"  href="#Popover_1"> <span class="mui-icon mui-icon-email"></span>
			<span class="mui-tab-label">统计</span>
		</a> <a class="mui-tab-item"> <span
			class="mui-icon mui-icon-chatboxes"></span> <span
			class="mui-tab-label">案件</span>
		</a>

	</nav>
	<div id="Popover_1" class="mui-popover mui-bar-popover" style="top: 376px; left: 112.167px;width:150px;text-align: center;position: fixed;">
			<div class="mui-popover-arrow mui-bottom">
			</div>
			<ul class="mui-table-view" style="width:150px;background-color: white;">
				<li class="mui-table-view-cell" >
					<a href="">按单位统计</a>
				</li>
				<li class="mui-table-view-cell">
					<a href="">按人员统计</a>
				</li>
			</ul>
		</div>
=======
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
>>>>>>> origin/lyqqer
	<script src="<%=basePath%>js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>js/User/mobile_police_one.js"></script>
	<script src="<%=basePath%>js/mui.min.js"></script>
	<script type="text/javascript">
		mui.init();
<<<<<<< HEAD
	
=======
		List_Police_By_Page(1);
>>>>>>> origin/lyqqer
	</script>
</body>

</html>