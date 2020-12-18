<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>    
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration Page</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1" crossorigin="anonymous">
</head>
<body>
<div class="container-fluid">
	<div class="row">
		<div class="col">
		<h1>Register!</h1>
		    <c:forEach items="${errors}" var="error">
		    	<p style="color: red;"><c:out value="${error}"/></p>
		    </c:forEach>
			<form:form method="POST" action="/registration" modelAttribute="user">
				<div class="container">
			   		<div class="row">
						<div class="col">
							<p style="margin-bottom: 23px;"><form:label path="firstName">First Name:</form:label></p>
							<p style="margin-bottom: 23px;"><form:label path="lastName">Last Name:</form:label></p>
							<p style="margin-bottom: 23px;"><form:label path="email">Email:</form:label></p>
							<p style="margin-bottom: 23px;"><form:label path="location">Location:</form:label></p>
							<p style="margin-bottom: 23px;"><form:label path="state">State:</form:label></p>
							<p style="margin-bottom: 23px;"><form:label path="password">Password:</form:label></p>
							<p style="margin-bottom: 23px;"><form:label path="passwordConfirmation">Password Confirmation:</form:label></p>
						</div>
						<div class="col">
							<p><form:input path="firstName"/></p>
							<p><form:password path="lastName"/></p>
							<p><form:input path="email"/></p>
							<p><form:password path="location"/></p>
							<p><form:select path="state">
								<form:option value="">Select State</form:option>
								<form:option value="AL">Alabama</form:option>
								<form:option value="AK">Alaska</form:option>
								<form:option value="AZ">Arizona</form:option>
								<form:option value="AR">Arkansas</form:option>
								<form:option value="CA">California</form:option>
								<form:option value="CO">Colorado</form:option>
								<form:option value="CT">Connecticut</form:option>
								<form:option value="DE">Delaware</form:option>
								<form:option value="DC">District Of Columbia</form:option>
								<form:option value="FL">Florida</form:option>
								<form:option value="GA">Georgia</form:option>
								<form:option value="HI">Hawaii</form:option>
								<form:option value="ID">Idaho</form:option>
								<form:option value="IL">Illinois</form:option>
								<form:option value="IN">Indiana</form:option>
								<form:option value="IA">Iowa</form:option>
								<form:option value="KS">Kansas</form:option>
								<form:option value="KY">Kentucky</form:option>
								<form:option value="LA">Louisiana</form:option>
								<form:option value="ME">Maine</form:option>
								<form:option value="MD">Maryland</form:option>
								<form:option value="MA">Massachusetts</form:option>
								<form:option value="MI">Michigan</form:option>
								<form:option value="MN">Minnesota</form:option>
								<form:option value="MS">Mississippi</form:option>
								<form:option value="MO">Missouri</form:option>
								<form:option value="MT">Montana</form:option>
								<form:option value="NE">Nebraska</form:option>
								<form:option value="NV">Nevada</form:option>
								<form:option value="NH">New Hampshire</form:option>
								<form:option value="NJ">New Jersey</form:option>
								<form:option value="NM">New Mexico</form:option>
								<form:option value="NY">New York</form:option>
								<form:option value="NC">North Carolina</form:option>
								<form:option value="ND">North Dakota</form:option>
								<form:option value="OH">Ohio</form:option>
								<form:option value="OK">Oklahoma</form:option>
								<form:option value="OR">Oregon</form:option>
								<form:option value="PA">Pennsylvania</form:option>
								<form:option value="RI">Rhode Island</form:option>
								<form:option value="SC">South Carolina</form:option>
								<form:option value="SD">South Dakota</form:option>
								<form:option value="TN">Tennessee</form:option>
								<form:option value="TX">Texas</form:option>
								<form:option value="UT">Utah</form:option>
								<form:option value="VT">Vermont</form:option>
								<form:option value="VA">Virginia</form:option>
								<form:option value="WA">Washington</form:option>
								<form:option value="WV">West Virginia</form:option>
								<form:option value="WI">Wisconsin</form:option>
								<form:option value="WY">Wyoming</form:option>
							</form:select></p>
							<p><form:password path="password"/></p>
							<p><form:password path="passwordConfirmation"/></p>
							<input type="submit" value="Register!"/>
						</div>
					</div>
				</div>
			</form:form>
		</div>
	
		<div class="col">
		    <h1>Login</h1>
		    <p style="color: red;"><c:out value="${error}" /></p>
		    <form method="post" action="/login">
		    	<div class="container">
			   		<div class="row">
						<div class="col">
							<p style="margin-bottom: 23px;"><label for="email">Email: </label></p>
							<p><label for="password">Password: </label></p>
						</div>
						<div class="col">
							<p><input type="text" id="email" name="email"/></p>
							<p><input type="password" id="password" name="password"/></p>
							<p><input type="submit" value="Login!"/></p>
		        		</div>
					</div>
				</div>
		    </form> 
		</div>
    </div>
</div>
</body>
</html>