package com.projeto_jdbc.main;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
	public static void main(String[] args) {

		String url = "jdbc:sqlite:BancoDeDados.db";

		try (Connection conn = DriverManager.getConnection(url)) {
			if (conn != null) {
				System.out.println("Conexão estabelecida!");

				String sqlCreateTable = "CREATE TABLE IF NOT EXISTS usuarios ("
						+ "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "nome TEXT NOT NULL, "
						+ "email TEXT NO NULL UNIQUE" + ");";
				try (Statement stmt = conn.createStatement()) {
					stmt.execute(sqlCreateTable);
					System.out.println("Tabela 'usuarios' criada ou já existe.");
				}
				
				
				String sqlInsert = "INSERT INTO usuarios (nome, email) VALUES ('Vinicius Ribeiro', 'Vinicius@jdbc.com')";
				try (Statement stmt = conn.createStatement()) {
					stmt.executeUpdate(sqlInsert);
					System.out.println("Usuário inserido com sucesso.");
				}

				String sqlSelect = "SELECT * FROM usuarios";
				try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sqlSelect)) {
					System.out.println("Usuários cadastrados:");
					while (rs.next()) {
						System.out.println("ID: " + rs.getInt("id") + " Nome: " + rs.getString("nome") + ", Email: "
								+ rs.getString("email"));
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("Erro ao conectar com SQLite: " + e.getMessage());
		}

	}
}
