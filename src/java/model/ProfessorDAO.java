package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidade.Professor;

public class ProfessorDAO implements Dao<Professor> {

    @Override
    public Professor get(int id) {
        Conexao conexao = new Conexao();
        Professor professor = new Professor();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM Professores WHERE id = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();

            if (resultado != null) {
                while (resultado.next()) {
                    professor.setId(Integer.parseInt(resultado.getString("id")));
                    professor.setNome(resultado.getString("nome"));
                    professor.setEmail(resultado.getString("email"));
                    professor.setCpf(resultado.getString("cpf"));
                    professor.setSenha(resultado.getString("senha"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (get professor) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return professor;
    }
    public Professor getByCPF(String cpf) throws Exception {
        Conexao conexao = new Conexao();
        try {
            Professor professor = new Professor();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM Professores WHERE CPF = ? LIMIT 1 ");
            sql.setString(1, cpf);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    professor.setId(Integer.parseInt(resultado.getString("ID")));
                    professor.setNome(resultado.getString("NOME"));
                    professor.setEmail(resultado.getString("EMAIL"));
                    professor.setCpf(resultado.getString("CPF"));
                    professor.setSenha(resultado.getString("SENHA"));
                }
            }
            return professor;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public ArrayList<Professor> getAll() {
        ArrayList<Professor> professores = new ArrayList<Professor>();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM Professores ORDER BY nome";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Professor Professor = new Professor(resultado.getInt("id"), resultado.getString("nome"),
                            resultado.getString("email"), resultado.getString("cpf"), resultado.getString("senha"));
                    professores.add(Professor);
                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (GetAll - professores) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return professores;
    }

    @Override
    public void insert(Professor Professor) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(
                    "INSERT INTO Professores (nome, email, cpf, senha)"
                            + " VALUES (?,?,?,?)");
            sql.setString(1, Professor.getNome());
            sql.setString(2, Professor.getEmail());
            sql.setString(3, Professor.getCpf());
            sql.setString(4, Professor.getSenha());

            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void update(Professor Professor) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(
                    "UPDATE Professores SET nome = ?, email = ?, cpf = ?, senha = ?  WHERE ID = ? ");
            sql.setString(1, Professor.getNome());
            sql.setString(2, Professor.getEmail());
            sql.setString(3, Professor.getCpf());
            sql.setString(4, Professor.getSenha());
            sql.setInt(5, Professor.getId());

            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar professor) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void delete(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM Professores WHERE id = ? ");
            sql.setInt(1, id);
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir professor) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public Professor Logar(Professor Professor) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao()
                .prepareStatement("SELECT * FROM Professores WHERE cpf=? and senha =? LIMIT 1");
            sql.setString(1, Professor.getCpf());
            sql.setString(2, Professor.getSenha());
            ResultSet resultado = sql.executeQuery();
            Professor ProfessorObtido = new Professor();
            if (resultado != null) {
                while (resultado.next()) {
                    ProfessorObtido.setId(Integer.parseInt(resultado.getString("id")));
                    ProfessorObtido.setNome(resultado.getString("nome"));
                    ProfessorObtido.setEmail(resultado.getString("email"));
                    ProfessorObtido.setCpf(resultado.getString("cpf"));
                    ProfessorObtido.setSenha(resultado.getString("senha"));
                }
            }
            return ProfessorObtido;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

}
