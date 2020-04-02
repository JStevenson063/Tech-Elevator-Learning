<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html>

<html>

<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<c:url var="cssUrl" value="/css/site.css" />
<link rel="stylesheet" href="${cssUrl }" />

</head>
<body>

	<nav>
		<ul class="header">
			<c:url var="home" value="/" />
			<c:url var="survey" value="/survey" />
			<c:url var="favorites" value="/survey/confirmation" />
			<li><a href="${home}">Home</a></li>
			<li><a href="${survey}">Survey</a></li>
			<li><a href="${favorites}">Favorites</a></li>
		</ul>
	</nav>
	
	