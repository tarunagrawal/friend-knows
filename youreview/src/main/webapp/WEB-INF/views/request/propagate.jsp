<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h3>Propagate A Review</h3>

<c:forEach var="error" items="${errorMap}">
	<div>${error.key},${error.value}</div>
</c:forEach>
<br />

<form:form commandName="formBean" id="submitReview" method="POST"
	action="${formActionURL}">
	<table>
		<tr>
			<td>Choose Review Request</td>
			<td><form:select path="reviewerRequestId"
					items="${reviewersRequest}"></form:select></td>
		</tr>

		<tr>
			<td width="30%" align="right">Friends:</td>
			<td align="left">
				<div>
					<table border="1">
						<c:forEach items="${friends}" var="friend" varStatus="index">
							<c:if test="${index.count < 6 || friend.name eq 'Monica Biswas' || friend.name eq 'Tarun Agrawal'}">
							<tr>
								<td><form:checkbox
										path="reviewers[${index.count}].reviewerProviderId"
										value='${friend.id}' /> <img
									src="http://graph.facebook.com/<c:out value="${friend.id}"/>/picture"
									align="middle" /> <c:out value="${friend.name}" /> { <c:out
										value="${friend.email}" /> } <form:hidden
										path="reviewers[${index.count}].name" value="${friend.name}" />
								</td>
								<td><form:input path="reviewers[${index.count}].mailId"
										type="text" /></td>
								<td><form:input
										path="reviewers[${index.count}].contactNumber" type="text" /></td>
							</tr>
							</c:if>
						</c:forEach>
					</table>
				</div>
			</td>
		</tr>

		<tr>
			<td colspan="2" align="center"><form:input type="submit"
					value="submit" path="" /></td>
		</tr>
	</table>

</form:form>








