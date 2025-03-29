<%@ page contentType="text/html" pageEncoding="UTF-8" import="java.util.List, entidade.Administrador" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="#">
    <title>School</title>
    <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css" rel="stylesheet">
</head>
<body class="d-flex h-100 text-center bg-dark text-light">
    <div class="container">
        <jsp:include page="../../comum/menuAdm.jsp" />
        <div class="row justify-content-center">
            <div class="col-12 col-md-8 col-lg-6 mt-4">
                <h3 class="text-center">Aprove um administrador</h3>

                <% String msgError = (String) request.getAttribute("msgError");
                    if ((msgError != null) && (!msgError.isEmpty())) { %>
                    <div class="alert alert-danger" role="alert">
                        <%= msgError %>
                    </div>
                <% } %>
                <% String msgSuccess = (String) request.getAttribute("msgSuccess");
                    if ((msgSuccess != null) && (!msgSuccess.isEmpty())) { %>
                    <div class="alert alert-success" role="alert">
                        <%= msgSuccess %>
                    </div>
                <% } %>

                <form action="/aplicacaoMVC/admin/aprovaAdm" method="POST">
                    <!-- Tabela de Administradores -->
                    <%
                        // Recuperando a lista de administradores
                        List<Administrador> administradores = (List<Administrador>) request.getAttribute("administradores");

                        // Verifica se a lista é nula ou vazia
                        if (administradores == null || administradores.isEmpty()) {
                    %>
                        <div class="alert alert-info text-center mt-3" role="alert">
                            Não tem nenhum administrador para aprovar.
                        </div>
                        <div class="text-center mt-3">
                            <a href="/aplicacaoMVC/home" class="btn btn-primary">Voltar</a>
                        </div>
                    <%
                        } else {
                    %>
                    <div class="table-responsive-sm">
                        <table class="table table-striped table-dark table-bordered table-hover mt-3">
                            <thead>
                                <tr>
                                    <th>Nome</th>
                                    <th>CPF</th>
                                    <th>Aprovar</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    // Iterando sobre a lista de administradores para criar as linhas da tabela
                                    for (Administrador admin : administradores) {
                                %>
                                    <tr>
                                        <td><%= admin.getNome() %></td>
                                        <td><%= admin.getCpf() %></td>
                                        <td>
                                            <div class="form-check form-switch d-flex justify-content-center">
                                                <input class="form-check-input" type="checkbox" name="administradores" value="<%= admin.getId() %>" id="admin<%= admin.getId() %>">
                                            </div>
                                        </td>
                                    </tr>
                                <%
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>

                        <div class="d-flex justify-content-between">
                            <a href="/aplicacaoMVC/admin/dashboard" class="btn btn-secondary">Voltar</a>
                            <input type="submit" value="Enviar" class="btn btn-primary">
                        </div>
                    <%
                        }
                    %>
                </form>
            </div>
        </div>
    </div>
    <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>
