<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<div class="row">
	<div class="small-11 columns small-centered end"
		style="display: inline;">
		<c:forEach items="${interestedFriends}" var="friend">
			<a href="#" data-dropdown="var_${friend.user.id}_id"> <img
				src="${friend.user.imageUrl}" />
			</a>
			<ul id="var_${friend.user.id}_id" class="small f-dropdown"
				data-dropdown-content>
				<li><div>Hiiii</div></li>
			</ul>
		</c:forEach>
	</div>
</div>
