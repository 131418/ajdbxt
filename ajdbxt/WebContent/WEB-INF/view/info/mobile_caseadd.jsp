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
		<link rel="stylesheet" href="<%=basePath%>css/app.css" />
		<link rel="stylesheet" href="<%=basePath%>css/mui.picker.min.css" />
<style>
.index_nav {
	background-color: #007aff;
	color: white;
	font-size: 15px;
}

.index_nav h1, a {
	color: white;
}
.mui-input-row select, .mui-input-row input{
font-size:14px;
}
</style>
</head>

<body>
<s:action name="User_mobile_navbar" namespace="/user"
		executeResult="true" />
	<header class="mui-bar mui-bar-nav index_nav">
		<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left" onclick="window.location.href='<%=basePath%>info/Info_page_mobileCaseList'"></a>
		<h1 class="mui-title">新增案件</h1>
	</header>
	
		<!-------------------------------------------------->
	<div class="mui-content">
		<div class="mui-card" style="margin:0px;">
			<!--页眉，放置标题-->
			<!--	<div class="mui-card-header">页眉</div>-->
			<!--内容区-->
			<div class="mui-card-content" id="case_input">
				<form class="mui-input-group" >
					<div class="mui-input-row">
						<label class=" mui-h5">案件名称</label> <input type="text"  name="info.info_name" 
						class="mui-input-clear"	id="input_info_name" placeholder="请输入案件名称">
					</div>
					<div class="mui-input-row">
						<label  class=" mui-h5">案件类别</label> <select id="input_info_category"
						name="info.info_category"  style="font-size:14px;" >
							<option selected="selected" value="">请选择</option>
							<option value="行政案件">行政案件</option>
							<option value="刑事案件">刑事案件</option>
							
						</select>
					</div>
					<div class="mui-input-row">
						<label  class=" mui-h5">办案单位</label> <select id="info_department"
						name="info.info_department"  style="font-size:14px;">
						</select>
					</div>
					<div class="mui-input-row">
						<label  class=" mui-h5">抓获时间</label> 
						<button id='demo1' data-options='{}' class="btn mui-btn mui-btn-block" name="info.info_catch_time" style="float:left;font-size:14px;width:200px;;">选择抓获时间 </button>
					</div>
					<div class="mui-input-row">
						<label  class=" mui-h5">主办民警</label> <select name="info.info_main_police" 
						id="info_main_police"  style="font-size:14px;">
						</select>
					</div>
					<div class="mui-input-row">
							<label  class=" mui-h5">协办民警1</label> <select name="info.info_assistant_police_one"
						id="info_assistant_police_one"  style="font-size:14px;">
						</select>
					</div>
						<div class="mui-input-row " id="add_img">
						<label  class=" mui-h5">添加协办民警2</label>
						<img alt="" src="<%=basePath%>img/addition_fill.png" id="add_police_two">
					</div>
					<div class="mui-input-row " id="police_two_td">
							<label  class=" mui-h5">协办民警2</label> <select name="info.info_assistant_police_two" 
						id="info_assistant_police_two"  style="font-size:14px;">
						</select>
					</div>
					<div class="mui-input-row">
						<label  class=" mui-h5">所（队）法制员</label> <select name="info.info_department_legal_member"
						id="info_department_legal_member"  style="font-size:14px;">
						</select>
					</div>
					<div class="mui-input-row">
						<label  class=" mui-h5">所（队）长</label><input type="text"  name="police.police_name"
						class="mui-input-clear"	id="info_department_captain_name" >
							<input  id="info_department_captain_id" class="mui-input-clear"
											name="info.info_department_captain" type="hidden">
					</div>
					<div class="mui-input-row">
							<label  class=" mui-h5">法制大队值班民警</label> <select name="info.info_legal_team_member"  
						id="info_legal_team_member"  style="font-size:14px;">
						</select>
					</div>
					<div class="mui-input-row">
						<label  class=" mui-h5">值班局领导</label> <select name="info.info_bureau_leader" 
						id="info_bureau_leader"  style="font-size:14px;">
						</select>
						<input type="hidden" name="info.ajdbxt_info_id">
					</div>
				</form>
			</div>
			<!--页脚，放置补充信息或支持的操作-->
			<div class="mui-card-footer">
				<button type="button" class="mui-btn mui-btn-primary mui-btn-outlined" id="input_sure"
					style="width:100%;" >确认添加</button>
			</div>
		</div>
	</div>

	<!--------------------------------->
	
		<script type="text/javascript"
 		src="<%=basePath%>js/Info/mobile_caseAdd.js"></script> 
		<script>
			(function($) {
				$.init();
				var btns = $('.btn');
				btns.each(function(i, btn) {
					btn.addEventListener('tap', function() {
						var _self = this;
						if(_self.picker) {
							_self.picker.show(function (rs) {
								_self.innerText = rs.text;
								_self.picker.dispose();
								_self.picker = null;
							});
						} else {
							var optionsJson = this.getAttribute('data-options') || '{}';
							var options = JSON.parse(optionsJson);
							var id = this.getAttribute('id');
							/*
							 * 首次显示时实例化组件
							 * 示例为了简洁，将 options 放在了按钮的 dom 上
							 * 也可以直接通过代码声明 optinos 用于实例化 DtPicker
							 */
							_self.picker = new $.DtPicker(options);
							_self.picker.show(function(rs) {
								/*
								 * rs.value 拼合后的 value
								 * rs.text 拼合后的 text
								 * rs.y 年，可以通过 rs.y.vaue 和 rs.y.text 获取值和文本
								 * rs.m 月，用法同年
								 * rs.d 日，用法同年
								 * rs.h 时，用法同年
								 * rs.i 分（minutes 的第二个字母），用法同年
								 */
								 _self.innerText = rs.text;
								/* 
								 * 返回 false 可以阻止选择框的关闭
								 * return false;
								 */
								/*
								 * 释放组件资源，释放后将将不能再操作组件
								 * 通常情况下，不需要示放组件，new DtPicker(options) 后，可以一直使用。
								 * 当前示例，因为内容较多，如不进行资原释放，在某些设备上会较慢。
								 * 所以每次用完便立即调用 dispose 进行释放，下次用时再创建新实例。
								 */
								_self.picker.dispose();
								_self.picker = null;
							});
						}
						
					}, false);
				});
			})(mui);
		</script>

</body>

</html>