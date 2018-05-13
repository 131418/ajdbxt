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
	List_Total_User(a_value,1);
	$(".input_date").bind("change", function() {
		List_Total_User(a_value,1);
	});

});
function List_Total_User(e,pageIndex) {
	var e_str="";
	if(e=="按主办行政案件数统计"){
		e_str="1";
	}
	else if(e=="按主办刑事案件数统计"){
		e_str="2";
	}
	else if(e=="按协办行政案件数统计"){
		e_str="3";
	}
	else if(e=="按协办刑事案件数统计"){
		e_str="4";
	}	
	else{
		e_str="0";
	}
	getXMLHttp();
	var select_start_time = document.getElementById("select_start_time").innerText;
	var select_stop_time = document.getElementById("select_stop_time").innerText;
	var input_PoliceSearchText=document.getElementById("input_PoliceSearchText").value;
	var select_case_department_id = document.getElementById("select_case_department_id").innerText;
	var total_info_type = null;

	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				total_vo = JSON.parse(xhr.responseText);
				var new_li = null;
				var new_a = null;
				var span_num = null;// 部门序号
				var span_policeName = null;// 人员
				var span_checkTotal = null;// 判断根据什么统计

				// -----折叠信息-----
				var new_div = null;
				var new_form = null;// 折叠信息表单

				// 办案单位
				var div_department = null;
				var label_department = null;
				var input_department = null;
				
				// 人员
				var div_policeNme = null;
				var label_policeNme = null;
				var input_policeNme = null;


				// 主办行政案件数
				var div_MainadminCase = null;
				var label_MainadminCase = null;
				var input_MainadminCase = null;

				// 主办刑事案件数
				var div_MaincriminalCase = null;
				var label_MaincriminalCase = null;
				var input_MaincriminalCase = null;
				
				// 协办行政案件数
				var div_InsisadminCase = null;
				var label_InsisadminCase = null;
				var input_InsisadminCase = null;

				// 协办刑事案件数
				var div_InsiscriminalCase = null;
				var label_InsiscriminalCase = null;
				var input_InsiscriminalCase = null;

				// 平均分
				var div_averageScore = null;
				var label_averageScore = null;
				var input_averageScore = null;

				// 获得ul的id
				var ul_total_user = document.getElementById("ul_total_user");

				/*
				 * 移出除标题以外的所有行
				 */

				var old_li = document.getElementsByClassName("new_li");
				var long = old_li.length;
				for (var i = 0; i < long; i++) {
					old_li[0].parentNode.removeChild(old_li[0]);
				}

				var str_page_row = null;
				console.log("total_vo.totalRecords:" +total_vo.totalRecords);
				if (total_vo.totalRecords > 0) {

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
					for (var num = 0; num < total_vo.statisticPoliceCaseDto.length; num++) {
						new_li = document.createElement("li");
						new_li.className = "mui-table-view-cell mui-collapse new_li";
						new_li.appendChild(document.createTextNode(''));
						ul_total_user.appendChild(new_li);
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
						 * 2.人员
						 */
						span_policeName = document.createElement("span");
						new_a.appendChild(span_policeName);
						span_policeName.innerHTML =total_vo.statisticPoliceCaseDto[num].police.police_name;
						var input_police=span_policeName.innerText;

						/*
						 * 3.判断按平均分还是主办行政或刑事案件还是协办行政或刑事案件统计
						 */
						span_checkTotal = document.createElement("span");
						span_checkTotal.className = "mui-badge mui-badge-blue";
						new_a.appendChild(span_checkTotal);
						
						if(document.getElementById("total_info_type").innerText=="按协办刑事案件数统计"){
							span_checkTotal.innerHTML = total_vo.statisticPoliceCaseDto[num].crimalAsistCase;
						}else if(document.getElementById("total_info_type").innerText=="按协办行政案件数统计"){
							span_checkTotal.innerHTML = total_vo.statisticPoliceCaseDto[num].adminAsistCase;
						}else if(document.getElementById("total_info_type").innerText=="按主办刑事案件数统计"){
							span_checkTotal.innerHTML = total_vo.statisticPoliceCaseDto[num].crimalMainCase;
						}else if(document.getElementById("total_info_type").innerText=="按主办行政案件数统计"){
							span_checkTotal.innerHTML = total_vo.statisticPoliceCaseDto[num].adminMianCase;
						}else{
							span_checkTotal.innerHTML = total_vo.statisticPoliceCaseDto[num].score_mian;
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
						
						// 人员
						div_policeNme = document.createElement("div");
						div_policeNme.className = "mui-input-row";
						label_policeNme = document.createElement("label");
						label_policeNme.innerHTML = "人员";
						label_policeNme.style.padding = "11px 0px";
						input_policeNme = document.createElement("input");
						input_policeNme.className = "mui-input-clear  mui-h5";

						input_policeNme.value = input_police;
						input_policeNme.type = "text";
						input_policeNme.disabled = "disabled";
						input_policeNme.style.paddingLeft = "20px";
						div_policeNme.appendChild(label_policeNme);
						div_policeNme.appendChild(input_policeNme);
						new_form.appendChild(div_policeNme);
						
						// 平均分
						div_averageScore = document.createElement("div");
						div_averageScore.className = "mui-input-row";
						label_averageScore = document.createElement("label");
						label_averageScore.innerHTML = "平均分";
						label_averageScore.style.padding = "11px 0px";
						input_averageScore = document.createElement("input");
						input_averageScore.className = "mui-input-clear  mui-h5";

						input_averageScore.value = total_vo.statisticPoliceCaseDto[num].score_mian;
						input_averageScore.type = "text";
						input_averageScore.disabled = "disabled";
						input_averageScore.style.paddingLeft = "20px";
						div_averageScore.appendChild(label_averageScore);
						div_averageScore.appendChild(input_averageScore);
						new_form.appendChild(div_averageScore);
						
						// 主办行政案件数
						div_MainadminCase = document.createElement("div");
						div_MainadminCase.className = "mui-input-row";
						label_MainadminCase = document.createElement("label");
						label_MainadminCase.innerHTML = "行政案件";
						label_MainadminCase.style.padding = "11px 0px";
						input_MainadminCase = document.createElement("input");
						input_MainadminCase.className = "mui-input-clear  mui-h5";

						input_MainadminCase.value =  total_vo.statisticPoliceCaseDto[num].adminMianCase;
						input_MainadminCase.type = "text";
						input_MainadminCase.disabled = "disabled";
						input_MainadminCase.style.paddingLeft = "20px";
						div_MainadminCase.appendChild(label_MainadminCase);
						div_MainadminCase.appendChild(input_MainadminCase);
						new_form.appendChild(div_MainadminCase);
						
						// 主办刑事案件数
						div_MaincriminalCase = document.createElement("div");
						div_MaincriminalCase.className = "mui-input-row";
						label_MaincriminalCase = document.createElement("label");
						label_MaincriminalCase.innerHTML = "刑事案件";
						label_MaincriminalCase.style.padding = "11px 0px";
						input_MaincriminalCase = document.createElement("input");
						input_MaincriminalCase.className = "mui-input-clear  mui-h5";

						input_MaincriminalCase.value = total_vo.statisticPoliceCaseDto[num].crimalMainCase;
						input_MaincriminalCase.type = "text";
						input_MaincriminalCase.disabled = "disabled";
						input_MaincriminalCase.style.paddingLeft = "20px";
						div_MaincriminalCase.appendChild(label_MaincriminalCase);
						div_MaincriminalCase.appendChild(input_MaincriminalCase);
						new_form.appendChild(div_MaincriminalCase);
						
						// 协办行政案件数
						div_InsisadminCase = document.createElement("div");
						div_InsisadminCase.className = "mui-input-row";
						label_InsisadminCase = document.createElement("label");
						label_InsisadminCase.innerHTML = "行政案件";
						label_InsisadminCase.style.padding = "11px 0px";
						input_InsisadminCase = document.createElement("input");
						input_InsisadminCase.className = "mui-input-clear  mui-h5";

						input_InsisadminCase.value =  total_vo.statisticPoliceCaseDto[num].adminAsistCase;
						input_InsisadminCase.type = "text";
						input_InsisadminCase.disabled = "disabled";
						input_InsisadminCase.style.paddingLeft = "20px";
						div_InsisadminCase.appendChild(label_InsisadminCase);
						div_InsisadminCase.appendChild(input_InsisadminCase);
						new_form.appendChild(div_InsisadminCase);

						// 协办刑事案件数
						div_InsiscriminalCase = document.createElement("div");
						div_InsiscriminalCase.className = "mui-input-row";
						label_InsiscriminalCase = document.createElement("label");
						label_InsiscriminalCase.innerHTML = "刑事案件";
						label_InsiscriminalCase.style.padding = "11px 0px";
						input_InsiscriminalCase = document.createElement("input");
						input_InsiscriminalCase.className = "mui-input-clear  mui-h5";

						input_InsiscriminalCase.value = total_vo.statisticPoliceCaseDto[num].crimalAsistCase;
						input_InsiscriminalCase.type = "text";
						input_InsiscriminalCase.disabled = "disabled";
						input_InsiscriminalCase.style.paddingLeft = "20px";
						div_InsiscriminalCase.appendChild(label_InsiscriminalCase);
						div_InsiscriminalCase.appendChild(input_InsiscriminalCase);
						new_form.appendChild(div_InsiscriminalCase);
						
						// 办案单位
						div_department = document.createElement("div");
						div_department.className = "mui-input-row mui-h5";
						label_department = document.createElement("label");
						label_department.innerHTML = "办案单位";
						label_department.style.padding = "11px 0px";
						input_department = document.createElement("input");
						input_department.className = "mui-input-clear  mui-h5";

						input_department.value = total_vo.statisticPoliceCaseDto[num].department.department_name;
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
					else {
						$("#ul_total_user")
								.html(
										'<li class="mui-table-view-cell mui-collapse new_li" style="text-align:center;color:red;font-size:20px;margin:10px 0;">抱歉，无统计信息</li>');
					}
					/*console.log("total_vo.currePage:" + total_vo.currePage);
					console.log("total_vo.totalPages:" +total_vo.totalPages);
					console.log("total_vo.totalRecords:" + total_vo.totalRecords);*/
				console.log("total_vo.totalRecords:" + total_vo.totalRecords);
					// 翻页
					str_page_row = '<li class="mui-disabled "><span onclick="flip(2)"> &laquo; </span>';// 上一页
					str_page_row += '<span><select class="mui-select" id="select_page" style="padding:0 15px;margin-bottom:0px;">'
					for (var pageCount = 1; pageCount <= total_vo.totalPages; pageCount++) {
						str_page_row += '<option';
						if (pageCount == total_vo.currePage) {
							str_page_row += ' selected="selected" ';
						}
						str_page_row += '><a>' + pageCount + ' </a></option>';
					}

					str_page_row += '</select></span>';
					str_page_row += '<span onclick="flip(3)">&raquo;</span></li>';// 下一页
					$("#ul_page_count").html(str_page_row);
					// 翻页跳转页面
					document.getElementById("select_page").onchange = function() {
						List_Total_User(a_value,this.value);
						console.log(this.value,"");
					};// ---- 翻页

			}
		}
	}
	if (pageIndex == null || pageIndex.preventDefault) {
		pageIndex = 1;
	}
	xhr.open("POST", "/ajdbxt/total/Total_getListPoiceCaseStatistic", true);
	var formData = new FormData();
	formData.append("policeCaseStatisticVo.currePage", pageIndex);
	formData.append("policeCaseStatisticVo.searchPolice",input_PoliceSearchText);
	formData.append("policeCaseStatisticVo.department", select_case_department_id);
	formData.append("policeCaseStatisticVo.start_time", select_start_time);
	formData.append("policeCaseStatisticVo.stop_time", select_stop_time);
	formData.append("policeCaseStatisticVo.orderString",e_str);
	xhr.send(formData);
}

/*
 * 判断页数
 */
function flip(flipPage) {
	switch (flipPage) {
	/* 首页 */
	case 1: {
		List_Total_User(a_value,1)
		break;
	}
		/* 上一页 */
	case 2: {
		if (total_vo.currePage - 1 == 0) {
			mui.toast("已经是第一页了");
		} else {
			List_Total_User(a_value,total_vo.currePage - 1);
		}
		break;
	}
		/* 下一页 */
	case 3: {
		if (total_vo.currePage == total_vo.totalPages) {
			mui.toast("已经是最后一页了");
		} else {
			List_Total_User(a_value,total_vo.currePage + 1);
		}
		break;
	}
		/* 尾页 */
	case 4: {
		List_Total_User(a_value,total_vo.totalPages);

		break;
	}

	}
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
