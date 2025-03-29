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
</head>
<body class="d-flex h-100 text-center bg-dark text-light">
    <div class="container">
        <jsp:include page="../../comum/menuProfessor.jsp" />
        <div class="row justify-content-center">
            <div class="col-12 col-md-8 col-lg-8 mt-5">
                <h3 class="text-center">Lance a nota de um aluno</h3>

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

                <form action="/aplicacaoMVC/professor/lancarNota" method="POST">
                    <%
                        ArrayList<AlunoProfessorDisciplina> turmas = (ArrayList<AlunoProfessorDisciplina>) request.getAttribute("turmas");

                        // Verifica se a lista é nula ou vazia
                        if (turmas == null || turmas.isEmpty()) {
                    %>
                        <div class="alert alert-info text-center mt-3" role="alert">
                            Não tem nenhum aluno para lançar a nota.
                        </div>
                        <div class="text-center mt-3">
                            <a href="/aplicacaoMVC/home" class="btn btn-primary">Voltar</a>
                        </div>
                    <%
                        } else {
                    %>
                        <div class="table-responsive-sm">
                            <table class="table table-bordered table-dark table-striped table-sm mt-5">
                                <thead>
                                    <tr>
                                        <th>Código da turma</th>
                                        <th>Disciplina</th>
                                        <th>Nome</th>
                                        <th>CPF</th>
                                        <th>Nota</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <%
                                        for (AlunoProfessorDisciplina turma : turmas) {
                                    %>
                                        <tr>
                                            <td><%= turma.getTurma().getCodigoTurma() %></td>
                                            <td><%= turma.getDisciplina().getNome() %></td>
                                            <td><%= turma.getAluno().getNome() %></td>
                                            <td><%= turma.getAluno().getCpf() %></td>
                                            <td>
                                                <div class="d-flex justify-content-center">
                                                    <input type="hidden" name="alunoIds" value="<%= turma.getAluno().getId() %>">
                                                <input type="hidden" name="disciplinaIds" value="<%= turma.getDisciplina().getId() %>">

                                                <!-- Condição para verificar se a nota é -1.0 -->
                                                <input type="number"
                                                       <%= (turma.getTurma().getNota() == -1.0) ? "" : "value='" + (int) Math.floor(turma.getTurma().getNota()) + "'" %>
                                                       name="notas" class="form-control form-control-sm" placeholder="Digite a nota" min="0" max="10">
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
                            <a href="/aplicacaoMVC/professor/dashboard" class="btn btn-secondary">Voltar</a>
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
