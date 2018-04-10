/**
 * 
 */
var total_vo = null;
function List_Total_By_Page(pageIndex) {
	var formData = new FormData();
	var xhr = false;
	xhr = new XMLHttpRequest();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				total_vo = JSON.parse(xhr.responseText);
				/*
				 * 
				 */

				var new_tr = null;
				var new_td = null;
				var new_a=null;
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
				for (var num = 0; num < total_vo.list.length; num++) {
					new_tr = document.createElement("tr");
					new_tr.className = "new_tr";
					new_tr.appendChild(document.createTextNode(''));
					table_total.firstElementChild.appendChild(new_tr);
					/*
					 * 1. 人员
					 */
					new_td = document.createElement("td");
					new_a=document.createElement("a");
					new_td.appendChild(new_a);
					new_tr.appendChild(new_td);
					new_a.innerHTML = total_vo.list[num].info_department;
					new_a.style.cursor="pointer";
					
					/*
					 * 2. 行政案件
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = total_vo.list[num].info_main_police;
					
					  
					/*
					 * 3.刑事案件
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = total_vo.list[num].info_main_police;
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
	
	formData.append("total_vo.currentPage", pageIndex);
	xhr.open("POST", "/ajdbxt/total/Total_listAllInfo");
	xhr.send(formData);

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
