<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<div class="row">
	<div class="small-12 large-12 columns ">
		<div class="row">
			<div class="small-8 columns small-centered"
				style="margin-top: 10rem;">
				<div class="row">
					<div class="small-6 columns fk-padding">
						<div class="fk-padding right fk-dashboad-box" style="">
							<h5>Requests</h5>
							<ul>
								<li><c:if test="${totalRequest eq 0}">You have not created any request !</c:if>
									<c:if test="${totalRequest gt 0}">You have created ${totalRequest} requests</c:if></li>
								<li><a style="" href="<c:url value="/Request/New"/>">Create
										a request</a></li>
								<li><a href="<c:url value="/profile"/>">Your Requests</a></li>
							</ul>
						</div>
					</div>
					<div class="small-6 columns fk-padding">
						<div class="fk-padding left fk-dashboad-box">
							<h5>Pending Answer</h5>
							<ul>
								<li><c:if test="${pendingAnswer eq 0}">
								    You do not have any request for answer !
								</c:if> <c:if test="${pendingAnswer gt 0}">
								You have ${pendingAnswer} request pending for answer
								</c:if></li>
								<li><a href="<c:url value="/request/assigned"/>">Answer
										friend request</a></li>
								<li>Your Reviews</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>

