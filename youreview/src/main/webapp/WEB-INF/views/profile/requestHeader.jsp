<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page session="false"%>
<div class="request-header">
	<div>
		<span id="${bean.category.id}" class="heading highlight">${bean.category.description}</span>
		<span>&nbsp;&nbsp;&nbsp;</span> <span id="${bean.item.id}"
			class="heading highlight"> ${bean.item.description}</span> <span>&nbsp;&nbsp;&nbsp;</span>

		<span class="detail">${bean.createDateTime}</span>

		<div class="operations">
			<span> <a
				href="<c:url value="/Request/${bean.id}/Reviewer/Add"/>"
				class="add_reviewer_link operation-link">Add Reviewers</a> <a
				href="<c:url value="/Request/${bean.id}/Close"/>"
				class="remove_request_link operation-link">Remove</a>
			</span>
		</div>
	</div>
	<div class="descripton">
		<a href="#" class="editable-in-place" data-type="textarea"
			data-url="http://localhost:8080/review/Request/${bean.id}/Description/Edit"
			data-inputclass="detail">${bean.description}</a>
	</div>
	<div class="tags">
		<div class="tag">
			<div class="label">All Tags:</div>
			<div class="data">
				<c:forEach items="${bean.item.rating.popularTags}" var="weightTag">
					<c:if test="${weightTag ne null}">
						<span class="heading highlight"> ${weightTag.tag.viewName}
							[${weightTag.count}]</span>
						<span>&nbsp;&nbsp;&nbsp; </span>
					</c:if>
				</c:forEach>
			</div>
			<div style="clear: both;"></div>
		</div>
		<div class="tag">
			<div class="label">Connected Tags:</div>
			<div class="data">
				<c:forEach items="${bean.item.connectedRating.popularTags}"
					var="weightTag">
					<c:if test="${weightTag ne null}">
						<span class="heading highlight"> ${weightTag.tag.viewName}
							[${weightTag.count}]</span>
						<span>&nbsp;&nbsp;&nbsp; </span>
					</c:if>
				</c:forEach>
			</div>
			<div style="clear: both;"></div>
		</div>
	</div>
</div>