<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page session="false"%>
<html class="no-js" lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>friendknows.com</title>


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
</style>
</head>
<body class="fk-background">
	<%-- 	<section>
		<div class="row fk-no-margin fk-top-bar">
			<div class="small-12 columns">
				<div class="row">
					<div class="small-3 columns fk-logo-and-name">FriendKnows</div>
					<div class="small-6 columns hide-for-small">
						<div class="row">
							<div class="small-9 columns small-centered">
								<input type="text" class="fk-search-box" id="item_search_box"
									placeholder="search existing products..">
							</div>
						</div>
					</div>
					<div class="small-3 columns text-right">
						<a href="<c:url value="/signout" />">Signout</a>
					</div>
				</div>
			</div>
		</div>
	</section> --%>

	<nav class="top-bar" data-topbar="is_hover=true,">
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
						<div class="large-9 small-9 columns">
							<input type="text" id="product_search_box"
								placeholder="search exiting items...">
						</div>
						<div class="large-3 small-3 columns">
							<a href="#" class="alert button expand">Search</a>
						</div>
					</div>
				</li>
				<li><a href="#">${user.name}</a></li>
				<li><a href="<c:url value="/signout" />">Signout</a></li>
			</ul>
		</section>
	</nav>
	<!-- rgb(234, 73, 15) - final top bar -->
	<section style="position: relative;">
		<div class="row">
			<div class="small-12 columns ">
				<div class="row">
					<div class="small-1 columns "
						style="padding: 0px;">
						<div class="row">
							<div class="small-12 columns fk-full-height hide-for-small end" style="padding: 0px;">
								<div class="icon-bar vertical six-up fk-sidebar">
									<a class="item" href="<c:url value="/Request/New"/>"> <label
										class="fi-plus fk-navigation-icon-size"></label>
									</a> <a class="item" href="<c:url value="/request/assigned"/>">
										<label class="fi-anchor fk-navigation-icon-size"></label>
									</a> <a class="item"> <label>RVC</label>
									</a> <a class="item"><label>PVC</label> </a>
								</div>
							</div>
						</div>
					</div>
					<div class="small-10 columns">
						<tiles:insertAttribute name="content" />
					</div>
					<div class="small-1 columns end"></div>
				</div>
			</div>
		</div>
	</section>
	<section>
		<script src="<c:url value="/resources/js/foundation.min.js"/> "></script>
		<script src="<c:url value="/resources/js/vendor/modernizr.js"/> "></script>
		<script>
			$(document).foundation();
			$(".fk-full-height").height(
					$("body").height() - $(".fk-top-bar").height() - 2);
			$(document).ready(
					function() {
						$("#item_search_box").tokenInput(
								"/friendknows/category/search/", {
									tokenLimit : 1,
								});

						var availableTags = [ "ActionScript", "AppleScript",
								"Asp", "BASIC", "C", "C++", "Clojure", "COBOL",
								"ColdFusion", "Erlang", "Fortran", "Groovy",
								"Haskell", "Java", "JavaScript", "Lisp",
								"Perl", "PHP", "Python", "Ruby", "Scala",
								"Scheme" ];
						$("#product_search_box").autocomplete({
							source : availableTags
						});
					});
		</script>
	</section>
</body>
</html>

