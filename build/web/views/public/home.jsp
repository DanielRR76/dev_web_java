<%@page contentType="text/html" pageEncoding="UTF-8" import="entidade.Professor, entidade.Aluno, entidade.Administrador"%>
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
      <%
          Professor professorLogado = (Professor) session.getAttribute("professor");
          Aluno alunoLogado = (Aluno) session.getAttribute("aluno");
          Administrador administradorLogado = (Administrador) session.getAttribute("administrador");
      %>
      <% if (professorLogado != null) { %>
        <jsp:include page="../comum/menuProfessor.jsp" />
      <% } else if (alunoLogado != null) { %>
        <jsp:include page="../comum/menuAluno.jsp" />
      <% } else if (administradorLogado != null) { %>
        <jsp:include page="../comum/menuAdm.jsp" />
      <% } else { %>
        <jsp:include page="../comum/menu.jsp" />
      <% } %>

      <div class="mt-5">
        <h3>Seja Bem-vindo ao School</h3>
        <h4>Site onde:</h4>
        <h5>
          Os alunos conseguem se inscrever nas disciplinas e ver suas notas
        </h5>
        <h5>
          Os professores conseguem ver os alunos e lançar as suas notas
        </h5>
        <h5>
          E onde os administradores conseguem criar novas disciplinas/turmas, cadastrar alunos e professores
        </h5>
      </div>
    </div>
    <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
  </body>
</html>
