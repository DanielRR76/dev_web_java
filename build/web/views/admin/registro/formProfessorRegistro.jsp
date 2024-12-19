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
            <div class="col-sm-6 offset-3 mt-5">

                <h3>Cadastre um professor</h3>

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
                <form action="/aplicacaoMVC/admin/registrarProfessor" method="POST">
                    <div class="mb-3">
                        <label for="nome" class="form-label">Nome</label>
                        <input type="text" name="nome" class="form-control" placeholder="Digite o nome" maxlength="50">
                    </div>
                    <div class="mb-3">
                        <label for="cpf" class="form-label">CPF</label>
                        <input type="text" name="cpf" class="form-control" placeholder="999.999.999-99" maxlength="14">
                    </div>
                    <div class="mb-3">
                        <label for="email" class="form-label">Email</label>
                        <input type="email" name="email" class="form-control" placeholder="Digite o email" maxlength="50">
                    </div>
                    <div class="mb-3">
                        <label for="senha" class="form-label">Senha</label>
                        <input type="password" name="senha"  class="form-control" maxlength="255">
                    </div>
                    <div class="mb-3">
                        <label for="senha2" class="form-label">Redigite a senha</label>
                        <input type="password" name="senha2"  class="form-control" maxlength="255">
                    </div>
                    <div class="row">
                        <div class="col-sm-2">
                            <input type="submit" value="Enviar" class="btn btn-primary">  
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
