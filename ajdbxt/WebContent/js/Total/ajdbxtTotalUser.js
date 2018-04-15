var xhr;
var total_vo = null;

function List_Total_User_By_Page(pageIndex) {
	var select_case_kind = document.getElementById("select_case_kind").value;
	var input_Total_CaseSearchText = document
			.getElementById("input_Total_CaseSearchText").value;
	var select_start_time = document.getElementById("select_start_time");
	var select_stop_time = document.getElementById("select_stop_time");
	
	var input_police_id=document.getElementById("input_police_id").value;
/*	var span_total_department=document.getElementById("span_total_department").value;*/
	/*console.log("select_start_time2:" + select_start_time.value);
	console.log("select_stop_time2:" + select_stop_time.value);*/
	getXMLHttp();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				total_vo = JSON.parse(xhr.responseText);
				console.log("xhr.total_vo:" + xhr.responseText);
				var new_tr = null;
				var new_td = null;
				var ner_a = null;
				var table_total_user = document
						.getElementById("table_total_user");

				/*
				 * 移出除标题以外的所有行
				 */
				var old_tr = document.getElementsByClassName("new_tr");
				var long = old_tr.length;
				for (var i = 0; i < long; i++) {
					old_tr[0].parentNode.removeChild(old_tr[0]);
					// table_total_user.firstElementChild.removeChild(old_tr[0]);
				}
				/*
				 * 将数据库的数据取出来放到表格里
				 */
				for (var num = 0; num < total_vo.caseListByPolice.length; num++) {
					new_tr = document.createElement("tr");
					new_tr.className = "new_tr";
					new_tr.appendChild(document.createTextNode(''));
					table_total_user.firstElementChild.appendChild(new_tr);
					
					/*获取单位*/
					document.getElementById("span_total_department").innerHTML=total_vo.caseListByPolice[num].department.department_name;
					/*
					 * 1. 所有類型
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = total_vo.caseListByPolice[num].caseInfo.info_category;

					/*
					 * 2. 案件名
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = total_vo.caseListByPolice[num].caseInfo.info_name;
					/*
					 * 3. 评分
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = total_vo.caseListByPolice[num].caseProcess.process_score;
					/*
					 * 4. 主办民警
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = total_vo.caseListByPolice[num].mainPolice.police_name;
					/*
					 * 5. 协办民警
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = total_vo.caseListByPolice[num].insisPoliceOne.police_name
						+","+total_vo.caseListByPolice[num].insisPOliceTwo.police_name;

					/* 加载图标 */
					var i_pulse = document.getElementById("i_pulse");
					i_pulse.style.display = "none";
				}
		
				/*获取人员*/
				document.getElementById("span_total_user").innerHTML=total_vo.policeName;
				/*
				 * * 设置页数 /
				 */
				document.getElementById("span_pageIndex").innerHTML = total_vo.currePage;// 当前页
				document.getElementById("span_totalPages").innerHTML = total_vo.totalPages;// 总页数
				document.getElementById("span_totalRecords").innerHTML = total_vo.totalRecords;// 总记录数
			} else {
				toastr.error(xhr.status);
			}
		}
	}
	var formData = new FormData();
	formData.append("listEachPoliceCaseVO.currePage", pageIndex);
	formData.append("listEachPoliceCaseVO.queryCaseName",
			input_Total_CaseSearchText);
	formData.append("listEachPoliceCaseVO.category", select_case_kind);// 要确认！
	formData.append("listEachPoliceCaseVO.start_time", select_start_time.value);
	formData.append("listEachPoliceCaseVO.stop_time", select_stop_time.value);
	xhr.open("POST", "/ajdbxt/total/Total_getListPoiceCase?listEachPoliceCaseVO.police_id="+input_police_id, true);
	xhr.send(formData);
}
/*
 * 判断页数
 */
function flip(flipPage) {
	switch (flipPage) {
	/* 首页 */
	case 1: {
		List_Total_User_By_Page(1)
		break;
	}
		/* 上一页 */
	case 2: {
		if (total_vo.currePage - 1 == 0) {
			toastr.warning("已经是第一页了");
		} else {
			List_Total_User_By_Page(total_vo.currePage - 1);
		}
		break;
	}
		/* 下一页 */
	case 3: {
		if (total_vo.currePage == total_vo.totalPages) {
			toastr.warning("已经是最后一页了");
		} else {
			List_Total_User_By_Page(total_vo.currePage + 1);
		}
		break;
	}
		/* 尾页 */
	case 4: {
		List_Total_User_By_Page(total_vo.totalPages);

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