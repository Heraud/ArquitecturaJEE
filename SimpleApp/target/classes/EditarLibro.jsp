<%@page import="com.heraud.init.JDBCHelper"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Editar Libro</title>
</head>
<body>
	<%
		String id = request.getParameter("id");
		String isbn = request.getParameter("isbn");
		String titulo = request.getParameter("titulo");
		String categoria = request.getParameter("categoria");		
		String sql = "UPDATE Libro SET isbn='"+isbn+"',titulo='"+titulo+"',categoria='"+categoria+"' WHERE id='"+id+"'";
		JDBCHelper jdbc = new JDBCHelper();
		jdbc.modificarRegitro(sql);			
		response.sendRedirect("MostrarLibros.jsp");
	%>
</body>
</html>