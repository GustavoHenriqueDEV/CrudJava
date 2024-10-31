package com.Crudexample.crud.dao;

import com.Crudexample.crud.model.Usuario;
//Objeto de Acesso a Dados

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class UsuarioDAO {

    private final Connection conexao;

    public UsuarioDAO(Connection conexao) {
        this.conexao = conexao;
    }

    String selectEmailAndNome = "SELECT nome, email, idade FROM USUARIO WHERE idusuario = ? AND login = ?";
    String sqlId = "SELECT 1 FROM USUARIO WHERE idusuario = ?";
    String selectUser = "SELECT nome, idade FROM USUARIO WHERE idusuario = ?";
    String insertIntoUser = "INSERT INTO USUARIO (idusuario, nome, login, senha, email, idade) VALUES (?, ?, ?, ?, ?, ?)";
    String deletUserWhereId = "DELETE FROM USUARIO WHERE idusuario = ?";
    String sqlAtt = "UPDATE USUARIO SET nome = ?, login = ?, senha = ?, email = ?, idade = ? WHERE idusuario = ?";

    public void pesquisarUser(String usuario) {
        try {
            PreparedStatement stmt = conexao.prepareStatement(selectEmailAndNome);
            stmt.setInt(1, 1);
            stmt.setString(2, usuario);
            ResultSet resultSet = stmt.executeQuery();
            System.out.println("Dados retornados com sucesso");
            //Isso aqui verifica se ainda existe dados na linha seguinte do result set
            while (resultSet.next()) {
                System.out.println("Nome: " + resultSet.getString("nome"));
                System.out.println("Email: " + resultSet.getString("email"));
                System.out.println("Idade: " + resultSet.getInt("idade"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao recuperar dados: " + e.getMessage());
        }
    }

    public void criarUsuario(Usuario user) throws SQLException {
        try {
            conexao.setAutoCommit(false);
            PreparedStatement stmtIdUser = conexao.prepareStatement(sqlId);
            stmtIdUser.setInt(1, user.getIdUsuario());
            ResultSet resultSet = stmtIdUser.executeQuery();
            //Aqui primeiro eu armazeno o id do usuario passado dentro do resultSet para verificar se ele já existe.
            if (resultSet.next()) {
                System.out.println("Usuário já existe");
                conexao.rollback();
            } else {
                PreparedStatement stmt = conexao.prepareStatement(insertIntoUser);
                stmt.setInt(1, user.getIdUsuario());
                stmt.setString(2, user.getNome());
                stmt.setString(3, user.getLogin());
                stmt.setString(4, user.getSenha());
                stmt.setString(5, user.getEmail());
                stmt.setInt(6, user.getIdade());
                stmt.executeUpdate();

                conexao.commit();
                System.out.println("Usuário inserido com sucesso");
            }

        } catch (SQLException e) {
            System.out.println("Erro ao inserir dados: " + e.getMessage());
            conexao.rollback();
        } finally {
            conexao.setAutoCommit(true);
        }
    }

    public void deletarUsuario(int idusuario) throws SQLException {
        try {
            conexao.setAutoCommit(false);

            PreparedStatement stmtSelect = conexao.prepareStatement(selectUser);
            stmtSelect.setInt(1, idusuario);
            ResultSet resultSet = stmtSelect.executeQuery();
            //Também verifico se esse usuario também existe dentro do result set e ainda uso essa informação para printar o nome dele

            if (resultSet.next()) {
                //Isso aqui pega o nome do user, extraindo o valor da coluna que eu passei ali
                String nome = resultSet.getString("nome");
                PreparedStatement stmtDelete = conexao.prepareStatement(deletUserWhereId);
                stmtDelete.setInt(1, idusuario);
                stmtDelete.executeUpdate();

                conexao.commit();
                System.out.println("Usuário: " + nome + "deletado com sucesso!");

            } else {
                System.out.println("Usuário não encontrado");
                conexao.rollback();
            }

        } catch (SQLException e) {
            System.out.println("Erro ao deletar usuário: " + e.getMessage());
            conexao.rollback();
        } finally {
            conexao.setAutoCommit(true);
        }
    }

    public void atualizarDadosUser(int idusuario, String nome, String login, String senha, String email, int idade) throws SQLException {
        try {
            conexao.setAutoCommit(false);

            PreparedStatement stmtId = conexao.prepareStatement(sqlId);
            stmtId.setInt(1, idusuario);
            ResultSet resultSet = stmtId.executeQuery();

            if (resultSet.next()) {
                PreparedStatement stmtAtt = conexao.prepareStatement(sqlAtt);
                stmtAtt.setString(1, nome);
                stmtAtt.setString(2, login);
                stmtAtt.setString(3, senha);
                stmtAtt.setString(4, email);
                stmtAtt.setInt(5, idade);
                stmtAtt.setInt(6, idusuario);

                stmtAtt.executeUpdate();
                conexao.commit();
                System.out.println("Dados do usuário " + nome + " atualizados com sucesso!");
            } else {
                System.out.println("Usuário não encontrado no banco de dados.");
            }
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar dados do usuário: " + e.getMessage());
            conexao.rollback();
        } finally {
            conexao.setAutoCommit(true);
        }
    }
}
