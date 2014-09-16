<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<style>
.ui-autocomplete-loading {
	background: white url('images/ui-anim_basic_16x16.gif') right center
		no-repeat;
}

.fk-no-height {
	height: 2.18rem;
}

.fk-inline-prefix {
	height: 2.7125rem;
}
</style>
<script>
	$(document)
			.ready(
					function() {

						$("#friend-search").tokenInput(
								"<c:url value='/facebook/search/' />", {
									theme : "facebook",
									preventDuplicates : true,
								});

						$("#item-search")
								.tokenInput(
										"<c:url value='/item/search/' />",
										{
											theme : "facebook",
											tokenLimit : 1,
											onResult : function(results) {
												var id = "token-input-"
														+ $(this).attr("id");
												var searchTerm = $("#" + id)
														.val();
												results[0] = {
													id : "new:" + searchTerm,
													name : searchTerm
												};
												return results;
											},

											onAdd : function() {
												var url = "<c:url value='/item/'/>"
														+ $("#item-search")
																.val()
														+ "/friends/interested";
												$
														.ajax(
																{
																	url : url,
																	headers : {
																		Accept : "text/html"
																	},
																	success : function(
																			data) {
																		var $interested_friend = $("#interested_friend");
																		$interested_friend.html(data);
																		$interested_friend
																				.show("slow");
																	},
																	error : function(
																			data) {
																		alert(data);
																	}
																}).done(
																function(data) {
																});

											},
											onDelete : function() {
												var $interested_friend = $("#interested_friend");
												$interested_friend.hide("slow",
														function() {
															$interested_friend
																	.empty();
														});
											}

										});

						$("#category-search").tokenInput(
								"<c:url value='/category/search/'/>", {
									theme : "facebook",
									tokenLimit : 1,
								});

					});
</script>


<form:form commandName="form" id="initateReview" method="POST"
	action="${formActionURL}">
	<div class="row">
		<div class="small-2 columns fk-full-height hide-for-small end"
			style="padding-left: 0px; padding-right: 0px;">
			<jsp:include page="../profile/iconbar.jsp"></jsp:include>
		</div>

		<div class="small-6 columns fk-padding">
			<div class="row">
				<div class="small-12 large-12 columns fk-error">
					<c:forEach var="error" items="${messages}">
						<div>${error.value}</div>
					</c:forEach>
				</div>

			</div>
			<div class="row">
				<div class="row collapse">
					<div class="small-3 large-2 columns">
						<span class="prefix ">Category</span>
					</div>
					<div class="small-9 large-10 columns">

						<select name="category">
							<c:forEach items="${categories}" var="category">
								<option value="${category.id}">${category.description}</option>
							</c:forEach>
						</select> 
					</div>
				</div>
				<div class="row collapse fk-padding-top">
					<div class="small-3 large-2 columns">
						<span class="prefix fk-inline-prefix">Item</span>
					</div>
					<div class="small-9 large-10 columns">
						<form:input id="item-search" type="text" path="item"
							placeholder="Select Item..." />
					</div>
				</div>
				<div class="row collapse">
					<div class="small-12 large-12 columns">
						<form:textarea path="description"
							placeholder="Write description....." rows="5" />
					</div>
				</div>
				<div class="row collapse fk-margin-top">
					<div class="small-3 large-2 columns fk-margin-top">
						<span class="prefix">Scope</span>
					</div>
					<div class="small-9 large-10 columns fk-margin-top">
						<form:select path="scope">
							<form:option value="public">Public</form:option>
							<form:option value="private">Private</form:option>
						</form:select>
					</div>
				</div>
				<div class="row collapse fk-margin-top">
					<div class="small-3 large-2 columns fk-margin-top">
						<span class="prefix fk-inline-prefix">Friends</span>
					</div>
					<div class="small-9 large-10 columns fk-margin-top">
						<input id="friend-search" type="text" name="friends" />
					</div>
				</div>
				<div class="row ">
					<div class="small-3 large-2 columns small-centered">
						<form:input type="submit" value="submit" class="button" path="" />
					</div>
				</div>
			</div>
		</div>
		<div class="small-4 columns end fk-padding" id="interested_friend"></div>
	</div>

</form:form>
