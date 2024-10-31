package com.Crudexample.crud.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

    public static Connection getConnection(){

        Connection conexao = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conexao = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/test", "root", "");
            System.out.println("Conexão estabelecida com sucesso!");

        } catch (ClassNotFoundException e) {
            System.out.println("Driver do banco de dados não localizado");
        } catch (
                SQLException e) {
            System.out.println("Erro ao conectar ao banco de dados: " + e.getMessage());
        }
        return conexao;
    }
}
