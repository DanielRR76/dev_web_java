package controller.aluno;

import entidade.Aluno;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.AlunoDAO;

@WebServlet(name = "AutenticaAlunoController", urlPatterns = { "/AutenticaAlunoController" })
public class AutenticaAlunoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/views/autenticacao/formLoginAluno.jsp");
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
            rd = request.getRequestDispatcher("/views/autenticacao/formLoginAluno.jsp");
            rd.forward(request, response);

        } else {
            Aluno alunoObtido;
            Aluno aluno = new Aluno(cpf_user, senha_user);
            AlunoDAO AlunoDAO = new AlunoDAO();
            try {
                alunoObtido = AlunoDAO.getByCPF(aluno.getCpf());
                if (alunoObtido.getId() == 0) {
                    request.setAttribute("msgError", "Usuário nao cadastrado");
                    rd = request.getRequestDispatcher("/views/autenticacao/formLoginAluno.jsp");
                    rd.forward(request, response);

                }
                else if(!alunoObtido.getSenha().equals(aluno.getSenha())) {
                    request.setAttribute("msgError", "Senha incorreta");
                    rd = request.getRequestDispatcher("/views/autenticacao/formLoginAluno.jsp");
                    rd.forward(request, response);
                }
                else {
                    HttpSession session = request.getSession();
                    session.setAttribute("aluno", alunoObtido);

                    response.sendRedirect("/aplicacaoMVC/aluno/dashboard");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha na query para Logar");
            }
        }
    }

}
