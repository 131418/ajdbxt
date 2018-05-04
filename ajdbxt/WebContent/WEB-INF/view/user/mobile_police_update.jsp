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
<title>手机端—修改人员</title>
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
</style>
</head>

<body>
	<header class="mui-bar mui-bar-nav index_nav">
		<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
		<h1 class="mui-title">修改人员</h1>
	</header>
	<div class="mui-content">
		<div class="mui-card" style="margin:0px;">
			<!--页眉，放置标题-->
			<!--	<div class="mui-card-header">页眉</div>-->
			<!--内容区-->
			<div class="mui-card-content">
				<form class="mui-input-group mui-h5">
					<div class="mui-input-row">
						<label>警号</label> <input type="text" class="mui-input-clear "
							id="update_input_police_serial_number">
					</div>
					<div class="mui-input-row">
						<label>密码</label> <input type="text" class="mui-input-clear"
							id="update_input_police_password">
					</div>
					<div class="mui-input-row">
						<label>姓名</label> <input type="text" class="mui-input-clear"
							id="update_input_police_name">
					</div>
					<div class="mui-input-row">
						<label>单位</label> <select class="mui-select"
							id="update_input_police_department">
						</select>
					</div>
					<div class="mui-input-row">
						<label>职务</label> <input type="text" class="mui-input-clear"
							id="update_input_police_duty">
					</div>
					<div class="mui-input-row">
						<label>法制员</label> <select class="mui-select"
							id="iupdate_nput_police_legaler">
							<option selected="selected" value="">请选择</option>
							<option value="1">是</option>
							<option value="2">否</option>
						</select>
					</div>
					<div class="mui-input-row">
						<label>权限</label> <select id="update_input_police_power">
							<!-- <option value="1">单位内浏览</option>
							<option value="2">单位内管理</option>
							<option value="3">所有单位内管理</option> -->
						</select>
					</div>
					<div class="mui-input-row">
						<label>电话</label> <input type="text" class="mui-input-clear"
							id="update_input_police_phone_number">
					</div>
				</form>
			</div>
			<!--页脚，放置补充信息或支持的操作-->
			<div class="mui-card-footer">
				<button type="button" class="mui-btn mui-btn-primary mui-btn-outlined"
					style="width: 100%;" onclick="updatePolice();">确认修改</button>
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
		//修改初始化
		var update_police_vo = null;
		var update_xhr = new XMLHttpRequest();
		update_xhr.open("POST", "/ajdbxt/user/User_findPoliceById");
		update_xhr.send(null);
		update_xhr.onreadystatechange = function() {
			if (update_xhr.readyState == 4) {
				if (update_xhr.status == 200) {
					update_police_vo = JSON
							.parse(update_xhr.responseText);
					console.log("xhr.readyState:"
							+ update_xhr.readyState);
					console.log("xhr.status:" + update_xhr.status);
						// Id
						var update_input_ajdbxt_police_id = document
								.getElementById("update_input_ajdbxt_police_id");
						update_input_ajdbxt_police_id.value = ajdbxt_police_id;

						// 警号
						var update_input_police_serial_number = document
								.getElementById("update_input_police_serial_number");
						update_input_police_serial_number.value = update_police_vo.ajdbxt_police.police_serial_number;
						// 密码
						var update_input_police_password = document
								.getElementById("update_input_police_password");
						update_input_police_password.value = update_police_vo.ajdbxt_police.police_password;
						console.log("update_input_police_password:"
								+ update_input_police_password.value);

						// 姓名
						var update_input_police_name = document
								.getElementById("update_input_police_name");
						update_input_police_name.value = update_police_vo.ajdbxt_police.police_name;

						// 单位
						var xmlHttpRequest = new XMLHttpRequest();
						xmlHttpRequest.open("POST",
								"/ajdbxt/user/User_getPower");
						xmlHttpRequest.send(null);
						xmlHttpRequest.onreadystatechange = function() {
							if (xmlHttpRequest.readyState == 4
									&& xmlHttpRequest.status == 200) {
								var loginRole = JSON
										.parse(xmlHttpRequest.responseText);
								var option = '';
								if (loginRole.ajdbxt_police.police_power == "3") {
									var deparment = update_police_vo.ajdbxt_department.ajdbxt_department_id;
									$
											.post(
													'/ajdbxt/user/User_findDepartmentByPage',
													function(
															Department_data) {
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
																.html(
																		option);
													}, 'json');

								} else {
									option += '<option value="'
											+ login_police_deparment_id
											+ '">'
											+ login_police_deparment
											+ '</option>';
									$('#input_police_department')
											.html(option);
								}
							}

						}

						// 职务
						var update_input_police_duty = document
								.getElementById("update_input_police_duty");
						update_input_police_duty.value = update_police_vo.ajdbxt_police.police_duty;
						// 职务
						var update_input_police_legaler = document
								.getElementById("update_input_police_legaler");
						update_input_police_legaler.value = update_police_vo.ajdbxt_police.police_legaler;

						// 角色
						var update_input_police_power = document
								.getElementById("update_input_police_power");
						update_input_police_power.value = update_police_vo.ajdbxt_police.police_power;

						// 手机号码
						var update_input_police_phone_number = document
								.getElementById("update_input_police_phone_number");
						update_input_police_phone_number.value = update_police_vo.ajdbxt_police.police_phone_number;
					}
				}
			}
	</script>
</body>

</html>