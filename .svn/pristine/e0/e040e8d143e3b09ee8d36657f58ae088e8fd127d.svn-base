/**
 * 
 */
function doClose(requestId) {
	alert(requestId);
	$.ajax({
		url : "http://localhost:8080/review/request/close/" + requestId,
	}).done(function(data) {
		alert(data);
	});
}

function verify(reviewId) {
	$.ajax({
		url : "http://localhost:8080/review/reply/verify/" + reviewId,
		success : function(data) {
		},
		error : function(data) {
			alert(data);
		}
	}).done(function(data) {
		alert(data);
	});
}

function reply(id) {
	alert(id);
	$("#model-dialog-form input[name=reviewerRequestId]").val(id);
	$("#model-dialog").dialog("open");
}

function editDescription(request) {

}

$(function() {
	$("#model-dialog")
			.dialog(
					{
						autoOpen : false,
						height : 300,
						width : 350,
						modal : true,
						buttons : {
							"Reply" : function() {
								$
										.ajax(
												{
													method : "post",
													url : "http://localhost:8080/review/review/submit/ajax/done/",
													data : $(
															"#model-dialog-form")
															.serialize(),
												}).done(function(data) {
										});
							},
							Cancel : function() {
								$("#model-dialog-form").trigger("reset");
								$(this).dialog("close");
							}
						},
						close : function() {
							$("#model-dialog-form").trigger("reset");
						}
					});
	$("#model-dialog-request").dialog({
		autoOpen : false,
		modal : true,
		draggable : false,
		width : 700,
		buttons : {
			"Create" : function() {
				$.ajax({
					method : "post",
					headers : {
						Accept : "application/json",
					},
					url : "http://localhost:8080/review/request/submit",
					data : $(this).find("form").serialize(),
					dataType : 'json',
					error : function(response) {
						var obj = JSON.parse(response.responseText);
						$.each(obj.messages, function(key, value) {
							alert(key + ":" + value);
						});
					},
					success : function(response) {
						alert(response.responseText);
						$("#model-dialog").dialog("close");
					}
				}).done(function(data) {
					$(this).dialog("close");
				});
			},
			"Cancel" : function() {
				$("#friend-search").tokenInput("clear");
				$(this).trigger("reset");
				$(this).dialog("close");
			}
		},
		close : function() {
			$("#friend-search").tokenInput("clear");
			$(this).trigger("reset");
		}
	});

	$(".reply").button().click(function() {
		$("#model-dialog").dialog("open");
	});

	$("#friend-search").tokenInput("/review/facebook/search/", {
		theme : "facebook",
		preventDuplicates : true,
	});

	$("#item-search").tokenInput("/review/item/search/", {
		theme : "facebook",
		tokenLimit : 1,
		onResult : function(results) {
			return results;
		}
	});

	$("#category-search").tokenInput("/review/category/search/", {
		theme : "facebook",
		tokenLimit : 1,
	});

	$(".initiateRequest").button().click(function() {
		$("#model-dialog-request").find("form").trigger("reset");
		$("#model-dialog-request").dialog("open");
	});

	$("#model-dialog-add-reviewer")
			.dialog(
					{
						autoOpen : false,
						modal : true,
						draggable : false,
						width : 700,
						buttons : {
							"Add" : function() {
								$
										.ajax(
												{
													method : "post",
													headers : {
														Accept : "application/json",
													},
													url : "http://localhost:8080/review/reviewer/add",
													data : $(this).find("form")
															.serialize(),
													dataType : 'json',
													error : function(response) {
														var obj = JSON
																.parse(response.responseText);
														$
																.each(
																		obj.messages,
																		function(
																				key,
																				value) {
																			alert(key
																					+ ":"
																					+ value);
																		});
													},
													success : function(response) {
														var obj = response;
														$
																.each(
																		obj.bean,
																		function(
																				index,
																				reviewer) {
																			var requestId = $(
																					"#model-dialog-add-reviewer form input[name=requestId]")
																					.val();
																			alert(requestId);
																			alert(index
																					+ ":"
																					+ reviewer.id);
																			$(
																					"#"
																							+ requestId)
																					.append(
																							"<div>It is working</div");
																		});

														$(
																"#model-dialog-add-reviewer")
																.dialog("close");
													}
												})
										.done(
												function(data) {
													$(
															"#model-dialog-add-reviewer")
															.dialog("close");
												});
							},
							"Cancel" : function() {
								$("#friend-search-dialog").tokenInput("clear");
								$(this).trigger("reset");
								$(this).dialog("close");
							}
						},
						close : function() {
							$("#friend-search-dialog").tokenInput("clear");
							$(this).trigger("reset");
						}
					});

	$("#friend-search-dialog").tokenInput("/review/facebook/search/", {
		theme : "facebook",
		preventDuplicates : true,
	});

	$("#model-dialog-request-description-edit")
			.dialog(
					{
						autoOpen : false,
						modal : true,
						draggable : false,
						width : 700,
						buttons : [
								{
									text : "Save",
									click : function() {
										$
												.ajax(
														{
															method : "post",
															headers : {
																Accept : "application/json",
															},
															url : "http://localhost:8080/review/request/description/edit",
															data : $(this)
																	.find(
																			"form")
																	.serialize(),
															dataType : 'json',
															error : function(
																	response) {
																var obj = JSON
																		.parse(response.responseText);
																$
																		.each(
																				obj.messages,
																				function(
																						key,
																						value) {
																					alert(key
																							+ ":"
																							+ value);
																				});
															},
															success : function(
																	response) {
																$(
																		"#model-dialog-request-description-edit")
																		.dialog(
																				"close");
															}
														})
												.done(
														function(data) {
															$(
																	"#model-dialog-request-description-edit")
																	.dialog(
																			"close");
														});
									},

								}, {
									text : "Cancel",
									click : function() {
										$(this).trigger("reset");
										$(this).dialog("close");
									}
								} ],

						close : function() {
							$(this).trigger("reset");
						}
					});

});

function removeReviewer(reviewer, request) {
	$.ajax(
			{
				headers : {
					Accept : "application/json",
				},
				datatype : "json",
				url : "http://localhost:8080/review/reviewer/remove/" + request
						+ "/" + reviewer,
			}).done(function(data) {
		alert(data.responseText);
	});

}

function editdescription(request) {
	var descTagId = "#desc-" + request;
	var text = $(descTagId).text();
	$("#model-dialog-request-description-edit form textarea[name=description]")
			.val(text);
	$("#model-dialog-request-description-edit form input[name=requestId]").val(
			request);
	$("#model-dialog-request-description-edit").dialog("open");

}

function addReviewers(request) {
	$("#model-dialog-add-reviewer form input[name=requestId]").val(request);
	$("#model-dialog-add-reviewer").dialog("open");
}
