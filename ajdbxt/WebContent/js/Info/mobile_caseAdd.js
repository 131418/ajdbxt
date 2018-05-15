window.onload = function() {
	each_info_department();
}
function each_info_department(){
	   //所有办案单位
		$.post('/ajdbxt/info/Info_lal', function(Case_data) {
			var option = '';
			var data_list = Case_data.departments;
			for (var len = 0; len < data_list.length; len++) {
				option += '<option value="'
						+ data_list[len].ajdbxt_department_id + '">'
						+ data_list[len].department_name + '</option>';
			}
			
			$('#info_department').html(
					'<option selected="selected" value="" >请选择</option>'
							+ option);
		}, 'json');
		//所有法制大队值班民警
		$.post('/ajdbxt/info/Info_lal', function(Case_data) {
			var option = '';
			var data_list = Case_data.legals;
			for (var len = 0; len < data_list.length; len++) {
				option += '<option value="'
						+ data_list[len].ajdbxt_police_id + '">'
						+ data_list[len].police_name + '</option>';
			}
			$('#info_legal_team_member').html(
					'<option selected="selected" value="" >请选择</option>'
							+ option);
		}, 'json');
		//所有法制员
		$.post('/ajdbxt/info/Info_lal', function(Case_data) {
			var option = '';
			var data_list = Case_data.legalers;
			for (var len = 0; len < data_list.length; len++) {
				option += '<option value="'
						+ data_list[len].ajdbxt_police_id + '">'
						+ data_list[len].police_name + '</option>';
			}
			$('#info_department_legal_member').html(
					'<option selected="selected" value="" >请选择</option>'
							+ option);
		}, 'json');
		//所有值班局领导
		$.post('/ajdbxt/info/Info_lal', function(Case_data) {
			var option = '';
			var data_list = Case_data.leaders;
			for (var len = 0; len < data_list.length; len++) {
				option += '<option value="'
						+ data_list[len].ajdbxt_police_id + '">'
						+ data_list[len].police_name + '</option>';
			}
			$('#info_bureau_leader').html(
					'<option selected="selected" value="" >请选择</option>'
							+ option);
		}, 'json');
		
		$('#input_sure')
		.click(
				function() {
					//var this_modal = $(this);
					$.post(
							'/ajdbxt/info/Info_saveCase',
							$('#case_input form').serialize(),
							function(xhr) {
								if (isContains(xhr,'success')) {
									//toastr.success('添加成功!');
									window.location.href='<%=basePath%>info/Info_page_mobileCaseList'
									//get_ListBreakecaseInformationByPageAndSearch(query_data);
								} else {
									toastr.error('添加失败!');
									return false;
								}
							}, 'text')
				});
		//指派主办民警、协办民警1、所队长
		$("select#info_department").change(
				function() {
					//hideSecondPolice();
					//clearPolicesLast();
					
					$.post('/ajdbxt/info/Info_save', $('#case_input form')
						.serialize(), function(Case_data) {
					//所队长
					$('#info_department_captain_name').val(Case_data.cap.police_name);
					$('#info_department_captain_id').val(Case_data.cap.ajdbxt_police_id);
					var option = '';
					option += '<option value="'
							+ Case_data.police[0].ajdbxt_police_id + '">'
							+ Case_data.police[0].police_name + '</option>';
					$('select[name="info.info_main_police"]').html(option);
//							.selectpicker('refresh').selectpicker('val',
//									Case_data.police[0].ajdbxt_police_id);
					var option1 = '';
					option1 += '<option value="'
							+ Case_data.police[1].ajdbxt_police_id + '">'
							+ Case_data.police[1].police_name + '</option>';
					$('select[name="info.info_assistant_police_one"]')
							.html(option1).selectpicker('refresh').selectpicker(
									'val', Case_data.police[1].ajdbxt_police_id);
				}, 'json')
		});
		//指派协办民警2
		$("img#add_police_two")
		.click(
				function() {
					$("#police_two_td").show(0, function() {
										$.post('/ajdbxt/info/Info_save', $('#case_input form').serialize(),
												function(Case_data) {
															var option = '';
															option += '<option value="'
																	+ Case_data.police[2].ajdbxt_police_id
																	+ '">'
																	+ Case_data.police[2].police_name
																	+ '</option>';
															$(
																	'select[name="info.info_assistant_police_two"]')
																	.html(
																			option)
																	.selectpicker(
																			'refresh')
																	.selectpicker(
																			'val',
																			Case_data.police[2].ajdbxt_police_id);

														}, 'json')
									});
					$("#police_two_tdd").show();
					$("#add_img").hide();
					// alert("bbcb");
				});
		$("#police_two_td").hide();
		$("#police_two_tdd").hide();

}

//每次变更办案单位后清除上一次指派的人员
function clearPolicesLast() {
	if ($('#info_main_police').val() != '') {
		$('select[name="info.info_main_police"]')
			.html('')
			.selectpicker('refresh');
		$('select[name="info.info_assistant_police_one"]')
			.html('')
			.selectpicker('refresh');
		$('select[name="info.info_assistant_police_two"]')
			.html('')
			.selectpicker('refresh');
	}
}