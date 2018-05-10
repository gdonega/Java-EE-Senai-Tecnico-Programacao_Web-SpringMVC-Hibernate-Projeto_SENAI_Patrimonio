<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <c:url value="/assets/images" var="images" />
        <c:url value="/assets/css" var="css" />
        <c:url value="/assets/js" var="js" />

        <!DOCTYPE html>
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
            <link rel="stylesheet" href="${css}/HomeEstilo.css">
            <script src="${js}/home.js"></script>
            <c:import url="templates/head.jsp" />
            <title>Home - SENAI PATRIMONIO</title>
        </head>

        <body>

            <c:import url="templates/nav.jsp" />

            <div class="conteudoDaPag">
                <ul class="menu">
                    <li>
                        <div>
                            <img src="${images }/property_icon.svg">
                            <h1>Patrimonios</h1>
                        </div>

                        <div>
                            <img src="${images }/enviroment_icon.svg">
                            <h1>Ambientes</h1>
                        </div>
                    </li>

                    <li>
                        <div>
                            <img src="${images }/categoria_icon.svg">
                            <h1>Categorias</h1>
                        </div>

                        <div>
                            <img src="${images }/user_icon.svg">
                            <h1>Usuarios</h1>
                        </div>

                    </li>
                </ul>

            </div>


            <div class="contentWellcome dialog d-none">
                <h1> Bem Vindo, ${usuarioLogado.nome} ${usuarioLogado.sobrenome}</h1>
            </div>

        </body>

        </html>