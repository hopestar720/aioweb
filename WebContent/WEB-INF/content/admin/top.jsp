<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<style type="text/css">
body {
	margin-left: 0px;
	margin-top: 0px;
	margin-right: 0px;
	margin-bottom: 0px;
}
a:link{text-decoration:none ; color:white ;}
a:visited {text-decoration:none ; color:white;}
a:hover {text-decoration:underline ; color:white;}
a:active {text-decoration:none ; color:white;}
.STYLE1 {
	font-size: 12px;
	color: #000000;
}
.STYLE5 {font-size: 12}
.STYLE7 {font-size: 12px; color: #FFFFFF; }
.STYLE7 a{font-size: 12px; color: #FFFFFF; }
a img {
	border:none;
}
#nav {font-size: 12px; color: #fff;float:left;width:100%;height:35px;}
#nav li {float:left;list-style-type: none;color:#000000;}
#nav li a {display:block; text-decoration:none; color:#fff;line-height:25px;}
#nav li a span {padding:0 8px 0 0; margin:0 0 0 12px; display:block;}
.current {background:url(images/main_35.gif)}                          /*鼠标点击时变换背景，方便JS获取样式*/
.current span{color:#000;}                                                          /*鼠标点击时变换背景，方便JS获取样式*/
</style>
<script src="js/clock.js" type="text/javascript"></script>
<script language="javascript" type="text/javascript">
	function skip(menu,main) {	
        self.parent.document.getElementById('menu-frame').src="${ctx}"+menu;
        self.parent.document.getElementById('main-frame').src="${ctx}"+main;
        //window.parent.document.getElementById('header-frame').src="admin/top.jsp";
        this.document.getElementById('selectedCorporation').innerHTML= "";
    }
    
    function getBg(num) {
        var items = document.getElementsByTagName("li");
        for (id = 0; id < items.length; id++) {
            if (id == num) {
                document.getElementById("mynav" + id).className = "current";
            }
            else {
                document.getElementById("mynav" + id).className = "";
            }
        }
    }
</script>
</head>
<body>
<table width="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td height="57" background="images/main_03.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="417" height="57" background="images/main_01.gif">&nbsp;</td>
        <td>&nbsp;</td>
        <td width="281" valign="bottom"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="33" height="27"><img src="images/main_05.gif" width="33" height="27" /></td>
            <td width="248" background="images/main_06.gif"><table width="225" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td height="17"><div align="right"><a href="admin/purview/userinfo/inputChange.action" target="main-frame"><img src="images/pass.gif" width="69" height="17" /></a></div></td>
                <td><div align="right"><a href="common/globalgoon.html" target="main-frame"><img src="images/user.gif" width="69" height="17" /></a></div></td>
                <td><div align="right"><a href="j_spring_security_logout" target="_top"><img src="images/quit.gif" alt=" " width="69" height="17" /></a></div></td>
              </tr>
            </table></td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="40" background="images/main_10.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>  
        <td width="194" height="40" background="images/main_07.gif">&nbsp;</td>
        <td>
            <div id="nav">
                <ul>
                	
	                    <li><a href='javascript:void(0)' id="mynav0" broder="0" class="current" onclick="skip('/admin/leftWelcome.jsp','/ReportServer?reportlet=stat_index.cpt&op=view');getBg(0)"><span>首页</span></a></li>
						
						<li><a href="javascript:void(0)" id="mynav1" broder="0" onclick="skip('/admin/left_sale.jsp','/admin/welcome.jsp');getBg(1)" ><span>经营企业监管</span></a></li>
							
                </ul>
            </div>
        </td>
        <td width="248" background="images/main_11.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="16%"><span class="STYLE5"></span></td>
            <td width="75%"><div align="center"><span id="ele_clock" class="STYLE7"></span></div></td>
            <td width="9%">&nbsp;</td>
          </tr>
        </table></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="30" background="images/main_31.gif">
    <table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="8" height="30"><img src="images/main_28.gif" width="8" height="30" /></td>
        <td width="147" background="images/main_29.gif">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="24%">&nbsp;</td>
            <td width="43%" height="20" valign="bottom" class="STYLE1">管理菜单</td>
            <td width="33%">&nbsp;</td>
          </tr>
        </table></td>
        <td width="39"><img src="images/main_30.gif" width="39" height="30" /></td>
        <td>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
          	<td>
          		<span class="STYLE1" id="selectedCorporation">
					<font  id="titleDiv">
					</font>
		          	<font color="" style="font-weight: bold;"  id="corportionNameDiv">
		          	</font>
	          	</span>
          	</td>
            <td height="20" valign="bottom" align="right">
            <span class="STYLE1">当前登录用户：
            	<font color="" style="font-weight: bold;"></font>
            	&nbsp;&nbsp;所属单位：<font style="font-weight: bold;"></font>
            </span>
            </td>
            <td valign="bottom" class="STYLE1"><div align="right"></div></td>
          </tr>
        </table>
        </td>
        <!-- <td><a href="javascript:toggleMenu();" id="allscreen" style="font-size: 16px;color: black;">全屏显示</a></td> -->
        <td width="17"><img src="images/main_32.gif" width="17" height="30" /></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>
<script type="text/javascript">
    var clock = new Clock();
    clock.display(document.getElementById("ele_clock"));
    var flag = false;
    function toggleMenu()
	{
	  frmTop = self.parent.document.getElementById('frame-top');
	  frmBody = self.parent.document.getElementById("frame-body");
	  dragFrame = frmBody.document.getElementById("drag-frame");
	  imgArrow =  dragFrame.document.body.getElementsByTagName("img");
	  if(flag){
	    frmTop.rows="127,10,*";
	    frmBody.cols="200, 10, *";
	    imgArrow.src = "images/arrow_left.gif";
	    flag =false;
	    document.getElementById("allscreen").innerText = "全屏显示";
	  }else{
	  	frmTop.rows="0,10,*";
	    frmBody.cols="0, 10, *";
	    imgArrow.src = "images/arrow_right.gif";
		flag =true;
	    document.getElementById("allscreen").innerText = "退出全屏";
	    
	  }
	}

    function Request(argname)
    {
        var url = document.location.href;
        var arrStr = url.substring(url.indexOf("?")+1).split("&");
        //return arrStr;
        for(var i =0;i<arrStr.length;i++)
            {
            var loc = arrStr[i].indexOf(argname+"=");
            
            if(loc!=-1)
                {
                return arrStr[i].replace(argname+"=","").replace("?","");
                break;
                }
            }
        return "";
    }
    //使用Request("argname")即可得到参数值; 
    var data=Request("corporationName");  
    if(data == ""){
    	this.document.getElementById('titleDiv').innerHTML= "";
    }else{
    	getBg(1);
    	this.document.getElementById('titleDiv').innerHTML= "您所选企业:";
    }
    this.document.getElementById('corportionNameDiv').innerHTML= data;
</script>
