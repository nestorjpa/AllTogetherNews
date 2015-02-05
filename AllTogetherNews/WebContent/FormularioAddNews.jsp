<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Alta de Noticias</title>
<link rel="stylesheet" type="text/css" href="estilo2.css">
</head>
<body>
<div class="form-box">
<h1>ALTA NOTICIAS</h1>
<form action="AddNewServlet" method="POST">
<input type="text" name="url" placeholder="Url"><br>
<input type="text" name="PatronUrl" placeholder="Patrón Url">
<input type="text" name="PatronNoticia" placeholder="Patrón Noticia">
<input type="text" name="PatronTitular" placeholder="Patrón Titular">
<input type="text" name="PatronSubtitular" placeholder="Patrón Subtitular">

<div class="combo">
<select name="Tematica">
  <option value="NA">--Temática--</option>
  <option value="2">Actualidad</option>
  <option value="1">Deportes</option>
  <option value="3">Economía</option>
</select>

 
 <input type="submit" name="add" class="button button-submit" value="AÑADIR">
 
</form>
</div>
</body>
</html>