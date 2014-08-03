<%@ page session="false"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h3>Review Acknowledgement</h3>


<form:form commandName="viewBean" id="reviewAcknowledgement" method="POST" action="">

	<table>
		<tr>
			<td width="30%" align="right">&nbsp;</td>
			<td align="left">&nbsp;</td>
		</tr>
		<tr>
			<td width="30%" align="right">Item:</td>
			<td align="left"><form:input type="text" path="item" readonly="true"/></td>
		</tr>
		<tr>
			<td width="30%" align="right">Description:</td>
			<td align="left"><form:input type="text" path="description" readonly="true"/></td>
		</tr>
		<tr>
			<td width="30%" align="right">Scope:</td>
			<td align="left">
			<form:radiobutton path="scope" value="public" readonly="true"/> Public
			<form:radiobutton path="scope" value="Private" readonly="true"/> Private
		</tr>
		<tr>
			<td width="30%" align="right">Friends:</td>
			<td align="left">
				<div>
					<table border="1">
						<c:forEach items="${friends}" var="friend" varStatus="index">
							<tr>
								<td><form:checkbox path="reviewers[${index.count}].reviewerProviderId" value='${friend.id}' readonly="true"/> <img
									src="http://graph.facebook.com/<c:out value="${friend.id}"/>/picture"
									align="middle" /> <c:out value="${friend.name}" /> { <c:out
										value="${friend.email}" /> }</td>
								<td><form:input path="reviewers[${index.count}].mailId" type="text"/></td>
								<td><form:input path="reviewers[${index.count}].contactNumber" type="text"/></td>		
							</tr>
						</c:forEach>
					</table>
				</div>
			</td>
		</tr>
	</table>

</form:form>








