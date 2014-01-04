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
	<%
		String id = request.getParameter("id");
			Libro libro = new Libro(Integer.parseInt(id));
			libro = libro.buscarLibroPorId();
	%>

	<form action="EditarLibro.jsp" method="post">
		<fieldset>
			<legend>Insertar Libro</legend>
			<p>
				<input type="hidden" value="<%=libro.getId()%>" name="id" />
			</p>
			<p>
				<label for="isbn">ISBN: </label> <input type="text"
					value="<%=libro.getIsbn()%>" name="isbn" />
			</p>
			<p>
				<label for="titulo">Titulo: </label> <input type="text"
					value="<%=libro.getTitulo()%>" name="titulo" />
			</p>
			<p>
				<label for="categoria">Categoría: </label> <select name="categoria">
					<option value="<%=libro.getCategoria()%>"><%=libro.getCategoria()%></option>
					<%
					List<String> listCateoria = Libro.listarTodoCategorias();
					for (String categoria : listCateoria) {
					%>
					<option value="<%=categoria%>">
						<%=categoria%>
					</option>
					<% } %>
			</p>
			<p>
				<input type="submit" value="Guardar" />
			</p>

		</fieldset>
	</form>
</body>
</html>