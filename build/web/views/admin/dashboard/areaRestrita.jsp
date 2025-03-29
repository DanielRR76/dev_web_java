<%@page contentType="text/html" pageEncoding="UTF-8" import="entidade.Administrador, entidade.AlunoProfessorDisciplina, java.util.ArrayList" %>

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
            <jsp:include page="../../comum/menuAdm.jsp" />
            <div class="mt-5">

              <h1>Área Restrita</h1>
              <%
                  Administrador administradorLogado = (Administrador) session.getAttribute("administrador");
                  out.println("<h3>Usuário logado com sucesso</h3>");
                  out.println("<h2>Nome: " + administradorLogado.getNome() + "</h2>");
              %>

                <div class="d-grid gap-2 d-sm-flex justify-content-sm-center  w-50 mx-auto">
                    <div class="btn-group flex-wrap gap-2 mt-5">
                        <button type="button" class="btn btn-outline-primary btn-lg px-4 gap-3" onclick="location.href='/aplicacaoMVC/admin/AdmController?acao=Listar'">Administradores</button>
                        <button type="button" class="btn btn-outline-danger btn-lg px-4 gap-3" onclick="location.href='/aplicacaoMVC/admin/ProfController?acao=Listar'">Professores</button>
                        <button type="button" class="btn btn-outline-success btn-lg px-4 gap-3" onclick="location.href='/aplicacaoMVC/admin/AlunoController?acao=Listar'">Alunos</button>
                        <button type="button" class="btn btn-outline-warning btn-lg px-4 gap-3" onclick="location.href='/aplicacaoMVC/admin/DisciplinaController?acao=Listar'">Disciplinas</button>
                        <button type="button" class="btn btn-outline-light btn-lg px-4 gap-3" onclick="location.href='/aplicacaoMVC/admin/TurmaController?acao=Listar'">Turmas</button>
                        <button type="button" class="btn btn-outline-info btn-lg px-4 gap-3" onclick="location.href='/aplicacaoMVC/admin/relatorio'">Relatório</button>
                        <button type="button" class="btn btn-outline-light btn-lg px-4 gap-3" style="border: 1px solid #c20dc2; color: #c20dc2;" onclick="location.href='/aplicacaoMVC/admin/aprovaAdm'">Aprovação</button>
                    </div>
                </div>
            </div>
        </div>
        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
