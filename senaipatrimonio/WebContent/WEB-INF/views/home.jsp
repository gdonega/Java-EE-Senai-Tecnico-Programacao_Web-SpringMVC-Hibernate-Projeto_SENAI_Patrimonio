<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url value="/assets/images" var="images" />
<c:url value="/assets/css" var="css" />

        <!DOCTYPE html>
        <html>

            <head>
                <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
                <link rel="stylesheet" href="${css}/HomeEstilo.css">
                <c:import url="templates/head.jsp" />
                <title>Home - SENAI PATRIMONIO</title>
            </head>

            <body>

                <c:import url="templates/nav.jsp" />

                <div class="conteudoDaPag">
                    
                </div>


                <div class="contentWellcome dialog" style="display: flex;align-content: center;justify-content: center;">
                    <h1> Bem Vindo, ${usuarioLogado.nome} ${usuarioLogado.sobrenome}</h1>
                </div>

            </body>

        </html>