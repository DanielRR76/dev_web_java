<%@page contentType="text/html" pageEncoding="UTF-8" import="entidade.Professor" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-danger">
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
                        Professor ProfessorLogado = (Professor) session.getAttribute("professor");
                        if (ProfessorLogado != null) { %>
                            <a class="nav-link" href="/aplicacaoMVC/professor/dashboard">Dashboard</a>
                            <a class="nav-link" href="/aplicacaoMVC/professor/logOut">Logout</a>
                <%  } else { %>

                            <a class="nav-link" href="/aplicacaoMVC/MostrarComentarios">Coment&aacute;rios</a>
                            <a class="nav-link" href="/aplicacaoMVC/AutenticaController?acao=Login">Login</a>
                <%    }
                    }%>



            </div>
        </div>
    </div>
</nav>
