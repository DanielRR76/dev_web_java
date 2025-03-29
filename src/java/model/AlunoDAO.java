package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidade.Aluno;

public class AlunoDAO implements Dao<Aluno> {

    @Override
    public Aluno get(int id) {
        Conexao conexao = new Conexao();
        Aluno aluno = new Aluno();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM Alunos WHERE id = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();

            if (resultado != null) {
                while (resultado.next()) {
                    aluno.setId(Integer.parseInt(resultado.getString("id")));
                    aluno.setNome(resultado.getString("nome"));
                    aluno.setEmail(resultado.getString("email"));
                    aluno.setCelular(resultado.getString("celular"));
                    aluno.setCpf(resultado.getString("cpf"));
                    aluno.setSenha(resultado.getString("senha"));
                    aluno.setEndereco(resultado.getString("endereco"));
                    aluno.setCidade(resultado.getString("cidade"));
                    aluno.setBairro(resultado.getString("bairro"));
                    aluno.setCep(resultado.getString("cep"));
                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (get aluno) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return aluno;
    }

    public Aluno getByCPF(String cpf) throws Exception {
        Conexao conexao = new Conexao();
        try {
            Aluno aluno = new Aluno();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM Alunos WHERE CPF = ? LIMIT 1 ");
            sql.setString(1, cpf);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    aluno.setId(Integer.parseInt(resultado.getString("ID")));
                    aluno.setNome(resultado.getString("NOME"));
                    aluno.setEmail(resultado.getString("EMAIL"));
                    aluno.setCpf(resultado.getString("CPF"));
                    aluno.setSenha(resultado.getString("SENHA"));
                    aluno.setCelular(resultado.getString("CELULAR"));
                    aluno.setEndereco(resultado.getString("ENDERECO"));
                    aluno.setCidade(resultado.getString("CIDADE"));
                    aluno.setBairro(resultado.getString("BAIRRO"));
                    aluno.setCep(resultado.getString("CEP"));
                }
            }
            return aluno;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public ArrayList<Aluno> getAll() {
        ArrayList<Aluno> alunos = new ArrayList<Aluno>();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM Alunos";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Aluno Aluno = new Aluno(resultado.getInt("id"), resultado.getString("nome"),
                            resultado.getString("email"),
                            resultado.getString("celular"),
                            resultado.getString("cpf"),
                            resultado.getString("senha"),
                            resultado.getString("endereco"),
                            resultado.getString("cidade"),
                            resultado.getString("bairro"),
                            resultado.getString("cep"));
                    alunos.add(Aluno);
                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (GetAll - alunos) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return alunos;
    }

    @Override
    public void insert(Aluno Aluno) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(
                    "INSERT INTO Alunos (nome, email, celular, cpf, senha, endereco, cidade, bairro, cep)"
                            + " VALUES (?,?,?,?,?,?,?,?,?)");
            sql.setString(1, Aluno.getNome());
            sql.setString(2, Aluno.getEmail());
            sql.setString(3, Aluno.getCelular());
            sql.setString(4, Aluno.getCpf());
            sql.setString(5, Aluno.getSenha());
            sql.setString(6, Aluno.getEndereco());
            sql.setString(7, Aluno.getCidade());
            sql.setString(8, Aluno.getBairro());
            sql.setString(9, Aluno.getCep());

            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void update(Aluno Aluno) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(
                    "UPDATE Alunos SET nome = ?, email = ?, celular = ?, cpf = ?, senha = ?, endereco = ?, cidade = ?, bairro = ?, cep = ?  WHERE ID = ? ");
            sql.setString(1, Aluno.getNome());
            sql.setString(2, Aluno.getEmail());
            sql.setString(3, Aluno.getCelular());
            sql.setString(4, Aluno.getCpf());
            sql.setString(5, Aluno.getSenha());
            sql.setString(6, Aluno.getEndereco());
            sql.setString(7, Aluno.getCidade());
            sql.setString(8, Aluno.getBairro());
            sql.setString(9, Aluno.getCep());
            sql.setInt(10, Aluno.getId());

            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar aluno) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void delete(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM Alunos WHERE id = ? ");
            sql.setInt(1, id);
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir aluno) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public Aluno Logar(Aluno Aluno) throws Exception {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao()
                    .prepareStatement("SELECT * FROM Alunos WHERE cpf=? and senha =? LIMIT 1");
            sql.setString(1, Aluno.getCpf());
            sql.setString(2, Aluno.getSenha());
            ResultSet resultado = sql.executeQuery();
            Aluno AlunoObtido = new Aluno();
            if (resultado != null) {
                while (resultado.next()) {
                    AlunoObtido.setId(Integer.parseInt(resultado.getString("id")));
                    AlunoObtido.setNome(resultado.getString("nome"));
                    AlunoObtido.setEmail(resultado.getString("email"));
                    AlunoObtido.setCelular(resultado.getString("celular"));
                    AlunoObtido.setCpf(resultado.getString("cpf"));
                    AlunoObtido.setSenha(resultado.getString("senha"));
                    AlunoObtido.setEndereco(resultado.getString("endereco"));
                    AlunoObtido.setCidade(resultado.getString("cidade"));
                    AlunoObtido.setBairro(resultado.getString("bairro"));
                    AlunoObtido.setCep(resultado.getString("cep"));
                }
            }
            return AlunoObtido;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }
}
