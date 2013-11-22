/**
 * 提供常用搜索服务
 * By MozillaEngine Engine
 * Date 2013-4-7
 */
(function() {
	var Trace = (function() {
		var TraceTool = function() {
		};
		TraceTool.buildLinkTrace = function() {
			var links = document.links, start = 0, len = links.length;
			for (; start < len; start++) {
				var linkObj = links[start];
				if (linkObj) {
					linkObj.onclick = function(evt) {
						evt.preventDefault();
						linkTrace(this);
					};
				}
			}
		};
		TraceTool.addTrace = function(title, href, key, btn) {
			trace(title, href, key, btn);
		};
		function linkTrace(node) {
			var title = node.getAttribute("title") || node.innerHTML, href = node
					.getAttribute("href");
			if (typeof title != "undefined" && typeof href != "undefined") {
				var pNode = $(node).parents("[track-key]").get(0), key = pNode ? (pNode
						.getAttribute("track-key") || "uncategory")
						: undefined;
				if (typeof key != "undefined") {
					trace(title, href, key, node);
				} else {
					restoreDefaultAction(node);
				}
			} else {
				restoreDefaultAction(node);
			}
		}
		function trace(title, href, key, btn) {
			if (title !== "" && href !== "" && key !== "") {
				var image = new Image(), cid = "unknown";
				cid = getChannelid();
				image.src = 'images/track.gif' + '?r=' + Math.random() + '&d='
						+ encodeURIComponent(document.location.host) + '&c='
						+ encodeURIComponent(key) + '&a='
						+ encodeURIComponent("") + '&u='
						+ encodeURIComponent(href) + '&cid='
						+ encodeURIComponent(cid) + '&t='
						+ encodeURIComponent(title);
				image.onload = function() {
					restoreDefaultAction(btn);
				};
				image.onerror = function() {
					restoreDefaultAction(btn);
				};
			} else {
				restoreDefaultAction(btn);
			}
		}
		function restoreDefaultAction(btn) {
			var btn = $(btn), tagName = $(btn).get(0).tagName.toLowerCase();
			switch (tagName) {
			case "a":
				window.location.href = btn.attr("href");
				break;
			case "form":
				btn.submit();
				break;
			}
		}
		function getChannelid() {
			try {
				var is_gecko = ((window.Components + '') == '[object nsXPCComponents]');
				if (is_gecko) {
					if (window.cehomepage) {
						var startup = window.cehomepage.startup;
						if (!startup.channelid) {
							return "unknown";
						} else {
							return startup.channelid();
						}
					}
				}
			} catch (e) {
				return "unknown";
			}
			return "unknown";
		}
		return TraceTool;
	})();
	var TAB_LIST = [
			{
				name : "web",
				label : "网页",
				lists : [ {
					name : 'baidu_web',
					label : '百度',
					url : 'http://localhost',
					classname : 'baidu-logo',
					href : 'http://www.baidu.com/index.php?tn=monline_5_dg',
					charset : 'utf-8'
				}, {
					name : 'google_web',
					label : '谷歌',
					url : 'http://localhost',
					classname : 'google-logo',
					href : 'http://i.firefoxchina.cn/parts/google_rdr.html'
				} ]
			},
			{
				name : "shopping",
				label : "购物",
				lists : [
						{
							name : 'taobao_shopping',
							label : '淘宝',
							url : 'http://localhost',
							classname : 'taobao-logo',
							href : 'http://pindao.huoban.taobao.com/tms/channel/channelcode.htm?pid=mm_28347190_2425761_9494155',
							charset : 'utf-8'
						}, {
							name : 'amazon_shopping',
							label : '亚马逊',
							url : 'http://localhost',
							classname : 'amazon-logo',
							href : 'http://www.amazon.cn/?source=Mozilla'
						}, {
							name : 'jingdong_shopping',
							label : '京东',
							url : 'http://localhost',
							classname : 'jingdong-logo',
							href : 'http://www.360buy.com',
							charset : 'gbk'
						} ]
			},
			{
				name : "music",
				label : "MP3",
				lists : [ {
					name : 'baidu_music',
					label : '百度',
					url : 'http://localhost',
					classname : 'baidu-logo',
					href : 'http://mp3.baidu.com/',
					charset : 'utf-8'
				} ]
			},
			{
				name : "image",
				label : "图片",
				lists : [
						{
							name : 'baidu_image',
							label : '百度',
							url : 'http://localhost',
							classname : 'baidu-logo',
							href : 'http://image.baidu.com/',
							charset : 'utf-8'
						},
						{
							name : 'google_image',
							label : '谷歌',
							url : 'http://localhost',
							classname : 'google-logo',
							href : 'http://www.google.com.hk/imghp?hl=zh-CN&tab=wi&client=aff-priustest&channel=hotlink',
							charset : 'utf-8'
						} ]
			},
			{
				name : "map",
				label : "地图",
				lists : [
						{
							name : 'google_map',
							label : '谷歌',
							url : 'http://localhost',
							classname : 'google-logo',
							href : 'http://ditu.google.cn/maps?hl=zh-CN&tab=il&client=aff-priustest&channel=hotlink',
							charset : 'utf-8'
						}, {
							name : 'baidu_map',
							label : '百度',
							url : 'http://localhost',
							classname : 'baidu-logo',
							href : 'http://map.baidu.com/',
							charset : 'utf-8'
						} ]
			},
			{
				name : "news",
				label : "新闻",
				lists : [
						{
							name : 'baidu_news',
							label : '百度',
							url : 'http://localhost',
							classname : 'baidu-logo',
							href : 'http://news.baidu.com/',
							charset : 'utf-8'
						},
						{
							name : 'google_news',
							label : '谷歌',
							url : 'http://localhost',
							classname : 'google-logo',
							href : 'http://news.google.com.hk/nwshp?hl=zh-CN&tab=in&client=aff-priustest&channel=hotlink',
							charset : 'utf-8'
						} ]
			},
			{
				name : "video",
				label : "视频",
				lists : [
						{
							name : 'google_video',
							label : '谷歌',
							url : 'http://localhost',
							classname : 'google-logo',
							href : 'http://www.google.com.hk/videohp?hl=zh-CN&ned=cn&tab=nv&client=aff-priustest&channel=hotlink',
							charset : 'utf-8'
						}, {
							name : 'baidu_video',
							label : '百度',
							url : 'http://localhost',
							classname : 'baidu-logo',
							href : 'http://video.baidu.com/',
							charset : 'utf-8'
						} ]
			}, {
				name : "zhidao",
				label : "知道",
				lists : [ {
					name : 'baidu_zhidao',
					label : '知道',
					url : 'http://localhost',
					classname : 'baidu-logo',
					href : 'http://zhidao.baidu.com/',
					charset : 'utf-8'
				} ]
			} ];
	var MozillaEngine = function(tabs) {
		var that = this;
		that.tabs = tabs;
		that.init();
	};
	MozillaEngine.prototype = {
		navEle : $("#searchNav"),
		radioEle : $("#searchRadio"),
		formEle : $("#searchForm"),
		keyEle : $("#searchKey"),
		logoEle : $("#searchLogo"),
		linkEle : $("#searchLogo a"),
		curEle : $("#searchCurrent"),
		submitEle : $("#searchBtn"),
		tipEle : $("#searchTip"),
		navIndex : 0,
		radioIndex : 0,
		lastword : "",
		tabs : null,
		bindEvents : function() {
			var that = this, formEle = that.formEle, submitEle = that.submitEle, keyEle = that.keyEle, tipEle = that.tipEle;
			$(document).on("mouseover", "#searchTip li", function() {
				var btn = $(this), selVal = btn.html();
				btn.addClass("selected").siblings().removeClass("selected");
				keyEle.val(selVal);
				that.lastword = selVal;
			});
			$(document).on("click", "#searchTip li", function() {
				that.addFormTrace();
			});
			submitEle.click(function() {
				that.addFormTrace();
			});
			$(document).on("click", function() {
				tipEle.hide();
			});
			keyEle.keyup(function() {
				that.showSearchTips(this);
			});
			keyEle.keydown(function(evt) {
				var btn = $(this), keyCode = evt.keyCode, tips = tipEle
						.find("li"), tlen = tips.length, selectTip = tipEle
						.find(".selected"), selVal = "";
				selectIndex = 0, changeIndex = 0;
				if (selectTip.length) {
					selectIndex = selectTip.eq(0).index();
				}
				if (keyCode == 38) {
					changeIndex = selectIndex > 0 ? selectIndex - 1 : tlen - 1;
					tips.eq(changeIndex).addClass("selected").siblings()
							.removeClass("selected");
					selVal = tipEle.find("li.selected").html();
					btn.val(selVal);
					that.lastword = selVal;
				} else if (keyCode == 40) {
					changeIndex = selectIndex < tlen - 1 ? selectIndex + 1 : 0;
					tips.eq(changeIndex).addClass("selected").siblings()
							.removeClass("selected");
					selVal = tipEle.find("li.selected").html();
					btn.val(selVal);
					that.lastword = selVal;
				} else if (keyCode == 13) {
					tipEle.hide();
					that.addFormTrace();
					return false;
				}
			});
		},
		init : function() {
			var that = this;
			that.bindEvents();
			that.showSearchFrame();
			Trace.buildLinkTrace();
		},
		addFormTrace : function() {
			var that = this, formEle = that.formEle, title = formEle
					.attr("track-title"), url = formEle.attr("track-url"), key = formEle
					.attr("track-key");
			Trace.addTrace(title, url, key, formEle);
		},
		showSearchTips : function(btn) {
			var that = this, btn = $(btn), tipEle = that.tipEle, keyEle = that.keyEle, word = btn
					.val(), lastWord = that.lastword, kOffset = keyEle.offset();
			tipEle.css({
				"left" : kOffset.left + "px",
				"top" : kOffset.top + keyEle.height()
			});
			if (word != "" && word != lastWord) {
				that.lastword = word;
				var tabName = that.tabs[that.navIndex].name, searchURL1 = "http://suggestion.baidu.com/su?", searchURL2 = "http://nssug.baidu.com/su?", tipSRC = searchURL1, tipParams = "";
				switch (tabName) {
				case 'music':
					tipSRC = searchURL2;
					tipParams = "prod=mp3&";
					break;
				case 'image':
					tipSRC = searchURL2;
					tipParams = "prod=image&";
					break;
				case 'news':
					tipSRC = searchURL2;
					tipParams = "prod=news&";
					break;
				case 'video':
					tipSRC = searchURL2;
					tipParams = "prod=video&";
					break;
				case 'zhidao':
					tipSRC = searchURL2;
					tipParams = "prod=zhidao&";
					break;
				}
				tipParams += "wd=" + encodeURIComponent(word);
				var htm = "<script charset='gb2312' type='text/javascript' src='"
						+ tipSRC + tipParams + "'></script>";
				$(document.body).append(htm);
			}
		},
		showSearchFrame : function() {
			var that = this;
			that.showSearchNav();
			that.showSearchInput(0, 0);
			that.showSearchRadio(0, 0);
		},
		showSearchNav : function() {
			var that = this, tabs = that.tabs, tlen = tabs.length, navEle = that.navEle, engines = that.engines, index = 0, htm = "";
			$.each(tabs, function(i, n) {
				htm += "<li"
						+ (i == 0 ? " class='current'"
								: i == tlen - 1 ? " class='last'" : "") + ">"
						+ n.label + "</li>";
			});
			navEle.html(htm);
			var navList = navEle.children();
			navList.click(function() {
				var btn = $(this), bIndex = btn.index();
				navList.removeClass("current");
				btn.addClass("current");
				that.showSearchInput(bIndex, 0);
				that.showSearchRadio(bIndex, 0);
			});
		},
		showSearchInput : function(navIndex, radioIndex) {
			var that = this, formEle = that.formEle, curEle = that.curEle, logoEle = that.logoEle, linkEle = that.linkEle, tab = that.tabs[navIndex], engineList = tab.lists;
			engine = engineList[radioIndex], eName = engine.name,
					eLabel = engine.label, eURL = engine.url;
			formEle.attr({
				"action" : eURL,
				"accept-charset" : engine.charset,
				"track-title" : eLabel,
				"track-url" : eURL,
				"track-key" : "search_" + eName
			});
			curEle.val(engine.name);
			logoEle.attr({
				"class" : engine.classname,
				"track-key" : "search_" + eName
			});
			linkEle.attr({
				"href" : engine.href,
				"title" : eLabel
			});
			that.navIndex = navIndex;
			that.radioIndex = radioIndex;
		},
		showSearchRadio : function(navIndex, radioIndex) {
			var that = this, formEle = that.formEle, curEle = that.curEle, logoEle = that.logoEle, linkEle = that.linkEle, radioEle = that.radioEle, tab = that.tabs[navIndex], engineList = tab.lists;
			elen = engineList.length, htm = "";
			radioEle.empty();
			if (elen > 1) {
				$
						.each(
								engineList,
								function(i, n) {
									var engine = engineList[i], checked = i == 0 ? " checked='checked'"
											: "";
									htm += "<li><input type='radio' nindex='"
											+ navIndex + "' rindex='" + i
											+ "' name='" + tab.name + "' id='"
											+ engine.name + "'" + checked
											+ "/>" + "<label for='"
											+ engine.name + "'>" + engine.label
											+ "</label></li>";
								});
				radioEle.html(htm);
			}
			radioEle
					.children()
					.find("input:radio")
					.click(
							function() {
								var btn = $(this), nIndex = btn.attr("nindex"), rIndex = btn
										.attr("rindex");
								that.showSearchInput(nIndex, rIndex);
							});
		}
	};
	var mozillaEngine = new MozillaEngine(TAB_LIST);
	window.baidu = {
		sug : function(sug) {
			var tipEle = mozillaEngine.tipEle;
			tipEle.empty();
			if (sug.s.length > 0) {
				for ( var i = 0; i < sug.s.length; i++) {
					tipEle.append("<li>" + sug.s[i] + "</li>");
				}
				tipEle.show();
			} else {
				tipEle.hide();
			}
		}
	};
})();