<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
    <body>
        <div class="container">
            <jsp:include page="../../comum/menuAdm.jsp" />
            <div class="row justify-content-center">
                <div class="col-12 col-md-8 col-lg-6 mt-5">

                    <h3 class="text-center">Cadastre uma Disciplina</h3>
    
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
                    <form action="/aplicacaoMVC/admin/registrarDisciplina" method="POST">
                        <div class="mb-3">
                            <label for="nome" class="form-label">Nome</label>
                            <input type="text" name="nome" class="form-control" placeholder="Digite o nome" maxlength="50">
                        </div>
                        <div class="mb-3">
                            <label for="requisito" class="form-label">Requisito</label>
                            <input type="text" name="requisito" class="form-control" placeholder="Digite o requisito" maxlength="255">
                        </div>
                        <div class="mb-3">
                            <label for="ementa" class="form-label">Ementa</label>
                            <input type="text" name="ementa" class="form-control" placeholder="Digite a ementa" maxlength="255">
                        </div>
                        <div class="mb-3">
                            <label for="cargaHoraria" class="form-label">Carga Horária</label>
                            <input type="number" name="cargaHoraria" class="form-control" placeholder="Digite a carga horária" min="0" max="65">
                        </div>
                        <div class="d-flex justify-content-between">
                            <a href="/aplicacaoMVC/admin/cadastro" class="btn btn-secondary">Voltar</a>
                            <input type="submit" value="Enviar" class="btn btn-primary">
                        </div>
                    </form>
                </div>
            </div>
            
        </div>
        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>

