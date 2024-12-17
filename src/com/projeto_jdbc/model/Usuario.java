package com.projeto_jdbc.model;

public class Usuario {

	// Atributos
	private Integer id;
	private String nome;
	private String email;
	
	// Construtores
	public Usuario() {}
	
	public Usuario(String nome, String email) {
		this.nome = nome;
		this.email = email;
	}
	
	public Usuario(Integer id, String nome, String email) {
		this.id = id;
		this.nome = nome;
		this.email = email;
	}
	
	// Getters e setters
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email + "]";
	}
	
}
