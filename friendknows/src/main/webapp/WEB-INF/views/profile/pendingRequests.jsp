<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page session="false"%>
<style>
div.token-input-dropdown-facebook {
	z-index: 101;
}

div.ui-dialog {
	border-width: 2px;
	border-color: black;
	border-style: outset;
}

div.ui-widget-overlay {
	background-color: white;
}

div.border {
	border: 1px solid #8496ba;
}

textarea {
	border: none;
}

.editable-click {
	border-bottom: none;
}
</style>


<div class="row">
	<div class="small-12 large-7 columns"
		style="padding-left: 0px; padding-right: 0px;">
		<div class="small-1 columns fk-full-height hide-for-small end"
			style="padding-left: 0px; padding-right: 0px;">
			<jsp:include page="iconbar.jsp"></jsp:include>
		</div>
		<div class="small-11 columns fk-padding-top  end">
			<c:forEach items="${pending}" var="bean" varStatus="index">
				<c:set var="bean" value="${bean}" scope="request"></c:set>
				<div>
				<jsp:include page="reviewerData.jsp"></jsp:include>
				</div>
				
			</c:forEach>
		</div>
	</div>
</div>


