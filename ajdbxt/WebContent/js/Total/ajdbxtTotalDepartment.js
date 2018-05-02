/**
 * 
 */
var xhr;
var total_vo = null;

function List_Total_By_Department(){
	getXMLHttp();
	
	var select_start_time=document.getElementById("select_start_time").value;
	var select_stop_time=document.getElementById("select_stop_time").value;
	var adminCase=document.getElementById("adminCase").value;
	var criminalCase=document.getElementById("criminalCase").value;
	var average=document.getElementById("average").value;
	
	xhr.onreadystatechange=function(){
		if (xhr.readyState == 4) {
			if (xhr.status == 200) {

			total_vo=JSON.parse(xhr.responseText);
			var new_tr=null;
			var new_td=null;
			var table_total=document.getElementById("table_total");
			
			
			/*
			 * 将数据库的数据取出来放到表格里
			 */
			for(var num=0;num<total_vo.statisticPoliceCaseNumDTO.length;num++){
				new_tr=document.createElement("tr");
				new_tr.ClassName="new_tr";
				new_tr.appendChild(document.createTextNode(''));
				table_total.firstElementChild.appendChild(new_tr);
				
				/*
				 * 1.序号
				 */
				new_td=document.createElement("td");
				new_tr.appendChild(new_td);
				new_td.innerHTML = total_vo.statisticPoliceCaseNumDTO[num].length;
				
				/*
				 * 2.办案单位
				 */
				new_td=document.createElement("td");
				new_tr.appendChild(new_td);
				new_td.innerHTML = total_vo.statisticPoliceCaseNumDTO[num].department.department_name;
				
				/*
				 * 3.行政案件
				 */
				new_td=document.createElement("td");
				new_tr.appendChild(new_td);
				new_td.innerHTML = total_vo.statisticPoliceCaseNumDTO[num].adminCase;
				
				/*
				 * 4.刑事案件
				 */
				new_td=document.createElement("td");
				new_tr.appendChild(new_td);
				new_td.innerHTML = total_vo.statisticPoliceCaseNumDTO[num].criminalCase;
				/*
				 * 5.总案件数
				 */
				new_td=document.createElement("td");
				new_tr.appendChild(new_td);
				new_td.innerHTML = total_vo.statisticPoliceCaseNumDTO[num].totalCase;
				
				/*
				 * 6.平均分
				 */
				new_td=document.createElement("td");
				new_tr.appendChild(new_td);
				new_td.innerHTML = total_vo.statisticPoliceCaseNumDTO[num].averageScore;
				/*
				 * 加载图标
				 */
				var i_pulse=document.getElementById("i_pulse");
				i_pulse.style.display="none";
			}		
			}
		else{
			toastr.error(xhr.status);
		}
		}
	}
	xhr.open("POST","/ajdbxt/total/Total_getListDeparmentCaseStatistics",true);
	var formData=new FormData();
	formData.append("departmentStatisticVo.start_time",select_start_time);
	formData.append("departmentStatisticVo.stop_time",select_stop_time);
	/*formData.append("adminCase",adminCase);
	formData.append("criminalCase",criminalCase);
	formData.append("average",average);*/
	xhr.send(formData);
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