<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alta de Noticias</title>
<link rel="stylesheet" type="text/css" href="style.css">
</head>
<body>

<header>
   <h1>All Together News</h1>
</header>
	   
<aside>
	<p>illustration</p>
</aside>

<section class="form-box">
<div id="form-box">
<h1>ALTA NOTICIAS</h1>
<form action="AddNewServlet" method="POST">
<input type="text" name="url" placeholder="Url"><br>
<input type="text" name="PatronUrl" placeholder="Patr�n Url">
<input type="text" name="PatronNoticia" placeholder="Patr�n Noticia">
<input type="text" name="PatronTitular" placeholder="Patr�n Titular">
<input type="text" name="PatronSubtitular" placeholder="Patr�n Subtitular">

<div class="combo">
<select name="Tematica">
  <option value="NA">--Tem�tica--</option>
  <option value="2">Actualidad</option>
  <option value="1">Deportes</option>
  <option value="3">Econom�a</option>
</select>
</div>
 
 <input type="submit" name="add" class="button button-submit" value="A�ADIR">
 
</form>
</div>
</section>
<footer>
<small> Copyright &copy; 2015<br/>
        Actualizado en: 10 Febrero 2015           
</small>        
</footer>
</body>
</html>