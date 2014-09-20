<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<div class="row cleafix">
	<div class="small-11 columns ">
		<div class="row">
			<div class="small-11 columns text-center"
				style="background-color: rgba(239, 239, 13, 1); padding: 10px;">
				<span style="font-size: 1rem; font-weight: bold;">Friends
					already reviewed</span>
			</div>
		</div>
		<div class="row">
			<div class="small-11 columns medium-text-right">
				<c:forEach items="${interestedFriends}" var="friend">
					<div class="row fk-suggested-friend-background"
						style="margin-bottom: 3px">
						<div class="small-2 columns medium-text-center ">
							<img src="${friend.user.imageUrl}" class="profileImage" />
						</div>
						<div class="small-8 columns medium-text-left end">
							<span class="fk-bold" style="font-size: 0.8rem;">${friend.user.name}</span>
							<br /> <span style="font-weight: 100; color: gray;">Reviews
								: </span><span>${friend.toalReviews}</span>
						</div>
					</div>
				</c:forEach>
			</div>
		</div>
	</div>
</div>


