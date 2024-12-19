<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link rel="shortcut icon" href="#" />
    <title>School</title>
    <link
      href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css"
      rel="stylesheet"
    />
  </head>
  <body>
    <div class="container">
        <jsp:include page="../../comum/menuAdm.jsp" />
        <div class="col-sm-6 offset-3 mt-5">

            <!-- <h3>Cadastre um administrador</h3> -->

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
            <div class="px-4 py-5 my-5 text-center">
                <h1 class="display-5 fw-bold text-body-emphasis">Cadastro</h1>
                <div class="d-grid gap-2 d-sm-flex justify-content-sm-center">
                    <div class="btn-group gap-2 mt-5">
                        <button type="button" class="btn btn-primary btn-lg px-4 gap-3 " onclick="location.href='/aplicacaoMVC/admin/registrarAdm'"> Administrador</button>
                        <button type="button" class="btn btn-secondary btn-lg px-4 gap-3" onclick="location.href='/aplicacaoMVC/admin/registrarProfessor'"> Professor</button>
                        <button type="button" class="btn btn-success btn-lg px-4 gap-3" onclick="location.href='/aplicacaoMVC/admin/registrarAluno'"> Aluno</button>
                        <button type="button" class="btn btn-warning btn-lg px-4 gap-3" onclick="location.href='/aplicacaoMVC/admin/registrarDisciplina'"> Disciplina</button>
                        <button type="button" class="btn btn-danger btn-lg px-4 gap-3" onclick="location.href='/aplicacaoMVC/admin/registrarTurma'"> Turma</button>
                    </div>
                    
                </div>
            </div>
        </div>
    </div>
    <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>
