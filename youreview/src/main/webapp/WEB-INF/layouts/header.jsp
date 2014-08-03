<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ page session="false"%>

<div class="row">
	<div class="large-5 columns ">
		<h1>
			<a href="<c:url value="/"/>"> FriendKnows.com</a>
		</h1>
	</div>
	<div class="large-6 columns">
		<input type="text" id="fk_item_search_box"
			placeholder="search item example hyundai, refrigrator, restaruant..etc"
			style="height: 50px; width: 500px; float: left; margin-top: 20px; margin-left: 200px" />
	</div>
	<aside class="large-1 columns">
		<a href="<c:url value="/signout" />">Sign Out</a>
	</aside>
</div>
