window.onload = function() {
	var url = window.location.href;
	info_id = url.substring(url.indexOf("=") + 1);
	console.log(info_id);
	get_staffDetails(info_id);
	get_processDetails(info_id);
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
			// 单选框
			$.each(case1, function(k, v) {
				// 嫌疑人延长（单选框）
				if (k == "process_lengthen_subpoena") {
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
				} else if (k == "process_nonage") {
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
				} else if (k == "process_authenticate") {
					if (v != null && v.length>0) {
					if ($('input[type="radio"]').eq(4).val() == v) {
						$('input[type="radio"]').eq(4).attr("checked",
								"checked");
					} else {
						$('input[type="radio"]').eq(5).attr("checked",
								"checked");
					}
				}else{
					$('input[type="radio"]').eq(4).attr("checked",
					false);
					$('input[type="radio"]').eq(5).attr("checked",
					false);
				}
				}// 涉案财物
				else if (k == "process_case_goods") {
					if (v != null && v.length>0) {
					if ($('input[type="radio"]').eq(6).val() == v
							&& $('input[type="radio"]').eq(6).val() != "") {
						$('input[type="radio"]').eq(6).attr("checked",
								"checked");
					} else {
						$('input[type="radio"]').eq(7).attr("checked",
								"checked");
					}
				}else{
					$('input[type="radio"]').eq(6).attr("checked",
					false);
					$('input[type="radio"]').eq(7).attr("checked",
					false);
				}
				} else if (k == "process_treatment_category") {
					if (v != null && v.length>0) {
					if ($('input[type="radio"]').eq(8).val() == v) {
						$('input[type="radio"]').eq(8).attr("checked",
								"checked");
					} else {
						$('input[type="radio"]').eq(9).attr("checked",
								"checked");
					}
				}else{
					$('input[type="radio"]').eq(8).attr("checked",
					false);
					$('input[type="radio"]').eq(9).attr("checked",
					false);
				}
				}// 听证
				else if (k == "process_apply_right") {
					if (v != null && v.length>0) {
					if ($('input[type="radio"]').eq(10).val() == v) {
						$('input[type="radio"]').eq(10).attr("checked",
								"checked");
					} else {
						$('input[type="radio"]').eq(11).attr("checked",
								"checked");
					}
				}else{
					$('input[type="radio"]').eq(10).attr("checked",
					false);
					$('input[type="radio"]').eq(11).attr("checked",
					false);
				}
				}// 提出问题
				else if (k == "process_question_list") {
					if (v != null && v.length>0) {
					if ($('input[type="radio"]').eq(12).val() == v) {
						$('input[type="radio"]').eq(12).attr("checked",
								"checked");
						 $("#process_question").show();
					} else {
						$('input[type="radio"]').eq(13).attr("checked",
								"checked");
					}
				}else{
					$('input[type="radio"]').eq(12).attr("checked",
					false);
					$('input[type="radio"]').eq(13).attr("checked",
					false);
				}
				}// 审核
				else if (k == "process_captain_check") {
					if (v != null && v.length>0) {
					if ($('input[type="radio"]').eq(14).val() == v) {
						$('input[type="radio"]').eq(14).attr("checked",
								"checked");
					} else {
						$('input[type="radio"]').eq(15).attr("checked",
								"checked");
					}
				}else{
					$('input[type="radio"]').eq(14).attr("checked",
					false);
					$('input[type="radio"]').eq(15).attr("checked",
					false);
				}
				}// 问题整改
				else if (k == "process_question") {
					if (v != null && v.length>0) {
					if ($('input[type="radio"]').eq(16).val() == v) {
						$('input[type="radio"]').eq(16).attr("checked",
								"checked");
					} else {
						$('input[type="radio"]').eq(17).attr("checked",
								"checked");
					}
				}else{
					$('input[type="radio"]').eq(16).attr("checked",
					false);
					$('input[type="radio"]').eq(17).attr("checked",
					false);
				}
				}// 结案
				else if (k == "process_case_end") {
					if (v != null && v.length>0) {
					if ($('input[type="radio"]').eq(18).val() == v) {
						$('input[type="radio"]').eq(18).attr("checked",
								"checked");
					} else {
						$('input[type="radio"]').eq(19).attr("checked",
								"checked");
					}
				}else{
					$('input[type="radio"]').eq(18).attr("checked",
					false);
					$('input[type="radio"]').eq(19).attr("checked",
					false);
				}
				}
			});
		}
	}

	xmlhttp.open("post", url, true);
	xmlhttp.send();
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
		}
	}

	xmlhttp.open("post", url, true);
	xmlhttp.send();
}
// 获取单选框元素
// 性别
// var staff_sex = document.getElementsByName("sex_content");
// var sex_man = staff_sex[0];
// var sex_woman = staff_sex[1];
// 是否正式
// var staff_format = document.getElementsByName("format_content");
// var isFormat = staff_format[0];
// var isNotFormat = staff_format[1];
// 如果是性别
// if (key == "xsjsglxt_sex") {
// if (value == "男") {
// sex_man.checked = true;
// } else {
// sex_woman.checked = true;
// }
// }
// // 如果是是否正式
// else if (key == "staff_isItFormal") {
// if (value == "是") {
// isFormat.checked = true;
// } else {
// isNotFormat.checked = true;
// }
// }
// $('#staff_duty').val(staff_info.staff_duty);
// $('#staff_MaxEducationalBackground').val(
// staff_info.staff_MaxEducationalBackground);
// $('#staff_politicalStatus').val(staff_info.staff_politicalStatus);
// document.getElementById("photo-show").innerHTML = "<img
// src='/xsjsglxt/team/Staff_downloadPhoto?staff_imageFileName="
// + staff_info.staff_photo + "' />";

// show_studyAjax(staff_id);
// show_workAjax(staff_id);
// show_familyAjax(staff_id);
// show_moveAjax(staff_id);
// show_rewardAjax(staff_id);
// show_againstAjax(staff_id);
// show_punishmentAjax(staff_id);
// show_furloughAjax(staff_id);

// 显示学习经历
// function show_studyAjax(staff_id) {
// console.log("study发生发生地方");
// var xmlhttp_study;
// if (window.XMLHttpRequest) {
// xmlhttp_study = new XMLHttpRequest();
// } else {
// xmlhttp_study = new ActiveXObject("Microsoft.XMLHTTP");
// }
// xmlhttp_study.onreadystatechange = function() {
// if (xmlhttp_study.readyState == 4 && xmlhttp_study.status == 200) {
// var staff_study = xmlhttp_study.responseText;
// if(staff_study=="studentIsNull"){
// $('#studyExperience_table tbody').html("");
// }else{
// console.log("staff_study" + staff_study);
// staff_study = JSON.parse(staff_study);
// console.log(staff_study.length);
// var table_elements=$("#studyExperience_table tbody");
// for(var i=1;i<table_elements.length;i++){
// table_elements.removeChild(table_elements.element[i]);
// }
// var str1 = '';
// for (var len = 0; len < staff_study.length; len++) {
// var xsjsglxt_staffStudent_id = staff_study[len].xsjsglxt_staffStudent_id;
// str1 += '<tr>';
// str1 += '<input type="hidden" class="xsjsglxt_staffStudent_id" id="'
// + xsjsglxt_staffStudent_id + '">';
// str1 += '<td>' + staff_study[len].staffStudent_address
// + '</td>';
// str1 += '<td>' + staff_study[len].staffStudent_startTime
// + '</td>';
// str1 += '<td>' + staff_study[len].staffStudent_startTime
// + '</td>';
// str1 += '<td>' + staff_study[len].staffStudent_stopTime
// + '</td>';
// str1 += '<td> <button class="btn btn-default btn-xs" data-toggle="modal"
// data-target="#reliveStudy_Modal" onclick="show_study(this)" type="button" ><i
// class="fa fa-pencil"></i></button><button class="btn btn-default btn-xs"
// onclick="delete_study(this)" type="button" ><i class="fa
// fa-trash"></i></button></td>';
//
// str1 += '</tr>';
// }
// $('#studyExperience_table tbody').html(str1);
// }
// }
// }
// xmlhttp_study.open("POST",
// "/xsjsglxt/team/StaffStudent_getStudentByStaffId?student.staffStudent_staff="
// + staff_id, true);
// xmlhttp_study.send();
// }
// // 显示工作经历
// function show_workAjax(staff_id) {
// var xmlhttp_work;
// if (window.XMLHttpRequest) {
// xmlhttp_work = new XMLHttpRequest();
// } else {
// xmlhttp_work = new ActiveXObject("Microsoft.XMLHTTP");
// }
// xmlhttp_work.onreadystatechange = function() {
// if (xmlhttp_work.readyState == 4 && xmlhttp_work.status == 200) {
// var staff_work = xmlhttp_work.responseText;
// if(staff_work=="worksIsNull"){
// $('#wordExperience_table tbody').html("");
// }else{
// console.log(staff_work);
// staff_work = JSON.parse(staff_work);
// console.log(staff_work);
//
// var str2 = '';
// for (var len = 0; len < staff_work.length; len++) {
// var xsjsglxt_staffWork_id = staff_work[len].xsjsglxt_staffWork_id;
// str2 += '<tr>';
// str2 += '<input type="hidden" class="xsjsglxt_staffWork_id" id="'
// + xsjsglxt_staffWork_id + '">';
// str2 += '<td>' + staff_work[len].staffWork_address + '</td>';
// str2 += '<td>' + staff_work[len].staffWork_startTime + '</td>';
// str2 += '<td>' + staff_work[len].staffWork_duty + '</td>';
// str2 += '<td>' + staff_work[len].staffWork_stopTime + '</td>';
// str2 += '<td>' + staff_work[len].staffWork_remarks + '</td>';
// str2 += '<td> <button class="btn btn-default btn-xs" data-toggle="modal"
// data-target="#reliveWork_Modal" onclick="show_work(this)" type="button" ><i
// class="fa fa-pencil"></i></button><button class="btn btn-default btn-xs"
// onclick="delete_work(this)" type="button" ><i class="fa
// fa-trash"></i></button></td>';
// str2 += '</tr>';
//
// }
// $('#wordExperience_table tbody').html(str2);
// }
// }
// }
// xmlhttp_work.open("POST",
// "/xsjsglxt/team/StaffWork_getWorkByStaffId?work.staffWork_staff="
// + staff_id, true);
// xmlhttp_work.send();
// }
// // 显示家庭关系
// function show_familyAjax(staff_id) {
// var xmlhttp_family;
// if (window.XMLHttpRequest) {
// xmlhttp_family = new XMLHttpRequest();
// } else {
// xmlhttp_family = new ActiveXObject("Microsoft.XMLHTTP");
// }
// xmlhttp_family.onreadystatechange = function() {
// if (xmlhttp_family.readyState == 4 && xmlhttp_family.status == 200) {
// var staff_family = xmlhttp_family.responseText;
// if(staff_family=="familyIsNull"){
// $('#family_table tbody').html("");
// }else{
// console.log(staff_family);
// staff_family = JSON.parse(staff_family);
// console.log(staff_family);
//
// var str3 = '';
// for (var len = 0; len < staff_family.length; len++) {
// var xsjsglxt_staffFamily_id = staff_family[len].xsjsglxt_staffFamily_id;
//
// str3 += '<tr>';
// str3 += '<input type="hidden" class="xsjsglxt_staffFamily_id" id="'
// + xsjsglxt_staffFamily_id + '">';
// str3 += '<td >'
// + staff_family[len].staffFamily_contactsRelationship
// + '</td>';
// str3 += '<td>' + staff_family[len].staffFamily_name + '</td>';
// str3 += '<td>' + staff_family[len].staffFamily_IDcard + '</td>';
// str3 += '<td>' + staff_family[len].staffFamily_birthday
// + '</td>';
// str3 += '<td>' + staff_family[len].staffFamily_tel + '</td>';
// str3 += '<td>' + staff_family[len].staffFamily_WeiXin + '</td>';
// str3 += '<td>' + staff_family[len].staffFamily_workSpace
// + '</td>';
// str3 += '<td>' + staff_family[len].staffFamily_duty + '</td>';
// str3 += '<td>'
// + staff_family[len].staffFamily_remarks + '</td>';
// str3 += '<td> <button class="btn btn-default btn-xs" data-toggle="modal"
// data-target="#reliveFamily_Modal" onclick="show_family(this)" type="button"
// ><i class="fa fa-pencil"></i></button><button class="btn btn-default btn-xs"
// onclick="delete_family(this)" type="button" ><i class="fa
// fa-trash"></i></button></td>';
// str3 += '</tr>';
// }
// $('#family_table tbody').html(str3);
// }
// }
// }
// xmlhttp_family.open("POST",
// "/xsjsglxt/team/StaffFamily_getFamilyByStaffId?family.staffFamily_staff="
// + staff_id, true);
// xmlhttp_family.send();
// }
//
// // 显示刑警大队调动情况
// function show_moveAjax(staff_id) {
// var xmlhttp_move;
// if (window.XMLHttpRequest) {
// xmlhttp_move = new XMLHttpRequest();
// } else {
// xmlhttp_move = new ActiveXObject("Microsoft.XMLHTTP");
// }
// xmlhttp_move.onreadystatechange = function() {
// if (xmlhttp_move.readyState == 4 && xmlhttp_move.status == 200) {
// var staff_move = xmlhttp_move.responseText;
// if(staff_move=="moveIsNull"){
// $('#policeChange_table tbody').html("");
// }else{
// console.log("staff_move" + staff_move);
// staff_move = JSON.parse(staff_move);
// console.log(staff_move.length);
//
// var str4 = '';
// for (var len = 0; len < staff_move.length; len++) {
// var xsjsglxt_staffMove_id = staff_move[len].xsjsglxt_staffMove_id;
//				
// str4 += '<tr>';
// str4 += '<input type="hidden" class="xsjsglxt_staffMove_id" id="'
// + xsjsglxt_staffMove_id + '">';
// str4 += '<td>' + staff_move[len].staffMove_inTime + '</td>';
// str4 += '<td>' + staff_move[len].staffMove_outTime + '</td>';
// str4 += '<td>' + staff_move[len].staffMove_guard + '</td>';
// str4 += '<td>' + staff_move[len].staffMove_remarks + '</td>';
// str4 += '<td> <button class="btn btn-default btn-xs" data-toggle="modal"
// data-target="#reliveMove_Modal" onclick="show_move(this)" type="button" ><i
// class="fa fa-pencil"></i></button><button class="btn btn-default btn-xs"
// onclick="delete_move(this)" type="button" ><i class="fa
// fa-trash"></i></button></td>';
// str4 += '</tr>';
// }
// $('#policeChange_table tbody').html(str4);
// }
// }
// }
// xmlhttp_move.open("POST",
// "/xsjsglxt/team/StaffMove_getMoveByStaffId?move.staffMove_staff="
// + staff_id, true);
// xmlhttp_move.send();
// }
// // 显示立功受奖情况
// function show_rewardAjax(staff_id) {
// var xmlhttp_reward;
// if (window.XMLHttpRequest) {
// xmlhttp_reward = new XMLHttpRequest();
// } else {
// xmlhttp_reward = new ActiveXObject("Microsoft.XMLHTTP");
// }
// xmlhttp_reward.onreadystatechange = function() {
// if (xmlhttp_reward.readyState == 4 && xmlhttp_reward.status == 200) {
// var staff_reward = xmlhttp_reward.responseText;
// if(staff_reward=="rewardsIsNull"){
// $('#prized_table tbody').html("");
// }else{
// console.log(staff_reward);
// staff_reward = JSON.parse(staff_reward);
// console.log(staff_reward);
//
// var str5 = '';
// for (var len = 0; len < staff_reward.length; len++) {
// var xsjsglxt_staffReward_id = staff_reward[len].xsjsglxt_staffReward_id;
//				
// str5 += '<tr>';
// str5 += '<input type="hidden" class="xsjsglxt_staffReward_id" id="'
// + xsjsglxt_staffReward_id + '">';
// str5 += '<td>' + staff_reward[len].staffReward_situation
// + '</td>';
// str5 += '<td>' + staff_reward[len].staffReward_Time + '</td>';
// str5 += '<td>' + staff_reward[len].staffReward_remarks
// + '</td>';
// str5 += '<td> <button class="btn btn-default btn-xs" data-toggle="modal"
// data-target="#reliveReward_Modal" onclick="show_reward(this)" type="button"
// ><i class="fa fa-pencil"></i></button><button class="btn btn-default btn-xs"
// onclick="delete_reward(this)" type="button" ><i class="fa
// fa-trash"></i></button></td>';
// str5 += '</tr>';
// }
// $('#prized_table tbody').html(str5);
// }
// }
// }
// xmlhttp_reward.open("POST",
// "/xsjsglxt/team/StaffReward_getRewardByStaffId?reward.staffReward_staff="
// + staff_id, true);
// xmlhttp_reward.send();
// }
// // 显示违纪情况
// function show_againstAjax(staff_id) {
// var xmlhttp_against;
// if (window.XMLHttpRequest) {
// xmlhttp_against = new XMLHttpRequest();
// } else {
// xmlhttp_against = new ActiveXObject("Microsoft.XMLHTTP");
// }
// xmlhttp_against.onreadystatechange = function() {
// if (xmlhttp_against.readyState == 4 && xmlhttp_against.status == 200) {
// var staff_against = xmlhttp_against.responseText;
// if(staff_against=="principleIsNull"){
// $('#againstPrinciple_table tbody').html("");
// }else{
// staff_against = JSON.parse(staff_against);
// console.log(staff_against);
//
// var str6 = '';
// for (var len = 0; len < staff_against.length; len++) {
// var xsjsglxt_staffPrinciple_id =
// staff_against[len].xsjsglxt_staffPrinciple_id;
//				
// str6 += '<tr>';
// str6 += '<input type="hidden" class="xsjsglxt_staffPrinciple_id" id="'
// + xsjsglxt_staffPrinciple_id + '">';
//
// str6 += '<td>' + staff_against[len].staffPrinciple_situation
// + '</td>';
// str6 += '<td>' + staff_against[len].staffPrinciple_Time
// + '</td>';
// str6 += '<td>' + staff_against[len].staffPrinciple_remarks
// + '</td>';
// str6 += '<td> <button class="btn btn-default btn-xs" data-toggle="modal"
// data-target="#reliveAgainst_Modal" onclick="show_against(this)" type="button"
// ><i class="fa fa-pencil"></i></button><button class="btn btn-default btn-xs"
// onclick="delete_against(this)" type="button" ><i class="fa
// fa-trash"></i></button></td>';
// str6 += '</tr>';
// }
// $('#againstPrinciple_table tbody').html(str6);
// }
// }
// }
// xmlhttp_against
// .open(
// "POST",
// "/xsjsglxt/team/StaffPrinciple_getPrincipleByStaffId?principle.staffPrinciple_staff="
// + staff_id, true);
// xmlhttp_against.send();
// }
// // 显示处分情况
// function show_punishmentAjax(staff_id) {
// var xmlhttp_punishment;
// if (window.XMLHttpRequest) {
// xmlhttp_punishment = new XMLHttpRequest();
// } else {
// xmlhttp_punishment = new ActiveXObject("Microsoft.XMLHTTP");
// }
// xmlhttp_punishment.onreadystatechange = function() {
// if (xmlhttp_punishment.readyState == 4
// && xmlhttp_punishment.status == 200) {
// var staff_punishment = xmlhttp_punishment.responseText;
// if(staff_punishment=="punishmentIsNull"){
// $('#punish_table tbody').html("");
// }else{
// console.log(staff_punishment);
// staff_punishment = JSON.parse(staff_punishment);
// console.log(staff_punishment);
//
// var str7 = '';
// for (var len = 0; len < staff_punishment.length; len++) {
// var xsjsglxt_staffPunishment_id =
// staff_punishment[len].xsjsglxt_staffPunishment_id;
//				
// str7 += '<tr>';
// str7 += '<input type="hidden" class="xsjsglxt_staffPunishment_id" id="'
// + xsjsglxt_staffPunishment_id + '">';
// str7 += '<td>'
// + staff_punishment[len].staffPunishment_situation
// + '</td>';
// str7 += '<td>' + staff_punishment[len].staffPunishment_Time
// + '</td>';
// str7 += '<td>' + staff_punishment[len].staffPunishment_remarks
// + '</td>';
// str7 += '<td> <button class="btn btn-default btn-xs" data-toggle="modal"
// data-target="#relivePunishment_Modal" onclick="show_punishment(this)"
// type="button" ><i class="fa fa-pencil"></i></button><button class="btn
// btn-default btn-xs" onclick="delete_punishment(this)" type="button" ><i
// class="fa fa-trash"></i></button></td>';
// str7 += '</tr>';
// }
// $('#punish_table tbody').html(str7);
// }
// }
// }
//
// xmlhttp_punishment
// .open(
// "POST",
// "/xsjsglxt/team/StaffPunishment_getPunishmentByStaffId?punishment.staffPunishment_staff="
// + staff_id, true);
// xmlhttp_punishment.send();
// }
// // 显示休假情况
// function show_furloughAjax(staff_id) {
// var xmlhttp_furlough;
// if (window.XMLHttpRequest) {
// xmlhttp_furlough = new XMLHttpRequest();
// } else {
// xmlhttp_furlough = new ActiveXObject("Microsoft.XMLHTTP");
// }
// xmlhttp_furlough.onreadystatechange = function() {
// if (xmlhttp_furlough.readyState == 4 && xmlhttp_furlough.status == 200) {
// var staff_furlough = xmlhttp_furlough.responseText;
// if(staff_furlough=="furloughIsNull"){
// $('#vocation_table tbody').html("");
// }else{
// staff_furlough = JSON.parse(staff_furlough);
//
// var str8 = '';
// for (var len = 0; len < staff_furlough.length; len++) {
// var xsjsglxt_staffFurlough_id =
// staff_furlough[len].xsjsglxt_staffFurlough_id;
//				
// str8 += '<tr>';
// str8 += '<input type="hidden" class="xsjsglxt_staffFurlough_id" id="'
// + xsjsglxt_staffFurlough_id + '">';
//
// str8 += '<td>' + staff_furlough[len].staffFurlough_mainContent
// + '</td>';
// str8 += '<td>' + staff_furlough[len].staffFurlough_startTime
// + '</td>';
// str8 += '<td>' + staff_furlough[len].staffFurlough_days
// + '</td>';
// str8 += '<td>' + staff_furlough[len].staffFurlough_whetherStop
// + '</td>';
// str8 += '<td>' + staff_furlough[len].staffFurlough_stopTime
// + '</td>';
// str8 += '<td>' + staff_furlough[len].staffFurlough_remarks
// + '</td>';
// str8 += '<td> <button class="btn btn-default btn-xs" data-toggle="modal"
// data-target="#reliveFurlough_Modal" onclick="show_furlough(this)"
// type="button" ><i class="fa fa-pencil"></i></button><button class="btn
// btn-default btn-xs" onclick="delete_furlough(this)" type="button" ><i
// class="fa fa-trash"></i></button></td>';
// str8 += '</tr>';
// }
// $('#vocation_table tbody').html(str8);
// }
// }
// }
// xmlhttp_furlough
// .open(
// "POST",
// "/xsjsglxt/team/StaffFurlough_getFurloughByStaffId?furlough.staffFurlough_staff="
// + staff_id, true);
// xmlhttp_furlough.send();
// }
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
			if (result == 'updateSuccess') {
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
			if (result == 'updateSuccess') {
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
			if (result == 'updateSuccess') {
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
			if (result == 'updateSuccess') {
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
			if (result == 'updateSuccess') {
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
function changeproblem_asking_yes(even) {
	var sex = document.getElementById("problem_asking_yes");
	sex.value = even.value;
	return sex.value;
}
function changeproblem_asking_no(even) {
	var sex = document.getElementById("problem_asking_no");
	sex.value = even.value;
	return sex.value;
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
// 是否提出问题按钮
function loadcaseDetail_problem_asking() {
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
			if (result == 'updateSuccess') {
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
			if (result == 'updateSuccess') {
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
function changeproblem_rectification_yes(even) {
	var sex = document.getElementById("problem_rectification_yes");
	sex.value = even.value;
	return sex.value;
}
function changeproblem_rectification_no(even) {
	var sex = document.getElementById("problem_rectification_no");
	sex.value = even.value;
	return sex.value;
}

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
	var processDetails = document.getElementById("processDetails");
	var formData = new FormData(processDetails);
	xmlhttp.onreadystatechange = function() {
		console.log("c2");
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var result = xmlhttp.responseText;
			if (result == 'updateSuccess') {
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
			if (result == 'updateSuccess') {
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
	var processDetails = document.getElementById("processDetails");
	var process_penalty= document.getElementById("process_detention");
	var formData = new FormData(processDetails);
	formData.append("ajdbxtProcess.process_detention", process_detention);
	xmlhttp.onreadystatechange = function() {
		console.log("c2");
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var result = xmlhttp.responseText;
			if (result == 'updateSuccess') {
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
// 处罚按钮
function loadcaseDetail_case_score() {
	console.log("b2");
	if (window.XMLHttpRequest) {
		xmlhttp = new XMLHttpRequest();
	} else {
		xmlhttp = new ActiveXOBject("Microsoft.XMLHTTP");
	}
	var processDetails = document.getElementById("processDetails");
	var case_score= document.getElementById("input_case_score");
	var formData = new FormData(processDetails);
	formData.append("ajdbxtProcess.process_score", case_score);
	xmlhttp.onreadystatechange = function() {
		console.log("c2");
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var result = xmlhttp.responseText;
			if (result == 'updateSuccess') {
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
