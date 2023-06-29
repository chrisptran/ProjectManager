<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>New Project</title>
<link rel="stylesheet"
	href="/webjars/bootstrap/5.2.3/css/bootstrap.min.css" />
<script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
<script src="/webjars/bootstrap/5.2.3/js/bootstrap.min.js"></script>
</head>
<body>
<h3><a href="/dashboard">Dashboard</a></h3>
<h1>Create a new Project</h1>
<form:form action="/projects/new" method="post" modelAttribute="project">
	<table>
		<thead>
			<tr>
				<td>
					<form:label path="title">Project Title</form:label>
					<form:errors path="title" class="text-danger" />
					<form:input path="title" class="input" />
				</td>
			</tr>
			<tr>
				<td>
					<form:label path="description">Project Description</form:label>
					<form:errors path="description" class="text-danger" />
					<form:textarea path="description" class="input" />
				</td>
			</tr>
			<tr>
				<td>
					<form:label path="dueDate">Due Date:</form:label>
					<form:errors path="dueDate" class="text-danger" />
					<form:input path="dueDate" type="date" />
				</td>
			</tr>
			<form:errors path="lead" class="error" />
			<form:input type="hidden" path="lead" value="${user_id}" />
			<tr>
				<td><a href="/dashboard">Cancel</a></td>
				<td><input type="submit" value="Submit" /></td>
			</tr>
		</thead>
	</table>
</form:form>
</body>
</html>