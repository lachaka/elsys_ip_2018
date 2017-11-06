<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Admin</title>
</head>
<body>
	<table>
		<tr>
			<th>ID</th>
			<th>Username</th>
			<th>Password</th>
			<th>Email</th>
		</tr>

		<form action="admin" method="post">
			Name: <input type="text" name="name"><br />
			Password: <input type="password" name="password"><br />
			Email: <input type="text" name="email"><br />
			<input type="submit" value="Create">
		</form>
		<br />
		<br />

		<form action="admin" method="get">
			Name: <input type="text" name="name"><br />
			<input type="submit" value="Search">
		</form>
		<br />
		<br />
		<br />
		<form action="logout" method="post">
			<input type="submit" value="Logout">
		</form>
		<c:forEach items="${users}" var="user">
			<tr>
				<td>${user.id}</td>
				<td>${user.name}</td>
				<td>${user.password}</td>
				<td>${user.email}</td>
				<td><a href="user?name=${user.name}">Go to user page</a></td>
				<td>
					<form action="delete" method="post">
						<input type="submit" name="delete" value="Delete">
						<input type="hidden" name="name" value=${user.name}>
					</form>
				</td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>