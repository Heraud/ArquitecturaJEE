package com.heraud.clases;

import java.sql.ResultSet;

import com.heraud.init.JDBCHelper;

public class Libro {
	public static ResultSet listarTodoLibros() {
		JDBCHelper helper = new JDBCHelper();
		ResultSet rs = helper
				.seleccionarRegistros("Select id, isbn, titulo, categoria from libro");
		return rs;
	}

	public static ResultSet listarTodoCategorias() {
		JDBCHelper helper = new JDBCHelper();
		ResultSet rs = helper
				.seleccionarRegistros("select distinct(categoria) from Libro");
		return rs;
	}

	public static void insertar(String isbn, String titulo, String categoria) {
		JDBCHelper helper = new JDBCHelper();
		String sql = "INSERT INTO LIBRO (isbn, titulo, categoria) VALUES('"
				+ isbn + "','" + titulo + "','" + categoria + "')";
		helper.modificarRegitro(sql);
	}

	public static void editar(int id, String isbn, String titulo,
			String categoria) {
		JDBCHelper helper = new JDBCHelper();
		String sql = "UPDATE Libro SET isbn='" + isbn + "',titulo='" + titulo
				+ "',categoria='" + categoria + "' WHERE id='" + id + "'";
		helper.modificarRegitro(sql);
	}

	public static ResultSet buscarLibroPorId(int id) {
		JDBCHelper helper = new JDBCHelper();
		ResultSet rs = helper
				.seleccionarRegistros("SELECT * FROM libro WHERE id=" + id);
		return rs;
	}

	public static void eliminar(int id) {
		JDBCHelper helper = new JDBCHelper();
		String sql = "DELETE FROM Libro WHERE id='" + id + "'";
		helper.modificarRegitro(sql);
	}

}
