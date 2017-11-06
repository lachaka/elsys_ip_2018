<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>User</title>
</head>
<body>
<table>
    <tr>
        <th>ID</th>
        <th>Username</th>
        <th>Password</th>
        <th>Email</th>
    </tr>

    <tr>
        <td>${user.id}</td>
        <td>${user.name}</td>
        <td>${user.password}</td>
        <td>${user.email}</td>
    </tr>
    <form action="user" method="post">
        Name: <input type="text" name="name"/><br />
        New Name: <input type="text" name="newName"/><br />
        New Password: <input type="password" name="password"/><br />
        New Email: <input type="email" name="email"/><br />
        <input type="submit" name="update" value="Update"/>
    </form>

    <br /><br />
    <form action="logout" method="post">
        <input type="submit" value="Logout">
    </form>
</table>
</body>
</html>