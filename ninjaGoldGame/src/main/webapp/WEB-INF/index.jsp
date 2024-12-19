<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Ninja Gold Game</title>
		<link rel="stylesheet" href="/css/style.css" />
		<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
	</head>
	<body>
		<div class="flex">
			<h2>Your Gold: </h2>
			<p class="paraPadded"><c:out value="${gold}"></c:out></p>
			<form action="/update" method="post">
			<input type="hidden" name="action" value="reset" />
				<button>Reset!</button>
			</form>
		</div>
		<div id="actions" class="flex">
			<div class="actioncard">
				<h2>Farm</h2>
				<p>(earns 10-20 gold)</p>
				<form action="/update" method="post">
					<input type="hidden" name="action" value="farm" />
					<button>Find Gold!</button>
				</form>
			</div>
			<div class="actioncard">
				<h2>Cave</h2>
				<p>(earns 5-10 gold)</p>
				<form action="/update" method="post">
					<input type="hidden" name="action" value="cave" />
					<button>Find Gold!</button>
				</form>
			</div>
			<div class="actioncard">
				<h2>House</h2>
				<p>(earns 2-5 gold)</p>
				<form action="/update" method="post">
					<input type="hidden" name="action" value="house" />
					<button>Find Gold!</button>
				</form>
			</div>
			<div class="actioncard">
				<h2>Quest</h2>
				<p>(earns/takes 0-50 gold)</p>
				<form action="/update" method="post">
					<input type="hidden" name="action" value="quest" />
					<button>Find Gold!</button>
				</form>
			</div>
			<div class="actioncard">
				<h2>Spa</h2>
				<p>(takes 5-20 gold)</p>
				<form action="/update" method="post">
					<input type="hidden" name="action" value="spa" />
					<button>Give Gold!</button>
				</form>
			</div>
		</div>
		<div id="display">
			<h2>Activities</h2>
			<div class="scrollable">
				<c:forEach var="activity" items="${activities}">
					<c:set var="color" value="green" />
					<c:if test="${activity.contains('failed')}">
						<c:set var="color" value="red" />
					</c:if>
					<p style="color:${color}"><c:out value="${activity}"></c:out></p>
				</c:forEach>
			</div>
		</div>
	</body>
</html>