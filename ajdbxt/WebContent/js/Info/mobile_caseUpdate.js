window.onload = function() {
	var url = window.location.href;
	info_id = url.substring(url.indexOf("=") + 1);
	get_caseDetails(info_id);
}	
function get_caseDetails(info_id){
		var update_police_vo = null;
		var update_xhr = new XMLHttpRequest();
		update_xhr.open("POST", "/ajdbxt/info/Info_getSingleInfo?info.ajdbxt_info_id="+info_id);
		update_xhr.send(null);
		update_xhr.onreadystatechange = function() {
			if (update_xhr.readyState == 4) {
				if (update_xhr.status == 200) {
					update_police_vo = JSON.parse(update_xhr.responseText);
					console.log("xhr.readyState:" + update_xhr.readyState);
					console.log("xhr.status:" + update_xhr.status);
					// Id
					var input_ajdbxt_info_id = document
							.getElementById("input_ajdbxt_info_id");
					input_ajdbxt_info_id.value = update_police_vo.info.ajdbxt_info_id;

					// 案件名称
					var input_info_name = document
							.getElementById("input_info_name");
					input_info_name.value = update_police_vo.info.info_name;
					// 案件类别
					var input_info_category = document
							.getElementById("input_info_category");
					input_info_category.value = update_police_vo.info.info_category;
					

					// 办案单位
					//var xmlHttpRequest = new XMLHttpRequest();
					//xmlHttpRequest.open("POST", "/ajdbxt/info/Info_lal");
					//xmlHttpRequest.send(null);
					xmlHttpRequest.onreadystatechange = function() {
						if (xmlHttpRequest.readyState == 4
								&& xmlHttpRequest.status == 200) {
							var loginRole = JSON
									.parse(xmlHttpRequest.responseText);
							var option = '';
//							if (loginRole.ajdbxt_police.police_power == "3") {
								var deparment = update_police_vo.ajdbxt_department.ajdbxt_department_id;
								console
										.log("update_police_vo.ajdbxt_department.ajdbxt_department_id:"
						 						+ update_police_vo.ajdbxt_department.ajdbxt_department_id);
								$
										.post(
												'/ajdbxt/user/User_findDepartmentByPage',
												function(Department_data) {
													// 所有案件循环
													for (var len = 0; len < Department_data.list.length; len++) {
														option += '<option ';
														if (Department_data.list[len].ajdbxt_department_id == deparment) {
															option += 'selected';
														}
														option += ' value="'
																+ Department_data.list[len].ajdbxt_department_id
																+ '">'
																+ Department_data.list[len].department_name
																+ '</option>';
													}
													$(
															'#input_police_department')
															.html(option);
												}, 'json');

//							} else {
//								option += '<option value="'
//											+ login_police_deparment_id
//											+ '">'
//										+ login_police_deparment + '</option>';
//								$('#input_police_department').html(option);
//							}
						}

					}

					// 职务
					var input_police_duty = document
							.getElementById("input_police_duty");
					input_police_duty.value = update_police_vo.ajdbxt_police.police_duty;
					// 法制员
					var input_police_legaler = document
							.getElementById("input_police_legaler");
					console
							.log("update_police_vo.ajdbxt_police.police_legaler:"
									+ update_police_vo.ajdbxt_police.police_legaler);
					input_police_legaler.value = update_police_vo.ajdbxt_police.police_legaler;
					// 权限
					var update_input_police_power = document
							.getElementById("update_input_police_power");
					update_input_police_power.value = update_police_vo.ajdbxt_police.police_power;

					// 手机号码
					var input_police_phone_number = document
							.getElementById("input_police_phone_number");
					console
							.log("update_police_vo.ajdbxt_police.police_phone_number:"
									+ update_police_vo.ajdbxt_police.police_phone_number);
					input_police_phone_number.value = update_police_vo.ajdbxt_police.police_phone_number;
				}
			}
		}
}