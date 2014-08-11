<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>


<form
	action="<c:url value="/Request/${requestId}/Reviewer/${reviewerId}/Review/${view.id}/Edit/Submit"/>"
	method="post">
	<input type="hidden" name="reviewerRequestId" value="${view.id}" />
	<div class="row fk-padding">
		<div class="small-12 columns small-centered">
			<div class="row collapse">
				<div class="small-11 columns small-text-left end">
					<textarea name="reviewDescription" rows="5" cols="30"
						placeholder="Write your views...">${view.description}</textarea>
				</div>
			</div>
			<div class="row collapse fk-padding-top fk-padding-bottom">
				<div class="small-3 columns small-text-right">
					<span class="prefix">One Word</span>
				</div>
				<div class="small-5 columns small-text-left end fk-padding-top">
					<select name="rating">
						<option value="0"
							<c:if test="${view.rating == 0}"> selected </c:if>>WORKS</option>
						<option value="1"
							<c:if test="${view.rating == 1}"> selected </c:if>>VALUE_FOR_MONEY</option>
						<option value="2"
							<c:if test="${view.rating == 2}"> selected </c:if>>AWESOME</option>
						<option value="3"
							<c:if test="${view.rating == 3}"> selected </c:if>>HORRIBLE</option>
						<option value="4"
							<c:if test="${view.rating == 4}"> selected </c:if>>WORTH_IT</option>
					</select>
				</div>
			</div>
			<div class="row fk-padding-top">
				<div class="small-3 columns small-centered">
					<input type="button" id="edit_review_button"
						class="button small fk-no-margin" value="Submit" />
				</div>
			</div>
		</div>

	</div>
</form>
<a class="close-reveal-modal">&#215;</a>
<SCRIPT type="text/javascript">
	$("#edit_review_button").bind("click", function(event) {
		event.preventDefault();
		$button = $(this);
		$dialog = $button.closest(".reveal-modal");
		$dialogId = $dialog.attr("id");
		$errorDiv = $dialog.find(".fk-error");
		$errorDiv.hide();
		$errorDiv.empty();
		$.ajax({
			method : "post",
			headers : {
				Accept : "application/json"
			},
			url : $button.closest("form").attr("action"),
			data : $button.closest("form").serialize(),
			error : function(response) {
				$errorDiv.append(response.responseText);
				$errorDiv.show("slow");
			},
			success : function(response) {
				var $requestParentId = $dialog.attr("data-append");
				var $reviewData = $("#"+$requestParentId);
				var $descriptionData = $reviewData.find(".fk-review-text-data");
				$descriptionData.hide("slow", function(){
					$descriptionData.empty();
					$descriptionData.html(response.view.description);
					$descriptionData.show("slow");
				});
				alert(response.view.tag.popularTags[0].tag);
				$reviewData.find(".fk-tag-viewName");
				
				$("#" + $dialogId).foundation('reveal', 'close');
			}
		});
	});
</SCRIPT>