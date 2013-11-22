var pageNumber = 1;
var pageSize = 10;

//获取分页数据
function getPageList(pageNumber,pageSize,param){
	
}

/**
 * 初始化分页控件
 * @param id
 */
function initPagination(id){
	//初始化分页
	$(id).pagination({
		total : count,
		showPageList : true,
		onSelectPage : function(pageNumber, pageSize) {
			$(this).pagination('loading');
			//调用分页列表事件
			refreshPage();
			$(this).pagination('loaded');
		},
		onChangePageSize : function(pageNumber, pageSize) {
			$(this).pagination('loading');
			refreshPage();
			$(this).pagination('loaded');
		}
	});
}

/**
 * 分页控件刷新方法
 */
function refreshPage(){
	//刷新总数
	$('#pagiation').pagination({
		pageNumber : pageNumber,
		pageSize : pageSize,
		total : count
	});
}