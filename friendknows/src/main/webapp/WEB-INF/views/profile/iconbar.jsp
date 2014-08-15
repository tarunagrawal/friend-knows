<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<div class="icon-bar vertical six-up fk-sidebar fixed "
	style="width: auto; margin-top: 2.8rem;">

	<a class="item" href="<c:url value="/"/>"> <label
		class="fi-clipboard-notes fk-navigation-icon-size"></label></a> <a
		class="item" href="<c:url value="/Request/New"/>"> <label
		class="fi-plus fk-navigation-icon-size"></label>
	</a> <a class="item" href="<c:url value="/request/assigned"/>"> <label
		class="fi-anchor fk-navigation-icon-size"></label>
	</a> <a class="item has-tip tip-right radius " data-tooltip title="your reviews list"> <label class="fi-lightbulb fk-navigation-icon-size"></label>
	</a> <a class="item"><label class="fi-results-demographics fk-navigation-icon-size"></label>
	</a>
</div>
