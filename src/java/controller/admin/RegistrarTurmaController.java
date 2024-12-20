package controller.admin;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidade.Aluno;
import entidade.Disciplina;
import entidade.Professor;
import entidade.Turma;
import model.AlunoDAO;
import model.DisciplinaDAO;
import model.ProfessorDAO;
import model.TurmaDAO;

@WebServlet(name = "RegistrarTurmaController", urlPatterns = {"/admin/registrarTurma"})
public class RegistrarTurmaController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        RequestDispatcher rd;

        ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();
        ArrayList<Aluno> alunos = new ArrayList<Aluno>();
        ArrayList<Professor> professores = new ArrayList<Professor>();

        DisciplinaDAO DisciplinaDAO = new DisciplinaDAO();
        AlunoDAO AlunoDAO = new AlunoDAO();
        ProfessorDAO ProfessorDAO = new ProfessorDAO();

        disciplinas = DisciplinaDAO.getAll();
        alunos = AlunoDAO.getAll();
        professores = ProfessorDAO.getAll();

        request.setAttribute("disciplinas", disciplinas);
        request.setAttribute("alunos", alunos);
        request.setAttribute("professores", professores);
        rd = request.getRequestDispatcher("/views/admin/registro/formTurmaRegistro.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        RequestDispatcher rd;
        ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();
        ArrayList<Aluno> alunos = new ArrayList<Aluno>();
        ArrayList<Professor> professores = new ArrayList<Professor>();

        DisciplinaDAO DisciplinaDAO = new DisciplinaDAO();
        AlunoDAO AlunoDAO = new AlunoDAO();
        ProfessorDAO ProfessorDAO = new ProfessorDAO();

        disciplinas = DisciplinaDAO.getAll();
        alunos = AlunoDAO.getAll();
        professores = ProfessorDAO.getAll();
        int idProfessor = Integer.parseInt(request.getParameter("prof"));
        System.out.println(idProfessor);
        int idAluno = Integer.parseInt(request.getParameter("aluno"));
        int idDisciplina = Integer.parseInt(request.getParameter("disciplina"));
        String codigoTurma = request.getParameter("codigoTurma");
        if (idProfessor == 0 || idAluno == 0 || idDisciplina == 0 || codigoTurma.isEmpty() ) {
            // dados não foram preenchidos retorna ao formulário

            request.setAttribute("disciplinas", disciplinas);
            request.setAttribute("alunos", alunos);
            request.setAttribute("professores", professores);
            request.setAttribute("msgError", "Todos os campos são obrigatórios");
            rd = request.getRequestDispatcher("/views/admin/registro/formTurmaRegistro.jsp");
            rd.forward(request, response);

        } else {
            Turma turma = new Turma(idProfessor, idDisciplina, idAluno, codigoTurma, -1.0);
            TurmaDAO TurmaDAO = new TurmaDAO();
            try {
                Turma turma2 = TurmaDAO.getByIds(idProfessor, idAluno, idDisciplina);
                ArrayList<Turma> maxLecionamento = TurmaDAO.getMaxLecionamento(idProfessor);
                ArrayList<Turma> maxIncricao = TurmaDAO.getMaxInscricao(idProfessor, idDisciplina);
                if (turma2.getIdProfessor() != 0 && turma2.getIdAluno() != 0 && turma2.getIdDisciplina() != 0) {
                    request.setAttribute("disciplinas", disciplinas);
                    request.setAttribute("alunos", alunos);
                    request.setAttribute("professores", professores);
                    request.setAttribute("msgError", "Turma já cadastrada");
                    rd = request.getRequestDispatcher("/views/admin/registro/formTurmaRegistro.jsp");
                    rd.forward(request, response);
                    
                }
                else if(maxIncricao.size() == 2) {
                    request.setAttribute("disciplinas", disciplinas);
                    request.setAttribute("alunos", alunos);
                    request.setAttribute("professores", professores);
                    request.setAttribute("msgError", "Não tem mais vagas disponíveis para essa turma");
                    rd = request.getRequestDispatcher("/views/admin/registro/formTurmaRegistro.jsp");
                    rd.forward(request, response);
                }
                else if (maxLecionamento.size() == 2) {
                    request.setAttribute("disciplinas", disciplinas);
                    request.setAttribute("alunos", alunos);
                    request.setAttribute("professores", professores);
                    request.setAttribute("msgError", "Professor já leciona duas turmas");
                    rd = request.getRequestDispatcher("/views/admin/registro/formTurmaRegistro.jsp");
                    rd.forward(request, response);
                }
                else {
                    TurmaDAO.insert(turma);
                    request.setAttribute("disciplinas", disciplinas);
                    request.setAttribute("alunos", alunos);
                    request.setAttribute("professores", professores);
                    request.setAttribute("msgSuccess", "Turma cadastrada com sucesso!");
                    rd = request.getRequestDispatcher("/views/admin/registro/formTurmaRegistro.jsp");
                    rd.forward(request, response);
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha na query para Logar");
            }
        }

    }
}
