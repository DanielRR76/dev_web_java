package controller.professor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidade.AlunoProfessorDisciplina;
import entidade.Professor;
import model.TurmaDAO;

@WebServlet(name = "RelatorioProfController", urlPatterns = {"/professor/relatorio"})
public class RelatorioController extends HttpServlet {

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

        rd = request.getRequestDispatcher("/views/professor/relatorio/relatorio.jsp");
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

        turmas = turmaDAO.getAllUnit();
        turmas = turmas.stream().filter(apd -> apd.getTurma().getIdProfessor() == professor.getId())
            .sorted(Comparator.comparing(apd -> apd.getTurma().getCodigoTurma()))
            .collect(Collectors.toCollection(ArrayList::new));


        request.setAttribute("turmas", turmas);

        rd = request.getRequestDispatcher("/views/professor/relatorio/relatorio.jsp");
        rd.forward(request, response);
    }

}
