<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page session="false"%>

<c:if test="${view.status eq 'Answered'}">
	<span class="fk-label fk-radius fk-answered">${view.status}</span>
</c:if>

<c:if test="${view.status eq 'Pending Answer'}">
	<span class="fk-label fk-radius fk-pending-answer">${view.status}</span>
</c:if>

<c:if test="${fn:contains(view.status, 'friends')}">
	<span class="fk-label fk-radius fk-forwarded">${view.status}</span>
</c:if>