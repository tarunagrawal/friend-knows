<!doctype html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib
	uri="http://www.springframework.org/spring-social/facebook/tags"
	prefix="facebook"%>
<%@ page session="false"%>
<html class="no-js" lang="en">
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>friendknows.com</title>
<link rel="stylesheet" type="text/css"
	href="http://fonts.googleapis.com/css">
<link rel="stylesheet"
	href="<c:url value="/resources/css/foundation.css" />" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/foundation-icons.css" />" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/normalize.css" />" />
<style>
body {
	/*font-family: 'Open', serif;*/
	font-family: 'Indie Flower', cursive;
	background-color: rgb(249, 245, 245);
}

.container {
	/* border-top-color: orange;
	border-top-style: solid;
	border-top-width: 0.5rem; */
	
}

.logo {
	font-size: 3rem;
	display: inline;
	color: white;
	text-shadow: 3px 3px rgba(23, 23, 27, 1);
}

.com {
	font-size: 1rem;
}

.bold {
	font-weight: bold;
}

.logoPanel {
	margin-top: 5rem;
}

.banner {
	background-image: url("resources/images/newbanner.png");
	background-repeat: no-repeat;
	background-position: center top;
	background-size: cover;
	height: 21rem;
	background-color: black;
}

.problem-border {
	border-width: 1px;
	border-style: solid;
}

.text-stmt {
	font-size: 1.4rem !important;
	color: rgba(72, 69, 69, 1) font-family: 'Crafty Girls', cursive;
}

.problem-text {
	border-bottom-color: rgba(197, 193, 193, 1);
	border-bottom-style: solid;
	border-bottom-width: 1px;
}
</style>
</head>
<body style="background-color: whitesmoke; height: 100%;">
	<section>
		<div class="container">
			<div class="row banner fk-no-margin">
				<div class="small-10 columns small-centered">
					<div class="row">
						<div class="small-12 columns small-centered logoPanel">
							<div class="left">
								<div>
									<span class="logo fk-prefix">Friend</span><span class="logo">Knows</span><span
										class="com logo">.com</span>
								</div>
								<div style="margin-top: 2rem;">
									<span> <b> <a href="<c:url value="/auth/facebook"/>"
											class="button small bold"><span class="fi-social-facebook" style="font-size:24;"></span> Signin with Facebook </a></b></span>
								</div>
								<div style="margin-top: 0px; font-size: 0.7rem;">
									<span style="color:white">(You will be redirected to Facebook login screen)</span>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</section>
	<section>
		<div class="row"
			style="box-shadow: inset 0px 0 200px -30px rgba(0, 0, 0, 1);">
			<div class="small-12 columns">
				<div class="row" style="margin-top: 1rem;">
					<div class="small-6 columns small-centered problem-text">
						<p class="text-center text-stmt">
							Have you searched through websites before buying a car? <br />
							Or <br /> hunted for ratings before booking a restaurant? <br />
							Are you <b style="color: rgba(0, 129, 255, 1);">UNSATISFIED</b>
							at times even after enough <b
								style="color: rgba(0, 129, 255, 1);">HOMEWORK</b>?
						</p>
					</div>
				</div>
				<div class="row" style="margin-top: 1rem;">
					<div class="small-6 columns small-centered problem-text">
						<p class="text-center text-stmt">
							Do you feel the same stress.. <br /> When the recommendations
							come through a <b style="color: red;">FRIEND</b>.<br /> Somebody
							with common interests. Somebody with a common background.
							Somebody you can <b style="color: red;">TRUST</b> ?
						</p>
					</div>
				</div>
				<div class="row" style="margin-top: 1rem;">
					<div class="small-6 columns small-centered">
						<p class="text-center text-stmt">
							<b style="color: maroon;">FriendKnows</b> helps you to reach out
							to trusted friends <br /> to get their recommendations <br />
							because <b style="color: red;">FRIEND</b> <b
								style="color: maroon;">Knows</b> everything.
						</p>
					</div>
				</div>
			</div>
		</div>
	</section>
	<section>
		<div class="row"
			style="background-color: black; color: rgba(96, 92, 92, 1); height: 40px;">
			<div class="small-12 columns hide-for-small">
				<div class="row">
					<div class="small-6 columns small-centered text-center"
						style="padding-top: 10px;">&#169; friendknows.com</div>
				</div>
			</div>
		</div>
	</section>
</body>