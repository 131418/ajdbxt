//判断权限
/*window.onload = function() {
 var xmlHttpRequest = new XMLHttpRequest();
 xmlHttpRequest.open("POST", "/ajdbxt/user/User_getPower");
 xmlHttpRequest.send(null);
 xmlHttpRequest.onreadystatechange = function() {
 if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
 var loginRole = JSON.parse(xmlHttpRequest.responseText);
 alert(loginRole.police_power);
 if (loginRole.police_power == "1") {
 var role_one = document.getElementsByClassName("role_one");
 for (var i = 0; i < role_one.length; i++) {
 role_one[i].onclick = function() {
 toastr.error("抱歉，您没有权限！");
 };
 }
 }
 }

 }
 }
 */
// 列表显示
var police_vo = null;
function List_Police_By_Page(pageIndex) {
	var input_PoliceSearchText = document
			.getElementById("input_PoliceSearchText").value;
	var formData = new FormData();
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				police_vo = JSON.parse(xhr.responseText);
				/*
				 * 
				 */

				var new_tr = null;
				var new_td = null;
				var table_police = document.getElementById("table_police");

				/*
				 * 移出除标题以外的所有行
				 */

				var old_tr = document.getElementsByClassName("new_tr");
				var long = old_tr.length;
				for (var i = 0; i < long; i++) {
					old_tr[0].parentNode.removeChild(old_tr[0]);
					// table_police.firstElementChild.removeChild(old_tr[0]);
				}

				/*
				 * 将数据库的数据取出来放到表格里
				 */
				for (var num = 0; num < police_vo.list.length; num++) {
					new_tr = document.createElement("tr");
					new_tr.className = "new_tr";
					new_tr.appendChild(document.createTextNode(''));
					table_police.firstElementChild.appendChild(new_tr);
					/*
					 * 警察id
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.style.display = "none";
					new_td.className = "input_ajdbxt_police_id";
					new_td.innerHTML = police_vo.list[num].ajdbxt_police_id;
					/*
					 * 1. 序号
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = num + 1;
					/*
					 * 2. 警号
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = police_vo.list[num].police_serial_number;
					/*
					 * 3. 姓名
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = police_vo.list[num].police_name;
					/*
					 * 4. 单位
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = police_vo.list[num].police_department;
					/*
					 * 5. 职务
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = police_vo.list[num].police_duty;

					/*
					 * 5. 权限
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					if(police_vo.list[num].police_power=="1"){
						new_td.innerHTML ="浏览" ;
					}else{
						new_td.innerHTML ="管理" ;
					}
					
					/*
					 * 6. 电话号码
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = police_vo.list[num].police_phone_number;

					/*
					 * 7. 操作
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = '<i  id="'
							+ police_vo.list[num].ajdbxt_police_id
							+ '" onClick=updatePolice(this) class="fa fa-pencil-square-o role_one" aria-hidden="true"></i>';
					new_td.style.cursor = "pointer";

					/*
					 * 8. 复选框
					 */
					new_td = document.createElement("td");
					new_td.appendChild(document.createTextNode(''));
					new_tr.appendChild(new_td);
					new_td.innerHTML = '<label class="fancy-checkbox" >'
							+ '<input  type="checkbox" class="checkbox_select">'
							+ '<span></span></label></div>';

				}
				/*
				 * 
				 */
				var i_pulse = document.getElementById("i_pulse");
				i_pulse.style.display = "none";

				// --------------------
				// ------判断角色-------
				var xmlHttpRequest = new XMLHttpRequest();
				xmlHttpRequest.open("POST", "/ajdbxt/user/User_getPower");
				xmlHttpRequest.send(null);
				xmlHttpRequest.onreadystatechange = function() {
					if (xmlHttpRequest.readyState == 4
							&& xmlHttpRequest.status == 200) {
						var loginRole = JSON.parse(xmlHttpRequest.responseText);
						if (loginRole.police_power == "1") {
							var role_one = document
									.getElementsByClassName("role_one");
							for (var i = 0; i < role_one.length; i++) {
								role_one[i].onclick = function() {
									toastr.error("抱歉，您没有权限！");
								};
							}
						}
					}

				}

				/*
				 * * 设置页数 /
				 */
				document.getElementById("span_pageIndex").innerHTML = police_vo.currentPage;// 当前页
				document.getElementById("span_totalPages").innerHTML = police_vo.totalPage;// 总页数
				document.getElementById("span_totalRecords").innerHTML = police_vo.allRow;// 总记录数
				document.getElementById("checkbox_all_select").checked = false;

			} else {
				toastr.error(xhr.status);
			}
		}
	}
	if (pageIndex == null || pageIndex.preventDefault) {
		pageIndex = 1;
	}
	console.log("pageIndex:" + pageIndex);
	formData.append("findPoliceByPageVO.currentPage", pageIndex);
	formData.append("findPoliceByPageVO.police_name", input_PoliceSearchText);
	xhr.open("POST", "/ajdbxt/user/User_queryForPage");
	xhr.send(formData);

}
// --------------------------------------
// --------新增人员---------
function createPolice() {
	var jc = $
			.confirm({
				smoothContent : false,
				columnClass : 'col-md-6 col-md-offset-3',
				title : '新增人员',
				content : '<table class="table table-hover" ><tbody>'
						+ '<tr><td>警号:</td><td><input type="text" id="input_police_serial_number" class="form-control" /></td></tr>'
						+ '<tr><td>密码:</td><td><input type="text" id="input_police_password" class="form-control" /></td></tr>'
						+ '<tr><td>姓名:</td><td><input type="text" id="input_police_name" class="form-control" /></td></tr>'
						+ '<tr><td>单位:</td><td><select id="input_police_department" class="form-control" >'
						+ '<option  selected="selected" value="">请选择</option>'
						+ '<option  value="侦查大队">侦查大队</option>'
						+ '</select></td></tr>'
						+ '<tr><td>职务:</td><td><input type="text" id="input_police_duty" class="form-control" /></td></tr>'
						+ '<tr><td>权限:</td><td><select id="input_police_power" class="form-control" >'
						+ '<option selected="selected" value="">请选择</option>'
						+ '<option value="1">浏览</option>'
						+ '<option value="2">管理</option>'
						+ '</select></td></tr>'
						+ '<tr><td>手机号码:</td><td><input type="text" id="input_police_phone_number" class="form-control" /></td></tr>'
						+ '</tbody></table>',
				buttons : {
					创建 : function() {
						// 警号
						var input_police_serial_number = document
								.getElementById("input_police_serial_number").value;
						if (input_police_serial_number == "") {
							toastr.error("警号不能为空！");
							return false;
						}
						// 密码
						var input_police_password = document
								.getElementById("input_police_password").value;
						if (input_police_password == "") {
							toastr.error("密码不能为空！");
							return false;
						}
						// 姓名
						var input_police_name = document
								.getElementById("input_police_name").value;
						if (input_police_name == "") {
							toastr.error("姓名不能为空！");
							return false;
						}
						// 单位
						var input_police_department = document
								.getElementById("input_police_department").value;
						if (input_police_department == "") {
							toastr.error("请选择单位！");
							return false;
						}
						// 职务
						var input_police_duty = document
								.getElementById("input_police_duty").value;
						if (input_police_duty == "") {
							toastr.error("职务不能为空！");
							return false;
						}
						// 角色
						var input_police_power = document
								.getElementById("input_police_power").value;
						if (input_police_power == "") {
							toastr.error("请选择权限！");
							return false;
						}
						// 手机号码
						var input_police_phone_number = document
								.getElementById("input_police_phone_number").value;
						if (input_police_phone_number == "") {
							toastr.error("手机号码不能为空！");
							return false;
						}
						var formData = new FormData();

						var xhr = false;
						xhr = new XMLHttpRequest();
						xhr.onreadystatechange = function() {
							if (xhr.readyState == 4) {
								if (xhr.status == 200) {
									/*
									 * success 添加成功 failed 用户已存在 error 添加失败
									 */
									if (xhr.responseText == "success") {
										toastr.success("添加成功！");
										List_Police_By_Page(1);
									} else if (xhr.responseText == "failed") {
										toastr.error("用户已存在！");
										return false;
									} else {
										toastr.error("添加失败！");
										return false;
									}

								} else {
									toastr.error(xhr.status);
								}
							}
						}

						// 警号
						formData.append("ajdbxt_police.police_serial_number",
								input_police_serial_number);
						// 密码
						formData.append("ajdbxt_police.police_password",
								input_police_password);
						// 姓名
						formData.append("ajdbxt_police.police_name",
								input_police_name);
						// 单位
						formData.append("ajdbxt_police.police_department",
								input_police_department);
						// 职位
						formData.append("ajdbxt_police.police_duty",
								input_police_duty);
						// 角色
						formData.append("ajdbxt_police.police_power",
								input_police_power);
						// 手机号码
						formData.append("ajdbxt_police.police_phone_number",
								input_police_phone_number);

						xhr.open("POST", "/ajdbxt/user/User_addPolice");
						xhr.send(formData);

					},
					取消 : function() {

					}
				},
				onContentReady : function() {

					$
							.post(
									'/ajdbxt/user/User_findDepartmentByPage',
									function(Department_data) {
										// 所有案件循环
										var option = '';
										for (var len = 0; len < Department_data.list.length; len++) {
											option += '<option ';
											option += ' value="'
													+ Department_data.list[len].department_name
													+ '">'
													+ Department_data.list[len].department_name
													+ '</option>';
										}
										$('#input_police_department').html(
												'<option selected="selected" value="">请选择</option>'
														+ option);
									}, 'json');

				}

			});

}

// -----------------------------
// --------删除人员--------------
function deletePolice() {

	$.confirm({
		smoothContent : false,
		title : '警告！删除人员信息',
		content : '此操作将删除所有所选的人员信息',
		type : 'red',
		autoClose : '取消|5000',// 自动关闭
		buttons : {
			删除 : {
				btnClass : 'btn-red',
				action : function() {
					var xhr = false;
					xhr = new XMLHttpRequest();
					xhr.onreadystatechange = function() {
						if (xhr.readyState == 4) {
							if (xhr.status == 200) {
								if (xhr.responseText == "success") {
									toastr.success("删除成功");
									List_Police_By_Page(1);
								} else {
									toastr.error("删除失败");
								}
							} else {
								toastr.error(xhr.status);
							}
						}
					}
					var checkbox_select = document
							.getElementsByClassName("checkbox_select");
					var input_ajdbxt_police_id = document
							.getElementsByClassName("input_ajdbxt_police_id");

					var formData = new FormData();
					for (var num = 0; num < checkbox_select.length; num++) {
						if (checkbox_select[num].checked) {
							formData.append("ids",
									input_ajdbxt_police_id[num].innerHTML);

						}
					}
					xhr.open("POST", "/ajdbxt/user/User_batchDelete");
					xhr.send(formData);
				}
			},
			取消 : function() {
			}
		}
	});

}

// --------------------------------------
// --------修改人员---------
function updatePolice(button) {
	var jc = $
			.confirm({
				smoothContent : false,
				columnClass : 'col-md-6 col-md-offset-3',
				title : '人员信息详情',
				content : '<table class="table table-hover"><tbody>'
						+ '<tr style="display:none;"><td>Id:</td><td><input type="text" id="input_ajdbxt_police_id" class="form-control" /></td></tr>'
						+ '<tr><td>警号:</td><td><input type="text" id="input_police_serial_number" class="form-control" /></td></tr>'
						+ '<tr><td>姓名:</td><td><input type="text" id="input_police_name" class="form-control" /></td></tr>'
						+ '<tr><td>单位:</td><td><select id="input_police_department" class="form-control" >'
						+ '</select></td></tr>'
						+ '<tr><td>职务:</td><td><input type="text" id="input_police_duty" class="form-control" /></td></tr>'
						+ '<tr><td>权限:</td><td><select id="input_police_power" class="form-control" >'
						+ '<option selected="selected" value="">请选择</option>'
						+ '<option value="1">浏览</option>'
						+ '<option value="2">管理</option>'
						+ '</select></td></tr>'
						+ '<tr><td>手机号码:</td><td><input type="text" id="input_police_phone_number" class="form-control" /></td></tr>'
						+ '</tbody></table>',
				buttons : {
					修改 : function() {
						var input_ajdbxt_police_id = document
								.getElementById("input_ajdbxt_police_id").value;
						// 警号
						var input_police_serial_number = document
								.getElementById("input_police_serial_number").value;
						if (input_police_serial_number == "") {
							toastr.error("警号不能为空！");
							return false;
						}
						// 姓名
						var input_police_name = document
								.getElementById("input_police_name").value;
						if (input_police_name == "") {
							toastr.error("姓名不能为空！");
							return false;
						}
						// 单位
						var input_police_department = document
								.getElementById("input_police_department").value;
						if (input_police_department == "") {
							toastr.error("请选择单位！");
							return false;
						}
						// 职务
						var input_police_duty = document
								.getElementById("input_police_duty").value;
						if (input_police_duty == "") {
							toastr.error("职务不能为空！");
							return false;
						}
						// 角色
						var input_police_power = document
								.getElementById("input_police_power").value;
						if (input_police_power == "") {
							toastr.error("请选择权限！");
							return false;
						}
						// 手机号码
						var input_police_phone_number = document
								.getElementById("input_police_phone_number").value;
						if (input_police_phone_number == "") {
							toastr.error("手机号码不能为空！");
							return false;
						}
						var formData = new FormData();

						var xhr = false;
						xhr = new XMLHttpRequest();
						xhr.onreadystatechange = function() {
							if (xhr.readyState == 4) {
								if (xhr.status == 200) {
									if (xhr.responseText == "success") {
										toastr.success("修改成功！");
										List_Police_By_Page(1);
									} else {
										toastr.error("修改失败！");
										return false;
									}

								} else {
									toastr.error(xhr.status);
								}
							}
						}
						// id
						formData.append("ajdbxt_police.ajdbxt_police_id",
								input_ajdbxt_police_id);
						// 警号
						formData.append("ajdbxt_police.police_serial_number",
								input_police_serial_number);
						// 姓名
						formData.append("ajdbxt_police.police_name",
								input_police_name);
						// 单位
						formData.append("ajdbxt_police.police_department",
								input_police_department);
						// 职位
						formData.append("ajdbxt_police.police_duty",
								input_police_duty);
						// 角色
						formData.append("ajdbxt_police.police_power",
								input_police_power);
						// 手机号码
						formData.append("ajdbxt_police.police_phone_number",
								input_police_phone_number);

						xhr.open("POST", "/ajdbxt/user/User_updatePolice");
						xhr.send(formData);

					},
					取消 : function() {

					}
				},
				onContentReady : function() {

					var button_id = button.id;
					console.log("button_id:" + button_id);
					for (var num = 0; num < police_vo.list.length; num++) {
						var ajdbxt_police_id = police_vo.list[num].ajdbxt_police_id;
						if (ajdbxt_police_id == button_id) {
							console.log("ajdbxt_police_id == button_id:"
									+ ajdbxt_police_id == button_id);

							// Id
							var input_ajdbxt_police_id = document
									.getElementById("input_ajdbxt_police_id");
							input_ajdbxt_police_id.value = ajdbxt_police_id;

							// 警号
							var input_police_serial_number = document
									.getElementById("input_police_serial_number");
							input_police_serial_number.value = police_vo.list[num].police_serial_number;
							console.log("input_police_serial_number:"
									+ input_police_serial_number);

							// 姓名
							var input_police_name = document
									.getElementById("input_police_name");
							input_police_name.value = police_vo.list[num].police_name;

							// 单位
							/*
							 * var input_police_department = document
							 * .getElementById("input_police_department");
							 * input_police_department.value =
							 * police_vo.list[num].police_department;
							 */
							var deparment = police_vo.list[num].police_department;
							$
									.post(
											'/ajdbxt/user/User_findDepartmentByPage',
											function(Department_data) {
												// 所有案件循环
												var option = '';
												for (var len = 0; len < Department_data.list.length; len++) {
													option += '<option ';
													console
															.log(len
																	+ ":"
																	+ Department_data.list[len].department_name);
													if (Department_data.list[len].department_name == deparment) {
														option += 'selected';
													}
													option += ' value="'
															+ Department_data.list[len].department_name
															+ '">'
															+ Department_data.list[len].department_name
															+ '</option>';
												}
												$('#input_police_department')
														.html(
																'<option selected="selected" value="">请选择</option>'
																		+ option);
											}, 'json');

							// 职务
							var input_police_duty = document
									.getElementById("input_police_duty");
							input_police_duty.value = police_vo.list[num].police_duty;

							// 角色
							var input_police_power = document
									.getElementById("input_police_power");
							input_police_power.value = police_vo.list[num].police_power;

							// 手机号码
							var input_police_phone_number = document
									.getElementById("input_police_phone_number");
							input_police_phone_number.value = police_vo.list[num].police_phone_number;
							break;
						}
					}
				}
			});
}

/*
 * 判断页数
 */
function flip(flipPage) {
	switch (flipPage) {
	/* 首页 */
	case 1: {
		List_Police_By_Page(1)
		break;
	}
		/* 上一页 */
	case 2: {
		if (police_vo.currentPage - 1 == 0) {
			toastr.warning("已经是第一页了");
		} else {
			List_Police_By_Page(police_vo.currentPage - 1);
		}
		break;
	}
		/* 下一页 */
	case 3: {
		if (police_vo.currentPage == police_vo.totalPage) {
			toastr.warning("已经是最后一页了");
		} else {
			List_Police_By_Page(police_vo.currentPage + 1);
		}
		break;
	}
		/* 尾页 */
	case 4: {
		List_Police_By_Page(police_vo.totalPage);

		break;
	}

	}
}