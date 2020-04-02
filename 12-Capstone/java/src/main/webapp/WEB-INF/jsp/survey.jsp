<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<style>
@import
	url('https://fonts.googleapis.com/css?family=Actor|Cabin+Sketch|Josefin+Sans|Noto+Sans+SC|Shadows+Into+Light+Two|Spinnaker&display=swap')
	;
</style>

<c:import url="/WEB-INF/jsp/common/header.jsp" />

<img class="homePageBG" src="img/wood-24490.png" />

<div id="treeImgs">
	<img class="tree1Survey" src="img/tree1.png" /> <img
		class="tree1CopySurvey" src="img/tree1.png" /> <img
		class="tree2Survey" src="img/tree2.png" /> <img
		class="tree2CopySurvey" src="img/tree2.png" /> <img
		class="tree3Survey" src="img/tree3.png" /> <img
		class="tree3CopySurvey" src="img/tree3.png" /> <img
		class="tree4Survey" src="img/tree4.png" /> <img
		class="tree4CopySurvey" src="img/tree4.png" />
</div>

<h1 class="homePageName">Park Survey</h1>

<c:url var="formAction" value="/survey" />
<form:form action="${formAction}" method="POST" modelAttribute="survey">


	<div id="surveyParkSelect">
		<form:select class="surveyParkList" path="parkCode">
			<c:forEach var="park" items="${parks}">
				<option value="${park.parkCode}">${park.parkName}</option>
			</c:forEach>
		</form:select>
	</div>

	<br></br>

	<div id="surveyStateSelect">
		<form:select class="surveyStateList" name="state" path="state">

			<option value="" selected="selected">Where ya from stranger?</option>
			<option value="AL">Alabama</option>
			<option value="AK">Alaska</option>
			<option value="AZ">Arizona</option>
			<option value="AR">Arkansas</option>
			<option value="CA">California</option>
			<option value="CO">Colorado</option>
			<option value="CT">Connecticut</option>
			<option value="DE">Delaware</option>
			<option value="FL">Florida</option>
			<option value="GA">Georgia</option>
			<option value="HI">Hawaii</option>
			<option value="ID">Idaho</option>
			<option value="IL">Illinois</option>
			<option value="IN">Indiana</option>
			<option value="IA">Iowa</option>
			<option value="KS">Kansas</option>
			<option value="KY">Kentucky</option>
			<option value="LA">Louisiana</option>
			<option value="ME">Maine</option>
			<option value="MD">Maryland</option>
			<option value="MA">Massachusetts</option>
			<option value="MI">Michigan</option>
			<option value="MN">Minnesota</option>
			<option value="MS">Mississippi</option>
			<option value="MO">Missouri</option>
			<option value="MT">Montana</option>
			<option value="NE">Nebraska</option>
			<option value="NV">Nevada</option>
			<option value="NH">New Hampshire</option>
			<option value="NJ">New Jersey</option>
			<option value="NM">New Mexico</option>
			<option value="NY">New York</option>
			<option value="NC">North Carolina</option>
			<option value="ND">North Dakota</option>
			<option value="OH">Ohio</option>
			<option value="OK">Oklahoma</option>
			<option value="OR">Oregon</option>
			<option value="PA">Pennsylvania</option>
			<option value="RI">Rhode Island</option>
			<option value="SC">South Carolina</option>
			<option value="SD">South Dakota</option>
			<option value="TN">Tennessee</option>
			<option value="TX">Texas</option>
			<option value="UT">Utah</option>
			<option value="VT">Vermont</option>
			<option value="VA">Virginia</option>
			<option value="WA">Washington</option>
			<option value="WV">West Virginia</option>
			<option value="WI">Wisconsin</option>
			<option value="WY">Wyoming</option>
		</form:select>
		<form:errors class="test" path="state" />
	</div>

	<br></br>

	<div id="surveyEmailInputMain" class="surveyEmailInput">
		<label for="email">Email</label>
		<form:input path="email" />
		<form:errors class="test" path="email" />
	</div>

	<br></br>

	<br></br>

	<div class="surveyActivityList">
		Please choose activity level: <br></br>

		<form:radiobutton name="activity" id="activity" path="activityLevel"
			value="Inactive" />
		Inactive
		<form:radiobutton name="activity" id="activity" path="activityLevel"
			value="Sedentary" />
		Sedentary
		<form:radiobutton name="activity" id="activity" path="activityLevel"
			value="Active" />
		Active
		<form:radiobutton name="activity" id="activity" path="activityLevel"
			value="Extremely Active" />
		Extremely Active <br />
		<form:errors class="test" path="activityLevel" />

	</div>

	<br></br>
	<br></br>

	<div>
		<input type="submit" class="surveySubmitButton" value="Submit" />
	</div>
</form:form>

<br></br>
<br></br>

<c:import url="/WEB-INF/jsp/common/footer.jsp" />