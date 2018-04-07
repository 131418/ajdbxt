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
<title>办案流程详情</title>
</head>
<body>

	<s:action name="User_navbar" namespace="/user" executeResult="true" />

	<div style="margin: 100px 0 0 0; float: left; width: 100%;">
		<!---------------------------------------------------------------------------------------------------->
		<!---------------------------------------------------------------------------------------------------->
		<div class="panel" style="width: 95%; margin: 20px auto;">
			<%-- <div id="" style="height: 80px; padding: 20px;">
				<div class="managerClass" style="float: right; margin-left: 10px;">
					<button onclick="cleanInput" data-toggle="modal"
						data-target="#addUser" class="btn btn-success">
						<span style="" class="glyphicon glyphicon-plus"></span>新增用户
					</button>
				</div>
				<div class="input-group" style="float: right; width: 300px;">
					<input id="queryString" type="text" class="form-control"
						placeholder="请输入搜索内容"> <span class="input-group-btn">
						<button onclick="queryUser()" class="btn btn-default"
							type="button">搜索</button>
					</span>
				</div>
			</div> --%>
			<%-- <div id="loadingDiv" style="width: 319px; margin: 0 auto;">
				<img alt="" src="<%=basePath%>img/loading.gif">
			</div> --%>
			<div id="tableDiv" class="hideDiv">
				<table class="table table-bordered" style="text-align: center;">
					<tbody id="userTable">
						<tr style="background-color: #696969; color: white;">
							<td>所属案件</td>
							<td>办案单位</td>
							<td>案件类型</td>
							<td>主办民警</td>
							<td>协办民警</td>
							<td>结案</td>
							<td>操作</td>
						</tr>

						<tr class='trHover'>
							<td>2018年3月萍乡学院盗窃案</td>
							<td>安源公安局</td>
							<td>刑事案件</td>
							<td>龙队长</td>
							<td>萧警官</td>
							<td>否</td>
							<td><button onclick='getProcessById(this)' value='1'
									"data-toggle='modal' data-target='#updateProcess'
									style='margin-left: 5px;' class='btn btn-primary managerPower'>修改</button></td>
						</tr>

					</tbody>
				</table>
			</div>

		</div>
</body>
</html>