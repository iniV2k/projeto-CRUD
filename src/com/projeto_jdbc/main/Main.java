package com.projeto_jdbc.main;


import com.projeto_jdbc.dao.UsuarioDAO;
import com.projeto_jdbc.model.Usuario;

public class Main {
	public static void main(String[] args) {

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		// Criar tabela (caso não exista)
		usuarioDAO.criarTabela();
		
		// Adicionar usuários
		usuarioDAO.inserirUsuario(new Usuario("Teste1", "Teste1@gmail.com"));
		
		// Listar usuarios
		usuarioDAO.listarUsuarios().forEach(System.out::println);
		
		// Procurar usuario
		// System.out.println(usuarioDAO.procurarUsuario(1));
		
		// Atualizar usuario
		// usuarioDAO.atualizarUsuario(new Usuario(1, "Vinicius", "Vinicius@gmail.com"));
		
		// Excluir usuario
		// usuarioDAO.excluirUsuario(1);
		
	}
}
