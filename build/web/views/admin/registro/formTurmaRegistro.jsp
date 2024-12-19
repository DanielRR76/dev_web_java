<%@page contentType="text/html" pageEncoding="UTF-8" import="entidade.Aluno, entidade.Professor, entidade.Disciplina, java.util.ArrayList" %>
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
    <body>
        <div class="container">
            <jsp:include page="../../comum/menuAdm.jsp" />
            <div class="col-sm-6 offset-3 mt-5">

                <h3>Cadastre uma Turma</h3>

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
                <form action="/aplicacaoMVC/admin/registrarTurma" method="POST">
                    <div class="mb-3">
                        <label for="nome" class="form-label">Selecione o Aluno</label>
                        <select name="aluno" id="aluno" class="form-select">
                            <%
                                ArrayList<Aluno> alunos = (ArrayList<Aluno>) request.getAttribute("alunos");
                                if(alunos != null){
                                    for(Aluno aluno : alunos){
                            %>
                                        <option value="<%= aluno.getId() %>"><%= aluno.getNome() %> - <%= aluno.getCpf() %></option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="professor" class="form-label">Selecione o Professor</label>
                        <select name="prof" id="professor" class="form-select">
                            <%
                                ArrayList<Professor> professores = (ArrayList<Professor>) request.getAttribute("professores");
                                if(professores != null){
                                    for(Professor professor : professores){
                            %>
                                        <option value="<%= professor.getId() %>"><%= professor.getNome() %> - <%= professor.getCpf() %></option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="disciplina" class="form-label">Selecione a Disciplina</label>
                        <select name="disciplina" id="disciplina" class="form-select">
                            <%
                                ArrayList<Disciplina> disciplinas = (ArrayList<Disciplina>) request.getAttribute("disciplinas");
                                if(disciplinas != null){
                                    for(Disciplina disciplina : disciplinas){ // Corrigido: tipo de dado correto
                            %>
                                        <option value="<%= disciplina.getId() %>"><%= disciplina.getNome() %></option>
                            <%
                                    }
                                }
                            %>
                        </select>
                    </div>
                    <div class="mb-3">
                        <label for="codigoTurma" class="form-label">Digite o código da turma</label>
                        <input type="text" name="codigoTurma" class="form-control" placeholder="Digite a carga horária" maxlength="2">
                    </div>
                    <div class="row">
                        <div class="col-sm-2">
                            <input type="submit" value="Enviar" class="btn btn-primary">  
                        </div>
                    </div>
                </form>
            </div>
        </div>
        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
