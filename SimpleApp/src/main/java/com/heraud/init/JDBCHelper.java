package com.heraud.init;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class JDBCHelper<T> {
	public static Connection getConexion() {
		Connection conexion = null;
		Properties properties = new Properties();
		try {
			// properties.load(JDBCHelper.class.getClassLoader().getResourceAsStream("config.properties"));
			properties.load(Thread.currentThread().getContextClassLoader()
					.getResourceAsStream("config.properties"));
			String driver = properties.getProperty("key.driver");
			String url = properties.getProperty("key.url");
			String user = properties.getProperty("key.user");
			String password = properties.getProperty("key.password");
			Class.forName(driver);
			conexion = DriverManager.getConnection(url, user, password);
		} catch (Exception e) {
			System.out.println("Error en getConexion: " + e.getMessage());
		}
		return conexion;
	}

	public List<T> seleccionarRegistros(String sql, Class clase) {
		Connection conexion = getConexion();
		Statement sentencia = null;
		ResultSet filas = null;
		List<T> listaDeObjetos = new ArrayList<T>();
		try {
			sentencia = conexion.createStatement();
			filas = sentencia.executeQuery(sql);
			while (filas.next()) {
				T objeto = (T) Class.forName(clase.getName()).newInstance();
				Method[] metodos = objeto.getClass().getDeclaredMethods();
				for (int i = 0; i < metodos.length; i++) {
					if (metodos[i].getName().startsWith("set")) {
						
						if (metodos[i].getParameterTypes()[0].getSimpleName()
								.equals("Integer")) {
							metodos[i].invoke(objeto, Integer.parseInt(filas
									.getString(metodos[i].getName()
											.substring(3))));
						} else {
							metodos[i].invoke(objeto, filas
									.getString(metodos[i].getName()
											.substring(3)));
						}
					}
					if (objeto.getClass().getName().equals("java.lang.String")) {
						objeto = (T) filas.getString(1);
					}
				}
				listaDeObjetos.add(objeto);
			}
		} catch (Exception e) {
			System.out.println("Error de SQL: " + e.getMessage());
		} finally {
			if (sentencia != null) {
				try {
					sentencia.close();
				} catch (SQLException e2) {

				}
			}
			if (conexion != null) {
				try {
					conexion.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
		}
		return listaDeObjetos;
	}

	public int modificarRegitro(String sql) {
		Connection conexion = getConexion();
		Statement sentencia = null;
		int filasAfectadas = 0;
		try {
			sentencia = conexion.createStatement();
			filasAfectadas = sentencia.executeUpdate(sql);
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

}
