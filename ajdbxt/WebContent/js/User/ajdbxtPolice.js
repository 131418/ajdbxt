var police_vo = null;
function List_Police_By_Page(pageIndex) {
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
				var table_Police = document.getElementById("table_Police");

				/*
				 * 移出除标题以外的所有行
				 */

				var old_tr = document.getElementsByClassName("new_tr");
				var long = old_tr.length;
				for (var i = 0; i < long; i++) {
					old_tr[0].parentNode.removeChild(old_tr[0]);
					// table_Police.firstElementChild.removeChild(old_tr[0]);
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
					new_td.className = "tr_police_id";
					new_td.innerHTML = police_vo.list[num].ajdbxtPoliceId;
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
					new_td.innerHTML = police_vo.list[num].policeSerialNumber;
					/*
					 * 3. 姓名
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = police_vo.list[num].policeName;
					/*
					 * 4. 单位
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = police_vo.list[num].policeDepartment;
					/*
					 * 5. 职务
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = police_vo.list[num].policeDuty;
					/*
					 * 6. 电话号码
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = police_vo.list[num].policePhoneNumber;

					/*
					 * 7. 操作
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = '<i  id="'
							+ police_vo.list[num].ajdbxtPoliceId
							+ '" onClick=updatePolice(this) class="fa fa-pencil-square-o" aria-hidden="true"></i>';
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
	formData.append("police_vo.currentPage", pageIndex);
	xhr.open("POST", "/ajdbxt/user/User_queryForPage");
	xhr.send(formData);

}

// --------------------------------------
// --------新增人员---------
function createPolice() {
	var jc = $
			.confirm({
				columnClass : 'col-md-6 col-md-offset-3',
				title : '新增人员',
				content : '<table class="table table-hover" style="text-align:center;"><tbody>'
						+ '<tr><td>警号:</td><td><input type="text" id="input_policeNumber" class="form-control" /></td></tr>'
						+ '<tr><td>密码:</td><td><input type="text" id="input_policePassword" class="form-control" /></td></tr>'
						+ '<tr><td>姓名:</td><td><input type="text" id="input_policeName" class="form-control" /></td></tr>'
						+ '<tr><td>单位:</td><td><select id="input_policeDepartment" class="form-control" >'
						+ '<option>1</option>'
						+ '<option>2</option>'
						+ '<option>3</option>'
						+ '</select></td></tr>'
						+ '<tr><td>职务:</td><td><input type="text" id="input_policeDuty" class="form-control" /></td></tr>'
						+ '<tr><td>角色:</td><td><input type="text" id="input_policePower" class="form-control" /></td></tr>'
						+ '<tr><td>手机号码:</td><td><input type="text" id="input_policePhone" class="form-control" /></td></tr>'
						+ '</tbody></table>',
				buttons : {
					创建 : function() {
						// 警号
						var input_policeNumber = document
								.getElementById("input_policeNumber").value;
						if (input_policeNumber == "") {
							toastr.error("警号不能为空！");
							return false;
						}
						// 密码
						var input_policePassword = document
								.getElementById("input_policePassword").value;
						if (input_policePassword == "") {
							toastr.error("密码不能为空！");
							return false;
						}
						// 姓名
						var input_policeName = document
								.getElementById("input_policeName").value;
						if (input_policeName == "") {
							toastr.error("姓名不能为空！");
							return false;
						}
						// 单位
						var input_policeDepartment = document
								.getElementById("input_policeDepartment").value;
						if (input_policeDepartment == "") {
							toastr.error("单位不能为空！");
							return false;
						}
						// 职务
						var input_policeDuty = document
								.getElementById("input_policeDuty").value;
						if (input_policeDuty == "") {
							toastr.error("职务不能为空！");
							return false;
						}
						// 角色
						var input_policeDuty = document
								.getElementById("input_policePower").value;
						if (input_policeDuty == "") {
							toastr.error("角色不能为空！");
							return false;
						}
						// 手机号码
						var input_policePhone = document
								.getElementById("input_policePhone").value;
						if (input_policePhone == "") {
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
						formData.append("ajdbxt_police.policeSerialNumber",
								input_policeNumber);
						// 密码
						formData.append("ajdbxt_police.policePassword",
								input_policePassword);
						// 姓名
						formData.append("ajdbxt_police.policeName",
								input_policeName);
						// 单位
						formData.append("ajdbxt_police.policeDepartment",
								input_policeDepartment);
						// 职位
						formData.append("ajdbxt_police.policeDuty",
								input_policeDuty);
						// 角色
						formData.append("ajdbxt_police.policePower",
								input_policeDuty);
						// 手机号码
						formData.append("ajdbxt_police.policePhoneNumber",
								input_policePhone);

						xhr.open("POST", "/ajdbxt/user/User_addPolice");
						xhr.send(formData);

					},
					取消 : function() {

					}
				}
			});

}

// -----------------------------
// --------删除人员--------------
function deletePolice() {

	$.confirm({
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
					var tr_police_id = document
							.getElementsByClassName("tr_police_id");

					var formData = new FormData();
					for (var num = 0; num < checkbox_select.length; num++) {
						if (checkbox_select[num].checked) {
							formData.append("listDeleteDNAID",
									tr_police_id[num].innerHTML);

						}
					}
					xhr.open("POST", "/ajdbxt/user/User_deletePolice");
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
				columnClass : 'col-md-6 col-md-offset-3',
				title : '人员信息详情',
				content : '<table class="table table-hover"><tbody>'
						+ '<tr style="display:none;"><td>Id:</td><td><input type="text" id="tr_police_id" class="form-control" /></td></tr>'
						+ '<tr><td>警号:</td><td><input type="text" id="input_policeNumber" class="form-control" /></td></tr>'
						+ '<tr><td>密码:</td><td><input type="text" id="input_policePassword" class="form-control" /></td></tr>'
						+ '<tr><td>姓名:</td><td><input type="text" id="input_policeName" class="form-control" /></td></tr>'
						+ '<tr><td>单位:</td><td><select id="input_policeDepartment" class="form-control" >'
						+ '</select></td></tr>'
						+ '<tr><td>职务:</td><td><input type="text" id="input_policeDuty" class="form-control" /></td></tr>'
						+ '<tr><td>角色:</td><td><input type="text" id="input_policePower" class="form-control" /></td></tr>'
						+ '<tr><td>手机号码:</td><td><input type="text" id="input_policePhone" class="form-control" /></td></tr>'
						+ '</tbody></table>',
				buttons : {
					修改 : function() {
						var tr_police_id = document
								.getElementById("tr_police_id").value;
						// 警号
						var input_policeNumber = document
								.getElementById("input_policeNumber").value;
						if (input_policeNumber == "") {
							toastr.error("警号不能为空！");
							return false;
						}
						// 密码
						var input_policePassword = document
								.getElementById("input_policePassword").value;
						if (input_policePassword == "") {
							toastr.error("密码不能为空！");
							return false;
						}
						// 姓名
						var input_policeName = document
								.getElementById("input_policeName").value;
						if (input_policeName == "") {
							toastr.error("姓名不能为空！");
							return false;
						}
						// 单位
						var input_policeDepartment = document
								.getElementById("input_policeDepartment").value;
						if (input_policeDepartment == "") {
							toastr.error("单位不能为空！");
							return false;
						}
						// 职务
						var input_policeDuty = document
								.getElementById("input_policeDuty").value;
						if (input_policeDuty == "") {
							toastr.error("职务不能为空！");
							return false;
						}
						// 角色
						var input_policeDuty = document
								.getElementById("input_policePower").value;
						if (input_policeDuty == "") {
							toastr.error("角色不能为空！");
							return false;
						}
						// 手机号码
						var input_policePhone = document
								.getElementById("input_policePhone").value;
						if (input_policePhone == "") {
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

						// 警号
						formData.append("ajdbxt_police.policeSerialNumber",
								input_policeNumber);
						// 密码
						formData.append("ajdbxt_police.policePassword",
								input_policePassword);
						// 姓名
						formData.append("ajdbxt_police.policeName",
								input_policeName);
						// 单位
						formData.append("ajdbxt_police.policeDepartment",
								input_policeDepartment);
						// 职位
						formData.append("ajdbxt_police.policeDuty",
								input_policeDuty);
						// 角色
						formData.append("ajdbxt_police.policePower",
								input_policeDuty);
						// 手机号码
						formData.append("ajdbxt_police.policePhoneNumber",
								input_policePhone);

						xhr.open("POST", "/ajdbxt/user/User_updatePolice");
						xhr.send(formData);

					},
					取消 : function() {

					}
				},
				onContentReady : function() {
					var button_id = button.id;
					for (var num = 0; num < police_vo.list.length; num++) {
						var dna_id = police_vo.list[num].xsjsglxt_dna_id;
						if (dna_id == button_id) {

							// Id
							var tr_police_id = document
									.getElementById("tr_police_id").value;
							tr_police_id = police_vo.list[num].ajdbxt_police_id;
							// 警号
							var input_policeNumber = document
									.getElementById("input_policeNumber").value;
							input_policeNumber = police_vo.list[num].police_serial_number;
							// 密码
							var input_policePassword = document
									.getElementById("input_policePassword").value;
							input_policePassword = police_vo.list[num].police_password;
							// 姓名
							var input_policeName = document
									.getElementById("input_policeName").value;
							input_policeName = police_vo.list[num].police_name;
							// 单位
							var input_policeDepartment = document
									.getElementById("input_policeDepartment").value;
							input_policeDepartment = police_vo.list[num].police_department;
							// 职务
							var input_policeDuty = document
									.getElementById("input_policeDuty").value;
							input_policeDuty = police_vo.list[num].police_duty;
							// 角色
							var input_policeDuty = document
									.getElementById("input_policePower").value;
							input_policeDuty = police_vo.list[num].police_power;
							// 手机号码
							var input_policePhone = document
									.getElementById("input_policePhone").value;
							input_policePhone = police_vo.list[num].police_phone_number;

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
			List_Police_By_Page(police_vo.pageIndex - 1);
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