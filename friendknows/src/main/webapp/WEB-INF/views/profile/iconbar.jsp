<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<div class="icon-bar vertical six-up fk-sidebar fixed "
	style="width: auto; margin-top: 2.8rem;">

	<a class="item has-tip tip-right radius" data-tooltip title="Dashboard"
		href="<c:url value="/dashboard/"/>"> <label
		class="fi-page-multiple fk-navigation-icon-size"></label>
	</a> <a class="item has-tip tip-right radius" data-tooltip
		title="Your requests" href="<c:url value="/profile"/>"> <label
		class="fi-clipboard-notes fk-navigation-icon-size"></label>
	</a> <a class="item has-tip tip-right radius " data-tooltip
		title="New request" href="<c:url value="/Request/New"/>"> <label
		class="fi-plus fk-navigation-icon-size"></label>
	</a> <a class="item has-tip tip-right radius " data-tooltip
		title="Friends request waiting for your answer !"
		href="<c:url value="/request/assigned"/>"> <label
		id="icon_pending_annswer_requests"
		class="fi-anchor fk-navigation-icon-size"></label>
	</a> <a class="item has-tip tip-right radius " data-tooltip
		title="Add a new review"
		href="<c:url value="/Review/Independent/New"/>"
		data-reveal-id="independent_review" data-reveal-ajax="true"> <label
		class="fi-lightbulb fk-navigation-icon-size"></label>
	</a> <a class="item has-tip tip-right radius"
		href="<c:url value='/my/reviews/'/>" data-tooltip title="Your Reviews"><label
		class="fi-results-demographics fk-navigation-icon-size"></label> </a>

	<div id="independent_review" class="reveal-modal small"
		data-options="close_on_background_click:false" data-reveal></div>
</div>
