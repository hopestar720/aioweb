<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/static/common/taglibs.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="keywords" content="星辉官方网站,软件,星辉" />
	<meta name="description" content="星辉软件" />
	<meta name="generator" content="星辉软件" />
	<meta name="author" content="星辉软件" />
	<meta name="copyright" content="星辉软件" />
	<meta name="copyright" content="星辉软件" />
	<title><s:text name="page.portal.index"/></title>
	<%@include file="/static/common/include.jsp" %>
	
	<link rel="stylesheet" href="${ctx}/static/jslib/jpolite/css/screen.css" type="text/css" media="screen"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/static/jslib/jpolite/css/jquery.gritter.css"/>
	<link rel="stylesheet" type="text/css" href="${ctx}/static/jslib/jpolite/css/jqModal.css"/>
	<link rel="alternate stylesheet" title="classic" href="${ctx}/static/jslib/jpolite/css/style0.css" type="text/css" media="screen"/>
	<link rel="alternate stylesheet" title="silver" href="${ctx}/static/jslib/jpolite/css/style.css" type="text/css" media="screen"/>
	<link rel="stylesheet" title="modern" href="${ctx}/static/jslib/jpolite/css/style1.css" type="text/css" media="screen"/>
	<!-- Sample jQuery UI Theme CSS file from http://jqueryui.com/themeroller/ -->
	<link rel="stylesheet" type="text/css" href="${ctx}/static/jslib/jpolite/css/start/jquery-ui-1.7.2.custom.css" media="screen"/>
	<!--[if IE]>
		<link rel="stylesheet" href="${ctx}/static/jslib/jpolite/css/ie.css" type="text/css" media="screen"/>
	<![endif]-->
</head>
<body class='normal'>
<div id="header">
	<div id="logo"></div>
	<div id="title">A Lightweight Portal Framework based on jQuery</div>
	<ul id="main_nav">
		<li id="t1"><b class="hover">About V2</b>About V2</li>
		<li id="t2"><b class="hover">Layout</b><span>Layout</span><dl><dd id="t21">Same content, another layout</dd></dl></li>
		<li id="t3"><b class="hover">Module</b>Module</li>
		<li id="t4"><b class="hover">Controls</b><span>Controls</span><dl><dd id="t41">Tabs &amp; Accordion</dd><dd id="t42">jQuery UI with Theme</dd></dl></li>
		<li id="t5"><b class="hover">Customize</b>Customize</li>
		<li id="t6"><b class="hover">XDO</b><span>XDO</span><dl><dd id="t61">Programming Model</dd></dl></li>
		<li id="t8"><b class="hover">Download</b>Download</li>
	</ul>
	<div id="info_bar">
		<b id="loading"></b><b id="menu_btn"></b><i class="left hint">Module menu</i>
		<i class="right">
			<b id="maxAll" title="Expand All Modules"></b>
			<b id="minAll" title="Collapse All Modules"></b>
		</i>
		<i class="hint right">Adjust module appearance</i>
	</div>
</div>

<div id="content" class="container">
	<div id="module_menu" class="jqmWindow"></div>
	<div id="c1" class="cc"></div>
	<div id="c2" class="cc"></div>
	<div id="c3" class="cc"></div>
	<div id="c4" class="cc">
		<div class="module blue" id="m104:t1">
			<div class="moduleFrame">
				<div class='moduleHeader'>
					<div class='moduleTitle'>Key New Features</div>
					<div class='moduleActions'>
						<b title="Collapse" class="actionMin"></b>
						<b title="Expand" class="actionMax"></b>
						<b title="Close" class="actionClose"></b>
					</div>
				</div>
				<div class='moduleContent'>
					<h4>SEO Support</h4>
					<p>Note this module is a static one pre-loaded in <b>index.html</b>, which is visible to search engines.</p>
					<script type="text/javascript">
						function switchTheme(t) {
							$('link[title]','head').each(function(){
								this.disabled = true;
								this.disabled = (this.title != t); 
							});
							return false;
						};
					</script>
					<fieldset><legend>Live Theme Switcher</legend>
						<p>
						<button onclick="switchTheme('modern')"><img style="width:126px;height:66px" src="img/t1.png" alt="Modern"/></button>
						<button onclick="switchTheme('silver')"><img style="width:126px;height:66px" src="img/t2.png" alt="Silver"/></button>
						<button onclick="switchTheme('classic')"><img style="width:126px;height:66px" src="img/t3.png" alt="Classic"/></button>
						</p>
					</fieldset>
					<div class="span-11">
						<h4>Layout Persistence</h4>
						<p>Module layout can be stored and retrieved to your selected store.</p>
						<button onclick="$.cookie('jpolite2layout',null)">Reset Layout</button>
						<p class="clear">Any drag-n-drop operation will trigger save layout. To resume the original layout, press above button then refresh.</p>						
					</div>
					<div class="span-11 last">
						<h4>Popup &amp; Dropdown Menu</h4>
						<p>A module popup menu is provided for users to add modules to current tab.</p>
						<button onclick="$('#menu_btn').click()">Show Module Menu</button>
						<p class="clear">Dropdown menu is now supported which provides additional navigation support in addition to horizontal tabs.</p>						
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

<div id="footer">
<div id="footer_bar"><a href="http://trilancer.wordpress.com/">Trilancer Blog</a> | <a href="http://www.jquery.com">About jQuery</a> | <a href="http://www.netvibes.com">About Netvibes</a></div>
Copyright 2009 (C) Trilancer.com
</div>

<%@include file="template.jsp" %>

<script type="text/javascript" src="${ctx}/static/jslib/jpolite/js/jquery.js"></script>
<script type="text/javascript" src="${ctx}/static/jslib/jpolite/js/jquery.cookie.js"></script>
<script type="text/javascript" src="${ctx}/static/jslib/jpolite/js/jquery-ui-1.7.2.custom.min.js"></script>
<script type="text/javascript" src="${ctx}/static/jslib/jpolite/js/modules.js"></script>
<script type="text/javascript" src="${ctx}/static/jslib/jpolite/js/jquery.gritter.js"></script>
<script type="text/javascript" src="${ctx}/static/jslib/jpolite/js/chain-0.2.pack.js"></script>
<script type="text/javascript" src="${ctx}/static/jslib/jpolite/js/jquery.kwicks-1.5.1.pack.js"></script>
<script type="text/javascript" src="${ctx}/static/jslib/jpolite/js/jquery.lavalamp.1.3.2-min.js"></script>
<script type="text/javascript" src="${ctx}/static/jslib/jpolite/js/jqModal.js"></script>
<script type="text/javascript" src="${ctx}/static/jslib/jpolite/js/jpolite.core.js"></script>
<script type="text/javascript" src="${ctx}/static/jslib/jpolite/js/jpolite.ext.js"></script>
</body>
</html>