<%@page import="java.util.List"%>
<%@page import="com.heraud.clases.Libro"%>
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
<title>Insert title here</title>
</head>
<body>
	<a href="FormularioInsertarLibro.jsp">Agregar</a>

	<br />
	<select name="categoria">
		<option value="seleccionar">seleccionar</option>
		<%
			List<String> listCateoria = Libro.listarTodoCategorias();
			for (String categoria : listCateoria) {
		%>
		<option value="<%=categoria%>">
			<%=categoria%>
		</option> 
		<%
			}
		%>
	</select>
	<br />
	<br />

	<%
		List<Libro> listLibro = Libro.listarTodoLibros();
		for (Libro libro : listLibro) {
	%>
	<%=libro.getId()%>
	<%=libro.getIsbn()%>
	<%=libro.getTitulo()%>
	<%=libro.getCategoria()%>
	<a href="FormularioEditarLibro.jsp?id=<%=libro.getId()%>">Editar</a>
	<a href="EliminarLibro.jsp?id=<%=libro.getId()%>">Eliminar</a>
	<br />
	<%
		}
	%>
</body>
</html>