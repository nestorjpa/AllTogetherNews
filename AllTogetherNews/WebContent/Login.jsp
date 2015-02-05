<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
<head>
<meta charset="utf-8" />
<title>Login Administrador</title>
<link rel="stylesheet" href="style.css"/>
</head>
<body>

    <header>
	   <h1>All Together News</h1>
	 </header>
	   
	<aside>
		<p>illustration</p>
	</aside>
       		
	<section  class="login-box">
		<div id="log-box">
		<h1>LOG-IN</h1>
			<form action="loginServlet" method="POST">
				<input type="text" name="user" placeholder="Username">
				<input type="password" name="pass" placeholder="Password"> 
				<input type="submit" name="login" class="button button-submit"value="Login">
			</form>
		</div>
	</section>
    <footer>
        <small>
                Copyright &copy; 2015<br/>
                Actualizado en: 10 Febrero 2015           
         </small>        
     </footer>

</body>
</html>
