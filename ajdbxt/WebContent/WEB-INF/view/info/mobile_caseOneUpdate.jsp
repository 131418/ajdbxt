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
	
	<script type="text/javascript">
		mui.init();
		//修改初始化
		var url = window.location.href;
	info_id = url.substring(url.indexOf("=") + 1);
		var update_police_vo = null;
		var update_xhr = new XMLHttpRequest();
		update_xhr.open("POST", "/ajdbxt/info/Info_getSingleInfo?info.ajdbxt_info_id="+info_id);
		update_xhr.send(null);
		update_xhr.onreadystatechange = function() {
			if (update_xhr.readyState == 4) {
				if (update_xhr.status == 200) {
					update_police_vo = JSON.parse(update_xhr.responseText);
					console.log("xhr.readyState:" + update_xhr.readyState);
					console.log("xhr.status:" + update_xhr.status);
					// Id
					var input_ajdbxt_police_id = document
							.getElementById("input_ajdbxt_police_id");
					input_ajdbxt_police_id.value = update_police_vo.ajdbxt_police.ajdbxt_police_id;

					// 案件名称
					var input_info_name = document
							.getElementById("input_info_name");
					input_info_name.value = update_police_vo.info.info_name;
					// 密码
					var input_police_password = document
							.getElementById("input_police_password");
					input_police_password.value = update_police_vo.ajdbxt_police.police_password;
					console.log("input_police_password:"
							+ input_police_password.value);

					// 姓名
					var input_police_name = document
							.getElementById("input_police_name");
					input_police_name.value = update_police_vo.ajdbxt_police.police_name;

					// 单位
					var xmlHttpRequest = new XMLHttpRequest();
					xmlHttpRequest.open("POST", "/ajdbxt/user/User_getPower");
					xmlHttpRequest.send(null);
					xmlHttpRequest.onreadystatechange = function() {
						if (xmlHttpRequest.readyState == 4
								&& xmlHttpRequest.status == 200) {
							var loginRole = JSON
									.parse(xmlHttpRequest.responseText);
							var option = '';
							if (loginRole.ajdbxt_police.police_power == "3") {
								var deparment = update_police_vo.ajdbxt_department.ajdbxt_department_id;
								console
										.log("update_police_vo.ajdbxt_department.ajdbxt_department_id:"
												+ update_police_vo.ajdbxt_department.ajdbxt_department_id);
								$
										.post(
												'/ajdbxt/user/User_findDepartmentByPage',
												function(Department_data) {
													// 所有案件循环
													for (var len = 0; len < Department_data.list.length; len++) {
														option += '<option ';
														if (Department_data.list[len].ajdbxt_department_id == deparment) {
															option += 'selected';
														}
														option += ' value="'
																+ Department_data.list[len].ajdbxt_department_id
																+ '">'
																+ Department_data.list[len].department_name
																+ '</option>';
													}
													$(
															'#input_police_department')
															.html(option);
												}, 'json');

							} else {
								option += '<option value="'
											+ login_police_deparment_id
											+ '">'
										+ login_police_deparment + '</option>';
								$('#input_police_department').html(option);
							}
						}

					}

					// 职务
					var input_police_duty = document
							.getElementById("input_police_duty");
					input_police_duty.value = update_police_vo.ajdbxt_police.police_duty;
					// 法制员
					var input_police_legaler = document
							.getElementById("input_police_legaler");
					console
							.log("update_police_vo.ajdbxt_police.police_legaler:"
									+ update_police_vo.ajdbxt_police.police_legaler);
					input_police_legaler.value = update_police_vo.ajdbxt_police.police_legaler;
					// 权限
					var update_input_police_power = document
							.getElementById("update_input_police_power");
					update_input_police_power.value = update_police_vo.ajdbxt_police.police_power;

					// 手机号码
					var input_police_phone_number = document
							.getElementById("input_police_phone_number");
					console
							.log("update_police_vo.ajdbxt_police.police_phone_number:"
									+ update_police_vo.ajdbxt_police.police_phone_number);
					input_police_phone_number.value = update_police_vo.ajdbxt_police.police_phone_number;
				}
			}
		}
	</script>
</body>

</html>