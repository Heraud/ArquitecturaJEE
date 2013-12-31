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
	
	<br/>
	<select name="categoria">
		<option value="seleccionar">seleccionar</option>
		<%
			ResultSet rs = null;
			try {				
				rs = Libro.listarTodoCategorias();
				while (rs.next()) {
		%>
		<option value="<%=rs.getString("categoria")%>">
			<%=rs.getString("categoria")%>
		</option>
		<% } %>
	</select>
	<br />
	<br />
	
	<%
			rs = Libro.listarTodoLibros();
			while (rs.next()) {
	%>
	
	<%=rs.getString("id")%>
	<%=rs.getString("isbn")%>
	<%=rs.getString("titulo")%>
	<%=rs.getString("categoria")%>
	
	<a href="FormularioEditarLibro.jsp?id=<%=rs.getString("id")%>">Editar</a>
	<a href="EliminarLibro.jsp?id=<%=rs.getString("id")%>">Eliminar</a>
	<br />
	<%
		}
		}catch (SQLException e) {
			System.out.println("Error accediendo a las BDs: "
					+ e.getMessage());
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException e) {
					System.out.println("Error cerrando el ResultSet: "
							+ e.getMessage());
				}

			}
		}
	%>
</body>
</html>