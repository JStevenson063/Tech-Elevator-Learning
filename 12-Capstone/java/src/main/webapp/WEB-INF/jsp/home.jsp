<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<style>
@import
	url('https://fonts.googleapis.com/css?family=Actor|Cabin+Sketch|Josefin+Sans|Noto+Sans+SC|Shadows+Into+Light+Two|Spinnaker&display=swap')
	;
</style>

<c:import url="/WEB-INF/jsp/common/header.jsp" />


<h1 class="homePageName">National Park Service</h1>


<img class="homePageBG" src="img/wood-24490.png" />

<div id="treeImgs">
	<img class="tree1" src="img/tree1.png" /> <img class="tree1Copy"
		src="img/tree1.png" /> <img class="tree2" src="img/tree2.png" /> <img
		class="tree2Copy" src="img/tree2.png" /> <img class="tree3"
		src="img/tree3.png" /> <img class="tree3Copy" src="img/tree3.png" />
	<img class="tree4" src="img/tree4.png" /> <img class="tree4Copy"
		src="img/tree4.png" />
</div>


<div>
	<c:forEach var="park" items="${parks}">
		<div>
			<c:url var="parkDetailsPageHref" value="/park/details">
				<c:param name="parkCode">${park.parkCode}</c:param>
			</c:url>
			<a href="${parkDetailsPageHref}"> <img class="parkImages"
				src="img/parks/${fn:toLowerCase(park.parkCode)}.jpg" />
			</a>
			<div class="homeParkDetails">
				<h2 class="parkName">${park.parkName}</h2>
				<h3 class="parkState">- ${park.state} -</h3>
				<p class="parkDescription">${park.parkDescription}</p>
			</div>
		</div>
	</c:forEach>
</div>

<br></br>
<br></br>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />