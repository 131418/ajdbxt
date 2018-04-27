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
<!-- ------------------------------------------------------------------------------------->
<div style="margin: 80px 0 0 0; float: left; width: 100%;">
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
						<div id="administrative_process_left" class="col-md-4"></div>
							<!-- 右边 -->
							<form action="" id="processDetails">
							<div id="administrative_process_right" class="col-md-8">
							<!-- 	<div id="administrative_process_right" class="col-md-12"> -->
								<!-------------------------------------->
								<!-------------第一阶段 传唤---------------->
								<div class="first_process_div col-md-12">
									<div class="first_process">
										<fieldset>
											<legend>传唤</legend>
											<!-- 是否传唤嫌疑人 -->
											<div class="summon_process col-md-12">
												<div class="process_title col-md-4">延长传唤嫌疑人：</div>
												<div class="radio col-md-4">
													<label style="margin: 0 10px;"> <input type="radio" onclick=changesuspect_summon_yes(this)
														name="ajdbxtProcess.process_lengthen_subpoena" id="suspect_summon_yes" value="是">
														是
													</label><label style="margin: 0 10px;"> <input type="radio" onclick=changesuspect_summon_no(this)
														name="ajdbxtProcess.process_lengthen_subpoena" id="suspect_summon_no" value="否">
														否
													</label>
												</div>
												<div class="col-md-4 process_button">
													<button type="button" class="btn btn-primary  btn-block"
													onclick="suspect_summon(this)"	style="width: 40%; float: right;">提交</button>
												</div>
											</div>
											<!-- 询问未成年人 -->
											<div class="summon_process col-md-12">
												<div class="process_title col-md-4">询问未成年人：</div>
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
													<button type="button" class="btn btn-primary  btn-block" onclick="minors_asking()"
														style="width: 40%; float: right;">提交</button>
												</div>
												<!-- <div class="col-md-4 process_title">提醒：通知监护人到场</div> -->
											</div>

											<!-- 鉴定 -->
											<div class="summon_process col-md-12">
												<div class="process_title col-md-4">鉴定：</div>
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
													<button type="button" class="btn btn-primary  btn-block" onclick="identification()"
														style="width: 40%; float: right;">提交</button>
												</div>
											</div>

											<!-- 涉案财物 -->
											<div class="summon_process col-md-12">
												<div class="process_title col-md-4">涉案财物：</div>
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
													<button type="button" class="btn btn-primary  btn-block"
														style="width: 40%; float: right;" onclick="case_property()">提交</button>
												</div>
											</div>

											<!-- 涉案财物已入库 -->
<!-- 											<div id="property_storage_div" class="summon_process col-md-12" style="display:none;"> -->
<!-- 												<div class="process_title col-md-4">涉案财物已入库：</div> -->
<!-- 												<div class="radio col-md-4"> -->
<!-- 													<label style="margin: 0 10px;"> <input type="radio" -->
<!-- 														name="property_storage" id="property_storage_yes" value=""> -->
<!-- 														是 -->
<!-- 													</label><label style="margin: 0 10px;"> <input type="radio" -->
<!-- 														name="property_storage" id="property_storage_no" value=""> -->
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
								<!-- ------------------------ -->
								<!-- 第二阶段    处罚-->
								<div class="first_process_div col-md-12">
									<div class="first_process">
										<fieldset>
											<legend>处罚</legend>
											<!-- 处罚 -->
											<div class="summon_process col-md-12">
												<div class="process_title col-md-2">处罚:</div>
												<div class="checkbox col-md-8" >
													<label style="margin: 0 10px;"> <input
														type="checkbox" name="process.process_detention" value="是" id="process_detention"> 行政拘留
													</label><label style="margin: 0 10px;"> <input
														type="checkbox" name="process.process_penalty" value="是" id="process_penalty"> 罚款
													</label>
													 <label style="margin: 0 10px;"> <input onclick=changeprocess_treatment_category_yes(this) id="process_treatment_category_yes"
														type="radio" name="ajdbxtProcess.process_treatment_category" value="是">强制隔离戒毒
													</label><label style="margin: 0 0px;"> <input id="process_treatment_category_no" onclick=changeprocess_treatment_category_no(this)
														type="radio" name="ajdbxtProcess.process_treatment_category" value="否"> 社区戒毒
													</label>
												</div>
												<div class="col-md-2 process_button">
													<button type="button" class="btn btn-primary  btn-block" onclick="punishmentab()"
														style="width: 90%; float: right;">提交</button>
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
								<!-------------第三阶段 听证---------------->
								<div class="first_process_div col-md-12">
									<div class="first_process">
										<fieldset>
											<legend>听证</legend>
											<!-- 听证申请-->
											<div class="summon_process col-md-12" id="hearing_applying_div">
												<div class="process_title col-md-4">听证申请：</div>
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
													<button type="button" class="btn btn-primary  btn-block"
														style="width: 40%; float: right;" onclick="hearing_applying()">提交</button>
												</div>
											</div>
											<!-- 受理听证-->
<!-- 											<div class="summon_process col-md-12" id="hearing_accepting_div"  style="display:none;"> -->
<!-- 												<div class="process_title col-md-4">受理听证：</div> -->
<!-- 												<div class="radio col-md-4"> -->
<!-- 													<label style="margin: 0 10px;"> <input type="radio" -->
<!-- 														name="hearing_accepting" id="hearing_accepting_yes" -->
<!-- 														value="hearing_accepting_yes"> 是 -->
<!-- 													</label><label style="margin: 0 10px;"> <input type="radio" -->
<!-- 														name="hearing_accepting" id="hearing_accepting_no" value="hearing_accepting_no"> -->
<!-- 														否 -->
<!-- 													</label> -->
<!-- 												</div> -->
<!-- 												<div class="col-md-4 process_button"> -->
<!-- 													<button type="button" class="btn btn-primary  btn-block" -->
<!-- 														style="width: 40%; float: right;" onclick="hearing_acceptingBtnClick(this)">提交</button> -->
<!-- 												</div> -->
<!-- 											</div> -->
<!-- 											听证举行 -->
<!-- 											<div class="summon_process col-md-12" id="hearing_holding_div" style="display:none;"> -->
<!-- 												<div class="process_title col-md-4">听证举行：</div> -->
<!-- 												<div class="radio col-md-4"> -->
<!-- 													<label style="margin: 0 10px;"> <input type="radio" -->
<!-- 														name="hearing_holding" id="hearing_holding_yes" value="hearing_holding_yes"> -->
<!-- 														是 -->
<!-- 													</label><label style="margin: 0 10px;"> <input type="radio" -->
<!-- 														name="hearing_holding" id="hearing_holding_no" value="hearing_holding_no"> -->
<!-- 														否 -->
<!-- 													</label> -->
<!-- 												</div> -->
<!-- 												<div class="col-md-4 process_button"> -->
<!-- 													<button type="button" class="btn btn-primary  btn-block" -->
<!-- 														style="width: 40%; float: right;" >提交</button> -->
<!-- 												</div> -->
<!-- 											</div> -->
										</fieldset>
									</div>
								</div>
								<!-------------------------------------->
								<!-------------第四阶段 法制大队提出问题---------------->
								<div class="first_process_div col-md-12">
									<div class="first_process">
										<fieldset>
											<legend>提出问题</legend>
											<div class="summon_process col-md-12">
												<div class="process_title col-md-4">提出问题：</div>
												<div class="radio col-md-4">
													<label style="margin: 0 10px;"> <input type="radio" onclick=changeproblem_asking_yes(this)
														name="ajdbxtProcess.process_question_list" id="problem_asking_yes" value="是">
														是
													</label><label style="margin: 0 10px;"> <input type="radio" onclick=changeproblem_asking_no(this)
														name="ajdbxtProcess.process_question_list" id="problem_asking_no" value="否">
														否
													</label>
												</div>
												<div class="col-md-4 process_button">
													<button type="button" class="btn btn-primary  btn-block" onclick="problem_asking()"
														style="width: 40%; float: right;">提交</button>
												</div>
											</div>
										</fieldset>
									</div>
								</div>

								<!-------------------------------------->
								<!-------------第五阶段   所队长 审核---------------->
<%-- 								<s:if test='#session.loginPolice.police_duty=="所长"'> --%>
								<div class="first_process_div col-md-12">
									<div class="first_process">
										<fieldset>
											<legend>审核</legend>
											<!--  -->
											<div class="summon_process col-md-12">
												<div class="process_title col-md-4">审核：</div>
												<div class="radio col-md-4">
													<label style="margin: 0 10px;"> <input type="radio" onclick=changecase_review_yes(this)
														name="ajdbxtProcess.process_captain_check" id="case_review_yes" value="是"> 是
													</label><label style="margin: 0 10px;"> <input type="radio" onclick=changecase_review_no(this)
														name="ajdbxtProcess.process_captain_check" id="case_review_no" value="否"> 否
													</label>
												</div>
												<div class="col-md-4 process_button">
													<button type="button" class="btn btn-primary  btn-block" onclick="case_review()"
														style="width: 40%; float: right;">提交</button>
												</div>
											</div>
										</fieldset>
									</div>
								</div>
<%--                                 </s:if>    --%>
								<!-------------------------------------->
								<!-------------第六阶段 普通警员进行问题整改---------------->
								<div class="first_process_div col-md-12" >
									<div class="first_process">
										<fieldset>
											<legend>问题整改</legend>
											<!--  -->
											<div class="summon_process col-md-12">
												<div class="process_title col-md-4">问题整改：</div>
												<div class="radio col-md-4">
													<label style="margin: 0 10px;"> <input type="radio" onclick=changeproblem_rectification_yes(this)
														name="ajdbxtProcess.process_question" id="problem_rectification_yes"
														value="是"> 是
													</label><label style="margin: 0 10px;"> <input type="radio" onclick=changeproblem_rectification_no(this)
														name="ajdbxtProcess.process_question" id="problem_rectification_no"
														value="否"> 否
													</label>
												</div>
												<div class="col-md-4 process_button">
													<button type="button" class="btn btn-primary  btn-block" onclick="problem_rectification()"
														style="width: 40%; float: right;">提交</button>
												</div>
											</div>
										</fieldset>
									</div>
								</div>
								

								<!-------------------------------------->
								<!-------------第八阶段 法制大队评分---------------->
								<div class="first_process_div col-md-12">
									<div class="first_process">
										<fieldset>
											<legend>评分</legend>
											<!--  -->
											<div class="summon_process col-md-12">
												<div class="process_title col-md-4">案件评分：</div>
												<div class="process_title col-md-4">
													<input type="text" class="form-control" name="process.process_score"
														id="input_case_score" placeholder="请填写案件评分">
												</div>
												<div class="col-md-4 process_button">
													<button type="button" class="btn btn-primary  btn-block" onclick="case_score()"
														style="width: 40%; float: right;">提交</button>
												</div>
											</div>
										</fieldset>
									</div>
								</div>
								
								<!-------------------------------------->
								<!-------------第七阶段 法制大队结案---------------->
								<div class="first_process_div col-md-12">
									<div class="first_process">
										<fieldset>
											<legend>结案</legend>
											<!--  -->
											<div class="summon_process col-md-12">
												<div class="process_title col-md-4">结案：</div>
												<div class="radio col-md-4">
													<label style="margin: 0 10px;"> <input type="radio" onclick=changecase_ending_yes(this)
														name="ajdbxtProcess.process_case_end" id="case_ending_yes" value="是"> 是
													</label><label style="margin: 0 10px;"> <input type="radio" onclick=changecase_ending_no(this)
														name="ajdbxtProcess.process_case_end" id="case_ending_no" value="否"> 否
													</label>
												</div>
												<div class="col-md-4 process_button">
													<button type="button" class="btn btn-primary  btn-block" onclick="case_ending()"
														style="width: 40%; float: right;">提交</button>
												</div>
											</div>

										</fieldset>
									</div>
								</div>
								
								<!-------------------------------------->
								<!-------------第九阶段 办案民警---------------->
								<div class="first_process_div col-md-12">
									<div class="first_process">
										<fieldset>
											<legend>案件上交</legend>
											<!--  -->
											<div class="summon_process col-md-12">
												<div class="process_title col-md-4">案件上交：</div>
												<div class="process_title col-md-4">
													<button type="button" class="btn btn-primary  btn-block" 
														style="float: center;">上交案件</button>
												</div>

											</div>
										</fieldset>
									</div>
								</div>
								
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
<%-- 	<script type="text/javascript" --%>
<%--  		src="<%=basePath%>js/Process/processDetails.js"></script>  --%>
<%-- 	<script type="text/javascript" --%>
<%-- 		src="<%=basePath%>js/Process/administrativeProcess.js"></script> --%>
<!-- ------------------------------------------------------------------------------------->
<!-- ------------------------------------------------------------------------------------->
<!-- ------------------------------------------案件流程---------------------------------------------------------------------------- -->
<!-- 				<div class="panel-body staff_body"> -->
<!-- 				<form id="processDetails" enctype="multipart/form-data" -->
<!-- 					accept-charset="utf-8"> -->
<!-- 					<table style="width:90%;margin-left: 30px;"> -->
<!-- 						<tr> -->
<!-- 							<td colspan="6" class="staff_info_title">流程信息</td> -->
<!-- 						</tr> -->
<!-- 						<tr style="height: 20px"></tr> -->
<!-- 						<tr> -->
<!-- 							<td><label>是否延长传唤</label></td> -->
<!-- 							<td><label style="float: left; width: 50px;" class="fancy-radio" > -->
<!-- 									<input name="process.process_lengthen_subpoena" onclick="buildCase_chose(this)" -->
<%-- 									type="radio" value="是"> <span><i></i>是</span> --%>
<!-- 							</label><label style="float: left; margin-left: 10px;" -->
<!-- 								class="fancy-radio"> <input name="process.process_lengthen_subpoena" -->
<!-- 									onclick="buildCase_nochose(this)" type="radio" value="否"> -->
<%-- 									<span><i></i>否</span> --%>
<!-- 							</label> <input type="hidden" name="process.process_lengthen_subpoena" id="subpoena"></td> -->

<!-- 							<td><label>是否未成年</label></td>  -->
<!-- 							<td><label style="float: left; width: 50px;" -->
<!-- 								class="fancy-radio "> <input name="nonage" -->
<%-- 									onclick="nonage_isFormat(this)" type="radio" value="是"> <span><i></i>是</span> --%>
<!-- 							</label> <label style="float: left; margin-left: 10px; width: 50px;" -->
<!-- 								class="fancy-radio "> <input name="nonage" -->
<%-- 									onclick="nonage_isNotFormat(this)" type="radio" value="否"> <span><i></i>否</span> --%>
<!-- 							</label> <input type="hidden" name="process.process_nonage" -->
<!-- 								id="format" /></td> -->
<!-- 						</tr> -->
<!-- 						<tr> -->
<!-- 							<td><label>鉴定</label></td> -->
<!-- 						<td><label style="float: left; width: 50px;" -->
<!-- 								class="fancy-radio "> <input name="authenticate" -->
<%-- 									onclick="isFormat(this)" type="radio" value="是"> <span><i></i>是</span> --%>
<!-- 							</label> <label style="float: left; margin-left: 10px; width: 50px;" -->
<!-- 								class="fancy-radio "> <input name="authenticate" -->
<%-- 									onclick="isNotFormat(this)" type="radio" value="否"> <span><i></i>否</span> --%>
<!-- 							</label> <input type="hidden" name="process.process_authenticate" -->
<!-- 								id="format" /></td> -->
                         
<!-- 							<td><label>涉案财物</label></td> -->
<!-- 							<td><label style="float: left; width: 50px;" -->
<!-- 								class="fancy-radio "> <input name="goods" -->
<%-- 									onclick="goods_isFormat(this)" type="radio" value="是"> <span><i></i>是</span> --%>
<!-- 							</label> <label style="float: left; margin-left: 10px; width: 50px;" -->
<!-- 								class="fancy-radio "> <input name="goods" -->
<%-- 									onclick="goods_isNotFormat(this)" type="radio" value="否"> <span><i></i>否</span> --%>
<!-- 							</label> <input type="hidden" name="process.process_case_goods" -->
<%-- 								id="format" /><span id="caiwu" style="display:none;">24小时入库</span></td> --%>
<!-- 						</tr> -->
<!-- 	</table> -->
<!-- 	<div class="panel-body staff_body"> -->
<%-- 	<span id="jianding" style="display:none;padding-right:0px;">5日内将鉴定意见复印件送达违法嫌疑人及被害人</span> --%>
<!-- 	</div> -->
<!-- 	</form> -->
<!-- 					<button style="float: right; margin-right: 9%; margin-top: 30px;" -->
<!-- 					type="button" class="btn btn-default button" -->
<!-- 					onclick="case_relive()"> -->
<!-- 					<i class="fa fa-pencil"></i> 提交 -->
<!-- 				</button> -->
<!-- </div> -->
<!-- <!-- --------------------------------------------------------------------------------------------------> 
<!-- 				<div class="panel-body staff_body"> -->
<!-- 				<form id="processDetails" enctype="multipart/form-data" -->
<!-- 					accept-charset="utf-8"> -->
<!-- 					<table style="width:90%;margin-left: 30px;"> -->
<!-- 						<tr> -->
<!-- 							<td colspan="6" class="staff_info_title"></td> -->
<!-- 						</tr> -->
<!-- 						<tr style="height: 20px"></tr> -->
<!-- 						<tr> -->
<!-- 							<td><label>行政处罚</label></td> -->
<!-- 							<td><label style="float: left; width: 80px;"  > -->
<!-- 									<input name="subpoena" onclick="buildCase_chose(this)" -->
<%-- 									type="checkbox" value="是"> <span><i></i>行政拘留</span> --%>
<!-- 							</label> -->
<!-- 							<label style="float: left; margin-left: 80px;"> <input name="subpoena" -->
<!-- 									onclick="buildCase_chose(this)" type="checkbox" value="否"> -->
<%-- 									<span><i></i>罚款</span> --%>
<!-- 							</label> -->
<!-- 							<label style="float: left; margin-left: 80px;"> <input name="subpoena" -->
<!-- 									onclick="buildCase_chose(this)" type="checkbox" value="否"> -->
<%-- 									<span><i></i>强制隔离戒毒</span> --%>
<!-- 							</label> -->
<!-- 							<label style="float: left; margin-left: 80px;"> <input name="subpoena" -->
<!-- 									onclick="buildCase_chose(this)" type="checkbox" value="否"> -->
<%-- 									<span><i></i>社区戒毒</span> --%>
<!-- 							</label> -->
<!-- 								 <input type="hidden" name="process.process_lengthen_subpoena"></td> -->
<!-- 						</tr> -->
<!-- 	</table> -->
<!-- 	</form> -->
<!-- 					<button style="float: right; margin-right: 9%; margin-top: 30px;" -->
<!-- 					type="button" class="btn btn-default button" -->
<!-- 					onclick="staff_relive()"> -->
<!-- 					<i class="fa fa-pencil"></i> 提交 -->
<!-- 				</button> -->
<!-- </div> -->

<!-- ------------------------------------------案件流程---------------------------------------------------------------------------- -->
		</div>
	</div>

	<script type="text/javascript"
		src="<%=basePath%>js/Info/CaseDetails.js"></script>
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