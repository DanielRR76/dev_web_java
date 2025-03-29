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
                <h2 class="text-center">Lista de Administradores</h2>

                    <%
                        // Recuperando a lista de administradores
                        List<Administrador> administradores = (List<Administrador>) request.getAttribute("administradores");

                        // Verifica se a lista é nula ou vazia
                        if (administradores == null || administradores.isEmpty()) {
                    %>
                        <div class="alert alert-info text-center mt-3" role="alert">
                            Não tem nenhum administrador.
                        </div>
                        <div class="text-center mt-3">
                            <a href="/aplicacaoMVC/admin/dashboard" class="btn btn-primary">Voltar</a>
                        </div>
                    <%
                        } else {
                    %>
                    <div class="table-responsive-sm overflow-auto" style="max-height: 500px;">
                        <table class="table table-striped table-dark table-bordered table-hover mt-3">
                            <thead>
                                <tr>
                                    <th scope="col">Id</th>
                                    <th scope="col">Nome</th>
                                    <th scope="col">CPF</th>
                                    <th scope="col">Acões</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    // Iterando sobre a lista de administradores para criar as linhas da tabela
                                    for (Administrador admin : administradores) {
                                %>
                                    <tr>
                                        <td><%= admin.getId() %></td>
                                        <td><%= admin.getNome() %></td>
                                        <td><%= admin.getCpf() %></td>
                                        <td>
                                                <a href="/aplicacaoMVC/admin/AdmController?acao=Alterar&id=<%=admin.getId()%>" class="btn btn-warning">Alterar</a>
                                                <a href="/aplicacaoMVC/admin/AdmController?acao=Excluir&id=<%=admin.getId()%>" class="btn btn-danger">Excluir</a>
                                        </td>
                                    </tr>
                                <%
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>

                        <div class="d-flex justify-content-between mt-3">
                            <a href="/aplicacaoMVC/admin/dashboard" class=" mb-2 btn btn-secondary">Voltar</a>
                            <a href="/aplicacaoMVC/admin/AdmController?acao=Incluir" class="mb-2 btn btn-primary">Incluir</a>
                        </div>
                    <%
                        }
                    %>
            </div>
        </div>
    </div>
    <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>
