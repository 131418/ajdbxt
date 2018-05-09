/**
 * 
 */
var xhr;
var total_vo = null;
var a_value = null;
$(".a_total_type").bind("click", function() {
	total_info_type = this.innerHTML;
	mui('.mui-popover').popover('hide');
	$("#total_info_type").html(total_info_type);
	a_value = this.innerText;
	List_Total_Department(a_value,1);
	$(".input_date").bind("change", function() {
		List_Total_Department(a_value,1);
	});

});
function List_Total_Department(e,pageIndex) {
	var e_str="";
	if(e=="按刑事案件数统计"){
		e_str="2";
	}
	else if(e=="按行政案件数统计"){
		e_str="1";
	}
	else{
		e_str="0";
	}  
	getXMLHttp();
	var select_start_time = document.getElementById("select_start_time").value;
	var select_stop_time = document.getElementById("select_stop_time").value;
	var total_info_type = null;

	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				total_vo = JSON.parse(xhr.responseText);
				var new_li = null;
				var new_a = null;
				var span_num = null;// 部门序号
				var span_department = null;// 办案单位
				var span_average = null;// 平均分

				// -----折叠信息-----
				var new_div = null;
				var new_form = null;// 折叠信息表单

				// 办案单位
				var div_department = null;
				var label_department = null;
				var input_department = null;

				// 行政案件
				var div_adminCase = null;
				var label_adminCase = null;
				var input_adminCase = null;

				// 刑事案件
				var div_criminalCase = null;
				var label_criminalCase = null;
				var input_criminalCase = null;

				// 总案件数
				var div_sumCase = null;
				var label_sumCase = null;
				var input_sumCase = null;

				// 平均分
				var div_averageScore = null;
				var label_averageScore = null;
				var input_averageScore = null;

				// 获得ul的id
				var ul_total_department = document
						.getElementById("ul_total_department");


					/*
					 * 移出除标题以外的所有行
					 */

					var old_li = document.getElementsByClassName("new_li");
					var long = old_li.length;
					for (var i = 0; i < long; i++) {
						old_li[0].parentNode.removeChild(old_li[0]);
					}

					/*
					 * 将数据库的数据取出来放到表格里
					 */
					for (var num = 0; num < total_vo.statisticDepartmentCaseNumDTO.length; num++) {
						new_li = document.createElement("li");
						new_li.className = "mui-table-view-cell mui-collapse new_li";
						new_li.appendChild(document.createTextNode(''));
						ul_total_department.appendChild(new_li);
						/*
						 * 0. a链接
						 */
						new_a = document.createElement("a");
						new_a.className = "mui-navigate-right";
						new_a.appendChild(document.createTextNode(''));
						new_li.appendChild(new_a);

						/*
						 * 1. 部门序号
						 */
						span_num = document.createElement("span");
						span_num.innerHTML = num + 1;
						span_num.style.margin = " 0 10px 0 0";
						span_num.style.padding = " 0 10px";
						new_a.appendChild(span_num);

						/*
						 * 2.办案单位
						 */
						span_department = document.createElement("span");
						new_a.appendChild(span_department);
						span_department.innerHTML = total_vo.statisticDepartmentCaseNumDTO[num].department.department_name;

						/*
						 * 3.判断按平均分还是刑事案件或行政案件统计
						 */
						span_checkTotal = document.createElement("span");
						span_checkTotal.className = "mui-badge mui-badge-blue";
						new_a.appendChild(span_checkTotal);
						
						if(document.getElementById("total_info_type").innerText=="按行政案件数统计"){
							span_checkTotal.innerHTML = total_vo.statisticDepartmentCaseNumDTO[num].adminCase;
						}else if(document.getElementById("total_info_type").innerText=="按刑事案件数统计"){
							span_checkTotal.innerHTML = total_vo.statisticDepartmentCaseNumDTO[num].criminalCase;
						}else{
							span_checkTotal.innerHTML = total_vo.statisticDepartmentCaseNumDTO[num].averageScore;
						}
						

						/*
						 * 4. 折叠信息
						 */
						new_div = document.createElement("div");// 折叠信息div
						new_div.className = "mui-collapse-content mui-h5";
						new_div.style.text_indent = "25px";
						new_div.style.padding = "0 20px 0 20px";
						new_div.style.textIndent = "25px";

						new_form = document.createElement("form");
						new_form.className = "mui-input-group mui-h5";
						
						// 平均分
						div_averageScore = document.createElement("div");
						div_averageScore.className = "mui-input-row";
						label_averageScore = document.createElement("label");
						label_averageScore.innerHTML = "平均分";
						label_averageScore.style.padding = "11px 0px";
						input_averageScore = document.createElement("input");
						input_averageScore.className = "mui-input-clear  mui-h5";

						input_averageScore.value = total_vo.statisticDepartmentCaseNumDTO[num].averageScore;
						input_averageScore.type = "text";
						input_averageScore.disabled = "disabled";
						input_averageScore.style.paddingLeft = "20px";
						div_averageScore.appendChild(label_averageScore);
						div_averageScore.appendChild(input_averageScore);
						new_form.appendChild(div_averageScore);

						// 行政案件
						div_adminCase = document.createElement("div");
						div_adminCase.className = "mui-input-row";
						label_adminCase = document.createElement("label");
						label_adminCase.innerHTML = "行政案件";
						label_adminCase.style.padding = "11px 0px";
						input_adminCase = document.createElement("input");
						input_adminCase.className = "mui-input-clear  mui-h5";

						input_adminCase.value = total_vo.statisticDepartmentCaseNumDTO[num].adminCase;
						input_adminCase.type = "text";
						input_adminCase.disabled = "disabled";
						input_adminCase.style.paddingLeft = "20px";
						div_adminCase.appendChild(label_adminCase);
						div_adminCase.appendChild(input_adminCase);
						new_form.appendChild(div_adminCase);

						// 刑事案件
						div_criminalCase = document.createElement("div");
						div_criminalCase.className = "mui-input-row";
						label_criminalCase = document.createElement("label");
						label_criminalCase.innerHTML = "刑事案件";
						label_criminalCase.style.padding = "11px 0px";
						input_criminalCase = document.createElement("input");
						input_criminalCase.className = "mui-input-clear  mui-h5";

						input_criminalCase.value = total_vo.statisticDepartmentCaseNumDTO[num].criminalCase;
						input_criminalCase.type = "text";
						input_criminalCase.disabled = "disabled";
						input_criminalCase.style.paddingLeft = "20px";
						div_criminalCase.appendChild(label_criminalCase);
						div_criminalCase.appendChild(input_criminalCase);
						new_form.appendChild(div_criminalCase);

						// 总案件数
						div_sumCase = document.createElement("div");
						div_sumCase.className = "mui-input-row";
						label_sumCase = document.createElement("label");
						label_sumCase.innerHTML = "总案件数";
						label_sumCase.style.padding = "11px 0px";
						input_sumCase = document.createElement("input");
						input_sumCase.className = "mui-input-clear  mui-h5";

						input_sumCase.value = total_vo.statisticDepartmentCaseNumDTO[num].totalCase;
						input_sumCase.type = "text";
						input_sumCase.disabled = "disabled";
						input_sumCase.style.paddingLeft = "20px";
						div_sumCase.appendChild(label_sumCase);
						div_sumCase.appendChild(input_sumCase);
						new_form.appendChild(div_sumCase);

						// 办案单位
						div_department = document.createElement("div");
						div_department.className = "mui-input-row mui-h5";
						label_department = document.createElement("label");
						label_department.innerHTML = "办案单位";
						label_department.style.padding = "11px 0px";
						input_department = document.createElement("input");
						input_department.className = "mui-input-clear  mui-h5";

						input_department.value = total_vo.statisticDepartmentCaseNumDTO[num].department.department_name;
						input_department.type = "text";
						input_department.disabled = "disabled";
						input_department.style.paddingLeft = "20px";
						div_department.appendChild(label_department);
						div_department.appendChild(input_department);
						new_form.appendChild(div_department);


						$("label").css("padding", "11px 0px");
						$("label").css("text-align", "center");
						$("input").css("padding", "0 30px");
						$("input").css("text-align", "right");
						new_div.appendChild(new_form);
						new_li.appendChild(new_div);
					}
			}
		}
	}
	xhr
			.open("POST", "/ajdbxt/total/Total_getListDeparmentCaseStatistics",
					true);
	var formData = new FormData();
	formData.append("departmentStatisticVo.currePage", pageIndex);
	formData.append("departmentStatisticVo.start_time", select_start_time);
	formData.append("departmentStatisticVo.stop_time", select_stop_time);
	formData.append("departmentStatisticVo.orderString", e_str);
	xhr.send(formData);
}

function getXMLHttp() {
	if (window.XMLHttpRequest) {
		// IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
		xhr = new XMLHttpRequest();
	} else {
		// IE6, IE5 浏览器执行代码
		xhr = new ActiveXObject("Microsoft.XMLHTTP");
	}
}
