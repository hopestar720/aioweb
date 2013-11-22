<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<!-- A series of hidden module templates carried within the page, which can be modified easily -->
<div class="module_template"><!-- A default template is required here without an ID -->
	<div class="moduleFrame">
		<div class='moduleHeader'>
			<div class='moduleTitle'></div>
			<div class='moduleActions'>
				<b title="Refresh" class="actionRefresh"></b>
				<b title="Collapse" class="actionMin"></b>
				<b title="Expand" class="actionMax"></b>
				<b title="Close" class="actionClose"></b>
			</div>
		</div>
		<div class='moduleContent'>
			<img src="${ctx}/jpolite/img/loading.gif" alt="Loading..."/> Loading...
		</div>
	</div>
</div>
<div class="module_template" id="B"><!-- The ID 'B' will be used as the index to match all module layout definition with mt:'B' -->
	<div class="moduleFrame" style="border:6px groove red">
		<div class='moduleContent' style="background:#ffc">
			<img src="${ctx}/jpolite/img/loading.gif" alt="Loading..."/> Loading...
		</div>
		<div class='moduleHeader'>
			<div class='moduleTitle'></div>
			<div class='moduleActions'>
				<b title="Collapse" class="actionMin"></b>
				<b title="Expand" class="actionMax"></b>
				<b title="Close" class="actionClose"></b>
			</div>
		</div>
	</div>
</div>
<div class="module_template" id="C"><!-- The ID 'C' will be used as the index to match all module layout definition with mt:'C' -->
	<div class="moduleFrame" style="border:6px double green">
		<div class='moduleContent' style="background:#cff">
			<img src="${ctx}/jpolite/img/loading.gif" alt="Loading..."/> Loading...
		</div>
		<div class='moduleHeader'>
			<div class='moduleTitle'></div>
			<div class='moduleActions'>
				<b title="Collapse" class="actionMin"></b>
				<b title="Expand" class="actionMax"></b>
				<b title="Close" class="actionClose"></b>
			</div>
		</div>
	</div>
</div>