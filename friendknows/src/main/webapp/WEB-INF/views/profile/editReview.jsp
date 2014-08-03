<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<form
	action="<c:url value="/Request/${requestId}/Reviewer/${reviewerId}/Review/${view.id}/Edit/Submit"/>"
	method="post">
	<table>
		<tr>
			<td colspan="2"><input type="hidden" name="reviewerRequestId"
				value="${view.id}" /></td>
		</tr>
		<tr>
			<td>Review</td>
			<td>
				<div class="border">
					<textarea name="reviewDescription">${view.description}</textarea>
				</div>
			</td>
		</tr>
		<tr>
			<td>Rating</td>
			<td><select name="rating">
					<option value="0" <c:if test="${view.rating == 0}"> selected </c:if>>WORKS</option>
					<option value="1" <c:if test="${view.rating == 1}"> selected </c:if>>VALUE_FOR_MONEY</option>
					<option value="2" <c:if test="${view.rating == 2}"> selected </c:if>>AWESOME</option>
					<option value="3" <c:if test="${view.rating == 3}"> selected </c:if>>HORRIBLE</option>
					<option value="4" <c:if test="${view.rating == 4}"> selected </c:if>>WORTH_IT</option>
			</select></td>
		</tr>
	</table>
</form>