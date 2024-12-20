package entidade;

public class AlunoProfessorDisciplina {
 private Aluno aluno;
 private Professor professor;
 private Disciplina disciplina;
 private Turma turma;
 
 public AlunoProfessorDisciplina(Aluno aluno, Professor professor, Disciplina disciplina, Turma turma) {
  this.aluno = aluno;
  this.professor = professor;
  this.disciplina = disciplina;
  this.turma = turma;
 }
 
 public Aluno getAluno() {
  return this.aluno;
 }
 
 public Professor getProfessor() {
  return this.professor;
 }
 
 public Disciplina getDisciplina() {
  return this.disciplina;
 }
 
 public Turma getTurma() {
  return this.turma;
 }
}
