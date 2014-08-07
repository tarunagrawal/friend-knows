<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ taglib
	uri="http://www.springframework.org/spring-social/facebook/tags"
	prefix="facebook"%>
<%@ page session="false"%>
<link rel="stylesheet"
	href="<c:url value="/resources/css/foundation.css" />" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/foundation-icons.css" />" />
<link rel="stylesheet"
	href="<c:url value="/resources/css/normalize.css" />" />
<style>
body {
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
	background-image: url("/friendknows/resources/images/newbanner.png");
	background-repeat: no-repeat;
	background-position: center top;
	background-size: cover;
	height: 22rem;
	background-color: black;
}

.problem-border {
	border-width: 1px;
	border-style: solid;
}
</style>
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
								<a href="<c:url value="/auth/facebook"/>" class="button small">Signin
									with Facebook </a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="small-6 columns small-centered logopanel">
				<div class="left fk-label text-center"
					style="color: white; height: 10rem; border-color: white;">Looking
					for review, opinion online ?</div>
			</div>
		</div>
	</div>
</section>


