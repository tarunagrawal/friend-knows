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
</head>
<body class="fk-background">
	<nav class="top-bar" data-topbar="is_hover=true,">
		<ul class="title-area ">
			<li class="name"><h1>
					<a href="<c:url value="/"/>"> <span class="fk-logo-color">FriendKnows.com</span></a>
				</h1></li>
		</ul>
		<section class="top-bar-section">
			<ul class="right">
				<li class="has-dropdown"><a href="#">${user.name}</a>
					<ul class="dropdown">
						<li><a href="<c:url value="/signout" />">Signout</a></li>
					</ul></li>
			</ul>
		</section>
	</nav>
	<section>
		<div class="row">
			<div class="small-12 columns ">
				<div class="row">
					<div class="small-1 columns" style="padding: 0px;">
						<div class="icon-bar vertical six-up">
							<a class="item" href="<c:url value="/Request/New"/>"> <label
								class="fi-plus fk-navigation-icon-size"></label>
							</a> <a class="item" href="<c:url value="/request/assigned"/>"> <label
								class="fi-anchor fk-navigation-icon-size"></label>
							</a> <a class="item"> <label>Reviews</label>
							</a> <a class="item"><label>Profile</label> </a>
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
		</script>
	</section>
</body>
</html>

