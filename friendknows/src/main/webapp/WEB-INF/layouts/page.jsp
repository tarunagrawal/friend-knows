<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page session="false"%>
<html class="no-js" lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>friendknows.com</title>
<link rel="stylesheet" type="text/css"
	href="http://fonts.googleapis.com/css">
<link rel="stylesheet" href="<c:url value="/resources/page.css" />"
	type="text/css" media="screen" />
<link rel="stylesheet" href="<c:url value="/resources/form.css" />"
	type="text/css" media="screen" />
<link rel="stylesheet"
	href="<c:url value="/resources/messages/messages.css" />"
	type="text/css" media="screen" />
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
<link rel="stylesheet"
	href="<c:url value="/resources/multiselect/token-input-facebook.css" />"
	type="text/css" media="screen" />
<link rel="stylesheet"
	href="<c:url value="/resources/multiselect/token-input-mac.css" />"
	type="text/css" media="screen" />
<link rel="stylesheet"
	href="<c:url value="/resources/multiselect/token-input.css" />"
	type="text/css" media="screen" />
<link
	href="http://cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.0/jqueryui-editable/css/jqueryui-editable.css"
	rel="stylesheet" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/foundation.css" />" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/foundation-icons.css" />" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/normalize.css" />" />
<script src="http://code.jquery.com/jquery-1.11.0.min.js"></script>
<script src="http://code.jquery.com/ui/1.10.4/jquery-ui.js"></script>
<script
	src="<c:url value="/resources/multiselect/jquery.tokeninput.js" />"></script>
<script
	src="http://cdnjs.cloudflare.com/ajax/libs/x-editable/1.5.0/jqueryui-editable/js/jqueryui-editable.min.js"></script>
<script src="<c:url value="/resources/application.js" />"></script>
<style type="text/css">
.top-bar input[type="text"],.top-bar .button {
	font-size: 0.77778rem;
	position: relative;
	top: 7px;
}

.top-bar input[type="text"] {
	height: auto;
	padding-top: 0.35rem;
	padding-bottom: 0.35rem;
	font-size: 0.75rem;
}

.icon-bar>* label {
	color: rgb(115, 110, 110);
}

.icon-bar>*:hover {
	background-color: #333333;
	color: white;
}

.icon-bar>* label:hover {
	color: white;
}

.editable-click,a.editable-click,a.editable-click:hover {
	color: black;
	border-bottom: none;
}

body {
	line-height: 1.5rem;
	font-size: 0.8rem;
	background-color: rgba(239, 239, 240, 1);
	/* font-family: 'Open Sans', sans-serif; */
	/* font-family: 'Indie Flower', cursive; */
	font-family: 'Josefin Sans', sans-serif;
	font-weight: 400;
}

#footer {
	position: fixed;
	z-index: 98;
	bottom: 0px;
	width: 100%;
	margin: 0px !important;
	padding: 0px !important;
	background-color: rgba(162, 162, 162, 0.49);
	height: 45px;
	-webkit-transform: translateZ(0);
}

.fk-request-summary-container {
	
}

.has-tip {
	border-bottom: none;
}

.has-tip:hover,.has-tip:focus {
	border: none !important;
}

.joyride-tip-guide {
	width: 600px !important;
}
/* .ui-state-active,
.ui-widget-content .ui-state-active,
.ui-widget-header .ui-state-active, 
.ui-autocomplete, .ui-autocomplete:hover, 
.ui-menu-item, .ui-menu-item:hover,
.ui-menu-item a, .ui-menu-item a:hover,
.ui-widget-content .ui-state-focus,
.ui-widget-header .ui-state-focus,
.ui-widget-content .ui-state-hover,
.ui-widget-header .ui-state-hover,
.ui-menu .ui-menu-item a.ui-state-focus,
.ui-menu .ui-menu-item a.ui-state-active,
.ui-menu .ui-menu-item a
{ background: #ffffff none no-repeat; 
padding:0;
margin:0;
display:block;
border:0;border-collapse:collapse;
}
 */
.tooltip {
	font-size: 0.7rem;
}
</style>
</head>
<body class="fk-background">
	<div>
		<div class="fixed">
			<nav class="top-bar" data-topbar="is_hover=false"
				style="-webkit-transform: translateZ(0);">
				<ul class="title-area ">
					<li class="name"><h1>
							<a href="<c:url value="/"/>"> <span
								class="fk-logo-color fk-bold">FriendKnows.com</span></a>
						</h1></li>
				</ul>
				<section class="top-bar-section">
					<ul class="right">
						<li class="has-form">
							<div class="row collapse">
								<div class="large-10 small-10 columns">
									<input type="text" id="product_search_box"
										placeholder="search exiting items..." size="50"> <input
										type="hidden" id="hidden_search" name="itemId" value="">
								</div>
								<div class="large-2 small-2 columns">
									<a href="<c:url value="/item/SearchItem/"/>"
										class="alert button expand fi-magnifying-glass"
										id="product_search_button"></a>
								</div>
							</div>
						</li>
						<li><a href="<c:url value='/feedback/form/'></c:url>"
							class="fi-record" data-reveal-id="feedback_pop_up"
							data-reveal-ajax="true">&nbsp;Feedback</a>
							<div id="feedback_pop_up" class="reveal-modal small"
								data-options="close_on_background_click:false" data-reveal>
							</div></li>
						<li><a href="#" class="fi-torso">&nbsp;${user.name}</a></li>
						<li><a href="<c:url value="/signout" />">Signout</a></li>
					</ul>
				</section>
			</nav>
		</div>
	</div>
	<section>
		<div class="row" style="margin-bottom: 50px;">
			<div class="small-12 columns fk-no-margin" style="padding: 0px;">
				<div class="row fk-no-margin">
					<div class="small-1 columns fk-full-height hide-for-small "
						style="padding-left: 0px; padding-right: 0px;">
						<jsp:include page="../views/profile/iconbar.jsp"></jsp:include>
					</div>
					<div class="small-9 columns " id="content_container">
						<tiles:insertAttribute name="content" />
					</div>
					<div class="small-2 columns end" id="notification_container">
					</div>
				</div>
			</div>
		</div>
	</section>
	<section>
		<div class="row">
			<div class="small-12 columns hide-for-small" id="footer">
				<div class="row">
					<div class="small-6 columns small-centered text-center">
						&#169; friendknows.com</div>
				</div>
			</div>
		</div>
	</section>
	<c:if test="${newUser}">
		<section>
			<ol class="joyride-list" data-joyride>
				<li data-text="Next"
					data-options="tip_location: top; prev_button: false">
					<p>
						Hello, <b>${user.name}</b> and welcome to the FriendKnows.com
					</p>
					<p>Lets take a tour.</p>
				</li>
				<li data-button="Next" data-prev-text="Prev"
					data-options="tip_location:top;tip_animation:fade">
					<h4 class="fi-plus">&nbsp;&nbsp;Create Request</h4>
					<p>Ask your friends about the products</p>
				</li>
				<li data-button="Next" data-prev-text="Prev"
					data-options="tip_location:top;tip_animation:fade">
					<h4 class="fi-anchor">&nbsp;&nbsp;Help friends</h4>
					<p>Your friends are looking for your help, let them know you
						views about products.</p>
				</li>
				<li data-button="Next" data-prev-text="Prev"
					data-options="tip_location:top;tip_animation:fade">
					<h4 class="">Create a knowledge chain</h4>
					<p>Include your friends you anwer your trusted friends query</p>
				</li>
				<li data-button="End" data-prev-text="Prev">
					<p>Lets create a better world!</p>
				</li>
			</ol>
		</section>
	</c:if>
	<section>
		<script src="<c:url value="/resources/js/foundation.min.js"/> "></script>
		<script src="<c:url value="/resources/js/notify.js"/> "></script>
		<script src="<c:url value="/resources/js/vendor/modernizr.js"/> "></script>
		<script>
			$(document).foundation();
			$(document)
					.ready(
							function() {
								$("#product_search_box")
										.autocomplete(
												{
													source : "<c:url value="/item/search/"/>",
													response : function(event,
															ui) {
														$
																.each(
																		ui.content,
																		function(
																				index,
																				row) {
																			row.label = row.name;
																			row.value = row.name;
																		});
													},
													select : function(event, ui) {
														$("#hidden_search")
																.val(ui.item.id);
													}
												});

								$(
										".fk-requests-bar .fk-request-summary:first-child")
										.find(".fk-clickable").trigger("click");

								$(".fk-full-height").height(
										$("body").height()
												- $(".fk-top-bar").height());

								(function(i, s, o, g, r, a, m) {
									i['GoogleAnalyticsObject'] = r;
									i[r] = i[r] || function() {
										(i[r].q = i[r].q || []).push(arguments)
									}, i[r].l = 1 * new Date();
									a = s.createElement(o), m = s
											.getElementsByTagName(o)[0];
									a.async = 1;
									a.src = g;
									m.parentNode.insertBefore(a, m)
								})
										(
												window,
												document,
												'script',
												'//www.google-analytics.com/analytics.js',
												'ga');

								ga('create', 'UA-53827779-1', {
									'cookieDomain' : 'none'
								});
								ga('send', 'pageview');

								$(document).foundation('joyride', 'start');

								poll();

								$(".fk-reviewers div:last-child").removeClass(
										"fk-bottom-border");
							});
		</script>
	</section>
</body>
</html>

