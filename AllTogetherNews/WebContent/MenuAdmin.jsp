<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.ArrayList,java.util.Iterator" %> 


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
<head>
<meta charset="utf-8" />
<title>Menu Administrador</title>
<link rel="stylesheet" href="style.css"/>
 <script src="jquery-1.11.2.min.js" type="text/javascript"></script>   
</head>
<body>
<div class="header">
<header>
   <h1>All Together News<small>Todos Informados</small></h1>

</header>

<div id="franja">
 <nav>
        <ul >

		<li ><a href="#" class="addNew">INSERTAR NOTICIAS</a></li>
		<li ><a href="#" class="addTema">INSERTAR TEMÁTICA</a></li>
		<li ><a href="#" id="modifNew">MODIFICAR NOTICIA</a></li>

	</ul>
</nav>

</div>
</div>


<script>

$(document).ready(function(){
	if($( "nav" ).on("click",".addNew", function() {    
		 $("#form-tema").css("display", "none");   
		$("#form-box").css("display", "block");
	      
	    }));
});

$(document).ready(function(){
if($( "nav" ).on("click",".addTema", function() {    
    $("#form-box").css("display", "none");
    $("#form-tema").css("display", "block");
 }));
});


 
</script>
	
<section>
<div id="form-box">

<h1>ALTA NOTICIAS</h1>
<form action="AddNewServlet" method="POST" >
<input type="text" name="PatronUrl" placeholder="Patrón Url">
<input type="text" name="raiz" placeholder="Patrón raíz"><br>
<input type="text" name="PatronTitular" placeholder="Patrón Titular">
<input type="text" name="PatronLink" placeholder="Patrón Link">
<input type="text" name="PatronSubtitular" placeholder="Patrón Subtitular">
<input type="text" name="PatronExcep" placeholder="Patrón Excepcion">

<div class="combo">
<select name="Tematica">

  <option value="NA">--Temática--</option>
  <%Iterator<String> iteratorTemas = ((ArrayList<String>)request.getAttribute("lista")).iterator();
	while(iteratorTemas.hasNext()){
		String tema = iteratorTemas.next();%>
		<option ><%=tema%></option>
<% } %>
  
  

</div>
 
<input type="submit" name="add" class="button-submit" value="AÑADIR" >

<input type="submit" name="prueba" class="button-submit" value="PROBAR" >


<div id="probar">
 <%
 String prueba=null;
 if (request.getAttribute("listaPrueba")!=null){
 Iterator<String> ite = ((ArrayList<String>)request.getAttribute("listaPrueba")).iterator();
	while(ite.hasNext()){
		prueba = ite.next();
	
		
	out.println(prueba);
}} %>
</div>

</form>	
</div>

</section>

<section>
<div id="form-tema">
<h1>Temáticas Almacenadas:</h1>
<form action="InsertarTemaServlet" method="POST">
<ul class="lista">

		
   <%Iterator<String> iterator = ((ArrayList<String>)request.getAttribute("lista")).iterator();
	while(iterator.hasNext()){
	String tema = iterator.next();%>
	<li ><p> <%=tema%></p></li>
<% } %>
	

</ul>
<p>Nueva temática:</p>
<input type="text" name="tematica" placeholder="Url"><br>
<input type="submit" name="add" class="button button-submit" value="AÑADIR" >

</form>
</div>
</section>
<footer>
  <small>
    Copyright &copy; 2015<br/>
    Actualizado en: 2015           
  </small>        
</footer>
</body>
</html>
