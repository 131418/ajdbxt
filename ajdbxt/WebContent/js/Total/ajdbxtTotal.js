var xhr;
var total_vo = null;

function List_Total_By_Page(pageIndex) {
	var select_start_time = document.getElementById("select_start_time");
	var select_stop_time = document.getElementById("select_stop_time");
	var input_Total_PoliceSearchText = document
			.getElementById("input_Total_PoliceSearchText").value;
	var select_case_department = document
			.getElementById("select_case_department").value;
	var MainadminCase = document.getElementById("MainadminCase").value;
	var MaincriminalCase = document.getElementById("MaincriminalCase").value;
	var InsisadminCase = document.getElementById("InsisadminCase").value;
	var InsiscriminalCase = document.getElementById("InsiscriminalCase").value;
	var averageScore=document.getElementById("averageScore").value;

	getXMLHttp();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {

				total_vo = JSON.parse(xhr.responseText);
				var new_tr = null;
				var new_td = null;
				var new_a = null;
				var table_total = document.getElementById("table_total");

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
				for (var num = 0; num < total_vo.statisticPoliceCaseDto.length; num++) {
					new_tr = document.createElement("tr");
					new_tr.className = "new_tr";
					new_tr.appendChild(document.createTextNode(''));
					table_total.firstElementChild.appendChild(new_tr);

					/*
					 * 序号
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);

					new_td.innerHTML = (num + 1);
					new_td.colSpan = '2';

					/*
					 * 1. 办案单位
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = total_vo.statisticPoliceCaseDto[num].department.department_name;

					new_td.colSpan = '2';
					/*
					 * 人员id
					 */
					/*
					 * new_td = document.createElement("td");
					 * new_tr.appendChild(new_td); var td_ajdbxt_police_id =
					 * total_vo.StatisticPoliceCaseDto[num].police.ajdbxt_police_id
					 * new_td.innerHTML = td_ajdbxt_police_id;
					 * new_td.style.display = "none";
					 */

					/*
					 * 2. 人员
					 */
					new_td = document.createElement("td");

					new_tr.appendChild(new_td);
					new_td.innerHTML = total_vo.statisticPoliceCaseDto[num].police.police_name;
					new_td.colSpan = '2';
					/* new_a.style.cursor = "pointer"; */

					/*
					 * 3. 主办行政案件数
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = total_vo.statisticPoliceCaseDto[num].adminMianCase;
					/*
					 * 4. 主办刑事案件数
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = total_vo.statisticPoliceCaseDto[num].crimalMainCase;
					/*
					 * 5. 协办行政案件数
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = total_vo.statisticPoliceCaseDto[num].adminAsistCase;
					/*
					 * 6. 协办刑事案件数
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = total_vo.statisticPoliceCaseDto[num].crimalAsistCase;
					/*
					 * 7.平均分
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);

					new_td.innerHTML = total_vo.statisticPoliceCaseDto[num].score_mian;



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
	formData.append("policeCaseStatisticVo.currePage", pageIndex);

	formData.append("policeCaseStatisticVo.searchPolice",
			input_Total_PoliceSearchText);
	formData.append("policeCaseStatisticVo.department", select_case_department);
	formData.append("policeCaseStatisticVo.start_time", select_start_time.value);
	formData.append("policeCaseStatisticVo.stop_time", select_stop_time.value);
	formData.append("policeCaseStatisticVo.orderString", MainadminCase);
	formData.append("policeCaseStatisticVo.orderString", MaincriminalCase);
	formData.append("policeCaseStatisticVo.orderString", InsisadminCase);
	formData.append("policeCaseStatisticVo.orderString",
			InsiscriminalCase);
	formData.append("policeCaseStatisticVo.orderString",averageScore);
	
	xhr.open("POST", "/ajdbxt/total/Total_getListPoiceCaseStatistic", true);
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