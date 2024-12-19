<%@page contentType="text/html" pageEncoding="UTF-8" import="entidade.Administrador" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
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
                        Administrador AdministradorLogado = (Administrador) session.getAttribute("administrador");
                        if (AdministradorLogado != null) { %>
                            <a class="nav-link" href="/aplicacaoMVC/admin/dashboard">Dashboard</a>
                            <a class="nav-link" href="/aplicacaoMVC/admin/cadastro">Cadastro</a>
                            <!-- <a class="nav-link" href="/aplicacaoMVC/admin/registrarAdm">Cadastrar Administrador</a>
                            <a class="nav-link" href="/aplicacaoMVC/admin/registrarProfessor">Cadastrar Professor</a>
                            <a class="nav-link" href="/aplicacaoMVC/admin/registrarAluno">Cadastrar Aluno</a> -->
                            <a class="nav-link" href="/aplicacaoMVC/admin/logOut">Logout</a>
                <%  } else { %>
                
                            <!-- <a class="nav-link" href="/aplicacaoMVC/MostrarComentarios">Coment&aacute;rios</a> -->
                            <a class="nav-link" href="/aplicacaoMVC/AutenticaAdmController?acao=Login">Login como Administrador</a>
                            <a class="nav-link" href="/aplicacaoMVC/AutenticaAlunoController?acao=Login">Login como Aluno</a>
                            <a class="nav-link" href="/aplicacaoMVC/AutenticaProfessorController?acao=Login">Login como Professor</a>
                <%    }
                    }%>



            </div>
        </div>
    </div>
</nav>
