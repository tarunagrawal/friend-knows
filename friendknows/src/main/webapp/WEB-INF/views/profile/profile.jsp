<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="row">
	<div class="small-12 large-4 columns ">
		<div class="row">
			<div
				class="small-12 columns fk-padding-top fk-summary-border fk-requests-bar end"
				style="margin-left: 0 !important; padding-left: 0px">
				<div class="fk-request-summary-container">
					<c:forEach items="${initiated}" var="bean" varStatus="index">
						<div class="row  fk-padding fk-bottom-border fk-request-summary"
							id="summary_${bean.id}">
							<div class="row">
								<div class="small-12 columns medium-text-right end">
									<a href="<c:url value="/Request/${bean.id}/Close"/>"
										class="fk-remove-request-link fk-operation-link fi-trash"
										data-remove="details-${bean.id}"></a>
								</div>
							</div>
							<div class="fk-clickable"
								onclick="javascript:displayDetails('${bean.id}');">
								<div class="row ">
									<div class="small-8 columns ">
										<span class="fk-label fk-radius"
											style="font-size: 0.9rem; color: rgba(84, 53, 53, 1); font-weight: bold; background-color: #F5E469; box-shadow: 2px 2px 4px #A5A3A3 !important; border-color: #F5E469;">${bean.category.description}</span>
										<span class="fk-label fk-radius"
											style="font-size: 0.9rem; color: rgba(84, 53, 53, 1); font-weight: bold; background-color: #F5E469; box-shadow: 2px 2px 4px #A5A3A3 !important; border-color: #F5E469;">${bean.item.description}</span>
									</div>
									<div class="small-4 columns medium-text-right end">
										<span class="fk-date">${bean.createDateTime}</span>
									</div>
								</div>
								<div class="row fk-padding-top">
									<div class="small-12 columns medium-text-left">
										<p class="fk-short-description">&quot;&nbsp;
											${bean.shortDescription} &nbsp;&quot;</p>
									</div>
								</div>
							</div>

						</div>
					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	<div
		class="small-12 large-8 columns fk-padding-top end  hide-for-small">
		<c:forEach items="${initiated}" var="bean" varStatus="index">
			<div class="row hide fk-details fk-padding" id="details-${bean.id}"
				style="display: block; box-shadow: rgb(205, 197, 197) 5px 5px 5px; background-color: rgb(244, 244, 244); margin-left: 3px; border: rgb(229, 225, 225) 1px solid;">
				<div class="small-12 columns">
					<div class="row">
						<div class="small-7 columns ">
							<span class="fk-label fk-radius"
								style="font-size: 0.9rem; color: rgba(84, 53, 53, 1); font-weight: bold; background-color: #F5E469; box-shadow: 2px 2px 4px #A5A3A3 !important; border-color: #F5E469;">${bean.category.description}</span>
							<span class="fk-label fk-radius"
								style="font-size: 0.9rem; color: rgba(84, 53, 53, 1); font-weight: bold; background-color: #F5E469; box-shadow: 2px 2px 4px #A5A3A3 !important; border-color: #F5E469;">${bean.item.description}</span>
						</div>
						<div class="small-4 columns medium-text-right end">
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
						<div class="small-11 columns end">
							<div class="medium-text-left fk-request-description">
								<p>
									&quot;&nbsp; <a href="#" class="editable-in-place"
										data-type="textarea" data-append="summary_${bean.id}"
										data-url="<c:url value="/Request/${bean.id}/Description/Edit"/>">${bean.description}</a>
									&nbsp;&quot;
								</p>
							</div>
						</div>
					</div>
					<div class="row fk-line-height">
						<div class="small-2 columns text-right">
							<b>Public Views:</b>
						</div>
						<div class="small-10 columns medium-text-left">
							<c:if test="${empty bean.item.rating}">No Review Available</c:if>
							<c:forEach items="${bean.item.rating}" var="tagView">
								<c:if test="${tagView ne null}">
									<span
										class="fk-label fk-header-fill fk-color-white fi-megaphone"></span><span class="fk-label "> 
										${tagView.tagName}</span><span class="fk-label fk-header-fill fk-color-white">${tagView.count}</span>
									<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
								</c:if>
							</c:forEach>
						</div>
					</div>
					<div class="row fk-line-height">
						<div class="small-2 columns text-right">
							<b>Friend Views:</b>
						</div>
						<div class="small-10 columns medium-text-left">
							<c:if test="${empty bean.item.rating}">No Review Available</c:if>
							<c:forEach items="${bean.item.connectedRating}" var="tagView">
								<c:if test="${tagView ne null}">
									<span
										class="fk-label fk-header-fill-trusted fk-color-white fi-link"></span><span class="fk-label-trusted"> 
										${tagView.tagName}</span><span class="fk-label fk-header-fill-trusted fk-color-white">${tagView.count}</span>
									<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
								</c:if>
							</c:forEach>
						</div>
					</div>
					<div class="row fk-reviewers">
						<div class="small-11 columns fk-reviewers-rows">
							<c:forEach items="${bean.reviewers}" var="reviewer"
								varStatus="count">
								<div class="row fk-reviewer fk-no-margin fk-bottom-border "
									style="padding-bottom: 10px; margin-top: 10px !important; background-color: rgba(239, 239, 240, 1);">
									<div class="small-1 columns fk-no-margin fk-no-padding">
										<img src="${reviewer.user.imageUrl}" class="profileImage" />
									</div>
									<div
										class="small-11 columns medium-text-left fk-no-left-padding end ">
										<div class="row fk-no-margin">
											<div class="small-3 columns medium-text-left fk-bold">${reviewer.user.name}
											</div>
											<div class="small-8 columns medium-text-left"
												style="margin-top: 3px;">

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
													href="<c:url value="/Request/${bean.id}/Reviewer/${reviewer.id}/Remove"/>"
													class="fk-remove-reviewer-link fk-operation-link fi-trash"></a>
											</div>
										</div>
										<c:if test="${not empty reviewer.reviews}">
											<c:forEach items="${reviewer.reviews}" var="review">
												<div class="row fk-no-margin fk-padding-top"
													style="margin-top: 0.4rem !important; margin-bottom: 0.4rem !important; background-color: whitesmoke; border-radius: 10px;">
													<div class="small-10 columns end fk-review-display">
														<div class="row">
															<div class="small-12 columns">
																<p>${review.description}</p>
															</div>
														</div>
														<div class="row">
															<div class="small-4 columns">
																<span
																	class="fk-label fk-header-fill-trusted fk-color-white fi-link"></span><span
																	class="fk-label-trusted"> ${review.tag.tagName}</span>
															</div>
															<div class="small-6 columns medium-text-left end ">
																<ul class="inline-list fk-padding">
																	<li><c:if test="${review.agreed}">
																			<span class="fk-agreed">Agreed</span>
																		</c:if> <c:if test="${not review.agreed}">
																			<a class="fk-operation-link fk-agree-link"
																				href="<c:url value="/Request/${bean.id}/Reviewer/${reviewer.id}/Review/${review.id}/Agree/"/>">Agree</a>
																		</c:if></li>
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

<script type="text/javascript">
	
</script>