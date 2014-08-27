<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<div class="row fk-error hide"></div>
<h1>Please submit your feedback !</h1>
<form action="<c:url value="/feedback/form/submit/"/>" method="post">
	<div class="row fk-padding">
		<div class="small-12 columns small-centered feedback-container">
			<div class="row collapse">
				<div class="small-11 columns small-text-left end">
					<textarea name="description" rows="5" cols="30"
						placeholder="Write your feedback..."></textarea>
				</div>
			</div>
			<div class="row collapse fk-padding-top fk-padding-bottom">
				<div class="small-3 columns small-text-right">
					<span class="prefix">Category</span>
				</div>
				<div class="small-5 columns small-text-left end fk-padding-top">
					<select name="category">
						<option value="Functional">Functional</option>
						<option value="User Interface">User Interface</option>
						<option value="General">General</option>
					</select>
				</div>
			</div>
			<div class="row fk-padding-top">
				<div class="small-3 columns small-centered">
					<input type="button" id="feedback_button"
						class="button small fk-no-margin" value="Submit" />
				</div>
			</div>
		</div>

	</div>
</form>
<a class="close-reveal-modal">&#215;</a>
<SCRIPT type="text/javascript">
	$("#feedback_button").bind("click", function(event) {
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
				Accept : "text/html"
			},
			url : $button.closest("form").attr("action"),
			data : $button.closest("form").serialize(),
			error : function(response) {
				$errorDiv.append(response.responseText);
				$errorDiv.show("slow");
			},
			success : function(response) {
				var $container = $("#feedback-container");
				$container.hide("slow", function() {
					$container.empty();
					$container.html("<div>Thanks for your response</div>");
					$container.show("slow");
				});
				$("#" + $dialogId).foundation('reveal', 'close');
			}
		});
	});
</SCRIPT>