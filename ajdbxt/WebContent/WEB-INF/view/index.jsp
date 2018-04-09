<<<<<<< HEAD
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
=======
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
>>>>>>> origin/LSY
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<<<<<<< HEAD

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>


<title>首页</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style type="text/css">
.table_case_info tbody tr {
	text-align: center;
}

#page_flip span a:hover {
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
				<h3 class="panel-title">首页</h3>
			</div>
			<div class="panel-body">
				<div class="col-md-12">
					<div class="panel">
						<!--  -->
						<div class="panel-body">
							<div style="height: 34px; width: 100%;">

								<div class="operation" style="margin-bottom: 6px;">
									<select id="type_chose"
										style="width: 220px; float: left;"
										class="form-control" onchange="List_Index_CaseInfo_By_Page(1)">
										<!-- <option selected="selected" value="">请选择</option> -->
										<option   value="正在参与的案件"
											selected="selected">正在参与的案件</option>
										<option   value="待核对案件">待核对案件</option>
										<option   value="等待提交问题清单的案件">等待提交问题清单的案件</option>
										<option   value="等待评分的案件">等待评分的案件</option>

									</select>
								</div>
							</div>

							<table id="table_case_info" class="table table-hover table-bordered"
								style="text-align: center; margin: 20px 0;">
								<tbody>
=======
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<!---------------------------------------------------------------------------------------------------->
<!---------------------------------------------------------------------------------------------------->
<title>首页</title>
</head>
<body>
	<s:action name="User_navbar" namespace="/user" executeResult="true" />
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<div style="margin: 80px 0 0 0; float: left; width: 100%;">
		<div class="panel" style="width: 95%; margin: 20px auto;">
			<!--  -->
			<div class="panel-heading">    
				<h3 class="panel-title">首页</h3>
			</div>
			<div class="panel-body">
				<div class="operation" style="margin-bottom: 6px;">
					<select id="type_chose"
					style="width:220px; margin-left:15px; float: left;"
					class="form-control" onchange="material(this)">
					<!-- <option selected="selected" value="">请选择</option> -->
					<option class="a" value="joinCase_table_info" selected="selected" >正在参与的案件</option>
					<option class="a" value="checkCase_table_info">待核对案件</option>
					<option class="a"  value="wsubmitCase_table_info">等待提交问题清单的案件</option>
					<option class="a"  value="scoreCase_table_info">等待评分的案件</option> 
					
				</select> 
				</div>
				<div class="col-md-12">
					<!-- TABLE HOVER -->
					<div class="panel">
						<div class="panel-heading">
							<h3 class="panel-title">案件列表</h3>
							
						</div>
						<div class="panel-body">
							<table
								class="table table-hover table-condensed breakcase_table_info">
								<thead>
>>>>>>> origin/LSY
									<tr>
										<th>案件名称</th>
										<th>案件类别</th>
										<th>办案单位</th>
										<th>抓获时间</th>
										<th>主办民警</th>
										<th>协办民警</th>
									</tr>
<<<<<<< HEAD
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

=======
								</thead>
								<tbody>

								</tbody>
								<tfoot>
									<tr>
										<td colspan="8" style="font-size: 12px;" class="page_info"><a
											onclick="firstPage()"><i class="fa fa-angle-double-left">首页</i>
										</a>&nbsp&nbsp<a onclick="prePage()"><i
												class="fa fa-angle-left"></i>上一页 </a>&nbsp&nbsp<a
											onclick="nextPage()">下一页<i class="fa fa-angle-right"></i>
										</a>&nbsp&nbsp <a onclick="lastPage()">尾页<i
												class="fa fa-angle-double-right"></i>
										</a> <br />
											<p class='info'></p></td>
									</tr>
								</tfoot>
							</table>
>>>>>>> origin/LSY
						</div>
					</div>
					<!-- END TABLE HOVER -->
				</div>

			</div>
		</div>
	</div>
<<<<<<< HEAD
	<script type="text/javascript"
		src="<%=basePath%>js/Index/indexCaseInfo.js"></script>
	
</body>
</html>
=======
	
	<script type="text/javascript" src="<%=basePath%>js/Index/indexCaseInfo.js"></script>
</body>
</html>
>>>>>>> origin/LSY
