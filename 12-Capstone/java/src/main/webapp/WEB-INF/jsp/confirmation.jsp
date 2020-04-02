<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>


<style>
@import
	url('https://fonts.googleapis.com/css?family=Actor|Cabin+Sketch|Josefin+Sans|Noto+Sans+SC|Shadows+Into+Light+Two|Spinnaker&display=swap')
	;
</style>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<img class="homePageBG" src="../img/wood-24490.png" />

<div id="treeImgs">
	<img class="tree1Survey" src="../img/tree1.png" /> <img
		class="tree1CopySurvey" src="../img/tree1.png" /> <img
		class="tree2Survey" src="../img/tree2.png" /> <img
		class="tree2CopySurvey" src="../img/tree2.png" /> <img
		class="tree3Survey" src="../img/tree3.png" /> <img
		class="tree3CopySurvey" src="../img/tree3.png" /> <img
		class="tree4Survey" src="../img/parks/tree4.png" /> <img
		class="tree4CopySurvey" src="../img/tree4.png" />
</div>

<h1 class="homePageName">Favorite Parks</h1>


<c:forEach var="popular" items="${popularParks}">
	<div class="favoriteParkNameVotes">
		<h2>${popular.parkName}</h2>
		<c:url var="parkDetailsPageHref" value="/park/details">
			<c:param name="parkCode">${popular.parkCode}</c:param>
		</c:url>
		<h2>Number of Votes: ${popular.surveyCount}</h2>
	</div>	
	<a href="${parkDetailsPageHref}"> <img class="parkImages"
		src="<c:url value="/img/parks/${fn:toLowerCase(popular.parkCode)}.jpg" />" />
	</a>
	<br></br>
	<br></br>
</c:forEach>

<br></br>
<br></br>


<c:import url="/WEB-INF/jsp/common/footer.jsp" />