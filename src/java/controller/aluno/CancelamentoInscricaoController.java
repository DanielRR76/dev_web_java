package controller.aluno;

import javax.servlet.RequestDispatcher;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entidade.Aluno;
import entidade.AlunoProfessorDisciplina;
import entidade.Turma;
import model.AdministradorDAO;
import model.TurmaDAO;

@WebServlet(name = "CancelamentoInscricaoController", urlPatterns = { "/aluno/cancelarInscricao" })
public class CancelamentoInscricaoController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        RequestDispatcher rd;

        ArrayList<AlunoProfessorDisciplina> turmas = new ArrayList<AlunoProfessorDisciplina>();
        TurmaDAO turmaDAO = new TurmaDAO();
        Aluno aluno = (Aluno)((HttpServletRequest) request).getSession().getAttribute("aluno");

        turmas = turmaDAO.getAllUnit();

        turmas = turmas.stream().filter(apd -> apd.getTurma().getIdAluno() == aluno.getId() && apd.getTurma().getNota() == -1)
            .sorted(Comparator.comparing(apd -> apd.getDisciplina().getNome()))
            .collect(Collectors.toCollection(ArrayList::new));
        request.setAttribute("turmas", turmas);

        rd = request.getRequestDispatcher("/views/aluno/inscricao/cancelamentoInscricao.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        RequestDispatcher rd;
        ArrayList<AlunoProfessorDisciplina> turmas = new ArrayList<AlunoProfessorDisciplina>();
        TurmaDAO turmaDAO = new TurmaDAO();
        ArrayList<AlunoProfessorDisciplina> turmasSemVagas = new ArrayList<AlunoProfessorDisciplina>();
        Aluno aluno = (Aluno)((HttpServletRequest) request).getSession().getAttribute("aluno");
        String[] turmasCadastradas = request.getParameterValues("turmas");


        if(turmasCadastradas == null || turmasCadastradas.length == 0) {
            request.setAttribute("msgError", "Por favor, escolha pelo menos uma turma.");
            turmas = turmaDAO.getAllUnit();

            turmas = turmas.stream().filter(apd -> apd.getTurma().getIdAluno() == aluno.getId() && apd.getTurma().getNota() == -1)
                .sorted(Comparator.comparing(apd -> apd.getDisciplina().getNome()))
                .collect(Collectors.toCollection(ArrayList::new));
            request.setAttribute("turmas", turmas);

            rd = request.getRequestDispatcher("/views/aluno/inscricao/cancelamentoInscricao.jsp");
            rd.forward(request, response);
        }
        else {
            for(String ids: turmasCadastradas) {
                try {
                    String[] partes = ids.split("-");
                    Turma turma = turmaDAO.getByIds(Integer.parseInt(partes[0]), aluno.getId(), Integer.parseInt(partes[1]));
                    turmaDAO.delete(turma.getId());

                    if(turmasCadastradas.length > 1) {
                        request.setAttribute("msgSuccess", "Cancelado a inscrição com sucesso!");
                    }
                    else {
                        request.setAttribute("msgSuccess", "Cancelado as inscrições com sucesso!");
                    }
                    turmas = turmaDAO.getAllUnit();

                    turmas = turmas.stream().filter(apd -> apd.getTurma().getIdAluno() == aluno.getId() && apd.getTurma().getNota() == -1)
                        .sorted(Comparator.comparing(apd -> apd.getDisciplina().getNome()))
                        .collect(Collectors.toCollection(ArrayList::new));
                    request.setAttribute("turmas", turmas);

                    rd = request.getRequestDispatcher("/views/aluno/inscricao/cancelamentoInscricao.jsp");
                    rd.forward(request, response);

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
}
