<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page session="false"%>
<div class="reviewer">
	<div id="${reviewer.id}" class="reviewer-data">
		<div class="image">
			<img src="${reviewer.user.imageUrl}" align="middle" />
		</div>
		<div class="data">
			<span class="detail">${reviewer.user.name}</span> <span>&nbsp;&nbsp;&nbsp;</span>
			<span class="detail">${reviewer.status}</span> <span>&nbsp;&nbsp;&nbsp;</span>
			<c:if test="${onlyverified eq false}">
				<div style="float: right">
					<a
						href="<c:url value="Request/${bean.id}/Reviewer/${reviewer.id}/Remove"/>"
						class="remove_reviewer_link operation-link">Remove</a>
				</div>
			</c:if>
			<c:if test="${not empty reviewer.reviews}">
				<div class="review">
					<c:forEach items="${reviewer.reviews}" var="review">
						<c:if test="${onlyverified eq true}">
							<c:if test="${review.verified eq 'Y'}">
								<div>
									<span class="detail">${review.description}</span><span>&nbsp;&nbsp;&nbsp;</span>
									<span class="detail"> <c:forEach
											items="${review.tag.popularTags}" var="weightTag">
											<c:if test="${weightTag ne null}">
												<span class="detail">
													${weightTag.tag.viewName}[${weightTag.count}]</span>
												<span>&nbsp;&nbsp;&nbsp; </span>
											</c:if>
										</c:forEach>
									</span> <span>&nbsp;&nbsp;&nbsp;</span> <span class="detail">${review.dateTime}</span>
								</div>
							</c:if>
						</c:if>
						<c:if test="${onlyverified eq false}">
							<div>
								<span class="detail">${review.description}</span><span>&nbsp;&nbsp;&nbsp;</span>
								<span class="detail"> <c:forEach
										items="${review.tag.popularTags}" var="weightTag">
										<c:if test="${weightTag ne null}">
											<span class="detail"> ${weightTag.tag.viewName}</span>
											<span>&nbsp;&nbsp;&nbsp; </span>
										</c:if>
									</c:forEach>
								</span>
								<div>
									<span class="detail">${review.dateTime}</span> <a
										onclick="javascript:verify('${review.id}');"
										class="operation-link">Agree</a>
								</div>
							</div>
						</c:if>
					</c:forEach>
				</div>
			</c:if>
			<c:if test="${reviewer.status eq 'PROPAGATED'}">
				<div>
					<span>${reviewer.user.name} &nbsp; have forwarded to
						following friends : <c:forEach
							items="${reviewer.forwardRequest.reviewers}" var="tempReviewer"> ${tempReviewer.user.name} &nbsp; ,</c:forEach>
					</span>
				</div>
				<%
					Object bean = request.getAttribute("bean");
						boolean onlyverified = (Boolean) request.getAttribute("onlyverified");
						request.setAttribute("onlyverified", new Boolean(true));
						request.setAttribute("bean", new Boolean(true));
				%>
				<c:set var="bean" value="${reviewer.forwardRequest}" scope="request" />
				<jsp:include page="reviewersPrint.jsp"></jsp:include>
				<%
					request.setAttribute("bean", bean);
						request.setAttribute("onlyverified", new Boolean(onlyverified));
				%>
			</c:if>
		</div>
		<div style="clear: both;"></div>
	</div>
</div>
