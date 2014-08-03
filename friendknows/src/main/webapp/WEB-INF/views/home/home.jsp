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
<section>
	<div class="panel">
		<p>
			<a href="<c:url value="/auth/facebook"/>"> <img
				src="<c:url value="/resources/social/facebook/sign-in-with-facebook.png"/>"
				border="0" />
			</a><br />
		</p>
	</div>
	<div class="panel ">
        <p>
            <a href="<c:url value="/auth/facebook"/>"> <img
                src="<c:url value="/resources/social/facebook/sign-in-with-facebook.png"/>"
                border="0" />
            </a><br />
        </p>
    </div>
</section>
