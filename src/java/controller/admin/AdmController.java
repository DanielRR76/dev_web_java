package controller.admin;

import entidade.Administrador;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AdministradorDAO;

@WebServlet(name = "AdmController", urlPatterns = {"/admin/AdmController"})
public class AdmController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String acao = (String) request.getParameter("acao");
        Administrador administrador = new Administrador();
        AdministradorDAO administradorDAO = new AdministradorDAO();
        RequestDispatcher rd;
        switch (acao) {
            case "Listar":
                ArrayList<Administrador> listaAdministradores = administradorDAO.ListaDeAdministrador();
                request.setAttribute("administradores", listaAdministradores);

                rd = request.getRequestDispatcher("/views/admin/listar/listarAdm.jsp");
                rd.forward(request, response);

                break;
            case "Alterar":
            case "Excluir":
                int id = Integer.parseInt(request.getParameter("id"));
                administrador = administradorDAO.getAdministrador(id);
                request.setAttribute("administrador", administrador);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/form/formAdm.jsp");
                rd.forward(request, response);
                break;
            case "Incluir":
                request.setAttribute("administrador", administrador);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/form/formAdm.jsp");
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
        String endereco_user = request.getParameter("endereco");
        String senha_user = request.getParameter("senha");
        String senha_confirm_user = request.getParameter("senha2");

        if ((cpf_user.isEmpty() || senha_user.isEmpty() || senha_confirm_user.isEmpty() || nome_user.isEmpty() || endereco_user.isEmpty()) && !btEnviar.equals("Excluir")) {
            Administrador administrador = new Administrador();
            switch (btEnviar) {
                case "Alterar":
                case "Excluir":
                    try {
                        AdministradorDAO administradorDAO = new AdministradorDAO();
                        administrador = administradorDAO.getAdministrador(id);

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha em uma query para cadastro de Administrador");
                }
                break;
            }

            request.setAttribute("administrador", administrador);
            request.setAttribute("acao", btEnviar);

            request.setAttribute("msgError", "É necessário preencher todos os campos");

            rd = request.getRequestDispatcher("/views/admin/form/formAdm.jsp");
            rd.forward(request, response);

        } else if (!senha_user.equals(senha_confirm_user) && !btEnviar.equals("Excluir")) {
            Administrador administrador = new Administrador();
            switch (btEnviar) {
                case "Alterar":
                    try {
                        AdministradorDAO administradorDAO = new AdministradorDAO();
                        administrador = administradorDAO.getAdministrador(id);

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        throw new RuntimeException("Falha em uma query para cadastro de Administrador");
                    }
                    request.setAttribute("msgError", "As senhas devem ser iguais");
                    break;
                case "Incluir":
                    request.setAttribute("msgError", "As senhas devem ser iguais");
                    break;
            }

            request.setAttribute("administrador", administrador);
            request.setAttribute("acao", btEnviar);

            rd = request.getRequestDispatcher("/views/admin/form/formAdm.jsp");
            rd.forward(request, response);
        } else {

            Administrador administrador = new Administrador(nome_user, cpf_user, senha_user, endereco_user);
            AdministradorDAO AdministradorDAO = new AdministradorDAO();
            try {
                switch (btEnviar) {
                    case "Incluir":
                        try {
                            Administrador administrador2 = AdministradorDAO.getAdministradorByCPF(cpf_user);
                            if (administrador2.getCpf() != null && !administrador2.getCpf().isEmpty()) {
                                request.setAttribute("msgError", "Usuário ja cadastrado com esse CPF");
                                request.setAttribute("administrador", administrador);
                                request.setAttribute("acao", btEnviar);
                                rd = request.getRequestDispatcher("/views/admin/form/formAdm.jsp");
                                rd.forward(request, response);
                            }
                            else {
                                AdministradorDAO.Inserir(administrador);
                                request.setAttribute("msgOperacaoRealizada", "Usuário cadastrado com sucesso!");
                                request.setAttribute("link", "/aplicacaoMVC/admin/AdmController?acao=Listar");
                                rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                                rd.forward(request, response);
                            }
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }

                        break;
                    case "Alterar":
                        try {
                            Administrador administrador2 = AdministradorDAO.getAdministrador(id);
                            administrador2.setId(id);
                            administrador2.setNome(nome_user);
                            administrador2.setCpf(cpf_user);
                            administrador2.setEndereco(endereco_user);
                            administrador2.setSenha(senha_user);
                            AdministradorDAO.Alterar(administrador2);
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                        request.setAttribute("msgOperacaoRealizada", "Usuário atualizado com sucesso!");
                        request.setAttribute("link", "/aplicacaoMVC/admin/AdmController?acao=Listar");
                        rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                        rd.forward(request, response);
                        break;
                    case "Excluir":
                        try {
                            AdministradorDAO.Excluir(id);
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                        request.setAttribute("msgOperacaoRealizada", "Usuário excluido com sucesso!");
                        request.setAttribute("link", "/aplicacaoMVC/admin/AdmController?acao=Listar");
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
