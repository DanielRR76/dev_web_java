package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidade.Disciplina;

public class DisciplinaDAO implements Dao<Disciplina> {

    @Override
    public Disciplina get(int id) {
        Conexao conexao = new Conexao();
        Disciplina disciplina = new Disciplina();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM Disciplina WHERE id = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();

            if (resultado != null) {
                while (resultado.next()) {
                    disciplina.setId(Integer.parseInt(resultado.getString("id")));
                    disciplina.setNome(resultado.getString("nome"));
                    disciplina.setRequisito(resultado.getString("requisito"));
                    disciplina.setEmenta(resultado.getString("ementa"));
                    disciplina.setCargaHoraria(Integer.parseInt(resultado.getString("carga_horaria")));
                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (get disciplina) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return disciplina;
    }

    public Disciplina getByNome(String cpf) throws Exception {
        Conexao conexao = new Conexao();
        try {
            Disciplina disciplina = new Disciplina();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM Disciplina WHERE NOME = ? LIMIT 1 ");
            sql.setString(1, cpf);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    disciplina.setId(Integer.parseInt(resultado.getString("ID")));
                    disciplina.setNome(resultado.getString("NOME"));
                    disciplina.setRequisito(resultado.getString("REQUISITO"));
                    disciplina.setEmenta(resultado.getString("EMENTA"));
                    disciplina.setCargaHoraria(Integer.parseInt(resultado.getString("CARGA_HORARIA")));
                }
            }
            return disciplina;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public ArrayList<Disciplina> getAll() {
        ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM Disciplina ORDER BY nome";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Disciplina Disciplina = new Disciplina(resultado.getInt("id"),
                            resultado.getString("nome"),
                            resultado.getString("requisito"),
                            resultado.getString("ementa"),
                            Integer.parseInt(resultado.getString("carga_horaria")));
                    disciplinas.add(Disciplina);
                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (GetAll - professores) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return disciplinas;
    }

    @Override
    public void insert(Disciplina Disciplina) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(
                    "INSERT INTO Disciplina (nome, requisito, ementa, carga_horaria)"
                            + " VALUES (?,?,?,?)");
            sql.setString(1, Disciplina.getNome());
            sql.setString(2, Disciplina.getRequisito());
            sql.setString(3, Disciplina.getEmenta());
            sql.setInt(4, Disciplina.getCargaHoraria());

            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void update(Disciplina Disciplina) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(
                    "UPDATE Disciplina SET nome = ?, requisito = ?, ementa = ?, carga_horaria = ?  WHERE id = ? ");
            sql.setString(1, Disciplina.getNome());
            sql.setString(2, Disciplina.getRequisito());
            sql.setString(3, Disciplina.getEmenta());
            sql.setInt(4, Disciplina.getCargaHoraria());
            sql.setInt(5, Disciplina.getId());

            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar disciplina) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void delete(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM Disciplina WHERE id = ? ");
            sql.setInt(1, id);
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir disciplina) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

}
