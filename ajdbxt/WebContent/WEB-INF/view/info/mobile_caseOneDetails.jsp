<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!doctype html>
<html>

<head>
<meta charset="UTF-8">
<title></title>
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
<link href="<%=basePath%>css/mui.min.css" rel="stylesheet" />
<style>
body {
	font-size: 15px;
}

.index_nav {
	background-color: #007aff;
	color: white;
	font-size: 15px;
}

h1, a {
	color: white;
}

input {
	text-align: right;
}
.mui-input-row{
padding:0 5px;
}
</style>
</head>

<body>
<s:action name="User_mobile_navbar" namespace="/user"
		executeResult="true" />
	<header class="mui-bar mui-bar-nav index_nav">
		<a class="mui-action-back mui-icon mui-icon-left-nav mui-pull-left"></a>
		<h1 class="mui-title" style="color: white;">案件信息</h1>
	</header>
	<!--------------------------------->
	
	<div class="mui-content" id="case_details_content">

		<div class="mui-card"
			style="width: 100%; margin: 0px;">
			<!--页眉，放置标题-->
			<div class="mui-card-header">
				<h3 class="mui-h4" style="color:#007aff">案件基本信息</h3>
			</div>
			<div class="mui-card-content">
				<form class="mui-input-group" id="staffDetails">
					<div class="mui-input-row">
						<label class="mui-h5">案件名称</label> <input type="text" name="info.info_name"
							class="mui-input-clear mui-h5" style="font-size:16px;" placeholder="" >
					</div>
					<div class="mui-input-row">
						<label class="mui-h5">案件类别</label> <input type="text" name="info.info_category"
							class="mui-input-clear mui-h5" style="font-size:16px;" placeholder="" >
					</div>
					<div class="mui-input-row">
						<label class="mui-h5">办案单位</label> <input type="text" style="font-size:16px;"
							class="mui-input-clear mui-h5" placeholder="" name="department.department_name" >
					</div>
					<div class="mui-input-row">
						<label class="mui-h5">抓获时间</label> <input type="text" style="font-size:16px;"
							class="mui-input-clear mui-h5" placeholder="" name="info.info_catch_time">
					</div>
					<div class="mui-input-row"> 
						<label class="mui-h5">主办民警</label> <input type="text" style="font-size:16px;"
							class="mui-input-clear mui-h5" placeholder="" name="police[0].police_name" >
					</div>
					<div class="mui-input-row">
						<label class="mui-h5">协办民警1</label> <input type="text" style="font-size:16px;"
							class="mui-input-clear mui-h5" placeholder="" name="police[1].police_name">
					</div>
                   <div class="mui-input-row">
						<label class="mui-h5">协办民警2</label> <input type="text" style="font-size:16px;"
							class="mui-input-clear mui-h5" placeholder="" name="police[2].police_name">
					</div>
					 <div class="mui-input-row">
						<label class="mui-h5">法制员</label> <input type="text" style="font-size:16px;"
							class="mui-input-clear mui-h5" placeholder="" name="info.info_department_legal_member">
					</div>
					 <div class="mui-input-row">
						<label class="mui-h5">所（队）长</label> <input type="text" style="font-size:16px;"
							class="mui-input-clear mui-h5" placeholder="" name="info.info_department_captain">
					</div>
					 <div class="mui-input-row">
						<label class="mui-h5">值班局领导</label> <input type="text" style="font-size:16px;"
							class="mui-input-clear mui-h5" placeholder="" name="leader.police_name">
					</div>
					 <div class="mui-input-row">
						<label class="mui-h5">值班民警</label> <input type="text" style="font-size:16px;"
							class="mui-input-clear mui-h5" placeholder="" name="legal.police_name">
					</div>
				</form>
			</div>
			<!--页脚，放置补充信息或支持的操作-->
			<div class="mui-card-footer">
				<button type="button" id="btn"
					class="mui-btn mui-btn-primary mui-btn-outlined"
					style="width: 100%;" >查看案件流程</button>
<%-- 					onclick="window.location.href='<%=basePath%>info/Info_page_mobile_caseProcess?ajdbxt_info_id ='+info.ajdbxt_info_id" --%>
			</div>
		</div>



	</div>

	
	<script type="text/javascript"
		src="<%=basePath%>js/Info/mobile_caseOneDetails.js"></script>
	<script type="text/javascript">
		mui.init();
		document.getElementById("btn").addEventListener("tap",function(){
			var webview = mui.openWindow({
				url:'<%=basePath%>info/Info_page_mobile_caseProcess?ajdbxt_info_id='+ajdbxt_info_id,
// 				extras:{
// 					ajdbxt_info_id:'info.ajdbxt_info_id'  //扩展参数
// 				}
			});
		//	console.log(webview.name);//输出mui字符串
		})
	</script>
<%-- <%-- 	<script type="text/javascript"> --%>
<!--  		case_details();  -->
<%-- <%-- 	</script> --%>
</body>

</html>