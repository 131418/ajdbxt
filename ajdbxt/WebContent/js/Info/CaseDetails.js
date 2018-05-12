window.onload = function() {
	var url = window.location.href;
	info_id = url.substring(url.indexOf("=") + 1);
//console.log(info_id);
	get_staffDetails(info_id);
	get_processDetails(info_id);
	get_penalProcessDetails(info_id);
}

function isContains(str, substr) {
    return str.indexOf(substr) >= 0;
}

function get_staffDetails(info_id) {
//console.log("b1");
	var url = "/ajdbxt/info/Info_getSingleInfo?info.ajdbxt_info_id=" + info_id;
	get_staffDetails_Ajax(url, info_id);
}

function get_processDetails(info_id) {
//console.log("b1");
	var url = "/ajdbxt/process/findSingleProcessAction?ajdbxtProcess.process_case_id="
			+ info_id;
	get_processDetails_Ajax(url, info_id);
}
function get_penalProcessDetails(info_id){
	var url = "/ajdbxt/process/findSingleProcessAction?ajdbxtProcess.process_case_id="
		+ info_id;
get_penalProcessDetails_Ajax(url, info_id);
}
//案件基本信息
function get_staffDetails_Ajax(url, info_id) {
	var xmlhttp;
	if (window.XMLHttpRequest) {
		// IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
		xmlhttp = new XMLHttpRequest();
	} else {
		// IE6, IE5 浏览器执行代码
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var staff_info = xmlhttp.responseText;
			staff_info = JSON.parse(staff_info);
			// 遍历并插入input的value
			$.each(staff_info.info, function(key, value) {
				$('input[name="info.' + key + '"]').val(value);
				//法制员
				if (key == 'info_department_legal_member') {
					$('input[name="info.' + key + '"]').val(staff_info.legal.police_name);
				}
				//所队长
				if (key == 'info_department_captain') {
					$('input[name="info.' + key + '"]').val(staff_info.cap.police_name);
				}
			});
			// 办案单位
			$.each(staff_info.department, function(key, value) {
				$('input[name="department.' + key + '"]').val(value);
			});
			//值班局领导
			$.each(staff_info.leader, function(key, value) {
				$('input[name="leader.' + key + '"]').val(value);
			});
			//值班法制大队民警
			$.each(staff_info.legal, function(key, value) {
				$('input[name="legal.' + key + '"]').val(value);
			});
			// 主办民警
			$.each(staff_info.police[0], function(key, value) {
				$('input[name="police[0].' + key + '"]').val(value);
			});
			// 协办民警1
			$.each(staff_info.police[1], function(key, value) {
				$('input[name="police[1].' + key + '"]').val(value);
			});
			// 协办民警2
			$.each(staff_info.police[2], function(key, value) {
				$('input[name="police[2].' + key + '"]').val(value);
			});
			info_category(staff_info);
		}
	}
	xmlhttp.open("post", url, true);
	xmlhttp.send();
}

//判断案件类别显示相应的案件流程
function info_category(staff_info){
	var case1=staff_info.info;
	if(case1.info_category=='行政案件'){
		$("#xingzheng_case").show();
	}else {
		$("#xingzheng_case").hide();
	}
	if(case1.info_category=='刑事案件'){
		$("#xingshi_case").show();
	}else{
		$("#xingshi_case").hide();
	}
}


//刑事案件流程
function get_penalProcessDetails_Ajax(url, info_id){
	var xmlhttp;
	if (window.XMLHttpRequest) {
		// IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
		xmlhttp = new XMLHttpRequest();
	} else {
		// IE6, IE5 浏览器执行代码
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var staff_info = xmlhttp.responseText;
			staff_info = JSON.parse(staff_info);
			var case1 = staff_info.process;
			$.each(case1, function(key, value) {
				$('input[name="ajdbxtProcess.' + key + '"]').val(value);
			});
			//延长传唤
			if(case1.process_lengthen_subpoena!=null && case1.process_lengthen_subpoena.length>0){
				if("是"==case1.process_lengthen_subpoena){
					$('#penalsuspect_summon_yes').attr("checked","checked");
					$("#penalsuspect_summon_no").prop("disabled", true);
				}
				if("否"==case1.process_lengthen_subpoena){
					$('#penalsuspect_summon_no').attr("checked","checked");
					$("#penalsuspect_summon_yes").prop("disabled", true);
				}
			}  
			//未成年人
			if(case1.process_nonage!=null && case1.process_nonage.length>0){
				if("是"==case1.process_nonage){
					$('#penalminors_asking_yes').attr("checked","checked");
					$("#penalminors_asking_no").prop("disabled", true);
				}
				if("否"==case1.process_nonage){
					$('#penalminors_asking_no').attr("checked","checked");
					$("#penalminors_asking_yes").prop("disabled", true);
				}
			}  
			//鉴定
			if(case1.process_authenticate!=null && case1.process_authenticate.length>0){
				if("是"==case1.process_authenticate){
					$('#penalidentification_yes').attr("checked","checked");
					$("#penalidentification_no").prop("disabled", true);
				}
				if("否"==case1.process_authenticate){
					$('#penalidentification_no').attr("checked","checked");
					$("#penalidentification_yes").prop("disabled", true);
				}
			}
			//听证
			if(case1.process_apply_right!=null && case1.process_apply_right.length>0){
				if("是"==case1.process_apply_right){
					$('#penalhearing_applying_yes').attr("checked","checked");
					$("#penalhearing_applying_no").prop("disabled", true);
				}
				if("否"==case1.process_apply_right){
					$('#penalhearing_applying_no').attr("checked","checked");
					$("#penalhearing_applying_yes").prop("disabled", true);
				}
			}
			//第一次强制措施
			if(case1.process_force_measure_one!=null && case1.process_force_measure_one.length>0){
				if("拘留"==case1.process_force_measure_one){
					$('#measure_one_one').attr("checked","checked");
					$("#measure_one_two").prop("disabled", true);
					$("#measure_one_three").prop("disabled", true);
				}
				if("监视居住"==case1.process_force_measure_one){
					$('#measure_one_two').attr("checked","checked");
					$("#measure_one_one").prop("disabled", true);
					$("#measure_one_three").prop("disabled", true);
				}
				if("取保候审"==case1.process_force_measure_one){
					$('#measure_one_three').attr("checked","checked");
					$("#measure_one_one").prop("disabled", true);
					$("#measure_one_two").prop("disabled", true);
				}
			} 
			//有无涉案财物
			if(case1.process_case_goods!=null && case1.process_case_goods.length>0){
				if("是"==case1.process_case_goods){
					$('#penalcase_property_yes').attr("checked","checked");
					$("#penalcase_property_no").prop("disabled", true);
				}
				if("否"==case1.process_case_goods){
					$('#penalcase_property_no').attr("checked","checked");
					$("#penalcase_property_yes").prop("disabled", true);
				}
			} 
		//结案
			if(case1.process_case_end!=null && case1.process_case_end.length>0){
				if("是"==case1.process_case_end){
					$('#pencalcase_ending_yes').attr("checked","checked");
					$("#pencalcase_ending_no").prop("disabled", true);
				}
				if("否"==case1.process_case_end){
					$('#pencalcase_ending_no').attr("checked","checked");
					$("#pencalcase_ending_yes").prop("disabled", true);
				}
			} 
			//延长拘留
			if(case1.process_lengthen_criminal_detention!=null && case1.process_lengthen_criminal_detention.length>0){
				if("30"==case1.process_lengthen_criminal_detention){
					$('#process_lengthen_criminal_detention_one').attr("checked","checked");
					$("#process_lengthen_criminal_detention_two").prop("disabled", true);
					$("#process_lengthen_criminal_detention_three").prop("disabled", true);
				}
				if("7"==case1.process_lengthen_criminal_detention){
					$('#process_lengthen_criminal_detention_two').attr("checked","checked");
					$("#process_lengthen_criminal_detention_one").prop("disabled", true);
					$("#process_lengthen_criminal_detention_three").prop("disabled", true);
				}
				if("0"==case1.process_lengthen_criminal_detention){
					$('#process_lengthen_criminal_detention_three').attr("checked","checked");
					$("#process_lengthen_criminal_detention_one").prop("disabled", true);
					$("#process_lengthen_criminal_detention_two").prop("disabled", true);
				}
			} 
			//第二次强制措施
			if(case1.process_force_measure_two!=null && case1.process_force_measure_two.length>0){
				if("逮捕"==case1.process_force_measure_two){
					$('#second_punishment_one').attr("checked","checked");
					$("#second_punishment_two").prop("disabled", true);
					$("#second_punishment_three").prop("disabled", true);
				}
				if("取保候审"==case1.process_force_measure_two){
					$('#second_punishment_two').attr("checked","checked");
					$("#second_punishment_one").prop("disabled", true);
					$("#second_punishment_three").prop("disabled", true);
				}
				if("监视居住"==case1.process_force_measure_two){
					$('#second_punishment_three').attr("checked","checked");
					$("#second_punishment_one").prop("disabled", true);
					$("#second_punishment_two").prop("disabled", true);
				}
				if("撤案"==case1.process_force_measure_two){
					$('#chenantwo_yes').attr("checked","checked");
					$("#chenantwo_no").prop("disabled", true);
				}
				if("起诉"==case1.process_force_measure_two){
					$('#chenantwo_no').attr("checked","checked");
					$("#chenantwo_yes").prop("disabled", true);
				}
			} 
			//第三强制措施
			if(case1.process_force_measure_three!=null && case1.process_force_measure_three.length>0){
				if("取保候审"==case1.process_force_measure_three){
					$('#qubaothree_yes').attr("checked","checked");
					$("#qubaothree_no").prop("disabled", true);
				}
				if("起诉"==case1.process_force_measure_three){
					$('#qubaothree_no').attr("checked","checked");
					$("#qubaothree_yes").prop("disabled", true);
					$('#cheanthree_no').attr("checked","checked");
					$("#cheanthree_yes").prop("disabled", true);
				}
				if("撤案"==case1.process_force_measure_three){
					$('#cheanthree_yes').attr("checked","checked");
					$("#cheanthree_no").prop("disabled", true);
				}
			}
			//第四次强制措施
			if(case1.process_result_of_prosecution!=null && case1.process_result_of_prosecution.length>0){
				if("撤案"==case1.process_result_of_prosecution){
					$('#cheanfour_yes').attr("checked","checked");
					$("#chenanfour_no").prop("disabled", true);
				}
				if("起诉"==case1.process_result_of_prosecution){
					$('#chenanfour_no').attr("checked","checked");
					$("#cheanfour_yes").prop("disabled", true);
				}
			}
			//第一次补查
			if(case1.process_search_result_one!=null && case1.process_search_result_one.length>0){
				if("是"==case1.process_search_result_one){
					$('#checkone_yes').attr("checked","checked");
					$("#checkedone_no").prop("disabled", true);
				}
				if("否"==case1.process_result_of_prosecution){
					$('#checkedone_no').attr("checked","checked");
					$("#checkone_yes").prop("disabled", true);
				}
			}
			//第二次补查
			if(case1.process_search_result_two!=null && case1.process_search_result_two.length>0){
				if("是"==case1.process_search_result_two){
					$('#checktwo_yes').attr("checked","checked");
					$("#checkedtwo_no").prop("disabled", true);
				}
				if("否"==case1.process_result_of_prosecution){
					$('#checkedtwo_no').attr("checked","checked");
					$("#checktwo_yes").prop("disabled", true);
				}
			}
			//涉案财物已入库
			if(case1.process_goods_in_lib!=null && case1.process_goods_in_lib.length>0){
				if("是"==case1.process_goods_in_lib){
					$('#goods_in_lib_yes').attr("checked","checked");
					$("#goods_in_lib_no").prop("disabled", true);
				}
				if("否"==case1.process_goods_in_lib){
					$('#goods_in_lib_no').attr("checked","checked");
					$("#goods_in_lib_yes").prop("disabled", true);
				}
			} 
			  pencalmanagement(case1);
		}
	}
	xmlhttp.open("post", url, true);
	xmlhttp.send();
}
//------------------------------------------------隐藏与显示
function   pencalmanagement(case1){
	//涉案财物已入库
	if(case1.process_case_goods=="是"){
		$("#property_storage_div").show();
	}
	//拘留延长期限、第二次强制措施
	if(case1.process_force_measure_one=="拘留"){
		$("#detention_delay_date").show();
		$("#second_punishment").show();
		$("#twocase_hand_juliu").show();
	}
	if(case1.process_force_measure_one=="取保候审"||case1.process_force_measure_one=="监视居住"){
		$("#qubao_second_punishment").show();
		$("#twocase_hand_qubao").show();
	}
	//第三次强制措施
	if(case1.process_force_measure_two=="逮捕"){
		$("#third_punishment").show();
		$("#threecase_hand_daibu").show();
	}
	if(case1.process_force_measure_two=="取保候审"||case1.process_force_measure_two=="监视居住"){
		$("#qubao_third_punishment").show();
		$("#threecase_hand_qubao").show();
	}
//	if(case1.process_force_measure_two=="起诉"){
//		$("#supplement_check").show();
//		$("#checkOne").show();
//	}
	//第四次强制措施
	if(case1.process_force_measure_three=="取保候审"){
		$("#fourth_punishment").show();
	}
//	if(caase1.process_force_measure_three=="起诉"){
//		$("#supplement_check").show();
//		$("#checkOne").show();
//	}
	//第四次的强制措施中的起诉
//	if(case1.process_result_of_prosecution=="起诉"){
//		$("#supplement_check").show();
//		$("#checkOne").show();
//	}
	//补查一次
	if(case1.process_search_result_one=="是"){
		$("#supplement_check").show();
		$("#checkTwo").show();
	}
	if(case1.process_search_result_one=="是"||case1.process_search_result_one=="否"){
		$("#supplement_check").show();
		$("#checkOne").show();
	}
}
//隐藏2
/*
 * 第一次强制措施方法
 */
function mandatory_measuresBtnClick() {
	var mandatory_measuresOne = document
			.getElementsByName("ajdbxtProcess.process_force_measure_one");
	for (var num = 0; num < 3; num++) {
		var mandatory_measuresOne_value = mandatory_measuresOne[num].value;
		if (mandatory_measuresOne[num].checked) {
			if (mandatory_measuresOne_value == "拘留") {// 根据第一次强制措施选择拘留进行判断
				document.getElementById("detention_delay_date").style.display = "block";// 选择拘留时，显示出拘留延长期限
				/*
				 * 选择拘留显示第二次强制措施内容
				 */
				document.getElementById("second_punishment").style.display = "block";
				document.getElementById("qubao_second_punishment").style.display = "none";
				document.getElementById("supplement_check").style.display = "none";// 隐藏补查
				
				$("#third_punishment").css("display", "none");// 第三次强制措施内容隐藏

				$("#fourth_punishment").css("display", "none");// 第四次强制措施内容隐藏

			} else if (mandatory_measuresOne_value == "取保候审"||mandatory_measuresOne_value == "监视居住") {// 选择取保候审显示第二次强制措施内容
				document.getElementById("detention_delay_date").style.display = "none";
				document.getElementById("second_punishment").style.display = "none";
				document.getElementById("qubao_second_punishment").style.display = "block";
			
				document.getElementById("supplement_check").style.display = "none";// 隐藏补查

				$("#third_punishment").css("display", "none");// 第三次强制措施内容隐藏

				$("#fourth_punishment").css("display", "none");// 第四次强制措施内容隐藏

			} else  {// 选择监视居住显示第二次强制措施内容
				//document.getElementById("detention_delay_date").style.display = "none";
				//document.getElementById("second_punishment").style.display = "none";
			//	document.getElementById("qubao_second_punishment").style.display = "none";
				
				document.getElementById("supplement_check").style.display = "none";// 隐藏补查

				$("#third_punishment").css("display", "none");// 第三次强制措施隐藏

				$("#fourth_punishment").css("display", "none");// 第四次强制措施隐藏
			}

		}
	}
}
/*
 * 第二次强制措施方法
 */
function second_punishmentClick() {

	var second_punishment = document.getElementsByName("ajdbxtProcess.process_force_measure_two");
	for (var num = 0; num < 5; num++) {
		var second_punishment_value = second_punishment[num].value;
		if (second_punishment[num].checked) {
			if (second_punishment_value == "逮捕") {// 选择第二次的逮捕显示第三次强制措施内容
				$("#qubao_third_punishment").css("display", "none");
				$("#third_punishment").css("display", "block");
				document.getElementById("supplement_check").style.display = "none";// 隐藏补查

			} else if (second_punishment_value == "取保候审"||second_punishment_value == "监视居住") {// 选择第二次的取保候审显示第三次强制措施内容
				
				$("#qubao_third_punishment").css("display", "block");
				$("#third_punishment").css("display", "none");
				$("#fourth_punishment").css("display", "none");// 第四次强制措施隐藏
				document.getElementById("supplement_check").style.display = "none";// 隐藏补查

			} 	else if(second_punishment_value == "起诉"){
				document.getElementById("supplement_check").style.display = "block";
			
				document.getElementById("checkOne").style.display = "block";
				document.getElementById("checkTwo").style.display = "none";
			}
			else {// 选择第二次的监视居住显示第三次强制措施内容
			
				//$("#third_punishment").css("display", "block");
			
				
				document.getElementById("checkOne").style.display = "none";
				document.getElementById("checkTwo").style.display = "none";
				$("#fourth_punishment").css("display", "none");// 第四次强制措施隐藏
				document.getElementById("supplement_check").style.display = "none";// 隐藏补查

			}
		}

	}
}


/*
 * 第三次强制措施方法
 */
function third_punishmentClick() {

	var third_punishment = document.getElementsByName("ajdbxtProcess.process_force_measure_three");
	for (var num = 0; num < 4; num++) {
		var third_punishment_value = third_punishment[num].value;
		if (third_punishment[num].checked) {
			if (third_punishment_value == "起诉") {// 选择第三次的起诉显示补查一次
				document.getElementById("supplement_check").style.display = "block";
				// 第四次强制措施隐藏
				$("#fourth_punishment").css("display", "none");
				document.getElementById("checkOne").style.display = "block";
				document.getElementById("checkTwo").style.display = "none";
			
			} else if(third_punishment_value == "撤案"){
				$("#fourth_punishment").css("display", "none");
				document.getElementById("checkOne").style.display = "none";
				document.getElementById("checkTwo").style.display = "none";
			}
			else {// 选择第二次的监视居住显示第三次强制措施内容
				document.getElementById("checkOne").style.display = "none";
				document.getElementById("checkTwo").style.display = "none";
				$("#fourth_punishment").css("display", "block");
				document.getElementById("supplement_check").style.display = "none";// 隐藏补查
			}
		}

	}

}
/* 
 * 第四次强制措施
 */
function  four_punishmentClick(){

	var four_punishment = document.getElementsByName("ajdbxtProcess.process_result_of_prosecution");
	for (var num = 0; num < 2; num++) {
		var four_punishment_value = four_punishment[num].value;
		if (four_punishment[num].checked) {
			if (four_punishment_value == "起诉") {// 选择第三次的起诉显示补查一次
				document.getElementById("supplement_check").style.display = "block";
				// 第四次强制措施隐藏
			
				document.getElementById("checkOne").style.display = "block";
				document.getElementById("checkTwo").style.display = "none";
			
			} else {// 选择第二次的监视居住显示第三次强制措施内容
				document.getElementById("checkOne").style.display = "none";
				document.getElementById("checkTwo").style.display = "none";
		
				document.getElementById("supplement_check").style.display = "none";// 隐藏补查
			}
		}

	}
}
/*
 * 点击补查一次为是时，显示补查二次。否则不显示
 */
function checkOne_Click() {
	var checkOne = document.getElementsByName("ajdbxtProcess.process_search_result_one");
	for (var num = 0; num < 2; num++) {
		var checkOne_value = checkOne[num].value;
		if (checkOne[num].checked) {
			if (checkOne_value == "是") {
				document.getElementById("checkTwo").style.display = "block";
			} else {
				document.getElementById("checkTwo").style.display = "none";
			}
		}
	}
}
//-----------------------------------------按钮操作-------------------------------
//涉案财物已入库
function pencalchange_goods_in_lib_yes(even) {
	var sex = document.getElementById("goods_in_lib_yes");
	sex.value = '是';
	return sex.value;
}
function pencalchange_goods_in_lib_no(even) {
	var sex = document.getElementById("goods_in_lib_no");
	sex.value ='否';
	return sex.value;
}
function goods_in_lib() {
	$.confirm({
		title : '提交!',
		content : '确定提交么!',
		buttons : {

			取消 : function() {
			},
			确定 : {
				action : function() {
					penalloadgoods_in_lib();
				}
			}
		}
	});
}
// 是否涉案财物入库提交按钮
function penalloadgoods_in_lib() {
	console.log("b2");
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXOBject("Microsoft.XMLHTTP");
	}
	var processDetails = document.getElementById("penalProcessDetails");
	var formData = new FormData(processDetails);
	xmlhttp.onreadystatechange = function() {
		console.log("c2");
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var result = xmlhttp.responseText;
			if (isContains(result,'success')) {
				get_processDetails(info_id);
				toastr.success('编辑成功！');
			} else {
				toastr.error('编辑失败！');
			}
		}
	};
	xmlhttp.open("post",
			"/ajdbxt/process/updateProcessAction?ajdbxtProcess.process_case_id="
					+ info_id, true);
	xmlhttp.send(formData);
}
//第二次强制措施
function changesecond_punishmentClick_one(even) {
	var sex = document.getElementById("second_punishment_one");
	sex.value = '逮捕';
	return sex.value;
}
function changesecond_punishmentClick_two(even) {
	var sex = document.getElementById("second_punishment_two");
	sex.value ='取保候审';
	return sex.value;
}
function changesecond_punishmentClick_three(even) {
	var sex = document.getElementById("second_punishment_three");
	sex.value ='监视居住';
	return sex.value;
}
function pencalchange_cheantwo_yes(even){
	var sex = document.getElementById("chenantwo_yes");
	sex.value ='撤案';
	return sex.value;
}
function pencalchange_cheantwo_no(even){
	var sex = document.getElementById("chenantwo_no");
	sex.value ='起诉';
	return sex.value;
}
function second_punishment() {
	$.confirm({
		title : '提交!',
		content : '确定提交么!',
		buttons : {

			取消 : function() {
			},
			确定 : {
				action : function() {
					penalloadsecond_punishment();
				}
			}
		}
	});
}
function chenantwo_second_punishment() {
	$.confirm({
		title : '提交!',
		content : '确定提交么!',
		buttons : {

			取消 : function() {
			},
			确定 : {
				action : function() {
					penalloadchenantwo_second_punishment();
				}
			}
		}
	});
}
// 是否第二次强制措施提交按钮
function penalloadsecond_punishment() {
	console.log("b2");
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXOBject("Microsoft.XMLHTTP");
	}
	var processDetails = document.getElementById("penalProcessDetails");
	var formData = new FormData(processDetails);
	xmlhttp.onreadystatechange = function() {
		console.log("c2");
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var result = xmlhttp.responseText;
			if (isContains(result,'success')) {
				get_processDetails(info_id);
				toastr.success('编辑成功！');
			} else {
				toastr.error('编辑失败！');
			}
		}
	};
	xmlhttp.open("post",
			"/ajdbxt/process/updateProcessAction?ajdbxtProcess.process_case_id="
					+ info_id, true);
	xmlhttp.send(formData);
}
//
function penalloadchenantwo_second_punishment() {
	console.log("b2");
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXOBject("Microsoft.XMLHTTP");
	}
	var processDetails = document.getElementById("penalProcessDetails");
	var formData = new FormData(processDetails);
	xmlhttp.onreadystatechange = function() {
		console.log("c2");
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var result = xmlhttp.responseText;
			if (isContains(result,'success')) {
				get_processDetails(info_id);
				toastr.success('编辑成功！');
			} else {
				toastr.error('编辑失败！');
			}
		}
	};
	xmlhttp.open("post",
			"/ajdbxt/process/updateProcessAction?ajdbxtProcess.process_case_id="
					+ info_id, true);
	xmlhttp.send(formData);
}
//延长拘留
function pencalchangecriminal_detention_one(even) {
	var sex = document.getElementById("process_lengthen_criminal_detention_one");
	sex.value = '30';
	return sex.value;
}
function pencalchangecriminal_detention_two(even) {
	var sex = document.getElementById("process_lengthen_criminal_detention_two");
	sex.value ='7';
	return sex.value;
}
function pencalchangecriminal_detention_three(even) {
	var sex = document.getElementById("process_lengthen_criminal_detention_three");
	sex.value ='0';
	return sex.value;
}
function process_lengthen_criminal_detention() {
	$.confirm({
		title : '提交!',
		content : '确定提交么!',
		buttons : {

			取消 : function() {
			},
			确定 : {
				action : function() {
					penalloadprocess_lengthen_criminal_detention();
				}
			}
		}
	});
}
// 是否延长拘留提交按钮
function penalloadprocess_lengthen_criminal_detention() {
	console.log("b2");
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXOBject("Microsoft.XMLHTTP");
	}
	var processDetails = document.getElementById("penalProcessDetails");
	var formData = new FormData(processDetails);
	xmlhttp.onreadystatechange = function() {
		console.log("c2");
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var result = xmlhttp.responseText;
			if (isContains(result,'success')) {
				get_processDetails(info_id);
				toastr.success('编辑成功！');
			} else {
				toastr.error('编辑失败！');
			}
		}
	};
	xmlhttp.open("post",
			"/ajdbxt/process/updateProcessAction?ajdbxtProcess.process_case_id="
					+ info_id, true);
	xmlhttp.send(formData);
}
//改变是否涉案财物
function penalchangecase_property_yes(even) {
	var sex = document.getElementById("penalcase_property_yes");
	sex.value = '是';
	return sex.value;
}
function penalchangecase_property_no(even) {
	var sex = document.getElementById("penalcase_property_no");
	sex.value ='否';
	return sex.value;
}
function penalcase_property() {
	$.confirm({
		title : '提交!',
		content : '确定提交么!',
		buttons : {

			取消 : function() {
			},
			确定 : {
				action : function() {
					penalloadcaseDetail_case_property();
				}
			}
		}
	});
}
// 是否涉案财物提交按钮
function penalloadcaseDetail_case_property() {
	console.log("b2");
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXOBject("Microsoft.XMLHTTP");
	}
	var processDetails = document.getElementById("penalProcessDetails");
	var formData = new FormData(processDetails);
	xmlhttp.onreadystatechange = function() {
		console.log("c2");
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var result = xmlhttp.responseText;
			if (isContains(result,'success')) {
				get_processDetails(info_id);
				toastr.success('编辑成功！');
			} else {
				toastr.error('编辑失败！');
			}
		}
	};
	xmlhttp.open("post",
			"/ajdbxt/process/updateProcessAction?ajdbxtProcess.process_case_id="
					+ info_id, true);
	xmlhttp.send(formData);
}
//第三次强制措施
function pencalchangequbaothree_yes(even) {
	var sex = document.getElementById("qubaothree_yes");
	sex.value = '取保候审';
	return sex.value;
}
function pencalchangequbaothree_no(even) {
	var sex = document.getElementById("qubaothree_no");
	sex.value ='起诉';
	return sex.value;
}
function pencalchangecheanthree_yes(even) {
	var sex = document.getElementById("cheanthree_yes");
	sex.value ='撤案';
	return sex.value;
}
function pencalchangecheanthree_no(even) {
	var sex = document.getElementById("cheanthree_no");
	sex.value ='起诉';
	return sex.value;
}
function third_punishment() {
	$.confirm({
		title : '提交!',
		content : '确定提交么!',
		buttons : {

			取消 : function() {
			},
			确定 : {
				action : function() {
					penalloadthird_punishment();
				}
			}
		}
	});
}
function qubao_third_punishment() {
	$.confirm({
		title : '提交!',
		content : '确定提交么!',
		buttons : {

			取消 : function() {
			},
			确定 : {
				action : function() {
					penalloadqubao_third_punishment();
				}
			}
		}
	});
}
// 是否第三次强制措施提交按钮
function penalloadthird_punishment() {
	console.log("b2");
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXOBject("Microsoft.XMLHTTP");
	}
	var processDetails = document.getElementById("penalProcessDetails");
	var formData = new FormData(processDetails);
	xmlhttp.onreadystatechange = function() {
		console.log("c2");
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var result = xmlhttp.responseText;
			if (isContains(result,'success')) {
				get_processDetails(info_id);
				toastr.success('编辑成功！');
			} else {
				toastr.error('编辑失败！');
			}
		}
	};
	xmlhttp.open("post",
			"/ajdbxt/process/updateProcessAction?ajdbxtProcess.process_case_id="
					+ info_id, true);
	xmlhttp.send(formData);
}
//
function penalloadqubao_third_punishment() {
	console.log("b2");
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXOBject("Microsoft.XMLHTTP");
	}
	var processDetails = document.getElementById("penalProcessDetails");
	var formData = new FormData(processDetails);
	xmlhttp.onreadystatechange = function() {
		console.log("c2");
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var result = xmlhttp.responseText;
			if (isContains(result,'success')) {
				get_processDetails(info_id);
				toastr.success('编辑成功！');
			} else {
				toastr.error('编辑失败！');
			}
		}
	};
	xmlhttp.open("post",
			"/ajdbxt/process/updateProcessAction?ajdbxtProcess.process_case_id="
					+ info_id, true);
	xmlhttp.send(formData);
}
//第四次强制措施
function pencalchangechenfour_yes(even) {
	var sex = document.getElementById("cheanfour_yes");
	sex.value = '撤案';
	return sex.value;
}
function pencalchangechenfour_no(even) {
	var sex = document.getElementById("chenanfour_no");
	sex.value = '起诉';
	return sex.value;
}
function fourth_punishment() {
	$.confirm({
		title : '提交!',
		content : '确定提交么!',
		buttons : {

			取消 : function() {
			},
			确定 : {
				action : function() {
					penalloadfourth_punishment();
				}
			}
		}
	});
}
// 是否第四次强制措施提交按钮
function penalloadfourth_punishment() {
	console.log("b2");
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXOBject("Microsoft.XMLHTTP");
	}
	var processDetails = document.getElementById("penalProcessDetails");
	var formData = new FormData(processDetails);
	xmlhttp.onreadystatechange = function() {
		console.log("c2");
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var result = xmlhttp.responseText;
			if (isContains(result,'success')) {
				get_processDetails(info_id);
				toastr.success('编辑成功！');
			} else {
				toastr.error('编辑失败！');
			}
		}
	};
	xmlhttp.open("post",
			"/ajdbxt/process/updateProcessAction?ajdbxtProcess.process_case_id="
					+ info_id, true);
	xmlhttp.send(formData);
}
//第一次补查
function pencalchangecheckone_yes(even) {
	var sex = document.getElementById("checkone_yes");
	sex.value = '是';
	return sex.value;
}
function pencalchangecheckone_no(even) {
	var sex = document.getElementById("checkedone_no");
	sex.value ='否';
	return sex.value;
}
function checkOne() {
	$.confirm({
		title : '提交!',
		content : '确定提交么!',
		buttons : {

			取消 : function() {
			},
			确定 : {
				action : function() {
					penalloadcheckOne();
				}
			}
		}
	});
}
// 是否第一次补查提交按钮
function penalloadcheckOne() {
	console.log("b2");
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXOBject("Microsoft.XMLHTTP");
	}
	var processDetails = document.getElementById("penalProcessDetails");
	var formData = new FormData(processDetails);
	xmlhttp.onreadystatechange = function() {
		console.log("c2");
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var result = xmlhttp.responseText;
			if (isContains(result,'success')) {
				get_processDetails(info_id);
				toastr.success('编辑成功！');
			} else {
				toastr.error('编辑失败！');
			}
		}
	};
	xmlhttp.open("post",
			"/ajdbxt/process/updateProcessAction?ajdbxtProcess.process_case_id="
					+ info_id, true);
	xmlhttp.send(formData);
}
//第二次补查
function pencalchangechecktwo_yes(even) {
	var sex = document.getElementById("checktwo_yes");
	sex.value = '是';
	return sex.value;
}
function pencalchangechecktwo_no(even) {
	var sex = document.getElementById("checkedtwo_no");
	sex.value ='否';
	return sex.value;
}
function checkTwo() {
	$.confirm({
		title : '提交!',
		content : '确定提交么!',
		buttons : {

			取消 : function() {
			},
			确定 : {
				action : function() {
					penalloadcheckTwo();
				}
			}
		}
	});
}
// 是否第二次补查提交按钮
function penalloadcheckTwo() {
	console.log("b2");
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXOBject("Microsoft.XMLHTTP");
	}
	var processDetails = document.getElementById("penalProcessDetails");
	var formData = new FormData(processDetails);
	xmlhttp.onreadystatechange = function() {
		console.log("c2");
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var result = xmlhttp.responseText;
			if (isContains(result,'success')) {
				get_processDetails(info_id);
				toastr.success('编辑成功！');
			} else {
				toastr.error('编辑失败！');
			}
		}
	};
	xmlhttp.open("post",
			"/ajdbxt/process/updateProcessAction?ajdbxtProcess.process_case_id="
					+ info_id, true);
	xmlhttp.send(formData);
}
//延长传唤
function penalchangesuspect_summon_yes(even) {
	var sex = document.getElementById("penalsuspect_summon_yes");
	sex.value = '是';
	return sex.value;
}
function penalchangesuspect_summon_no(even) {
	var sex = document.getElementById("penalsuspect_summon_no");
	sex.value ='否';
	return sex.value;
}
function penalsuspect_summon() {
	$.confirm({
		title : '提交!',
		content : '确定提交么!',
		buttons : {

			取消 : function() {
			},
			确定 : {
				action : function() {
					penalloadpenalsuspect_summon();
				}
			}
		}
	});
}
// 是否延长传唤提交按钮
function penalloadpenalsuspect_summon() {
	console.log("b2");
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXOBject("Microsoft.XMLHTTP");
	}
	var processDetails = document.getElementById("penalProcessDetails");
	var formData = new FormData(processDetails);
	xmlhttp.onreadystatechange = function() {
		console.log("c2");
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var result = xmlhttp.responseText;
			if (isContains(result,'success')) {
				get_processDetails(info_id);
				toastr.success('编辑成功！');
			} else {
				toastr.error('编辑失败！');
			}
		}
	};
	xmlhttp.open("post",
			"/ajdbxt/process/updateProcessAction?ajdbxtProcess.process_case_id="
					+ info_id, true);
	xmlhttp.send(formData);
}
//未成年人
function penalchangeminors_asking_yes(even) {
	var sex = document.getElementById("penalminors_asking_yes");
	sex.value = '是';
	return sex.value;
}
function penalchangeminors_asking_no(even) {
	var sex = document.getElementById("penalminors_asking_no");
	sex.value ='否';
	return sex.value;
}
function penalminors_asking() {
	$.confirm({
		title : '提交!',
		content : '确定提交么!',
		buttons : {

			取消 : function() {
			},
			确定 : {
				action : function() {
					penalloadepnalminors_asking();
				}
			}
		}
	});
}
// 是否未成年人提交按钮
function penalloadepnalminors_asking() {
	console.log("b2");
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXOBject("Microsoft.XMLHTTP");
	}
	var processDetails = document.getElementById("penalProcessDetails");
	var formData = new FormData(processDetails);
	xmlhttp.onreadystatechange = function() {
		console.log("c2");
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var result = xmlhttp.responseText;
			if (isContains(result,'success')) {
				get_processDetails(info_id);
				toastr.success('编辑成功！');
			} else {
				toastr.error('编辑失败！');
			}
		}
	};
	xmlhttp.open("post",
			"/ajdbxt/process/updateProcessAction?ajdbxtProcess.process_case_id="
					+ info_id, true);
	xmlhttp.send(formData);
}
//鉴定
function penalchangeidentification_yes(even) {
	var sex = document.getElementById("penalidentification_yes");
	sex.value = '是';
	return sex.value;
}
function penalchangeidentification_no(even) {
	var sex = document.getElementById("penalidentification_no");
	sex.value ='否';
	return sex.value;
}
function penalidentification() {
	$.confirm({
		title : '提交!',
		content : '确定提交么!',
		buttons : {

			取消 : function() {
			},
			确定 : {
				action : function() {
					penalloadpenalidentification();
				}
			}
		}
	});
}
// 是否鉴定提交按钮
function penalloadpenalidentification() {
	console.log("b2");
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXOBject("Microsoft.XMLHTTP");
	}
	var processDetails = document.getElementById("penalProcessDetails");
	var formData = new FormData(processDetails);
	xmlhttp.onreadystatechange = function() {
		console.log("c2");
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var result = xmlhttp.responseText;
			if (isContains(result,'success')) {
				get_processDetails(info_id);
				toastr.success('编辑成功！');
			} else {
				toastr.error('编辑失败！');
			}
		}
	};
	xmlhttp.open("post",
			"/ajdbxt/process/updateProcessAction?ajdbxtProcess.process_case_id="
					+ info_id, true);
	xmlhttp.send(formData);
}
//听证
function penalchangehearing_applying_yes(even) {
	var sex = document.getElementById("penalhearing_applying_yes");
	sex.value = '是';
	return sex.value;
}
function penalchangehearing_applying_no(even) {
	var sex = document.getElementById("penalhearing_applying_no");
	sex.value ='否';
	return sex.value;
}
function penalhearing_applying() {
	$.confirm({
		title : '提交!',
		content : '确定提交么!',
		buttons : {

			取消 : function() {
			},
			确定 : {
				action : function() {
					penalloadpenalhearing_applying();
				}
			}
		}
	});
}
// 是否听证提交按钮
function penalloadpenalhearing_applying() {
	console.log("b2");
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXOBject("Microsoft.XMLHTTP");
	}
	var processDetails = document.getElementById("penalProcessDetails");
	var formData = new FormData(processDetails);
	xmlhttp.onreadystatechange = function() {
		console.log("c2");
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var result = xmlhttp.responseText;
			if (isContains(result,'success')) {
				get_processDetails(info_id);
				toastr.success('编辑成功！');
			} else {
				toastr.error('编辑失败！');
			}
		}
	};
	xmlhttp.open("post",
			"/ajdbxt/process/updateProcessAction?ajdbxtProcess.process_case_id="
					+ info_id, true);
	xmlhttp.send(formData);
}
//第一次强制措施
function mandatory_measuresBtnClick_one(even) {
	var sex = document.getElementById("measure_one_one");
	sex.value = '拘留';
	return sex.value;
}
function mandatory_measuresBtnClick_two(even) {
	var sex = document.getElementById("measure_one_two");
	sex.value ='监视居住';
	return sex.value;
}
function mandatory_measuresBtnClick_three(even) {
	var sex = document.getElementById("measure_one_three");
	sex.value ='取保候审';
	return sex.value;
}
function penalmeasure_one() {
	$.confirm({
		title : '提交!',
		content : '确定提交么!',
		buttons : {

			取消 : function() {
			},
			确定 : {
				action : function() {
					penalloadpenalmeasure_one();
				}
			}
		}
	});
}
// 第一次强制措施提交按钮
function penalloadpenalmeasure_one() {
	console.log("b2");
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXOBject("Microsoft.XMLHTTP");
	}
	var processDetails = document.getElementById("penalProcessDetails");
	var formData = new FormData(processDetails);
	xmlhttp.onreadystatechange = function() {
		console.log("c2");
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var result = xmlhttp.responseText;
			if (isContains(result,'success')) {
				get_processDetails(info_id);
				toastr.success('编辑成功！');
			} else {
				toastr.error('编辑失败！');
			}
		}
	};
	xmlhttp.open("post",
			"/ajdbxt/process/updateProcessAction?ajdbxtProcess.process_case_id="
					+ info_id, true);
	xmlhttp.send(formData);
}
//提出问题
function pencalproblem_asking() {
	$.confirm({
		title : '提交!',
		content : '确定提交么!',
		buttons : {

			取消 : function() {
			},
			确定 : {
				action : function() {
					penalloadpencalproblem_asking();
				}
			}
		}
	});
}
// 是否提出问题提交按钮
function penalloadpencalproblem_asking() {
	console.log("b2");
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXOBject("Microsoft.XMLHTTP");
	}
	var question_list = document.getElementById("penalprocess_question_list").value;
	var formData = new FormData(processDetails);
	formData.append("ajdbxtProcess.process_question_list", question_list);
	xmlhttp.onreadystatechange = function() {
		console.log("c2");
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var result = xmlhttp.responseText;
			if (isContains(result,'success')) {
				get_processDetails(info_id);
				toastr.success('编辑成功！');
			} else {
				toastr.error('编辑失败！');
			}
		}
	};
	xmlhttp.open("post",
			"/ajdbxt/process/updateProcessAction?ajdbxtProcess.process_case_id="
					+ info_id, true);
	xmlhttp.send(formData);
}
//问题整改
function pencalproblem_rectification() {
	$.confirm({
		title : '提交!',
		content : '确定提交么!',
		buttons : {

			取消 : function() {
			},
			确定 : {
				action : function() {
					penalloadpencalproblem_rectification();
				}
			}
		}
	});
}
// 是否问题整改提交按钮
function penalloadpencalproblem_rectification() {
	console.log("b2");
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXOBject("Microsoft.XMLHTTP");
	}
	var penalprocess_question = document.getElementById("penalprocess_question").value;
	var formData = new FormData(processDetails);
	formData.append("ajdbxtProcess.process_question", penalprocess_question);
	xmlhttp.onreadystatechange = function() {
		console.log("c2");
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var result = xmlhttp.responseText;
			if (isContains(result,'success')) {
				get_processDetails(info_id);
				toastr.success('编辑成功！');
			} else {
				toastr.error('编辑失败！');
			}
		}
	};
	xmlhttp.open("post",
			"/ajdbxt/process/updateProcessAction?ajdbxtProcess.process_case_id="
					+ info_id, true);
	xmlhttp.send(formData);
}
//结案
function pencalchangecase_ending_yes(even) {
	var sex = document.getElementById("pencalcase_ending_yes");
	sex.value = '是';
	return sex.value;
}
function pencalchangecase_ending_no(even) {
	var sex = document.getElementById("pencalcase_ending_no");
	sex.value ='否';
	return sex.value;
}
function pencalcase_ending() {
	$.confirm({
		title : '提交!',
		content : '确定提交么!',
		buttons : {

			取消 : function() {
			},
			确定 : {
				action : function() {
					penalloadpencalcase_ending();
				}
			}
		}
	});
}
// 是否结案提交按钮
function penalloadpencalcase_ending() {
	console.log("b2");
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXOBject("Microsoft.XMLHTTP");
	}
	var processDetails = document.getElementById("penalProcessDetails");
	var formData = new FormData(processDetails);
	xmlhttp.onreadystatechange = function() {
		console.log("c2");
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var result = xmlhttp.responseText;
			if (isContains(result,'success')) {
				get_processDetails(info_id);
				toastr.success('编辑成功！');
			} else {
				toastr.error('编辑失败！');
			}
		}
	};
	xmlhttp.open("post",
			"/ajdbxt/process/updateProcessAction?ajdbxtProcess.process_case_id="
					+ info_id, true);
	xmlhttp.send(formData);
}
//评分
function pencalcase_score() {
	$.confirm({
		title : '提交!',
		content : '确定提交么!',
		buttons : {

			取消 : function() {
			},
			确定 : {
				action : function() {
					penalloadpencalcase_score();
				}
			}
		}
	});
}
// 是否评分提交按钮
function penalloadpencalcase_score() {
	console.log("b2");
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXOBject("Microsoft.XMLHTTP");
	}
	var pencalinput_case_score = document.getElementById("pencalinput_case_score").value;
	var formData = new FormData(processDetails);
	formData.append("ajdbxtProcess.process_score", pencalinput_case_score);
	xmlhttp.onreadystatechange = function() {
		console.log("c2");
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var result = xmlhttp.responseText;
			if (isContains(result,'success')) {
				get_processDetails(info_id);
				toastr.success('编辑成功！');
			} else {
				toastr.error('编辑失败！');
			}
		}
	};
	xmlhttp.open("post",
			"/ajdbxt/process/updateProcessAction?ajdbxtProcess.process_case_id="
					+ info_id, true);
	xmlhttp.send(formData);
}
//案件上交
function pencalcasehand() {
	$.confirm({
		title : '提交!',
		content : '确定提交么!',
		buttons : {

			取消 : function() {
			},
			确定 : {
				action : function() {
					penalloadpencalcasehand();
				}
			}
		}
	});
}
// 是否案件上交提交按钮
function penalloadpencalcasehand() {
	console.log("b2");
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXOBject("Microsoft.XMLHTTP");
	}
	var processDetails = document.getElementById("penalProcessDetails");
	var formData = new FormData(processDetails);
	xmlhttp.onreadystatechange = function() {
		console.log("c2");
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var result = xmlhttp.responseText;
			if (isContains(result,'success')) {
				get_processDetails(info_id);
				toastr.success('编辑成功！');
			} else {
				toastr.error('编辑失败！');
			}
		}
	};
	xmlhttp.open("post",
			"/ajdbxt/process/updateProcessAction?ajdbxtProcess.process_case_id="
					+ info_id, true);
	xmlhttp.send(formData);
}
//行政案件流程
function get_processDetails_Ajax(url, info_id) {
	var xmlhttp;
	if (window.XMLHttpRequest) {
		// IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
		xmlhttp = new XMLHttpRequest();
	} else {
		// IE6, IE5 浏览器执行代码
		xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	}
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var staff_info = xmlhttp.responseText;
			staff_info = JSON.parse(staff_info);
			//单选框
			var case1 = staff_info.process;
			console.log("aaaaa"+case1.process_lengthen_subpoena);
			//最开始隐藏“打回修改完成”
			if(case1.process_is_rollback == "是") {
				$('#xiugaiok').show();
			} else {
				$('#xiugaiok').hide();
			}
			
			//流程的值不为空，则对应值默认选中，另一个值不可操作————为了不可修改
			$.each(case1, function(key, value) {
				$('input[name="ajdbxtProcess.' + key + '"]').val(value);
			});
			// 复选框
			$.each(case1, function(k, v) {
				var obj = $('input[name="process.' + k + '"]');
				if (obj.attr("type") == "checkbox") {
					if (v == '是') {
						obj.attr("checked", "checked");
					}
				} else
					obj.val(v);
			});
		//单选框     //嫌疑人
			if(case1.process_lengthen_subpoena!=null && case1.process_lengthen_subpoena.length>0){
				if("是"==case1.process_lengthen_subpoena){
					$('#suspect_summon_yes').attr("checked","checked");
					if (case1.process_is_rollback != '是') {
						$("#suspect_summon_no").prop("disabled", "true");
						$("#suspect_summon_yes").prop("disabled", "true");
					} else {
						$("#suspect_summon_no").prop("disabled",false);
						$("#suspect_summon_yes").prop("disabled",false);
					}
				}
				if(case1.process_lengthen_subpoena != '是'){
					$('#suspect_summon_no').attr("checked","checked");
					if (case1.process_is_rollback != '是') {
						$("#suspect_summon_no").prop("disabled", "true");
						$("#suspect_summon_yes").prop("disabled", "true");
					} else {
						$("#suspect_summon_no").prop("disabled",false);
						$("#suspect_summon_yes").prop("disabled",false);
					}
				}
			}    
			//未成年
			if(case1.process_nonage!=null && case1.process_nonage.length>0){
				if("是"==case1.process_nonage){
					$('#minors_asking_yes').attr("checked","checked");
					$("#minors_asking_no").prop("disabled", true);
				}
				if("否"==case1.process_nonage){
					$('#minors_asking_no').attr("checked","checked");
					$("#minors_asking_yes").prop("disabled", true);
				}
			}  
			//鉴定
			if(case1.process_authenticate!=null && case1.process_authenticate.length>0){
				if("是"==case1.process_authenticate){
					$('#identification_yes').attr("checked","checked");
					$("#identification_no").prop("disabled", true);
				}
				if("否"==case1.process_authenticate){
					$('#identification_no').attr("checked","checked");
					$("#identification_yes").prop("disabled", true);
				}
			}
			//有无涉案财物
			if(case1.process_case_goods!=null && case1.process_case_goods.length>0){
				if("是"==case1.process_case_goods){
					$('#case_property_yes').attr("checked","checked");
					if (case1.process_is_rollback != '是') {
						$("#case_property_no").prop("disabled", "true");
						$("#case_property_yes").prop("disabled", "true");
					} else {
//						$("#case_property_no").removeProp("disabled","true");
						$("#case_property_no").prop("disabled",false);
						$("#case_property_yes").prop("disabled",false);
					}
				}
				if(case1.process_case_goods != '是'){
					$('#case_property_no').attr("checked","checked");
					if (case1.process_is_rollback != '是') {
						$("#case_property_no").prop("disabled", "true");
						$("#case_property_yes").prop("disabled", "true");
					} else {
						$("#case_property_no").prop("disabled",false);
						$("#case_property_yes").prop("disabled",false);
					}
				}
			} 
			//听证
			if(case1.process_apply_right!=null && case1.process_apply_right.length>0){
				if("是"==case1.process_apply_right){
					$('#hearing_applying_yes').attr("checked","checked");
					$("#hearing_applying_no").prop("disabled", true);
				}
				if("否"==case1.process_apply_right){
					$('#hearing_applying_no').attr("checked","checked");
					$("#hearing_applying_yes").prop("disabled", true);
				}
			}
			
			//戒毒
			if(case1.process_treatment_category!=null && case1.process_treatment_category.length>0){
				if($('#process_treatment_category_yes').val()==case1.process_treatment_category){
					$('#process_treatment_category_yes').attr("checked","checked");
					$("#process_treatment_category_no").prop("disabled", true);
				}else{
					$('#process_treatment_category_no').attr("checked","checked");
					$("#process_treatment_category_yes").prop("disabled", true);
				}
			}
			
			
			//提出问题
			/*if(case1.process_question_list!=null && case1.process_question_list.length>0){
				if($('#problem_asking_yes').val()==case1.process_question_list){
					$('#problem_asking_yes').attr("checked","checked");
					$("#problem_asking_no").prop("disabled", true);
				}else{
					$('#problem_asking_no').attr("checked","checked");
					$("#problem_asking_yes").prop("disabled", true);
				}
			}*/
			//审核
			if(case1.process_captain_check!=null && case1.process_captain_check.length>0){
				if($('#case_review_yes').val()==case1.process_captain_check){
					$('#case_review_yes').attr("checked","checked");
					$("#case_review_no").prop("disabled", true);
				}else{
					$('#case_review_no').attr("checked","checked");
					$("#case_review_yes").prop("disabled", true);
				}
			}
			//问题整改
			/*if(case1.process_question!=null && case1.process_question.length>0){
				if($('#problem_rectification_yes').val()==case1.process_question){
					$('#problem_rectification_yes').attr("checked","checked");
					$("#problem_rectification_no").prop("disabled", true);
				}else{
					$('#problem_rectification_no').attr("checked","checked");
					$("#problem_rectification_yes").prop("disabled", true);
				}
			}*/
			//结案
			if(case1.process_case_end!=null && case1.process_case_end.length>0){
				if($('#case_ending_yes').val()==case1.process_case_end){
					$('#case_ending_yes').attr("checked","checked");
					$("#case_ending_no").prop("disabled", true);
				}else{
					$('#case_ending_no').attr("checked","checked");
					$("#case_ending_yes").prop("disabled", true);
				}
			}
			// 单选框
			//$.each(case1, function(k, v) {
				// 嫌疑人延长（单选框）
	/*			if (k == "process_lengthen_subpoena") {
					if (v != null && v.length>0) {
						if ($('input[type="radio"]').eq(0).val() == v) {
							$('input[type="radio"]').eq(0).attr("checked",
									"checked");
							$("#suspect_summon_no").prop("disabled", true);
						} else {
							$('input[type="radio"]').eq(1).attr("checked",
									"checked");
							$("#suspect_summon_yes").prop("disabled", true);
						}
					} else {
						$('input[type="radio"]').eq(0).attr("checked",
								false);
						$('input[type="radio"]').eq(1).attr("checked",
								false);
					}
				} else */
				/*	if (k == "process_nonage") {
					if (v != null && v.length>0) {
					if ($('input[type="radio"]').eq(2).val() == v) {
						$('input[type="radio"]').eq(2).attr("checked",
								"checked");
					} else {
						$('input[type="radio"]').eq(3).attr("checked",
								"checked");
					}
				}else{
					$('input[type="radio"]').eq(2).attr("checked",
					false);
					$('input[type="radio"]').eq(3).attr("checked",
					false);
				}
				} else */
//					if (k == "process_authenticate") {
//					if (v != null && v.length>0) {
//					if ($('input[type="radio"]').eq(4).val() == v) {
//						$('input[type="radio"]').eq(4).attr("checked",
//								"checked");
//					} else {
//						$('input[type="radio"]').eq(5).attr("checked",
//								"checked");
//					}
//				}else{
//					$('input[type="radio"]').eq(4).attr("checked",
//					false);
//					$('input[type="radio"]').eq(5).attr("checked",
//					false);
//				}
//				}// 涉案财物
//				else if (k == "process_case_goods") {
//					if (v != null && v.length>0) {
//					if ($('input[type="radio"]').eq(6).val() == v
//							&& $('input[type="radio"]').eq(6).val() != "") {
//						$('input[type="radio"]').eq(6).attr("checked",
//								"checked");
//					} else {
//						$('input[type="radio"]').eq(7).attr("checked",
//								"checked");
//					}
//				}else{
//					$('input[type="radio"]').eq(6).attr("checked",
//					false);
//					$('input[type="radio"]').eq(7).attr("checked",
//					false);
//				}
//				} else if (k == "process_treatment_category") {
//					if (v != null && v.length>0) {
//					if ($('input[type="radio"]').eq(8).val() == v) {
//						$('input[type="radio"]').eq(8).attr("checked",
//								"checked");
//					} else {
//						$('input[type="radio"]').eq(9).attr("checked",
//								"checked");
//					}
//				}else{
//					$('input[type="radio"]').eq(8).attr("checked",
//					false);
//					$('input[type="radio"]').eq(9).attr("checked",
//					false);
//				}
//				}// 听证
//				else if (k == "process_apply_right") {
//					if (v != null && v.length>0) {
//					if ($('input[type="radio"]').eq(10).val() == v) {
//						$('input[type="radio"]').eq(10).attr("checked",
//								"checked");
//					} else {
//						$('input[type="radio"]').eq(11).attr("checked",
//								"checked");
//					}
//				}else{
//					$('input[type="radio"]').eq(10).attr("checked",
//					false);
//					$('input[type="radio"]').eq(11).attr("checked",
//					false);
//				}
//				}// 提出问题
//				else if (k == "process_question_list") {
//					if (v != null && v.length>0) {
//					if ($('input[type="radio"]').eq(12).val() == v) {
//						$('input[type="radio"]').eq(12).attr("checked",
//								"checked");
//						// $("#process_question").show();
//					} else {
//						$('input[type="radio"]').eq(13).attr("checked",
//								"checked");
//					}
//				}else{
//					$('input[type="radio"]').eq(12).attr("checked",
//					false);
//					$('input[type="radio"]').eq(13).attr("checked",
//					false);
//				}
//				}// 审核
//				else if (k == "process_captain_check") {
//					if (v != null && v.length>0) {
//					if ($('input[type="radio"]').eq(14).val() == v) {
//						$('input[type="radio"]').eq(14).attr("checked",
//								"checked");
//					} else {
//						$('input[type="radio"]').eq(15).attr("checked",
//								"checked");
//					}
//				}else{
//					$('input[type="radio"]').eq(14).attr("checked",
//					false);
//					$('input[type="radio"]').eq(15).attr("checked",
//					false);
//				}
//				}// 问题整改
//				else if (k == "process_question") {
//					if (v != null && v.length>0) {
//					if ($('input[type="radio"]').eq(16).val() == v) {
//						$('input[type="radio"]').eq(16).attr("checked",
//								"checked");
//					} else {
//						$('input[type="radio"]').eq(17).attr("checked",
//								"checked");
//					}
//				}else{
//					$('input[type="radio"]').eq(16).attr("checked",
//					false);
//					$('input[type="radio"]').eq(17).attr("checked",
//					false);
//				}
//				}// 结案
//				else if (k == "process_case_end") {
//					if (v != null && v.length>0) {
//					if ($('input[type="radio"]').eq(18).val() == v) {
//						$('input[type="radio"]').eq(18).attr("checked",
//								"checked");
//					} else {
//						$('input[type="radio"]').eq(19).attr("checked",
//								"checked");
//					}
//				}else{
//					$('input[type="radio"]').eq(18).attr("checked",
//					false);
//					$('input[type="radio"]').eq(19).attr("checked",
//					false);
//				}
//				}
//			});
			  management(case1);
		}
		
	}
	xmlhttp.open("post", url, true);
	xmlhttp.send();
}

//根据有无涉案财物判断是否显示”涉案财物已入库“
function management(case1){
	if(case1.process_case_goods=='是'){
		$('#sheancaiwu').show();
	}else{
		$('#sheancaiwu').hide();
	}
}


/**********************************点击提交按钮，改变对应的提交值********************************************* */
//改变延长传唤
function changesuspect_summon_yes(even) {
	var sex = document.getElementById("suspect_summon_yes");
	sex.value = '是'
	
	//return sex.value;
}
function changesuspect_summon_no(even) {
	var sex = document.getElementById("suspect_summon_no");
	sex.value = '否'
	//return sex.value;
}
// 办理延长传唤		提交
function suspect_summon(button) {
	$.confirm({
		title : '提交!',
		content : '确定提交么!',
		buttons : {

			取消 : function() {
			},
			确定 : {
				action : function() {
					loadcaseDetail_suspect_summon(button);
				}
			}
		}
	});
}
function loadcaseDetail_suspect_summon(button) {
//console.log("b2");
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXOBject("Microsoft.XMLHTTP");
	}
	var processDetails = document.getElementById("processDetails");
	var formData = new FormData(processDetails);
	xmlhttp.onreadystatechange = function() {
//console.log("c2");
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			
			var result = xmlhttp.responseText;
			if (isContains(result,'success')) {
//				location.reload(true);
				get_processDetails(info_id);
				toastr.success('编辑成功！');
			} else {
				toastr.error('编辑失败！');
			}
		}
	};
	xmlhttp.open("post",
			"/ajdbxt/process/updateProcessAction?ajdbxtProcess.process_case_id="
					+ info_id, true);
	xmlhttp.send(formData);
}


//改变是否未成年
function changeminors_asking_yes(even) {
	var sex = document.getElementById("minors_asking_yes");
	sex.value = '是';
//	return sex.value;
}
function changeminors_asking_no(even) {
	var sex = document.getElementById("minors_asking_no");
	sex.value = '否';
//	return sex.value;
}
// CaseDetails.jsp中的是否未成年提交
function minors_asking() {
	$.confirm({
		title : '提交!',
		content : '确定提交么!',
		buttons : {

			取消 : function() {
			},
			确定 : {
				action : function() {
					loadcaseDetail_minors_asking();
				}
			}
		}
	});
}
// 是否未成年提交按钮
function loadcaseDetail_minors_asking() {
	console.log("b2");
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXOBject("Microsoft.XMLHTTP");
	}
	var processDetails = document.getElementById("processDetails");
	var formData = new FormData(processDetails);
	xmlhttp.onreadystatechange = function() {
		console.log("c2");
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var result = xmlhttp.responseText;
			if (isContains(result,'success')) {
				get_processDetails(info_id);
				toastr.success('编辑成功！');
				//location.reload(true);    
			} else {
				toastr.error('编辑失败！');
			}
		}
	};
	xmlhttp.open("post",
			"/ajdbxt/process/updateProcessAction?ajdbxtProcess.process_case_id="
					+ info_id, true);
	xmlhttp.send(formData);
}


//改变是否鉴定
function changeidentification_yes(even) {
	var sex = document.getElementById("identification_yes");
	sex.value = '是';
	return sex.value;
}
function changeidentification_no(even) {
	var sex = document.getElementById("identification_no");
	sex.value = '否';
	return sex.value;
}
// CaseDetails.jsp中的是否鉴定提交
function identification() {
	$.confirm({
		title : '提交!',
		content : '确定提交么!',
		buttons : {

			取消 : function() {
			},
			确定 : {
				action : function() {
					loadcaseDetail_minors_asking();
				}
			}
		}
	});
}
// 是否鉴定提交按钮
function loadcaseDetail_minors_asking() {
	console.log("b2");
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXOBject("Microsoft.XMLHTTP");
	}
	var processDetails = document.getElementById("processDetails");
	var formData = new FormData(processDetails);
	xmlhttp.onreadystatechange = function() {
		console.log("c2");
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var result = xmlhttp.responseText;
			if (isContains(result,'success')) {
				get_processDetails(info_id);
				toastr.success('编辑成功！');
			} else {
				toastr.error('编辑失败！');
			}
		}
	};
	xmlhttp.open("post",
			"/ajdbxt/process/updateProcessAction?ajdbxtProcess.process_case_id="
					+ info_id, true);
	xmlhttp.send(formData);
}


//改变是否涉案财物
function changecase_property_yes(even) {
	var sex = document.getElementById("case_property_yes");
	sex.value = '是';
	return sex.value;
}
function changecase_property_no(even) {
	var sex = document.getElementById("case_property_no");
	sex.value ='否';
	return sex.value;
}
function case_property() {
	$.confirm({
		title : '提交!',
		content : '确定提交么!',
		buttons : {

			取消 : function() {
			},
			确定 : {
				action : function() {
					loadcaseDetail_case_property();
				}
			}
		}
	});
}
// 是否涉案财物提交按钮
function loadcaseDetail_case_property() {
	console.log("b2");
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXOBject("Microsoft.XMLHTTP");
	}
	var processDetails = document.getElementById("processDetails");
	var formData = new FormData(processDetails);
	xmlhttp.onreadystatechange = function() {
		console.log("c2");
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var result = xmlhttp.responseText;
			if (isContains(result,'success')) {
				get_processDetails(info_id);
				toastr.success('编辑成功！');
			} else {
				toastr.error('编辑失败！');
			}
		}
	};
	xmlhttp.open("post",
			"/ajdbxt/process/updateProcessAction?ajdbxtProcess.process_case_id="
					+ info_id, true);
	xmlhttp.send(formData);
}



//改变是否听证申请
function changehearing_applying_yes(even) {
	var sex = document.getElementById("hearing_applying_yes");
	sex.value = '是';
	return sex.value;
}
function changehearing_applying_no(even) {
	var sex = document.getElementById("hearing_applying_no");
	sex.value = '否';
	return sex.value;
}
function hearing_applying() {
	$.confirm({
		title : '提交!',
		content : '确定提交么!',
		buttons : {

			取消 : function() {
			},
			确定 : {
				action : function() {
					loadcaseDetail_hearing_applying();
				}
			}
		}
	});
}
// 是否听证申请提交按钮
function loadcaseDetail_hearing_applying() {
	console.log("b2");
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXOBject("Microsoft.XMLHTTP");
	}
	var processDetails = document.getElementById("processDetails");
	var formData = new FormData(processDetails);
	xmlhttp.onreadystatechange = function() {
		console.log("c2");
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var result = xmlhttp.responseText;
			if (isContains(result,'success')) {
				get_processDetails(info_id);
				toastr.success('编辑成功！');
			} else {
				toastr.error('编辑失败！');
			}
		}
	};
	xmlhttp.open("post",
			"/ajdbxt/process/updateProcessAction?ajdbxtProcess.process_case_id="
					+ info_id, true);
	xmlhttp.send(formData);
}


//是否打回提交按钮
function changechangerollback_yes(even) {
	var sex = document.getElementById("rollback_yes");
	sex.value = '是';
	return sex.value;
}
function changechangerollback_no(even) {
	var sex = document.getElementById("rollback_no");
	sex.value = '否';
	return sex.value;
}
function process_rollback() {
	$.confirm({
		title : '提交!',
		content : '确定提交么!',
		buttons : {

			取消 : function() {
			},
			确定 : {
				action : function() {
					loadcaseDetail_process_rollback();
				}
			}
		}
	});
}
function loadcaseDetail_process_rollback() {
	//console.log("b2");
		if (window.XMLHttpRequest) {
			xmlhttp = new XMLHttpRequest();
		} else {
			xmlhttp = new ActiveXOBject("Microsoft.XMLHTTP");
		}
		//var processDetails = document.getElementById("processDetails");
		var process_question_list=document.getElementById("process_question_list").value;
		var formData = new FormData(processDetails);
		formData.append("ajdbxtProcess.process_question_list", process_question_list);
		xmlhttp.onreadystatechange = function() {
			console.log("c2");
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				var result = xmlhttp.responseText;
				if (isContains(result,'success')) {
					toastr.success('编辑成功！');
					//location.reload(true);
				} else {
					toastr.error('编辑失败！');
				}
				get_processDetails(info_id);
			}
		};
		xmlhttp.open("post",
				"/ajdbxt/process/updateProcessAction?ajdbxtProcess.process_case_id="
						+ info_id, true);
		xmlhttp.send(formData);
	}


// CaseDetails.jsp中的是否提出问题提交
function problem_asking() {
	$.confirm({
		title : '提交!',
		content : '确定提交么!',
		buttons : {

			取消 : function() {
			},
			确定 : {
				action : function() {
					loadcaseDetail_problem_asking();
				}
			}
		}
	});
}
//是否提出问题按钮
function loadcaseDetail_problem_asking() {
//console.log("b2");
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXOBject("Microsoft.XMLHTTP");
	}
	//var processDetails = document.getElementById("processDetails");
	var process_question_list=document.getElementById("process_question_list").value;
	var formData = new FormData(processDetails);
	formData.append("ajdbxtProcess.process_question_list", process_question_list);
	xmlhttp.onreadystatechange = function() {
		console.log("c2");
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var result = xmlhttp.responseText;
			if (isContains(result,'success')) {
				get_processDetails(info_id);
				toastr.success('编辑成功！');
				//location.reload(true);
			} else {
				toastr.error('编辑失败！');
			}
		}
	};
	xmlhttp.open("post",
			"/ajdbxt/process/updateProcessAction?ajdbxtProcess.process_case_id="
					+ info_id, true);
	xmlhttp.send(formData);
}


///////////////////////////////////////////////////是否打回
function changerollback_yes(even) {
	var sex = document.getElementById("rollback_yes");
	sex.value = '是';
	return sex.value;
}
function changerollback_no(even) {
	var sex = document.getElementById("rollback_no");
	sex.value = '否';
	return sex.value;
}
function rollback_modified() {
	$.confirm({
		title : '提交!',
		content : '确定提交么!',
		buttons : {

			取消 : function() {
			},
			确定 : {
				action : function() {
					loadcaseDetail_rollback_modified();
				}
			}
		}
	});
}
function loadcaseDetail_rollback_modified() {
//	console.log("b2");
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXOBject("Microsoft.XMLHTTP");
	}
	var processDetails = document.getElementById("processDetails");
	var formData = new FormData(processDetails);
	xmlhttp.onreadystatechange = function() {
//console.log("c2");
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var result = xmlhttp.responseText;
			if (isContains(result,'success')) {
				get_processDetails(info_id);
				toastr.success('编辑成功！');
			} else {
				toastr.error('编辑失败！');
			}
		}
	};
	xmlhttp.open("post",
			"/ajdbxt/process/updateProcessAction?ajdbxtProcess.process_case_id="
					+ info_id, true);
	xmlhttp.send(formData);
}


// CaseDetails.jsp中的是否提出问题提交
function problem_asking() {
	$.confirm({
		title : '提交!',
		content : '确定提交么!',
		buttons : {

			取消 : function() {
			},
			确定 : {
				action : function() {
					loadcaseDetail_rollback_modified();
				}
			}
		}
	});
}
////////////




//改变是否审核
function changecase_review_yes(even) {
	var sex = document.getElementById("case_review_yes");
	sex.value = '是';
	return sex.value;
}
function changecase_review_no(even) {
	var sex = document.getElementById("case_review_no");
	sex.value = '否';
	return sex.value;
}
// CaseDetails.jsp中的是否审核提交
function case_review() {
	$.confirm({
		title : '提交!',
		content : '确定提交么!',
		buttons : {

			取消 : function() {
			},
			确定 : {
				action : function() {
					loadcaseDetail_case_review();
				}
			}
		}
	});
}
// 是否审核按钮
function loadcaseDetail_case_review() {
	console.log("b2");
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXOBject("Microsoft.XMLHTTP");
	}
	var processDetails = document.getElementById("processDetails");
	var formData = new FormData(processDetails);
	xmlhttp.onreadystatechange = function() {
		console.log("c2");
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var result = xmlhttp.responseText;
			if (isContains(result,'success')) {
				get_processDetails(info_id);
				toastr.success('编辑成功！');
			} else {
				toastr.error('编辑失败！');
			}
		}
	};
	xmlhttp.open("post",
			"/ajdbxt/process/updateProcessAction?ajdbxtProcess.process_case_id="
					+ info_id, true);
	xmlhttp.send(formData);
}


//问题整改数量提交
function problem_rectification() {
	$.confirm({
		title : '提交!',
		content : '确定提交么!',
		buttons : {

			取消 : function() {
			},
			确定 : {
				action : function() {
					loadcaseDetail_problem_rectification();
				}
			}
		}
	});
}
// 是否问题整改按钮
function loadcaseDetail_problem_rectification() {
	console.log("b2");
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXOBject("Microsoft.XMLHTTP");
	}
	//var processDetails = document.getElementById("processDetails");
	var process_question = document.getElementById("process_question").value;
	var formData = new FormData(processDetails);
	formData.append("ajdbxtProcess.process_question", process_question);
	xmlhttp.onreadystatechange = function() {
		console.log("c2");
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var result = xmlhttp.responseText;
			if (isContains(result,'success')) {
				get_processDetails(info_id);
				toastr.success('编辑成功！');
				//location.reload(true);
			} else {
				toastr.error('编辑失败！');
			}
		}
	};
	xmlhttp.open("post",
			"/ajdbxt/process/updateProcessAction?ajdbxtProcess.process_case_id="
					+ info_id, true);
	xmlhttp.send(formData);
}


//改变是否结案
function changecase_ending_yes(even) {
	var sex = document.getElementById("case_ending_yes");
	sex.value = '是';
	return sex.value;
}
function changecase_ending_no(even) {
	var sex = document.getElementById("case_ending_no");
	sex.value = '否';
	return sex.value;
}
// CaseDetails.jsp中的是否结案提交
function case_ending() {
	$.confirm({
		title : '提交!',
		content : '确定提交么!',
		buttons : {

			取消 : function() {
			},
			确定 : {
				action : function() {
					loadcaseDetail_case_ending();
				}
			}
		}
	});
}
// 是否结案按钮
function loadcaseDetail_case_ending() {
	console.log("b2");
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXOBject("Microsoft.XMLHTTP");
	}
	var processDetails = document.getElementById("processDetails");
	var formData = new FormData(processDetails);
	xmlhttp.onreadystatechange = function() {
		console.log("c2");
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var result = xmlhttp.responseText;
			if (isContains(result,'success')) {
				get_processDetails(info_id);
				toastr.success('编辑成功！');
			} else {
				toastr.error('编辑失败！');
			}
		}
	};
	xmlhttp.open("post",
			"/ajdbxt/process/updateProcessAction?ajdbxtProcess.process_case_id="
					+ info_id, true);
	xmlhttp.send(formData);
}


//改变是否戒毒
/*function changeprocess_treatment_category_yes(even) {
	var sex = document.getElementById("process_treatment_category_yes");
	sex.value = even.value;
	return sex.value;
}
function changeprocess_treatment_category_no(even) {
	var sex = document.getElementById("process_treatment_category_no");
	sex.value = even.value;
	return sex.value;
}*/



// CaseDetails.jsp中的处罚提交
function punishmentab() {
	$.confirm({
		title : '提交!',
		content : '确定提交么!',
		buttons : {

			取消 : function() {
			},
			确定 : {
				action : function() {
					loadcaseDetail_punishmentab();
				}
			}
		}
	});
}
// 处罚按钮
function loadcaseDetail_punishmentab() {
	console.log("b2");
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXOBject("Microsoft.XMLHTTP");
	}
/*	var process_detention = document.getElementById("process_detention");
	var process_penalty= document.getElementById("process_penalty");
	console.log(process_detention.getAttribute('c'))
*/	
	var process_detention = '否'
	if($('#process_detention').is(':checked')){
		process_detention = '是'
	}
	var process_penalty = '否'
	if($('#process_penalty').is(':checked')){
		process_penalty = '是'
	}
	var process_mandatory_abandon_drug = '否'
		if($('#process_mandatory_abandon_drug').is(':checked')){
			process_mandatory_abandon_drug = '是'
		}
	var process_community_abandon_drug = '否'
		if($('#process_community_abandon_drug').is(':checked')){
			process_community_abandon_drug = '是'
		}
	var formData = new FormData(processDetails);
	formData.append("ajdbxtProcess.process_detention", process_detention);
	formData.append("ajdbxtProcess.process_penalty", process_penalty);
	formData.append("ajdbxtProcess.process_mandatory_abandon_drug", process_mandatory_abandon_drug);
	formData.append("ajdbxtProcess.process_community_abandon_drug", process_community_abandon_drug);
	xmlhttp.onreadystatechange = function() {
		console.log("c2");
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var result = xmlhttp.responseText;
			if (isContains(result,'success')) {
				get_processDetails(info_id);
				toastr.success('编辑成功！');
			} else {
				toastr.error('编辑失败！');
			}
		}
	};
	xmlhttp.open("post",
			"/ajdbxt/process/updateProcessAction?ajdbxtProcess.process_case_id="
					+ info_id, true);
	xmlhttp.send(formData);
}
//评分
function case_score() {
	$.confirm({
		title : '提交!',
		content : '确定提交么!',
		buttons : {

			取消 : function() {
			},
			确定 : {
				action : function() {
					loadcaseDetail_case_score();
				}
			}
		}
	});
}
// 评分按钮
function loadcaseDetail_case_score() {
	console.log("b2");
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXOBject("Microsoft.XMLHTTP");
	}
	//var processDetails = document.getElementById("processDetails");
	var case_score= document.getElementById("input_case_score").value;
	var formData = new FormData(processDetails);
	formData.append("ajdbxtProcess.process_score", case_score);
	xmlhttp.onreadystatechange = function() {
		console.log("c2");
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var result = xmlhttp.responseText;
			if (isContains(result,'success')) {
				get_processDetails(info_id);
				toastr.success('编辑成功！');
			} else {
				toastr.error('编辑失败！');
			}
		}
	};
	xmlhttp.open("post",
			"/ajdbxt/process/updateProcessAction?ajdbxtProcess.process_case_id="
					+ info_id, true);
	xmlhttp.send(formData);
}

//确认案卷上交按钮
function process_file_hand(){
	$.confirm({
		title : '上交!',
		content : '确定上交么!',
		buttons : {

			取消 : function() {
			},
			确定 : {
				action : function() {
					loadcaseDetail_process_file_hand();
				}
			}
		}
	});
}
function loadcaseDetail_process_file_hand(){
//console.log("b2");
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXOBject("Microsoft.XMLHTTP");
	}
	var processDetails = document.getElementById("processDetails");
	var formData = new FormData(processDetails);
	xmlhttp.onreadystatechange = function() {
//console.log("c2");
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var result = xmlhttp.responseText;
			if (isContains(result,'success')) {
				get_processDetails(info_id);
				toastr.success('编辑成功！');
			} else {
				toastr.error('编辑失败！');
			}
		}
	};
	xmlhttp.open("post",
			"/ajdbxt/process/updateProcessAction?ajdbxtProcess.process_case_id="
					+ info_id, true);
	xmlhttp.send(formData);
}


//确认打回修改完成按钮
function process_is_rollback_ok(){
	$.confirm({
		title : '上交!',
		content : '确定上交么!',
		buttons : {

			取消 : function() {
			},
			确定 : {
				action : function() {
					loadcaseDetail_process_is_rollback_ok();
				}
			}
		}
	});
}
function loadcaseDetail_process_is_rollback_ok(){
//console.log("b2");
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXOBject("Microsoft.XMLHTTP");
	}
	var processDetails = document.getElementById("processDetails");
	var formData = new FormData(processDetails);
	xmlhttp.onreadystatechange = function() {
//console.log("c2");
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var result = xmlhttp.responseText;
			if (isContains(result,'success')) {
				get_processDetails(info_id);
				toastr.success('编辑成功！');
			} else {
				toastr.error('编辑失败！');
			}
		}
	};
	xmlhttp.open("post",
			"/ajdbxt/process/updateProcessAction?ajdbxtProcess.process_case_id="
					+ info_id, true);
	xmlhttp.send(formData);
}
//staffDetail.jsp中的修改人员
//function staff_relive() {
//	$.confirm({
//		title : '修改!',
//		content : '确定修改么!',
//		buttons : {
//
//			取消 : function() {
//			},
//			确定 : {
//				action : function() {
//					loadstaffDetail_staff_relive();
//				}
//			}
//		}
//	});
//}
// 修改按钮
//function loadstaffDetail_staff_relive() {
//	console.log("b2");
//	if (window.XMLHttpRequest) {
//		xmlhttp = new XMLHttpRequest();
//	} else {
//		xmlhttp = new ActiveXOBject("Microsoft.XMLHTTP");
//	}
//	var staffDetails = document.getElementById("staffDetails");
//	var formData = new FormData(staffDetails);
//	xmlhttp.onreadystatechange = function() {
//		console.log("c2");
//		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
//			var result = xmlhttp.responseText;
//			if (result == 'updateSuccess') {
//				toastr.success('编辑成功！');
//			} else {
//				toastr.error('编辑失败！');
//			}
//		}
//	};
//	xmlhttp.open("post",
//			"/xsjsglxt/team/Staff_updatePoliceman?policeman.xsjsglxt_staff_id="
//					+ staff_id, true);
//	xmlhttp.send(formData);
//}
//刑事案件



