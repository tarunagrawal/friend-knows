<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
	<form
		action="<c:url value="/Request/${requestId}/Reviewer/${reviewerId}/Forward/New/Submit"/>"
		method="post" class="form">
		<table>
			<tr>
				<td align="left" colspan="2"><input type="hidden"
					name="reviewerRequestId" value="${reviewerId}" /></td>
			</tr>
			<tr>
				<td width="30%" align="right">Friends:</td>
				<td align="left"><input id="search-box" type="text"
					name="friends" /></td>
			</tr>
		</table>
	</form>
</body>
</html>