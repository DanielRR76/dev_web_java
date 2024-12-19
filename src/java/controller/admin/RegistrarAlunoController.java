package controller.admin;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidade.Aluno;
import model.AlunoDAO;

@WebServlet(name = "RegistrarAlunoController", urlPatterns = {"/admin/registrarAluno"})
public class RegistrarAlunoController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/views/admin/registro/formAlunoRegistro.jsp");
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
        String email_user = request.getParameter("email");
        String celular_user = request.getParameter("celular");
        String endereco_user = request.getParameter("endereco");
        String cidade_user = request.getParameter("cidade");
        String bairro_user = request.getParameter("bairro");
        String cep_user = request.getParameter("cep");
        String senha_user = request.getParameter("senha");
        String senha_confirm_user = request.getParameter("senha2");
        if (cpf_user.isEmpty() || senha_user.isEmpty() || senha_confirm_user.isEmpty() || nome_user.isEmpty() || email_user.isEmpty() || celular_user.isEmpty()) {
            // dados não foram preenchidos retorna ao formulário
            request.setAttribute("msgError", "Os campos 'Nome', 'CPF', 'Senha', 'Redigite a senha', 'E-mail' e 'Celular' devem ser preenchidos");
            rd = request.getRequestDispatcher("/views/admin/registro/formAlunoRegistro.jsp");
            rd.forward(request, response);

        } else if (!senha_user.equals(senha_confirm_user)) {
            request.setAttribute("msgError", "As senhas devem ser iguais");
            rd = request.getRequestDispatcher("/views/admin/registro/formAlunoRegistro.jsp");
            rd.forward(request, response);
        } else {
            Aluno aluno = new Aluno(nome_user, email_user, celular_user, cpf_user, senha_user, endereco_user, cidade_user, bairro_user, cep_user);
            AlunoDAO AlunoDAO = new AlunoDAO();
            try {
                Aluno aluno2 = AlunoDAO.getByCPF(cpf_user);
                if (aluno2.getCpf() != null && !aluno2.getCpf().isEmpty()) {
                    request.setAttribute("msgError", "Usuário ja cadastrado");
                    rd = request.getRequestDispatcher("/views/admin/registro/formAlunoRegistro.jsp");
                    rd.forward(request, response);
                    
                }
                else {
                    AlunoDAO.insert(aluno);
                    request.setAttribute("msgSuccess", "Usuário cadastrado com sucesso!");
                    rd = request.getRequestDispatcher("/views/admin/registro/formAlunoRegistro.jsp");
                    rd.forward(request, response);
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha na query para Logar");
            }
        }

    }
}
