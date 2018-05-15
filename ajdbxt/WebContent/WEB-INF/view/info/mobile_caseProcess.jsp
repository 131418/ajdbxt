<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!doctype html>
<html>

<head>
<meta charset="UTF-8">
<title></title>
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<link href="<%=basePath%>css/mui.min.css" rel="stylesheet" />
<link rel="stylesheet" href="<%=basePath%>css/Process/processDetails.css">
<style>
body {
	font-size: 15px;
}

.index_nav {
	background-color: #007aff;
	color: white;
	font-size: 15px;
}

h1, a {
	color: white;
}

input {
	text-align: right;
}
.mui-input-row{
padding:0 5px;
}
</style>
</head>

<body>
	<header class="mui-bar mui-bar-nav index_nav">
		<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
		<h1 class="mui-title" style="color: white;">案件信息</h1>
	</header>
	<div class="mui-content" id="case_details_content">

		<div class="mui-card"
			style="width: 100%; margin: 0px;">
			<!--页眉，放置标题-->
<!-- 			<div class="mui-card-header"> -->
<!-- 				<h3 class="mui-h4" style="color:#007aff">案件流程信息</h3> -->
<!-- 			</div> -->
			<div class="mui-card-content">
	<div style="margin: 0px 0 0 0; float: left; width: 100%;" id="xingzheng_case">
		<div class="panel" style="width: 95%; ">
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

							<!-- 右边 -->
							<form action="" id="processDetails">
							<div id="administrative_process_right" class="col-md-12">
					
								<!-------------------------------------->
								<!-------------第一阶段 传唤---------------->
								<div class="first_process_div col-md-12">
									<div class="first_process">
										<fieldset>
											<legend>传讯</legend>
											<!-- 是否传唤嫌疑人 -->
											<div class="summon_process col-md-12 mui-input-row ">
												<div class="process_title col-md-4">办案单位延长传唤：</div>
												<div class="radio col-md-4 ">
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
													onclick="loadcaseDetail_suspect_summon(this)"	style="width: 20%; float: right;">提交</button>
												<div id="cuo"><img alt="" src="<%=basePath%>img/对(2).png"></div>
												
												</s:if>
												</div>
											</div>
											<!-- 询问未成年人 -->
											<div class="summon_process col-md-12 mui-input-row">
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
														style="width: 20%; float: right;">提交</button>
													</s:if>
												</div>
												<!-- <div class="col-md-4 process_title">提醒：通知监护人到场</div> -->
											</div>

											<!-- 鉴定 -->
											<div class="summon_process col-md-12 mui-input-row">
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
														style="width: 20%; float: right;">提交</button>
													</s:if>
												</div>
											</div>

											<!-- 涉案财物 -->
											<div class="summon_process col-md-12 mui-input-row">
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
														style="width: 20%; float: right;" onclick="case_property()">提交</button>
												    </s:if>
												</div>
											</div>

										</fieldset>
									</div>
								</div>
								<!-- ------------------------ -->
								<!-- 第二阶段   听证 -->
									<div class="first_process_div col-md-12 mui-input-row">
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
														style="width: 20%; float: right;" onclick="hearing_applying()">提交</button>
												    </s:if>
												</div>
											</div>
										</fieldset>
									</div>
								</div>

								<!-------------------------------------->
								<!-------------第3阶段 法制大队打回案件---------------->
								<s:if test='#session.loginPolice.ajdbxt_police.police_department=="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'>
									<div class="first_process_div col-md-12 mui-input-row" id="dahuixiugai">
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
														style="width: 20%; float: right;">提交</button>
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
								<div class="first_process_div col-md-12 mui-input-row" id="xiugaiok" hidden="true">
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
								<div class="first_process_div col-md-12 ">
									<div class="first_process">
										<fieldset>
											<legend>法制大队提出问题</legend>
											<div class="summon_process col-md-12">
<!-- 												<div class="process_title col-md-4">提出问题数量：</div> -->
<!--                                                 <div class="process_title col-md-4"> -->
												<label class="mui-h5">提出问题数量：</label>
													<input type="text" class="" name="process.process_question_list" style=""
														id="process_question_list" >
<!-- 												</div> -->
												<div class="col-md-4 process_button">
												<s:if test='#session.loginPolice.ajdbxt_police.police_department=="67ed5ab3-d773-4ac1-981b-2839ed0cec5c"'>
													<button type="button" class="btn btn-primary  btn-block" onclick="problem_asking()"
														style="width: 20%; float: right;">提交</button>
												</s:if>
												</div>
											</div>
										</fieldset>
									</div>
								</div>
								<!-------------------------------------->
								<!-------------第5阶段 法制大队天界问题整改数量---------------->
								<div class="first_process_div col-md-12 mui-input-row" >
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
														style="width: 20%; float: right;">提交</button>
													</s:if>
												</div>
											</div>
										</fieldset>
									</div>
								</div>
								<!-------------------------------------->
								<!-------------第6阶段 处罚---------------->
									<div class="first_process_div col-md-12 mui-input-row">
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
													 	<input type="checkbox" name="process.process_mandatory_abandon_drug" value="是" id="process_mandatory_abandon_drug">隔离戒毒
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
														style="width: 20%; float: right;">提交</button>
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
								<div class="first_process_div col-md-12 ">
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
				
			</div>
			<!--页脚，放置补充信息或支持的操作-->
			
		</div>



	</div>

	<!--------------------------------->
	<!--底部导航-->
	<nav class="mui-bar mui-bar-tab">
				<a class="mui-tab-item " onclick="window.location.href='<%=basePath%>user/User_mobile_index'"> <span
			class="mui-icon mui-icon-home"></span> <span class="mui-tab-label">首页</span>
		</a> <a class="mui-tab-item" onclick="window.location.href='<%=basePath%>user/User_mobile_police_one'"> <span class="mui-icon mui-icon-person"></span>
			<span class="mui-tab-label" >人员</span>
		</a> <a class="mui-tab-item"  href="#Popover_1"> <span class="mui-icon mui-icon-email"></span>
			<span class="mui-tab-label">统计</span>
		</a> <a class="mui-tab-item" onclick="window.location.href='<%=basePath%>info/Info_page_mobileCaseList'"> <span
			class="mui-icon mui-icon-chatboxes"></span> <span
			class="mui-tab-label">案件</span>
		</a>

	</nav>
	<div id="Popover_1" class="mui-popover mui-bar-popover" style="top: 376px; left: 112.167px;width:150px;text-align: center;position: fixed;">
			<div class="mui-popover-arrow mui-bottom">
			</div>
			<ul class="mui-table-view" style="width:150px;background-color: white;">
				<li class="mui-table-view-cell" >
					<a href="">按单位统计</a>
				</li>
				<li class="mui-table-view-cell">
					<a href="">按人员统计</a>
				</li>
			</ul>
		</div>
	<script src="<%=basePath%>js/jquery-3.1.1.min.js"></script>
		
 	<script type="text/javascript" 
 		src="<%=basePath%>js/Info/mobile_caseOneProcess.js"></script> 
	<script src="<%=basePath%>js/mui.min.js"></script>
	<script type="text/javascript">
		mui.init();
		var self = plus.webview.currentWebview();  
		var ajdbxt_info_id = self.ajdbxt_info_id;  
		console.log(ajdbxt_info_id);
	</script>
<%-- <%-- 	<script type="text/javascript"> --%>
<!--  		case_details();  -->
<%-- <%-- 	</script> --%>
</body>

</html>