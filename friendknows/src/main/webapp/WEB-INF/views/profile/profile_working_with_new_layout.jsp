<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="row fk-padding-top">
	<div class="small-12 columns fk-padding-top">
		<c:forEach items="${initiated}" var="bean" varStatus="index">
			<div class="row">
				<div class="row">
					<div class="small-1 columns ">
						<span class="fk-label fk-radius">${bean.category.description}</span>
					</div>
					<div class="small-1 columns medium-text-left">
						<span class="fk-label fk-radius">${bean.item.description}</span>
					</div>
					<div class="small-4 columns medium-text-right end">
						<span class="fk-operation-link">${bean.createDateTime}</span> <a
							href="<c:url value="/Request/${bean.id}/Reviewer/Add"/>"
							class="add_reviewer_link fk-operation-link">[Add Reviewers]</a> <a
							href="<c:url value="/Request/${bean.id}/Close"/>"
							class="remove_request_link fk-operation-link">[Remove]</a>
					</div>
				</div>
				<div class="row">
					<div class="small-6 columns end">
						<div class="medium-text-left fk-request-description">
							<p>
								<a href="#" class="editable-in-place" data-type="textarea"
									data-url="http://localhost:8080/review/Request/${bean.id}/Description/Edit">${bean.description}</a>
							</p>
						</div>
					</div>
				</div>
				<div class="row fk-line-height">
					<div class="small-12 columns medium-text-left">
						<c:forEach items="${bean.item.rating.popularTags}" var="weightTag">
							<c:if test="${weightTag ne null}">
								<span class="fk-label fk-header-fill fk-color-white">P</span>
								<span class="fk-label fk-radius">
									${weightTag.tag.viewName}[${weightTag.count}] </span>
								<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
							</c:if>
						</c:forEach>
					</div>
				</div>
				<div class="row fk-line-height">
					<div class="small-12 columns medium-text-left">
						<c:forEach items="${bean.item.connectedRating.popularTags}"
							var="weightTag">
							<c:if test="${weightTag ne null}">
								<span class="fk-label fk-header-fill fk-color-white">T</span>
								<span class="fk-label fk-radius">
									${weightTag.tag.viewName}[${weightTag.count}] </span>
								<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
							</c:if>
						</c:forEach>
					</div>
				</div>
				<div class="row ">
					<c:forEach items="${bean.reviewers}" var="reviewer"
						varStatus="count">
						<div class="row">
							<div class="small-1 columns medium-text-right">
								<img src="${reviewer.user.imageUrl}"
									class="fk-padding-except-right" />
							</div>
							<div class="small-11 columns medium-text-left end">
								<div class="row fk-padding-top">
									<div class="small-2 columns medium-text-left fk-bold">${reviewer.user.name}</div>
									<div class="small-4 columns medium-text-right end">
										<span>[${reviewer.status}]</span> <a
											href="<c:url value="Request/${bean.id}/Reviewer/${reviewer.id}/Remove"/>"
											class="remove_reviewer_link fk-operation-link">[Remove]</a>
									</div>
								</div>
								<c:if test="${not empty reviewer.reviews}">
									<c:forEach items="${reviewer.reviews}" var="review">
										<div class="row  fk-padding-top">
											<div class="small-7 columns end fk-review-display">
												<div class="row">
													<div class="small-12 columns">
														<p>${review.description}</p>
													</div>
												</div>
												<div class="row">
													<div class="small-3 columns">
														<c:forEach items="${review.tag.popularTags}"
															var="weightTag">
															<c:if test="${weightTag ne null}">
																<span class="fk-label fk-header-fill fk-color-white">T</span>
																<span class="fk-label fk-radius">
																	${weightTag.tag.viewName}</span>
																<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
															</c:if>
														</c:forEach>
													</div>
													<div class="small-4 columns medium-text-left end ">
														<ul class="inline-list">
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
						</div>
					</c:forEach>
				</div>
			</div>
			<hr />
		</c:forEach>
	</div>
</div>