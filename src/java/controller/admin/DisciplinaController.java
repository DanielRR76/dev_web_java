package controller.admin;

import entidade.Administrador;
import entidade.Disciplina;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.AdministradorDAO;
import model.DisciplinaDAO;
@WebServlet(name = "DisciplinaController", urlPatterns = {"/admin/DisciplinaController"})
public class DisciplinaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String acao = (String) request.getParameter("acao");
        Disciplina disciplina = new Disciplina();
        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
        RequestDispatcher rd;
        switch (acao) {
            case "Listar":
                ArrayList<Disciplina> listaDisciplinas = disciplinaDAO.getAll();
                request.setAttribute("disciplinas", listaDisciplinas);

                rd = request.getRequestDispatcher("/views/admin/listar/listarDisciplina.jsp");
                rd.forward(request, response);

                break;
            case "Alterar":
            case "Excluir":
                int id = Integer.parseInt(request.getParameter("id"));
                disciplina = disciplinaDAO.get(id);
                request.setAttribute("disciplina", disciplina);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/form/formDisciplina.jsp");
                rd.forward(request, response);
                break;
            case "Incluir":
                request.setAttribute("disciplina", disciplina);
                request.setAttribute("msgError", "");
                request.setAttribute("acao", acao);

                rd = request.getRequestDispatcher("/views/admin/form/formDisciplina.jsp");
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
        String nome_disciplina = request.getParameter("nome");
        nome_disciplina = nome_disciplina.toUpperCase();
        String requisito_disciplina = request.getParameter("requisito");
        String ementa_disciplina = request.getParameter("ementa");
        String cargaHoraria_disciplina = request.getParameter("cargaHoraria");
        int carga_horaria_int;

        if (nome_disciplina.isEmpty()) {
            Disciplina disciplina = new Disciplina();
            switch (btEnviar) {
                case "Alterar":
                case "Excluir":
                    try {
                        DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
                        disciplina = disciplinaDAO.get(id);

                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    throw new RuntimeException("Falha em uma query para cadastro de Administrador");
                }
                break;
            }

            request.setAttribute("disciplina", disciplina);
            request.setAttribute("acao", btEnviar);

            request.setAttribute("msgError", "É necessário preencher todos os campos");

            rd = request.getRequestDispatcher("/views/admin/form/formDisciplina.jsp");
            rd.forward(request, response);

        } else {
            if (cargaHoraria_disciplina.isEmpty()) {
                carga_horaria_int = 0;
            } else {
                carga_horaria_int = Integer.parseInt(cargaHoraria_disciplina);
            }
            Disciplina disciplina = new Disciplina(nome_disciplina,requisito_disciplina, ementa_disciplina, carga_horaria_int);
            DisciplinaDAO disciplinaDAO = new DisciplinaDAO();
            try {
                switch (btEnviar) {
                    case "Incluir":
                        try {
                            Disciplina disciplina2 = disciplinaDAO.getByNome(nome_disciplina);
                            if (disciplina2.getNome() != null && !disciplina2.getNome().isEmpty()) {
                                request.setAttribute("msgError", "Disciplina ja cadastrada com esse nome");
                                request.setAttribute("disciplina", disciplina);
                                request.setAttribute("acao", btEnviar);
                                rd = request.getRequestDispatcher("/views/admin/form/formDisciplina.jsp");
                                rd.forward(request, response);
                            }
                            else {
                                disciplinaDAO.insert(disciplina);
                                request.setAttribute("msgOperacaoRealizada", "Disciplina cadastrada com sucesso!");
                                request.setAttribute("link", "/aplicacaoMVC/admin/DisciplinaController?acao=Listar");
                                rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                                rd.forward(request, response);
                            }
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }

                        break;
                    case "Alterar":
                        try {
                            Disciplina disciplina2 = disciplinaDAO.get(id);
                            disciplina2.setId(id);
                            disciplina2.setNome(nome_disciplina);
                            disciplina2.setRequisito(requisito_disciplina);
                            disciplina2.setEmenta(ementa_disciplina);
                            disciplina2.setCargaHoraria(carga_horaria_int);
                            disciplinaDAO.update(disciplina2);
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                        request.setAttribute("msgOperacaoRealizada", "Disciplina atualizada com sucesso!");
                        request.setAttribute("link", "/aplicacaoMVC/admin/DisciplinaController?acao=Listar");
                        rd = request.getRequestDispatcher("/views/comum/showMessage.jsp");
                        rd.forward(request, response);
                        break;
                    case "Excluir":
                        try {
                            disciplinaDAO.delete(id);
                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                        }
                        request.setAttribute("msgOperacaoRealizada", "Disciplina excluida com sucesso!");
                        request.setAttribute("link", "/aplicacaoMVC/admin/DisciplinaController?acao=Listar");
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
