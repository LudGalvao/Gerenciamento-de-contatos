package dao;

import modelo.Contato;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContatoDAO {
    private Connection connection;

    public ContatoDAO() {
        try {
            this.connection = Factory.getConnection();
            System.out.println("Conexão criada com sucesso.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void inserirContato(Contato contato) {
        String sql = "INSERT INTO CONTATOS (nome, email, endereco) VALUES (?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, contato.getNome());
            statement.setString(2, contato.getEmail());
            statement.setString(3, contato.getEndereco());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Contato> listarContatos() {
        List<Contato> contatos = new ArrayList<>();
        String sql = "SELECT * FROM CONTATOS";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Contato contato = new Contato();
                contato.setId(resultSet.getInt("id"));
                contato.setNome(resultSet.getString("nome"));
                contato.setEmail(resultSet.getString("email"));
                contato.setEndereco(resultSet.getString("endereco"));
                contatos.add(contato);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Trate adequadamente
        }
        return contatos;
    }

    public List<Contato> listarContatosPorLetraInicial(char letra) {
        List<Contato> contatos = new ArrayList<>();
        String sql = "SELECT * FROM CONTATOS WHERE nome LIKE ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, letra + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Contato contato = new Contato();
                contato.setNome(resultSet.getString("nome"));
                contato.setEmail(resultSet.getString("email"));
                contato.setEndereco(resultSet.getString("endereco"));
                contatos.add(contato);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Trate adequadamente
        }
        return contatos;
    }

    public Contato buscarContatoPorId(int id) {
        String sql = "SELECT * FROM CONTATOS WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Contato contato = new Contato();
                contato.setNome(resultSet.getString("nome"));
                contato.setEmail(resultSet.getString("email"));
                contato.setEndereco(resultSet.getString("endereco"));
                return contato;
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Trate adequadamente
        }
        return null;
    }
    public void removerContatoPorId(int id) {
        String sql = "DELETE FROM CONTATOS WHERE id = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}