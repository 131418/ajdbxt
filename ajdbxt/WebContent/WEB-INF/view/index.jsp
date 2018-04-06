<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
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
									<tr>
										<th>案件名称</th>
										<th>案件类别</th>
										<th>办案单位</th>
										<th>抓获时间</th>
										<th>主办民警</th>
										<th>协办民警</th>
									</tr>
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
						</div>
					</div>
					<!-- END TABLE HOVER -->
				</div>

			</div>
		</div>
	</div>
	
	<script type="text/javascript" src="<%=basePath%>js/Index/indexCaseInfo.js"></script>
</body>
</html>