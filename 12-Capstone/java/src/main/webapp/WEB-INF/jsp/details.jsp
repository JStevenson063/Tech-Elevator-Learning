<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<style>
@import
	url('https://fonts.googleapis.com/css?family=Actor|Cabin+Sketch|Josefin+Sans|Noto+Sans+SC|Shadows+Into+Light+Two|Spinnaker&display=swap')
	;
</style>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<img class="homePageBG" src="../img/wood-24490.png" />

<div id="treeImgsDetails">
	<img class="tree1Details" src="../img/tree1.png" /> <img
		class="tree1CopyDetails" src="../img/tree1.png" /> <img
		class="tree2Details" src="../img/tree2.png" /> <img
		class="tree2CopyDetails" src="../img/tree2.png" /> <img
		class="tree3Details" src="../img/tree3.png" /> <img
		class="tree3CopyDetails" src="../img/tree3.png" /> <img
		class="tree4Details" src="../img/tree4.png" /> <img
		class="tree4CopyDetails" src="../img/tree4.png" />
</div>


<div>
	<h1 class="homePageName">Park Details</h1>

</div>

<div class="detailsQuote">
	<h3>" ${park.inspirationalQuote} "</h3>
	<h4>- ${park.inspirationalQuoteSource}</h4>
</div>

<img class="detailsParkImages"
	src="<c:url value="/img/parks/${fn:toLowerCase(park.parkCode)}.jpg" />" />

<div class="detailsPageInfo1">
	<h2>${park.parkName}</h2>
	<h2>- ${park.state} -</h2>
	<h2>Founded In ${park.yearFounded}</h2>
	<h2>Visitors Per Year: ${park.annualVisitorCount}</h2>
</div>

<br></br>

<div class="detailsPageInfo2">
	<p>${park.parkDescription}</p>
	<p>Climate: ${park.climate}</p>
	<p>Acreage: ${park.acreage} acres</p>
	<p>Elevation: ${park.elevationInFeet} feet</p>
	<p>Distance of Trail: ${park.milesOfTrail} miles</p>
	<p>Number of Camp Sites: ${park.numberOfCampSites}</p>
	<p>Entry Fee: $${park.entryFee}</p>
	<p>Unique Animal Species: ${park.numberOfAnimalSpecies} species</p>

</div>

<br></br>
<br></br>

<div class="tempDisplayButton">
	<h2>Temperature Display</h2>
	<c:url var="formAction" value="/park/details">
	</c:url>

	<form action="${formAction}" method="POST">

		<input type="radio" name="tempSelect" id="temperatureDisplay"
			value="Fahrenheit" />Fahrenheit <input type="radio"
			name="tempSelect" id="temperatureDisplay" value="Celsius" />Celsius
		<input type="hidden" name="parkCode" value="${park.parkCode}" />
		<br></br>
		<div>
			<input class="surveySubmitButton" type="submit" value="Submit" />
		</div>
	</form>
</div>

<br></br>

<div class="forecast">
	<c:set var="count" value="0" />
	<c:forEach items="${weather}" var="weather">
		<div>

			<h2>${weather.fiveDayForecastValue.plusDays(count)}</h2>
			<c:set var="count" value="${count + 1}" />
			<p>Forecast: ${weather.forecast}</p>
			<c:choose>
				<c:when test="${tempChoice.tempChoice.equals('Celsius')}">
					<p>High Temperature: ${weather.celciusHigh}</p>
					<p>Low Temperature: ${weather.celciusLow}</p>
				</c:when>
				<c:otherwise>
					<p>High Temperature: ${weather.temperatureHigh}</p>
					<p>High Temperature: ${weather.temperatureLow}</p>
				</c:otherwise>
			</c:choose>
			<c:set var="incomingWeather" value="${weather.forecast}" />
			<img class="parkImages"
				src="<c:url value="/img/weather/${fn:replace(incomingWeather, ' ', '')}.png" />" />
			<div>
				<c:choose>
					<c:when test="${tempChoice.tempChoice.equals('Celsius')}">
						<p>${weather.recommendationCelcius}</p>
					</c:when>
					<c:otherwise>
						<p>${weather.recommendationFahrenheit}</p>
					</c:otherwise>
				</c:choose>
			</div>

		</div>
	</c:forEach>
</div>
<br></br>
<br>
<br>
<br></br>
<c:import url="/WEB-INF/jsp/common/footer.jsp" />