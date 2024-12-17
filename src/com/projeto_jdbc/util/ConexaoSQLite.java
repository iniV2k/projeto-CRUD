package com.projeto_jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoSQLite {

	private static final String URL = "jdbc:sqlite:BancoDeDados.db";

	public static Connection getConnection() {
		try {
			return DriverManager.getConnection(URL);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Erro ao conectar com banco de dados!");
		}
	}
}