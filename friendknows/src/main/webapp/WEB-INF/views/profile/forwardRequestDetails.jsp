<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page session="false"%>

<div>
	<span>You have forwarded to following friends : <c:forEach
			items="${bean.forwardRequest.reviewers}" var="reviewer">
                 ${reviewer.user.name} &nbsp; ,
             </c:forEach>
	</span>
</div>

<%
	Object temp = request.getAttribute("bean");
%>
<c:forEach items="${bean.forwardRequest.reviewers}" var="reviewer">
	<c:if test="${not empty reviewer.reviews}">
		<div>
			<c:forEach items="${reviewer.reviews}" var="review">
				<div>
					<span id="desc-${review.id}">${review.description}</span><span>&nbsp;&nbsp;&nbsp;</span>
					<span id="rating-${review.id}">${review.rating.rating}</span><span>&nbsp;&nbsp;&nbsp;</span>
					<span>${review.dateTime}</span> <span class="detail"><button
							onclick="javascript:editReview('${review.id}');">edit</button></span> <span
						class="detail"><button
							onclick="javascript:deleteReview('${review.id}');">delete</button></span>
				</div>
			</c:forEach>
		</div>
	</c:if>
	<c:if test="${reviewer.status eq 'PROPAGATED'}">
		<c:set var="bean" value="${reviewer}" scope="request" />
		<jsp:include page="forwardRequestDetails.jsp"></jsp:include>
	</c:if>
</c:forEach>
<%
	request.setAttribute("bean", temp);
%>



