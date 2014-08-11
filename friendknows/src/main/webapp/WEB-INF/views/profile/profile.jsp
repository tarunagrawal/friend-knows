<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row">

	<div class="small-12 large-5 columns">
		<div class="row">
			<div class="small-2 columns fk-full-height hide-for-small end"
				style="padding-left: 0px; padding-right: 0px;">
				<jsp:include page="iconbar.jsp"></jsp:include>
			</div>
			<div
				class="small-10 columns fk-padding-top fk-summary-border fk-requests-bar"
				style="margin-left: 0 !important; padding-left: 0px">
				<c:forEach items="${initiated}" var="bean" varStatus="index">
					<div class="row  fk-padding fk-bottom-border fk-request-summary"
						id="summary_${bean.id}">
						<div class="fk-clickable"
							onclick="javascript:displayDetails('${bean.id}');">
							<div class="row fk-padding-top">
								<div class="small-8 columns ">
									<span class="fk-label fk-radius">${bean.category.description}</span>
									<span class="fk-label fk-radius">${bean.item.description}</span>
								</div>
								<div class="small-4 columns medium-text-right end">
									<span class="fk-date">${bean.createDateTime}</span>
								</div>
							</div>
							<div class="row fk-padding-top">
								<div class="small-12 columns medium-text-left">
									<p class="fk-short-description">${bean.shortDescription}</p>
								</div>
							</div>
						</div>
						<div class="row fk-padding-top">
							<div class="small-6 columns medium-text-left end">
								<a href="<c:url value="/Request/${bean.id}/Close"/>"
									class="fk-remove-request-link fk-operation-link fi-trash"
									data-remove="details-${bean.id}"></a>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
	<div class="small-7 columns fk-padding-top end hide-for-small">
		<c:forEach items="${initiated}" var="bean" varStatus="index">
			<div class="row hide fk-details fk-padding" id="details-${bean.id}">
				<div class="small-12 columns">
					<div class="row">
						<div class="small-7 columns ">
							<span class="fk-label fk-radius">${bean.category.description}</span>
							<span class="fk-label fk-radius">${bean.item.description}</span>
						</div>
						<div class="small-3 columns medium-text-right end">
							<a href="<c:url value="/Request/${bean.id}/Reviewer/Add"/>"
								data-reveal-id="add_reviewer_${bean.id}" data-reveal-ajax="true"
								class="fk_add_reviewer_link fk-operation-link fi-torsos">&nbsp;
								Add Friends</a>

							<div id="add_reviewer_${bean.id}" class="reveal-modal small"
								data-append="details-${bean.id}"
								data-options="close_on_background_click:false" data-reveal>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="small-10 columns end">
							<div class="medium-text-left fk-request-description">
								<p>
									<a href="#" class="editable-in-place" data-type="textarea"
										data-append="summary_${bean.id}"
										data-url="<c:url value="/Request/${bean.id}/Description/Edit"/>">${bean.description}</a>
								</p>
							</div>
						</div>
					</div>
					<div class="row fk-line-height">
						<div class="small-10 columns medium-text-left">
							<c:forEach items="${bean.item.rating}" var="tagView">
								<c:if test="${tagView ne null}">
									<span class="fk-label fk-header-fill fk-color-white">P</span>
									<span class="fk-label fk-radius">
										${tagView.tagName}[${tagView.count}] </span>
									<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
								</c:if>
							</c:forEach>
						</div>
					</div>
					<div class="row fk-line-height">
						<div class="small-10 columns medium-text-left">
							<c:forEach items="${bean.item.connectedRating}"
								var="tagView">
								<c:if test="${tagView ne null}">
									<span class="fk-label fk-header-fill fk-color-white">T</span>
									<span class="fk-label fk-radius">
										${tagView.tagName}[${tagView.count}] </span>
									<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
								</c:if>
							</c:forEach>
						</div>
					</div>
					<div class="row fk-reviewers">
						<div class="small-10 columns fk-reviewers-rows">
							<c:forEach items="${bean.reviewers}" var="reviewer"
								varStatus="count">
								<div
									class="row fk-reviewer fk-no-margin fk-padding-top fk-bottom-border">
									<div class="small-1 columns fk-no-margin fk-no-padding">
										<img src="${reviewer.user.imageUrl}" class="" />
									</div>
									<div
										class="small-11 columns medium-text-left fk-no-left-padding end ">
										<div class="row ">
											<div class="small-4 columns medium-text-left fk-bold">${reviewer.user.name}
											</div>
											<div class="small-4 columns medium-text-left ">

												<c:if test="${reviewer.status eq 'Answered'}">
													<span class="fk-label fk-radius fk-answered">${reviewer.status}</span>
												</c:if>

												<c:if test="${reviewer.status eq 'Pending Answer'}">
													<span class="fk-label fk-radius fk-pending-answer">${reviewer.status}</span>
												</c:if>

												<c:if test="${reviewer.status eq 'Forwarded to friends'}">
													<span class="fk-label fk-radius fk-forwarded">${reviewer.status}</span>
												</c:if>
											</div>
											<div class="small-4 columns medium-text-right end">
												<a
													href="<c:url value="Request/${bean.id}/Reviewer/${reviewer.id}/Remove"/>"
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
															<div class="small-4 columns">
																<span class="fk-label fk-header-fill fk-color-white">T</span>
																<span class="fk-label fk-radius">
																	${review.tag.tagName}</span>
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
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
	</div>
</div>

