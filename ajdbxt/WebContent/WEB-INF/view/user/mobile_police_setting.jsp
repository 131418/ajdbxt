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
		<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left" href="<%=basePath%>user/User_mobile_index"></a>
		<h1 class="mui-title">修改密码</h1>
	</header>
	<div class="mui-content">
		<div class="mui-card" style="margin:0px;padding:0 0 10px 0;">
			<!--页眉，放置标题-->
			<!--	<div class="mui-card-header">页眉</div>-->
			<!--内容区-->
			<div class="mui-card-content">
				<form class="mui-input-group">
					<div class="mui-input-row">
						<label class=" mui-h5">输入密码</label> <input type="text" class="mui-input-clear"
							id="newPassword" placeholder="请输入新密码">
					</div>
					<div class="mui-input-row">
						<label  class=" mui-h5">确认密码</label> <input type="text" class="mui-input-clear " 
							id="newPasswordAgain" placeholder="请确认新密码">
					</div>
				</form>
			</div>
			<!--页脚，放置补充信息或支持的操作-->
			<div class="mui-card-footer">
				<button type="button" class="mui-btn mui-btn-primary mui-btn-outlined"
					style="width:100%;" onclick="mobile_password_update();">确认修改</button>
			</div>
		</div>
	</div>

	<script src="<%=basePath%>js/jquery-3.1.1.min.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>js/User/mobile_password_update.js"></script>
	<script src="<%=basePath%>js/mui.min.js"></script>
	<script type="text/javascript">
		mui.init();
	</script>
</body>

</html>