/**
 * 
 */
var xhr;
var total_vo = null;

function List_Total_By_Department(e,pageIndex){
	alert(e);
	getXMLHttp();	
	var select_start_time=document.getElementById("select_start_time").value;
	var select_stop_time=document.getElementById("select_stop_time").value;
	var adminCase=document.getElementById("adminCase").value;
	var criminalCase=document.getElementById("criminalCase").value;
	var averageScore=document.getElementById("averageScore").value;
	
	xhr.onreadystatechange=function(){
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {
			total_vo=JSON.parse(xhr.responseText);
			var new_tr=null;
			var new_td=null;
			var table_total=document.getElementById("table_total");

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
			for(var num=0;num<total_vo.statisticPoliceCaseDto.length;num++){
				new_tr=document.createElement("tr");
				new_tr.ClassName="new_tr";
				new_tr.appendChild(document.createTextNode(''));
				table_total.firstElementChild.appendChild(new_tr);
				
				/*
				 * 1.序号
				 */
				new_td=document.createElement("td");
				new_tr.appendChild(new_td);

				new_td.innerHTML =(num+1);
	
				/*
				 * 2.办案单位
				 */
				new_td=document.createElement("td");
				new_tr.appendChild(new_td);
				new_td.innerHTML = total_vo.statisticPoliceCaseDto[num].department.department_name;
				
				/*
				 * 3.行政案件
				 */
				new_td=document.createElement("td");
				new_tr.appendChild(new_td);
				new_td.innerHTML = total_vo.statisticPoliceCaseDto[num].adminCase;
				
				/*
				 * 4.刑事案件
				 */
				new_td=document.createElement("td");
				new_tr.appendChild(new_td);
				new_td.innerHTML = total_vo.statisticPoliceCaseDto[num].criminalCase;
				/*
				 * 5.总案件数
				 */
				new_td=document.createElement("td");
				new_tr.appendChild(new_td);
				new_td.innerHTML = total_vo.statisticPoliceCaseDto[num].totalCase;
				
				/*
				 * 6.平均分
				 */
				new_td=document.createElement("td");
				new_tr.appendChild(new_td);
				new_td.innerHTML = total_vo.statisticPoliceCaseDto[num].averageScore;
				/*
				 * 加载图标
				 */
				var i_pulse=document.getElementById("i_pulse");
				i_pulse.style.display="none";
			}
			/*
			 * * 设置页数 /
			 */
			document.getElementById("span_pageIndex").innerHTML = total_vo.currePage;// 当前页
			document.getElementById("span_totalPages").innerHTML = total_vo.totalPages;// 总页数
			document.getElementById("span_totalRecords").innerHTML = total_vo.totalRecords;// 总记录数
			}
		else{
			toastr.error(xhr.status);
		}
		}
	}
	xhr.open("POST","/ajdbxt/total/Total_getListDeparmentCaseStatistics",true);
	var formData=new FormData();
	formData.append("departmentStatisticVo.currePage", pageIndex);
	formData.append("departmentStatisticVo.start_time",select_start_time);
	formData.append("departmentStatisticVo.stop_time",select_stop_time);
	formData.append("departmentStatisticVo.orderString",e);
	xhr.send(formData);
}
/*
 * 判断页数
 */
function flip(flipPage) {
	switch (flipPage) {
	/* 首页 */
	case 1: {
		List_Total_By_Department(1)
		break;
	}
		/* 上一页 */
	case 2: {
		if (total_vo.currePage - 1 == 0) {
			toastr.warning("已经是第一页了");
		} else {
			List_Total_By_Department(total_vo.currePage - 1);
		}
		break;
	}
		/* 下一页 */
	case 3: {
		if (total_vo.currePage == total_vo.totalPages) {
			toastr.warning("已经是最后一页了");
		} else {
			List_Total_By_Department(total_vo.currePage + 1);
		}
		break;
	}
		/* 尾页 */
	case 4: {
		List_Total_By_Department(total_vo.totalPages);

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
