function getFormatDate(){
	var date = new Date();
	var year = date.getFullYear();
	var month = date.getMonth();
	var day = date.getDay();
	var hour = date.getHours();
	var min = date.getMinutes();
	var sec = date.getSeconds();
}
/**
 * 初始化日期选择控件
 * @param obj（如："#dt_search"）
 */
function initDatePicker(obj){
	$(obj).datepicker({
		dateFormat: "yy-mm-dd",
		defaultDate: "+1w",
		changeMonth: true,
		numberOfMonths: 1,
		beforeShow: function (i, e) {  
			var z = jQuery(i).closest(".ui-dialog").css("z-index") + 1;  
			e.dpDiv.css('z-index', z);  
		}
	});
}

/**
 * 初始化开始结束日期控件（无时间）
 * @param objFrom（开始日期控件ID，如"#dt_from"）
 * @param objTo（结束日期控件ID，如"#dt_to"）
 */
function initFromToDatePicker(objFrom,objTo){
	$(objFrom).datepicker({
		dateFormat: "yy-mm-dd",
		defaultDate: "+1w",
		changeMonth: true,
		numberOfMonths: 1,
		beforeShow: function (i, e) {  
			var z = jQuery(i).closest(".ui-dialog").css("z-index") + 1;  
			e.dpDiv.css('z-index', z);  
		},
		onClose: function( selectedDate ) {
			$( objTo ).datepicker("option", "minDate", selectedDate );
			}
	});
	$(objTo).datepicker({
		dateFormat: "yy-mm-dd",
		defaultDate: "+1w",
		changeMonth: true,
		numberOfMonths: 1,
		beforeShow: function (i, e) {  
			var z = jQuery(i).closest(".ui-dialog").css("z-index") + 1;  
			e.dpDiv.css('z-index', z);
		},
		onClose: function( selectedDate ) {
			$( objFrom ).datepicker( "option", "maxDate", selectedDate );
			}
	});
}