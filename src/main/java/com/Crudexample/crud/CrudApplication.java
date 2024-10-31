package com.Crudexample.crud;

import com.Crudexample.crud.config.DatabaseConfig;
import com.Crudexample.crud.dao.UsuarioDAO;
import com.Crudexample.crud.model.Usuario;
import com.Crudexample.crud.service.UsuarioService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@SpringBootApplication
public class CrudApplication {

	public static void main(String[] args) throws SQLException {
		SpringApplication.run(CrudApplication.class, args);
		Connection conexao = DatabaseConfig.getConnection();
		Usuario teste = new Usuario(100, "teste", "teste.moura", "123", "teste@gmail.com", 24);
//		Usuario teste1 = new Usuario(53, "teste", "teste.moura", "123", "teste@gmail.com", 24);

		UsuarioDAO user = new UsuarioDAO(conexao);
		user.pesquisarUser("joaosilva");
		user.criarUsuario(teste);
		user.deletarUsuario(14);
		user.atualizarDadosUser(53, "useratualizado", "atulizado.novo", "senha1234", "userAtuliazado@gmail.com", 29);

		UsuarioService usuarioService = new UsuarioService(conexao) ;

		List<Usuario> usuarios = usuarioService.buscarPorIdade(2);
		for(Usuario usuario : usuarios){
			System.out.println("ID: " + usuario.getIdUsuario());
			System.out.println("Nome: " + usuario.getNome());
			System.out.println("Login: " + usuario.getLogin());
			System.out.println("Email: " + usuario.getEmail());
			System.out.println("Idade: " + usuario.getIdade());
			System.out.println("------");
		}
	}
}
