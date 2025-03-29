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

@WebServlet(name = "InscricaoController", urlPatterns = { "/aluno/inscricao" })
public class InscricaoController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        RequestDispatcher rd;

        ArrayList<AlunoProfessorDisciplina> turmas = new ArrayList<AlunoProfessorDisciplina>();
        TurmaDAO turmaDAO = new TurmaDAO();
        Aluno aluno = (Aluno)((HttpServletRequest) request).getSession().getAttribute("aluno");
        ArrayList<AlunoProfessorDisciplina> turmasSemVagas = new ArrayList<AlunoProfessorDisciplina>();

        turmas = turmaDAO.getAllUnit();
        for (int i = 0; i < turmas.size(); i++) {
            for (int j = i + 1; j < turmas.size(); j++) {
                if(turmas.get(i).getTurma().getIdDisciplina() == turmas.get(j).getTurma().getIdDisciplina()
                    && turmas.get(i).getTurma().getIdProfessor() == turmas.get(j).getTurma().getIdProfessor()) {
                    if(!turmasSemVagas.contains(turmas.get(i))) {
                        turmasSemVagas.add(turmas.get(i));
                    }
                    if (!turmasSemVagas.contains(turmas.get(j))) {
                        turmasSemVagas.add(turmas.get(j));

                    }
                }
            }
        }
        turmas.removeAll(turmasSemVagas);
        turmas = turmas.stream().filter(apd -> apd.getTurma().getIdAluno() != aluno.getId())
            .sorted(Comparator.comparing(apd -> apd.getDisciplina().getNome()))
            .collect(Collectors.toCollection(ArrayList::new));
        request.setAttribute("turmas", turmas);

        rd = request.getRequestDispatcher("/views/aluno/inscricao/inscricao.jsp");
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
            for (int i = 0; i < turmas.size(); i++) {
                for (int j = i + 1; j < turmas.size(); j++) {
                    if(turmas.get(i).getTurma().getIdDisciplina() == turmas.get(j).getTurma().getIdDisciplina()
                        && turmas.get(i).getTurma().getIdProfessor() == turmas.get(j).getTurma().getIdProfessor()) {
                        if(!turmasSemVagas.contains(turmas.get(i))) {
                            turmasSemVagas.add(turmas.get(i));
                        }
                        if (!turmasSemVagas.contains(turmas.get(j))) {
                            turmasSemVagas.add(turmas.get(j));

                        }
                    }
                }
            }
            turmas.removeAll(turmasSemVagas);
            turmas = turmas.stream().filter(apd -> apd.getTurma().getIdAluno() != aluno.getId())
                .sorted(Comparator.comparing(apd -> apd.getDisciplina().getNome()))
                .collect(Collectors.toCollection(ArrayList::new));
            request.setAttribute("turmas", turmas);

            rd = request.getRequestDispatcher("/views/aluno/inscricao/inscricao.jsp");
            rd.forward(request, response);
        }
        else {
            for(String ids: turmasCadastradas) {
                try {
                    String[] partes = ids.split("-");
                    Turma turmaExistente = turmaDAO.getByIds(Integer.parseInt(partes[0]), aluno.getId(), Integer.parseInt(partes[1]));
                    if (turmaExistente.getId() != 0) {
                        request.setAttribute("msgError", "Vc já tá inscrito em algum turma");
                        turmas = turmaDAO.getAllUnit();
                        for (int i = 0; i < turmas.size(); i++) {
                            for (int j = i + 1; j < turmas.size(); j++) {
                                if(turmas.get(i).getTurma().getIdDisciplina() == turmas.get(j).getTurma().getIdDisciplina()
                                    && turmas.get(i).getTurma().getIdProfessor() == turmas.get(j).getTurma().getIdProfessor()) {
                                    if(!turmasSemVagas.contains(turmas.get(i))) {
                                        turmasSemVagas.add(turmas.get(i));
                                    }
                                    if (!turmasSemVagas.contains(turmas.get(j))) {
                                        turmasSemVagas.add(turmas.get(j));

                                    }
                                }
                            }
                        }
                        turmas.removeAll(turmasSemVagas);
                        turmas = turmas.stream().filter(apd -> apd.getTurma().getIdAluno() != aluno.getId())
                            .sorted(Comparator.comparing(apd -> apd.getDisciplina().getNome()))
                            .collect(Collectors.toCollection(ArrayList::new));
                        request.setAttribute("turmas", turmas);

                        rd = request.getRequestDispatcher("/views/aluno/inscricao/inscricao.jsp");
                        rd.forward(request, response);
                    }
                    else {
                        Turma turma = new Turma(Integer.parseInt(partes[0]), Integer.parseInt(partes[1]), aluno.getId(), partes[2], -1.0 );
                        turmaDAO.insert(turma);

                        if(turmasCadastradas.length > 1) {
                            request.setAttribute("msgSuccess", "Você foi inscrito na turma com sucesso!");
                        }
                        else {
                            request.setAttribute("msgSuccess", "Você foi inscrito nas turmas com sucesso!");
                        }
                        turmas = turmaDAO.getAllUnit();
                        for (int i = 0; i < turmas.size(); i++) {
                            for (int j = i + 1; j < turmas.size(); j++) {
                                if(turmas.get(i).getTurma().getIdDisciplina() == turmas.get(j).getTurma().getIdDisciplina()
                                    && turmas.get(i).getTurma().getIdProfessor() == turmas.get(j).getTurma().getIdProfessor()) {
                                    if(!turmasSemVagas.contains(turmas.get(i))) {
                                        turmasSemVagas.add(turmas.get(i));
                                    }
                                    if (!turmasSemVagas.contains(turmas.get(j))) {
                                        turmasSemVagas.add(turmas.get(j));

                                    }
                                }
                            }
                        }
                        turmas.removeAll(turmasSemVagas);
                        turmas = turmas.stream().filter(apd -> apd.getTurma().getIdAluno() != aluno.getId())
                            .sorted(Comparator.comparing(apd -> apd.getDisciplina().getNome()))
                            .collect(Collectors.toCollection(ArrayList::new));
                        request.setAttribute("turmas", turmas);

                        rd = request.getRequestDispatcher("/views/aluno/inscricao/inscricao.jsp");
                        rd.forward(request, response);
                    }

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
        }
    }
}
