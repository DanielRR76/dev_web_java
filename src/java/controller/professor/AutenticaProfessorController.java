package controller.professor;

import entidade.Professor;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.ProfessorDAO;

@WebServlet(name = "AutenticaProfessorController", urlPatterns = { "/AutenticaProfessorController" })
public class AutenticaProfessorController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/views/autenticacao/formLoginProfessor.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        RequestDispatcher rd;
        String cpf_user = request.getParameter("cpf");
        String senha_user = request.getParameter("senha");
        if (cpf_user.isEmpty() || senha_user.isEmpty()) {
            request.setAttribute("msgError", "Preencha todos os campos");
            rd = request.getRequestDispatcher("/views/autenticacao/formLoginProfessor.jsp");
            rd.forward(request, response);

        } else {
            Professor professorObtido;
            Professor professor = new Professor(cpf_user, senha_user);
            ProfessorDAO ProfessorDAO = new ProfessorDAO();
            try {
                professorObtido = ProfessorDAO.getByCPF(professor.getCpf());
                if(professorObtido.getId() == 0) {
                    request.setAttribute("msgError", "Usuário nao cadastrado");
                    rd = request.getRequestDispatcher("/views/autenticacao/formLoginProfessor.jsp");
                    rd.forward(request, response);
                }
                else if (!professorObtido.getSenha().equals(professor.getSenha())) {
                    request.setAttribute("msgError", "Senha incorreta");
                    rd = request.getRequestDispatcher("/views/autenticacao/formLoginProfessor.jsp");
                    rd.forward(request, response);
                }
                else {
                    HttpSession session = request.getSession();
                    session.setAttribute("professor", professorObtido);

                    response.sendRedirect("/aplicacaoMVC/professor/dashboard");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha na query para Logar");
            }
        }
    }

}
