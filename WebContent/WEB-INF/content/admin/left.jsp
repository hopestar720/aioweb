<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/general.css" rel="stylesheet" type="text/css" />
<link href="css/leftmenu.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/prototype.js"></script>
<script language="JavaScript">
<!--
var noHelp   = "<p align='center' style='color: #666'>暂时还没有该部分内容</p>";
var helpLang = "zh-cn";
//-->
</script>
</head>
<div id="tabbar-div">
	<p>
		<span style="float:right; padding: 0px 10px;" >
			<a href="javascript:toggleCollapse();" style="font-size: 12px;color: red;">
				<img id="toggleImg" src="images/menu_minus.gif" width="9" height="9" border="0" alt="闭合" />
			</a>
		</span>
			<span class="tab-front" id="menu-tab">菜单</span>  
			<span class="tab-back" id="help-tab">帮助</span>
	</p>
</div>
<div id="main-div">
	<div id="menu-list">
			<ul>
				<li class="explode">
					基本信息
					<ul>
					<li class="menu-item"><a href="" target="main-frame">企业信息</a></li>
					<li class="menu-item"><a href="" target="main-frame">认证情况</a></li>
					<li class="menu-item"><a href="" target="main-frame">仓库信息</a></li>
					<li class="menu-item"><a href="" target="main-frame">关键人员</a></li>
					<li class="menu-item"><a href="" target="main-frame">执法记录（企）</a></li>
					</ul>
				</li>
			</ul>
			<ul>
				<li class="explode">
					监管信息
					<ul>
					</ul>
				</li>
			</ul>
			
			<ul>
				<li class="explode">
					药械经营
					<ul>
						<li class="menu-item"><a href="javascript:void(0)" target="main-frame">质量投诉处理</a></li>
						<li class="menu-item"><a href="javascript:void(0)" target="main-frame">养护记录</a></li>
						<li class="menu-item"><a href="javascript:void(0)" target="main-frame">质量跟踪记录</a></li>
					</ul>
				</li>
			</ul>
	</div>
	<div id="help-div" style="display: none">
		<h1 id="help-title"></h1>
		<div id="help-content"></div>
	</div>
</div>
<script language="JavaScript">
<!--
var collapse_all = "闭合";
var expand_all = "展开";
var collapse = true;

function toggleCollapse()
{
  var items = document.getElementsByTagName('LI');
  for (i = 0; i < items.length; i++)
  {
    if (collapse)
    {
      if (items[i].className == "explode")
      {
        toggleCollapseExpand(items[i], "collapse");
      }
    }
    else
    {
      if (items[i].className == "collapse")
      {
        toggleCollapseExpand(items[i], "explode");
      }
    }
  }

  collapse = !collapse;

  $('toggleImg').src = collapse ? 'images/menu_plus.gif' : 'images/menu_minus.gif';
  $('toggleImg').alt = collapse ? collapse_all : expand_all;
}

function toggleCollapseExpand(obj, status)
{
  if (obj.tagName.toLowerCase() == 'li' && obj.className != 'menu-item')
  {
    for (i = 0; i < obj.childNodes.length; i++)
    {
      if (obj.childNodes[i].tagName == "UL")
      {
        if (status == null)
        {
          if (obj.childNodes[1].style.display != "none")
          {
            obj.childNodes[1].style.display = "none";
            obj.className = "collapse";
          }
          else
          {
            obj.childNodes[1].style.display = "block";
            obj.className = "explode";
          }
          break;
        }
        else
        {
          obj.className = status;
          obj.childNodes[1].style.display = (status == "explode") ? "block" : "none";
        }
      }
    }
  }
}
$('menu-list').onclick=function(e)
{
  var obj = Utils.srcElement(e);
  toggleCollapseExpand(obj);
}

$('tabbar-div').onmouseover=function(e)
{
  var obj = Utils.srcElement(e);
  if (obj.className == "tab-back")
  {
    obj.className = "tab-hover";
  }
}

$('tabbar-div').onmouseout=function(e)
{
  var obj = Utils.srcElement(e);
  if (obj.className == "tab-hover")
  {
    obj.className = "tab-back";
  }
}

$('tabbar-div').onclick=function(e)
{
  var obj = Utils.srcElement(e);

  var mnuTab = $('menu-tab');
  var hlpTab = $('help-tab');
  var mnuDiv = $('menu-list');
  var hlpDiv = $('help-div');

  if (obj.id == 'menu-tab')
  {
    mnuTab.className = 'tab-front';
    hlpTab.className = 'tab-back';
    mnuDiv.style.display = "block";
    hlpDiv.style.display = "none";
  }

  if (obj.id == 'help-tab')
  {
    mnuTab.className = 'tab-back';
    hlpTab.className = 'tab-front';
    mnuDiv.style.display = "none";
    hlpDiv.style.display = "block";

    loc = parent.frames['main-frame'].location.href;
    pos1 = loc.lastIndexOf("/");
    pos2 = loc.lastIndexOf("?");
    pos3 = loc.indexOf("act=");
    pos4 = loc.indexOf("&", pos3);

    filename = loc.substring(pos1 + 1, pos2 - 4);
    act = pos4 < 0 ? loc.substring(pos3 + 4) : loc.substring(pos3 + 4, pos4);
    loadHelp(filename, act);
  }
}

/**
 * 载入帮助内容
 */
function loadHelp(filename, act)
{
  var doc = createDocument();
  var path = 'help/' + helpLang + '/' + filename + ".xml";

  $("help-title").innerHTML = '';
  $("help-content").innerHTML = noHelp;
  try
  {
    doc.load(path);

    var items = doc.getElementsByTagName('section');

    for (i = 0; i < items.length; i++)
    {
      if (items[i].getAttribute("id") == act)
      {
        var title = items[i].getElementsByTagName("title");
        $("help-title").innerHTML = (Browser.isIE) ? title[0].text : title[0].textContent;

        var content = items[i].getElementsByTagName("content");
        $("help-content").innerHTML = (Browser.isIE) ? content[0].text : content[0].textContent;

        break;
      };
    }
  }
  catch (e)
  {
    alert(e.message);
  }
}

/**
 * 创建XML对象
 */
function createDocument()
{
  var xmlDoc;

  // create a DOM object
  if (window.ActiveXObject)
  {
    try
    {
      xmlDoc = new ActiveXObject("Msxml2.DOMDocument.6.0");
    }
    catch (e)
    {
      try
      {
        xmlDoc = new ActiveXObject("Msxml2.DOMDocument.5.0");
      }
      catch (e)
      {
        try
        {
          xmlDoc = new ActiveXObject("Msxml2.DOMDocument.4.0");
        }
        catch (e)
        {
          try
          {
            xmlDoc = new ActiveXObject("Msxml2.DOMDocument.3.0");
          }
          catch (e)
          {
            alert(e.message);
          }
        }
      }
    }
  }
  else
  {
    if (document.implementation && document.implementation.createDocument)
    {
      xmlDoc = document.implementation.createDocument("","doc",null);
    }
    else
    {
      alert("Create XML object is failed.");
    }
  }
  xmlDoc.async = false;

  return xmlDoc;
}

var Browser = new Object();

Browser.isMozilla = (typeof document.implementation != 'undefined') && (typeof document.implementation.createDocument != 'undefined') && (typeof HTMLDocument != 'undefined');
Browser.isIE = window.ActiveXObject ? true : false;
Browser.isFirefox = (navigator.userAgent.toLowerCase().indexOf("firefox") != - 1);
Browser.isSafari = (navigator.userAgent.toLowerCase().indexOf("safari") != - 1);
Browser.isOpera = (navigator.userAgent.toLowerCase().indexOf("opera") != - 1);

var Utils = new Object();

Utils.srcElement = function(e)
{
  if (typeof e == "undefined") e = window.event;
  var src = document.all ? e.srcElement : e.target;

  return src;
}

Utils.fixEvent = function(e)
{
  var evt = (typeof e == "undefined") ? window.event : e;
  return evt;
}
//-->
</script>

</body>
</html>