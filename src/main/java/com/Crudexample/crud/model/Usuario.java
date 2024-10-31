package com.Crudexample.crud.model;

public class Usuario {


    //Aqui eu defino todas as entidades que correspondem ao banco de dados.

    public Usuario(int idusuario, String nome, String login, String senha, String email, int idade) {
        this.idusuario = idusuario;
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.email = email;
        this.idade = idade;
    }

        private int idade;
        private int idusuario;
        private String nome;
        private String login;
        private String senha;
        private String email;

    public Usuario() {

    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public int getIdusuario() {
        return idusuario;
    }

    public void setIdusuario(int idusuario) {
        this.idusuario = idusuario;
    }

    public int getIdUsuario() {
        return idusuario;
    }
    public void setIdUsuario(int idusuario) {
        this.idusuario = idusuario;
    }
    public String getNome() {
        return nome;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getSenha() {
        return senha;
    }
    public void setSenha(String senha) {
        this.senha = senha;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;

    }
}
