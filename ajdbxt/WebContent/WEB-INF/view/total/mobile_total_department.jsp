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
<title>手机端—按单位统计</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<link href="<%=basePath%>css/mui.min.css" rel="stylesheet" />
<style>
.index_nav {
	background-color: #007aff;
	color: white;
	font-size: 15px;
}

.index_nav h1, a {
	color: white;
}

.total_info_title a {
	color: #333;
}

#popover {
	height: 140px;
	width: 160px;
}

#total_info_type {
	color: #007aff;
}
</style>
</head>

<body>
	<header class="mui-bar mui-bar-nav index_nav">
		<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"
			onclick="window.location.href='<%=basePath%>user/User_mobile_index'"></a>
		<h1 class="mui-title" style="color: white;">按单位统计</h1>
		<div class="index_nav_option" style="float: right;">
			<a style="color: white;"
				class="mui-icon mui-action-menu mui-icon-bars mui-pull-left"
				href="#popover"></a>
		</div>
	</header>
	<!--时间  -->
	<div style="display:none;">
					<input id="select_start_time"
						class="form-control mydate input_date"
						style="width: 120px; float: left; text-align: center;" type="text"
						placeholder="起始时间" value="2018-01-01" />
					<%--  --%>
					<span style="float: left; line-height: 34px;">至</span>
					<!--  -->
					<input id="select_stop_time" class="form-control mydate input_date"
						style="width: 120px; float: left; text-align: center;"
						type="text" placeholder="结束时间" value="2018-05-07" />
					<%--  --%>
				</div>
	<!--------------------------------->
	<!--弹出菜单-->
	<div id="popover" class="mui-popover">
		<div class="mui-scroll-wrapper">
			<div class="mui-scroll">
				<ul class="mui-table-view mui-h5" id="ul_total_type">
					<li class="mui-table-view-cell"><span class="total_info_title">
							<a class="a_total_type" id="averageScore" >按平均分统计
						</a>
					</span></li>
					<li class="mui-table-view-cell"><span class="total_info_title">
							<a class="a_total_type" id="adminCase" >按行政案件数统计
						</a>
					</span></li>

					<li class="mui-table-view-cell"><span class="total_info_title">
							<a class="a_total_type" id="criminalCase" >按刑事案件数统计
						</a>
					</span></li>

				</ul>
			</div>
		</div>
	</div>
	<div class="mui-content">
		<div class="mui-card" style="margin: 0 0 50px 0">
			<!--页眉，放置标题-->
			<div class="mui-card-header" style="padding: 0px;">
				<div class="mui-card-header" id="total_info_type">按平均分统计</div>
			</div>
				<!--内容区-->
				<div class="mui-card-content">
					<ul class="mui-table-view" id="ul_total_department">

					</ul>
				</div>
		</div>
		</div>

		<!--------------------------------->
		<!--------------------------------->
		<!--底部导航-->
		<nav class="mui-bar mui-bar-tab">
			<a class="mui-tab-item "
				onclick="window.location.href='<%=basePath%>user/User_mobile_index'">
				<span class="mui-icon mui-icon-home"></span> <span
				class="mui-tab-label">首页</span>
			</a> <a class="mui-tab-item"
				onclick="window.location.href='<%=basePath%>user/User_mobile_police_one'">
				<span class="mui-icon mui-icon-person"></span> <span
				class="mui-tab-label">人员</span>
			</a> <a class="mui-tab-item" href="#Popover_1"> <span
				class="mui-icon mui-icon-email"></span> <span class="mui-tab-label">统计</span>
			</a> <a class="mui-tab-item" onclick="window.location.href='<%=basePath%>info/Info_page_mobileCaseList'"> <span
				class="mui-icon mui-icon-chatboxes"></span> <span
				class="mui-tab-label">案件</span>
			</a>

		</nav>
		<div id="Popover_1" class="mui-popover mui-bar-popover"
			style="top: 376px; left: 112.167px; width: 150px; text-align: center; position: fixed;">
			<div class="mui-popover-arrow mui-bottom"></div>
			<ul class="mui-table-view"
				style="width: 150px; background-color: white;">
				<li class="mui-table-view-cell"><a href="">按单位统计</a></li>
				<li class="mui-table-view-cell"><a href="">按人员统计</a></li>
			</ul>
		</div>
		<script src="<%=basePath%>js/jquery-3.1.1.min.js"></script>
		<script src="<%=basePath%>js/mui.min.js"></script>
		<script type="text/javascript">
			mui.init();
			mui('.mui-scroll-wrapper').scroll()
		</script>
		<script type="text/javascript"
		src="<%=basePath%>js/Total/mobile_total_department.js"></script>
		<script type="text/javascript">
			var averageScore = document.getElementById("averageScore").innerHTML;
			console.log(1);
			List_Total_Department(averageScore,1);
			$(".input_date").bind("change", function() {
				List_Total_Department(averageScore,1);
			});
		</script>
</body>
</html>