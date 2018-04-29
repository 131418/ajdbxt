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
		<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
		<link href="<%=basePath%>css/mui.min.css" rel="stylesheet" />
		<style>
			body {
				font-size: 15px;
			}
			
			.index_nav {
				background-color: #007aff;
				color: white;
				font-size: 15px;
			}
			
			h1,
			a {
				color: white;
			}
			
			input {
				text-align: right;
			}
		</style>
	</head>

	<body>
		<header class="mui-bar mui-bar-nav index_nav">
			<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
			<h1 class="mui-title" style="color: white;">案件信息</h1>
		</header>
		<div class="mui-content" id="case_details_content">

			<div class="mui-card" style="width: 100%;margin: 10px 0 10px 0;color: red;">
				<!--页眉，放置标题-->
				<div class="mui-card-header">
					<h3 class="mui-h4">李四盗窃案</h3>
				</div>
			</div>

			<form class="mui-input-group" id="form_case_detail">
				<div class="mui-input-row">
					<label>案件类别</label>
					<input type="text" class="mui-input-clear mui-h5" placeholder="" value="李四盗窃案">
				</div>
				<div class="mui-input-row">
					<label>办案单位</label>
					<input type="text" class="mui-input-clear mui-h5" placeholder="" value="李四盗窃案">
				</div>
				<div class="mui-input-row">
					<label>抓获时间</label>
					<input type="text" class="mui-input-clear mui-h5" placeholder="" value="李四盗窃案">
				</div>
				<div class="mui-input-row">
					<label>主办民警</label>
					<input type="text" class="mui-input-clear mui-h5" placeholder="" value="李四盗窃案">
				</div>
				<div class="mui-input-row">
					<label>协办民警</label>
					<input type="text" class="mui-input-clear mui-h5" placeholder="" value="李四盗窃案">
				</div>
			</form>
			<button type="button" class="mui-btn mui-btn-blue mui-btn-block" style="font-size: 16px;padding: 8px 0; margin: 5px 0;">查看案件流程</button>
		</div>

		<!--------------------------------->
		<!--底部导航-->
		<nav class="mui-bar mui-bar-tab">
				<a class="mui-tab-item " onclick="window.location.href='<%=basePath%>user/User_mobile_index'" href="<%=basePath%>user/User_mobile_index"> <span
			class="mui-icon mui-icon-home"></span> <span class="mui-tab-label">首页</span>
		</a> <a class="mui-tab-item" onclick="window.location.href='<%=basePath%>user/User_mobile_police_one'" href='<%=basePath%>user/User_mobile_police_one'> <span class="mui-icon mui-icon-person"></span>
			<span class="mui-tab-label" >人员</span>
			<a class="mui-tab-item">
				<span class="mui-icon mui-icon-email"></span>
				<span class="mui-tab-label">统计</span>
			</a>
			<a class="mui-tab-item">
				<span class="mui-icon mui-icon-chatboxes"></span>
				<span class="mui-tab-label">案件</span>
			</a>

		</nav>
		<script src="<%=basePath%>js/jquery-3.1.1.min.js"></script>
<script type="text/javascript"
		src="<%=basePath%>js/Index/mobile_indexCaseDetails.js"></script>
		<script src="<%=basePath%>js/mui.min.js"></script>
		<script type="text/javascript">
			mui.init()
		</script>
		<script type="text/javascript">
		case_details();
		</script>
	</body>

</html>