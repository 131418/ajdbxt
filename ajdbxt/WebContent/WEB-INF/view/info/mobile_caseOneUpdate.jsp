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
<title>手机端—修改案件</title>
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

select, input {
	font-size: 14px;
}
</style>
</head>

<body>
<s:action name="User_mobile_navbar" namespace="/user"
		executeResult="true" />
	<header class="mui-bar mui-bar-nav index_nav">
		<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"
			onclick="window.location.href='<%=basePath%>user/User_mobile_police_one'"></a>
		<h1 class="mui-title">修改案件</h1>
	</header>
	<div class="mui-content">
		<div class="mui-card" style="margin: 0px;">
			<!--页眉，放置标题-->
			<!--	<div class="mui-card-header">页眉</div>-->
			<!--内容区-->
			<div class="mui-card-content">
				<form class="mui-input-group">
					<div class="mui-input-row" style="display: none;">
						<label class="mui-h5">Id</label> <input type="text"
							class="mui-input-clear " id="input_ajdbxt_info_id">
					</div>
					<div class="mui-input-row">
						<label class="mui-h5">案件名称</label> <input type="text"
							class="mui-input-clear " id="input_info_name" name="info.info_name">
					</div>
					<div class="mui-input-row" style="display: none;">
						<label  class=" mui-h5">案件类别</label> <select id="input_info_category" style="font-size:14px;" >
							<option value="行政案件">行政案件</option>
							<option value="刑事案件">刑事案件</option>
						</select>
					</div>
					<div class="mui-input-row">
						<label class="mui-h5">办案单位</label> <select class="mui-select"
							id="input_info_department" style="font-size: 14px;">
						</select>
					</div>
					<div class="mui-input-row">
						<label  class=" mui-h5">抓获时间</label> 
						<button id='demo1' data-options='{}' id="input_info_catch_time"class="btn mui-btn mui-btn-block" name="info.info_catch_time" style="float:left;font-size:14px;width:200px;;">选择抓获时间 </button>
					</div>
					<div class="mui-input-row">
						<label class="mui-h5">主办民警</label> <select class="mui-select"
							id="input_info_main_police" style="font-size: 14px;">
						</select>
					</div>
					<div class="mui-input-row">
						<label class="mui-h5">协办民警1</label> <select class="mui-select"
							id="input_info_assistant_police_one" style="font-size: 14px;">
						</select>
					</div>
					<div class="mui-input-row">
						<label class="mui-h5">协办民警2</label> <select class="mui-select"
							id="input_info_assistant_police_two" style="font-size: 14px;">
						</select>
					</div>
					<div class="mui-input-row">
						<label class="mui-h5">所（队）法制员</label> <select class="mui-select"
							id="input_info_department_legal_member" style="font-size: 14px;">
						</select>
					</div>
					<div class="mui-input-row">
						<label  class=" mui-h5">所（队）长</label><input type="text"  name="police.police_name"
						class="mui-input-clear"	id="info_department_captain_name" >
							<input  id="info_department_captain_id" class="mui-input-clear"
											name="info.info_department_captain" type="hidden">
					</div>
					<div class="mui-input-row">
						<label class="mui-h5">法制大队值班民警</label> <select class="mui-select"
							id="input_info_legal_team_member" style="font-size: 14px;">
						</select>
					</div>
					<div class="mui-input-row">
						<label class="mui-h5">值班局领导</label> <select class="mui-select"
							id="input_info_bureau_leader" style="font-size: 14px;">
						</select>
					</div>
				</form>
			</div>
			<!--页脚，放置补充信息或支持的操作-->
			<div class="mui-card-footer">
				<button type="button"
					class="mui-btn mui-btn-primary mui-btn-outlined"
					style="width: 100%;" onclick="updatePolice();">确认修改</button>
			</div>
		</div>
	</div>

	<!--------------------------------->
		<script type="text/javascript"
 		src="<%=basePath%>js/Info/mobile_caseUpdate.js"></script> 
	<script type="text/javascript">
		mui.init();
	</script>
</body>

</html>