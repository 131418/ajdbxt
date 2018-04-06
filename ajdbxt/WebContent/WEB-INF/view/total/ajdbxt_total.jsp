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
.table_total tbody tr {
	text-align: center;
}

#page_flip span a:hover {
	cursor: pointer;
}
</style>
</head>

<body>
	<s:action name="User_navbar" namespace="/user" executeResult="true" />

	<div style="float: left; width: 100%;">
		<div class="panel" style="width: 95%; margin: 20px auto;">
			<!--  -->
			<div class="panel-heading">
				<!-- <h3 class="panel-title">统计</h3> -->
			</div>
			<div class="panel-body">
				<div class="col-md-12">
					<div class="panel">
						<div class="panel-heading">
							<h3 class="panel-title">统计</h3>
						</div>
						<!--  -->
						<div class="panel-body">
							<div style="height: 34px; width: 100%;">
								<div style="width: 150px; float: left; margin: 0 20px 0 0">
									<input name="" style="float: left;" type="text" class="form-control mydate" placeholder="起始日期">
								</div>
							</div>

							<table id="table_total" class="table table-hover table-bordered"
								style="text-align: center; margin: 20px 0;">
								<tbody>
									<tr>
										<th>办案单位</th>
										<th>行政案件</th>
										<th>刑事案件</th>
									</tr>
								</tbody>
							</table>

							<!-- 加载图标 -->
							<div id="i_pulse" style="text-align: center;">
								<i class="fa fa-spinner fa-pulse fa-3x"></i>
							</div>

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
	<script src="/laydate/laydate.js"></script>
	<script type="text/javascript"
		src="<%=basePath%>js/Total/ajdbxtTotal.js"></script>
	<script type="text/javascript">
	List_Total_By_Page(1);
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
			timepicker : true, // 关闭时间选项
			format : 'Y-m-d H:i', // 格式化日期年-月-日
			minDate : '1990/01/01', // 设置最小日期
			maxDate : '2030/01/01', // 设置最大日期
		});
	</script>
</body>
</html>