var xhr;
var total_vo = null;
function List_Total_By_Page(pageIndex) {
	getXMLHttp();
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
			total_vo = JSON.parse(xhr.responseText);
			var new_tr = null;
			var new_td = null;
			var ner_a = null;
			var table_total = document.getElementById("table_total");
			var select_start_time=document.getElementById("select_start_time").value;
			var select_stop_time=document.getElementById("select_stop_time").value;
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
				new_td.innerHTML = total_vo.list[num].department;
				/*
				 * 2. 人员
				 */
				new_td = document.createElement("td");
				new_a = document.createElement("a");
				new_td.appendChild(new_a);
				new_tr.appendChild(new_td);
				new_a.innerHTML = total_vo.list[num].police;
				new_a.style.cursor = "pointer";
				/*
				 * 3. 行政案件
				 */
				new_td = document.createElement("td");
				new_tr.appendChild(new_td);
				new_td.innerHTML = total_vo.list[num].adminnistrCaseNum;
				/*
				 * 1. 刑事案件
				 */
				new_td = document.createElement("td");
				new_tr.appendChild(new_td);
				new_td.innerHTML = total_vo.list[num].criminalCaseNum;
				
				/* 加载图标 */
				var i_pulse = document.getElementById("i_pulse");
				i_pulse.style.display = "none";
			}
		} else {
			toastr.error(xhr.status);
		}
	}
	}
	var formData=new FormData();
	formData.append("total_vo.currentPage", pageIndex);
	formData.append("statisticDto.start_time",select_start_time);
	formData.append("statisticDto.stop_time",select_stop_time);
	xhr.open("POST", "/ajdbxt/total/Total_listDepartmentByCategory","true");
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


function getXMLHttp() {
	if (window.XMLHttpRequest) {
		// IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
		xhr = new XMLHttpRequest();
	} else {
		// IE6, IE5 浏览器执行代码
		xhr = new ActiveXObject("Microsoft.XMLHTTP");
	}
}