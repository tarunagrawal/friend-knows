<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h3>Submit A Review</h3>

<c:forEach var="error" items="${errorMap}">
	<div>${error.key},${error.value}</div>
</c:forEach>

<form:form commandName="formBean" id="submitReview" method="POST"
	action="${formActionURL}">
	<table>
		<tr>
			<td>Choose Review Request</td>
			<td><form:select path="reviewerRequestId"
					items="${reviewersRequest}"></form:select></td>
		</tr>

		<tr>
			<td>Review</td>
			<td><form:input path="reviewDescription" /></td>
		</tr>

		<tr>
			<td>Rating</td>
			<td><form:input path="rating" /></td>
		</tr>

		<tr>
			<td colspan="2" align="center"><form:input type="submit"
					value="submit" path="" /></td>
		</tr>
	</table>

</form:form>








