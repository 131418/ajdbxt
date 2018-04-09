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
	
}