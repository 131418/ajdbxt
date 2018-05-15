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
							var option = '';
								var deparment = update_police_vo.department	.department_name;
							
								$
										.post(
												'/ajdbxt/info/Info_lal',
												function(Department_data) {
													// 所有办案单位循环
													for (var len = 0; len < Department_data.departments.length; len++) {
														option += '<option ';
														if (Department_data.departments[len].department_name == deparment) {
															option += 'selected';
														}
														option += ' value="'
																+ Department_data.departments[len].ajdbxt_department_id
																+ '">'
																+ Department_data.departments[len].department_name
																+ '</option>';
													}
													$(
															'#input_info_department')
															.html(option);
												}, 'json');

					// 主办民警
								var option1 = '';
								var info_main_police = update_police_vo.info.info_main_police;
							
								$
										.post(
												'/ajdbxt/info/Info_getPolices',{
													"info.info_department" : update_police_vo.info.info_department
												},
												function(Info_main_police_data) {
													// 所有主办民警循环
													for (var len = 0; len < Info_main_police_data.length; len++) {
														option1 += '<option ';
														if (Info_main_police_data[len].ajdbxt_police_id == info_main_police) {
															option1 += 'selected';
														}
														option1 += ' value="'
																+ Info_main_police_data[len].ajdbxt_police_id
																+ '">'
																+ Info_main_police_data[len].police_name
																+ '</option>';
													}
													$(
															'#input_info_main_police')
															.html(option1);
												}, 'json');
				
					// 协办民警1
								var option2 = '';
								var info_assistant_police_one = update_police_vo.info.info_assistant_police_one;
							
								$
										.post(
												'/ajdbxt/info/Info_getPolices',{
													"info.info_department" : update_police_vo.info.info_department
												},
												function(Info_assistant_police_one_data) {
													// 所有协办民警1循环
													for (var len = 0; len < Info_assistant_police_one_data.length; len++) {
														option2 += '<option ';
														if (Info_assistant_police_one_data[len].ajdbxt_police_id == info_assistant_police_one) {
															option2 += 'selected';
														}
														option2 += ' value="'
																+ Info_assistant_police_one_data[len].ajdbxt_police_id
																+ '">'
																+ Info_assistant_police_one_data[len].police_name
																+ '</option>';
													}
													$(
															'#input_info_assistant_police_one')
															.html(option2);
												}, 'json');
					// 协办民警2
								var option3 = '';
								var info_assistant_police_two = update_police_vo.info.info_assistant_police_two;
							
								$
										.post(
												'/ajdbxt/info/Info_getPolices',{
													"info.info_department" : update_police_vo.info.info_department
												},
												function(Info_assistant_police_two_data) {
													// 所有协办民警2循环
													if(null == info_assistant_police_two){
														option3 += '<option selected value=""></option>'
													}
													for (var len = 0; len < Info_assistant_police_two_data.length; len++) {
														option3 += '<option ';
														if (Info_assistant_police_two_data[len].ajdbxt_police_id == info_assistant_police_two) {
															option3 += 'selected';
														}
														option3 += ' value="'
																+ Info_assistant_police_two_data[len].ajdbxt_police_id
																+ '">'
																+ Info_assistant_police_two_data[len].police_name
																+ '</option>';
													}
													$(
															'#input_info_assistant_police_two')
															.html(option3);
												}, 'json');

					// 所（队）法制员
					var option4 = '';
					var info_department_legal_member = update_police_vo.info.info_department_legal_member;
				
					$
							.post(
									'/ajdbxt/info/Info_lal',
									function(Info_department_legal_member_data) {
										// 所有所（队）法制员循环
										if(null == info_department_legal_member){
											option4 += '<option selected value=""></option>'
										}
										for (var len = 0; len < Info_department_legal_member_data.legalers.length; len++) {
											option4 += '<option ';
											if (Info_department_legal_member_data.legalers[len].ajdbxt_police_id == info_department_legal_member) {
												option4 += 'selected';
											}
											option4 += ' value="'
													+ Info_department_legal_member_data.legalers[len].ajdbxt_police_id
													+ '">'
													+ Info_department_legal_member_data.legalers[len].police_name
													+ '</option>';
										}
										$(
												'#input_info_department_legal_member')
												.html(option4);
									}, 'json');
					//所（队）长
					var info_department_captain_name = document
					.getElementById("info_department_captain_name");
					info_department_captain_name.value = update_police_vo.cap.police_name;
					//法制大队值班民警
					var option5 = '';
					var info_legal_team_member = update_police_vo.legal.police_name;
				
					$
							.post(
									'/ajdbxt/info/Info_lal',
									function(Info_legal_team_member_data) {
										// 所有法制大队值班民警循环
										if(null == info_legal_team_member){
											option5 += '<option selected value=""></option>'
										}
										for (var len = 0; len < Info_legal_team_member_data.legals.length; len++) {
											option5 += '<option ';
											if (Info_legal_team_member_data.legals[len].police_name == info_legal_team_member) {
												option5 += 'selected';
											}
											option5 += ' value="'
													+ Info_legal_team_member_data.legals[len].ajdbxt_police_id
													+ '">'
													+ Info_legal_team_member_data.legals[len].police_name
													+ '</option>';
										}
										$(
												'#input_info_legal_team_member')
												.html(option5);
									}, 'json');
					//值班局领导
					var option6 = '';
					var info_bureau_leader = update_police_vo.leader.police_name;
				
					$
							.post(
									'/ajdbxt/info/Info_lal',
									function(Info_bureau_leader_data) {
										// 所有值班局领导循环
										if(null == info_bureau_leader){
											option6 += '<option selected value=""></option>'
										}
										for (var len = 0; len < Info_bureau_leader_data.leaders.length; len++) {
											option6 += '<option ';
											if (Info_bureau_leader_data.leaders[len].police_name == info_bureau_leader) {
												option6 += 'selected';
											}
											option6 += ' value="'
													+ Info_bureau_leader_data.leaders[len].ajdbxt_police_id
													+ '">'
													+ Info_bureau_leader_data.leaders[len].police_name
													+ '</option>';
										}
										$(
												'#input_info_bureau_leader')
												.html(option6);
									}, 'json');
				}
			}
		}
}