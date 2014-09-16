<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<c:if test="${not empty interestedFriends}">
	<div class="row" style="margin-bottom: 1rem;">
		<div class="small-11 columns small-centered end"
			style="display: inline;">
			<c:forEach items="${interestedFriends}" var="friend">
				<div style="margin-left: 10px; display: inline;">
					<span
						data-dropdown="var_${requestId}_${reviewerId}_${friend.user.id}_id"
						data-options="is_hover:true"
						style="height: 20px; background-color: whitesmoke; padding: 5px; border-radius: 2px;">
						<a href="#"> <span class="fk-bold" style="font-size: 0.8rem;">${friend.user.name}</span>
					</a>
					</span>
					<div id="var_${requestId}_${reviewerId}_${friend.user.id}_id"
						class="f-dropdown" data-dropdown-content
						style="padding: 0px !important;">
						<div class=""
							style="margin: 0px !important; padding: 0px !important;">
							<div class="left">
								<img src="${friend.user.imageUrl}" class="profileImage" />
							</div>
							<div class="right fk-header-fill fk-center-align end"
								style="width: 50px;">
								<h3>
									<span class="fk-color-white fk-center-align">${friend.toalReviews}</span>
								</h3>
							</div>
						</div>
					</div>
				</div>
			</c:forEach>
		</div>
	</div>

	<script type="text/javascript">
		$(".f-dropdown").on('closed', function() {
			return false;
		});
	</script>
</c:if>
