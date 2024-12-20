package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import entidade.Aluno;
import entidade.AlunoProfessorDisciplina;
import entidade.Disciplina;
import entidade.Professor;
import entidade.Turma;

public class TurmaDAO implements Dao<Turma> {

    @Override
    public Turma get(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM Turmas WHERE id = ? ");
            sql.setInt(1, id);
            ResultSet resultado = sql.executeQuery();
            Turma turma = new Turma();

            if (resultado != null) {
                while (resultado.next()) {
                    turma.setId(Integer.parseInt(resultado.getString("id")));
                    turma.setIdProfessor(Integer.parseInt(resultado.getString("professor_id")));
                    turma.setIdDisciplina(Integer.parseInt(resultado.getString("disciplina_id")));
                    turma.setIdAluno(Integer.parseInt(resultado.getString("aluno_id")));
                    turma.setCodigoTurma(resultado.getString("codigo_turma"));
                    turma.setNota(Double.parseDouble(resultado.getString("nota")));
                }
            }
            return turma;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get turma) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public Turma getByIds(int idProfessor, int idAluno, int idDisciplina) throws Exception {
        Conexao conexao = new Conexao();
        try {
            Turma turma = new Turma();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM Turmas WHERE professor_id = ? and aluno_id = ? and disciplina_id = ?");
            sql.setInt(1, idProfessor);
            sql.setInt(2, idAluno);
            sql.setInt(3, idDisciplina);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    turma.setId(Integer.parseInt(resultado.getString("ID")));
                    turma.setIdProfessor(Integer.parseInt(resultado.getString("professor_id")));
                    turma.setIdDisciplina(Integer.parseInt(resultado.getString("disciplina_id")));
                    turma.setIdAluno(Integer.parseInt(resultado.getString("aluno_id")));
                    turma.setCodigoTurma(resultado.getString("codigo_turma"));
                    turma.setNota(Double.parseDouble(resultado.getString("nota")));
                }
            }
            return turma;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    public ArrayList<Turma> getMaxLecionamento(int idProfessor) throws Exception {
        Conexao conexao = new Conexao();
        try {
            ArrayList<Turma> turmas = new ArrayList<Turma>();
            String selectSQL = "WITH CTE AS (SELECT *,ROW_NUMBER() OVER (PARTITION BY professor_id, disciplina_id) AS rn "
                    + "FROM turmas WHERE professor_id = ?) SELECT * FROM CTE WHERE rn = 1";
            PreparedStatement sql = conexao.getConexao().prepareStatement(selectSQL);
            sql.setInt(1, idProfessor);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Turma Turma = new Turma(resultado.getInt("id"), resultado.getInt("professor_id"),
                            resultado.getInt("disciplina_id"), resultado.getInt("aluno_id"),
                            resultado.getString("codigo_turma"), Double.parseDouble(resultado.getString("nota")));
                    turmas.add(Turma);
                }
            }
            return turmas;

        } catch (SQLException e) {
            throw new RuntimeException(e.getMessage());
        } finally {
            conexao.closeConexao();
        }
    }

    public ArrayList<Turma> getMaxInscricao(int idProfessor, int idDisciplina) throws Exception {
        Conexao conexao = new Conexao();
        try {
            ArrayList<Turma> turmas = new ArrayList<Turma>();
            PreparedStatement sql = conexao.getConexao().prepareStatement("SELECT * FROM Turmas WHERE professor_id = ? and disciplina_id = ?");
            sql.setInt(1, idProfessor);
            sql.setInt(2, idDisciplina);
            ResultSet resultado = sql.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Turma Turma = new Turma(resultado.getInt("id"), resultado.getInt("professor_id"),
                            resultado.getInt("disciplina_id"), resultado.getInt("aluno_id"),
                            resultado.getString("codigo_turma"), Double.parseDouble(resultado.getString("nota")));
                    turmas.add(Turma);
                }
            }
            return turmas;

        } catch (SQLException e) {
            throw new RuntimeException("Query de select (get) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public ArrayList<Turma> getAll() {
        ArrayList<Turma> turmas = new ArrayList<Turma>();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT * FROM Turmas";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Turma Turma = new Turma(resultado.getInt("id"), resultado.getInt("professor_id"),
                            resultado.getInt("disciplina_id"), resultado.getInt("aluno_id"),
                            resultado.getString("codigo_turma"), Double.parseDouble(resultado.getString("nota")));
                    turmas.add(Turma);
                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (GetAll - turmas) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return turmas;
    }
    public ArrayList<AlunoProfessorDisciplina> getAllUnit() {
        ArrayList<AlunoProfessorDisciplina> turmas = new ArrayList<AlunoProfessorDisciplina>();
        Conexao conexao = new Conexao();
        try {
            String selectSQL = "SELECT a.id alunoId, a.nome AS alunoNome, a.email AS alunoEmail, a.celular AS alunoCelular,"
                    + "a.cpf AS alunoCpf, a.senha AS alunoSenha, a.endereco AS alunoEnd, a.cidade, a.bairro , a.cep,"
                    + "p.id AS profId, p.nome AS profNome, p.email as profEmail, p.cpf AS profCpf, p.senha AS profSenha,"
                    + "d.id AS disciplinaId, d.nome AS disciplinaNome, d.requisito , d.ementa, d.carga_horaria,"
                    + "t.id AS turmaId ,t.codigo_turma , t.nota FROM turmas t INNER JOIN alunos a on t.aluno_id = a.id "
                    + "INNER JOIN professores p on t.professor_id = p.id INNER JOIN disciplina d on t.disciplina_id = d.id "
                    + "ORDER BY alunoNome, profNome, disciplinaNome";
            PreparedStatement preparedStatement;
            preparedStatement = conexao.getConexao().prepareStatement(selectSQL);
            ResultSet resultado = preparedStatement.executeQuery();
            if (resultado != null) {
                while (resultado.next()) {
                    Aluno aluno = new Aluno(resultado.getInt("alunoId"), resultado.getString("alunoNome"),
                            resultado.getString("alunoEmail"), resultado.getString("alunoCelular"),
                            resultado.getString("alunoCpf"), resultado.getString("alunoSenha"),
                            resultado.getString("alunoEnd"), resultado.getString("cidade"),
                            resultado.getString("bairro"), resultado.getString("cep"));
                    Professor professor = new Professor(resultado.getInt("profId"), resultado.getString("profNome"),
                            resultado.getString("profEmail"), resultado.getString("profCpf"),
                            resultado.getString("profSenha"));
                    Disciplina disciplina = new Disciplina(resultado.getInt("disciplinaId"), resultado.getString("disciplinaNome"),
                            resultado.getString("requisito"), resultado.getString("ementa"),
                            Integer.parseInt(resultado.getString("carga_horaria")));
                    Turma turma = new Turma(resultado.getInt("turmaId"), resultado.getInt("profId"),
                            resultado.getInt("disciplinaId"), resultado.getInt("alunoId"),
                            resultado.getString("codigo_turma"), Double.parseDouble(resultado.getString("nota")));
                    AlunoProfessorDisciplina alunoProfessorDisciplina = new AlunoProfessorDisciplina(aluno, professor, disciplina, turma);
                    turmas.add(alunoProfessorDisciplina);
                }
            }
        } catch (SQLException e) {
            System.err.println("Query de select (GetAll - turmas) incorreta");
        } finally {
            conexao.closeConexao();
        }
        return turmas;
    }

    @Override
    public void insert(Turma Turma) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(
                    "INSERT INTO Turmas (professor_id, disciplina_id, aluno_id, codigo_turma, nota) "
                            + " VALUES (?,?,?,?,?)");
            sql.setInt(1, Turma.getIdProfessor());
            sql.setInt(2, Turma.getIdDisciplina());
            sql.setInt(3, Turma.getIdAluno());
            sql.setString(4, Turma.getCodigoTurma());
            sql.setDouble(5, Turma.getNota());

            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException();
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void update(Turma Turma) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement(
                    "UPDATE Turmas SET professor_id = ?, disciplina_id = ?, aluno_id = ?, codigo_turma = ?, nota = ?  WHERE id = ? ");
            sql.setInt(1, Turma.getIdProfessor());
            sql.setInt(2, Turma.getIdDisciplina());
            sql.setInt(3, Turma.getIdAluno());
            sql.setString(4, Turma.getCodigoTurma());
            sql.setDouble(5, Turma.getNota());
            sql.setInt(6, Turma.getId());

            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de update (alterar turma) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

    @Override
    public void delete(int id) {
        Conexao conexao = new Conexao();
        try {
            PreparedStatement sql = conexao.getConexao().prepareStatement("DELETE FROM Turmas WHERE id = ? ");
            sql.setInt(1, id);
            sql.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException("Query de delete (excluir turma) incorreta");
        } finally {
            conexao.closeConexao();
        }
    }

}
