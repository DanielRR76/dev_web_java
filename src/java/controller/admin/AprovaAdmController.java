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
import javax.servlet.http.HttpSession;
import model.AdministradorDAO;

@WebServlet(name = "AprovaAdmController", urlPatterns = {"/admin/aprovaAdm"})
public class AprovaAdmController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        RequestDispatcher rd;

        ArrayList<Administrador> administradores = new ArrayList<Administrador>();
        AdministradorDAO AdministradorDAO = new AdministradorDAO();
        administradores = AdministradorDAO.getAllAdministradorNaoAprovado();
        request.setAttribute("administradores", administradores);
        rd = request.getRequestDispatcher("/views/admin/aprovacao/aprovacao.jsp");
        rd.forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        RequestDispatcher rd;

        ArrayList<Administrador> administradores2 = new ArrayList<Administrador>();
        AdministradorDAO AdministradorDAO = new AdministradorDAO();

        String [] administradores = request.getParameterValues("administradores");
        if (administradores == null || administradores.length == 0) {
            request.setAttribute("msgError", "Por favor, selecione pelo menos um administrador.");
            administradores2 = AdministradorDAO.getAllAdministradorNaoAprovado();
            request.setAttribute("administradores", administradores2);
            request.getRequestDispatcher("/views/admin/aprovacao/aprovacao.jsp").forward(request, response);
        }
        else {
            for (String id : administradores) {
                try {
                    Administrador adm2 = AdministradorDAO.getAdministrador(Integer.parseInt(id));
                    adm2.setAprovado("s");
                    AdministradorDAO.Alterar(adm2);
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }
            }
            if(administradores.length > 1) {
                request.setAttribute("msgSuccess", "Administradores aprovados com sucesso!");
            }
            else {
                request.setAttribute("msgSuccess", "Administrador aprovado com sucesso!");
            }
            administradores2 = AdministradorDAO.getAllAdministradorNaoAprovado();
            request.setAttribute("administradores", administradores2);
            rd = request.getRequestDispatcher("/views/admin/aprovacao/aprovacao.jsp");
            rd.forward(request, response);
            
        }
    }

}
