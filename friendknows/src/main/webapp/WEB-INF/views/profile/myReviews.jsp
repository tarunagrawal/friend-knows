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
				<div class="row fk-margin-top">
					<div class="small-11 columns medium-text-left">
						<c:if test="${not empty view}">
							<c:forEach items="${view}" var="review">
								<div class="row fk-no-margin fk-padding-top"
									style="margin-top: 0.4rem !important; margin-bottom: 0.4rem !important; background-color: whitesmoke; border: 1px solid rgba(221, 219, 219, 1); border-radius:10px; ; border-bottom: 2px solid rgba(221, 219, 219, 1);border-left: 2px solid rgba(221, 219, 219, 1);">
									<div class="small-1 columns fk-no-padding">
										<img src="${review.reviewUser.imageUrl}" class="profileImage" />
									</div>
									<div class="small-10 columns end "
										style="padding-bottom: .8rem;">

										<div class="row">
											<div class="small-12 columns">
												<span class="fk-label fk-radius">${review.item.category.description}</span>
												<span class="fk-label fk-radius">${review.item.description}</span>
											</div>
										</div>

										<div class="row fk-padding-top">
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
											<div class="small-2 columns medium-text-left ">
												<c:if test="${review.agreed}">
													<span class="fk-agreed">Agreed</span>
												</c:if>
											</div>
											<div class="small-6 columns medium-text-right">
												<span class="fk-date">${review.dateTime}</span>
											</div>
										</div>
									</div>
								</div>
							</c:forEach>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>