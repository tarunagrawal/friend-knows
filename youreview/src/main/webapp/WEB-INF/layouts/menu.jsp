<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<ul class="side-nav">
	<li><a href="<c:url value="/Request/New"/>">Create Review</a></li>
	<li><a href="<c:url value="/request/assigned"/>">Needs
			Attention</a></li>
	<li><a href="<c:url value="/facebook"/>">Your Reviews</a></li>
	<li><a href="<c:url value="/facebook"/>">User Profile</a></li>
</ul>
