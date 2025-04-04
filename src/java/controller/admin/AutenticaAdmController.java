package controller.admin;

import entidade.Administrador;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.AdministradorDAO;

@WebServlet(name = "AutenticaAdmController", urlPatterns = { "/AutenticaAdmController" })
public class AutenticaAdmController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/views/autenticacao/formLoginAdm.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        RequestDispatcher rd;
        // pegando os parâmetros do request
        String cpf_user = request.getParameter("cpf");
        String senha_user = request.getParameter("senha");
        if (cpf_user.isEmpty() || senha_user.isEmpty()) {
            // dados não foram preenchidos retorna ao formulário
            request.setAttribute("msgError", "Preencha todos os campos");
            rd = request.getRequestDispatcher("/views/autenticacao/formLoginAdm.jsp");
            rd.forward(request, response);

        } else {
            Administrador administradorObtido;
            Administrador administrador = new Administrador(cpf_user, senha_user);
            AdministradorDAO AdministradorDAO = new AdministradorDAO();
            try {
                administradorObtido = AdministradorDAO.getAdministradorByCPF(administrador.getCpf());
                if(administradorObtido.getId() == 0) {
                    request.setAttribute("msgError", "Usuário não cadastrado");
                    rd = request.getRequestDispatcher("/views/autenticacao/formLoginAdm.jsp");
                    rd.forward(request, response);
                }
                else if(!administradorObtido.getSenha().equals(administrador.getSenha())) {
                    request.setAttribute("msgError", "Senha incorreta");
                    rd = request.getRequestDispatcher("/views/autenticacao/formLoginAdm.jsp");
                    rd.forward(request, response);
                }
                else if(administradorObtido.getAprovado().equals("n")) {
                    request.setAttribute("msgError", "Usuário ainda não foi aprovado");
                    rd = request.getRequestDispatcher("/views/autenticacao/formLoginAdm.jsp");
                    rd.forward(request, response);
                }
                else {
                    HttpSession session = request.getSession();
                    session.setAttribute("administrador", administradorObtido);

                    response.sendRedirect("/aplicacaoMVC/admin/dashboard");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha na query para Logar");
            }
        }
    }

}
