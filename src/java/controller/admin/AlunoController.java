package controller.admin;

import entidade.Aluno;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AlunoDAO;

@WebServlet(name = "AlunoController", urlPatterns = {"/admin/AlunoController"})
public class AlunoController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String acao = (String) request.getParameter("acao");
        Aluno aluno = new Aluno();
        AlunoDAO alunoDAO = new AlunoDAO();
        RequestDispatcher rd;
        switch (acao) {
            case "Listar":
                ArrayList<Aluno> listaAlunos = alunoDAO.getAll();
                request.setAttribute("alunos", listaAlunos);

                rd = request.getRequestDispatcher("/views/admin/listar/listarAluno.jsp");
                rd.forward(request, response);

                break;
            case "Alterar":
            case "Excluir":
                int id = Integer.parseInt(request.getParameter("id"));
                aluno = alunoDAO.get(id);
                request.setAttribute("aluno", aluno);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/form/formAluno.jsp");
                rd.forward(request, response);
                break;
            case "Incluir":
                request.setAttribute("aluno", aluno);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/form/formAluno.jsp");
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
        String celular_user = request.getParameter("celular");
        String endereco_user = request.getParameter("endereco");
        String cidade_user = request.getParameter("cidade");
        String bairro_user = request.getParameter("bairro");
        String cep_user = request.getParameter("cep");
        String senha_user = request.getParameter("senha");
        String senha_confirm_user = request.getParameter("senha2");

        if ((cpf_user.isEmpty() || senha_user.isEmpty() || senha_confirm_user.isEmpty() || nome_user.isEmpty()
                || email_user.isEmpty() || celular_user.isEmpty()) && !btEnviar.equals("Excluir")) {
            Aluno aluno = new Aluno();
            switch (btEnviar) {
                case "Alterar":
                case "Excluir":
                    try {
                        AlunoDAO alunoDAO = new AlunoDAO();
                        aluno = alunoDAO.get(id);

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha em uma query para cadastro de Aluno");
                }
                break;
            }

            request.setAttribute("aluno", aluno);
            request.setAttribute("acao", btEnviar);

            request.setAttribute("msgError", "É necessário preencher os campos: Nome, CPF, Email, Celular, Senha e Redigite a senha");

            rd = request.getRequestDispatcher("/views/admin/form/formAluno.jsp");
            rd.forward(request, response);

        } else if (!senha_user.equals(senha_confirm_user) && !btEnviar.equals("Excluir")) {
            Aluno aluno = new Aluno();
            switch (btEnviar) {
                case "Alterar":
                    try {
                        AlunoDAO alunoDAO = new AlunoDAO();
                        aluno = alunoDAO.get(id);

                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                        throw new RuntimeException("Falha em uma query para cadastro de Aluno");
                    }
                    request.setAttribute("msgError", "As senhas devem ser iguais");
                    break;
                case "Incluir":
                    request.setAttribute("msgError", "As senhas devem ser iguais");
                    break;
            }

            request.setAttribute("aluno", aluno);
            request.setAttribute("acao", btEnviar);

            rd = request.getRequestDispatcher("/views/admin/form/formAluno.jsp");
            rd.forward(request, response);
        } else {

            Aluno aluno = new Aluno(nome_user, email_user, celular_user, cpf_user, senha_user, endereco_user, cidade_user, bairro_user, cep_user);
            AlunoDAO alunoDAO = new AlunoDAO();
            try {
                switch (btEnviar) {
                    case "Incluir":
                        try {
                            Aluno aluno2 = alunoDAO.getByCPF(cpf_user);
                            if (aluno2.getCpf() != null && !aluno2.getCpf().isEmpty()) {
                                request.setAttribute("msgError", "Usuário ja cadastrado com esse CPF");
                                request.setAttribute("aluno", aluno);
                                request.setAttribute("acao", btEnviar);
                                rd = request.getRequestDispatcher("/views/admin/form/formAluno.jsp");
                                rd.forward(request, response);
                            }
                            else {
                                alunoDAO.insert(aluno);
                                request.setAttribute("msgOperacaoRealizada", "Usuário cadastrado com sucesso!");
                                request.setAttribute("link", "/aplicacaoMVC/admin/AlunoController?acao=Listar");
                                rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                                rd.forward(request, response);
                            }
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }

                        break;
                    case "Alterar":
                        try {
                            Aluno aluno2 = alunoDAO.get(id);
                            aluno2.setId(id);
                            aluno2.setNome(nome_user);
                            aluno2.setCpf(cpf_user);
                            aluno2.setEmail(email_user);
                            aluno2.setSenha(senha_user);
                            aluno2.setCelular(celular_user);
                            aluno2.setEndereco(endereco_user);
                            aluno2.setCidade(cidade_user);
                            aluno2.setBairro(bairro_user);
                            aluno2.setCep(cep_user);
                            alunoDAO.update(aluno2);
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                        request.setAttribute("msgOperacaoRealizada", "Usuário atualizado com sucesso!");
                        request.setAttribute("link", "/aplicacaoMVC/admin/AlunoController?acao=Listar");
                        rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                        rd.forward(request, response);
                        break;
                    case "Excluir":
                        try {
                            alunoDAO.delete(id);
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                        request.setAttribute("msgOperacaoRealizada", "Usuário excluido com sucesso!");
                        request.setAttribute("link", "/aplicacaoMVC/admin/AlunoController?acao=Listar");
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
