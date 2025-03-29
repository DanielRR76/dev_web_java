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

import entidade.AlunoProfessorDisciplina;
import model.TurmaDAO;

@WebServlet(name = "DashboardController", urlPatterns = {"/admin/dashboard"})
public class DashboardController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        RequestDispatcher rd;

        // ArrayList<AlunoProfessorDisciplina> turmas = new ArrayList<AlunoProfessorDisciplina>();
        // TurmaDAO turmaDAO = new TurmaDAO();

        // turmas = turmaDAO.getAllUnit();

        // request.setAttribute("turmas", turmas);
        rd = request.getRequestDispatcher("/views/admin/dashboard/areaRestrita.jsp");
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

        // turmas = turmaDAO.getAllUnit();

        // request.setAttribute("turmas", turmas);
        rd = request.getRequestDispatcher("/views/admin/dashboard/areaRestrita.jsp");
        rd.forward(request, response);
    }

}
