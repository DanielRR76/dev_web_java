package controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidade.Aluno;
import entidade.AlunoProfessorDisciplina;
import entidade.Disciplina;
import entidade.Professor;
import entidade.Turma;
import model.AlunoDAO;
import model.DisciplinaDAO;
import model.ProfessorDAO;
import model.TurmaDAO;


@WebServlet(name = "TurmaController", urlPatterns = {"/admin/TurmaController"})
public class TurmaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String acao = (String) request.getParameter("acao");

        ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();
        ArrayList<Aluno> alunos = new ArrayList<Aluno>();
        ArrayList<Professor> professores = new ArrayList<Professor>();

        DisciplinaDAO DisciplinaDAO = new DisciplinaDAO();
        AlunoDAO AlunoDAO = new AlunoDAO();
        ProfessorDAO ProfessorDAO = new ProfessorDAO();

        AlunoProfessorDisciplina turma = new AlunoProfessorDisciplina();
        TurmaDAO turmaDAO = new TurmaDAO();
        RequestDispatcher rd;
        switch (acao) {
            case "Listar":
                ArrayList<AlunoProfessorDisciplina> listaTurmas = turmaDAO.getAllUnit();
                request.setAttribute("turmas", listaTurmas);

                rd = request.getRequestDispatcher("/views/admin/listar/listarTurmas.jsp");
                rd.forward(request, response);

                break;
            case "Alterar":
            case "Excluir":
                int id = Integer.parseInt(request.getParameter("id"));
                turma  = turmaDAO.getById(id);
                disciplinas = DisciplinaDAO.getAll();
                alunos = AlunoDAO.getAll();
                professores = ProfessorDAO.getAll();

                request.setAttribute("disciplinas", disciplinas);
                request.setAttribute("alunos", alunos);
                request.setAttribute("professores", professores);
                request.setAttribute("turma", turma);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/form/formTurma.jsp");
                rd.forward(request, response);
                break;
            case "Incluir":
                disciplinas = DisciplinaDAO.getAll();
                alunos = AlunoDAO.getAll();
                professores = ProfessorDAO.getAll();

                request.setAttribute("disciplinas", disciplinas);
                request.setAttribute("alunos", alunos);
                request.setAttribute("professores", professores);
                request.setAttribute("turma", turma);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/form/formTurma.jsp");
                rd.forward(request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        RequestDispatcher rd;
        int id = Integer.parseInt(request.getParameter("id"));
        String btEnviar = request.getParameter("btEnviar");
        int idProfessor = request.getParameter("prof") != null && !request.getParameter("prof").isEmpty()
            ? Integer.parseInt(request.getParameter("prof"))
            : 0;
        int idAluno = request.getParameter("aluno") != null && !request.getParameter("aluno").isEmpty()
            ? Integer.parseInt(request.getParameter("aluno"))
            : -1;

        int idDisciplina = request.getParameter("disciplina") != null && !request.getParameter("disciplina").isEmpty()
            ? Integer.parseInt(request.getParameter("disciplina"))
            : -1;
        String codigoTurma = request.getParameter("codigoTurma");

        if ((idProfessor == 0 || idAluno == 0 || idDisciplina == 0 || codigoTurma.isEmpty()) && !btEnviar.equals("Excluir")) {
            AlunoProfessorDisciplina turma = new AlunoProfessorDisciplina();
            ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();
            ArrayList<Aluno> alunos = new ArrayList<Aluno>();
            ArrayList<Professor> professores = new ArrayList<Professor>();
            switch (btEnviar) {
                case "Alterar":
                case "Excluir":
                    try {
                        TurmaDAO turmaDAO = new TurmaDAO();
                        DisciplinaDAO DisciplinaDAO = new DisciplinaDAO();
                        AlunoDAO AlunoDAO = new AlunoDAO();
                        ProfessorDAO ProfessorDAO = new ProfessorDAO();

                        turma = turmaDAO.getById(id);
                        disciplinas = DisciplinaDAO.getAll();
                        alunos = AlunoDAO.getAll();
                        professores = ProfessorDAO.getAll();

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha em uma query para cadastro de Turma");
                }
                break;
            }

            request.setAttribute("turma", turma);
            request.setAttribute("disciplinas", disciplinas);
            request.setAttribute("alunos", alunos);
            request.setAttribute("professores", professores);
            request.setAttribute("acao", btEnviar);

            request.setAttribute("msgError", "É necessário preencher todos os campos");

            rd = request.getRequestDispatcher("/views/admin/form/formTurma.jsp");
            rd.forward(request, response);

        } else {

            Turma turma = new Turma(idProfessor, idDisciplina, idAluno, codigoTurma, -1.0);
            ArrayList<Disciplina> disciplinas = new ArrayList<Disciplina>();
            ArrayList<Aluno> alunos = new ArrayList<Aluno>();
            ArrayList<Professor> professores = new ArrayList<Professor>();
            AlunoProfessorDisciplina turmaAux = new AlunoProfessorDisciplina();

            TurmaDAO TurmaDAO = new TurmaDAO();
            DisciplinaDAO DisciplinaDAO = new DisciplinaDAO();
            AlunoDAO AlunoDAO = new AlunoDAO();
            ProfessorDAO ProfessorDAO = new ProfessorDAO();
            try {
                switch (btEnviar) {
                    case "Incluir":
                        try {
                            Turma turma2 = TurmaDAO.getByIds(idProfessor, idAluno, idDisciplina);
                            ArrayList<Turma> maxLecionamento = TurmaDAO.getMaxLecionamento(idProfessor);
                            ArrayList<Turma> maxIncricao = TurmaDAO.getMaxInscricao(idProfessor, idDisciplina);
                            if (turma2.getIdProfessor() != 0 && turma2.getIdAluno() != 0 && turma2.getIdDisciplina() != 0) {
                                disciplinas = DisciplinaDAO.getAll();
                                alunos = AlunoDAO.getAll();
                                professores = ProfessorDAO.getAll();

                                request.setAttribute("disciplinas", disciplinas);
                                request.setAttribute("turma", turmaAux);
                                request.setAttribute("alunos", alunos);
                                request.setAttribute("professores", professores);
                                request.setAttribute("msgError", "Turma já cadastrada");
                                request.setAttribute("acao", btEnviar);
                                rd = request.getRequestDispatcher("/views/admin/form/formTurma.jsp");
                                rd.forward(request, response);
                            }else if(maxIncricao.size() == 2) {
                                disciplinas = DisciplinaDAO.getAll();
                                alunos = AlunoDAO.getAll();
                                professores = ProfessorDAO.getAll();

                                request.setAttribute("disciplinas", disciplinas);
                                request.setAttribute("turma", turmaAux);
                                request.setAttribute("alunos", alunos);
                                request.setAttribute("professores", professores);
                                request.setAttribute("msgError", "Não tem mais vagas disponíveis para essa turma");
                                request.setAttribute("acao", btEnviar);
                                rd = request.getRequestDispatcher("/views/admin/form/formTurma.jsp");
                                rd.forward(request, response);
                            }
                            else if (maxLecionamento.size() == 2) {
                                disciplinas = DisciplinaDAO.getAll();
                                alunos = AlunoDAO.getAll();
                                professores = ProfessorDAO.getAll();

                                request.setAttribute("disciplinas", disciplinas);
                                request.setAttribute("turma", turmaAux);
                                request.setAttribute("alunos", alunos);
                                request.setAttribute("professores", professores);
                                request.setAttribute("msgError", "Professor já leciona duas turmas");
                                request.setAttribute("acao", btEnviar);
                                rd = request.getRequestDispatcher("/views/admin/form/formTurma.jsp");
                                rd.forward(request, response);
                            }
                            else {
                                TurmaDAO.insert(turma);
                                request.setAttribute("msgOperacaoRealizada", "Turma cadastrada com sucesso!");
                                request.setAttribute("link", "/aplicacaoMVC/admin/TurmaController?acao=Listar");
                                rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                                rd.forward(request, response);
                            }
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }

                        break;
                    case "Alterar":
                        try {
                            Turma turma2 = TurmaDAO.get(id);
                            turma2.setId(id);
                            turma2.setIdAluno(idAluno);
                            turma2.setIdDisciplina(idDisciplina);
                            turma2.setIdProfessor(idProfessor);
                            turma2.setCodigoTurma(codigoTurma);
                            TurmaDAO.update(turma2);
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                        request.setAttribute("msgOperacaoRealizada", "Turma atualizada com sucesso!");
                        request.setAttribute("link", "/aplicacaoMVC/admin/TurmaController?acao=Listar");
                        rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                        rd.forward(request, response);
                        break;
                    case "Excluir":
                        try {
                            TurmaDAO.delete(id);
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                        request.setAttribute("msgOperacaoRealizada", "Turma excluida com sucesso!");
                        request.setAttribute("link", "/aplicacaoMVC/admin/TurmaController?acao=Listar");
                        rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                        rd.forward(request, response);
                        break;
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException(ex.getMessage());
            }
        }
    }

}
