<%@ page contentType="text/html" pageEncoding="UTF-8" import="entidade.AlunoProfessorDisciplina, java.util.ArrayList" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="shortcut icon" href="#">
    <title>School</title>
    <link href="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.min.css" rel="stylesheet">
    <link href="http://localhost:8080/aplicacaoMVC/views/aluno/inscricao/inscricao.css" rel="stylesheet">
</head>
<body class="d-flex h-100 text-center bg-dark text-light">
    <div class="container">
        <jsp:include page="../../comum/menuAluno.jsp" />
        <div class="row justify-content-center">
            <div class="col-12 col-md-8 col-lg-8 mt-5">
                <h3 class="text-center">Cancele uma inscrição numa disciplina</h3>

                <% String msgError = (String) request.getAttribute("msgError");
                    if ((msgError != null) && (!msgError.isEmpty())) { %>
                    <div class="alert alert-danger" role="alert">
                        <%= msgError %>
                    </div>
                <% } %>
                <% String msgSuccess = (String) request.getAttribute("msgSuccess");
                    if ((msgSuccess != null) && (!msgSuccess.isEmpty())) { %>
                    <div class="alert alert-success" role="alert">
                        <%= msgSuccess %>
                    </div>
                <% } %>

                <form action="/aplicacaoMVC/aluno/cancelarInscricao" method="POST">
                    <%
                        ArrayList<AlunoProfessorDisciplina> turmas = (ArrayList<AlunoProfessorDisciplina>) request.getAttribute("turmas");

                        // Verifica se a lista é nula ou vazia
                        if (turmas == null || turmas.isEmpty()) {
                    %>
                        <div class="alert alert-info text-center mt-3" role="alert">
                            Não tem nenhuma disciplina para cancelar.
                        </div>
                        <div class="text-center mt-3">
                            <a href="/aplicacaoMVC/aluno/dashboard" class="btn btn-primary">Voltar</a>
                        </div>
                    <%
                        } else {
                    %>
                    <div class="table-responsive-sm">
                        <table class="table table-striped table-dark table-bordered table-hover mt-5">
                            <thead>
                                <tr>
                                    <th scope="col">Código da turma</th>
                                    <th scope="col">Professor</th>
                                    <th scope="col">CPF</th>
                                    <th scope="col">Disciplina</th>
                                    <th scope="col">Cancelamento</th>
                                </tr>
                            </thead>
                            <tbody>
                                <%
                                    for (AlunoProfessorDisciplina turma : turmas) {
                                %>
                                    <tr>
                                        <td><%= turma.getTurma().getCodigoTurma() %></td>
                                        <td><%= turma.getProfessor().getNome() %></td>
                                        <td><%= turma.getProfessor().getCpf() %></td>
                                        <td><%= turma.getDisciplina().getNome() %></td>
                                        <td>
                                            <div class="form-check form-switch d-flex justify-content-center">
                                                <input class="form-check-input custom-cancelamento" type="checkbox" name="turmas" value="<%= turma.getProfessor().getId() %>-<%= turma.getDisciplina().getId() %>-<%= turma.getTurma().getCodigoTurma() %>" id="turma<%= turma.getTurma().getId() %>">
                                            </div>
                                        </td>
                                    </tr>
                                <%
                                    }
                                %>
                            </tbody>
                        </table>
                    </div>

                        <div class="d-flex justify-content-between">
                            <a href="/aplicacaoMVC/aluno/dashboard" class="btn btn-secondary">Voltar</a>
                            <input type="submit" value="Enviar" class="btn btn-primary">
                        </div>
                    <%
                        }
                    %>
                </form>
            </div>
        </div>
    </div>
    <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
</body>
</html>
