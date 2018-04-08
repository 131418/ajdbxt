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

.span_catagory{
	float: left;
	margin:0 5px 0 0;
	
}
</style>
</head>

<body>
	<s:action name="User_navbar" namespace="/user" executeResult="true" />

	<div style="margin: 80px 0 0 0;float: left; width: 100%;">
		<div class="panel" style="width: 95%; margin: 20px auto;">
			<!--  -->
			<div class="panel-heading">
				<h3 class="panel-title">XXX人员</h3> 
			</div>
			<div class="panel-body">
				<div class="col-md-12">
					<div class="panel">
						<%-- <div class="panel-heading">
							<span class="span_catagory"><h3 class="panel-title">统计</h3></span> <span class="span_catagory">-</span>XXX单位 <span
								id="span_unit" class="span_catagory"></span>
						</div> --%>
						<!--  -->
						<div class="panel-body">
							<div style="height: 34px; width: 100%;">
								<div>
									<span
										style="float: left; margin: 0 0 0 20px; line-height: 34px;">按日期筛选：</span>
									<input id="select_start_time" class="form-control mydate"
										style="width: 150px; float: left; text-align: center;"
										type="text">
									<%--  --%>
									<span
										style="float: left; margin: 0 0 0 20px; line-height: 34px;">至</span>
									<!--  -->
									<input id="select_stop_time" class="form-control mydate"
										style="width: 150px; float: left; margin: 0 0 0 20px; text-align: center;"
										type="text">
									<%--  --%>


								</div>
								<!-- 检索 -->
								<div class="input-group" style="width: 300px; float: right;">
									<input id="input_DNASearchText" class="form-control"
										oninput="List_DNA_By_PageAndSearch(1)" type="text"> <span
										class="input-group-addon"> <i class="fa fa-search"></i>
									</span>
								</div>

							</div>

							<table id="table_total" class="table table-hover table-bordered"
								style="text-align: center; margin: 20px 0;">
								<tbody>
									<tr>
									<th><select id="total_user" class="form-control">
										</select></th>									
										<th>案件名</th>
										<th>评分</th>
										<th>主办民警</th>
										<th>协办民警</th>

									</tr>
								</tbody>
							</table>

							<!-- 加载图标 -->
							<div id="i_pulse" style="text-align: center;">
								<i class="fa fa-spinner fa-pulse fa-3x"></i>
							</div>
							<!--翻页  -->
							<div id="page_flip"
								style="margin: 0 auto; width: 400px; text-align: center;">
								<span> <a onclick="flip(1)"><i
										class="fa fa-angle-double-left">首页</i> </a> &nbsp&nbsp <a
									onclick="flip(2)"><i class="fa fa-angle-left"></i>上一页 </a>
									&nbsp&nbsp <a onclick="flip(3)">下一页<i
										class="fa fa-angle-right"></i>
								</a> &nbsp&nbsp <a onclick="flip(4)">尾页<i
										class="fa fa-angle-double-right"></i>
								</a> <br />
									<p class='info' style="margin-top: 5px;">
										第<span id="span_pageIndex">1</span>页&nbsp&nbsp共 <span
											id="span_totalPages">1</span>页&nbsp&nbsp共 <span
											id="span_totalRecords">0</span>条记录
									</p></span>
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
		src="<%=basePath%>js/Total/ajdbxtTotalUnit.js"></script>
	<script type="text/javascript">
		List_Total_By_Page(1);
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