/**
 * 初始化弹出层
 * @param id（弹出层对象ID，如：“#addDiv”或"#addDiv,#updateDiv"）
 * @param width（弹出层宽度，如：450）
 * @param buttons
 */
function initDialog(id,width,buttons){
	if(id == void 0){
		alert("common.js->initDialog:请指定初始化弹出层ID");
	}
	if(width == void 0){
		width = 450;
	}
	
	if(buttons == void 0){
		buttons = {
				"取消" : function() {
					$(this).dialog("close");
				},
				"确定" : function() {
					$(this).dialog("close");
				}
			};
	}
	var json = {
			autoOpen : false,
			bgiframe : true,
			modal : true,
			width : width,
			buttons : buttons
		};
	$(id).dialog(json);
}