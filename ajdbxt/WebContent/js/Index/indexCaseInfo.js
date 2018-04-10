//判断权限
window.onload = function() {
	var xmlHttpRequest = new XMLHttpRequest();
	xmlHttpRequest.open("POST", "/ajdbxt/user/User_getPower");
	xmlHttpRequest.send(null);
	xmlHttpRequest.onreadystatechange = function() {
		if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
			var loginRole = JSON.parse(xmlHttpRequest.responseText);
			alert(loginRole.police_power);
			if (loginRole.police_power == "1（浏览）" || loginRole.police_power == "1") {
				$("#type_chose").html('<option  value="正在参与的案件">正在参与的案件</option>');
				}
			}
		}
}

function List_Index_CaseInfo_By_Page(pageIndex){
	var index_case_info_vo = null;
		var formData = new FormData();
		var xhr = false;
		xhr = new XMLHttpRequest();
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {
				if (xhr.status == 200) {
					index_case_info_vo = JSON.parse(xhr.responseText);
					/*
					 * 
					 */

					var new_tr = null;
					var new_td = null;
					var new_a=null;
					var table_case_info = document.getElementById("table_case_info");

					/*
					 * 移出除标题以外的所有行
					 */

					var old_tr = document.getElementsByClassName("new_tr");
					var long = old_tr.length;
					for (var i = 0; i < long; i++) {
						old_tr[0].parentNode.removeChild(old_tr[0]);
						// table_case_info.firstElementChild.removeChild(old_tr[0]);
					}

					/*
					 * 将数据库的数据取出来放到表格里
					 */
					for (var num = 0; num < index_case_info_vo.list.length; num++) {
						new_tr = document.createElement("tr");
						new_tr.className = "new_tr";
						new_tr.appendChild(document.createTextNode(''));
						table_case_info.firstElementChild.appendChild(new_tr);
						/*
						 * 案件id
						 */
						new_td = document.createElement("td");
						new_tr.appendChild(new_td);
						new_td.style.display = "none";
						new_td.className = "input_ajdbxt_police_id";
						new_td.innerHTML = index_case_info_vo.list[num].ajdbxt_police_id;
						/*
						 * 1. 案件名称
						 */
						new_td = document.createElement("td");
						new_a=document.createElement("a");
						new_a.href="";
						new_td.appendChild(new_a);
						new_tr.appendChild(new_td);
						new_td.innerHTML = num + 1;
						/*
						 * 2. 案件类别
						 */
						new_td = document.createElement("td");
						new_tr.appendChild(new_td);
						new_td.innerHTML = index_case_info_vo.list[num].police_serial_number;
						/*
						 * 3. 办案单位
						 */
						new_td = document.createElement("td");
						new_tr.appendChild(new_td);
						new_td.innerHTML = index_case_info_vo.list[num].police_name;
						/*
						 * 4. 抓获时间
						 */
						new_td = document.createElement("td");
						new_tr.appendChild(new_td);
						new_td.innerHTML = index_case_info_vo.list[num].police_department;
						/*
						 * 5. 主办民警
						 */
						new_td = document.createElement("td");
						new_tr.appendChild(new_td);
						new_td.innerHTML = index_case_info_vo.list[num].police_duty;

						/*
						 * 6. 协办民警
						 */
						new_td = document.createElement("td");
						new_tr.appendChild(new_td);
						if(index_case_info_vo.list[num].police_power=="1"){
							new_td.innerHTML ="浏览" ;
						}else{
							new_td.innerHTML ="管理" ;
						}
						

					}
					/*
					 * 
					 */
					var i_pulse = document.getElementById("i_pulse");
					i_pulse.style.display = "none";


					/*
					 * * 设置页数 /
					 */
					document.getElementById("span_pageIndex").innerHTML = index_case_info_vo.currentPage;// 当前页
					document.getElementById("span_totalPages").innerHTML = index_case_info_vo.totalPage;// 总页数
					document.getElementById("span_totalRecords").innerHTML = index_case_info_vo.allRow;// 总记录数
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
		formData.append("findPoliceByPageVO.currentPage", pageIndex);
		xhr.open("POST", "/ajdbxt/user/User_listAll");
		xhr.send(formData);

}