//判断权限
window.onload = function() {
	var xmlHttpRequest = new XMLHttpRequest();
	xmlHttpRequest.open("POST", "/ajdbxt/user/User_getPower");
	xmlHttpRequest.send(null);
	xmlHttpRequest.onreadystatechange = function() {
		if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
			var loginRole = JSON.parse(xmlHttpRequest.responseText);
			console.log("权限:"+loginRole.police_power);
			if (loginRole.police_power == "1（浏览）" || loginRole.police_power == "1") {
				$("#type_chose").html('<option  value="正在参与的案件">正在参与的案件</option>');
				}
			}
		}
	
	List_Index_CaseInfo_By_Page(1);
}

function List_Index_CaseInfo_By_Page(pageIndex){
	var type_chose=document.getElementById("type_chose").value;
	var index_case_info_vo = null;
		var formData = new FormData();
		var xhr = false;
		xhr = new XMLHttpRequest();
		console.log("pageIndex1:"+pageIndex);
		xhr.onreadystatechange = function() {
			if (xhr.readyState == 4) {
				if (xhr.status == 200) {
					index_case_info_vo = JSON.parse(xhr.responseText);
					alert(xhr.readyState);
					/*
					 * 
					 */

					var new_tr = null;
					var new_td = null;
					var new_a=null;
					var table_case_info = document.getElementById("table_case_info");
					
					console.log("type_chose:"+type_chose);

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
						new_td.innerHTML = index_case_info_vo.list[num].ajdbxt_info_id;
						/*
						 * 1. 案件名称
						 */
						new_td = document.createElement("td");
						new_a=document.createElement("a");
						new_a.href="";
						new_td.appendChild(new_a);
						new_tr.appendChild(new_td);
						new_td.innerHTML = index_case_info_vo.list[num].info_name;
						/*
						 * 2. 案件类别
						 */
						new_td = document.createElement("td");
						new_tr.appendChild(new_td);
						new_td.innerHTML = index_case_info_vo.list[num].info_category;
						/*
						 * 3. 办案单位
						 */
						new_td = document.createElement("td");
						new_tr.appendChild(new_td);
						new_td.innerHTML = index_case_info_vo.list[num].info_department;
						/*
						 * 4. 抓获时间
						 */
						new_td = document.createElement("td");
						new_tr.appendChild(new_td);
						new_td.innerHTML = index_case_info_vo.list[num].info_catch_time;
						/*
						 * 5. 主办民警
						 */
						new_td = document.createElement("td");
						new_tr.appendChild(new_td);
						new_td.innerHTML = index_case_info_vo.list[num].info_main_police;

						/*
						 * 6. 协办民警
						 */
						new_td = document.createElement("td");
						new_tr.appendChild(new_td);
						new_td.innerHTML = index_case_info_vo.list[num].info_assistant_police_one;
						new_td.innerHTML +=index_case_info_vo.list[num].info_assistant_police_two;

					}
					/*
					 * 
					 */
					var i_pulse = document.getElementById("i_pulse");
					i_pulse.style.display = "none";


					/*
					 * * 设置页数 /
					 */
					document.getElementById("span_pageIndex").innerHTML = index_case_info_vo.currPage;// 当前页
					document.getElementById("span_totalPages").innerHTML = index_case_info_vo.totalPages;// 总页数
					document.getElementById("span_totalRecords").innerHTML = index_case_info_vo.countRecords;// 总记录数

				} else {
					toastr.error(xhr.status);
				}
			}
		}
		if (pageIndex == null || pageIndex.preventDefault) {
			pageIndex = 1;
		}
		
		formData.append("infoVO.currPage", pageIndex);
		console.log(type_chose);
		if(type_chose=="正在参与的案件"){
			formData.append("ajdbxtProcess.case_end","false");
		}else if(type_chose=="待核对案件"){
			formData.append("ajdbxtProcess.captain_check","false");
		}else if(type_chose=="等待提交问题清单的案件"){
			formData.append("ajdbxtProcess.process_qustion","false");
		}else if(type_chose=="等待评分的案件"){
			formData.append("ajdbxtProcess.process_score","false");
		}else{
			return;
		}
		console.log("pageIndex:" + pageIndex);
		formData.append("infoVO.currPage", type_chose);
		xhr.open("POST", "/ajdbxt/process/getInfoProcessAction");
		xhr.send(formData);

}