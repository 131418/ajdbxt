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
		<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left" onclick="window.location.href='<%=basePath%>info/Info_page_mobileCaseList'"></a>
		<h1 class="mui-title">新增案件</h1>
	</header>
	<div class="mui-content">
		<div class="mui-card" style="margin:0px;">
			<!--页眉，放置标题-->
			<!--	<div class="mui-card-header">页眉</div>-->
			<!--内容区-->
			<div class="mui-card-content" id="case_input">
				<form class="mui-input-group" >
					<div class="mui-input-row">
						<label class=" mui-h5">案件名称</label> <input type="text"  name="info.info_name" 
							id="input_info_name" placeholder="请输入案件名称">
					</div>
					<div class="mui-input-row">
						<label  class=" mui-h5">案件类别</label><select id="input_info_category" name="info.info_category" style="font-size:14px;" >
							<option selected="selected" value="">请选择</option>
							<option value="1">行政案件</option>
							<option value="2">刑事案件</option>
						</select>
					</div>
					<div class="mui-input-row">
						<label  class=" mui-h5">办案单位</label> <select style="witdh: 100%;"
											class="form-control selectpicker" data-live-search="true"
											name="info.info_department" id="info_department"
											title="请选择"></select>
					</div>
					<div class="mui-input-row">
						<label  class=" mui-h5">抓获时间</label> <input type="text" name="info.info_catch_time"
						class="mui-input-clear mydate_minute"  id="input_info_catch_time">
					</div>
					<div class="mui-input-row">
						<label  class=" mui-h5">主办民警</label> <select style="witdh: 100%;"
											class="form-control selectpicker" data-live-search="true"
											name="info.info_main_police"  id="info_main_police"
											></select>
					</div>
					<div class="mui-input-row">
						<label  class=" mui-h5">协办民警1</label> <select  style="witdh: 100%;" id="info_assistant_police_one"
											class="form-control selectpicker" data-live-search="true"
											name="info.info_assistant_police_one"></select>
					</div>
<%-- 					<div class="mui-input-clear"><img alt="" src="<%=basePath%>img/addition_fill.png" id="add_police_two">	</div>		 --%>
						<div class="mui-input-row " id="add_img">
						<label  class=" mui-h5">添加协办民警2</label>
						<img alt="" src="<%=basePath%>img/addition_fill.png" id="add_police_two">
					</div>
					<div class="mui-input-row " id="police_two_td">
						<label  class=" mui-h5" >协办民警2</label><select style="witdh: 100%;" id="police_two_tdd"
											class="form-control selectpicker" data-live-search="true"  
											name="info.info_assistant_police_two" 
											></select>
					</div>
					<div class="mui-input-row">
						<label  class=" mui-h5">所（队）法制员</label><select style="witdh: 100%;" id="info_department_legal_member"
											class="form-control selectpicker" data-live-search="true"
											name="info.info_department_legal_member"<%--  onclick="legal()" --%>
											title="请选择"></select>
					</div>
					<div class="mui-input-row">
						<label  class=" mui-h5">所（队）长</label><input type="text"  name="police.police_name"
							id="info_department_captain_name" >
							<input  id="info_department_captain_id"
											name="info.info_department_captain" type="hidden">
					</div>
					<div class="mui-input-row">
						<label  class=" mui-h5">法制大队值班民警</label><select style="witdh: 100%;"
											class="form-control selectpicker" data-live-search="true"
											name="info.info_legal_team_member" onclick="legal()" id="info_legal_team_member"
											title="请选择"></select>
					</div>
					<div class="mui-input-row">
						<label  class=" mui-h5">值班局领导</label><select style="witdh: 100%;" id="info_bureau_leader"
											class="form-control selectpicker" data-live-search="true"
											name="info.info_bureau_leader" onclick="leader()"
											title="请选择"></select>
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
		<script type="text/javascript"
		src="<%=basePath%>js/Info/mobile_caseAdd.js"></script>
	<script src="<%=basePath%>js/mui.min.js"></script>
	<script type="text/javascript">
		mui.init();
	</script>
	<script type="text/javascript"> 
 	
 		$.datetimepicker.setLocale('ch'); 
 		$('.mydate').datetimepicker({ 
 			yearStart : 1990, // 设置最小年份
 			yearEnd : 2050, // 设置最大年份 
 			yearOffset : 0, // 年偏差 
 			timepicker : false, // 关闭时间选项 
 			format : 'Y-m-d', // 格式化日期年-月-日 
 			minDate : '1990/01/01', // 设置最小日期 
 			maxDate : '2030/01/01', // 设置最大日期 
 		}); 
 		$('.mydate_minute').datetimepicker({ 
 			yearStart : 1990, // 设置最小年份 
 			yearEnd : 2050, // 设置最大年份
 			yearOffset : 0, // 年偏差
 			step: 1,
		timepicker : true, // 关闭时间选项 
 			format : 'Y-m-d H:i', // 格式化日期年-月-日 
 			step: 1,
 			minDate : '1990/01/01', // 设置最小日期 
 			maxDate : '2030/01/01', // 设置最大日期
		}); 
 	</script> 
</body>

</html>