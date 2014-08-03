<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page session="false"%>
<div class="reviewers">
	<c:forEach items="${bean.reviewers}" var="reviewer" varStatus="count">
		<c:set var="reviewer" value="${reviewer}" scope="request" />
		<jsp:include page="reviewerPrint.jsp"></jsp:include>
	</c:forEach>
</div>