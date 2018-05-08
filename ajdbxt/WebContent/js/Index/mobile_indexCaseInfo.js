var case_info_type = null;

$(".a_case_type").bind("click", function() {
	case_info_type = this.innerText;
	mui('.mui-popover').popover('hide');
	$("#case_info_type").html(case_info_type);
	List_Index_CaseInfo_By_Page(1);
})

// 判断权限
window.onload = function() {
	var xmlHttpRequest = new XMLHttpRequest();
	xmlHttpRequest.open("POST", "/ajdbxt/user/User_getPower");
	xmlHttpRequest.send(null);
	xmlHttpRequest.onreadystatechange = function() {
		if (xmlHttpRequest.readyState == 4 && xmlHttpRequest.status == 200) {
			var loginRole = JSON.parse(xmlHttpRequest.responseText);
			$(".index_nav_police_name").html(loginRole.ajdbxt_police.police_name);
			console.log("loginRole.ajdbxt_police.police_name:"+loginRole.ajdbxt_police.police_name);
			$(".span_police_status").html(loginRole.ajdbxt_police.police_duty);
			$(".span_police_depart").html(loginRole.ajdbxt_department.department_name);
			console.log("权限:" + loginRole.ajdbxt_police.police_power);
			if (loginRole.ajdbxt_police.police_power == "1") {
				$("#ul_case_type")
						.html(
								'<li class="mui-table-view-cell"><span class="mui-icon mui-icon-paperplane"></span><span> <a class=" "  > 正在参与的案件 </a>	</span> <span class="mui-badge mui-badge-green">33</span></li>');
			}
		}
	}

	List_Index_CaseInfo_By_Page(1);
	
}

var index_case_info_vo = null;
function List_Index_CaseInfo_By_Page(pageIndex) {
	var type_chose = document.getElementById("case_info_type").innerHTML;
	var formData = new FormData();
	var xhr = false;
	xhr = new XMLHttpRequest();
	console.log("pageIndex1:" + pageIndex);
	xhr.onreadystatechange = function() {
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
				index_case_info_vo = JSON.parse(xhr.responseText);
				var new_li = null;
				var new_span0 = null;// 案件id
				var new_span1 = null;// 案件序号
				var new_span2 = null;// 案件名
				var new_span3=null;// 案件类型角标
				var new_a = null;// 案件名链接
				/*var new_div=null; //折叠信息
				var new_form=null;//折叠信息表单
				var div_row_ajlb=null;
				var label_ajlb=null;
				var input_ajlb=null;
				var div_row_badw=null;
				var div_row_zhsj=null;
				var div_row_zbmj=null;
				var div_row_xbmj=null;
				var div_row_btn=null;*/
				
				var ul_case_info = document.getElementById("ul_case_info");
				console.log("type_chose:" + type_chose);

				var str_page_row=null;
				if (index_case_info_vo.totalPages > 0) {
					str_page_row='<li class="mui-disabled"><span onclick="flip(2)"> &laquo; </span>';//上一页
					str_page_row += '<span><select class="mui-select" id="select_page" style="padding:0 15px;margin-bottom:0px;">'
					for (var pageCount = 1; pageCount <= index_case_info_vo.totalPages; pageCount++) {
						str_page_row += '<option';
						if(pageCount==index_case_info_vo.currPage){
							str_page_row += ' selected="selected" ';
						}
						str_page_row += '><a>'+pageCount +' </a></option>';
					}
					str_page_row += '</select></span>';
					str_page_row+='<li class="mui-disabled"><a onclick="flip(3)">&raquo;</a></li>';//下一页
					$("#ul_page_count").html(str_page_row);
					document.getElementById("select_page").onchange=function(){
						List_Police_By_Page(this.value);
					};
				
				/*
				 * 移出除标题以外的所有行
				 */

				var old_li = document.getElementsByClassName("new_li");
				var long = old_li.length;
				for (var i = 0; i < long; i++) {
					old_li[0].parentNode.removeChild(old_li[0]);
					// ul_case_info.firstElementChild.removeChild(old_li[0]);
				}

				/*
				 * 将数据库的数据取出来放到表格里
				 */
				for (var num = 0; num < index_case_info_vo.Caselist.length; num++) {
					new_li = document.createElement("li");
					new_li.className = "mui-table-view-cell mui-collapse new_li";
					new_li.appendChild(document.createTextNode(''));
					ul_case_info.appendChild(new_li);
					/*
					 * 案件id
					 */
					new_span0 = document.createElement("span");
					new_li.appendChild(new_span0);
					new_span0.style.display = "none";
					new_span0.className = "input_ajdbxt_police_id";
					new_span0.innerHTML = index_case_info_vo.Caselist[num].info.ajdbxt_info_id;

					/*
					 * 1. 案件序号
					 */
					new_span1 = document.createElement("span");
					new_span1.innerHTML = num + 1;
					new_span1.style.margin = " 0 10px 0 0";
					new_li.appendChild(new_span1);

					/*
					 * 2. 案件名
					 */
					new_span2 = document.createElement("span");
					new_a = document.createElement("a");
					new_a.innerHTML = index_case_info_vo.Caselist[num].info.info_name;
					new_a.className = "mui-navigate-right a_to_details";
					/*new_a.href = "/ajdbxt/user/User_mobile_index_details";*/
					new_span2.appendChild(new_a);
					new_span3=document.createElement("span");
					if(index_case_info_vo.Caselist[num].info.info_category=="行政案件"){
						new_span3.className = "mui-badge mui-badge-warning";
						new_span3.innerHTML="行政案件";
					}else{
						new_span3.className = "mui-badge mui-badge-danger";
						new_span3.innerHTML="刑事案件";
					}
					
					new_li.appendChild(new_span2);
					new_li.appendChild(new_span3);
					/*
					 * 3. 折叠信息
					 */
					/*new_div = document.createElement("div");
					new_div.className = "mui-collapse-content mui-h5";
					new_form = document.createElement("form");
					new_form.className = "mui-input-group";
					//案件类别
					div_row_ajlb= document.createElement("div");
					div_row_ajlb.className = "mui-input-row";
					label_ajlb= document.createElement("label");
					label_ajlb.innerHTML = "案件类别";
					input_ajlb= document.createElement("input");
					input_ajlb.className = "mui-input-clear mui-h5";
					input_ajlb.type="text";
					input_ajlb.value=index_case_info_vo.Caselist[num].info.info_category;
					div_row_ajlb.appendChild(label_ajlb);
					div_row_ajlb.appendChild(input_ajlb);
					new_form.appendChild(div_row_ajlb);
					new_div.appendChild(new_form);
					new_li.appendChild(new_div);*/
				}
				//-------------------------
				//给每个案件链接绑定一个click方法
				$(".a_to_details").bind("click", function() {
					window.location.href="/ajdbxt/user/User_mobile_index_details";
				})
				
				}else{
					$("#ul_case_info").html('<li class="mui-table-view-cell mui-collapse new_li" style="text-align:center;color:red;font-size:20px;margin:10px 0;">无案件信息</li>');
				}
				console.log("index_case_info_vo.currPage:"
						+ index_case_info_vo.currPage);
				console.log("index_case_info_vo.totalPages:"
						+ index_case_info_vo.totalPages);
				console.log("index_case_info_vo.countRecords:"
						+ index_case_info_vo.countRecords);
				
				

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
	if (type_chose == "正在参与的案件") {
		formData.append("ajdbxtProcess.process_case_end", "false");
	} else if (type_chose == "待核对案件") {
		formData.append("ajdbxtProcess.process_captain_check", "false");
	} else if (type_chose == "等待提交问题清单的案件") {
		formData.append("ajdbxtProcess.process_question", "false");
	} else if (type_chose == "等待评分的案件") {
		formData.append("ajdbxtProcess.process_score", "false");
	} else {
		return;
	}
	
	
	xhr.open("POST", "/ajdbxt/process/getInfoProcessAction");
	xhr.send(formData);
}


/*
 * 判断页数
 */
function flip(flipPage) {
	switch (flipPage) {
	/* 首页 */
	case 1: {
		List_Index_CaseInfo_By_Page(1)
		break;
	}
		/* 上一页 */
	case 2: {
		if (index_case_info_vo.currPage - 1 == 0) {
			mui.toast("已经是第一页了");
		} else {
			List_Index_CaseInfo_By_Page(index_case_info_vo.currPage - 1);
		}
		break;
	}
		/* 下一页 */
	case 3: {
		if (index_case_info_vo.currPage == index_case_info_vo.totalPages) {
			mui.toast("已经是最后一页了");
		} else {
			List_Index_CaseInfo_By_Page(index_case_info_vo.currPage + 1);
		}
		break;
	}
		/* 尾页 */
	case 4: {
		List_Index_CaseInfo_By_Page(index_case_info_vo.totalPages);

		break;
	}

	}
}

