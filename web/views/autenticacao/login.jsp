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
  <body class="d-flex h-100 text-center bg-dark text-light">
    <div class="container">
        <jsp:include page="../comum/menu.jsp" />
        <div class="col-12 col-sm-8 col-md-6 offset-md-3 mt-5">
            <div class="px-4 py-5 my-5 text-center">
                <h1 class="display-5 fw-bold text-body-emphasis">Login</h1>
                <div class="d-grid gap-2 d-sm-flex justify-content-sm-center">
                    <div class="btn-group flex-wrap gap-2 mt-5">
                        <button type="button" class="btn btn-primary btn-lg px-4 gap-3" onclick="location.href='/aplicacaoMVC/AutenticaAdmController'">Administrador</button>
                        <button type="button" class="btn btn-danger btn-lg px-4 gap-3" onclick="location.href='/aplicacaoMVC/AutenticaProfessorController'">Professor</button>
                        <button type="button" class="btn btn-success btn-lg px-4 gap-3" onclick="location.href='/aplicacaoMVC/AutenticaAlunoController'">Aluno</button>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>
