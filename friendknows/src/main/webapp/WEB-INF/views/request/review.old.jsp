<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<style>
.ui-autocomplete-loading {
	background: white url('images/ui-anim_basic_16x16.gif') right center
		no-repeat;
}
</style>
<script>
	$(function() {
		function split(val) {
			return val.split(/,\s*/);
		}
		function extractLast(term) {
			return split(term).pop();
		}

		$("#category-search")
		// don't navigate away from the field on tab when selecting an item
		.bind(
				"keydown",
				function(event) {
					if (event.keyCode === $.ui.keyCode.TAB
							&& $(this).data("ui-autocomplete").menu.active) {
						event.preventDefault();
					}
				}).autocomplete({
			source : function(request, response) {
				$.getJSON("/review/category/search/", {
					term : extractLast(request.term)
				}, response);
			},
			search : function() {
				// custom minLength
				var term = extractLast(this.value);
				if (term.length < 2) {
					return false;
				}
			},
			focus : function() {
				// prevent value inserted on focus
				return false;
			},
			select : function(event, ui) {
				$("#category-search").val(ui.item.label);
				$("#category-search-hidden").val(ui.item.value);
				return false;
			}
		}).data("ui-autocomplete")._renderItem = function(ul, item) {
			return $("<li>").attr("data-value", item.value).append(
					$("<a>").text(item.label)).appendTo(ul);
		};

		$("#item-search")
		// don't navigate away from the field on tab when selecting an item
		.bind(
				"keydown",
				function(event) {
					if (event.keyCode === $.ui.keyCode.TAB
							&& $(this).data("ui-autocomplete").menu.active) {
						event.preventDefault();
					}
				}).autocomplete({
			source : function(request, response) {
				$.getJSON("/review/item/search/", {
					term : extractLast(request.term)
				}, response);
			},
			search : function() {
				// custom minLength
				var term = extractLast(this.value);
				if (term.length < 2) {
					return false;
				}
			},
			focus : function() {
				// prevent value inserted on focus
				return false;
			},
			select : function(event, ui) {
				$("#item-search").val(ui.item.label);
				$("#item-search-hidden").val(ui.item.value);
				return false;
			}
		}).data("ui-autocomplete")._renderItem = function(ul, item) {
			return $("<li>").attr("data-value", item.value).append(
					$("<a>").text(item.label)).appendTo(ul);
		};

		$("#friend-search")
		// don't navigate away from the field on tab when selecting an item
		.bind(
				"keydown",
				function(event) {
					if (event.keyCode === $.ui.keyCode.TAB
							&& $(this).data("ui-autocomplete").menu.active) {
						event.preventDefault();
					}
				}).autocomplete({
			source : function(request, response) {
				$.getJSON("/review/facebook/search/", {
					term : extractLast(request.term)
				}, response);
			},
			search : function() {
				// custom minLength
				var term = extractLast(this.value);
				if (term.length < 2) {
					return false;
				}
			},
			focus : function() {
				// prevent value inserted on focus
				return false;
			},
			select : function(event, ui) {
				var terms = split(this.value);
				var ids = split($("#friend-search-hidden").val());

				terms.pop();
				terms.push(ui.item.label);
				terms.push("");
				var value = terms.join(", ");

				ids.pop();
				ids.push(ui.item.value);
				ids.push("");
				var hiddenValues = ids.join(", ");

				$("#friend-search").val(value);
				$("#friend-search-hidden").val(hiddenValues);
				return false;
			}
		}).data("ui-autocomplete")._renderItem = function(ul, item) {
			return $("<li>").attr("data-value", item.value).append(
					$("<a>").text(item.label)).appendTo(ul);
		};

	});
</script>

<h3>Initiate a review</h3>

<c:forEach var="error" items="${errorMap}">
	<div>${error.key},${error.value}</div>
</c:forEach>

<form:form commandName="formBean" id="initateReview" method="POST"
	action="${formActionURL}">

	<table>

		<tr>
			<td width="30%" align="right">Category:</td>
			<td align="left"><input id="category-search" type="text" /> <form:input
					id="category-search-hidden" type="hidden" path="category" /></td>
		</tr>

		<tr>
			<td width="30%" align="right">Item:</td>
			<td align="left"><form:input id="item-search" type="text"
					path="itemDescription" /> <form:input id="item-search-hidden"
					type="hidden" path="item" /></td>
		</tr>
		<tr>
			<td width="30%" align="right">Description:</td>
			<td align="left"><form:input type="text" path="description" /></td>
		</tr>
		<tr>
			<td width="30%" align="right">Scope:</td>
			<td align="left"><form:radiobutton path="scope" value="public" />
				Public <form:radiobutton path="scope" value="Private" /> Private
		</tr>
		<tr>
			<td width="30%" align="right">Friends:</td>
			<td align="left"><input id="friend-search" type="text" /> <form:input
					id="friend-search-hidden" type="hidden" path="friends" /> <%-- 				<div>
					<table border="1">
						<c:forEach items="${friends}" var="friend" varStatus="index">
							<c:if
								test="${index.count < 6 || friend.name eq 'Monica Biswas' || friend.name eq 'Tarun Agrawal'}">
								<tr>
									<td><form:checkbox
											path="reviewers[${index.count-1}].reviewerProviderId"
											value='${friend.id}' /> <img
										src="http://graph.facebook.com/<c:out value="${friend.id}"/>/picture"
										align="middle" /> <c:out value="${friend.name}" /> { <c:out
											value="${friend.email}" /> } <form:hidden
											path="reviewers[${index.count-1}].name"
											value="${friend.name}" /></td>
									<td><form:input path="reviewers[${index.count-1}].mailId"
											type="text" /></td>
									<td><form:input
											path="reviewers[${index.count-1}].contactNumber" type="text" /></td>
								</tr>
							</c:if>
						</c:forEach>
					</table>
				</div>
 --%></td>
		</tr>
		<tr>
			<td colspan="2" align="center"><form:input type="submit"
					value="submit" path="" /></td>
		</tr>

	</table>

</form:form>








