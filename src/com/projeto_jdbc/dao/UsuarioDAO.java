package com.projeto_jdbc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.projeto_jdbc.model.Usuario;
import com.projeto_jdbc.util.ConexaoSQLite;

public class UsuarioDAO {

	// Atributos
	private Connection conexao;

	// Construtores
	public UsuarioDAO() {
		this.conexao = ConexaoSQLite.getConnection();
	}

	// Métodos
	public void criarTabela() {
		String sqlCreateTable = "CREATE TABLE IF NOT EXISTS usuarios (" 
				+ "id INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "nome TEXT NOT NULL, " 
				+ "email TEXT NO NULL UNIQUE" 
				+ ");";

		try (Statement stmt = conexao.createStatement()) {
			stmt.execute(sqlCreateTable);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void inserirUsuario(Usuario usuario) {
		List<Usuario> usuarios = listarUsuarios();
		
		// Verificação
		boolean emailEncontrado = usuarios.stream()
				.anyMatch(u -> u.getEmail().equals(usuario.getEmail()));
		if (emailEncontrado) {
			System.out.println("E-mail ja cadastrado no sistema.");
			return;
		}
		
		String sqlInsert = "INSERT INTO usuarios (nome, email) VALUES (?, ?)";
		
		try (PreparedStatement pstmt = conexao.prepareStatement(sqlInsert)) {
			pstmt.setString(1, usuario.getNome());
			pstmt.setString(2, usuario.getEmail());
			pstmt.executeUpdate();
			System.out.println("Usuario '" + usuario.getNome() + "' adicionado com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<Usuario> listarUsuarios() {
		List<Usuario> usuarios = new ArrayList<>();
		
		String sqlListar = "SELECT * FROM usuarios;";
		
		try (Statement pstmt = conexao.createStatement();
			 ResultSet rs = pstmt.executeQuery(sqlListar)) {
			while (rs.next()) {
				Usuario usuario = new Usuario(rs.getInt("id"), rs.getString("nome"), rs.getString("email"));
				usuarios.add(usuario);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuarios;
	}
	
	public Usuario procurarUsuario(int id) {
		List<Usuario> usuarios = listarUsuarios();
		
		// verificação
		boolean idEncontrado = usuarios.stream()
				.anyMatch(usuario -> usuario.getId() == id);
		if (!idEncontrado) {
			System.out.println("Usuario com ID [" + id + "] nao encontrado");
			return null;
		}
		
		String sqlProcurarUsuario = "SELECT * FROM usuarios WHERE id = ?";
		
		try (PreparedStatement pstmt = conexao.prepareStatement(sqlProcurarUsuario)) {
			for (Usuario usuario : usuarios) {
				if (usuario.getId() == id) {
					return usuario;
				}
			}
		} catch (SQLException e) {
			e.getStackTrace();
		}
		return null;
	}

	public void atualizarUsuario(Usuario usuario) {
		// Verificação
		List<Usuario> usuarios = listarUsuarios();
		boolean idEncontrado = usuarios.stream()
				.anyMatch(u -> u.getId() == usuario.getId());
		
		if (!idEncontrado) {
			System.out.println("Usuario com ID [" + usuario.getId() + "] nao encontrado");
			return;
		}
		
		String sqlAtualizar = "UPDATE usuarios SET nome = ?, email = ? WHERE id = ?";
		
		try (PreparedStatement pstmt = conexao.prepareStatement(sqlAtualizar)) {
			pstmt.setString(1, usuario.getNome());
			pstmt.setString(2, usuario.getEmail());
			pstmt.setInt(3, usuario.getId());
			pstmt.executeUpdate();
			System.out.println("Usuario com ID [" + usuario.getId() + "] atualizado com sucesso!" );
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void excluirUsuario(int id) {
		List<Usuario> usuarios = listarUsuarios();
		
		// Veficação
		boolean idEncontrado = usuarios.stream()
				.anyMatch(u -> u.getId() == id);
		if (!idEncontrado) {
			System.out.println("Usuario com ID [" + id + "] nao encontrado");
			return;
		}
		
		String sqlExcluir = "DELETE FROM usuarios where ID = ?";
		
		try (PreparedStatement pstmt = conexao.prepareStatement(sqlExcluir)) {
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			System.out.println("Usuario com ID [" + id + "] excluido com sucesso!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}	
}
