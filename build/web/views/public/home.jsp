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
      <jsp:include page="../comum/menu.jsp" />
      <div class="mt-5">
        <h3>Seja Bem-vindo School</h3>
        <h4>Site onde:</h4>
        <h5>
          Os alunos conseguem se inscrever nas disciplinas e ver suas notas
        </h5>
        <h5>
          Os professores conseguem ver os alunos e lançar as notas pra eles
        </h5>
        <h5>
          E onde os administradores conseguem criar novas disciplinas/turmas, cadastrar alunos e professores
        </h5>
      </div>
    </div>
    <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
  </body>
</html>
