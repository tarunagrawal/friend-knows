<%@page import="com.system.you.review.request.bean.Reviewer"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page session="false"%>

<div class="row fk-padding">
	<div class="small-12 columns end">
		<div class="row ">
			<div class="small-10 columns ">
				<span class="fk-label fk-radius">${view.category.description}</span>
				<span class="fk-label fk-radius">${view.item.description}</span>
				<jsp:include page="reviewerStatus.jsp">
					<jsp:param value="${view}" name="view" />
				</jsp:include>
			</div>
			<div class="small-2 columns text-right">
				<span class="fk-date">${view.createDateTime}</span>
			</div>
		</div>
		<div class="row fk-no-margin fk-padding-top">
			<div class="small-1 columns fk-no-margin fk-no-padding">
				<img src="${view.initiatedUser.imageUrl}" />
			</div>
			<div class="small-11 columns text-left">
				<div class="row">
					<div class="small-12 columns">
						<span class="detail fk-bold">${view.initiatedUser.name}</span> <span
							class="detail">is looking for your help</span>
					</div>
				</div>
				<div class="row fk-padding-top">
					<div class="small-12 columns end">
						<p style="margin-bottom: 0px !important;">&quot;&nbsp;&nbsp;${view.requestDescription}&nbsp;&nbsp;&quot;</p>
					</div>
				</div>
				<c:if test="${not empty view.reviews}">
					<div class="row fk-reviews" style="margin-top: 10px;">
						<c:forEach items="${view.reviews}" var="review">
							<div class="small-12 columns" id="review_details_${review.id}" style="margin-bottom: 10px !important;">
								<div class="row fk-review fk-no-margin fk-padding" style="border-bottom: 1px solid  #D8D5D5;background-color: rgba(239, 239, 240, 1);">
									<div class="small-1 columns fk-no-margin fk-no-padding">
										<img src="${view.user.imageUrl}" />
									</div>

									<div class="small-9 columns fk-no-margin end">
										<div class="row fk-no-margin">
											<div class="small-12 columns fk-review-text-data">
												<p style="margin-bottom: 0px !important;">${review.description}</p>
											</div>
										</div>

										<div class="row fk-no-margin">
											<div class="small-12 columns fk-padding-top text-left">
												<div style="display: inline">
													<span class=""> <span
														class="fk-label fk-radius-left fk-header-fill fk-color-white">T</span><span
														class="fk-label fk-radius-right fk-tag-viewName">
															${review.tag.tagName}</span>
													</span>

												</div>
												<a class="fi-page-edit" style="margin-left: 15px;"
													href="<c:url value="/Request/${view.requestId}/Reviewer/${view.id}/Review/${review.id}/Edit"/>"
													data-reveal-id="edit_review_${review.id}"
													data-reveal-ajax="true"></a>

												<div id="edit_review_${review.id}"
													class="reveal-modal small"
													data-append="reviewer_data_container_${view.id}"
													data-options="close_on_background_click:false" data-reveal></div>

												<a class="fk-remove-review-link fi-trash"
													style="margin-left: 15px;"
													href="<c:url value="/Request/${view.requestId}/Reviewer/${view.id}/Review/${review.id}/Remove"/>"></a>

												<div class="small-text-left fk-date "
													style="display: inline; margin-left: 15px;">
													${review.dateTime}</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</c:forEach>
					</div>
				</c:if>
			</div>

			<c:if test="${fn:contains(view.status, 'friends')}">
				<div class="row" style="margin-left: 4rem;">
					<div class="small-12 columns">
						<div class="row">
							<div class="small-12 columns fk-padding-top">
								<p class="fk-bold" style="margin-bottom: 0px !important;">You
									have forwarded to friends</p>
							</div>
						</div>
						<c:forEach items="${view.forwardRequest.reviewers}" var="reviewer">
							<div
								class="row fk-reviewer fk-no-margin fk-padding-top fk-bottom-border">
								<div class="small-1 columns fk-no-margin fk-no-padding">
									<img src="${reviewer.user.imageUrl}" class="" />
								</div>
								<div
									class="small-11 columns medium-text-left fk-no-left-padding end ">
									<div class="row ">
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
										<c:if test="${fn:contains(reviewer.status, 'Answered')}">
											<div class="small-3 columns">
												<span class="fk-label fk-header-fill fk-color-white">T</span><span
													class="fk-label"> ${reviewer.reviews[0].tag.tagName}</span>
											</div>
										</c:if>
										<div class="small-1 columns medium-text-right ">
											<a
												href="<c:url value="/Reviewer/${view.id}/Request/${view.forwardRequest.id}/Reviewer/${reviewer.id}/Remove"/>"
												class="fk-remove-fwd-reviewer-link fk-operation-link fi-trash"
												data-append="reviewer_data_container_${view.id}"></a>
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
														<div class="small-12 columns medium-text-left end ">
															<ul class="inline-list fk-padding">
																<li><a
																	class="fk-operation-link fk-propagated-back-link"
																	data-append="reviewer_data_container_${view.id}"
																	href="<c:url value="/Reviewer/${view.id}/Review/${review.id}"/>">Let
																		${view.initiatedUser.name} know </a></li>
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
					</div>
				</div>
			</c:if>
		</div>

		<div class="row fk-padding-top">
			<div class="small-1 columns  small-text-left">
				<c:if test="${view.showReply}">
					<a class="fk_add_review_link fk-operation-link"
						href="<c:url value="/Request/${view.requestId}/Reviewer/${view.id}/Review/New"/>/"
						data-reveal-id="add_review_${view.id}" data-reveal-ajax="true">Reply</a>

					<div id="add_review_${view.id}" class="reveal-modal small"
						data-append="reviewer_data_container_${view.id}"
						data-options="close_on_background_click:false" data-reveal>
					</div>
				</c:if>
			</div>
			<div class="small-2 columns  small-text-left end">
				<a class="fk-forward-review-link fk-operation-link"
					href="<c:url value="/Request/${view.requestId}/Reviewer/${view.id}/Forward/New"/>/"
					data-reveal-id="forward_review_${view.id}" data-reveal-ajax="true">forward</a>
				<div id="forward_review_${view.id}" class="reveal-modal small"
					data-append="reviewer_data_container_${view.id}"
					data-options="close_on_background_click:false" data-reveal></div>
			</div>
		</div>
	</div>
</div>