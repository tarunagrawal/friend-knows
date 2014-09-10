<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<div class="row">
	<div class="small-8 columns medium-text-right  small-centered">
		<c:forEach items="${interestedFriends}" var="friend">
			<div class="row fk-suggested-friend-background" style="margin-bottom: 10px">
				<div
					class="small-3 columns medium-text-center ">
					<img src="${friend.user.imageUrl}" />
				</div>
				<div
					class="small-6 columns medium-text-left ">
					<p class="fk-bold" style="font-size: 0.8rem;">${friend.user.name}</p>
				</div>
				<div
					class="small-3 columns  fk-header-fill fk-center-align end">
					<h3>
						<span class="fk-color-white fk-center-align">${friend.toalReviews}</span>
					</h3>
				</div>
			</div>
		</c:forEach>
	</div>
</div>
