<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login Administrador</title>
<link rel="stylesheet" type="text/css" href="estilo2.css">
</head>
<body>
<div class="login-box">
<h1>LOG-IN</h1>


<form action="loginServlet" method="POST">
 <input type="text" name="user" placeholder="Username">
 <input type="password" name="pass" placeholder="Password">
 <input type="submit" name="login" class="button button-submit" value="Login">
</form>
</div>
</body>
</html>