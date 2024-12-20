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
    <body>
        <div class="container">
            <jsp:include page="../../comum/menuAdm.jsp" />
            <div class="mt-5">
                <%
                    Administrador administradorLogado = (Administrador) session.getAttribute("administrador");
                    out.println("<h2>Bem vindo, " + administradorLogado.getNome() + "!</h2>");
                %>


            </div>

            <div class="mt-5 table-responsive">
                <h4 class="mb-5 text-center">Informações das turmas</h4>
                <table class="table table-striped table-dark table-bordered table-hover">
                  <caption>As notas não estão sendo exibidas porque as turmas acabaram de ser criadas. A função de lançar as notas é dos professores</caption>
                    <thead>
                      <tr>
                        <th scope="col">Aluno</th>
                        <th scope="col">Professor</th>
                        <th scope="col">Disciplina</th>
                        <th scope="col">Nota</th>
                        <th scope="col">Código da turma</th>
                      </tr>
                    </thead>
                    <tbody>
                      <%
                        ArrayList<AlunoProfessorDisciplina> turmas = (ArrayList<AlunoProfessorDisciplina>) request.getAttribute("turmas");
                        if(turmas != null){
                          for(AlunoProfessorDisciplina turma : turmas){
                            out.println("<tr>");
                            out.println("<td>" + turma.getAluno().getNome() + "</td>");
                            out.println("<td>" + turma.getProfessor().getNome() + "</td>");
                            out.println("<td>" + turma.getDisciplina().getNome() + "</td>");
                            String notaExibida = (turma.getTurma().getNota() == -1) ? "-" : String.valueOf(turma.getTurma().getNota());
                            out.println("<td>" + notaExibida + "</td>");
                            out.println("<td>" + turma.getTurma().getCodigoTurma() + "</td>");
                            out.println("</tr>");
                          }
                        }
                      %>
                    </tbody>
                  </table>
                
            </div>
        </div>
        <script src="http://localhost:8080/aplicacaoMVC/views/bootstrap/bootstrap.bundle.min.js"></script>
    </body>
</html>
