package controller.professor;

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

import entidade.AlunoProfessorDisciplina;
import entidade.Professor;
import entidade.Turma;
import model.TurmaDAO;

@WebServlet(name = "LancarNotaController", urlPatterns = { "/professor/lancarNota" })
public class LancarNotaController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        RequestDispatcher rd;

        ArrayList<AlunoProfessorDisciplina> turmas = new ArrayList<AlunoProfessorDisciplina>();
        TurmaDAO turmaDAO = new TurmaDAO();
        Professor professor = (Professor)((HttpServletRequest) request).getSession().getAttribute("professor");

        turmas = turmaDAO.getAllUnit();
        turmas = turmas.stream().filter(apd -> apd.getTurma().getIdProfessor() == professor.getId())
            .sorted(Comparator.comparing(apd -> apd.getTurma().getCodigoTurma()))
            .collect(Collectors.toCollection(ArrayList::new));


        request.setAttribute("turmas", turmas);

        rd = request.getRequestDispatcher("/views/professor/lancarNota/lancarNota.jsp");
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
        Professor professor = (Professor)((HttpServletRequest) request).getSession().getAttribute("professor");

        String[] alunoIds = request.getParameterValues("alunoIds");
        String[] notas = request.getParameterValues("notas");
        String [] disciplinaIds = request.getParameterValues("disciplinaIds");
        Boolean vazio = true;
        for (int i = 0; i < notas.length; i++) {
            if(!notas[i].isEmpty()) {
                vazio = false;
            }
        }
        if(vazio) {
            request.setAttribute("msgError", "Por favor, preencha pelo menos uma nota.");
            turmas = turmaDAO.getAllUnit();
            turmas = turmas.stream().filter(apd -> apd.getTurma().getIdProfessor() == professor.getId())
                .sorted(Comparator.comparing(apd -> apd.getTurma().getCodigoTurma()))
                .collect(Collectors.toCollection(ArrayList::new));


            request.setAttribute("turmas", turmas);
            request.getRequestDispatcher("/views/professor/lancarNota/lancarNota.jsp");
        }
        else {
            for (int i = 0; i < alunoIds.length; i++) {
                if(!notas[i].isEmpty() && alunoIds[i] != null) {
                    Turma turma =  turmaDAO.getByIds(professor.getId(), Integer.parseInt(alunoIds[i]), Integer.parseInt(disciplinaIds[i]));
                    turma.setNota(Double.parseDouble(notas[i]));
                    turmaDAO.update(turma);
                }
            }
            request.setAttribute("msgSuccess", "Notas lançadas com sucesso!");
        }
        turmas = turmaDAO.getAllUnit();
        turmas = turmas.stream().filter(apd -> apd.getTurma().getIdProfessor() == professor.getId())
            .sorted(Comparator.comparing(apd -> apd.getTurma().getCodigoTurma()))
            .collect(Collectors.toCollection(ArrayList::new));


        request.setAttribute("turmas", turmas);
        rd = request.getRequestDispatcher("/views/professor/lancarNota/lancarNota.jsp");
        rd.forward(request, response);
    }
}
