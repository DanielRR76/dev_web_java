package entidade;

public class Turma {

    private int id;
    private int idProfessor;
    private int idDisciplina;
    private int idAluno;
    private String codigoTurma;
    private Double nota;

    public Turma(int id, int idProfessor, int idDisciplina, int idAluno, String codigoTurma, Double nota) {
        this.id = id;
        this.idProfessor = idProfessor;
        this.idDisciplina = idDisciplina;
        this.idAluno = idAluno;
        this.codigoTurma = codigoTurma;
        this.nota = nota;
    }

    public Turma() {
        this.id = 0;
        this.idProfessor = 0;
        this.idDisciplina = 0;
        this.idAluno = 0;
        this.codigoTurma = "";
        this.nota = 0.0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdProfessor() {
        return idProfessor;
    }

    public void setIdProfessor(int idProfessor) {
        this.idProfessor = idProfessor;
    }

    public int getIdDisciplina() {
        return idDisciplina;
    }

    public void setIdDisciplina(int idDisciplina) {
        this.idDisciplina = idDisciplina;
    }

    public int getIdAluno() {
        return idAluno;
    }

    public void setIdAluno(int idAluno) {
        this.idAluno = idAluno;
    }

    public String getCodigoTurma() {
        return codigoTurma;
    }

    public void setCodigoTurma(String codigoTurma) {
        this.codigoTurma = codigoTurma;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }
}
