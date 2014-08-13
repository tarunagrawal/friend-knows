<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="sf"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<div class="icon-bar vertical six-up fk-sidebar fixed "
	style="width: auto; margin-top: 2.8rem;">
	<a class="item" href="<c:url value="/Request/New"/>"> <label
		class="fi-plus fk-navigation-icon-size"></label>
	</a> <a class="item" href="<c:url value="/request/assigned"/>"> <label
		class="fi-anchor fk-navigation-icon-size"></label>
	</a> <a class="item"> <label class="fk-navigation-icon-size">rc</label>
	</a> <a class="item"><label class="fk-navigation-icon-size">pv</label>
	</a>
</div>
