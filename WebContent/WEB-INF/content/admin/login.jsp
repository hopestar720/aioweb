<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	<title>山西省食品药品电子监管平台</title>
  	<meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>
	<meta http-equiv="Cache-Control" content="no-store"/>
	<meta http-equiv="Pragma" content="no-cache"/>
	<meta http-equiv="Expires" content="0"/>
	<style type="text/css">
	body {
		margin-left: 0px;
		margin-top: 0px;
		margin-right: 0px;
		margin-bottom: 0px;
		overflow:hidden;
	}
	.input_login1{height:20px;width:100px;font-names:"宋体";font-size:"9pt";background-color:#87adbf; border:solid 1px #153966; font-size:12px; color:#283439;}
	.STYLE3 {font-size: 12px; color: #adc9d9; }
</style>
</head>

<body class="body_pmos" onload="document.all.strUserName.focus();" >
<form method="post" name="loginform" action="" id="loginform">
<table width="100%"  height="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td bgcolor="#1075b1">&nbsp;</td>
  </tr>
  <tr>
    <td height="608" background="images/login_03.gif"><table width="847" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td height="318" background="images/login_04.gif">&nbsp;</td>
      </tr>
      <tr>
        <td height="84">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="381" height="84" background="images/login_06.gif"></td>
            <td width="162" valign="middle" background="images/login_07.gif">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
	               	<td height="24" width="50" valign="bottom">
	               		<div align="right"><span class="STYLE3">用户名：</span></div>
	               	</td>
					<td height="24" valign="bottom">
						<div align="left">
							<input id="loginname" type="text" class="input_login1" onkeypress="if(event.keyCode==13) $('#loginform').submit();" name="strUserName">
						</div>
	                </td>
				</tr>
              	<tr>
					<td height="24" valign="bottom"><div align="right"><span class="STYLE3">密&nbsp;&nbsp;码：</span></div></td>
					<td height="24" valign="bottom">
						<input class="input_login1" onkeypress="if(event.keyCode==13) $('#loginform').submit();" type="password" name="strPassWord">
					</td>
				</tr>
            </table>
            </td>
            <td width="26"><img src="images/login_08.gif" width="26" height="84"></td>
            <td width="67" background="images/login_09.gif">
            <table width="100%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td height="25">
                	<div align="center">
                		<a href="javascript:$('#loginform').submit();"><img alt="登陆" src="images/dl.gif" border="0"></a>
                   </div>
				</td>
              </tr>
              <tr>
                <td height="25">
                	<div align="center">
                		<a href=""><img alt="重置" src="images/cz.gif" border="0"></a>
                    </div>
                 </td>
              </tr>
            </table></td>
            <td width="211" style="background-image:url(images/login_10.gif)">&nbsp;</td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td height="206" style="background-image:url(images/login_11.gif)">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td bgcolor="#152753">&nbsp;</td>
  </tr>
</table>
    </form>
</body>
</html>
