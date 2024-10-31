package com.Crudexample.crud.service;

import com.Crudexample.crud.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class UsuarioService {

    private final Connection conexao;
    public UsuarioService(Connection conexao) {
        this.conexao = conexao;
    }
    public List<Usuario> buscarPorIdade(int idadeMaior) {

        String sqlSelectByIdade = "SELECT * FROM usuario WHERE idade > ?";
        List<Usuario> usuarios = new ArrayList<>();

        try {
            PreparedStatement stmt = conexao.prepareStatement(sqlSelectByIdade);
            stmt.setInt(1, idadeMaior);
            ResultSet resultSet = stmt.executeQuery();
            //Equanto exister usuarios com aquele idade o while percorre e adiciona dentro da lista
            while (resultSet.next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(resultSet.getInt("idusuario"));
                usuario.setNome(resultSet.getString("nome"));
                usuario.setLogin(resultSet.getString("login"));
                usuario.setSenha(resultSet.getString("senha"));
                usuario.setEmail(resultSet.getString("email"));
                usuario.setIdade(resultSet.getInt("idade"));
                usuarios.add(usuario);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return usuarios;
    }
}
