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
<title>移动端-首页</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<style>
.index_nav {
	background-color: #007aff;
	color: white;
	font-size: 15px;
}

.index_nav_img, .index_nav_police {
	float: left;
}

.index_nav_img {
	margin: 0px 15px 0 10px;
}

.index_nav_police {
	float: left;
}

.index_nav_police span {
	font-size: 12px;
	margin: 0 20px 5px 0;
}

.case_info_title a {
	color: #333;
}

#popover {
	height: 110px;
	width: 200px;
}
</style>
</head>

<body>
	<s:action name="User_mobile_navbar" namespace="/user"
		executeResult="true" />
	<!--------------------------------->
	<!--头部-->
	<header class="mui-bar mui-bar-nav index_nav">
		<div class="index_nav_img">
			<a href="#setting">
				<img src="<%=basePath%>img/police.png" height="35px;" style="margin-top:5px;" />
			</a>
		</div>
		<div class="index_nav_police">
			<div class="index_nav_police_name"></div>
			<div class="index_nav_police_detail">
				<span class="span_police_depart"></span><span
					class="span_police_status"></span>
			</div>
		</div>
		<div class="index_nav_option" style="float: right;display:none;">
			<a style="color: white;"
				class="mui-icon mui-action-menu mui-icon-bars mui-pull-left"
				href="#popover"></a>
		</div>
	</header>
	<!--------------------------------->
	<!--弹出菜单-->
	<div id="popover" class="mui-popover">
		<div class="mui-scroll-wrapper">
			<div class="mui-scroll">
				<ul class="mui-table-view mui-h5" id="ul_case_type">
				<li class="mui-table-view-cell"><span
						class="mui-icon mui-icon-paperclip"></span> <span
						class="case_info_title"> <a class="a_case_type" > 未结案的案件 </a>
					</span> <span class="mui-badge mui-badge-red">33</span></li>
					
					<li class="mui-table-view-cell">
						<span
						class="mui-icon mui-icon-checkmarkempty"></span> <span
						class="case_info_title"> <a class="a_case_type"  > 待审核的案件 </a>
					</span> <span class="mui-badge mui-badge-warning">33</span></li>
				</ul>
			</div>
		</div>
	</div>
	<!-- <a href="#popover" id="openPopover"
		class="mui-btn mui-btn-primary mui-btn-block">打开弹出菜单</a> -->

	<!--------------------------------->
	<!--主体部分-->
	<div class="mui-content" >
		<!--卡片视图-->
		<div class="mui-card" style="margin: 0;">
			<!--页眉，放置标题-->
			<div class="mui-card-header" id="case_info_type">未结案的案件</div>
			<!--内容区-->
			<div class="mui-card-content">
				<!--信息列表-->
				<ul class="mui-table-view" id="ul_case_info">
				</ul>

			</div>
			<!--页脚，放置补充信息或支持的操作-->
			<div class="mui-card-footer">

				<ul class="mui-pagination" id="ul_page_count">
				</ul>
			</div>
		</div>

	</div>
	<!-- 修改密码和退出登录部分 -->
	<div id="setting" class="mui-popover mui-popover-action mui-popover-bottom" style="display: block;">
			<ul class="mui-table-view">
				<li class="mui-table-view-cell">
					<a href="<%=basePath%>user/User_mobile_police_setting">修改密码</a>
				</li>
				<li class="mui-table-view-cell">
					<a href="<%=basePath%>user/User_mobile_loginout" style="color: red;">退出登录</a>
				</li>
			</ul>
			<ul class="mui-table-view">
				<li class="mui-table-view-cell">
					<a href="#setting"><b>取消</b></a>
				</li>
			</ul>
		</div>
	
	<script type="text/javascript"
		src="<%=basePath%>js/Index/mobile_indexCaseInfo.js"></script>
	<script type="text/javascript">
		mui.init();
		mui('.mui-scroll-wrapper').scroll()
	</script>
</body>

</html>