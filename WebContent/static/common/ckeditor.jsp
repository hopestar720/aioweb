<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@include file="/static/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>文本编辑器</title>

<link type="text/css" href="${ctx}/static/jslib/ckeditor/contents.css"/>
<script type="text/javascript" src="${ctx}/static/jslib/ckeditor/ckeditor.js"></script>
<script type="text/javascript">

</script>
</head>
<body>
<div id="divEditor"></div>

<script>
CKEDITOR.appendTo("divEditor");
</script>

</body>
</html>