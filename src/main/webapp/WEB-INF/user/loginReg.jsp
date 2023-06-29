<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Registration</title>
<link rel="stylesheet"
	href="/webjars/bootstrap/5.2.3/css/bootstrap.min.css" />
<script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
<script src="/webjars/bootstrap/5.2.3/js/bootstrap.min.js"></script>
</head>
<body>
	<h1>Welcome!</h1>
	<h3>Join our growing community.</h3>
	<div class="container">
		<div class="row">
			<div class="col">
				<h1>Register</h1>
				<form:form action="/users/process" method="post"
					modelAttribute="newUser">
					<div class="form-group">
						<form:label path="userName">User Name</form:label>
						<form:input path="userName" class="form-control" />
						<form:errors path="userName" class="text-danger" />
					</div>
					<div class="form-group">
						<form:label path="email">Email</form:label>
						<form:input path="email" class="form-control" />
						<form:errors path="email" class="text-danger" />
					</div>
					<div class="form-group">
						<form:label path="password">Password</form:label>
						<form:input path="password" type="password" class="form-control" />
						<form:errors path="password" class="text-danger" />
					</div>
					<div class="form-group">
						<form:label path="confirm">Confirm PW</form:label>
						<form:input path="confirm" type="password" class="form-control" />
						<form:errors path="confirm" class="text-danger" />
					</div>
					<input type="submit" value="Submit" class="btn btn-primary" />
				</form:form>
			</div>
			<div class="col">
				<h1>Log In</h1>
				<form:form action="/users/login" method="post"
					modelAttribute="loginUser">
					<div class="form-group">
						<form:label path="email">Email</form:label>
						<form:input path="email" class="form-control" />
						<form:errors path="email" class="text-danger" />
					</div>
					<div class="form-group">
						<form:label path="password">Password</form:label>
						<form:input path="password" class="form-control" type="password"/>
						<form:errors path="password" class="text-danger" />
					</div>
					<input type="submit" value="Submit" class="btn btn-primary" />
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>