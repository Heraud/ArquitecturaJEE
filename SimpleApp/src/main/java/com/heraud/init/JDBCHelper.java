package com.heraud.init;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCHelper {
	private static final String driver = "com.mysql.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/librodb2";
	private static final String usuario = "root";
	private static final String clave = "hesleither";

	public int modificarRegitro(String sql) {
		Connection conexion = null;
		Statement sentencia = null;
		
		int filasAfectadas = 0;
		
		try {
			Class.forName(driver);
			conexion = DriverManager.getConnection(url, usuario, clave);
			sentencia = conexion.createStatement();
			filasAfectadas = sentencia.executeUpdate(sql);
		} catch (ClassNotFoundException e) {
			System.out.println("Error driver" + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Error de SQL" + e.getMessage());
		} finally {
			if (sentencia != null) {
				try {
					sentencia.close();
				} catch (SQLException e) {
				}
			}
			if (conexion != null) {
				try {
					conexion.close();
				} catch (SQLException e) {
				}
			}
		}
		return filasAfectadas;
	}

	public ResultSet seleccionarRegistros(String sql) {
		Connection conexion = null;
		Statement sentencia = null;
		ResultSet filas = null;
		try {
			Class.forName(driver);
			conexion = DriverManager.getConnection(url, usuario, clave);
			sentencia = conexion.createStatement();
			filas = sentencia.executeQuery(sql);
		} catch (ClassNotFoundException e) {
			System.out.println("Error Driver" + e.getMessage());
		} catch (SQLException e) {
			System.out.println("Error de SQL " + e.getMessage());
		}
		return filas;
	}

}
