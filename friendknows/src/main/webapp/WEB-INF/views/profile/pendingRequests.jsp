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

.fk-reviewer-data-container {
	border-width: 1px;
	border-style: solid;
	border-color: rgb(192, 191, 191);
	background-color:  whitesmoke;
	border-radius:10px;
	border-left: 2px solid rgb(192, 191, 191);
	border-bottom: 2px solid rgb(192, 191, 191);
	/*  */
}

 .fk-reviewer-data-container:HOVER {
	box-shadow: inset 0px 70px 30px rgb(192, 191, 191);
 } 
</style>




<div class="row">
	<div class="small-12 large-10 columns ">
		<div class="row">
			<div class="small-10 columns fk-padding-top  end">

				<c:if test="${empty pending}">
                        You do not have any request pending requests !
				</c:if>

				<c:forEach items="${pending}" var="bean" varStatus="index">
					<c:set var="view" value="${bean}" scope="request"></c:set>
					<div class="fk-reviewer-data-container fk-margin-top"
						id="reviewer_data_container_${view.id}">
						<jsp:include page="reviewerData.jsp"></jsp:include>
					</div>

				</c:forEach>
			</div>
		</div>
	</div>
</div>


