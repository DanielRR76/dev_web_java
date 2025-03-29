<%@ page contentType="text/html" pageEncoding="UTF-8" import="java.util.List, entidade.AlunoProfessorDisciplina" %>
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
            <div class="col-12 col-md-8 col-lg-8 mt-4">
                <h2 class="text-center">Lista de Turmas</h2>

                    <%
                        List<AlunoProfessorDisciplina> turmas = (List<AlunoProfessorDisciplina>) request.getAttribute("turmas");

                        // Verifica se a lista é nula ou vazia
                        if (turmas == null || turmas.isEmpty()) {
                    %>
                        <div class="alert alert-info text-center mt-3" role="alert">
                            Não tem nenhuma turma.
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
                                    <th scope="col">Aluno</th>
                                    <th scope="col">Professor</th>
                                    <th scope="col">Disciplina</th>
                                    <th scope="col">Acões</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    // Iterando sobre a lista de turmas para criar as linhas da tabela
                                    for (AlunoProfessorDisciplina turma : turmas) {
                                %>
                                    <tr>
                                        <td><%= turma.getTurma().getId() %></td>
                                        <td><%= turma.getAluno().getNome() %></td>
                                        <td><%= turma.getProfessor().getNome() %></td>
                                        <td><%= turma.getDisciplina().getNome() %></td>
                                        <td>
                                                <a href="/aplicacaoMVC/admin/TurmaController?acao=Alterar&id=<%=turma.getTurma().getId()%>" class="btn btn-warning">Alterar</a>
                                                <a href="/aplicacaoMVC/admin/TurmaController?acao=Excluir&id=<%=turma.getTurma().getId()%>" class="btn btn-danger">Excluir</a>
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
                            <a href="/aplicacaoMVC/admin/TurmaController?acao=Incluir" class="mb-2 btn btn-primary">Incluir</a>
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
