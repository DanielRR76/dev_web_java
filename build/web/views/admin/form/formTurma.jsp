<%@page contentType="text/html" pageEncoding="UTF-8" import="entidade.AlunoProfessorDisciplina, entidade.Aluno, entidade.Professor, entidade.Disciplina, java.util.ArrayList" %>
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
    <body class="d-flex h-100 bg-dark text-light">
        <div class="container">
            <jsp:include page="../../comum/menuAdm.jsp" />
            <div class="row justify-content-center">
                <div class="col-12 col-md-8 col-lg-6 mt-5">

                    <div class=" text-center">
                        <%
                            AlunoProfessorDisciplina turma = (AlunoProfessorDisciplina) request.getAttribute("turma");
                            String acao = (String) request.getAttribute("acao");
                            switch (acao) {
                                case "Incluir":
                                    out.println("<h3>Incluir Turma</h3>");
                                    break;
                                case "Alterar":
                                    out.println("<h3>Alterar Turma</h3>");
                                    break;
                                case "Excluir":
                                    out.println("<h3>Excluir Turma</h3>");
                                    break;
                            }
                        %>
                    </div>

                    <%
                        String msgError = (String) request.getAttribute("msgError");
                        if (msgError != null && !msgError.isEmpty()) {
                    %>
                    <div class="alert alert-danger" role="alert">
                        <%= msgError %>
                    </div>
                    <% } %>

                    <%
                        String msgSuccess = (String) request.getAttribute("msgSuccess");
                        if (msgSuccess != null && !msgSuccess.isEmpty()) {
                    %>
                    <div class="alert alert-success" role="alert">
                        <%= msgSuccess %>
                    </div>
                    <% } %>

                    <form action="/aplicacaoMVC/admin/TurmaController" method="POST">
                        <input type="hidden" name="id" value="<%=turma.getTurma().getId()%>" class="form-control">
                        <div class="mb-3">
                            <label for="aluno" class="form-label">Selecione o Aluno</label>
                            <select name="aluno" id="aluno" <%= acao.equals("Excluir") ? "disabled" : "" %> class="form-select">
                                <%
                                ArrayList<Aluno> alunos = (ArrayList<Aluno>) request.getAttribute("alunos");
                                ArrayList<Professor> professores = (ArrayList<Professor>) request.getAttribute("professores");
                                ArrayList<Disciplina> disciplinas = (ArrayList<Disciplina>) request.getAttribute("disciplinas");

                                if (turma.getTurma().getId() != 0) {
                                %>
                                    <option value="<%= turma.getAluno().getId() %>">
                                        <%= turma.getAluno().getNome() %> - <%= turma.getAluno().getCpf() %>
                                    </option>
                                <%
                                    if (alunos != null) {
                                        for (Aluno aluno : alunos) {
                                            if (turma.getAluno().getId() != aluno.getId()) {
                                    %>
                                        <option value="<%= aluno.getId() %>">
                                            <%= aluno.getNome() %> - <%= aluno.getCpf() %>
                                        </option>
                                    <%
                                            }
                                        }
                                    }
                                } else {
                                    if (alunos != null) {
                                        for (Aluno aluno : alunos) {
                                    %>
                                        <option value="<%= aluno.getId() %>">
                                            <%= aluno.getNome() %> - <%= aluno.getCpf() %>
                                        </option>
                                    <%
                                        }
                                    }
                                }
                                %>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label for="professor" class="form-label">Selecione o Professor</label>
                            <select name="prof" id="professor" class="form-select" <%= acao.equals("Excluir") ? "disabled" : "" %>>
                                <%
                                if (turma.getTurma().getId() != 0) {
                                %>
                                    <option value="<%= turma.getProfessor().getId() %>">
                                        <%= turma.getProfessor().getNome() %> - <%= turma.getProfessor().getCpf() %>
                                    </option>
                                <%
                                    if (professores != null) {
                                        for (Professor professor : professores) {
                                            if (turma.getProfessor().getId() != professor.getId()) {
                                    %>
                                        <option value="<%= professor.getId() %>">
                                            <%= professor.getNome() %> - <%= professor.getCpf() %>
                                        </option>
                                    <%
                                            }
                                        }
                                    }
                                } else {
                                    if (professores != null) {
                                        for (Professor professor : professores) {
                                    %>
                                        <option value="<%= professor.getId() %>">
                                            <%= professor.getNome() %> - <%= professor.getCpf() %>
                                        </option>
                                    <%
                                        }
                                    }
                                }
                                %>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label for="disciplina" class="form-label">Selecione a Disciplina</label>
                            <select name="disciplina" id="disciplina" class="form-select" <%= acao.equals("Excluir") ? "disabled" : "" %>>
                                <%
                                if (turma.getTurma().getId() != 0) {
                                %>
                                    <option value="<%= turma.getDisciplina().getId() %>">
                                        <%= turma.getDisciplina().getNome() %>
                                    </option>
                                <%
                                    if (disciplinas != null) {
                                        for (Disciplina disciplina : disciplinas) {
                                            if (turma.getDisciplina().getId() != disciplina.getId()) {
                                    %>
                                        <option value="<%= disciplina.getId() %>">
                                            <%= disciplina.getNome() %>
                                        </option>
                                    <%
                                            }
                                        }
                                    }
                                } else {
                                    if (disciplinas != null) {
                                        for (Disciplina disciplina : disciplinas) {
                                    %>
                                        <option value="<%= disciplina.getId() %>">
                                            <%= disciplina.getNome() %>
                                        </option>
                                    <%
                                        }
                                    }
                                }
                                %>
                            </select>
                        </div>

                        <div class="mb-3">
                            <label for="codigoTurma" class="form-label">Digite o código da turma</label>
                            <input type="text" name="codigoTurma" <%= acao.equals("Excluir") ? "Readonly" : "" %> class="form-control"
                                   value="<%= turma != null ? turma.getTurma().getCodigoTurma() : "" %>"
                                   placeholder="Digite a carga horária" maxlength="2" required>
                        </div>

                        <div class="d-flex justify-content-between">
                            <a href="/aplicacaoMVC/admin/TurmaController?acao=Listar" class="btn btn-secondary">Voltar</a>
                            <input type="submit" name="btEnviar" value="<%=acao%>" class="btn btn-primary">
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
