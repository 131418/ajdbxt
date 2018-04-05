/**
 * 
 */
var xhr;

 window.onload=judegRole;
 function judegRole(){
	 getXMLHttp();
	 xhr.open("POST","","true");
	 xhr.send();
	 
	 xhr.onreadystatechange=function(){
		 if(xhr.readyState==4 && xhr.status==200){
			 var result=xhr.responseText;
			 if(result=="1"){
				 var str='';
				 str='<option value="joinCae">正在参与的案件</option>';
				 $("#type_chose").html(str);
				 
			 }
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
 
 
 
 

/* window.onload=function(){
	
//	应该移除的是全部的4个option，可是实际上只移除了第一和第三个。？
	 var type_chose=document.getElementById("type_chosen");
	 var op=document.getElementsByTagName("option");
	 var o=op.length;
	 for(var i=0;i<o;i++){
		alert(op[i].innerHTML);
	 type_chose.removeChild(op[i]);
	 }
	 
	 type_chose.appendChild(optionText);
	 type_chose.appendChild(optioText);
	 
	 
	 
	 var str='';
	 str='<option value="erertergerg">2222</option><option value="erertergerg">67657rtseryrthyrdth</option>';
	 $("#type_chose").html(str);
	 
	 
	 
	 $("#type_chose .a").css("display","none");
	 
	 
	var a= document.getElementsByClassName("a");
	alert(a);
	for(i=0;i<a.length;i++){
		alert(a[i].innerHTML);
		a[i].style.display="none";
	}
	 
	 
	 
 }*/