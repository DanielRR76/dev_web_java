<%@page contentType="text/html" pageEncoding="UTF-8" import="entidade.Disciplina"%>
<!DOCTYPE html>
<html lang="pt-br">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="shortcut icon" href="#">
        <title>School</title>
        <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"  rel="stylesheet">
    </head>
    <body class="d-flex h-100 bg-dark text-light">
        <div class="container">
            <jsp:include page="../../comum/menuAdm.jsp" />
            <div class="row justify-content-center">
                <div class="col-12 col-md-8 col-lg-6 mt-5">

                    <div class=" text-center">
                        <%
                        Disciplina disciplina = (Disciplina) request.getAttribute("disciplina");
                            String acao = (String) request.getAttribute("acao");
                            switch (acao) {
                                case "Incluir":
                                    out.println("<h3>Incluir Disciplina</h3>");
                                    break;
                                case "Alterar":
                                    out.println("<h3>Alterar Disciplina</h3>");
                                    break;
                                case "Excluir":
                                    out.println("<h3>Excluir Disciplina</h3>");
                                    break;
                            }
                        %>
                    </div>

                    <%
                        String msgError = (String) request.getAttribute("msgError");
                        if ((msgError != null) && (!msgError.isEmpty())) {%>
                    <div class="alert alert-danger" role="alert">
                        <%= msgError%>
                    </div>
                    <% }%>
                    <%
                        String msgSuccess = (String) request.getAttribute("msgSuccess");
                        if ((msgSuccess != null) && (!msgSuccess.isEmpty())) {%>
                    <div class="alert alert-success" role="alert">
                        <%= msgSuccess%>
                    </div>
                    <% }%>
                    <form action="/aplicacaoMVC/admin/DisciplinaController" method="POST">
                        <input type="hidden" name="id" value="<%=disciplina.getId()%>" class="form-control">
                        <div class="mb-3">
                            <label for="nome" class="form-label">Nome</label>
                            <input type="text" name="nome" class="form-control" <%= acao.equals("Excluir") ? "Readonly" : "" %> value="<%= disciplina != null ? disciplina.getNome() : "" %>" placeholder="Digite o nome" maxlength="50" required>
                        </div>
                        <div class="mb-3">
                            <label for="requisito" class="form-label">Requisito</label>
                            <input type="text" name="requisito" class="form-control" <%= acao.equals("Excluir") ? "Readonly" : "" %> value="<%= disciplina != null ? disciplina.getRequisito() : "" %>" placeholder="Digite o requisito" maxlength="255">
                        </div>
                        <div class="mb-3">
                            <label for="ementa" class="form-label">Ementa</label>
                            <input type="text" name="ementa" class="form-control" <%= acao.equals("Excluir") ? "Readonly" : "" %> value="<%= disciplina != null ? disciplina.getEmenta() : "" %>" placeholder="Digite a ementa" maxlength="255">
                        </div>
                        <div class="mb-3">
                            <label for="cargaHoraria" class="form-label">Carga Horária</label>
                            <input type="number" name="cargaHoraria" class="form-control" <%= acao.equals("Excluir") ? "Readonly" : "" %> value="<%= disciplina != null ? disciplina.getCargaHoraria() : "" %>" placeholder="Digite a carga horária" min="0" max="65">
                        </div>
                        <div class="d-flex justify-content-between">
                            <a href="/aplicacaoMVC/admin/DisciplinaController?acao=Listar" class="btn btn-secondary">Voltar</a>
                            <input type="submit" name="btEnviar" value="<%=acao%>" class="btn btn-primary">
                        </div>
                    </form>
                </div>
            </div>

        </div>
        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>

