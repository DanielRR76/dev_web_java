<%@page contentType="text/html" pageEncoding="UTF-8" import="entidade.Professor, entidade.AlunoProfessorDisciplina, java.util.ArrayList" %>

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
    <body class="d-flex h-100 text-center bg-dark text-light">
        <div class="container">
            <jsp:include page="../../comum/menuProfessor.jsp" />
            <div class="mt-5">
                <h1>Área Restrita</h1>
              <%
                  Professor professorLogado = (Professor) session.getAttribute("professor");
                  out.println("<h3>Usuário logado com sucesso</h3>");
                  out.println("<h2>Nome: " + professorLogado.getNome() + "</h2>");
              %>


            </div>
            <div class="d-grid gap-2 d-sm-flex justify-content-sm-center">
                <div class="btn-group flex-wrap gap-2 mt-5">
                    <button type="button" class="btn btn-primary btn-lg px-4 gap-3" onclick="location.href='/aplicacaoMVC/professor/relatorio'">Seus alunos</button>
                  <button type="button" class="btn btn-success btn-lg px-4 gap-3" onclick="location.href='/aplicacaoMVC/professor/lancarNota'">Lançar nota</button>
                </div>
            </div>
        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
