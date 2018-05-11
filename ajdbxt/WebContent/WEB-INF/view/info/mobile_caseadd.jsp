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

.index_nav h1, a {
	color: white;
}
select,input{
font-size:14px;
}
</style>
</head>

<body>
	<header class="mui-bar mui-bar-nav index_nav">
		<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left" onclick="window.location.href='<%=basePath%>user/User_mobile_police_one'"></a>
		<h1 class="mui-title">新增案件</h1>
	</header>
	<div class="mui-content">
		<div class="mui-card" style="margin:0px;">
			<!--页眉，放置标题-->
			<!--	<div class="mui-card-header">页眉</div>-->
			<!--内容区-->
			<div class="mui-card-content">
				<form class="mui-input-group">
					<div class="mui-input-row">
						<label class=" mui-h5">警号</label> <input type="text" class="mui-input-clear"
							id="input_police_serial_number" placeholder="请输入警号">
					</div>
					<div class="mui-input-row">
						<label  class=" mui-h5">密码</label> <input type="text" class="mui-input-clear " 
							id="input_police_password" placeholder="请输入密码">
					</div>
					<div class="mui-input-row">
						<label  class=" mui-h5">姓名</label> <input type="text" class="mui-input-clear"
							id="input_police_name" placeholder="请输入姓名">
					</div>
					<div class="mui-input-row">
						<label  class=" mui-h5">单位</label> <select id="input_police_department" style="font-size:14px;">
							<option>1</option>
						</select>
					</div>
					<div class="mui-input-row">
						<label  class=" mui-h5">职务</label> <input type="text" class="mui-input-clear "
							id="input_police_duty" placeholder="请输入职务">
					</div>
					<div class="mui-input-row">
						<label  class=" mui-h5">法制员</label> <select id="input_police_legaler" style="font-size:14px;" >
							<option selected="selected" value="">请选择</option>
							<option value="1">是</option>
							<option value="2">否</option>
						</select>
					</div>
					<div class="mui-input-row ">
						<label  class=" mui-h5">权限</label> <select id="input_police_power" style="font-size:14px;">
							<!-- <option value="1">单位内浏览</option>
							<option value="2">单位内管理</option>
							<option value="3">所有单位内管理</option> -->
						</select>
					</div>
					<div class="mui-input-row">
						<label  class=" mui-h5">电话</label> <input type="text" class="mui-input-clear "
							id="input_police_phone_number" placeholder="请输入电话号码">
					</div>
				</form>
			</div>
			<!--页脚，放置补充信息或支持的操作-->
			<div class="mui-card-footer">
				<button type="button" class="mui-btn mui-btn-primary mui-btn-outlined"
					style="width:100%;" onclick="createPolice();">确认添加</button>
			</div>
		</div>
	</div>

	<!--------------------------------->
	<!--底部导航-->
	<nav class="mui-bar mui-bar-tab">
				<a class="mui-tab-item " onclick="window.location.href='<%=basePath%>user/User_mobile_index'"> <span
			class="mui-icon mui-icon-home"></span> <span class="mui-tab-label">首页</span>
		</a> <a class="mui-tab-item" onclick="window.location.href='<%=basePath%>user/User_mobile_police_one'"> <span class="mui-icon mui-icon-person"></span>
			<span class="mui-tab-label" >人员</span>
		</a> <a class="mui-tab-item"  href="#Popover_1"> <span class="mui-icon mui-icon-email"></span>
			<span class="mui-tab-label">统计</span>
		</a> <a class="mui-tab-item" onclick="window.location.href='<%=basePath%>info/Info_page_mobileCaseList'"> <span
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
	<script src="<%=basePath%>js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>js/User/mobile_police_one.js"></script>
	<script src="<%=basePath%>js/mui.min.js"></script>
	<script type="text/javascript">
		mui.init();
	</script>
</body>

</html>