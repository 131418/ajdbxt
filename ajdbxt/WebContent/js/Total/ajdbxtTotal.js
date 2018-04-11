var xhr;
var total_vo = null;

function List_Total_By_Page(pageIndex) {
	var select_start_time = document.getElementById("select_start_time");
	var select_stop_time = document.getElementById("select_stop_time");
	console.log("select_start_time2:" + select_start_time.value);
	console.log("select_stop_time2:" + select_stop_time.value);
	var input_Total_PoliceSearchText = document
			.getElementById("input_Total_PoliceSearchText").value;
	var select_case_department = document.getElementById("select_case_department").value;
	
	console.log("select_case_department:" + select_case_department);
	

	getXMLHttp();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				
				total_vo = JSON.parse(xhr.responseText);
				var new_tr = null;
				var new_td = null;
				var ner_a = null;
				var table_total = document.getElementById("table_total");

				/*
				 * 移出除标题以外的所有行
				 */
				var old_tr = document.getElementsByClassName("new_tr");
				var long = old_tr.length;
				for (var i = 0; i < long; i++) {
					old_tr[0].parentNode.removeChild(old_tr[0]);
					// table_total.firstElementChild.removeChild(old_tr[0]);
				}
				/*
				 * 将数据库的数据取出来放到表格里
				 */
				for (var num = 0; num < total_vo.statisticPoliceCaseNumDTO.length; num++) {
					new_tr = document.createElement("tr");
					new_tr.className = "new_tr";
					new_tr.appendChild(document.createTextNode(''));
					table_total.firstElementChild.appendChild(new_tr);
					/*
					 * 1. 办案单位
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = total_vo.statisticPoliceCaseNumDTO[num].police.police_department;

					/*
					 * 2. 人员
					 */
					new_td = document.createElement("td");
					new_a = document.createElement("a");
					new_a.href = '/ajdbxt/total/Total_page_listPoliceCase';
					new_td.appendChild(new_a);
					new_tr.appendChild(new_td);
					new_a.innerHTML = total_vo.statisticPoliceCaseNumDTO[num].police.police_name;
					new_a.style.cursor = "pointer";

					/*
					 * 3. 行政案件
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = total_vo.statisticPoliceCaseNumDTO[num].adminCase;
					/*
					 * 4. 刑事案件
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = total_vo.statisticPoliceCaseNumDTO[num].criminalCase;

					/* 加载图标 */
					var i_pulse = document.getElementById("i_pulse");
					i_pulse.style.display = "none";
				}
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
	formData.append("listPoliceCaseByPageAndSearchVO.currePage",
			pageIndex);
	formData.append("listPoliceCaseByPageAndSearchVO.searchPolice",
			input_Total_PoliceSearchText);
	formData.append("listPoliceCaseByPageAndSearchVO.department",
			select_case_department);
	formData.append("listPoliceCaseByPageAndSearchVO.start_time",
			select_start_time.value);
	formData.append("listPoliceCaseByPageAndSearchVO.stop_time",
			select_stop_time.value);
	xhr.open("POST", "/ajdbxt/total/Total_getListPoliceCaseStatistics", "true");
	xhr.send(formData);
}
/*
 * 判断页数
 */
function flip(flipPage) {
	switch (flipPage) {
	/* 首页 */
	case 1: {
		List_Total_By_Page(1)
		break;
	}
		/* 上一页 */
	case 2: {
		if (total_vo.currePage - 1 == 0) {
			toastr.warning("已经是第一页了");
		} else {
			List_Total_By_Page(total_vo.currePage - 1);
		}
		break;
	}
		/* 下一页 */
	case 3: {
		if (total_vo.currePage == total_vo.totalPages) {
			toastr.warning("已经是最后一页了");
		} else {
			List_Total_By_Page(total_vo.currePage + 1);
		}
		break;
	}
		/* 尾页 */
	case 4: {
		List_Total_By_Page(total_vo.totalPages);

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