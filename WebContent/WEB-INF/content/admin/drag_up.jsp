<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
<style type="text/css">
body {
  margin: 0;
  padding: 0;
  background: #749BA7;
  cursor: s-resize;		/* 向下移动 */		
}
</style>
<script type="text/javascript" language="JavaScript">
<!--

var Browser = new Object();

Browser.isMozilla = (typeof document.implementation != 'undefined') && (typeof document.implementation.createDocument != 'undefined') && (typeof HTMLDocument != 'undefined');
Browser.isIE = window.ActiveXObject ? true : false;
Browser.isFirefox = (navigator.userAgent.toLowerCase().indexOf("firefox") != - 1);
Browser.isSafari = (navigator.userAgent.toLowerCase().indexOf("safari") != - 1);
Browser.isOpera = (navigator.userAgent.toLowerCase().indexOf("opera") != - 1);

var pic = new Image();
pic.src="images/arrow_down.gif";
var flag = false;

function toggleMenu()
{
  frmTop = window.parent.document.getElementById('frame-top');
  imgArrow = document.getElementById('img');
  if(flag){
    frmTop.rows="127,13,*";
    imgArrow.src = "images/arrow_up.gif";
    flag =false;
  }else{
    frmTop.rows="0, 13, *";
    imgArrow.src = "images/arrow_down.gif";
	flag =true;
  }
}


var orgY = 0;
document.onmousedown = function(e)
{
  var evt = Utils.fixEvent(e);
  orgY = evt.clientY;

  if (Browser.isIE) document.getElementById('tbl').setCapture();
}

document.onmouseup = function(e)
{
  var evt = Utils.fixEvent(e);

  frmBody = window.parent.document.getElementById('frame-top');
  frmHeight = frmBody.rows.substr(0, frmBody.rows.indexOf(','));
  frmHeight = (parseInt(frmHeight) + (evt.clientY - orgY));

  if(frmHeight < 127){
  	frmBody.rows = frmHeight + ", 13, *";
  }else{
	frmBody.rows = 127 + ", 13, *";
  }

  if (Browser.isIE) document.releaseCapture();
}


var Utils = new Object();

Utils.fixEvent = function(e)
{
  var evt = (typeof e == "undefined") ? window.event : e;
  return evt;
}
//-->
</script>

</head>
<body onselect="return false;">
<table cellspacing="0" cellpadding="0" id="tbl" width="100%" border="0" style="padding-top: 0px;">
  <tr>
  	<td align="center">
		<a href="javascript:toggleMenu();" style="padding-top: 0;margin-top: 0px;">
			<img src="images/arrow_up.gif" style="padding-top: 0;margin-top: 0px;" width="40" height="10" id="img" border="0" />
		</a>
  	</td>
  </tr>
</table>
</body>
</html>