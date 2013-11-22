
/**
* Based on JQuery
*/

/**
 * #######函数概要########
 * 1.设置下拉框选中：setSelectValue(objId,optVal)；
 * 2.选中多选按钮：setCheckValue(chkName,chkValue)；
 * 3.选中单选按钮：setRadioValue(rdoName,rdoValue)；
 * 4.获得多选按钮选中值：getCheckValue(chkName)；
 * 5.获取单选按钮选中值：getRadioValue(rdoName)；
 * #######函数概要########
 */

/**
 * 选中下拉框中数据
 */
function setSelectValue(objId,optVal){
	$(objId+" option[value='"+optVal+"']").attr("selected", true); 
}

/**
 * 选中多选按钮
 * @param chkName
 * @param chkValue
 */
function setCheckValue(chkName,chkValue){
	$("input[type='checkbox'][@name='"+chkName+"'][value='"+chkValue+"']").attr("checked", true);
}

/**
 * 选中单选按钮
 * @param rdoName
 * @param rdoValue
 */
function setRadioValue(rdoName,rdoValue){
	$("input[type='radio'][@name='"+rdoName+"'][value='"+rdoValue+"']").attr("checked", true);
}

/**
 * 获得多选按钮选中值
 * @param chkName
 * @returns {Array}
 */
function getCheckValue(chkName){
	var chk_value = [];
	$("input[type='checkbox'][name='"+chkName+"']:checked").each(function(){
		chk_value.push($(this).val());    
	});
	return chk_value;
}

/**
 * 获取单选按钮选中值
 * @param rdoName
 * @returns
 */
function getRadioValue(rdoName){
	return $("input[type='radio'][name='"+rdoName+"']:checked").val();
}

function getCurrentRow(){
	
}

/**
 * 动态获取下拉列表
 */
function getDropData(id,data,showText,defSelect){
	if(id == void 0){
		alert("common.js->getDropData():指定初始化的下拉框对象ID！");
		return;
	}
	$(id).empty();
	if(showText == void 0){
		$(id).append("<option id='' value=''>--请选择--</option>");
	}else{
		$(id).append("<option id='' value=''>--"+showText+"--</option>");
	}

	$.each(data.root, function(index, data) {
			$(id).append("<option  id='" + data.id + "' value='"+ data.id + "'>" + data.name+ "</option>");
	});
}

/**
 * 异步请求数据
 */
function sendData(url,param){
	var result = "";
	if(param == void 0){
		param = {};
	}
	$.ajax({
		url:url,
		type:'post',
		data:param,
		async:false,
		success:function(data){
			result = data;
		},
		error:function(){
			alert("common.js->sendData:请求出错,请重试！");
		}
	});
	return result;
}

