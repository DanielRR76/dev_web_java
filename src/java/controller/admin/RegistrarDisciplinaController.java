package controller.admin;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entidade.Disciplina;
import model.DisciplinaDAO;

@WebServlet(name = "RegistrarDisciplinaController", urlPatterns = {"/admin/registrarDisciplina"})
public class RegistrarDisciplinaController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        RequestDispatcher rd;
        rd = request.getRequestDispatcher("/views/admin/registro/formDisiciplinaRegistro.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        RequestDispatcher rd;
        String nome_disciplina = request.getParameter("nome");
        nome_disciplina = nome_disciplina.toUpperCase();
        String requisito_disciplina = request.getParameter("requisito");
        String ementa_disciplina = request.getParameter("ementa");
        int carga_horaria = Integer.parseInt(request.getParameter("cargaHoraria"));
        if (nome_disciplina.isEmpty()) {
            // dados não foram preenchidos retorna ao formulário
            request.setAttribute("msgError", "O nome da disciplina é obrigatorio");
            rd = request.getRequestDispatcher("/views/admin/registro/formDisiciplinaRegistro.jsp");
            rd.forward(request, response);

        } else {
            Disciplina disciplina = new Disciplina(nome_disciplina, requisito_disciplina, ementa_disciplina, carga_horaria);
            DisciplinaDAO DisciplinaDAO = new DisciplinaDAO();
            try {
                Disciplina disciplina2 = DisciplinaDAO.getByNome(nome_disciplina);
                if (disciplina2.getNome() != null && !disciplina2.getNome().isEmpty()) {
                    request.setAttribute("msgError", "Disciplina ja cadastrada");
                    rd = request.getRequestDispatcher("/views/admin/registro/formDisiciplinaRegistro.jsp");
                    rd.forward(request, response);
                    
                }
                else {
                    DisciplinaDAO.insert(disciplina);
                    request.setAttribute("msgSuccess", "Disciplina cadastrada com sucesso!");
                    rd = request.getRequestDispatcher("/views/admin/registro/formDisiciplinaRegistro.jsp");
                    rd.forward(request, response);
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                throw new RuntimeException("Falha na query para Logar");
            }
        }

    }
}
