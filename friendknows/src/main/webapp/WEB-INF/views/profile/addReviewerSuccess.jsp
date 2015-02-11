<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<c:forEach items="${view}" var="reviewer" varStatus="count">
	<div
		class="row fk-reviewer fk-no-margin fk-padding-top fk-bottom-border">
		<div class="small-1 columns fk-no-margin fk-no-padding">
			<img src="${reviewer.user.imageUrl}" class="profileImage" />
		</div>
		<div class="small-11 columns medium-text-left fk-no-left-padding end ">
			<div class="row fk-no-margin">
				<div class="small-3 columns medium-text-left fk-bold">${reviewer.user.name}
				</div>
				<div class="small-5 columns medium-text-left ">
					<c:if test="${reviewer.status eq 'Answered'}">
						<span class="fk-label fk-radius fk-answered">${reviewer.status}</span>
					</c:if>

					<c:if test="${reviewer.status eq 'Pending Answer'}">
						<span class="fk-label fk-radius fk-pending-answer">${reviewer.status}</span>
					</c:if>

					<c:if test="${fn:contains(reviewer.status, 'friends')}">
						<span class="fk-label fk-radius fk-forwarded">${reviewer.status}</span>
					</c:if>
				</div>
				<div class="small-1 columns medium-text-right ">
					<a
						href="<c:url value="/Request/${reviewer.requestId}/Reviewer/${reviewer.id}/Remove"/>"
						class="fk-remove-reviewer-link fk-operation-link fi-trash"></a>
				</div>
			</div>
		</div>
	</div>
</c:forEach>
