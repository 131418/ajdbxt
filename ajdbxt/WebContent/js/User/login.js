/**
 * 
 */
var xhr;
   
function login() {
	getXhr();
	console.log("11");
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	xhr.open("POST", "/ajdbxt/user/User_login", "true");
	var formData = new FormData();
	formData.append("username", username);
	formData.append("password", password);
	xhr.send(formData);
	xhr.onreadystatechange = function() {
		if (xhr.onreadystate == 4 && xhr.status == 200) {
			console.log(xhr.onreadystate);
			var result = xhr.responseText;
			if (result == "success") {
				window.location = "/ajdbxt/user/User_indexPage";
			} else {
				toastr.error("用户名或密码输入错误！");
			}
		}
	}
}

function getXhr() {
	if (window.XMLHttpRequest) {
		// IE7+, Firefox, Chrome, Opera, Safari 浏览器执行代码
		xhr = new XMLHttpRequest();
	} else {
		// IE6, IE5 浏览器执行代码
		xhr = new ActiveXObject("Microsoft.XMLHTTP");
	}
}