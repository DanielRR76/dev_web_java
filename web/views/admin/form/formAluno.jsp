<%@page contentType="text/html" pageEncoding="UTF-8" import="entidade.Aluno"%>
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
    <body class="d-flex h-100 bg-dark text-light">
        <div class="container">
            <jsp:include page="../../comum/menuAdm.jsp" />
            <div class="row justify-content-center">
                <div class="col-12 col-md-8 col-lg-6 mt-5">

                    <div class=" text-center">
                        <%
                            Aluno aluno = (Aluno) request.getAttribute("aluno");
                            String acao = (String) request.getAttribute("acao");
                            switch (acao) {
                                case "Incluir":
                                    out.println("<h3>Incluir Aluno</h3>");
                                    break;
                                case "Alterar":
                                    out.println("<h3>Alterar Aluno</h3>");
                                    break;
                                case "Excluir":
                                    out.println("<h3>Excluir Aluno</h3>");
                                    break;
                            }
                        %>
                    </div>

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
                    <form action="/aplicacaoMVC/admin/AlunoController" method="POST">
                        <input type="hidden" name="id" value="<%=aluno.getId()%>" class="form-control">
                        <div class="mb-3">
                            <label for="nome" class="form-label">Nome</label>
                            <input type="text" name="nome" class="form-control" <%= acao.equals("Excluir") ? "Readonly" : "" %> value="<%= aluno != null ? aluno.getNome() : "" %>" placeholder="Digite o nome" maxlength="50" required>
                        </div>
                        <div class="mb-3">
                            <label for="email" class="form-label">Email</label>
                            <input type="email" name="email" class="form-control" <%= acao.equals("Excluir") ? "Readonly" : "" %> value="<%= aluno != null ? aluno.getEmail() : "" %>" placeholder="Digite o email" maxlength="50" required>
                        </div>
                        <div class="mb-3">
                            <label for="celular" class="form-label">Celular</label>
                            <input type="text" name="celular" class="form-control" <%= acao.equals("Excluir") ? "Readonly" : "" %> value="<%= aluno != null ? aluno.getCelular() : "" %>" placeholder="(99)99999-9999" maxlength="14" required>
                        </div>
                        <div class="mb-3">
                            <label for="cpf" class="form-label">CPF</label>
                            <input type="text" name="cpf" class="form-control" <%= acao.equals("Excluir") ? "Readonly" : "" %> value="<%= aluno != null ? aluno.getCpf() : "" %>" placeholder="999.999.999-99" maxlength="14" required>
                        </div>
                        <div class="mb-3">
                            <label for="endereco" class="form-label">Endereço</label>
                            <input type="text" name="endereco" class="form-control" <%= acao.equals("Excluir") ? "Readonly" : "" %> value="<%= aluno != null ? aluno.getEndereco() : "" %>" placeholder="Digite o endereço" maxlength="50">
                        </div>
                        <div class="mb-3">
                            <label for="cidade" class="form-label">Cidade</label>
                            <input type="text" name="cidade" class="form-control" <%= acao.equals("Excluir") ? "Readonly" : "" %> value="<%= aluno != null ? aluno.getCidade() : "" %>" placeholder="Digite a cidade" maxlength="30">
                        </div>
                        <div class="mb-3">
                            <label for="bairro" class="form-label">Bairro</label>
                            <input type="text" name="bairro" class="form-control" <%= acao.equals("Excluir") ? "Readonly" : "" %> value="<%= aluno != null ? aluno.getBairro() : "" %>" placeholder="Digite o bairro" maxlength="30">
                        </div>
                        <div class="mb-3">
                            <label for="cep" class="form-label">CEP</label>
                            <input type="text" name="cep" class="form-control" <%= acao.equals("Excluir") ? "Readonly" : "" %> value="<%= aluno != null ? aluno.getCep() : "" %>" placeholder="99999-999" maxlength="9">
                        </div>
                        <div class="mb-3">
                            <label for="senha" class="form-label">Senha</label>
                            <input type="password" name="senha"  class="form-control" <%= acao.equals("Excluir") ? "Readonly" : "" %> placeholder="Digite a senha" maxlength="255" required>
                        </div>
                        <div class="mb-3">
                            <label for="senha2" class="form-label">Redigite a senha</label>
                            <input type="password" name="senha2"  class="form-control" <%= acao.equals("Excluir") ? "Readonly" : "" %> placeholder="Redigite a senha" maxlength="255" required>
                        </div>
                        <div class="d-flex justify-content-between">
                            <a href="/aplicacaoMVC/admin/AlunoController?acao=Listar" class="btn btn-secondary">Voltar</a>
                            <input type="submit" name="btEnviar" value="<%=acao%>" class="btn btn-primary">
                        </div>
                    </form>
                </div>
            </div>

        </div>
        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>

