package com.heraud.clases;

import java.util.ArrayList;
import java.util.List;

import com.heraud.init.JDBCHelper;

public class Libro {
	private Integer id;
	private String isbn;
	private String titulo;
	private String categoria;

	public Libro() {
	}

	public Libro(Integer id) {
		super();
		this.id = id;
	}

	public Libro(String isbn, String titulo, String categoria) {
		super();
		this.isbn = isbn;
		this.titulo = titulo;
		this.categoria = categoria;
	}

	public Libro(Integer id, String isbn, String titulo, String categoria) {
		super();
		this.id = id;
		this.isbn = isbn;
		this.titulo = titulo;
		this.categoria = categoria;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	// Métodos==============
	public static List<Libro> listarTodoLibros() {
		JDBCHelper<Libro> helper = new JDBCHelper<Libro>();
		List<Libro> list = new ArrayList<Libro>();
		String sql = "Select id, isbn, titulo, categoria from libro";
		list = helper.seleccionarRegistros(sql, Libro.class);
		return list;
	}

	public static List<String> listarTodoCategorias() {
		JDBCHelper<String> helper = new JDBCHelper<String>();
		List<String> list = new ArrayList<String>();
		list = helper.seleccionarRegistros(
				"select distinct(categoria) from Libro", String.class);
		return list;
	}

	public void insertar() {
		JDBCHelper helper = new JDBCHelper();
		String sql = "INSERT INTO LIBRO (isbn, titulo, categoria) VALUES('"
				+ this.isbn + "','" + this.titulo + "','" + this.categoria
				+ "')";
		helper.modificarRegitro(sql);
	}

	public void editar() {
		JDBCHelper helper = new JDBCHelper();
		String sql = "UPDATE Libro SET isbn='" + this.isbn + "',titulo='"
				+ this.titulo + "',categoria='" + this.categoria
				+ "' WHERE id='" + this.id + "'";
		helper.modificarRegitro(sql);
	}

	public Libro buscarLibroPorId() {
		JDBCHelper<Libro> helper = new JDBCHelper<Libro>();
		List<Libro> list = new ArrayList<Libro>();
		list = helper.seleccionarRegistros("SELECT * FROM libro WHERE id="
				+ this.id, Libro.class);
		return list.get(0);
	}

	public void eliminar() {
		JDBCHelper helper = new JDBCHelper();
		String sql = "DELETE FROM Libro WHERE id='" + this.id + "'";
		helper.modificarRegitro(sql);
	}

}
