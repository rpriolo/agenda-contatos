package br.com.rpriolo.controller;

import br.com.rpriolo.model.Contato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class CadastroDAO {

    private Connection conn;

    public CadastroDAO(Connection connection) {
        this.conn = connection;
    }

    public void salvar(String nome, String sobrenome, String dataNascimento) {
        String sql = "INSERT INTO contatos (nome, sobrenome, data_nascimento, ativo) VALUES (?, ?, ?, ?);";

        try (Connection conn = this.conn;
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false);

            preparedStatement.setString(1, nome);
            preparedStatement.setString(2, sobrenome);
            preparedStatement.setString(3, dataNascimento);
            preparedStatement.setBoolean(4, true);
            preparedStatement.execute();

            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }

    public Set<Contato> consultar() {
        HashSet<Contato> contatos = new HashSet<>();

        String sql = "SELECT * FROM contatos WHERE ativo = true";
        try (Connection conn = this.conn;
             PreparedStatement preparedStatement = conn.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            conn.setAutoCommit(false);

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String nome = resultSet.getString(2);
                String sobrenome = resultSet.getString(3);
                String dataNascimento = resultSet.getString(4);
                boolean estaAtivo = resultSet.getBoolean(5);

                Contato contato = new Contato(id, nome, sobrenome, dataNascimento, estaAtivo);
                contatos.add(contato);
            }

            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
        return contatos;
    }

    public Set<Contato> consultarPorId(int id) {
        HashSet<Contato> contatos = new HashSet<>();

        String sql = "SELECT * FROM contatos WHERE id = ?";
        try (Connection conn = this.conn;
             PreparedStatement preparedStatement = conn.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            conn.setAutoCommit(false);

            preparedStatement.setInt(1, id);

            while (resultSet.next()) {
                id = resultSet.getInt(1);
                String nome = resultSet.getString(2);
                String sobrenome = resultSet.getString(3);
                String dataNascimento = resultSet.getString(4);
                boolean estaAtivo = resultSet.getBoolean(5);

                Contato contato = new Contato(id, nome, sobrenome, dataNascimento, estaAtivo);
                contatos.add(contato);
            }

            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
        return contatos;
    }

    public Set<Contato> consultarPorSobrenome(String sobrenome) {
        HashSet<Contato> contatos = new HashSet<>();

        String sql = "SELECT * FROM contatos WHERE sobrenome = ?";
        try (Connection conn = this.conn;
             PreparedStatement preparedStatement = conn.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            conn.setAutoCommit(false);

            preparedStatement.setString(1, sobrenome);

            while (resultSet.next()) {
                int id = resultSet.getInt(1);
                String nome = resultSet.getString(2);
                sobrenome = resultSet.getString(3);
                String dataNascimento = resultSet.getString(4);
                boolean estaAtivo = resultSet.getBoolean(5);

                Contato contato = new Contato(id, nome, sobrenome, dataNascimento, estaAtivo);
                contatos.add(contato);
            }

            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
        return contatos;
    }

    public void editarNome(String novoNome, int id) {
        String sql = "UPDATE contatos SET nome = ? WHERE id = ?";
        try (Connection conn = this.conn;
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false);

            preparedStatement.setString(1, novoNome);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();

            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }

    public void editarSobrenome(String novoSobrenome, int id) {
        String sql = "UPDATE contatos SET sobrenome = ? WHERE id = ?";
        try (Connection conn = this.conn;
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false);

            preparedStatement.setString(1, novoSobrenome);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();

            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }

    public void editarDataNascimento(String novaDataNascimento, int id) {
        String sql = "UPDATE contatos SET data_nascimento = ? WHERE id = ?";
        try (Connection conn = this.conn;
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false);

            preparedStatement.setString(1, novaDataNascimento);
            preparedStatement.setInt(2, id);
            preparedStatement.execute();

            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }

    public void excluir(int id) {
        String sql = "DELETE FROM contatos WHERE id = ?";
        try (Connection conn = this.conn;
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false);

            preparedStatement.setInt(1, id);
            preparedStatement.execute();

            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }

    public void inativar(int id) {
        String sql = "UPDATE contatos SET ativo = false WHERE id = ?";
        try (Connection connection = this.conn;
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {

            conn.setAutoCommit(false);

            preparedStatement.setInt(1, id);
            preparedStatement.execute();

            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
    }



}