/**
 * 正则表达式校验通用组件
 * by lijiangwei
 * date 2013-5-18
 */

//验证邮件
var REG_MAIL = /^[\w-]+(\.[\w-]+)*@[\w-]+(\.[\w-]+)+$/;
//验证QQ号
var REG_QQ = /^[1-9]\d{4,8}$/;
//验证网址
var REG_URL = /^http:\/\/[A-Za-z0-9]+\.[A-Za-z0-9]+[\/=\?%\-&_~`@[\]\':+!]*([^<>\"\"])*$/;
//验证身份证
var REG_IDCARD = /^\d{15}(\d{2}[A-Za-z0-9])?$/;
//验证邮编
var REG_ZIPCODE = /^[1-9]\d{5}$/;
//验证手机号
//var REG_MOBILE = /^[1][3,8][0-9]{9}$/;
var REG_MOBILE = /^((\(\d{2,3}\))|(\d{3}\-))?13\d{9}$/;
//验证电话
var REG_TEL = /^((\(\d{2,3}\))|(\d{3}\-))?(\(0\d{2,3}\)|0\d{2,3}-)?[1-9]\d{6,7}(\-\d{1,4})?$/;
//验证IP地址
//var REG_IP = /^(\d+)\.(\d+)\.(\d+)\.(\d+)$/g;
var REG_IP = "^(d{1,2}|1dd|2[0-4]d|25[0-5]).(d{1,2}|1dd|2[0-4]d|25[0-5]).(d{1,2}|1dd|2[0-4]d|25[0-5]).(d{1,2}|1dd|2[0-4]d|25[0-5])$";
//验证端口号
var REG_PORT = 0;


//验证空格
var REG_BLANK = "^[ ]+$";
//验证必填
var REG_NULL = /.+/;
//验证正整数
var REG_I_INT = "^[0-9]+$";
//验证汉字、数字，字母组成串
var REG_CNC = "^[0-9a-zA-Z\u4e00-\u9fa5]+$";
//验证integer
//var REG_INT = /^[-]{0,1}[0-9]{1,}$/;
var REG_INT = /^[-\+]?\d+$/;
var REG_DOUBLE = /^[-\+]?\d+(\.\d+)?$/;
var REG_ENGLISH = /^[A-Za-z]+$/;
var REG_CHINESE = /^[\u0391-\uFFE5]+$/;
var REG_SEPICAL = /^(([A-Z]*|[a-z]*|\d*|[-_\~!@#\$%\^&\*\.\(\)\[\]\{\}<>\?\\\/\'\"]*)|.{0,5})$|\s/;
//验证供货币
var REG_CURRENCY = /^\d+(\.\d+)?$/;
//验证HTML代码
var REG_HTML_CODE = /<(.*)>.*<\/\1>|<(.*) \/>/;
//验证前后空格
var REG_TRIM = "(^\s*)|(\s*$)";
//验证回车换行
var REG_ENTRY = "\n[\s| ]*\r";


//验证邮箱提示消息
var REG_MSG_PRE_SUCCESS = "太棒啦,";
var REG_MSG_PRE_FAILURE = "温馨提示:";
var REG_MSG_MAIL_SUCCESS = REG_MSG_PRE_SUCCESS+"您输入的邮箱是正确的";
var REG_MSG_MAIL_FAILURE = "您提供的邮箱未通过审核，请核对是否输入有误";

var REG_MSG_MOBILE_SUCCESS = REG_MSG_PRE_SUCCESS+"您输入的手机号码是正确的";
var REG_MSG_MOBILE_FAILURE = REG_MSG_PRE_FAILURE+"您提供的手机号码未通过审核，请核对是否输入有误";

var REG_MSG_TEL_SUCCESS = REG_MSG_PRE_SUCCESS+"您输入的电话号码是正确的";
var REG_MSG_TEL_FAILURE = REG_MSG_PRE_FAILURE+"您提供的电话号码未通过审核，请核对是否输入有误";

var REG_MSG_IP_SUCCESS = REG_MSG_PRE_SUCCESS+"您输入的IP地址是正确的";
var REG_MSG_IP_FAILURE = REG_MSG_PRE_FAILURE+"您提供的IP地址未通过审核，请核对是否输入有误";

var REG_MSG_PORT_SUCCESS = REG_MSG_PRE_SUCCESS+"您输入的端口号是正确的";
var REG_MSG_PORT_FAILURE = REG_MSG_PRE_FAILURE+"您提供的端口号未通过审核，请核对是否输入有误";

var REG_MSG_BLANK_SUCCESS = REG_MSG_PRE_SUCCESS+"您输入的内容是正确的";
var REG_MSG_BLANK_FAILURE = REG_MSG_PRE_FAILURE+"您提供的内容为空，请核对是否输入有误";

var REG_MSG_EMPTY_SUCCESS = REG_MSG_PRE_SUCCESS+"您输入的内容是正确的";
var REG_MSG_EMPTY_FAILURE = REG_MSG_PRE_FAILURE+"您提供的内容为空，请核对是否输入有误";

var REG_MSG_NUM_SUCCESS = REG_MSG_PRE_SUCCESS+"您输入的数字是正确的";
var REG_MSG_NUM_FAILURE = REG_MSG_PRE_FAILURE+"您提供的数字未通过审核，请核对是否输入有误";

var REG_MSG_ID_SUCCESS = REG_MSG_PRE_SUCCESS+"您输入的身份证号是正确的";
var REG_MSG_ID_FAILURE = REG_MSG_PRE_FAILURE+"您提供的身份证号未通过审核，请核对是否输入有误";

var REG_MSG_ONLY_CNC_SUCCESS = REG_MSG_PRE_SUCCESS+"您输入的内容是正确的";
var REG_MSG_ONLY_CNC_FAILURE = REG_MSG_PRE_FAILURE+"您提供的内容只能包含（中文、英文和数字），请核对是否输入有误";

var REG_MSG_ONLY_NC_SUCCESS = REG_MSG_PRE_SUCCESS+"您输入的邮箱是正确的";
var REG_MSG_ONLY_NC_FAILURE = REG_MSG_PRE_FAILURE+"您提供的内容只能包含（数字和英文），请核对是否输入有误";

var REG_MSG_DATE_SUCCESS = REG_MSG_PRE_SUCCESS+"您输入的日期是正确的";
var REG_MSG_DATE_FAILURE = REG_MSG_PRE_FAILURE+"您提供的日期未通过审核，请核对是否输入有误";
var REG_MSG_DATE_AREA_SUCCESS = REG_MSG_PRE_SUCCESS+"您输入的起绐日期和截至日期是正确的";
var REG_MSG_DATE_AREA_FAILURE = REG_MSG_PRE_FAILURE+"您提供的起始日期超出了截至日期范围，请核对是否输入有误";


function isMatch(value,regex){
	var flag = regex.test(value);
	return flag;
}

function isMatchStr(value,regex){
	alert(value);
	var reg = new RegExp(regex);
	var flag = reg.test(value);
	return flag;
}

function checkDateArea(startDate,endDate){
	if(startDate>endDate){
		return false;
	}else{
		return true;
	}
}