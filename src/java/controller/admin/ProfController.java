package controller.admin;

import entidade.Professor;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProfessorDAO;

@WebServlet(name = "ProfController", urlPatterns = {"/admin/ProfController"})
public class ProfController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String acao = (String) request.getParameter("acao");
        Professor professor = new Professor();
        ProfessorDAO professorDAO = new ProfessorDAO();
        RequestDispatcher rd;
        switch (acao) {
            case "Listar":
                ArrayList<Professor> listaProfessores = professorDAO.getAll();
                request.setAttribute("professores", listaProfessores);

                rd = request.getRequestDispatcher("/views/admin/listar/listarProf.jsp");
                rd.forward(request, response);

                break;
            case "Alterar":
            case "Excluir":
                int id = Integer.parseInt(request.getParameter("id"));
                professor = professorDAO.get(id);
                request.setAttribute("professor", professor);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/form/formProf.jsp");
                rd.forward(request, response);
                break;
            case "Incluir":
                request.setAttribute("professor", professor);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/form/formProf.jsp");
                rd.forward(request, response);
                break;
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        RequestDispatcher rd;
        int id = Integer.parseInt(request.getParameter("id"));
        String btEnviar = request.getParameter("btEnviar");
        String nome_user = request.getParameter("nome");
        String cpf_user = request.getParameter("cpf");
        String email_user = request.getParameter("email");
        String senha_user = request.getParameter("senha");
        String senha_confirm_user = request.getParameter("senha2");

        if ((cpf_user.isEmpty() || senha_user.isEmpty() || senha_confirm_user.isEmpty() || nome_user.isEmpty() || email_user.isEmpty()) && !btEnviar.equals("Excluir")) {
            Professor professor = new Professor();
            switch (btEnviar) {
                case "Alterar":
                case "Excluir":
                    try {
                        ProfessorDAO professorDAO = new ProfessorDAO();
                        professor = professorDAO.get(id);

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha em uma query para cadastro de Professor");
                }
                break;
            }

            request.setAttribute("professor", professor);
            request.setAttribute("acao", btEnviar);

            request.setAttribute("msgError", "É necessário preencher todos os campos");

            rd = request.getRequestDispatcher("/views/admin/form/formProf.jsp");
            rd.forward(request, response);

        } else if (!senha_user.equals(senha_confirm_user) && !btEnviar.equals("Excluir")) {
            Professor professor = new Professor();
            switch (btEnviar) {
                case "Alterar":
                    try {
                        ProfessorDAO professorDAO = new ProfessorDAO();
                        professor = professorDAO.get(id);

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        throw new RuntimeException("Falha em uma query para cadastro de Professor");
                    }
                    request.setAttribute("msgError", "As senhas devem ser iguais");
                    break;
                case "Incluir":
                    request.setAttribute("msgError", "As senhas devem ser iguais");
                    break;
            }

            request.setAttribute("professor", professor);
            request.setAttribute("acao", btEnviar);

            rd = request.getRequestDispatcher("/views/admin/form/formProf.jsp");
            rd.forward(request, response);
        } else {

            Professor professor = new Professor(nome_user, email_user, cpf_user, senha_user);
            ProfessorDAO professorDAO = new ProfessorDAO();
            try {
                switch (btEnviar) {
                    case "Incluir":
                        try {
                            Professor professor2 = professorDAO.getByCPF(cpf_user);
                            if (professor2.getCpf() != null && !professor2.getCpf().isEmpty()) {
                                request.setAttribute("msgError", "Usuário ja cadastrado com esse CPF");
                                request.setAttribute("professor", professor);
                                request.setAttribute("acao", btEnviar);
                                rd = request.getRequestDispatcher("/views/admin/form/formProf.jsp");
                                rd.forward(request, response);
                            }
                            else {
                                professorDAO.insert(professor);
                                request.setAttribute("msgOperacaoRealizada", "Usuário cadastrado com sucesso!");
                                request.setAttribute("link", "/aplicacaoMVC/admin/ProfController?acao=Listar");
                                rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                                rd.forward(request, response);
                            }
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }

                        break;
                    case "Alterar":
                        try {
                            Professor professor2 = professorDAO.get(id);
                            professor2.setId(id);
                            professor2.setNome(nome_user);
                            professor2.setCpf(cpf_user);
                            professor2.setEmail(email_user);
                            professor2.setSenha(senha_user);
                            professorDAO.update(professor2);
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                        request.setAttribute("msgOperacaoRealizada", "Usuário atualizado com sucesso!");
                        request.setAttribute("link", "/aplicacaoMVC/admin/ProfController?acao=Listar");
                        rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                        rd.forward(request, response);
                        break;
                    case "Excluir":
                        try {
                            professorDAO.delete(id);
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                        request.setAttribute("msgOperacaoRealizada", "Usuário excluido com sucesso!");
                        request.setAttribute("link", "/aplicacaoMVC/admin/ProfController?acao=Listar");
                        rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                        rd.forward(request, response);
                        break;
                }

            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException(ex.getMessage());
            }
        }
    }

}
