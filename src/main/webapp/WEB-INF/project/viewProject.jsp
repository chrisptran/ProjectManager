<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Project</title>
<link rel="stylesheet"
	href="/webjars/bootstrap/5.2.3/css/bootstrap.min.css" />
<script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
<script src="/webjars/bootstrap/5.2.3/js/bootstrap.min.js"></script>
</head>
<body>
<h3><a href="/dashboard">Dashboard</a></h3>
<h1>Project Details</h1>

<table>
	<tbody>
		<tr>
			<td>Project: <c:out value="${project.title}" /></td>
		</tr>
		<tr>
			<td>Description: <c:out value="${project.description}" /></td>
		</tr>
		<tr>
			<td>Due Date: <c:out value="${project.dueDate}" /></td>
		</tr>		
	</tbody>
</table>

<c:if test = "${project.lead.id == user_id}">
	<h2>
		<a href="/projects/delete/${project.id}">Delete Project</a>
	</h2>
</c:if>
</body>
</html>