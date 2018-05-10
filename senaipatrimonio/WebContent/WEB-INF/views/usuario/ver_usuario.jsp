<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
        <c:url value="/assets/images" var="images" />
        <c:url value="/assets/css" var="css" />
        <c:url value="/assets/js" var="js" />


        <!DOCTYPE html>
        <html>

        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
            <c:import url="../templates/head.jsp" />
            <link rel="stylesheet" href="${css}/ver_usuario.css">
            <title>Usuario - SENAI PATRIMONIO</title>
            <script src="${js}/ver_usuario.js"></script>
        </head>

        <body>
            <c:import url="../templates/nav.jsp" />
            <div class="conteudoDaPag">

                <div id="titulo">
                    <h1>Suas informa��es</h1>
                </div>

                <div id="informacoes">
                    <p>Nome: ${usuarioLogado.nome} ${usuarioLogado.sobrenome}</p>
                    <p>Email: ${usuarioLogado.email}</p>
                    <p>N�vel de acesso: ${usuarioLogado.tipo}</p>
                </div>

                <div id="mudarSenha">

                    <button data-trigger-dialog="dialogMenuAlterarSenha">Alterar Senha</button>
                    <div class="dialog" id="dialogMenuAlterarSenha">
                        <form:form  action="${logar}" method="post">
                            <label>
                                <form:input placeholder="Email" required="true" path="email" type="email" maxlength="120" />
                                <form:errors path="email"></form:errors>
                            </label>

                            <label>
                                <form:password placeholder="Senha" required="true" path="senha" maxlength="20" />
                                <form:errors path="senha"></form:errors>
                            </label>
                            <button type="submit">ENTRAR</button>
                    </div>

                </div>
            </div>
        </body>

        </html>