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
<title>案件详情信息</title>
<link rel="stylesheet" href="<%=basePath%>css/Info/InfoDetails.css">
<link rel="stylesheet" href="<%=basePath%>css/Process/processDetails.css">
<style type="text/css">
html, body, .radio, .checkbox, .button_process {
	margin: 0px !important;
	padding: 0px !important;
}

.first_process_div {
	border: solid 1px #eee;
	margin: 0px 0px 40px 0px;
	/* float: left; */
}

.summon_process {
	border-bottom: solid 1px #eee;
	padding: 10px 0;
}

.process_title, button {
	display: inline-block;
	vertical-align: middle;
}
  #checkbox_process_question{
    }

    #checkbox_process_question ul{
        margin:0 10px;
         
        padding:0px;
        list-style-type:none;
        vertical-align:middle  ;
    }

    #checkbox_process_question li{
        float:left;
        display:block;
/*         width:100px;  */
/*         height:20px; */
/*         line-height:20px; */

/*         font-size:14px;   */
/*         font-weight:bold;           */
/*         color:#666666; */

        text-decoration:none;
/*         text-align:left;  */
        margin: 0 10px;
/*         background:#ffffff; */
        
       
    }
</style>
</head>
<body>
	<s:action name="User_navbar" namespace="/user" executeResult="true" />
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<!---------------------------------------------------------------------------------------------------->
	<div
		style="margin: 80px 0 0 0; float: left; width: 100%; overflow: hidden;">
		<div class="panel"
			style="width: 95%; margin: 20px auto; overflow: inherit; position: relative;">
			<!--  -->
			<div class="panel-heading">
				<h3 class="panel-title">案件详情</h3>
			</div>
			<button onclick="javascript:history.go(-1)" type="button"
				class="btn btn-default button button_return ">
				<i class="fa fa-reply"></i> 返回列表
			</button>
<!-- 			<button type="button" class="btn btn-default button button_change" -->
<!-- 				onclick="staff_relive()"> -->
<!-- 				<i class="fa fa-pencil"></i> 修改人员 -->
<!-- 			</button> -->
			<div class="panel-body staff_body">
				<form id="staffDetails" enctype="multipart/form-data"
					accept-charset="utf-8">
					<table style="width:90%;margin-left: 30px;">
						<tr>
							<td colspan="6" class="staff_info_title">基本信息</td>
						</tr>
						<tr style="height: 20px"></tr>
						<tr>
							<td><label>案件名称</label></td>
							<td><input name="info.info_name" disabled="disabled"
								class="form-control" type="text"></td>
							<td><label>案件类别</label></td>
							<td><input name="info.info_category" class="form-control" disabled="disabled"
								type="text"></td>
						</tr>
						<tr>
							<td><label>办案单位</label></td>
							<td><input name="department.department_name" disabled="disabled"
								class="form-control" type="text"></td>

							<td><label>抓获时间</label></td>
							<td><input style="font-size: 12px;" disabled="disabled"
								name="info.info_catch_time" class="form-control" type="text"></td>
						</tr>
						<tr>

							<td><label>主办民警</label></td>
							<td><input name="police[0].police_name" disabled="disabled"
								class="form-control" type="text"></td>
							<td><label>协办民警1</label></td>
							<td><input name="police[1].police_name" disabled="disabled"
								class="form-control" type="text"></td>

						</tr>
						
						<tr>

							<td><label>协办民警2</label></td>
							<td><input name="police[2].police_name" disabled="disabled"
								class="form-control" type="text"></td>
							<td><label>法制员</label></td>
							<td><input name="info.info_department_legal_member" disabled="disabled"
								class="form-control" type="text"></td>

						</tr>
						
							<tr>

							<td><label>所（队）长</label></td>
							<td><input name="info.info_department_captain" disabled="disabled"
								class="form-control" type="text"></td>
							<td><label>值班局领导</label></td>
							<td><input name="leader.police_name" disabled="disabled"
								class="form-control" type="text"></td>

						</tr>
						
						<tr>
							<td><label>值班民警</label></td>
							<td><input name="legal.police_name" disabled="disabled"
								class="form-control" type="text"></td>
						</tr>
				</table>
				</form>
			</div>
<!-- ------------------------------------------------------------------------------------->

<!-- -----------------------------------------行政案件-------------------------------------------->
<div style="margin: 80px 0 0 0; float: left; width: 100%;" id="xingzheng_case">
		<div class="panel" style="width: 95%; margin: 20px auto;">
			<!--  -->
			<div class="panel-heading">
				<h4 class="panel-title">行政案件流程</h4>
			</div>
			<div class="panel-body">
				<div class="col-md-12">
					<div class="panel">
						<!--  -->
						<div class="panel-body">
							<!-- 左边 -->
<!-- 						<div id="administrative_process_left" class="col-md-4"></div> -->
							<!-- 右边 -->
							<form action="" id="processDetails">
							<div id="administrative_process_right" class="col-md-12">
							<!-- 	<div id="administrative_process_right" class="col-md-12"> -->
								<!-------------------------------------->
								<!-------------第一阶段 传唤---------------->
								<div class="first_process_div col-md-12">
									<div class="first_process">
										<fieldset>
											<legend>传讯</legend>
											<!-- 是否传唤嫌疑人 -->
											<div class="summon_process col-md-12">
												<div class="process_title col-md-4">办案单位延长传唤：</div>
												<div class="radio col-md-4">
													<label style="margin: 0 10px;"> <input type="radio" onclick=changesuspect_summon_yes(this)
														name="ajdbxtProcess.process_lengthen_subpoena" id="suspect_summon_yes">
														是
													</label><label style="margin: 0 10px;"> <input type="radio" onclick=changesuspect_summon_no(this)
														name="ajdbxtProcess.process_lengthen_subpoena" id="suspect_summon_no">
														否
													</label>
												</div>
												<div class="col-md-4 process_button">
													<s:if test='#session.loginPolice.ajdbxt_police.police_duty=="警员" && #session.loginPolice.ajdbxt_police.police_department!="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'>
													<button type="button" class="btn btn-primary  btn-block"
													onclick="suspect_summon(this)"	style="width: 40%; float: right;">提交</button>
												</s:if>
												</div>
											</div>
											<!-- 询问未成年人 -->
											<div class="summon_process col-md-12">
												<div class="process_title col-md-4">办案单位询问未成年人：</div>
												<div class="radio col-md-4">
													<label style="margin: 0 10px;"> <input type="radio" onclick=changeminors_asking_yes(this)
														name="ajdbxtProcess.process_nonage" id="minors_asking_yes" value="是">
														是
													</label><label style="margin: 0 10px;"> <input type="radio" onclick=changeminors_asking_no(this)
														name="ajdbxtProcess.process_nonage" id="minors_asking_no" value="否">
														否
													</label>
												</div>
												<div class="col-md-4 process_button">
													<s:if test='#session.loginPolice.ajdbxt_police.police_duty=="警员" && #session.loginPolice.ajdbxt_police.police_department!="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'>
													<button type="button" class="btn btn-primary  btn-block" onclick="minors_asking()"
														style="width: 40%; float: right;">提交</button>
													</s:if>
												</div>
												<!-- <div class="col-md-4 process_title">提醒：通知监护人到场</div> -->
											</div>

											<!-- 鉴定 -->
											<div class="summon_process col-md-12">
												<div class="process_title col-md-4">办案单位是否鉴定：</div>
												<div class="radio col-md-4">
													<label style="margin: 0 10px;"> <input type="radio" onclick=changeidentification_yes(this)
														name="ajdbxtProcess.process_authenticate" id="identification_yes" value="是">
														是
													</label><label style="margin: 0 10px;"> <input type="radio" onclick=changeidentification_no(this)
														name="ajdbxtProcess.process_authenticate" id="identification_no" value="否">
														否
													</label>
												</div>
												<!-- <div class="col-md-4 process_title">提醒：请在4日内作出决定，5日内将鉴定意见复印件送达违法嫌疑人及被害人</div> -->
												<div class="col-md-4 process_button"> 
													<s:if test='#session.loginPolice.ajdbxt_police.police_duty=="警员" && #session.loginPolice.ajdbxt_police.police_department!="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'>
													<button type="button" class="btn btn-primary  btn-block" onclick="identification()"
														style="width: 40%; float: right;">提交</button>
													</s:if>
												</div>
											</div>

											<!-- 涉案财物 -->
											<div class="summon_process col-md-12">
												<div class="process_title col-md-4">办案单位涉案财物：</div>
												<div class="radio col-md-4">
													<label style="margin: 0 10px;"> <input type="radio" onclick=changecase_property_yes(this)
														name="ajdbxtProcess.process_case_goods" id="case_property_yes" value="是">
														有
													</label><label style="margin: 0 10px;"> <input type="radio" onclick=changecase_property_no(this)
														name="ajdbxtProcess.process_case_goods" id="case_property_no" value="否">
														无
													</label>
												</div>
												<div class="col-md-4 process_button">
													<s:if test='#session.loginPolice.ajdbxt_police.police_duty=="警员" && #session.loginPolice.ajdbxt_police.police_department!="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'>
													<button type="button" class="btn btn-primary  btn-block"
														style="width: 40%; float: right;" onclick="case_property()">提交</button>
												    </s:if>
												</div>
											</div>

										</fieldset>
									</div>
								</div>
								<!-- ------------------------ -->
								<!-- 第二阶段   听证 -->
									<div class="first_process_div col-md-12">
									<div class="first_process">
										<fieldset>
											<legend>听证</legend>
											<!-- 听证申请-->
											<div class="summon_process col-md-12" id="hearing_applying_div">
												<div class="process_title col-md-4">办案单位听证申请：</div>
												<div class="radio col-md-4">
													<label style="margin: 0 10px;"> <input type="radio" onclick=changehearing_applying_yes(this)
														name="ajdbxtProcess.process_apply_right" id="hearing_applying_yes" value="是">
														是
													</label><label style="margin: 0 10px;"> <input type="radio" onclick=changehearing_applying_no(this)
														name="ajdbxtProcess.process_apply_right" id="hearing_applying_no" value="否">
														否
													</label>
												</div>
												<div class="col-md-4 process_button">
													<s:if test='#session.loginPolice.ajdbxt_police.police_duty=="警员" && #session.loginPolice.ajdbxt_police.police_department!="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'>
													<button type="button" class="btn btn-primary  btn-block"
														style="width: 40%; float: right;" onclick="hearing_applying()">提交</button>
												    </s:if>
												</div>
											</div>
										</fieldset>
									</div>
								</div>

								<!-------------------------------------->
								<!-------------第3阶段 法制大队打回案件---------------->
								<s:if test='#session.loginPolice.ajdbxt_police.police_department=="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'>
									<div class="first_process_div col-md-12" id="dahuixiugai">
									<div class="first_process">
										<fieldset>
											<legend>打回修改</legend>
											<div class="summon_process col-md-12">
												<div class="process_title col-md-4">法制大队是否打回：</div>
												<div class="radio col-md-4">
													<label style="margin: 0 10px;"> <input type="radio" onclick=changerollback_yes(this)
														name="ajdbxtProcess.process_is_rollback" id="rollback_yes" value="是">
														是
													</label><label style="margin: 0 10px;"> <input type="radio" onclick=changerollback_no(this)
														name="ajdbxtProcess.process_is_rollback" id="rollback_no" value="否">
														否
													</label>
												</div>
												<div class="col-md-4 process_button">
												<s:if test='#session.loginPolice.ajdbxt_police.police_department=="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'>
													<button type="button" class="btn btn-primary  btn-block" onclick="process_rollback()"
														style="width: 40%; float: right;">提交</button>
												</s:if>
												</div>
											</div>
										</fieldset>
									</div>
								</div>
								</s:if>
								<!-------------------------------------->
								
								<!-------------------------打回修改完成  -->
								<s:if test='#session.loginPolice.ajdbxt_police.police_duty=="警员" && #session.loginPolice.ajdbxt_police.police_department!="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'>
								<div class="first_process_div col-md-12" id="xiugaiok" hidden="true">
									<div class="first_process">
										<fieldset>
											<legend>办案单位修改完成</legend>
											<!--  -->
											<div class="summon_process col-md-12">
												<!-- <div class="process_title col-md-4">修改完成：</div> -->
												<div class="process_title col-md-4"><input type="hidden" name="ajdbxtProcess.process_is_rollback" value="待处理">
													<s:if test='#session.loginPolice.ajdbxt_police.police_duty=="警员" && #session.loginPolice.ajdbxt_police.police_department!="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'>
													<button type="button" class="btn btn-primary  btn-block" onclick="process_is_rollback_ok()"
														style="float: center;">打回修改完成</button>
													</s:if>
												</div>

											</div>
										</fieldset>
									</div>
								</div>
								</s:if>
								<%-- <div class="first_process_div col-md-12">
									<div class="first_process">
										<fieldset>
											<legend>打回修改完成</legend>
											<div class="summon_process col-md-12">
												<div class="process_title col-md-4">修改完成：</div>
												<div class="radio col-md-4">
													<label style="margin: 0 10px;"> <input type="radio" onclick=changeproblem_asking_yes(this)
														name="" id="" value="是">
														是
													<!-- </label><label style="margin: 0 10px;"> <input type="radio" onclick=changeproblem_asking_no(this)
														name="" id="" value="否">
														否
													</label> -->
												</div>
												<div class="col-md-4 process_button">
													<s:if test='#session.loginPolice.ajdbxt_police.police_duty=="警员"'>
													<button type="button" class="btn btn-primary  btn-block"
														style="width: 40%; float: right;" onclick="hearing_applying()">提交</button>
												    </s:if>
												</div>
											</div>
										</fieldset>
									</div>
								</div> --%>
								
								<!-------------第四阶段 法制大队提出问题---------------->
								<div class="first_process_div col-md-12">
									<div class="first_process">
										<fieldset>
											<legend>法制大队提出问题</legend>
											<div class="summon_process col-md-12">
												<div class="process_title col-md-4">提出问题数量：</div>
                                                <div class="process_title col-md-4">
													<input type="text" class="form-control" name="process.process_question_list"
														id="process_question_list" placeholder="请填写提出问题数量">
												</div>
												<div class="col-md-4 process_button">
												<s:if test='#session.loginPolice.ajdbxt_police.police_department=="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'>
													<button type="button" class="btn btn-primary  btn-block" onclick="problem_asking()"
														style="width: 40%; float: right;">提交</button>
												</s:if>
												</div>
											</div>
										</fieldset>
									</div>
								</div>
								<!-------------------------------------->
								<!-------------第5阶段 法制大队天界问题整改数量---------------->
								<div class="first_process_div col-md-12" >
									<div class="first_process">
										<fieldset>
											<legend>法制大队问题整改</legend>
											<!--  -->
											<div class="summon_process col-md-12">
												<div class="process_title col-md-4">问题整改数量：</div>
                                                <div class="process_title col-md-4"  id="checkbox_process_question">
<!-- 													<input type="text" class="form-control" name="process.process_question" -->
<!-- 														id="process_question" placeholder="请填写问题整改数量"> -->
												</div>
												<div class="col-md-4 process_button">
													<s:if test='#session.loginPolice.ajdbxt_police.police_department=="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'>
													<button type="button" class="btn btn-primary  btn-block" onclick="problem_rectification()" id="but_process_question"
														style="width: 40%; float: right;">提交</button>
													</s:if>
												</div>
											</div>
										</fieldset>
									</div>
								</div>
								<!-------------------------------------->
								<!-------------第6阶段 处罚---------------->
									<div class="first_process_div col-md-12">
									<div class="first_process">
										<fieldset>
											<legend>办案单位处罚</legend>
											<!-- 处罚 -->
											<div class="summon_process col-md-12">
												<div class="process_title col-md-2">处罚:</div>
												<div class="checkbox col-md-8" >
													<label style="margin: 0 10px;">
														<input type="checkbox" name="process.process_detention" value="是" id="process_detention"> 行政拘留
													</label><label style="margin: 0 10px;">
														<input type="checkbox" name="process.process_penalty" value="是" id="process_penalty"> 罚款
													</label>
													 <label style="margin: 0 10px;">
													 <!-- onclick=changeprocess_treatment_category_yes(this) -->
													 	<input type="checkbox" name="process.process_mandatory_abandon_drug" value="是" id="process_mandatory_abandon_drug">强制隔离戒毒
													</label>
													<label style="margin: 0 0px;"> 
													<input type="checkbox" name="process.process_community_abandon_drug" value="是" id="process_community_abandon_drug"> 社区戒毒
													</label>
													<label style="margin: 0 0px;"> 
													<input type="checkbox" name="process.process_administrativ_warning" value="是" id="process_administrativ_warning"> 行政警告
													</label>
												</div>
												<div class="col-md-2 process_button">
													<s:if test='#session.loginPolice.ajdbxt_police.police_duty=="警员" && #session.loginPolice.ajdbxt_police.police_department!="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'>
													<button type="button" class="btn btn-primary  btn-block" onclick="punishmentab_chufa()" id="punishmentab"
														style="width: 90%; float: right;">提交</button>
													</s:if>
												</div>
											</div>
											<!-- 处罚已通知 -->
<!-- 											<div class="summon_process col-md-12"> -->
<!-- 												<div class="process_title col-md-4">处罚已通知：</div> -->
<!-- 												<div class="radio col-md-4"> -->
<!-- 													<label style="margin: 0 10px;"> <input type="radio" -->
<!-- 														name="punishment_notice" id="punishment_notice_yes" value=""> -->
<!-- 														是 -->
<!-- 													</label><label style="margin: 0 10px;"> <input type="radio" -->
<!-- 														name="punishment_notice" id="punishment_notice_no" value=""> -->
<!-- 														否 -->
<!-- 													</label> -->
<!-- 												</div> -->
<!-- 												<div class="col-md-4 process_button"> -->
<!-- 													<button type="button" class="btn btn-primary  btn-block" -->
<!-- 														style="width: 40%; float: right;">提交</button> -->
<!-- 												</div> -->
<!-- 											</div> -->


										</fieldset>
									</div>
								</div>
								<!-------------------------------------->
								<!-------------第7阶段   所队长 审核---------------->
								<%-- <s:if test='#session.loginPolice.ajdbxt_police.police_duty=="所队长"'> --%>
								<div class="first_process_div col-md-12">
									<div class="first_process">
										<fieldset>
											<legend>所队长审核</legend>
											<!--  -->
											<div class="summon_process col-md-12">
												<div class="process_title col-md-4">是否审核：</div>
												<div class="radio col-md-4">
													<label style="margin: 0 10px;"> <input type="radio" onclick=changecase_review_yes(this)
														name="ajdbxtProcess.process_captain_check" id="case_review_yes" value="是"> 是
													</label><label style="margin: 0 10px;"> <input type="radio" onclick=changecase_review_no(this)
														name="ajdbxtProcess.process_captain_check" id="case_review_no" value="否"> 否
													</label>
												</div>
												<div class="col-md-4 process_button">
<!-- 这里是所有的所队长可操作不是具体哪个 -->
												<s:if test='#session.loginPolice.ajdbxt_police.police_duty=="所队长"'>
													<button type="button" class="btn btn-primary  btn-block" onclick="case_review()"
														style="width: 40%; float: right;">提交</button>
												</s:if>
												</div>
											</div>
										</fieldset>
									</div>
								</div>
								<%-- </s:if> --%>
								<!-------------------------------------->
								<!-------------第7阶段   涉案财物入库---------------->
								<div class="first_process_div col-md-12" id="sheancaiwu">
									<div class="first_process">
										<fieldset>
											<legend>法制大队涉案财物入库</legend>
											<!--  -->
											<div class="summon_process col-md-12">
												<div class="process_title col-md-4">涉案财物已入库：</div>
												<div class="radio col-md-4">
													<label style="margin: 0 10px;"> <input type="radio" onclick=changecase_ending_yes(this)
														name="" id="" value="是"> 是
													</label><label style="margin: 0 10px;"> <input type="radio" onclick=changecase_ending_no(this)
														name="" id="" value="否"> 否
													</label>
												</div>
												<div class="col-md-4 process_button" >
													<s:if test='#session.loginPolice.ajdbxt_police.police_department=="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'>
													<button type="button" class="btn btn-primary  btn-block" onclick="case_ending()" id="process_case_end"
														style="width: 40%; float: right;">提交</button>
													</s:if>
												</div>
											</div>

										</fieldset>
									</div>
								</div>
								<!-------------------------------------->
								<!-------------第8阶段 法制大队结案---------------->
								<div class="first_process_div col-md-12">
									<div class="first_process">
										<fieldset>
											<legend>法制大队结案</legend>
											<!--  -->
											<div class="summon_process col-md-12">
												<div class="process_title col-md-4">是否结案：</div>
												<div class="radio col-md-4">
													<label style="margin: 0 10px;"> <input type="radio" onclick=changecase_ending_yes(this)
														name="ajdbxtProcess.process_case_end" id="case_ending_yes" value="是"> 是
													</label><label style="margin: 0 10px;"> <input type="radio" onclick=changecase_ending_no(this)
														name="ajdbxtProcess.process_case_end" id="case_ending_no" value="否"> 否
													</label>
												</div>
												<div class="col-md-4 process_button">
													<s:if test='#session.loginPolice.ajdbxt_police.police_department=="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'>
													<button type="button" class="btn btn-primary  btn-block" onclick="case_ending()" id="process_case_end"
														style="width: 40%; float: right;">提交</button>
													</s:if>
												</div>
											</div>
										</fieldset>
									</div>
								</div>
								<!-------------------------------------->
								<!-------------第9阶段 法制大队评分---------------->
								<div class="first_process_div col-md-12">
									<div class="first_process">
										<fieldset>
											<legend>法制大队评分</legend>
											<!--  -->
											<div class="summon_process col-md-12">
												<div class="process_title col-md-4">办案评分：</div>
												<div class="process_title col-md-4">
													<input type="text" class="form-control" name="process.process_score"
														id="input_case_score" placeholder="请填写案件评分">
												</div>
												<div class="col-md-4 process_button">
												<s:if test='#session.loginPolice.ajdbxt_police.police_department=="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'>
													<button type="button" class="btn btn-primary  btn-block" onclick="case_score()" id="case_score"
														style="width: 40%; float: right;">提交</button>
												</s:if>
												</div>
											</div>
										</fieldset>
									</div>
								</div>
								
								<!-------------------------------------->
								<!-------------第10阶段 法制大队确认案件上交---------------->
								<%-- 
								<s:if test='#session.loginPolice.ajdbxt_police.police_department=="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'>
								<div class="first_process_div col-md-12">
									<div class="first_process">
										<fieldset>
											<legend>案卷上交</legend>
											<!--  -->
											<div class="summon_process col-md-12">
												<!-- <div class="process_title col-md-4">案卷上交：</div> -->
												<div class="process_title col-md-4"><input type="hidden" name="ajdbxtProcess.process_file_hand" value="是">
													<s:if test='#session.loginPolice.ajdbxt_police.police_department=="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'>
													<button type="button" class="btn btn-primary  btn-block" onclick="process_file_hand()" id="process_file_hand"
														style="float: center;">上交案卷</button>
													</s:if>
												</div>

											</div>
										</fieldset>
									</div>
								</div>
								</s:if>
								 --%>
								 <s:if test='#session.loginPolice.ajdbxt_police.police_department=="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'>
								 <%-- <div class="first_process_div col-md-12">
									<div class="first_process">
										<fieldset>
											<legend>案卷上交</legend>
											<!--  -->
											<div class="summon_process col-md-12">
												<div class="process_title col-md-4">案卷已上交：</div>
												
												<div class="radio col-md-4">
													<label style="margin: 0 10px;"> <input type="radio" onclick=changecase_ending_yes(this)
														name="ajdbxtProcess.process_file_hand" value="是"> 是
													</label><label style="margin: 0 10px;"> <input type="radio" onclick=changecase_ending_no(this)
														name="ajdbxtProcess.process_file_hand" value="否"> 否
													</label>
												</div>
												<div class="col-md-4 process_button">
													<s:if test='#session.loginPolice.ajdbxt_police.police_department=="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'>
													<button type="button" class="btn btn-primary  btn-block" onclick="process_file_hand()" id="process_file_hand"
														style="width: 40%; float: right;">提交</button>
													</s:if>
												</div>
											</div>
										</fieldset>
									</div>
								</div> --%>
								<div class="first_process_div col-md-12">
									<div class="first_process">
										<fieldset>
											<legend>法制大队确认案卷上交</legend>
											<!--  -->
											<div class="summon_process col-md-12">
												<!-- <div class="process_title col-md-4">修改完成：</div> -->
												<div class="process_title col-md-4"><!-- <input type="hidden" name="ajdbxtProcess.process_is_rollback" value="是"> -->
													<s:if test='#session.loginPolice.ajdbxt_police.police_department=="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'>
													<button value="是" name="ajdbxtProcess.process_file_hand" type="button" class="btn btn-primary  btn-block" onclick="process_file_hand()"
														style="float: center;">案卷已上交</button>
													</s:if>
												</div>

											</div>
										</fieldset>
									</div>
								</div>
								</s:if>
								
								
								 
							</div>
							</form>
							<!-- administrative_process_right  -->
						</div>
						<!-- panel-body -->
					</div>
				</div>

			</div>
		</div>
	</div>
	
<!-- -------------------------------------------刑事案件----------------------------------------- -->
<div style="margin: 80px 0 0 0; float: left; width: 100%;" id="xingshi_case">
		<div class="panel" style="width: 95%; margin: 20px auto;">
			<!--  -->
			<div class="panel-heading">
				<h4 class="panel-title">刑事案件流程</h4>
			</div>
			<div class="panel-body">
				<div class="col-md-12">
					<div class="panel">
						<!--  -->
						<div class="panel-body">
							<!-- 左边 -->
<!-- 						<div id="administrative_process_left" class="col-md-4"></div> -->
							<!-- 右边 -->
							<form action="" id="penalProcessDetails">
										<div id="administrative_process_right" class="col-md-12">
											<!-- 	<div id="administrative_process_right" class="col-md-12"> -->
											<!-------------------------------------->
											<!-------------第一阶段   涉案财物入库---------------->
											<div class="first_process_div col-md-12" id="sheancaiwu">
												<div class="first_process">
													<fieldset>
														<legend>涉案财物</legend>
														<!-- 涉案财物 -->
														<div class="summon_process col-md-12" id="case_property">
															<div class="process_title col-md-4">办案单位涉案财物：</div>
															<div class="radio col-md-4">
																<label style="margin: 0 10px;"> <input
																	type="radio" onclick="penalchangecase_property_yes(this) ; case_propertyBtnClick(this)"
														name="ajdbxtProcess.process_case_goods" id="penalcase_property_yes" value="是"> 有
																</label><label style="margin: 0 10px;"> <input
																	type="radio" onclick="penalchangecase_property_no(this)  ; case_propertyBtnClick(this)"
														name="ajdbxtProcess.process_case_goods" id="penalcase_property_no" value="否"> 无
																</label>
															</div>
															<div class="col-md-4 process_button">
															<s:if test='#session.loginPolice.ajdbxt_police.police_duty=="警员" && #session.loginPolice.ajdbxt_police.police_department!="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'>
																<button type="button"
																	class="btn btn-primary  btn-block policemen_manager"  onclick="penalcase_property()"
																	style="width: 40%; float: right;">提交</button>
																	</s:if>
															</div>
														</div>
														<!-- 涉案财物已入库 -->
														<div class="summon_process col-md-12"
															id="property_storage_div" style="display: none;">
															<div class="process_title col-md-4">法制大队涉案财物已入库：</div>
															<div class="radio col-md-4">
																<label style="margin: 0 10px;"> <input  onclick="pencalchange_goods_in_lib_yes()" id="goods_in_lib_yes"
																	type="radio" name="ajdbxtProcess.process_goods_in_lib" value="是">   
																	是
																</label><label style="margin: 0 10px;"> <input  onclick="pencalchange_goods_in_lib_no()" id="goods_in_lib_no"
																	type="radio" name="ajdbxtProcess.process_goods_in_lib" value="否">
																	否
																</label>
															</div>
															<div class="col-md-4 process_button">
															<s:if test='#session.loginPolice.ajdbxt_police.police_department=="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'>
																<button type="button"    onclick="goods_in_lib()"
																	class="btn btn-primary  btn-block legal_team_manager_btn"
																	style="width: 40%; float: right;">提交</button>
																</s:if> 
															</div>
														</div>

													</fieldset>
												</div>
											</div>
											<!-------------------------------------->
											<!-------------第二阶段 传唤---------------->
											<div class="first_process_div col-md-12">
												<div class="first_process">
													<fieldset>
														<legend>传唤</legend>
														<!-- 是否传唤嫌疑人 -->
														<div class="summon_process col-md-12">
												<div class="process_title col-md-4">办案单位延长传唤：</div>
												<div class="radio col-md-4">
													<label style="margin: 0 10px;"> <input type="radio" onclick=penalchangesuspect_summon_yes(this)
														name="ajdbxtProcess.process_lengthen_subpoena" id="penalsuspect_summon_yes">
														是
													</label><label style="margin: 0 10px;"> <input type="radio" onclick=penalchangesuspect_summon_no(this)
														name="ajdbxtProcess.process_lengthen_subpoena" id="penalsuspect_summon_no">
														否
													</label>
												</div>
												<div class="col-md-4 process_button">
													<s:if test='#session.loginPolice.ajdbxt_police.police_duty=="警员" && #session.loginPolice.ajdbxt_police.police_department!="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'>
													<button type="button" class="btn btn-primary  btn-block"
													onclick="penalsuspect_summon(this)"	style="width: 40%; float: right;">提交</button>
												</s:if>
												</div>
											</div>
														<!-- 询问未成年人 -->
														<div class="summon_process col-md-12">
															<div class="process_title col-md-4">办案单位询问未成年人：</div>
															<div class="radio col-md-4">
																<label style="margin: 0 10px;"> <input
																	type="radio" name="ajdbxtProcess.process_nonage"  id="penalminors_asking_yes"  onclick=penalchangeminors_asking_yes(this)
																	value="是"> 是
																</label><label style="margin: 0 10px;"> <input
																	type="radio" name="ajdbxtProcess.process_nonage"  id="penalminors_asking_no"   onclick=penalchangeminors_asking_no(this)
																	value="否"> 否
																</label>
															</div>
															<div class="col-md-4 process_button">
															<s:if test='#session.loginPolice.ajdbxt_police.police_duty=="警员" && #session.loginPolice.ajdbxt_police.police_department!="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'>
																<button type="button"
																	class="btn btn-primary  btn-block policemen_manager"  onclick="penalminors_asking()"
																	style="width: 40%; float: right;">提交</button>
																	</s:if>
															</div>
															<!-- <div class="col-md-4 process_title">提醒：通知监护人到场</div> -->
														</div>

														<!-- 鉴定 -->
														<div class="summon_process col-md-12">
															<div class="process_title col-md-4">办案单位鉴定：</div>
															<div class="radio col-md-4">
																<label style="margin: 0 10px;"> <input
																	type="radio" name="ajdbxtProcess.process_authenticate"  id="penalidentification_yes"  onclick=penalchangeidentification_yes(this)
																	value="是"> 是
																</label><label style="margin: 0 10px;"> <input
																	type="radio" name="ajdbxtProcess.process_authenticate"  id="penalidentification_no"  onclick=penalchangeidentification_no(this)
																	value="否"> 否
																</label>
															</div>
															<!-- <div class="col-md-4 process_title">提醒：请在4日内作出决定，5日内将鉴定意见复印件送达违法嫌疑人及被害人</div> -->
															<div class="col-md-4 process_button">
															<s:if test='#session.loginPolice.ajdbxt_police.police_duty=="警员" && #session.loginPolice.ajdbxt_police.police_department!="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'>
																<button type="button"
																	class="btn btn-primary  btn-block policemen_manager"    onclick="penalidentification()"
																	style="width: 40%; float: right;">提交</button>
																	</s:if>
															</div>
														</div>

														<!-- 申请听证 -->
														<div class="summon_process col-md-12">
															<div class="process_title col-md-4">办案单位申请听证：</div>
															<div class="radio col-md-4">
																<label style="margin: 0 10px;"> <input
																	type="radio" name="ajdbxtProcess.process_apply_right" id="penalhearing_applying_yes" onclick=penalchangehearing_applying_yes(this)
																	value="是"> 是
																</label><label style="margin: 0 10px;"> <input
																	type="radio" name="ajdbxtProcess.process_apply_right"  id="penalhearing_applying_no"  onclick=penalchangehearing_applying_no(this)
																	value="否"> 否
																</label>
															</div>
															<div class="col-md-4 process_button">
															<s:if test='#session.loginPolice.ajdbxt_police.police_duty=="警员" && #session.loginPolice.ajdbxt_police.police_department!="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'>
																<button type="button"
																	class="btn btn-primary  btn-block policemen_manager"    onclick="penalhearing_applying()"
																	style="width: 40%; float: right;">提交</button>
																	</s:if>
															</div>
														</div>

													</fieldset>
												</div>
											</div>
											<!-- ------------------------ -->
											<!-- 第三阶段  第一次强制措施 -->
											<div class="first_process_div col-md-12">
												<div class="first_process">
													<fieldset>
														<legend>办案单位第一次强制措施 ：</legend>
														<div class="summon_process col-md-12"
															id="hearing_applying_div">

															<div class="radio col-md-4">
																<label style="margin: 0 10px;"> <input
																	type="radio" onclick="mandatory_measuresBtnClick_one(this); mandatory_measuresBtnClick(this) "   id="measure_one_one"
																	name="ajdbxtProcess.process_force_measure_one" value="拘留"> 拘留   
																</label><label style="margin: 0 10px;"> <input
																	type="radio" onclick="mandatory_measuresBtnClick_two(this) ;mandatory_measuresBtnClick(this)"  id="measure_one_two"
																	name="ajdbxtProcess.process_force_measure_one" value="监视居住"> 监视居住
																</label> <label style="margin: 0 10px;"> <input
																	type="radio" onclick="mandatory_measuresBtnClick_three(this) ;mandatory_measuresBtnClick(this)"  id="measure_one_three"
																	name="ajdbxtProcess.process_force_measure_one" value="取保候审"> 取保候审
																</label>
															</div>
															<div class="col-md-4 process_button">
															<s:if test='#session.loginPolice.ajdbxt_police.police_duty=="警员" && #session.loginPolice.ajdbxt_police.police_department!="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'>
																<button type="button"
																	class="btn btn-primary  btn-block policemen_manager"  onclick="penalmeasure_one()"
																	style="width: 40%; float: right;">提交</button>
																	</s:if>
															</div>
														</div>
													</fieldset>
												</div>
											</div>
											<!-- ---------------------------------------------------->
											<!-------------第一次强制措施案卷上交---------------->
											<s:if test='#session.loginPolice.ajdbxt_police.police_department=="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'> 
											<div class="first_process_div col-md-12">
												<div class="first_process">
													<fieldset>
														<legend>法制大队案卷上交</legend>
														<!--  -->
														<div class="summon_process col-md-12">
															<div class="process_title col-md-4">案卷上交：</div>
															<div class="process_title col-md-4">
																<input type="hidden"  
																	name="ajdbxtProcess.process_file_hand" value="是">
																<button type="button"   onclick="pencalcasehand()"
																	class="btn btn-primary  btn-block legal_team_manager_btn"
																	style="float: center;">案卷上交</button>
															</div>

														</div>
													</fieldset>
												</div>
											</div>
											</s:if> 
											<!-------------------------------------->
											<!-- ----------------------------------------案件已拿回------ -->
												<s:if test='#session.loginPolice.ajdbxt_police.police_department=="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'> 
											<div class="first_process_div col-md-12"  id="casehand_no" style="display: none;">
												<div class="first_process">
													<fieldset>
														<legend>法制大队案卷已拿回</legend>
														<!--  -->
														<div class="summon_process col-md-12">
															<div class="process_title col-md-4">案卷拿回：</div>
															<div class="process_title col-md-4">
																<input type="hidden"  
																	name="ajdbxtProcess.process_file_hand" value="否">
																<button type="button"   onclick="pencalcasehand_no()"
																	class="btn btn-primary  btn-block legal_team_manager_btn"
																	style="float: center;">案卷拿回</button>
															</div>

														</div>
													</fieldset>
												</div>
											</div>
											</s:if> 
											<!-------------第四阶段 拘留延长期限---------------->
											<div class="first_process_div col-md-12"
												style="display: none;" id="detention_delay_date">
												<div class="first_process">
													<fieldset>
														<legend>办案单位拘留延长期限</legend>
														<div class="summon_process col-md-12">
															<div class="process_title col-md-4">拘留延长期限：</div>
															<div class="radio col-md-4">
																<label style="margin: 0 10px;"> <input  onclick="pencalchangecriminal_detention_one()"  id="process_lengthen_criminal_detention_one"
																	type="radio" name="ajdbxtProcess.process_lengthen_criminal_detention" value="30">
																	30
																</label><label style="margin: 0 10px;"> <input  onclick="pencalchangecriminal_detention_two()"  id="process_lengthen_criminal_detention_two"
																	type="radio" name="ajdbxtProcess.process_lengthen_criminal_detention" value="7">
																	7
																</label><label style="margin: 0 10px;"> <input  onclick="pencalchangecriminal_detention_three()"  id="process_lengthen_criminal_detention_three"
																	type="radio" name="ajdbxtProcess.process_lengthen_criminal_detention" value="0">
																	0
																</label>
															</div>
															<div class="col-md-4 process_button">
															<s:if test='#session.loginPolice.ajdbxt_police.police_duty=="警员" && #session.loginPolice.ajdbxt_police.police_department!="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'> 
																<button type="button"   onclick="process_lengthen_criminal_detention()"
																	class="btn btn-primary  btn-block policemen_manager"
																	style="width: 40%; float: right;">提交</button>
																	</s:if> 
															</div>
														</div>
													</fieldset>
												</div>
											</div>
											<!-------------------------------------->
											<!-------------第五阶段 法制大队提出问题---------------->
											<div class="first_process_div col-md-12">
												<div class="first_process">
													<fieldset>
														<legend>法制大队提出问题</legend>
														<div class="summon_process col-md-12">
															<div class="process_title col-md-4">
																<input type="text" class="form-control"
																	name="process.process_question_list"  id="penalprocess_question_list" 
																	placeholder="请填写提出问题数量">
															</div>
															<div class="col-md-4 process_button">
															<s:if test='#session.loginPolice.ajdbxt_police.police_department=="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'> 
																<button type="button"
																	class="btn btn-primary  btn-block legal_team_manager_btn"    onclick="pencalproblem_asking()"
																	style="width: 40%; float: right;">提交</button>
 																	</s:if> 
															</div>
														</div>
													</fieldset>
												</div>
											</div>
											<!-------------------------------------->
											<!-------------第六阶段 普通警员进行问题整改---------------->
											<div class="first_process_div col-md-12">
												<div class="first_process">
													<fieldset>
														<legend>法制大队问题整改</legend>
														<!--  -->
														<div class="summon_process col-md-12">
															<!-- <div class="process_title col-md-4">问题整改：</div>	 -->
															<div class="process_title col-md-4">
																<input type="text" class="form-control"
																	name="process.process_question" id="penalprocess_question"
																	placeholder="请填写问题整改数量">
															</div>
															<div class="col-md-4 process_button">
														<s:if test='#session.loginPolice.ajdbxt_police.police_department=="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'> 
																<button type="button"
																	class="btn btn-primary  btn-block legal_team_manager_btn"  onclick="pencalproblem_rectification()" 
																	id="but_process_question"
																	style="width: 40%; float: right;">提交</button>
																	</s:if> 
															</div>
														</div>
													</fieldset>
												</div>
											</div>
											<!-------------------------------------->

											<!-------------第七阶段 第二次强制措施-----(拘留)----------->
											<div class="first_process_div col-md-12"
												id="second_punishment" style="display: none;">
												<div class="first_process">
													<fieldset>
														<legend>办案单位第二次强制措施 ：</legend>
														<!-- 处罚 -->
														<div class="summon_process col-md-12 ">
															<!-- <div class="process_title col-md-2">第二次强制措施:</div> -->
															<div class="checkbox col-md-8"
																id="second_punishment_content">
																<label style="margin: 0 10px;"> <input type="radio" name="ajdbxtProcess.process_force_measure_two" value="逮捕" 
																id="second_punishment_one"  onclick="changesecond_punishmentClick_one();second_punishmentClick()"> 逮捕</label>
																<label style="margin: 0 10px;"><input type="radio" name="ajdbxtProcess.process_force_measure_two" value="取保候审"
																 id="second_punishment_two" onclick="changesecond_punishmentClick_two() ;second_punishmentClick()" > 取保候审 </label>
																 <label style="margin: 0 10px;"><input type="radio" name="ajdbxtProcess.process_force_measure_two" value="监视居住" 
																 id="second_punishment_three" onclick="changesecond_punishmentClick_three(); second_punishmentClick()">监视居住 </label>
																</div>
															<div class="col-md-2 process_button">
															<s:if test='#session.loginPolice.ajdbxt_police.police_duty=="警员" && #session.loginPolice.ajdbxt_police.police_department!="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'>
																<button type="button"
																	class="btn btn-primary  btn-block policemen_manager"    onclick="second_punishment()"
																	style="width: 90%; float: right;">提交</button>
																	</s:if>
															</div>
														</div>
													</fieldset>
												</div>
											</div>
											<!-- ------------------------------------------第二次强制措施案卷上交------------------------------------------------------>
											<s:if test='#session.loginPolice.ajdbxt_police.police_department=="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'> 
											<div class="first_process_div col-md-12"  id="twocase_hand_juliu" style="display: none;">
												<div class="first_process">
													<fieldset>
														<legend>法制大队案卷上交</legend>
														<!--  -->
														<div class="summon_process col-md-12">
															<div class="process_title col-md-4">案卷上交：</div>
															<div class="process_title col-md-4">
																<input type="hidden"
																	name="ajdbxtProcess.process_file_hand" value="是">
																<button type="button"   onclick="pencalcasehand()"
																	class="btn btn-primary  btn-block legal_team_manager_btn"
																	style="float: center;">案卷上交</button>
															</div>

														</div>
													</fieldset>
												</div>
											</div>
											</s:if> 
											<!-- -------------------------------------------------------------取保候审、监视居住----------------- -->
											<div class="first_process_div col-md-12"
												id="qubao_second_punishment" style="display: none;">
												<div class="first_process">
													<fieldset>
														<legend>办案单位第二次强制措施 ：</legend>
														<!-- 处罚 -->
														<div class="summon_process col-md-12 ">
															<!-- <div class="process_title col-md-2">第二次强制措施:</div> -->
															<div class="checkbox col-md-8"
																id="second_punishment_content">
																<label style="margin: 0 10px;"> <input type="radio" name="ajdbxtProcess.process_force_measure_two" value="撤案" onclick="pencalchange_cheantwo_yes();second_punishmentClick() " id="chenantwo_yes"> 撤案 </label>
																<label style="margin: 0 0px;"> <input type="radio" name="ajdbxtProcess.process_force_measure_two"  value="起诉" onclick="pencalchange_cheantwo_no();second_punishmentClick()" id="chenantwo_no"> 起诉 </label>
																</div>
															<div class="col-md-2 process_button">
															<s:if test='#session.loginPolice.ajdbxt_police.police_duty=="警员" && #session.loginPolice.ajdbxt_police.police_department!="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'>
																<button type="button"
																	class="btn btn-primary  btn-block policemen_manager"    onclick="chenantwo_second_punishment()"
																	style="width: 90%; float: right;">提交</button>
																	</s:if>
															</div>
														</div>
													</fieldset>
												</div>
											</div>
											<!-- ---------------------------------------------------------第二次案卷上交----------------- -->
											<s:if test='#session.loginPolice.ajdbxt_police.police_department=="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'> 
											<div class="first_process_div col-md-12"  id="twocase_hand_qubao" style="display: none;">
												<div class="first_process">
													<fieldset>
														<legend>法制大队案卷上交</legend>
														<!--  -->
														<div class="summon_process col-md-12">
															<div class="process_title col-md-4">案卷上交：</div>
															<div class="process_title col-md-4">
																<input type="hidden"
																	name="ajdbxtProcess.process_file_hand" value="是">
																<button type="button"   onclick="pencalcasehand()"
																	class="btn btn-primary  btn-block legal_team_manager_btn"
																	style="float: center;">案卷上交</button>
															</div>

														</div>
													</fieldset>
												</div>
											</div>
											</s:if> 
											<!-------------------------------------->
											<!-------------第八阶段 第三次强制措施--------（逮捕）-------->
											<div class="first_process_div col-md-12"
												id="third_punishment" style="display: none;">
												<div class="first_process">
													<fieldset>
														<legend>办案单位第三次强制措施 ：</legend>
														<!-- 处罚 -->
														<div class="summon_process col-md-12 ">
															<!-- <div class="process_title col-md-2">第三次强制措施:</div> -->
															<div class="checkbox col-md-8"
																id="third_punishment_value">
																<label style="margin: 0 10px;"> <input type="radio" name="ajdbxtProcess.process_force_measure_three" value="取保候审" onclick="pencalchangequbaothree_yes() ;third_punishmentClick()" id="qubaothree_yes"> 取保候审</label>
																<label style="margin: 0 10px;"><input type="radio" name="ajdbxtProcess.process_force_measure_three" value="起诉" onclick="pencalchangequbaothree_no() ;third_punishmentClick()" id="qubaothree_no"> 起诉 </label>
																</div>
															<div class="col-md-2 process_button">
															<s:if test='#session.loginPolice.ajdbxt_police.police_duty=="警员" && #session.loginPolice.ajdbxt_police.police_department!="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'>
																<button type="button"    onclick="third_punishment()"
																	class="btn btn-primary  btn-block policemen_manager"
																	style="width: 90%; float: right;">提交</button>
																	</s:if>
															</div>
														</div>
													</fieldset>
												</div>
											</div>
											<!-- ------------------------------------------------------------------------------- -->
											<!-------------第三次案卷上交-------->
											<s:if test='#session.loginPolice.ajdbxt_police.police_department=="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'> 
											<div class="first_process_div col-md-12" id="threecase_hand_daibu" style="display: none;">
												<div class="first_process">
													<fieldset>
														<legend>法制大队案卷上交</legend>
														<!--  -->
														<div class="summon_process col-md-12">
															<div class="process_title col-md-4">案卷上交：</div>
															<div class="process_title col-md-4">
																<input type="hidden"
																	name="ajdbxtProcess.process_file_hand" value="是">
																<button type="button"   onclick="pencalcasehand()"
																	class="btn btn-primary  btn-block legal_team_manager_btn"
																	style="float: center;">案卷上交</button>
															</div>

														</div>
													</fieldset>
												</div>
											</div>
											</s:if> 
											<!-- ------------------------------取保候审、监视居住------------------------------------- -->
										<div class="first_process_div col-md-12"
												id="qubao_third_punishment" style="display: none;">
												<div class="first_process">
													<fieldset>
														<legend>办案单位第三次强制措施 ：</legend>
														<!-- 处罚 -->
														<div class="summon_process col-md-12 ">
															<!-- <div class="process_title col-md-2">第三次强制措施:</div> -->
															<div class="checkbox col-md-8"
																id="third_punishment_value">
																<label style="margin: 0 10px;"> <input type="radio" name="ajdbxtProcess.process_force_measure_three" value="撤案" onclick="pencalchangecheanthree_yes() ;third_punishmentClick()" id="cheanthree_yes"> 撤案 </label>
																<label style="margin: 0 0px;"> <input type="radio" name="ajdbxtProcess.process_force_measure_three" value="起诉" onclick="pencalchangecheanthree_no() ;third_punishmentClick()" id="cheanthree_no"> 起诉 </label>
																</div>
															<div class="col-md-2 process_button">
															<s:if test='#session.loginPolice.ajdbxt_police.police_duty=="警员" && #session.loginPolice.ajdbxt_police.police_department!="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'>
																<button type="button"   onclick="qubao_third_punishment()"
																	class="btn btn-primary  btn-block policemen_manager"
																	style="width: 90%; float: right;">提交</button>
																	</s:if>
															</div>
														</div>
													</fieldset>
												</div>
											</div>
											<!-------------第三次案卷上交---------------->
											<s:if test='#session.loginPolice.ajdbxt_police.police_department=="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'> 
											<div class="first_process_div col-md-12" id="threecase_hand_qubao" style="display: none;">
												<div class="first_process">
													<fieldset>
														<legend>法制大队案卷上交</legend>
														<!--  -->
														<div class="summon_process col-md-12">
															<div class="process_title col-md-4">案卷上交：</div>
															<div class="process_title col-md-4">
																<input type="hidden"
																	name="ajdbxtProcess.process_file_hand" value="是">
																<button type="button"   onclick="pencalcasehand()"
																	class="btn btn-primary  btn-block legal_team_manager_btn"
																	style="float: center;">案卷上交</button>
															</div>

														</div>
													</fieldset>
												</div>
											</div>
											</s:if> 
											<!-------------------------------------->
											<!-------------第九阶段 第四次强制措施------取保候审---------->
											<div class="first_process_div col-md-12"
												id="fourth_punishment" style="display: none;">
												<div class="first_process">
													<fieldset>
														<legend>办案单位第四次强制措施 ：</legend>
														<!-- 处罚 -->
														<div class="summon_process col-md-12 ">
															<!-- <div class="process_title col-md-2">第四次强制措施:</div> -->
															<div class="checkbox col-md-8"
																id="fourth_punishment_value">
																<label style="margin: 0 10px;"> <input type="radio" name="ajdbxtProcess.process_result_of_prosecution" value="撤案" onclick="pencalchangechenfour_yes() ;four_punishmentClick()" id="cheanfour_yes"> 撤案 </label>
																<label style="margin: 0 0px;"> <input type="radio" name="ajdbxtProcess.process_result_of_prosecution" value="起诉" onclick="pencalchangechenfour_no() ;four_punishmentClick()" id="chenanfour_no"> 起诉 </label>
																</div>
															<div class="col-md-2 process_button">
															<s:if test='#session.loginPolice.ajdbxt_police.police_duty=="警员" && #session.loginPolice.ajdbxt_police.police_department!="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'>
																<button type="button"    onclick="fourth_punishment()"
																	class="btn btn-primary  btn-block policemen_manager"
																	style="width: 90%; float: right;">提交</button>
																	</s:if>
															</div>
														</div>
													</fieldset>
												</div>
											</div>
											<!-------------------------------------->
											<!-------------第十阶段   补查---------------->
											<div class="first_process_div col-md-12"
												id="supplement_check" style="display: none;">
												<div class="first_process">
													<fieldset>
														<!-- <legend>补查</legend> -->
														<div class="summon_process col-md-12" id="checkOne"
															style="display: none;">
															<div class="process_title col-md-4">办案单位补查一次：</div>
															<div class="radio col-md-4" id="checkOne_value">
															<label style="margin: 0 10px;"> <input type="radio" name="ajdbxtProcess.process_search_result_one"  value="是" onclick="pencalchangecheckone_yes() ;checkOne_Click()" id="checkone_yes"> 是</label>
															<label style="margin: 0 10px;"> <input type="radio"   name="ajdbxtProcess.process_search_result_one"  value="否" onclick="pencalchangecheckone_no() ;checkOne_Click()" id="checkedone_no"> 否 </label>
															</div>
															<div class="col-md-4 process_button">
															<s:if test='#session.loginPolice.ajdbxt_police.police_duty=="警员" && #session.loginPolice.ajdbxt_police.police_department!="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'>
																<button type="button"  onclick="checkOne()"
																	class="btn btn-primary  btn-block policemen_manager"
																	style="width: 40%; float: right;">提交</button>
																	</s:if>
															</div>
														</div>
														<!--  -->
														<div class="summon_process col-md-12" id="checkTwo"
															style="display: none;">
															<div class="process_title col-md-4">办案单位补查二次：</div>
															<div class="radio col-md-4" id="checkTwo_value">
															<label style="margin: 0 10px;"> <input type="radio" name="ajdbxtProcess.process_search_result_two"  value="是"  onclick="pencalchangechecktwo_yes()" id="checktwo_yes"> 是 </label>
														    <label style="margin: 0 10px;"> <input type="radio" name="ajdbxtProcess.process_search_result_two" value="否" onclick="pencalchangechecktwo_no()" id="checkedtwo_no"> 否 </label>
															</div>
															<div class="col-md-4 process_button">
															<s:if test='#session.loginPolice.ajdbxt_police.police_duty=="警员" && #session.loginPolice.ajdbxt_police.police_department!="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'>
																<button type="button"  onclick="checkTwo()"
																	class="btn btn-primary  btn-block policemen_manager"
																	style="width: 40%; float: right;">提交</button>
																	</s:if>
															</div>
														</div>

													</fieldset>
												</div>
											</div>
											<!-------------------------------------->
											<!-------------第十一阶段 法制大队结案---------------->
											<div class="first_process_div col-md-12">
												<div class="first_process">
													<fieldset>
														<legend>结案</legend>
														<!--  -->
														<div class="summon_process col-md-12">
															<div class="process_title col-md-4">法制大队结案：</div>
															<div class="radio col-md-4">
																<label style="margin: 0 10px;"> <input
																	type="radio" name="ajdbxtProcess.process_case_end"   onclick=pencalchangecase_ending_yes(this)  id="pencalcase_ending_yes"
																	value="是"> 是
																</label><label style="margin: 0 10px;"> <input
																	type="radio" name="ajdbxtProcess.process_case_end"   onclick=pencalchangecase_ending_no(this)  id="pencalcase_ending_no"
																	value="否"> 否
																</label>
															</div>
															<div class="col-md-4 process_button">
															<s:if test='#session.loginPolice.ajdbxt_police.police_department=="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'> 
																<button type="button"
																	class="btn btn-primary  btn-block policemen_manager"   onclick="pencalcase_ending()" 
																	style="width: 40%; float: right;">提交</button>
																</s:if> 
															</div>
														</div>

													</fieldset>
												</div>
											</div>
											<!-------------------------------------->
											<!-------------第十二阶段 法制大队评分---------------->
											<div class="first_process_div col-md-12">
												<div class="first_process">
													<fieldset>
														<legend>法制大队评分</legend>
														<!--  -->
														<div class="summon_process col-md-12">
															<!-- <div class="process_title col-md-4">案件评分：</div> -->
															<div class="process_title col-md-4">
																<input type="text" class="form-control"   id="pencalinput_case_score"
																	name="process.process_score" placeholder="请填写案件评分">
															</div>
															<div class="col-md-4 process_button">
 																<s:if test='#session.loginPolice.ajdbxt_police.police_department=="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'> 
																<button type="button"
																	class="btn btn-primary  btn-block legal_team_manager_btn"    onclick="pencalcase_score()" 
																	style="width: 40%; float: right;">提交</button>
																	</s:if> 
															</div>
														</div>
													</fieldset>
												</div>
											</div>

											<!-------------------------------------->
											<!-------------第十三阶段 案卷上交---------------->
<%-- 											<s:if test='#session.loginPolice.ajdbxt_police.police_department=="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'>  --%>
<!-- 											<div class="first_process_div col-md-12"> -->
<!-- 												<div class="first_process"> -->
<!-- 													<fieldset> -->
<!-- 														<legend>法制大队案卷上交</legend> -->
<!-- 														 -->
<!-- 														<div class="summon_process col-md-12"> -->
<!-- 															<div class="process_title col-md-4">案卷上交：</div> -->
<!-- 															<div class="process_title col-md-4"> -->
<!-- 																<input type="hidden" -->
<!-- 																	name="ajdbxtProcess.process_file_hand" value="是"> -->
<!-- 																<button type="button"   onclick="pencalcasehand()" -->
<!-- 																	class="btn btn-primary  btn-block legal_team_manager_btn" -->
<!-- 																	style="float: center;">案卷上交</button> -->
<!-- 															</div> -->

<!-- 														</div> -->
<!-- 													</fieldset> -->
<!-- 												</div> -->
<!-- 											</div> -->
<%-- 											</s:if>  --%>

										</div>
									</form>
							<!-- administrative_process_right  -->
						</div>
						<!-- panel-body -->
					</div>
				</div>

			</div>
		</div>
	</div>
<!-- ------------------------------------------案件流程---------------------------------------------------------------------------- -->
		</div>
	</div>

	<script type="text/javascript"
		src="<%=basePath%>js/Info/CaseDetails.js"></script>
<%-- 	<script type="text/javascript" --%>
<%-- 		src="<%=basePath%>js/Process/penalCaseDetails.js"> --%>
<%-- 	<script type="text/javascript" --%>
<%-- 		src="<%=basePath%>js/Process/processDetails.js"></script> --%>
	<script type="text/javascript">
		$.datetimepicker.setLocale('ch');
		$('.staff_joinPartyTime').datetimepicker({
			yearStart : 1900, // 设置最小年份
			yearEnd : 2100, // 设置最大年份
			yearOffset : 0, // 年偏差
			timepicker : false, // 关闭时间选项
			format : 'Y-m-d', // 格式化日期年-月-日
			minDate : '1900/01/01', // 设置最小日期

			maxDate : '2100/01/01', // 设置最大日期

		});
		$('.staff_joinWorkTime').datetimepicker({
			yearStart : 1900, // 设置最小年份
			yearEnd : 2100, // 设置最大年份
			yearOffset : 0, // 年偏差
			timepicker : true, // 关闭时间选项
			format : 'Y-m-d', // 格式化日期年-月-日
			minDate : '1900/01/01', // 设置最小日期
			maxDate : '2100/01/01', // 设置最大日期
		});
		$('.staff_thePoliceTime').datetimepicker({
			yearStart : 1900, // 设置最小年份
			yearEnd : 2100, // 设置最大年份
			yearOffset : 0, // 年偏差
			timepicker : true, // 关闭时间选项
			format : 'Y-m-d ', // 格式化日期年-月-日
			minDate : '1900/01/01', // 设置最小日期
			maxDate : '2100/01/01', // 设置最大日期
		});
		$('.staff_birthday').datetimepicker({
			yearStart : 1900, // 设置最小年份
			yearEnd : 2100, // 设置最大年份
			yearOffset : 0, // 年偏差
			timepicker : true, // 关闭时间选项
			format : 'Y-m-d ', // 格式化日期年-月-日
			minDate : '1900/01/01', // 设置最小日期
			maxDate : '2100/01/01', // 设置最大日期
		});
		$('.staffStudent_startTime').datetimepicker({
			yearStart : 1900, // 设置最小年份
			yearEnd : 2100, // 设置最大年份
			yearOffset : 0, // 年偏差
			timepicker : true, // 关闭时间选项
			format : 'Y-m-d ', // 格式化日期年-月-日
			minDate : '1900/01/01', // 设置最小日期
			maxDate : '2100/01/01', // 设置最大日期
		});
		$('.staffStudent_stopTime').datetimepicker({
			yearStart : 1900, // 设置最小年份
			yearEnd : 2100, // 设置最大年份
			yearOffset : 0, // 年偏差
			timepicker : true, // 关闭时间选项
			format : 'Y-m-d ', // 格式化日期年-月-日
			minDate : '1900/01/01', // 设置最小日期
			maxDate : '2100/01/01', // 设置最大日期
		});
		$('.staffFamily_birthday').datetimepicker({
			yearStart : 1900, // 设置最小年份
			yearEnd : 2100, // 设置最大年份
			yearOffset : 0, // 年偏差
			timepicker : true, // 关闭时间选项
			format : 'Y-m-d ', // 格式化日期年-月-日
			minDate : '1900/01/01', // 设置最小日期
			maxDate : '2100/01/01', // 设置最大日期
		});
		$('.staffWork_startTime').datetimepicker({
			yearStart : 1900, // 设置最小年份
			yearEnd : 2100, // 设置最大年份
			yearOffset : 0, // 年偏差
			timepicker : true, // 关闭时间选项
			format : 'Y-m-d ', // 格式化日期年-月-日
			minDate : '1900/01/01', // 设置最小日期
			maxDate : '2100/01/01', // 设置最大日期
		});
		$('.staffWork_stopTime').datetimepicker({
			yearStart : 1900, // 设置最小年份
			yearEnd : 2100, // 设置最大年份
			yearOffset : 0, // 年偏差
			timepicker : true, // 关闭时间选项
			format : 'Y-m-d ', // 格式化日期年-月-日
			minDate : '1900/01/01', // 设置最小日期
			maxDate : '2100/01/01', // 设置最大日期
		});
		$('.staffMove_inTime').datetimepicker({
			yearStart : 1900, // 设置最小年份
			yearEnd : 2100, // 设置最大年份
			yearOffset : 0, // 年偏差
			timepicker : true, // 关闭时间选项
			format : 'Y-m-d ', // 格式化日期年-月-日
			minDate : '1990/01/01', // 设置最小日期
			maxDate : '2100/01/01', // 设置最大日期
		});
		$('.staffMove_outTime').datetimepicker({
			yearStart : 1900, // 设置最小年份
			yearEnd : 2100, // 设置最大年份
			yearOffset : 0, // 年偏差
			timepicker : true, // 关闭时间选项
			format : 'Y-m-d ', // 格式化日期年-月-日
			minDate : '1900/01/01', // 设置最小日期
			maxDate : '2100/01/01', // 设置最大日期
		});
		$('.staffReward_Time').datetimepicker({
			yearStart : 1900, // 设置最小年份
			yearEnd : 2100, // 设置最大年份
			yearOffset : 0, // 年偏差
			timepicker : true, // 关闭时间选项
			format : 'Y-m-d ', // 格式化日期年-月-日
			minDate : '1900/01/01', // 设置最小日期
			maxDate : '2100/01/01', // 设置最大日期
		});
		$('.staffPrinciple_Time').datetimepicker({
			yearStart : 1900, // 设置最小年份
			yearEnd : 2100, // 设置最大年份
			yearOffset : 0, // 年偏差
			timepicker : true, // 关闭时间选项
			format : 'Y-m-d ', // 格式化日期年-月-日
			minDate : '1990/01/01', // 设置最小日期
			maxDate : '2100/01/01', // 设置最大日期
		});
		$('.staffPunishment_Time').datetimepicker({
			yearStart : 1900, // 设置最小年份
			yearEnd : 2100, // 设置最大年份
			yearOffset : 0, // 年偏差
			timepicker : true, // 关闭时间选项
			format : 'Y-m-d', // 格式化日期年-月-日
			minDate : '1900/01/01', // 设置最小日期
			maxDate : '2100/01/01', // 设置最大日期
		});
		$('.staffFurlough_startTime').datetimepicker({
			yearStart : 1900, // 设置最小年份
			yearEnd : 2100, // 设置最大年份
			yearOffset : 0, // 年偏差
			timepicker : true, // 关闭时间选项
			format : 'Y-m-d ', // 格式化日期年-月-日
			minDate : '1900/01/01', // 设置最小日期
			maxDate : '2100/01/01', // 设置最大日期
		});
		$('.staffFurlough_stopTime').datetimepicker({
			yearStart : 1900, // 设置最小年份
			yearEnd : 2100, // 设置最大年份
			yearOffset : 0, // 年偏差
			timepicker : true, // 关闭时间选项
			format : 'Y-m-d ', // 格式化日期年-月-日
			minDate : '1900/01/01', // 设置最小日期
			maxDate : '2100/01/01', // 设置最大日期
		});
	</script>

</body>
</html>