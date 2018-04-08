var xhr;
var total_vo = null;
function List_Total() {
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
			for (var num = 0; num < total_vo.list.length; num++) {
				new_tr = document.createElement("tr");
				new_tr.className = "new_tr";
				new_tr.appendChild(document.createTextNode(''));
				table_total.firstElementChild.appendChild(new_tr);
				/*
				 * 1. 办案单位
				 */
				new_td = document.createElement("td");
				new_a = document.createElement("a");
				new_td.appendChild(new_a);
				new_tr.appendChild(new_td);
				new_a.innerHTML = total_vo.list[num].info_department;
				new_a.style.cursor = "pointer";
				/*
				 * 2. 行政案件
				 */
				new_td = document.createElement("td");
				new_tr.appendChild(new_td);
				new_td.innerHTML = total_vo.list[num].info_main_police;
				/*
				 * 3. 刑事案件
				 */
				new_td = document.createElement("td");
				new_tr.appendChild(new_td);
				new_td.innerHTML = total_vo.list[num].info_assistant_police_one;
				
				/* 加载图标 */
				var i_pulse = document.getElementById("i_pulse");
				i_pulse.style.display = "none";
			}
		} else {
			toastr.error(xhr.status);
		}
	}
	}
	xhr.open("POST", "/ajdbxt/total/Total_listAllInfo");
	xhr.send();
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