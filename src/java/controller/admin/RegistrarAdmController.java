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

@WebServlet(name = "RegistrarAdmController", urlPatterns = {"/admin/registrarAdm"})

public class RegistrarAdmController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/views/admin/registro/formAdmRegistro.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        RequestDispatcher rd;
        String nome_user = request.getParameter("nome");
        String cpf_user = request.getParameter("cpf");
        String endereco_user = request.getParameter("endereco");
        String senha_user = request.getParameter("senha");
        String senha_confirm_user = request.getParameter("senha2");
        if (cpf_user.isEmpty() || senha_user.isEmpty() || senha_confirm_user.isEmpty() || nome_user.isEmpty() || endereco_user.isEmpty()) {
            // dados não foram preenchidos retorna ao formulário
            request.setAttribute("msgError", "Todos os campos são obrigatórios");
            rd = request.getRequestDispatcher("/views/admin/registro/formAdmRegistro.jsp");
            rd.forward(request, response);

        } else if (!senha_user.equals(senha_confirm_user)) {
            request.setAttribute("msgError", "As senhas devem ser iguais");
            rd = request.getRequestDispatcher("/views/admin/registro/formAdmRegistro.jsp");
            rd.forward(request, response);
        } else {
            Administrador administrador = new Administrador(nome_user, cpf_user, senha_user, endereco_user);
            AdministradorDAO AdministradorDAO = new AdministradorDAO();
            try {
                Administrador administrador2 = AdministradorDAO.getAdministradorByCPF(cpf_user);
                if (administrador2.getCpf() != null && !administrador2.getCpf().isEmpty()) {
                    request.setAttribute("msgError", "Usuário ja cadastrado");
                    rd = request.getRequestDispatcher("/views/admin/registro/formAdmRegistro.jsp");
                    rd.forward(request, response);
                }
                else {
                    AdministradorDAO.Inserir(administrador);
                    request.setAttribute("msgSuccess", "Usuário cadastrado com sucesso!");
                    rd = request.getRequestDispatcher("/views/admin/registro/formAdmRegistro.jsp");
                    rd.forward(request, response);
                }
                
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha na query para Logar");
            }
        }

    }
}
