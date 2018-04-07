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
<script type="text/javascript" src="<%=basePath%>js/Process/List_CaseProcess.js"></script>
<title>案件流程列表</title>
</head>
<body>

	<!-----------------------------------------引入导航条 ------------------------------------------------------>
	<s:action name="User_navbar" namespace="/user" executeResult="true" />

	<!----------------------------------------主体内容 -------------------------------------------------------- -->

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
			<%-- <div style="margin: 20px 10px;float: left">
				<a class="btn btn-primary" href="<%=basePath%>process/page_CaseProcessInfoProcessAction">办案</a>
			</div> --%>
			
			<div id="tableDiv" class="hideDiv">
				<table class="table table-bordered" style="text-align: center;">
					<tbody id="userTable">
						<tr style="background-color: #696969; color: white;">
							<td>案件名称</td>
							<td>办案单位</td>
							<td>案件类型</td>
							<td>主办民警</td>
							<td>协办民警</td>
							<td>结案</td>
							<td>操作</td>
						</tr>
						
			<tr class='trHover'><td>2018年3月萍乡学院盗窃案</td>
			<td>安源公安局</td>
			<td>刑事案件</td>
			<td>龙队长</td>
			<td>萧警官</td>
			<td>否</td>
			<td><button onclick='getProcessById(this)' value='1'"data-toggle='modal' data-target='#updateProcess' 
						style='margin-left: 5px;' class='btn btn-primary managerPower'>修改</button></td>
			</tr>
						
					</tbody>
				</table>
			</div>

			<div id="bottomPage" style="padding: 20px;">
				<span>当前页数:<span id="currPage">1</span></span> <span>共:<span
					id="totalPage">2</span>页
				</span> <span onclick="skipToIndexPage()" id="indexPage"
					class="pageOperation">首页</span> <span onclick="skipToPrimaryPage()"
					id="previousPage" class="pageOperation">上一页</span> <span
					onclick="skipToNextPage()" id="nextPage" class="pageOperation">下一页</span>
				<span onclick="skipToLastPage()" id="lastPage" class="pageOperation">末页</span>
				<span> <input id="skipPage" type="text"
					style="text-align: center; width: 60px; height: 30px;"
					class="queryInput">
					<button onclick="skipToArbitrarilyPage()" class="btn btn-default"
						style="height: 30px; margin-bottom: 10px;">跳转</button>
				</span>
			</div>
		</div>
		<!---------------------------------------------------------------------------------------------------->
		<!---------------------------------------------------------------------------------------------------->
		
		<!-- ----------------------------------------------办案模态框------------------------------------------------------------------------------------- -->
		<div class="modal fade" id="updateProcessr" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			<
		</div>
		
		<!-----------------------------------------------------------------修改模态框----------------------------------------------------  -->
			<div class="modal fade" id="updateProcessr" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
			  <div class="modal-dialog" role="document">
			    <div class="modal-content">
			      <div class="modal-header">
			        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
			        <h4 class="modal-title">修改办案流程</h4>
			      </div>
			      <div class="modal-body">
			      	<div id="updateLoadingDiv" class="hideDiv" style="width: 319px; margin: 0 auto;">
						<img alt="" src="<%=basePath %>img/loading.gif">
					</div>
			      	 <div id="updateContent">	
			      		<form id="updateUserForm">
				      		<label>所属案件：</label>
				      		<input id="user_username_update" name="user_username" type="text" class="form-control" placeholder="请输入账号">
				      		<label>密码：</label>
				      		<input id="user_password_update" name="user_password" type="password" class="form-control" placeholder="请输入密码">
				      		<label>姓名：</label>
				      		<input id="user_name_update" name="user_name" type="text" class="form-control" placeholder="请输入用户姓名">
				      		<label>代码：</label>
				      		<input id="user_number_update" name="user_number" type="text" class="form-control" placeholder="请输入用户代码">
				      		<label>单位：</label>
				      		<input id="user_units_update" name="user_units" type="text" class="form-control" placeholder="请输入用户单位">
				      		<label>案件技术权限：</label>
				      		<select id="user_case_technology_power_update" name="user_case_technology_power" class="form-control">
				      			<option value="jurisdiction_none">无权限</option>
				      			<option value="jurisdiction_use">使用权限</option>
				      			<option value="jurisdiction_admin">管理权限</option>	
				      		</select>
				      		<label>案件侦查权限：</label>
				      		<select id="user_case_query_power_update" name="user_case_query_power" class="form-control">
				      			<option value="jurisdiction_none">无权限</option>
				      			<option value="jurisdiction_use">使用权限</option>
				      			<option value="jurisdiction_admin">管理权限</option>	
				      		</select>
				      		<label>检验鉴定权限：</label>
				      		<select id="user_check_power_update" name="user_check_power" class="form-control">
				      			<option value="jurisdiction_none">无权限</option>
				      			<option value="jurisdiction_use">使用权限</option>
				      			<option value="jurisdiction_admin">管理权限</option>	
				      		</select>
				      		<label>队伍管理权限：</label>
				      		<select id="user_army_manager_power_update" name="user_army_manager_power" class="form-control">
				      			<option value="jurisdiction_none">无权限</option>
				      			<option value="jurisdiction_use">使用权限</option>
				      			<option value="jurisdiction_admin">管理权限</option>	
				      		</select>
				      		<label>技术管理权限：</label>
				      		<select id="user_technology_manager_power_update" name="user_technology_manager_power" class="form-control">
				      			<option value="jurisdiction_none">无权限</option>
				      			<option value="jurisdiction_use">使用权限</option>
				      			<option value="jurisdiction_admin">管理权限</option>	
				      		</select>
				      		<label>统计权限：</label>
				      		<select id="user_statistics_power_update" name="user_statistics_power" class="form-control">
				      			<option value="jurisdiction_none">无权限</option>
				      			<option value="jurisdiction_use">使用权限</option>
				      			<option value="jurisdiction_admin">管理权限</option>	
				      		</select>
				      		<label>用户管理权限：</label>
				      		<select id="user_user_manager_power_update" name="user_user_manager_power" class="form-control">
				      			<option value="jurisdiction_none">无权限</option>
				      			<option value="jurisdiction_use">使用权限</option>
				      			<option value="jurisdiction_admin">管理权限</option>	
				      		</select>
			      		</form>
			      	</div>
			      </div>
			      <div class="modal-footer">
			        <button onclick="reLoadUser()" type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
			        <button id="updateBtn" onclick="updateUser(this)" type="button" class="btn btn-primary">修改</button>
			      </div>
			    </div><!-- /.modal-content -->
			  </div><!-- /.modal-dialog -->
			</div>
			<!--  -->
			<!--  -->
			<!--  -->
	</div>
</body>
</html>