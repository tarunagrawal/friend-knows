<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<h3>Your Recommendation</h3>
<div class="row fk-error hide"></div>
<form action="<c:url value="/Review/Independent/New/Submit"/>"
	method="post">
	<input type="hidden" name="reviewerRequestId" value="Independent" />
	<div class="row fk-padding">
		<div class="small-12 columns small-centered">

			<div class="row collapse fk-padding-top fk-padding-bottom">
				<div class="small-3 large-2 columns">
					<span class="prefix">Category</span>
				</div>
				<div class="small-9 large-10 columns">
					<select name="category">
						<c:forEach items="${categories}" var="category">
							<option value="${category.id}">${category.description}</option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="row collapse fk-padding-top fk-padding-bottom">
				<div class="small-3 large-2 columns">
					<span class="prefix fk-inline-prefix">Item</span>
				</div>
				<div class="small-9 large-10 columns">
					<input id="item-search" type="text" name="item"
						placeholder="Select Item..." />
				</div>
			</div>

			<div class="row collapse">
				<div class="small-11 columns small-text-left end">
					<textarea name="reviewDescription" rows="5" cols="30"
						placeholder="Write your views..."></textarea>
				</div>
			</div>
			<div class="row collapse fk-padding-top fk-padding-bottom">
				<div class="small-3 columns small-text-right">
					<span class="prefix">One Word</span>
				</div>
				<div class="small-5 columns small-text-left end fk-padding-top">
					<select name="rating">
						<option value="0">WORKS</option>
						<option value="1">VALUE_FOR_MONEY</option>
						<option value="2">AWESOME</option>
						<option value="3">HORRIBLE</option>
						<option value="4">WORTH_IT</option>
					</select>
				</div>
			</div>
			<div class="row fk-padding-top">
				<div class="small-3 columns small-centered">
					<input type="button" id="add_review_button"
						class="button small fk-no-margin" value="Add Review" />
				</div>
			</div>
		</div>

	</div>
</form>
<a class="close-reveal-modal">&#215;</a>

<SCRIPT type="text/javascript">

$(".fk-selected-icon").removeClass("fk-selected");
$("#independent-review-icon label").addClass("fk-selected-icon");



	$("#add_review_button").bind("click", function(event) {
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
				$errorDiv.html(response);
				$errorDiv.show("slow");
			},
			success : function(response) {
				$("#" + $dialogId).foundation('reveal', 'close');
			}
		});
	});

	$("#item-search").tokenInput("<c:url value='/item/search/'/>", {
		theme : "facebook",
		tokenLimit : 1,
		onResult : function(results) {
			var id = "token-input-" + $(this).attr("id");
			var searchTerm = $("#" + id).val();
			results[0] = {
				id : "new:" + searchTerm,
				name : searchTerm
			};
			return results;
		}
	});
</SCRIPT>