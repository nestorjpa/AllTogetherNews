<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ page import="java.util.ArrayList,java.util.Iterator,objetosPrimarios.Noticia" %> 
   

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="es">
<head>
<meta charset="utf-8" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="styleCliente.css">
<title>All Together News</title>
</head>
<body>

<header id="headerPag">
   <h1>All Together News<small>Todos Informados</small></h1>
</header>
<nav>
<ul>
<%Iterator<String> iterator = ((ArrayList<String>)request.getAttribute("lNav")).iterator();
while(iterator.hasNext()){
	String tema = iterator.next();%>


  <li><a href="#home"><%=tema %></a></li>
<%} %>
</ul>
</nav>

<%
Iterator<Noticia> iteratorNoticias = ((ArrayList<Noticia>)request.getAttribute("lista")).iterator();
while(iteratorNoticias.hasNext()){
	Noticia noti = iteratorNoticias.next();%>

<section id="noticia">
 <h1><a href="<%=noti.getLink() %>"><%=noti.getTitular() %></a></h1>
 <h2><%=noti.getFecha_inserccion() %><% out.print(" | "); %><%=noti.getM().getHome() %>  </h2>
 <p><%=noti.getSubti() %> </p>

</section>	
<%
}
%>	

<footer>
<div>2015<span>AllTogetherNews.es</span></div> 
</footer>

</body>
</html>