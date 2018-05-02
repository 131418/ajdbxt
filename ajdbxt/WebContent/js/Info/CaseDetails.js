window.onload = function() {
	var url = window.location.href;
	info_id = url.substring(url.indexOf("=") + 1);
	console.log(info_id);
	get_staffDetails(info_id);
	get_processDetails(info_id);
}
function isContains(str, substr) {
    return str.indexOf(substr) >= 0;
}
function get_staffDetails(info_id) {
	console.log("b1");
	var url = "/ajdbxt/info/Info_getSingleInfo?info.ajdbxt_info_id=" + info_id;
	get_staffDetails_Ajax(url, info_id);
}
function get_processDetails(info_id) {
	console.log("b1");
	var url = "/ajdbxt/process/findSingleProcessAction?ajdbxtProcess.process_case_id="
			+ info_id;
	get_processDetails_Ajax(url, info_id);
}
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
			console.log(staff_info);
			var case1 = staff_info.process;
			$.each(case1, function(key, value) {
				$('input[name="process.' + key + '"]').val(value);
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
				if($('#suspect_summon_yes').val()==case1.process_lengthen_subpoena){
					$('#suspect_summon_yes').attr("checked","checked");
					$("#suspect_summon_no").prop("disabled", true);
				}else{
					$('#suspect_summon_no').attr("checked","checked");
					$("#suspect_summon_yes").prop("disabled", true);
				}
			}    //未成年
			if(case1.process_nonage!=null && case1.process_nonage.length>0){
				if($('#minors_asking_yes').val()==case1.process_nonage){
					$('#minors_asking_yes').attr("checked","checked");
					$("#minors_asking_no").prop("disabled", true);
				}else{
					$('#minors_asking_no').attr("checked","checked");
					$("#minors_asking_yes").prop("disabled", true);
				}
			}  //鉴定
			if(case1.process_authenticate!=null && case1.process_authenticate.length>0){
				if($('#identification_yes').val()==case1.process_authenticate){
					$('#identification_yes').attr("checked","checked");
					$("#identification_no").prop("disabled", true);
				}else{
					$('#identification_no').attr("checked","checked");
					$("#identification_yes").prop("disabled", true);
				}
			}//涉案财物
			if(case1.process_case_goods!=null && case1.process_case_goods.length>0){
				if($('#case_property_yes').val()==case1.process_case_goods){
					$('#case_property_yes').attr("checked","checked");
					$("#case_property_no").prop("disabled", true);
				}else{
					$('#case_property_no').attr("checked","checked");
					$("#case_property_yes").prop("disabled", true);
				}
			} //戒毒
			if(case1.process_treatment_category!=null && case1.process_treatment_category.length>0){
				if($('#process_treatment_category_yes').val()==case1.process_treatment_category){
					$('#process_treatment_category_yes').attr("checked","checked");
					$("#process_treatment_category_no").prop("disabled", true);
				}else{
					$('#process_treatment_category_no').attr("checked","checked");
					$("#process_treatment_category_yes").prop("disabled", true);
				}
			}//听证
			if(case1.process_apply_right!=null && case1.process_apply_right.length>0){
				if($('#hearing_applying_yes').val()==case1.process_apply_right){
					$('#hearing_applying_yes').attr("checked","checked");
					$("#hearing_applying_no").prop("disabled", true);
				}else{
					$('#hearing_applying_no').attr("checked","checked");
					$("#hearing_applying_yes").prop("disabled", true);
				}
			}//提出问题
			if(case1.process_question_list!=null && case1.process_question_list.length>0){
				if($('#problem_asking_yes').val()==case1.process_question_list){
					$('#problem_asking_yes').attr("checked","checked");
					$("#problem_asking_no").prop("disabled", true);
				}else{
					$('#problem_asking_no').attr("checked","checked");
					$("#problem_asking_yes").prop("disabled", true);
				}
			}//审核
			if(case1.process_captain_check!=null && case1.process_captain_check.length>0){
				if($('#case_review_yes').val()==case1.process_captain_check){
					$('#case_review_yes').attr("checked","checked");
					$("#case_review_no").prop("disabled", true);
				}else{
					$('#case_review_no').attr("checked","checked");
					$("#case_review_yes").prop("disabled", true);
				}
			}//问题整改
			if(case1.process_question!=null && case1.process_question.length>0){
				if($('#problem_rectification_yes').val()==case1.process_question){
					$('#problem_rectification_yes').attr("checked","checked");
					$("#problem_rectification_no").prop("disabled", true);
				}else{
					$('#problem_rectification_no').attr("checked","checked");
					$("#problem_rectification_yes").prop("disabled", true);
				}
			}//结案
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
function management(case1){
	console.log("aaa"+case1.process_captain_check);
//	if(case1.process_case_end=='是'){
//		$('#process_file_hand').show();
//	}else  {
//		$('#process_file_hand').hide();
//	}
//	if(case1.process_question!=null){
//		$('#case_score').show();
//		$('#process_case_end').show();
//	}else{
//		$('#case_score').hide();
//		$('#process_case_end').hide();
//	}
//	if(case1.process_case_goods=='是'){
//		$('#punishmentab').show();
//	}else{
//		$('#punishmentab').hide();
//	}
	if(case1.process_case_goods=='是'){
		$('#sheancaiwu').show();
	}else{
		$('#sheancaiwu').hide();
	}
//	if(case1.process_question_list!=""){
//		$('#but_process_question').show();
//	}else{
//		$('#but_process_question').hide();
//	}
}
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
			console.log(staff_info);
			// 遍历并插入input的value
			$.each(staff_info.info, function(key, value) {
				$('input[name="info.' + key + '"]').val(value);
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
// 改变性别方法
//function changeSex_man(even) {
//	var sex = document.getElementById("sex");
//	sex.value = even.value;
//	return sex.value;
//}
//function changeSex_woman(even) {
//	var sex = document.getElementById("sex");
//	sex.value = even.value;
//	return sex.value;
//}
//// 改变是否正式
//function isFormat(even) {
//	var format = document.getElementById("format");
//	format.value = even.value;
//	return fomat.value;
//}
//function isNotFormat(even) {
//	var format = document.getElementById("format");
//	format.value = even.value;
//	return fomat.value;
//}
//改变延长传唤
function changesuspect_summon_yes(even) {
	var sex = document.getElementById("suspect_summon_yes");
	sex.value = even.value;
	return sex.value;
}
function changesuspect_summon_no(even) {
	var sex = document.getElementById("suspect_summon_no");
	sex.value = even.value;
	return sex.value;
}

// CaseDetails.jsp中的延长传唤提交
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
// 延长传唤提交按钮
function loadcaseDetail_suspect_summon(button) {
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
	sex.value = even.value;
	return sex.value;
}
function changeminors_asking_no(even) {
	var sex = document.getElementById("minors_asking_no");
	sex.value = even.value;
	return sex.value;
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
	sex.value = even.value;
	return sex.value;
}
function changeidentification_no(even) {
	var sex = document.getElementById("identification_no");
	sex.value = even.value;
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
	sex.value = even.value;
	return sex.value;
}
function changecase_property_no(even) {
	var sex = document.getElementById("case_property_no");
	sex.value = even.value;
	return sex.value;
}

// CaseDetails.jsp中的是否涉案财物提交
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
	sex.value = even.value;
	return sex.value;
}
function changehearing_applying_no(even) {
	var sex = document.getElementById("hearing_applying_no");
	sex.value = even.value;
	return sex.value;
}

// CaseDetails.jsp中的是否听证申请提交
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
//改变是否提出问题
//function changeproblem_asking_yes(even) {
//	var sex = document.getElementById("problem_asking_yes");
//	sex.value = even.value;
//	return sex.value;
//}
//function changeproblem_asking_no(even) {
//	var sex = document.getElementById("problem_asking_no");
//	sex.value = even.value;
//	return sex.value;
//}

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
// 是否提出问题按钮
function loadcaseDetail_problem_asking() {
	console.log("b2");
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
		}
	};
	xmlhttp.open("post",
			"/ajdbxt/process/updateProcessAction?ajdbxtProcess.process_case_id="
					+ info_id, true);
	xmlhttp.send(formData);
}
//改变是否审核
function changecase_review_yes(even) {
	var sex = document.getElementById("case_review_yes");
	sex.value = even.value;
	return sex.value;
}
function changecase_review_no(even) {
	var sex = document.getElementById("case_review_no");
	sex.value = even.value;
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
//改变是否问题整改
//function changeproblem_rectification_yes(even) {
//	var sex = document.getElementById("problem_rectification_yes");
//	sex.value = even.value;
//	return sex.value;
//}
//function changeproblem_rectification_no(even) {
//	var sex = document.getElementById("problem_rectification_no");
//	sex.value = even.value;
//	return sex.value;
//}

// CaseDetails.jsp中的是否问题整改提交
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
	sex.value = even.value;
	return sex.value;
}
function changecase_ending_no(even) {
	var sex = document.getElementById("case_ending_no");
	sex.value = even.value;
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
function changeprocess_treatment_category_yes(even) {
	var sex = document.getElementById("process_treatment_category_yes");
	sex.value = even.value;
	return sex.value;
}
function changeprocess_treatment_category_no(even) {
	var sex = document.getElementById("process_treatment_category_no");
	sex.value = even.value;
	return sex.value;
}

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
	var formData = new FormData(processDetails);
	formData.append("ajdbxtProcess.process_detention", process_detention);
	formData.append("ajdbxtProcess.process_penalty", process_penalty);
	xmlhttp.onreadystatechange = function() {
		console.log("c2");
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var result = xmlhttp.responseText;
			if (isContains(result,'success')) {
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
