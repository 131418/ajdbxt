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
									<button class="btn btn-default" onclick="">
										<i class="fa fa-plus-square"></i> 按单位统计
									</button>

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
										<th>办案单位</th>
										<th>主办民警</th>
<!-- 										<th>案件评分</th>
 -->										<th>案件类型</th>
										<th>抓获时间</th>
									</tr>
								</tbody>
							</table>

							<!-- 加载图标 -->
							<div id="i_pulse" style="text-align: center;">
								<i class="fa fa-spinner fa-pulse fa-3x"></i>
							</div>
							<!--翻页  -->
							<div id="page_flip"
								style="margin: 20px auto 30px; width: 300px; text-align: center;">
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
		src="<%=basePath%>js/Total/ajdbxtTotal.js"></script>
	<script type="text/javascript">
	List_Total_By_Page(1);
	</script>

</body>
</html>