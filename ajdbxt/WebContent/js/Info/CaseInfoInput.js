var query_data = {
	"infoVO.currPage" : "1",
};

// 当前页面分页信息
var page_infomantion = {
	currPage : 1,
	countRecords : 1,
	pageSize : 10,
	totalPages : 1,
	havePrePage : false,
	haveNexPage : false,
}
/*--------------------------------------------------------*/
// 判断身份证号是否正确，以及从身份证号中取出出生日期
// function BreakCaseListGetBirth() {
// var breakcase_suspecter_identity = document
// .getElementById("breakcase_suspecter_identity");
// var breakcase_suspecter_identityValue = breakcase_suspecter_identity.value
// .trim();
// var breakcase_suspecter_birthday = document
// .getElementById("breakcase_suspecter_birthday");
// var strRegExp = /(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
// if (strRegExp.test(breakcase_suspecter_identityValue) == true) {
// breakcase_suspecter_birthday.value = breakcase_suspecter_identityValue
// .substr(6, 4)
// + "-"
// + breakcase_suspecter_identityValue.substr(10, 2)
// + "-"
// + breakcase_suspecter_identityValue.substr(12, 2);
// } else {
// toastr.error("身份证号输入不合法！");
// return false;
// }
//
// }
$(function() {

	/*--------------------------------------------------------*/
	// 刑事破案添加模态框事件
	$('#breakCase_input').on(
			'show.bs.modal',

			function() {
				var this_modal = $(this);

				$.post('/ajdbxt/info/Info_lal', function(Case_data) {
					// 所有案件循环
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
	$('#breakCase_input').on(
			'show.bs.modal',

			function() {
				var this_modal = $(this);

				$.post('/ajdbxt/info/Info_lal', function(Case_data) {
					// 所有案件循环
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
	$('#breakCase_input').on(
			'show.bs.modal',

			function() {
				var this_modal = $(this);

				$.post('/ajdbxt/info/Info_lal', function(Case_data) {
					// 所有案件循环
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
	// 添加刑事破案
	$('.input_sure')
			.click(
					function() {
						$
								.post(
										'/ajdbxt/info/Info_saveCase',
										$('#breakCase_input form').serialize(),
										function(xhr) {
											if (xhr == 'success') {
												toastr.success('添加成功!');
												get_ListBreakecaseInformationByPageAndSearch(query_data);
												// $('#breakCase_input').find(
												// 'input,textarea').val(
												// '');
												// $('#breakCase_input').find(
												// 'select').find(
												// 'option:first-child')
												// .attr("selected",
												// "selected");
												// ;
											} else {
												toastr.error('添加失败!');
												return false;
											}
										}, 'text')
					});
	// 执行列表查询
	get_ListBreakecaseInformationByPageAndSearch(query_data);
})

/*--------------------------------------------------------*/
// 列表查询
function get_ListBreakecaseInformationByPageAndSearch(data) {

	$
			.post(
					'/ajdbxt/info/Info_listAll',
					data,
					function(xhr) {
						var data_list = xhr.Caselist;
						var str = '';
						for (var len = 0; len < data_list.length; len++) {
							str += '<tr>';
							str += '<td>' + (len + 1) + '</td>';// 序号
							str += '<td><a href="/ajdbxt/info/Info_page_CaseDetails?ajdbxt_info_id='
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
									+ '</td>';// 协办民警1
							str += '<td>'
									+ data_list[len].police[2].police_name
									+ '</td>';// 协办民警2
							/*
							 * str += '<td>' +
							 * data_list[len].breakCase.breakcase_contrast_locale_fingerprint_number + '</td>';//现场指纹编号
							 */
							str += '<td>'
									+ '<input type="hidden" value="'
									+ data_list[len].info.ajdbxt_info_id
									+ '" />'
									+ '<button type="button" style="margin-left:6px;" class="btn btn-primary btn-xs" data-toggle="modal" data-target="#breakCase_modification"><i class="fa fa-pencil-square-o" aria-hidden="true" ></i> 修改</button>'
									+ '<button type="button" style="margin-left:6px;" class="btn btn-danger btn-xs"><i class="fa fa-trash-o"></i> 删除</button>'
									+ '</td>';
							str += '</tr>';
						}
						// console.log('0000:'+str);
						// 加载到表格中
						$('.breakcase_table_info tbody').html(str); // 操作点击事件

						// -----------------------------------------------------
						// 设置点击事件
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

var modifi_delete = function() {
	var type = $(this).text().trim();
	var id = $(this).siblings('input').val();
	console.log(type, id);
	if (type == "修改") {

		$
				.post(
						'/ajdbxt/info/Info_getSingleInfo',
						{
							"info.ajdbxt_info_id" : id
						},
						function(xhr_data) {
							var str = '';
							str += '<table align="center" class="table table-hover table-condensed"><tbody><tr>';

							/*
							 * str += '<td>案件类型</td><td>'; str += '<select
							 * style="witdh:100%;" class="form-control"
							 * data-live-search="true"
							 * name="breakCase.breakcase_type">'; str += '<option ' +
							 * (xhr_data.breakcase_type == "新添案件" ? "selected" :
							 * "") + '>新添案件</option>'; str += '<option ' +
							 * (xhr_data.breakcase_type == "已有案件" ? "selected" :
							 * "") + '>已有案件</option>'; str += '</select></td>';
							 */
							str += '<td>案件名称</td><td><input style="witdh:70%;" class="form-control" name="info.info_name" type="text" value="'
									+ xhr_data.info.info_name + '"  /></td>';
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
							str += '<td>办案单位<i class="fa fa-spinner fa-pulse fa-fw load_remind"></td><td colspan="3">';
							str += '<select  id="breakcase_case" style="witdh:100%;" class="form-control selectpicker" data-live-search="true" name="info.info_department">';
							str += '</select></td>';
							str += '</tr>';

							/*
							 * str += '<tr>'; str += '<td>简要案情</td><td colspan="3"><textarea
							 * style="witdh:70%;display:none;"
							 * class="form-control"
							 * name="briefDetails.xsjsglxt_briefdetails_id" >' +
							 * xhr_data.breakcase_case_note+ '</textarea>'; str
							 * +='<textarea style="witdh:70%;"
							 * class="form-control"
							 * name="briefDetails.briefdetails_details"></textarea>';
							 * str += '</td>'; str += '</tr>';
							 */

							str += '<tr>';
							str += '<td>抓获时间</td><td><input style="witdh:70%;" class="form-control mydate" name="info.info_catch_time" type="text" value="'
									+ xhr_data.info.info_catch_time
									+ '"  /></td>';

							str += '<td>主办民警<i class="fa fa-spinner fa-pulse fa-fw load_remind"></td><td>';
							str += '<select  id="breakcase_case" style="witdh:100%;" class="form-control selectpicker" data-live-search="true" name="info.info_main_police">';
							str += '</select></td>';
							//							
							//							
							// str += '<td>主办民警</td><td><input
							// style="witdh:70%;" class="form-control"
							// name="info.info_main_police" type="text" value="'
							// + xhr_data.police[0].police_name + '" /></td>';
							str += '</tr>';

							str += '<tr>';
							str += '<td>协办民警1<i class="fa fa-spinner fa-pulse fa-fw load_remind"></td><td>';
							str += '<select  id="breakcase_case" style="witdh:100%;" class="form-control selectpicker" data-live-search="true" name="info.info_assistant_police_one">';
							str += '</select></td>';
							str += '<td>协办民警2<i class="fa fa-spinner fa-pulse fa-fw load_remind"></td><td>';
							str += '<select  id="breakcase_case" style="witdh:100%;" class="form-control selectpicker" data-live-search="true" name="info.info_assistant_police_two">';
							str += '</select></td>';
							str += '</tr>';

							str += '<tr>';
							str += '<td>所（队）法制员</td><td><input style="witdh:70%;" class="form-control" name="info.info_department_legal_member" type="text" value="'
									+ xhr_data.info.info_department_legal_member
									+ '"  /></td>';
							str += '<td>所（队）长</td><td><input style="witdh:70%;" class="form-control" name="info.info_department_captain" type="text" value="'
									+ xhr_data.info.info_department_captain
									+ '"  /></td>';
							str += '</tr>';

							str += '<tr>';
							str += '<td>法制大队值班民警<i class="fa fa-spinner fa-pulse fa-fw load_remind"></td><td colspan="3">';
							str += '<select  id="info.info_legal_team_member" style="witdh:100%;" class="form-control selectpicker"  data-live-search="true" name="info.info_legal_team_member">';
							str += '</select></td>';
							str += '</tr>';

							str += '<tr>';
							str += '<td>值班局领导<i class="fa fa-spinner fa-pulse fa-fw load_remind"></td><td colspan="3">';
							str += '<select  id="breakcase_case" style="witdh:100%;" class="form-control selectpicker" data-live-search="true" name="info.info_bureau_leader">';
							str += '</select></td>';
							str += '<input name="info.ajdbxt_info_id" type="hidden" value="'
									+ xhr_data.info.ajdbxt_info_id + '" />';
							// str += '</tr>';

							// str += '<tr>';
							// str += '<td>备注</td><td colspan="3"><textarea
							// style="witdh:70%;" class="form-control"
							// name="breakCase.breakcase_remark">'
							// + xhr_data.breakcase_remark + '</textarea>';
							// // 添加存丢失物的id隐藏域(上一兄元素为备注文本域)
							// str += '<input
							// name="breakCase.xsjsglxt_breakcase_id"
							// type="hidden" value="'
							// + xhr_data.xsjsglxt_breakcase_id + '" />';
							// str += '</td>';
							str += '</tr></tbody></table>';

							// str加载到模态框中
							$('#breakCase_modification .panel-body').html(str);

							// dddd
							$('#breakCase_modification')
									.on(
											'show.bs.modal',

											function() {
												var this_modal = $(this);

												$
														.post(
																'/ajdbxt/info/Info_lal',
																function(
																		Case_data) {
																	// 所有案件循环
																	var option = '';
																	var data_list = Case_data.legals;
																	for (var len = 0; len < data_list.length; len++) {
																		option += '<option ';
																		if (xhr_data.info.info_legal_team_member == data_list[len].police_name) {
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
																					'select[name="info.info_legal_team_member"]')
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

							$('#breakCase_modification')
									.on(
											'show.bs.modal',

											function() {
												var this_modal = $(this);

												$
														.post(
																'/ajdbxt/info/Info_lal',
																function(
																		Case_data) {
																	// 所有案件循环
																	var option = '';
																	var data_list = Case_data.leaders;
																	for (var len = 0; len < data_list.length; len++) {
																		option += '<option ';
																		if (xhr_data.info.info_bureau_leader == data_list[len].police_name) {
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
																					'select[name="info.info_bureau_leader"]')
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

							$('#breakCase_modification')
									.on(
											'show.bs.modal',

											function() {
												var this_modal = $(this);
                                              //主办民警
												$
														.post(
																'/ajdbxt/info/Info_getPolices',
																{
																	"info.info_department" : xhr_data.info.info_department
																},
																function(
																		Case_data) {
																	// 所有案件循环
																	var option = '';
																	// var
																	// data_list=Case_data.Caselist;
																	for (var len = 0; len < Case_data.length; len++) {
																		option += '<option';
																		if (xhr_data.police[0].police_name == Case_data[len].police_name) {
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
																					'select[name="info.info_main_police"]')
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
                                              //协办民警1
												
												$
												.post(
														'/ajdbxt/info/Info_getPolices',
														{
															"info.info_department" : xhr_data.info.info_department
														},
														function(
																Case_data) {
															// 所有案件循环
															var option = '';
															// var
															// data_list=Case_data.Caselist;
															for (var len = 0; len < Case_data.length; len++) {
																option += '<option';
																if (xhr_data.police[1].police_name == Case_data[len].police_name) {
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
												
												$
												.post(
														'/ajdbxt/info/Info_getPolices',
														{
															"info.info_department" : xhr_data.info.info_department
														},
														function(
																Case_data) {
															// 所有案件循环
															var option = '';
															// var
															// data_list=Case_data.Caselist;
															for (var len = 0; len < Case_data.length; len++) {
																option += '<option';
																if (xhr_data.police[2].police_name == Case_data[len].police_name) {
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

							$('#breakCase_modification')
									.on(
											'show.bs.modal',

											function() {
												var this_modal = $(this);

												$
														.post(
																'/ajdbxt/info/Info_lal',
																function(
																		Case_data) {
																	// 所有案件循环
																	var option = '';
																	var data_list = Case_data.departments;
																	for (var len = 0; len < data_list.length; len++) {
																		option += '<option ';
																		if (xhr_data.info.info_department == data_list[len].department_name) {
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

							// //模态框显示
							$('#breakCase_modification').modal('show');

							$
									.post(
											'/ajdbxt/info/Info_lal',
											function(Case_data) {
												// 所有案件循环
												var option = '';
												var data_list = Case_data.legals;
												for (var len = 0; len < data_list.length; len++) {
													option += '<option ';
													if (xhr_data.info.info_legal_team_member == data_list[len].police_name) {
														option += 'selected';
													}
													option += ' value="'
															+ data_list[len].ajdbxt_police_id
															+ '">'
															+ data_list[len].police_name
															+ '</option>';
												}
												// $('.selectpicker').html(option).selectpicker('refresh');

												this_modal
														.find(
																'select[name="info.info_legal_team_member"]')
														.html(option)
														.selectpicker('refresh');
												// 除去加载提示
												$('.load_remind').hide();
											}, 'json');
							// 确认按钮添加事件
							$('.breakCase_operation').unbind().click(
									breakecase_modification);
						}, 'json');

	} else if (type == "删除") {
		var formData = new FormData();
		formData.append('info.ajdbxt_info_id', id);
		$
				.confirm({
					title : '确定删除?',
					smoothContent : false,
					content : false,
					autoClose : 'cancelAction|10000',
					buttons : {
						deleteUser : {
							btnClass : 'btn-danger',
							text : '确认',
							action : function() {
								$
										.ajax({
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
												if (data == "success") {
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
$("select#info_department").change(
		function() {
			$.post('/ajdbxt/info/Info_save', $('#breakCase_input form')
					.serialize(), function(Case_data) {
				// alert(Case_data.police[0].police_name);
				// alert($('#info.info_main_police').val(Case_data.police[0].police_name));
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
// 隐藏和显示协办民警2
$("#police_two_td").hide();
$("#police_two_tdd").hide();
$("img#add_police_two")
		.click(
				function() {
					$("#police_two_td")
							.show(
									0,
									function() {
										$
												.post(
														'/ajdbxt/info/Info_save',
														$(
																'#breakCase_input form')
																.serialize(),
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