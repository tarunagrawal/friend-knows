/**
 * 
 */

function displayDetails(requestid) {
	var detailId = "#details-" + requestid;
	$(".fk-selected").removeClass("fk-selected");
	$("#summary_" + requestid).addClass("fk-selected");
	$(".fk-details").hide();
	$(detailId).show("slow");
}
function verify(requestId, reviewerId, reviewId) {
	$.ajax(
			{
				url : "http://localhost:8080/review/Request/" + requestId
						+ "/Reviewer/" + reviewerId + "/Review/" + reviewId
						+ "/Verify",
				success : function(data) {
				},
				error : function(data) {
					alert(data);
				}
			}).done(function(data) {
		alert(data);
	});
}

$(function() {

	$("#fk_item_search_box").tokenInput("/review/item/search/", {
		theme : "facebook",
		preventDuplicates : true,
		tokenLimit : 1,
	});

	$("body")
			.on(
					"click",
					"a.add_review_link",
					function(event) {
						event.preventDefault();
						var $link = $(this);
						var $dialog = $("<div></div");
						$dialog
								.load(
										$link.attr("href"),
										function() {
											$dialog
													.dialog({
														autoOpen : false,
														modal : true,
														draggable : false,
														width : 700,
														buttons : [
																{
																	text : "Add",
																	click : function() {
																		$
																				.ajax(
																						{
																							method : "post",
																							url : $dialog
																									.find(
																											"form")
																									.attr(
																											"action"),
																							data : $dialog
																									.find(
																											"form")
																									.serialize(),
																							success : function(
																									response) {
																								$dialog
																										.dialog("close");
																							},
																							error : function(
																									response) {
																								var jsonResponse = JSON
																										.parse(response.responseText);
																								$(
																										jsonResponse.messages)
																										.each(
																												function(
																														key,
																														value) {
																													alert(key
																															+ ":"
																															+ value);
																												});
																							}

																						})
																				.done(
																						function(
																								data) {
																							$dialog
																									.dialog("close");
																						});
																	}
																},
																{
																	text : "Cancel",
																	click : function() {
																		$dialog
																				.find(
																						"form")
																				.trigger(
																						"reset");
																		$dialog
																				.dialog("close");
																	}
																} ],
														close : function() {
															$dialog
																	.trigger("reset");
															$dialog
																	.trigger("destroy");
														}
													});

											$dialog.dialog("open");
										});
					});

	$("body")
			.on(
					"click",
					"a.edit_review_link",
					function(event) {
						event.preventDefault();
						var $link = $(this);
						var $dialog = $("<div></div");
						$dialog
								.load(
										$link.attr("href"),
										function() {
											$dialog
													.dialog({
														autoOpen : false,
														modal : true,
														draggable : false,
														width : 700,
														buttons : [
																{
																	text : "Update",
																	click : function() {
																		$
																				.ajax(
																						{
																							method : "post",
																							url : $dialog
																									.find(
																											"form")
																									.attr(
																											"action"),
																							data : $dialog
																									.find(
																											"form")
																									.serialize(),
																							success : function(
																									response) {
																								$dialog
																										.dialog("close");
																								$dialog
																										.trigger("destroy");

																							},
																							error : function(
																									response) {
																								var jsonResponse = JSON
																										.parse(response.responseText);
																								$(
																										jsonResponse.messages)
																										.each(
																												function(
																														key,
																														value) {
																													alert(key
																															+ ":"
																															+ value);
																												});
																							}

																						})
																				.done(
																						function(
																								data) {
																							$dialog
																									.dialog("close");
																						});
																	}
																},
																{
																	text : "Cancel",
																	click : function() {
																		$dialog
																				.find(
																						"form")
																				.trigger(
																						"reset");
																		$dialog
																				.dialog("close");
																	}
																} ],
														close : function() {
															$dialog
																	.trigger("reset");
															$dialog
																	.trigger("destroy");
														}
													});

											$dialog.dialog("open");
										});
					});

	$("body").on("click", ".fk-remove-reviewer-link", function(event) {
		event.preventDefault();
		var $link = $(this);
		$.ajax({
			method : "post",
			headers : {
				Accept : "application/json"
			},
			url : $link.attr("href"),
			data : $(this).find("form").serialize(),
			dataType : 'json',
			error : function(response) {
				var obj = JSON.parse(response.responseText);
				$.each(obj.messages, function(key, value) {
					alert(key + ":" + value);
				});
			},
			success : function(response) {
				var $reviewer = $link.closest(".fk-reviewer");
				$reviewer.hide("slow", function() {
					$reviewer.remove();
				});
			}
		});
		return false;
	});

	$("body").on("click", ".fk-remove-request-link", function(event) {
		event.preventDefault();
		var $link = $(this);
		var $details = $link.attr("data-remove");
		$.ajax({
			method : "post",
			headers : {
				Accept : "application/json"
			},
			url : $link.attr("href"),
			data : $(this).find("form").serialize(),
			dataType : 'json',
			error : function(response) {
				var obj = JSON.parse(response.responseText);
				$.each(obj.messages, function(key, value) {
					alert(key + ":" + value);
				});
			},
			success : function(response) {
				var $requestSummary = $link.closest(".fk-request-summary");
				$requestSummary.hide("slow", function() {
					$requestSummary.remove();
					$("#" + $details).remove();
				});

			}
		});
		return false;
	});

	$("body").on("click", "a.fk-remove-review-link", function(event) {
		event.preventDefault();
		var $link = $(this);
		$.ajax({
			method : "post",
			headers : {
				Accept : "text/html"
			},
			url : $link.attr("href"),
			data : $(this).find("form").serialize(),
			error : function(response) {
				alert(response);
			},
			success : function(response) {
				var $container = $link.closest(".fk-reviewer-data-container");
				$container.hide("slow", function() {
					$container.empty();
					$container.html(response);
					$container.show("slow");
				});
			}
		});
		return false;
	});

	$("body").on("click", "a.fk-remove-fwd-reviewer-link", function(event) {
		event.preventDefault();
		var $link = $(this);
		$.ajax({
			method : "post",
			headers : {
				Accept : "text/html"
			},
			url : $link.attr("href"),
			data : $(this).find("form").serialize(),
			error : function(response) {
				alert(response.responseText);
			},
			success : function(response) {
				var $containerId = $link.attr("data-append");
				var $container = $("#" + $containerId);
				$container.hide("slow", function() {
					$container.empty();
					$container.html(response);
					$container.show("slow");
				});
			}
		});
		return false;
	});

	// fk-agree-link

	$("body").on("click", "a.fk-agree-link", function(event) {
		event.preventDefault();
		var $link = $(this);
		$.ajax({
			headers : {
				Accept : "application/json"
			},
			url : $link.attr("href"),
			error : function(response) {
				alert(response.responseText);
			},
			success : function(response) {

				var $li = $link.closest("li");
				var $span = $("<span></span>").text("Agreed");
				$span.addClass("fk-agreed");

				$li.hide("slow", function() {
					$li.empty();
					$li.html($span);
					$li.show("slow");
				});
			}
		});
		return false;
	});

	$("body").on("click", "a.fk-propagated-back-link", function(event) {
		event.preventDefault();
		var $link = $(this);
		$.ajax({
			method : "post",
			headers : {
				Accept : "text/html"
			},
			url : $link.attr("href"),
			error : function(response) {
				alert(response.responseText);
			},
			success : function(response) {
				var $containerId = $link.attr("data-append");
				var $container = $("#" + $containerId);
				$container.hide("slow", function() {
					$container.empty();
					$container.html(response);
					$container.show("slow");
				});
			}
		});
		return false;
	});

	$("body").on("click", "a#product_search_button", function(event) {
		event.preventDefault();
		var $link = $(this);
		var href = $link.attr("href");
		var itemId = $("#hidden_search").val();
		if (itemId == "" || !($.trim(itemId).length > 0)) {
			return false;
		}
		href = href + "?itemId=" + itemId;
		$.ajax({
			method : "get",
			headers : {
				Accept : "text/html"
			},
			url : href,
			error : function(response) {
				alert(response.responseText);
			},
			success : function(response) {
				var $container = $("#content_container");
				$container.hide("slow", function() {
					$container.empty();
					$container.html(response);
					$container.show("slow");
				});
			}
		});

		return false;
	});

	$(".forward_review_link").bind("click", function(event) {
		event.preventDefault();
		var $link = $(this);
		var $dialog = $("<div></div");
		$dialog.load($link.attr("href"), function() {
			var $searchBox = $dialog.find("#search-box");
			$dialog.dialog({
				autoOpen : false,
				modal : true,
				draggable : false,
				width : 700,
				buttons : {
					"Add" : function() {
						$.ajax({
							method : "post",
							headers : {
								Accept : "application/json"
							},
							url : $(this).find("form").attr("action"),
							data : $(this).find("form").serialize(),
							dataType : 'json',
							error : function(response) {
								var obj = JSON.parse(response.responseText);
								$.each(obj.messages, function(key, value) {
									alert(key + ":" + value);
								});
							},
							success : function(response) {
							}
						}).done(function(data) {
							$dialog.dialog("close");
							$dialog.dialog("destroy");
						});

					},
					"Cancel" : function() {
						$searchBox.tokenInput("clear");
						$dialog.trigger("reset");
						$dialog.dialog("close");
						$dialog.dialog("destroy");
					}
				},
				close : function() {
					$searchBox.tokenInput("clear");
					$(this).trigger("reset");
				}
			});

			$dialog.on("dialogopen", function(event, ui) {
				$searchBox.tokenInput("/review/facebook/search/", {
					theme : "facebook",
					preventDuplicates : true,
				});

			});

			$dialog.dialog("open");

		});

	});

	$(".editable-in-place").each(
			function() {
				var $appnedId = $(this).attr("data-append");
				var $shortDescription = $("#" + $appnedId).find(
						".fk-short-description");
				$(this).editable({
					mode : 'inline',
					showbuttons : false,
					onblur : 'submit',
					send : 'always',
					ajaxOptions : {
						method : "post",
						headers : {
							Accept : "application/json",
						},
						dataType : 'json',
						success : function(response) {
							$shortDescription.hide("slow");
							$shortDescription.empty();
							$shortDescription.text(response.view);
							$shortDescription.show("slow");
						},
					}
				});
			});
});
