var query_data = {
	"infoVO.currPage" : "1",
};
function isContains(str, substr) {
    return str.indexOf(substr) >= 0;
}

// 当前页面分页信息
var page_infomantion = {
	currPage : 1,
	countRecords : 1,
	pageSize : 10,
	totalPages : 1,
	havePrePage : false,
	haveNexPage : false,
}


//录入案件信息
$(function() {
	/*--------------------------------------------------------*/
	// 案件信息录入模态框事件
	
	//所有法制大队民警
	$('#breakCase_input').on(
			'show.bs.modal',
			function() {
				var this_modal = $(this);
				$.post('/ajdbxt/info/Info_lal', function(Case_data) {
					var option = '';
					var data_list = Case_data.legals;
					for (var len = 0; len < data_list.length; len++) {
						option += '<option value="'
								+ data_list[len].ajdbxt_police_id + '">'
								+ data_list[len].police_name + '</option>';
					}
					this_modal.find(
							'select[name="info.info_legal_team_member"]').html(
							option).selectpicker('refresh');
					// 除去加载提示
					this_modal.find('.load_remind').remove();
				}, 'json');
			})
	//所有局领导
	$('#breakCase_input').on(
			'show.bs.modal',
			function() {
				var this_modal = $(this);
				$.post('/ajdbxt/info/Info_lal', function(Case_data) {
					var option = '';
					var data_list = Case_data.leaders;
					for (var len = 0; len < data_list.length; len++) {
						option += '<option value="'
								+ data_list[len].ajdbxt_police_id + '">'
								+ data_list[len].police_name + '</option>';
					}
					this_modal.find('select[name="info.info_bureau_leader"]')
							.html(option).selectpicker('refresh');
					// 除去加载提示
					this_modal.find('.load_remind').remove();
				}, 'json');

			})
	//所有办案单位
	$('#breakCase_input').on(
			'show.bs.modal',
			function() {
				var this_modal = $(this);
				$.post('/ajdbxt/info/Info_lal', function(Case_data) {
					var option = '';
					var data_list = Case_data.departments;
					for (var len = 0; len < data_list.length; len++) {
						option += '<option value="'
								+ data_list[len].ajdbxt_department_id + '">'
								+ data_list[len].department_name + '</option>';
					}
					
					this_modal.find('select[name="info.info_department"]')
							.html(option).selectpicker('refresh');
					// 除去加载提示
					this_modal.find('.load_remind').remove();
				}, 'json');

			})
	//所有法制员
	$('#breakCase_input').on(
			'show.bs.modal',
			function() {
				var this_modal = $(this);
				$.post('/ajdbxt/info/Info_lal', function(Case_data) {
					var option = '';
					var data_list = Case_data.legalers;
					for (var len = 0; len < data_list.length; len++) {
						option += '<option value="'
								+ data_list[len].ajdbxt_police_id + '">'
								+ data_list[len].police_name + '</option>';
					}
					this_modal.find('select[name="info.info_department_legal_member"]')
							.html(option).selectpicker('refresh');
					// 除去加载提示
					this_modal.find('.load_remind').remove();
				}, 'json');
			})
	// 添加刑事破案
	$('.input_sure')
			.click(
					function() {
						var this_modal = $(this);
						$.post(
								'/ajdbxt/info/Info_saveCase',
								$('#breakCase_input form').serialize(),
								function(xhr) {
									if (isContains(xhr,'success')) {
										toastr.success('添加成功!');
										get_ListBreakecaseInformationByPageAndSearch(query_data);
									} else {
										toastr.error('添加失败!');
										return false;
									}
								}, 'text')
					});
	// 执行列表查询
	get_ListBreakecaseInformationByPageAndSearch(query_data);
	
	/***********************************************************/

	//这个方法没用
	$('.to_quert').click(function() {
		var arr = $('#query_infomantion_inmodal').serializeArray();
		$.each(arr, function(key, value) {
			// key为arr里对象的索引，value为索引为key的对象。对象以{name:
			// 'firstname', value: 'Hello'}形式存储,
			// 以obj.name和obj.value形式遍历
			query_data[value.name] = value.value;
		});
		// $('.query_prompting_info')
		// .text(
		// '接警时间从'
		// + $(
		// 'input[name="page_list_BreakecaseInformation.start_time"]')
		// .val()
		// + '到'
		// + $(
		// 'input[name="page_list_BreakecaseInformation.stop_time"]')
		// .val());
		get_ListBreakecaseInformationByPageAndSearch(query_data);
	});
	//无用
	$('.empty_quert').click(function() {
		for ( var i in query_data) {
			query_data[i] = "";
		}
		// 选择框清除内容
		$('#newQuery select').val("");
		// 输入框清除内容
		$('$newQuery input').val("");

		/*
		 * //影藏模态框 $('#newQuery').modal('hide');
		 */
		// 成功提示
		toastr.success('清除查询信息成功');
	});
	/*--------------------------------------------------------*/
})


// 案件列表查询
function get_ListBreakecaseInformationByPageAndSearch(data) {
	$.post(
		'/ajdbxt/info/Info_listAll',
		data,
		function(xhr) {
			var data_list = xhr.Caselist;
			var str = '';
			for (var len = 0; len < data_list.length; len++) {
				str += '<tr>';
				str += '<td>' + (len + 1) + '</td>';// 序号
				str += '<td><a href="/ajdbxt/info/Info_page_CaseDetails?ajdbxt_info_id='
					/*'<td><a href="/ajdbxt/info/Info_page_CaseDetails?infoVO.Caselist['+len+'].info.ajdbxt_info_id='*/
						+ data_list[len].info.ajdbxt_info_id
						+ '">'
						+ data_list[len].info.info_name
						+ '</a></td>';// 案件名称
				str += '<td>' + data_list[len].info.info_category
						+ '</td>';// 案件类别
				str += '<td>'
						+ data_list[len].department.department_name
						+ '</td>';// 办案单位
				str += '<td>' + data_list[len].info.info_catch_time
						+ '</td>';// 抓获时间
				str += '<td>'
						+ data_list[len].police[0].police_name
						+ '</td>';// 主办民警
				str += '<td>'
						+ data_list[len].police[1].police_name
						+ ((null!=(data_list[len].police[2]))?('、'+data_list[len].police[2].police_name):(''))
						+ '</td>';// 协办民警1	
				/*str += '<td colspan="1">'
						+ data_list[len].police[2].police_name
					+ (null==(data_list[len].police[2]))?'':data_list[len].police[2].police_name
						+ '</td>';// 协办民警2
*/				/*
				 * str += '<td>' +
				 * data_list[len].breakCase.breakcase_contrast_locale_fingerprint_number + '</td>';//现场指纹编号
				 */
				str += '<td>'
						+ '<input type="hidden" value="'
						+ data_list[len].info.ajdbxt_info_id
						+ '" />'
						+ '<button type="button" style="margin-left:6px;" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#breakCase_modification"><i class="fa fa-pencil-square-o" aria-hidden="true" ></i>修改</button>'
						+ '<button type="button" style="margin-left:6px;" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i>删除</button>'
						+ '</td>';
				str += '</tr>';
			}
			// 加载案件列表到表格中
			$('.breakcase_table_info tbody').html(str); // 操作点击事件

			// -----------------------------------------------------
			// 设置确认、删除点击事件
			$('.btn-xs').click(modifi_delete);
			// -----------------------------------------------------

			// 分页信息存入page_infomantion中
			page_infomantion.currPage = xhr.currPage; // 当前页数
			page_infomantion.countRecords = xhr.countRecords; // 总页数
			page_infomantion.pageSize = xhr.pageSize; // 每页记录数
			page_infomantion.totalPages = xhr.totalPages; // 总记录数
			page_infomantion.havePrePage = xhr.havePrePage; // 是否有上一页
			page_infomantion.haveNexPage = xhr.haveNexPage; // 是否有下一页

			// 分页下的记录信息
			var opt = '<option value=""></option>';
			for (var index = 1; index <= xhr.totalPages; index++) {
				opt += '<option>' + index + '</option>';
			}
			$('.info').html(
					'共 ' + xhr.countRecords + '条信息 当前'
							+ xhr.currPage + '/' + xhr.totalPages
							+ '页 ' + xhr.pageSize
							+ '条信息/页&nbsp&nbsp转到第'
							+ '<select onchange="toPage(this)">'
							+ opt + '</select> 页');
			// 影藏模态框
			$('#newQuery').modal('hide')
		}, 'json')
}

//修改和删除模态框
var modifi_delete = function() {
	var type = $(this).text().trim();
	var id = $(this).siblings('input').val();
	if (type == "修改") {
		$.post(
				'/ajdbxt/info/Info_getSingleInfo',
						{
							"info.ajdbxt_info_id" : id
						},
						function(xhr_data) {
							var str = '';
							str += '<table align="center" class="table table-hover table-condensed"><tbody><tr>';
							str += '<td>案件名称</td><td><input style="witdh:70%;" class="form-control" name="info.info_name" type="text" value="'
									+ xhr_data.info.info_name + '"  /></td></tr><tr>';
							
							str += '<td>案件类别</td><td>';
							str += '<select style="witdh:100%;" class="form-control" data-live-search="true" name="info.info_category" >';
							str += '<option  '
									+ (xhr_data.info.info_category == "刑事案件" ? "selected"
											: "") + ' >刑事案件</option>';
							str += '<option '
									+ (xhr_data.info.info_category == "行政案件" ? "selected"
											: "") + '>行政案件</option>';
							str += '</select></td>';
							str += '</tr>';

							str += '<tr>';
							str += '<td>办案单位<i class="fa fa-spinner fa-pulse fa-fw load_remind"></td><td colspan="1">';
							str += '<select id="info_department" style="witdh:100%;" class="form-control selectpicker" data-live-search="true" name="info.info_department">';
							str += '</select></td>';
							str += '</tr>';

							str += '<tr>';
							str += '<td>抓获时间</td><td><input style="witdh:70%;" class="form-control mydate_minute" name="info.info_catch_time" type="text" value="'
									+ xhr_data.info.info_catch_time
									+ '"  /></td></tr><tr>';

							str += '<td>主办民警<i class="fa fa-spinner fa-pulse fa-fw load_remind"></td><td>';
							str += '<select style="witdh:100%;" class="form-control selectpicker" data-live-search="true" name="info.info_main_police">';
							str += '</select></td>';
							str += '</tr>';

							str += '<tr>';
							str += '<td>协办民警1<i class="fa fa-spinner fa-pulse fa-fw load_remind"></td><td>';
							str += '<select style="witdh:100%;" class="form-control selectpicker" data-live-search="true" name="info.info_assistant_police_one">';
							str += '</select></td></tr><tr>';
							
							str += '<td>协办民警2<i class="fa fa-spinner fa-pulse fa-fw load_remind"></td><td>';
							str += '<select style="witdh:100%;" class="form-control selectpicker" data-live-search="true" name="info.info_assistant_police_two">';
							str += '</select></td>';
							str += '</tr>';

							str += '<tr>';
							str +=  '<td>所（队）法制员<i class="fa fa-spinner fa-pulse fa-fw load_remind"></td><td colspan="1">'
									+'<select style="witdh:100%;" class="form-control selectpicker" data-live-search="true" name="info.info_department_legal_member">'
									+'</select></td>'
									+ '</td></tr><tr>';
							
							/*str += '<td>所（队）长</td><td><input style="witdh:70%;" class="form-control" name="info.info_department_captain" type="text" value="'
									+ xhr_data.cap.police_name
									+ '"  /></td>';*/
							str += '<td>所（队）长</td><td><input style="witdh: 70%;" class="form-control" id="info_department_captain_name" name="police.police_name" type="text"></td>'
								+ '<td hidden="true"><input style="witdh: 70%;" class="form-control" id="info_department_captain_id" name="info.info_department_captain" type="text"></td>';
							str += '</tr>';

							str += '<tr>';
							str += '<td>法制大队值班民警<i class="fa fa-spinner fa-pulse fa-fw load_remind"></td><td colspan="1">';
							str += '<select style="witdh:100%;" class="form-control selectpicker" data-live-search="true" name="info.info_legal_team_member">';
							str += '</select></td>';
							str += '</tr>';

							str += '<tr>';
							str += '<td>值班局领导<i class="fa fa-spinner fa-pulse fa-fw load_remind"></td><td colspan="1">';
							str += '<select style="witdh:100%;" class="form-control selectpicker" data-live-search="true" name="info.info_bureau_leader">';
							str += '</select></td>';
							str += '<input name="info.ajdbxt_info_id" type="hidden" value="'
									+ xhr_data.info.ajdbxt_info_id + '" />';
							str += '</tr></tbody></table>';

							// str加载到模态框中
							$('#breakCase_modification .panel-body').html(str);

							// dddd
							$('#breakCase_modification').on(
								'show.bs.modal',
								
								// 所有法制大队民警循环
								function() {
									var this_modal = $(this);
									$.post(
											'/ajdbxt/info/Info_lal',
											function(Case_data) {
												var option = '';
												var data_list = Case_data.legals;
												for (var len = 0; len < data_list.length; len++) {
													option += '<option ';
													if (xhr_data.legal.police_name == data_list[len].police_name) {
														option += ' selected';
													}
													option += ' value="'
															+ data_list[len].ajdbxt_police_id
															+ '">'
															+ data_list[len].police_name
															+ '</option>';
												}
												this_modal
														.find('select[name="info.info_legal_team_member"]')
														.html(option)
														.selectpicker('refresh');
														// 除去加载提示
												this_modal
														.find('.load_remind')
														.remove();
											}, 'json');

								})

								// 所有局领导循环
								$('#breakCase_modification')
										.on('show.bs.modal',
										function() {
												var this_modal = $(this);
												$.post('/ajdbxt/info/Info_lal',
														function(Case_data) {
																var option = '';
																var data_list = Case_data.leaders;
																for (var len = 0; len < data_list.length; len++) {
																	option += '<option ';
																	if (xhr_data.leader.police_name == data_list[len].police_name) {
																		option += ' selected';
																	}
																	option += ' value="'
																			+ data_list[len].ajdbxt_police_id
																			+ '">'
																			+ data_list[len].police_name
																			+ '</option>';
																}
																this_modal
																		.find('select[name="info.info_bureau_leader"]')
																		.html(option)
																		.selectpicker('refresh');
																// 除去加载提示
																this_modal
																		.find('.load_remind')
																		.remove();
															}, 'json');
												})

								// 所有民警循环
								$('#breakCase_modification')
									.on('show.bs.modal',
										function() {
												var this_modal = $(this);
												//主办民警
												$.post('/ajdbxt/info/Info_getPolices',
														{
															"info.info_department" : xhr_data.info.info_department
														},
														function(Case_data) {
																var option = '';
																for (var len = 0; len < Case_data.length; len++) {
																	option += '<option';
																	if (xhr_data.info.info_main_police == Case_data[len].ajdbxt_police_id) {
																		option += ' selected';
																	}
																	option += ' value="'
																			+ Case_data[len].ajdbxt_police_id
																			+ '">'
																			+ Case_data[len].police_name
																			+ '</option>';
																}
																this_modal
																		.find('select[name="info.info_main_police"]')
																		.html(option)
																		.selectpicker('refresh');
																// 除去加载提示
																this_modal
																		.find('.load_remind')
																		.remove();
																}, 'json');
												
											// 协办民警1				
											$.post('/ajdbxt/info/Info_getPolices',
													{
														"info.info_department" : xhr_data.info.info_department
													},
													function(Case_data) {
															// 所有案件循环
															var option = '';
															for (var len = 0; len < Case_data.length; len++) {
																option += '<option';
																if (xhr_data.info.info_assistant_police_one == Case_data[len].ajdbxt_police_id) {
																	option += ' selected';
																}
																option += ' value="'
																		+ Case_data[len].ajdbxt_police_id
																		+ '">'
																		+ Case_data[len].police_name
																		+ '</option>';
															}
															this_modal
																	.find(
																			'select[name="info.info_assistant_police_one"]')
																	.html(
																			option)
																	.selectpicker(
																			'refresh');
															// 除去加载提示
															this_modal
																	.find(
																			'.load_remind')
																	.remove();
														}, 'json');
											
											//协办民警2
											$.post('/ajdbxt/info/Info_getPolices',
													{
														"info.info_department" : xhr_data.info.info_department
													},
													function(Case_data) {
														// 所有案件循环
														var option = '';
														// var
														// data_list=Case_data.Caselist;
														/*未分配协办民警2则无被选中项*/
														if(null == xhr_data.info.info_assistant_police_two){
															option += '<option selected value=""></option>'
														}
														for (var len = 0; len < Case_data.length; len++) {
															option += '<option';
																if (xhr_data.info.info_assistant_police_two == Case_data[len].ajdbxt_police_id) {
																	option += ' selected';
																}
																option += ' value="'
																		+ Case_data[len].ajdbxt_police_id
																		+ '">'
																		+ Case_data[len].police_name
																		+ '</option>';
															}
															this_modal
																	.find(
																			'select[name="info.info_assistant_police_two"]')
																	.html(
																			option)
																	.selectpicker(
																			'refresh');
															// 除去加载提示
															this_modal
																	.find(
																			'.load_remind')
																	.remove();
														}, 'json');
												
												
												
											})

							//所有办案单位
							$('#breakCase_modification')
									.on('show.bs.modal',
										function() {
										var this_modal = $(this);
										$.post('/ajdbxt/info/Info_lal',
												function(Case_data) {
														// 所有案件循环
														var option = '';
														var data_list = Case_data.departments;
														for (var len = 0; len < data_list.length; len++) {
															option += '<option ';
															if (xhr_data.department.department_name == data_list[len].department_name) {
																option += ' selected';
															}
															option += ' value="'
																	+ data_list[len].ajdbxt_department_id
																	+ '">'
																	+ data_list[len].department_name
																	+ '</option>';
														}
														this_modal
																.find(
																		'select[name="info.info_department"]')
																.html(
																		option)
																.selectpicker(
																		'refresh');
														// 除去加载提示
														this_modal
																.find(
																		'.load_remind')
																.remove();
													}, 'json');

											})
											
											
											
							//TODO所有法制员循环
							$('#breakCase_modification')
									.on('show.bs.modal',
										function() {
										var this_modal = $(this);
										$.post('/ajdbxt/info/Info_lal',
												function(Case_data) {
														var option = '';
														var data_list = Case_data.legalers;
														for (var len = 0; len < data_list.length; len++) {
															option += '<option ';
															if (xhr_data.info.info_department_legal_member == data_list[len].ajdbxt_police_id) {
																option += ' selected';
															}
															option += ' value="'
																	+ data_list[len].ajdbxt_police_id
																	+ '">'
																	+ data_list[len].police_name
																	+ '</option>';
														}
														this_modal
																.find(
																		'select[name="info.info_department_legal_member"]')
																.html(
																		option)
																.selectpicker(
																		'refresh');
														// 除去加载提示
														this_modal
																.find(
																		'.load_remind')
																.remove();
													}, 'json');

											})
											
											

							// //模态框显示
							$('#breakCase_modification').modal('show');
							 
							/*$
									.post(
											'/ajdbxt/info/Info_lal',
											function(Case_data) {
												// 所有案件循环
												  var this_modal = $(this);
												var option = '';
												var data_list = Case_data.legals;
												for (var len = 0; len < data_list.length; len++) {
													option += '<option ';
													if (xhr_data.legal.police_name == data_list[len].police_name) {
														option += 'selected';
													}
													option += ' value="'
															+ data_list[len].ajdbxt_police_id
															+ '">'
															+ data_list[len].police_name
															+ '</option>';
												}
												console.log('fdfd:',option)
												this_modal
														.find(
																'select[name="info.info_legal_team_member"]')
														.html(option)
														.selectpicker('refresh');
												// 除去加载提示
												$('.load_remind').hide();
											}, 'json');*/
							// 确认按钮添加事件
							$('.breakCase_operation').unbind().click(breakecase_modification);
						}, 'json');

	} else if (type == "删除") {
		var formData = new FormData();
		formData.append('info.ajdbxt_info_id', id);
		$.confirm({
					title : '确定删除?',
					smoothContent : false,
					content : false,
					autoClose : 'cancelAction|10000',
					buttons : {
						deleteUser : {
							btnClass : 'btn-danger',
							text : '确认',
							action : function() {
								$.ajax({
											url : '/ajdbxt/info/Info_delete',
											type : 'post',
											data : formData,
											processData : false,
											contentType : false,
											dataType : 'text',
											success : function(data) {

												if (data == "success") {
													toastr.success("删除成功！");
													// 获取对应option中的value值
													get_ListBreakecaseInformationByPageAndSearch(query_data);
												} else {
													toastr.error("删除失败！");
												}
											}
										});
							}
						},
						cancelAction : {
							btnClass : 'btn-blue',
							text : '取消',
						}
					}
				});
	}
}

// 确认修改
var breakecase_modification = function() {
	$
			.confirm({
				title : '确定修改?',
				smoothContent : false,
				content : false,
				autoClose : 'cancelAction|10000',
				buttons : {
					deleteUser : {
						btnClass : 'btn-danger',
						text : '确认',
						action : function() {
							$
									.post(
											'/ajdbxt/info/Info_update',
											$('#breakCase_modification form')
													.serialize(),
											function(data) {
												if (isContains(data,'success')) {
													toastr.success("修改成功！");
													// 获取对应option中的value值
													get_ListBreakecaseInformationByPageAndSearch(query_data);
												} else {
													toastr.error("修改失败！");
												}
											}, 'json')

							/*
							 * $.ajax({ url :
							 * '/xsjsglxt/case/BreakCase_updateBreakcase', type :
							 * 'post', data : $('#breakCase_modification
							 * form').serialize(), processData : false,
							 * contentType : false, dataType : 'json', success :
							 * function(data) { if (data == "success") {
							 * toastr.success("修改成功！"); //获取对应option中的value值
							 * get_ListBreakecaseInformationByPageAndSearch(query_data); }
							 * else { toastr.error("修改失败！"); } } });
							 */
						}
					},
					cancelAction : {
						btnClass : 'btn-blue',
						text : '取消',
					}
				}
			});
}

/*
 * $("#breakcase_type").change(function (){
 * if($("#breakcase_type").val()=="已有案件"){ $("#breakcase_case").prop('disabled',
 * false).selectpicker('refresh'); }else{ $("#breakcase_case").prop('disabled',
 * true).selectpicker('refresh'); } });
 */

// 首页
function firstPage() {
	if (page_infomantion.currPage == 1) {
		toastr.error('已经是第一页！');
		return;
	}
	query_data['infoVO.currPage'] = 1;
	get_ListBreakecaseInformationByPageAndSearch(query_data);
}
// 上一页
function prePage() {
	if (page_infomantion.currPage <= 1) {
		toastr.error('已经是第一页！');
		return;
	}
	query_data['infoVO.currPage'] = page_infomantion.currPage - 1;
	get_ListBreakecaseInformationByPageAndSearch(query_data);
}
// 下一页
function nextPage() {
	if (page_infomantion.currPage >= page_infomantion.totalPages) {
		toastr.error('已经是最后一页！');
		return;
	}
	query_data['infoVO.currPage'] = page_infomantion.currPage + 1;
	get_ListBreakecaseInformationByPageAndSearch(query_data);
}
// 尾页
function lastPage() {
	if (page_infomantion.currPage == page_infomantion.totalPages) {
		toastr.error('已经是最后一页！');
		return;
	}
	query_data['infoVO.currPage'] = page_infomantion.totalPages;
	get_ListBreakecaseInformationByPageAndSearch(query_data);
}
// 跳转到n页
function toPage(object) {
	query_data['infoVO.currPage'] = $(object).val();
	get_ListBreakecaseInformationByPageAndSearch(query_data);
}

//隐藏和显示协办民警2
function hideSecondPolice() {
	$("#police_two_td").hide();
	$("#police_two_tdd").hide();
}
hideSecondPolice();

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



//指派协办民警2
/*$("img#add_police_two").click(
		function() {
			$("#police_two_td").show(0, function() {
					$.post('/ajdbxt/info/Info_save', $('#breakCase_input form').serialize(),
							function(Case_data) {
									var option = '';
									option += '<option value="'
											+ Case_data.police[2].ajdbxt_police_id
											+ '">'
											+ Case_data.police[2].police_name
											+ '</option>';
									$('select[name="info.info_assistant_police_two"]')
											.html(option)
											.selectpicker('refresh')
											.selectpicker('val', Case_data.police[2].ajdbxt_police_id);

								}, 'json')
			});
			$("#police_two_tdd").show();
			$("#add_img").hide();
			// alert("bbcb");
		});*/
$("img#add_police_two")
		.click(
				function() {
					$("#police_two_td").show(0, function() {
										$.post('/ajdbxt/info/Info_save', $('#breakCase_input form').serialize(),
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


//指派主办民警、协办民警1、所队长
$("select#info_department").change(
		function() {
			hideSecondPolice();
			clearPolicesLast();
			
			$.post('/ajdbxt/info/Info_save', $('#breakCase_input form')
				.serialize(), function(Case_data) {
			//所队长
			$('#info_department_captain_name').val(Case_data.cap.police_name);
			$('#info_department_captain_id').val(Case_data.cap.ajdbxt_police_id);
			var option = '';
			option += '<option value="'
					+ Case_data.police[0].ajdbxt_police_id + '">'
					+ Case_data.police[0].police_name + '</option>';
			$('select[name="info.info_main_police"]').html(option)
					.selectpicker('refresh').selectpicker('val',
							Case_data.police[0].ajdbxt_police_id);
			var option1 = '';
			option1 += '<option value="'
					+ Case_data.police[1].ajdbxt_police_id + '">'
					+ Case_data.police[1].police_name + '</option>';
			$('select[name="info.info_assistant_police_one"]')
					.html(option1).selectpicker('refresh').selectpicker(
							'val', Case_data.police[1].ajdbxt_police_id);
		}, 'json')
});