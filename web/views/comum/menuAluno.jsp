<%@page contentType="text/html" pageEncoding="UTF-8" import="entidade.Aluno" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-success">
    <div class="container-fluid">
        <a class="navbar-brand" href="/aplicacaoMVC/home">Home</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <%
                    // testar se está logado
                    HttpSession sessao = request.getSession(false);
                    if (sessao != null) {
                        Aluno alunoLogado = (Aluno) session.getAttribute("aluno");
                        if (alunoLogado != null) { %>
                            <a class="nav-link" href="/aplicacaoMVC/aluno/dashboard">Dashboard</a>
                            <a class="nav-link" href="/aplicacaoMVC/aluno/logOut">Logout</a>
                <%  } else { %>

                            <a class="nav-link" href="/aplicacaoMVC/AutenticaController?acao=Login">Login</a>
                <%    }
                    }%>



            </div>
        </div>
    </div>
</nav>
