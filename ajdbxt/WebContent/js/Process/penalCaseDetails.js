//涉案财物为是时，出现涉案财物是否入库
function case_propertyBtnClick() {
	var case_property = document.getElementsByName("case_property");
	for (var num = 0; num < 2; num++) {
		var case_property_value = case_property[num].value;
		if (case_property[num].checked) {
			if (case_property_value == "1") {
				document.getElementById("property_storage_div").style.display = "block";
			}else{
				document.getElementById("property_storage_div").style.display = "none";
			}
		} 
	}
}

//根据第一次强制措施所选进行判断

//选择拘留时，显示出拘留延长期限
function mandatory_measuresBtnClick() {
	var mandatory_measuresOne = document.getElementsByName("mandatory_measuresOne");
	for (var num = 0; num < 3; num++) {
		var mandatory_measuresOne_value = mandatory_measuresOne[num].value;
		if (mandatory_measuresOne[num].checked) {
			if (mandatory_measuresOne_value == "0") {
				document.getElementById("detention_delay_date").style.display = "block";
			}
			else if(mandatory_measuresOne_value == "1"){
				document.getElementById("detention_delay_date").style.display = "none";
			}
			else{
				document.getElementById("detention_delay_date").style.display = "none";
			}
		} 
	}
}
