<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>统计</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
#table_total tbody tr {
	text-align: center;
}

#page_flip span a:hover, #select_start_time, #select_stop_time {
	cursor: pointer;
}
</style>
</head>

<body>

	<s:action name="User_navbar" namespace="/user" executeResult="true" />

	<div style="margin: 80px 0 0 0; float: left; width: 100%;">
		<div class="panel" style="width: 95%; margin: 20px auto;">
			<!--  -->
			<div class="panel-heading">
				<h3 class="panel-title"><span>统计</span><span>-</span><span>按单位统计</span></h3>
			</div>
			<div class="panel-body">
				<div class="col-md-12">
					<div class="panel">
						<!--  -->
						<div class="panel-body">
							<div style="height: 34px; margin: 0 0 20px 0; width: 100%;">
								<div>
									<span
										style="float: left; margin: 0 0 0 20px; line-height: 34px;">按日期筛选：</span>
									<input id="select_start_time" class="form-control mydate"
										style="width: 150px; float: left; text-align: center;"
										type="text" placeholder="起始时间"
										onchange="List_Total_By_Department()" value="2018-01-01" />
									<%--  --%>
									<span
										style="float: left; margin: 0 0 0 20px; line-height: 34px;">至</span>
									<!--  -->
									<input id="select_stop_time" class="form-control mydate"
										style="width: 150px; float: left; margin: 0 0 0 20px; text-align: center;"
										type="text" placeholder="结束时间"
										onchange="List_Total_By_Department()" />
									<%--  --%>
								</div>
								<!--按类型统计  -->
								<div style="width: 160px; float: right;">
									<!-- <button class="btn btn-default role_one"
										onclick="createPolice()">按人员统计</button> -->
									<button class="btn btn-default role_one"
									onclick="javascript:location.href='/ajdbxt/total/Total_page_listPoliceCase'">按人员统计</button>
									
								</div>
							</div>
							<!--  -->
							<table id="table_total" class="table table-hover "
								style="text-align: center; margin: 20px 0;">
								<tbody>
									<tr>
										<th>序号</th>
										<th>办案单位</th>										
										<th><input type="button" id="adminCase" onclick="List_Total_By_Department()" value="行政案件" /></th>
										<th><input type="button" id="criminalCase" onclick="List_Total_By_Department()" value="刑事案件" /></th>
										<th>总案件数</th>
										<th><input type="button"  id="average" onclick="List_Total_By_Department()" value="平均分"></input></th>
									</tr>
								</tbody>
							</table>

							<!-- 加载图标 -->
							<div id="i_pulse" style="text-align: center;">
								<i class="fa fa-spinner fa-pulse fa-3x"></i>
							</div>
							
					</div>
					<!-- END TABLE HOVER -->
				</div>

			</div>
		</div>
	</div>

	<script type="text/javascript" src="<%=basePath%>js/icheck.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/Input_Select.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/laydate/laydate.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>js/Total/ajdbxtTotalDepartment.js"></script>
	<script type="text/javascript">
		var select_start_time = document.getElementById("select_start_time");
		var select_stop_time = document.getElementById("select_stop_time");
		var str = '';
		var now_date = new Date();
		var now_date_year = now_date.getFullYear();
		str += now_date_year;
		var now_date_month = now_date.getMonth() + 1;
		str += "-" + now_date_month;
		var now_date_date = now_date.getDate();
		str += "-" + now_date_date;
		console.log("str:" + str);
		/* select_start_time.value=str; */
		select_stop_time.value = str;
		console.log("select_start_time1:" + select_start_time.value);
		console.log("select_stop_time1:" + select_stop_time.value);
		List_Total_By_Department();
	</script>

	<script type="text/javascript">
		$.datetimepicker.setLocale('ch');
		$('.mydate').datetimepicker({
			yearStart : 1900, // 设置最小年份
			yearEnd : 2100, // 设置最大年份
			yearOffset : 0, // 年偏差
			timepicker : false, // 关闭时间选项
			format : 'Y-m-d', // 格式化日期年-月-日
			minDate : '1900/01/01', // 设置最小日期
			maxDate : '2100/01/01', // 设置最大日期
		});
		$('.mydate_minute').datetimepicker({
			yearStart : 1900, // 设置最小年份
			yearEnd : 2100, // 设置最大年份
			yearOffset : 0, // 年偏差
			timepicker : true, // 关闭时间选项
			format : 'Y-m-d H:i', // 格式化日期年-月-日
			minDate : '1900/01/01', // 设置最小日期
			maxDate : '2100/01/01', // 设置最大日期
		});
	</script>
</body>
</html>