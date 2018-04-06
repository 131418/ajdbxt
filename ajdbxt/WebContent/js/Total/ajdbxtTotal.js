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
					 * 1. 办案单位
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = total_vo.list[num].info_department;
					/*
					 * 2. 民警姓名
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = total_vo.list[num].info_main_police;
					/*
					 * 3. 案件评分
					  
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = total_vo.list[num].info_main_police;*/
					/*
					 * 4. 案件类型
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = total_vo.list[num].info_category;
					/*
					 * 5. 抓获时间
					 */
					new_td = document.createElement("td");
					new_tr.appendChild(new_td);
					new_td.innerHTML = total_vo.list[num].info_catch_time;

				}
				/*
				 * 
				 */
				var i_pulse = document.getElementById("i_pulse");
				i_pulse.style.display = "none";

				/*
				 * * 设置页数 /
				 */
				document.getElementById("span_pageIndex").innerHTML = total_vo.currentPage;// 当前页
				document.getElementById("span_totalPages").innerHTML = total_vo.totalPage;// 总页数
				document.getElementById("span_totalRecords").innerHTML = total_vo.allRow;// 总记录数
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
		List_Total_By_Page(1)
		break;
	}
		/* 上一页 */
	case 2: {
		if (total_vo.currentPage - 1 == 0) {
			toastr.warning("已经是第一页了");
		} else {
			List_Total_By_Page(total_vo.pageIndex - 1);
		}
		break;
	}
		/* 下一页 */
	case 3: {
		if (total_vo.currentPage == total_vo.totalPage) {
			toastr.warning("已经是最后一页了");
		} else {
			List_Total_By_Page(total_vo.currentPage + 1);
		}
		break;
	}
		/* 尾页 */
	case 4: {
		List_Total_By_Page(total_vo.totalPage);

		break;
	}

	}
}