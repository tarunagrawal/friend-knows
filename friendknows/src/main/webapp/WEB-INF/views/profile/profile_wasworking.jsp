<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<script type="text/javascript">
	
</script>
<%@ page session="false"%>
<style>
div.token-input-dropdown-facebook {
	z-index: 101;
}

div.ui-dialog {
	border-width: 2px;
	border-color: black;
	border-style: outset;
}

div.ui-widget-overlay {
	background-color: white;
}

div.border {
	border: 1px solid #8496ba;
}

textarea {
	border: none;
}

.editable-click {
	border-bottom: none;
}
</style>
<div class="fk-wall">
	<div class="fk-container">
		<div class="fk-request-container">
			<c:forEach items="${initiated}" var="bean" varStatus="index">
				<div class="fk-request" id="${bean.id}">
					<div class="fk-request-header">
						<div class="fk-category-item-operation-panel">
							<div class="fk-category-item">
								<span id="${bean.category.id}" class="fk-heading fk-highlight">${bean.category.description}</span>
								<span>&nbsp;&nbsp;&nbsp;</span> <span id="${bean.item.id}"
									class="fk-heading fk-highlight">
									${bean.item.description}</span> <span>&nbsp;&nbsp;&nbsp;</span>
							</div>
							<div class="fk-operations">
								<span class="fk-detail fk-operation-link">${bean.createDateTime}</span>
								<span> <a
									href="<c:url value="/Request/${bean.id}/Reviewer/Add"/>"
									class="add_reviewer_link fk-operation-link">[Add Reviewers]</a>
									<a href="<c:url value="/Request/${bean.id}/Close"/>"
									class="remove_request_link fk-operation-link">[Remove]</a>
								</span>
							</div>
						</div>
						<div class="fk-descripton">
							<a href="#" class="editable-in-place" data-type="textarea"
								data-url="http://localhost:8080/review/Request/${bean.id}/Description/Edit">${bean.description}</a>
						</div>
						<div class="fk-tags">
							<div class="fk-tag">
								<div class="fk-label">All Tags:</div>
								<div class="fk-data">
									<c:forEach items="${bean.item.rating.popularTags}"
										var="weightTag">
										<c:if test="${weightTag ne null}">
											<span class="fk-highlight"> ${weightTag.tag.viewName}
												[${weightTag.count}]</span>
											<span>&nbsp;&nbsp;&nbsp; </span>
										</c:if>
									</c:forEach>
								</div>
								<div style="clear: both;"></div>
							</div>
							<div class="fk-tag">
								<div class="fk-label">Trusted Tags:</div>
								<div class="fk-tag-data">
									<c:forEach items="${bean.item.connectedRating.popularTags}"
										var="weightTag">
										<c:if test="${weightTag ne null}">
											<span class="fk-highlight"> ${weightTag.tag.viewName}
												[${weightTag.count}]</span>
											<span>&nbsp;&nbsp;&nbsp; </span>
										</c:if>
									</c:forEach>
								</div>
								<div style="clear: both;"></div>
							</div>
						</div>
					</div>
					<div class="fk-reviewers">
						<div class="fk-reviewerscontainer">
							<c:forEach items="${bean.reviewers}" var="reviewer"
								varStatus="count">
								<div class="fk-reviewercontainer">
									<div class="fk-reviewer">
										<div id="${reviewer.id}" class="fk-reviewer-data">
											<div class="fk-image">
												<img src="${reviewer.user.imageUrl}" align="middle" />
											</div>
											<div class="fk-data">
												<div class="fk-information">
													<div class="fk-user-informaion">
														<span class="fk-detail">${reviewer.user.name}</span> <span>&nbsp;&nbsp;&nbsp;</span>
														<span class="fk-detail">${reviewer.status}</span> <span>&nbsp;&nbsp;&nbsp;</span>
													</div>
													<div class="fk-remove-operation-panel">
														<a
															href="<c:url value="Request/${bean.id}/Reviewer/${reviewer.id}/Remove"/>"
															class="remove_reviewer_link fk-operation-link">Remove</a>
													</div>
												</div>
												<c:if test="${not empty reviewer.reviews}">
													<div class="fk-review">
														<c:forEach items="${reviewer.reviews}" var="review">
															<div>
																<span class="fk-detail">${review.description}</span><span>&nbsp;&nbsp;&nbsp;</span>
																<span class="fk-detail"> <c:forEach
																		items="${review.tag.popularTags}" var="weightTag">
																		<c:if test="${weightTag ne null}">
																			<span class="detail">
																				${weightTag.tag.viewName}</span>
																			<span>&nbsp;&nbsp;&nbsp; </span>
																		</c:if>
																	</c:forEach>
																</span>
																<div>
																	<span class="fk-operation-link">${review.dateTime}</span>
																	<a onclick="javascript:verify('${review.id}');"
																		class="fk-operation-link">Agree</a>
																</div>
															</div>
														</c:forEach>
													</div>
												</c:if>
											</div>
											<div style="clear: both;"></div>
										</div>
									</div>
								</div>
							</c:forEach>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>
</div>
