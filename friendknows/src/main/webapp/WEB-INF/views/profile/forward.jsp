<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<div class="row fk-error hide"></div>
<form
	action="<c:url value="/Request/${requestId}/Reviewer/${reviewerId}/Forward/New/Submit"/>"
	method="post" class="form">
	<div class="row fk-padding-top">
		<input type="hidden" name="reviewerRequestId" value="${reviewerId}" />
		<div
			class="small-2 columns text-center fk-padding-top fk-label-heading">Friends</div>
		<div class="small-6 columns end">
			<input id="search-box" type="text" name="friends" />
		</div>
	</div>
	<div class="row fk-padding-top">
		<div class="small-2 columns small-centered">
			<input type="button" id="forward_request_button"
				class="button fk-no-margin" value="submit" />
		</div>
	</div>
</form>
<a class="close-reveal-modal">&#215;</a>
<SCRIPT type="text/javascript">
	$("#search-box").tokenInput("/friendknows/facebook/search/", {
		theme : "facebook",
		preventDuplicates : true,
	});

	$("#forward_request_button").bind("click", function(event) {
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
				var $containerId = $dialog.attr("data-append");
				var $container = $("#" + $containerId);
				$container.hide("slow", function() {
					$container.empty();
					$container.html(response);
					$container.show("slow");
				});
				$("#" + $dialogId).foundation('reveal', 'close');
			}
		});
	});
</SCRIPT>