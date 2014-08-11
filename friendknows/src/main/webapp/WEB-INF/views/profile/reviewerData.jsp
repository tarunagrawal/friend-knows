<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page session="false"%>

<div class="row fk-bottom-border fk-padding">
	<div class="small-11 columns end">
		<div class="row ">
			<div class="small-8 columns ">
				<span class="fk-label fk-radius">${bean.category.description}</span>
				<span class="fk-label fk-radius">${bean.item.description}</span>

				<c:if test="${bean.status eq 'Answered'}">
					<span class="fk-label fk-radius fk-answered">${bean.status}</span>
				</c:if>

				<c:if test="${bean.status eq 'Pending Answer'}">
					<span class="fk-label fk-radius fk-pending-answer">${bean.status}</span>
				</c:if>

				<c:if test="${bean.status eq 'Forwarded to friends'}">
					<span class="fk-label fk-radius fk-forwarded">${bean.status}</span>
				</c:if>

			</div>
			<div class="small-4 columns text-right">
				<span class="fk-date">${bean.createDateTime}</span>
			</div>
		</div>
		<div class="row fk-no-margin fk-padding-top">
			<div class="small-1 columns fk-no-margin fk-no-padding">
				<img src="${bean.initiatedUser.imageUrl}" />
			</div>
			<div class="small-11 columns text-left"
				style="margin-left: 0px !important; padding-left: 0px !important;">
				<span class="detail fk-bold">${bean.initiatedUser.name}</span> <span
					class="detail">is looking for your help</span>
			</div>
		</div>
		<div class="row fk-padding-top">
			<div class="small-12 columns end">
				<span class="detail">${bean.requestDescription}</span>
			</div>
		</div>
		<c:if test="${not empty bean.reviews}">
			<div class="row fk-reviews fk-padding-top">
				<c:forEach items="${bean.reviews}" var="review">
					<div class="small-12 columns" id="review_details_${review.id}">
						<div class="row fk-review fk-no-margin ">
							<div class="small-1 columns fk-no-margin fk-no-padding">
								<img src="${bean.user.imageUrl}" />
							</div>

							<div class="small-11 columns fk-no-margin fk-no-padding">
								<div class="row">
									<div class="small-12 columns fk-review-text-data">${review.description}</div>
								</div>

								<div class="row">
									<div class="small-4 columns fk-padding-top ">
										<span class="fk-label fk-header-fill fk-color-white">T</span>
										<span class="fk-label fk-radius fk-tag-viewName">
											${review.tag.tagName}</span>
									</div>

									<div
										class="small-1 columns fk-padding-top small-text-left fk-operation-link">
										<a class="fi-page-edit"
											href="<c:url value="/Request/${bean.requestId}/Reviewer/{${bean.id}}/Review/${review.id}/Edit"/>"
											data-reveal-id="edit_review_${bean.id}"
											data-reveal-ajax="true"></a>

										<div id="edit_review_${bean.id}" class="reveal-modal small"
											data-append="review_details_${review.id}"
											data-options="close_on_background_click:false" data-reveal></div>

									</div>

									<div
										class="small-1 columns fk-padding-top small-text-left fk-operation-link end">
										<a class="fk-remove-review-link fi-trash"
											href="<c:url value="/Request/${bean.requestId}/Reviewer/{${bean.id}}/Review/${review.id}/Remove"/>"></a>
									</div>
									<div
										class="small-2 columns fk-padding-top small-text-left fk-date end">
										${review.dateTime}</div>
								</div>
							</div>
						</div>

					</div>


				</c:forEach>
			</div>
		</c:if>
		<c:if test="${bean.status eq 'Forwarded to friends'}">
			<div>
				<span>You have forwarded to following friends : <c:forEach
						items="${bean.forwardRequest.reviewers}" var="reviewer">${reviewer.user.name} &nbsp;</c:forEach>
				</span>
			</div>

		</c:if>
		<div class="row fk-padding-top">
			<div class="small-1 columns  small-text-left">
				<c:if test="${bean.showReply}">
					<a class="fk_add_review_link fk-operation-link"
						href="<c:url value="/Request/${bean.requestId}/Reviewer/${bean.id}/Review/New"/>/"
						data-reveal-id="add_review_${bean.id}" data-reveal-ajax="true">Reply</a>

					<div id="add_review_${bean.id}" class="reveal-modal small"
						data-append="details-${bean.id}"
						data-options="close_on_background_click:false" data-reveal>
					</div>
				</c:if>
			</div>
			<div class="small-2 columns  small-text-left end">
				<a class="fk-forward-review-link fk-operation-link"
					href="<c:url value="/Request/${bean.requestId}/Reviewer/${bean.id}/Forward/New"/>/"
					data-reveal-id="forward_review_${bean.id}" data-reveal-ajax="true">forward</a>
				<div id="forward_review_${bean.id}" class="reveal-modal small"
					data-append="details-${bean.id}"
					data-options="close_on_background_click:false" data-reveal></div>

			</div>

		</div>
	</div>
</div>