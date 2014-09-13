<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<div class="row">
	<div class="small-12 large-10 columns ">
		<div class="row">
			<div class="small-10 columns fk-padding-top fk-requests-bar"
				style="margin-left: 0 !important; padding-left: 0px">
				<div class="row">
					<div class="small-11 columns ">
						<span class="fk-label fk-radius"
							style="font-size: 1rem; font-weight: bold; color: blue; border-color: blue !important;">${item.category.description}</span>
						<span class="fk-label fk-radius"
							style="font-size: 1rem; font-weight: bold; color: blue; border-color: blue !important;">${item.description}</span>
					</div>
				</div>
				<c:if test="${totalReviews eq 0}">
					<div class="row">
						<div class="small-11 columns">
							<p class="" style="margin-top: 2rem;">
								We have not received any recommendations for selected item,
								please <a href="<c:url value="/Request/New"/>"><b>create</b></a>
								a request and get the recommendations from trusted friends
							</p>
						</div>
					</div>
				</c:if>
				<c:if test="${totalReviews gt 0}">

					<div class="row fk-margin-top">
						<div class="small-3 columns text-right">
							<span><b>Total Reviews:</b></span>
						</div>
						<div class="small-9 columns medium-text-left">
							${totalReviews}</div>
					</div>
					<div class="row fk-margin-top">
						<div class="small-3 columns text-right">
							<span><b>Friend Reviews:</b></span>
						</div>
						<div class="small-9 columns medium-text-left">
							<c:if test="${totalConnectedReviews gt 0}">${totalConnectedReviews}</c:if>
							<c:if test="${totalConnectedReviews eq 0}">
								<p class="" >
									None of your friend has reviewed selected item, please <a
										href="<c:url value="/Request/New"/>"><b>create</b></a> a
									request and get the recommendations from trusted friends
								</p>
							</c:if>
						</div>
					</div>
					<div class="row fk-margin-top">
						<div class="small-3 columns text-right">
							<span><b>Public Views:</b></span>
						</div>
						<div class="small-9 columns medium-text-left">
							<c:forEach items="${item.rating}" var="tagView">
								<c:if test="${tagView ne null}">
									<span
										class="fk-label fk-header-fill fk-color-white fi-megaphone"></span><span class="fk-label"> 
										${tagView.tagName}</span><span class="fk-label fk-header-fill fk-color-white">${tagView.count}</span>
									<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
								</c:if>
							</c:forEach>
						</div>
					</div>
					<c:if test="${totalConnectedReviews gt 0}">
						<div class="row fk-margin-top">
							<div class="small-3 columns text-right">
								<span><b>Friend Views:</b></span>
							</div>
							<div class="small-9 columns medium-text-left">
								<c:forEach items="${item.connectedRating}" var="tagView">
									<c:if test="${tagView ne null}">
										<span
											class="fk-label fk-header-fill-trusted fk-color-white fi-link"></span><span class="fk-label-trusted"> 
											${tagView.tagName}</span><span class="fk-label fk-header-fill-trusted fk-color-white">${tagView.count}</span>
										<span>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span>
									</c:if>
								</c:forEach>
							</div>
						</div>
					</c:if>
					<div class="row fk-margin-top">

						<div class="small-11 columns medium-text-left">

							<c:if test="${not empty connected}">
								<c:forEach items="${connected}" var="review">
									<div class="row fk-no-margin fk-padding-top"
										style="margin-top: 0.4rem !important; margin-bottom: 0.4rem !important; background-color: whitesmoke; border-top: 3px solid #4698DD; border-bottom: 2px solid rgba(221, 219, 219, 1);">
										<div class="small-1 columns fk-no-padding">
											<img src="${review.reviewUser.imageUrl}" class="" />
										</div>
										<div class="small-10 columns end "
											style="padding-bottom: .8rem;">
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
												<div class="small-2 columns medium-text-left end ">
													<c:if test="${review.agreed}">
														<span class="fk-agreed">Agreed</span>
													</c:if>
												</div>
												<div class="small-6 columns medium-text-right end ">
													<a class="fk-date">${review.dateTime}</a>
												</div>
											</div>
										</div>
									</div>
								</c:forEach>
							</c:if>

							<c:if test="${not empty publicReviews}">
								<c:forEach items="${publicReviews}" var="review">
									<div class="row fk-no-margin fk-padding-top"
										style="margin-top: 0.4rem !important; margin-bottom: 0.4rem !important; background-color: whitesmoke; border-top: 3px solid #F05252; border-bottom: 2px solid rgba(221, 219, 219, 1);">
										<div class="small-1 columns fk-no-padding">
											<img src='<c:url value="/resources/images/anonymous.jpg"/>'
												class="" />
										</div>
										<div class="small-10 columns end "
											style="padding-bottom: .8rem;">
											<div class="row">
												<div class="small-12 columns">
													<p>${review.description}</p>
												</div>
											</div>
											<div class="row">
												<div class="small-4 columns">
													<span
														class="fk-label fk-header-fill fk-color-white fi-megaphone"></span><span
														class="fk-label"> ${review.tag.tagName}</span>
												</div>
												<div class="small-2 columns medium-text-left end ">
													<c:if test="${review.agreed}">
														<span class="fk-agreed">Agreed</span>
													</c:if>
												</div>
												<div class="small-6 columns medium-text-right end ">
													<a class="fk-date">${review.dateTime}</a>
												</div>
											</div>
										</div>
									</div>
								</c:forEach>
							</c:if>
						</div>
					</div>
				</c:if>
			</div>
		</div>
	</div>
</div>