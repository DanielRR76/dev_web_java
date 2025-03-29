package controller.aluno;

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

import entidade.Aluno;
import entidade.AlunoProfessorDisciplina;
import model.TurmaDAO;

@WebServlet(name = "DashboardAlunoController", urlPatterns = {"/aluno/dashboard"})
public class DashboardAlunoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        RequestDispatcher rd;

        // ArrayList<AlunoProfessorDisciplina> turmas = new ArrayList<AlunoProfessorDisciplina>();
        // TurmaDAO turmaDAO = new TurmaDAO();
        // Aluno aluno = (Aluno)((HttpServletRequest) request).getSession().getAttribute("aluno");

        // turmas = turmaDAO.getAllUnit();
        // turmas = turmas.stream().filter(apd -> apd.getTurma().getIdAluno() == aluno.getId() && apd.getTurma().getNota() != -1)
        //     .sorted(Comparator.comparing(apd -> apd.getDisciplina().getNome()))
        //     .collect(Collectors.toCollection(ArrayList::new));


        // request.setAttribute("turmas", turmas);

        rd = request.getRequestDispatcher("/views/aluno/dashboard/areaRestrita.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        RequestDispatcher rd;

        // ArrayList<AlunoProfessorDisciplina> turmas = new ArrayList<AlunoProfessorDisciplina>();
        // TurmaDAO turmaDAO = new TurmaDAO();
        // Aluno aluno = (Aluno)((HttpServletRequest) request).getSession().getAttribute("aluno");

        // turmas = turmaDAO.getAllUnit();
        // turmas = turmas.stream().filter(apd -> apd.getTurma().getIdAluno() == aluno.getId() && apd.getTurma().getNota() != -1)
        //     .sorted(Comparator.comparing(apd -> apd.getDisciplina().getNome()))
        //     .collect(Collectors.toCollection(ArrayList::new));


        // request.setAttribute("turmas", turmas);

        rd = request.getRequestDispatcher("/views/aluno/dashboard/areaRestrita.jsp");
        rd.forward(request, response);
    }

}
