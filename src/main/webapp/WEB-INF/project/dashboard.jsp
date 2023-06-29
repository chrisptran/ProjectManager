<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Dashboard</title>
<link rel="stylesheet"
	href="/webjars/bootstrap/5.2.3/css/bootstrap.min.css" />
<script src="/webjars/jquery/3.5.1/jquery.min.js"></script>
<script src="/webjars/bootstrap/5.2.3/js/bootstrap.min.js"></script>
</head>
<body>
<h1>Project Manager Dashboard</h1>
<p>Welcome, ${user.userName}</p>
<p><a href="/users/logout">Log Out</a></p>
<p><a href="/projects/new">Add Project</a></p>
<h4>All Projects</h4>
<table>
	<thead>
		<tr>
			<th>Project</th>
			<th>Team Lead</th>
			<th>Due Date</th>
			<th>Actions</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="project" items="${unassignedProjects}">
			<tr>
				<c:if test = "${project.lead.id != user.id}">
					<td>
						<a href="/projects/${project.id}">${project.title}</a>
					</td>
					<td>
						<c:out value="${project.lead.userName}" />
					</td>
					<td>
						<c:out value="${project.dueDate}" />
					</td>
					<td>
						<a href="/dashboard/join/${project.id}">Join Team</a>
					</td>
				</c:if>
			</tr>
		</c:forEach>
	</tbody>
</table>

<h4>Your Projects</h4>
<table>

<thead>
	<tr>
		<th>Project</th>
		<th>Team Lead</th>
		<th>Due Date</th>
		<th>Actions</th>
	</tr>
</thead>
<tbody>
<c:forEach var="project" items="${assignedProjects}">
<tr>
<td>
	<a href="/projects/${project.id}">${project.title}</a>
</td>

<td>
	<c:out value="${project.lead.userName}" />
</td>
	
<td>
	<c:out value="${project.dueDate}" />		
</td>
<c:if test = "${project.lead.id == user.id}">
<td>
	<a href="/projects/edit/${project.id}">Edit Project</a>
</td>
</c:if>
<c:if test = "${project.lead.id != user.id}">
	<td>
		<a href="/dashboard/leave/${project.id}">Leave Team</a>
	</td>
</c:if>

</tr>



</c:forEach>
</tbody>
</table>
</body>
</html>