<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<link href="css/general.css" rel="stylesheet" type="text/css"/>
	<link href="css/main.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="js/transport.js"></script>
	<script type="text/javascript" src="js/prototype.js"></script>
	<script type="text/javascript" src="js/common.js"></script>
	<script type="Text/Javascript" language="JavaScript">
		if (window.top != window){
		  window.top.location.href = document.location.href;
		}
	</script>
	<frameset id="frame-top" rows="127,13,*" framespacing="0" border="0">
	  	<frame src="top.jsp" id="header-frame" name="header-frame" frameborder="0" scrolling="no">
	  	<frame src="drag_up.jsp" id="drapup-frame" name="drapup-frame" frameborder="0" scrolling="no">
		<frameset id="frame-body" cols="200, 10, *" framespacing="0" border="0">
		    <frame src="left.jsp" id="menu-frame" name="menu-frame" frameborder="0" scrolling="yes">
		    <frame src="drag.jsp" id="drag-frame" name="drag-frame" frameborder="0" scrolling="no">
			<frame src="welcome.jsp" id="main-frame" name="main-frame" frameborder="0" scrolling="yes">
	  	</frameset>
	</frameset>
</head>
<body>
	
</body>
</html>