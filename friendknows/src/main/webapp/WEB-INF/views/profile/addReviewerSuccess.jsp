<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<c:forEach items="${view}" var="reviewer" varStatus="count">
	<%-- <div class="row fk-reviewer fk-no-margin fk-padding-top fk-bottom-border">
		<div class="small-1 columns fk-no-margin fk-no-padding">
			<img src="${reviewer.user.imageUrl}" />
		</div>
		<div class="small-11 columns medium-text-left fk-no-left-padding end">
			<div class="row">
				<div class="small-4 columns medium-text-left fk-bold">${reviewer.user.name}</div>
				<div class="small-4 columns medium-text-left ">
					<span class="fk-operation-link"> ${reviewer.status}</span>
				</div>
				<div class="small-4 columns medium-text-right end">
					<a
						href="<c:url value="Request/${reviewer.requestId}/Reviewer/${reviewer.id}/Remove"/>"
						class="fk-remove-reviewer-link fk-operation-link fi-trash"></a>
				</div>
			</div>
			<c:if test="${not empty reviewer.reviews}">
				<c:forEach items="${reviewer.reviews}" var="review">
					<div class="row  fk-padding-top">
						<div class="small-10 columns end fk-review-display">
							<div class="row">
								<div class="small-12 columns">
									<p>${review.description}</p>
								</div>
							</div>
							<div class="row">
								<div class="small-12 columns">
									<c:forEach items="${review.tag.popularTags}" var="weightTag">
										<c:if test="${weightTag ne null}">
											<span class="fk-label fk-header-fill fk-color-white">T</span>
											<span class="fk-label fk-radius">
												${weightTag.tag.viewName}</span>
											<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
										</c:if>
									</c:forEach>
								</div>
								<div class="small-12 columns medium-text-left end ">
									<ul class="inline-list fk-padding">
										<li><a href="javascript:verify('${review.id}">Agree</a></li>
										<li><a>${review.dateTime}</a></li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</c:if>
		</div>
	</div> --%>
	<div
		class="row fk-reviewer fk-no-margin fk-padding-top fk-bottom-border">
		<div class="small-1 columns fk-no-margin fk-no-padding">
			<img src="${reviewer.user.imageUrl}" class="" />
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
			<c:if test="${not empty reviewer.reviews}">
				<c:forEach items="${reviewer.reviews}" var="review">
					<div class="row fk-no-margin fk-padding-top"
						style="margin-top: 0.8rem !important; margin-bottom: 0.2rem !important; background-color: whitesmoke;">
						<div class="small-10 columns end fk-review-display">
							<div class="row">
								<div class="small-12 columns">
									<p>${review.description}</p>
								</div>
							</div>
							<div class="row">
								<div class="small-4 columns">
									<span class="fk-label fk-header-fill fk-color-white">T</span><span
										class="fk-label"> ${review.tag.tagName}</span>
								</div>
								<div class="small-6 columns medium-text-left end ">
									<ul class="inline-list fk-padding">
										<li><a class="fk-operation-link"
											href="javascript:verify('${review.id}">Agree</a></li>
										<li><a class="fk-date">${review.dateTime}</a></li>
									</ul>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</c:if>
		</div>
	</div>
</c:forEach>
