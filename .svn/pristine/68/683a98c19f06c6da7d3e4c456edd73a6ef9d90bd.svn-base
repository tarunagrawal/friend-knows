<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page session="false"%>
<div class="container">
	<div class="request">
		<div>
			<span id="${bean.category.id}" class="heading highlight">${bean.category.description}</span>
			<span id="${bean.item.id}" class="heading highlight">${bean.item.description}</span><span>&nbsp;&nbsp;&nbsp;</span>
			<span class="detail">${bean.initiatedUser.name}</span><span>&nbsp;&nbsp;&nbsp;</span>
			<span class="detail">Has asked your review for</span><span>&nbsp;&nbsp;&nbsp;</span>
			<span>&nbsp;&nbsp;&nbsp;</span> <span class="detail"> <c:forEach
					items="${bean.item.rating.popularTags}" var="weightTag">
					<c:if test="${weightTag ne null}">
						<span class="detail"> ${weightTag.tag.name}</span>
						<span>&nbsp;&nbsp;&nbsp; </span>
					</c:if>
				</c:forEach>
			</span> <span>&nbsp;&nbsp;&nbsp;</span><span class="detail">${bean.createDateTime}</span>
		</div>
		<div>
			<span class="detail">${bean.requestDescription}</span>
		</div>
		<c:if test="${not empty bean.reviews}">
			<div>
				<c:forEach items="${bean.reviews}" var="review">
					<div class="review">
						<span id="desc-${review.id}">${review.description}</span><span>&nbsp;&nbsp;&nbsp;</span>
						<span id="rating-${review.id}"> <c:forEach
								items="${review.tag.popularTags}" var="weightTag">
								<c:if test="${weightTag ne null}">
									<span class="detail"> ${weightTag.tag.name}</span>
									<span>&nbsp;&nbsp;&nbsp; </span>
								</c:if>
							</c:forEach>

						</span><span>&nbsp;&nbsp;&nbsp;</span> <span>${review.dateTime}</span> <span
							class="detail"> <a class="edit_review_link"
							href="<c:url value="/Request/${bean.requestId}/Reviewer/{${bean.id}}/Review/${review.id}/Edit"/>">edit</a>
						</span> <span class="detail"> <a class="remove_review_link"
							href="<c:url value="/Request/${bean.requestId}/Reviewer/{${bean.id}}/Review/${review.id}/Remove"/>">remove</a>
						</span>
					</div>
				</c:forEach>
			</div>
		</c:if>

		<span>${bean.status}</span>

		<c:if test="${bean.status eq 'Forwarded to friends'}">
			<jsp:include page="forwardRequestDetails.jsp"></jsp:include>
		</c:if>
		<div class="buttonPanel">
			<span class="detail"> <a class="add_review_link operation-link"
				href="<c:url value="/Request/${bean.requestId}/Reviewer/${bean.id}/Review/New"/>/">Reply</a>
				<a class="forward_review_link operation-link"
				href="<c:url value="/Request/${bean.requestId}/Reviewer/${bean.id}/Forward/New"/>/">forward</a>
			</span>
		</div>
		<div></div>
	</div>
</div>

