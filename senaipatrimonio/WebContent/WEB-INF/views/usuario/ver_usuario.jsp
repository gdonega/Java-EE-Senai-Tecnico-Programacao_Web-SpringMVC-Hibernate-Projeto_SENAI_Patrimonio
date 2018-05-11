<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
        <c:url value="/assets/images" var="images" />
        <c:url value="/assets/css" var="css" />
        <c:url value="/assets/js" var="js" />
		<c:url value="/app/usuario/senha/alterar" var="alterarSenha"/>

        <!DOCTYPE html>
        <html>

        <head>
            
            <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
            <c:import url="../templates/head.jsp" />
            <link rel="stylesheet" href="${css}/ver_usuario.css">
            <title>Usuario - SENAI PATRIMONIO</title>
        </head>

        <body>
            <c:import url="../templates/nav.jsp" />
            <div class="conteudoDaPag">
            <script src="${js}/ver_usuario.js"></script>
                <div id="titulo">
                    <h1>Suas informações</h1>
                </div>

                <div id="informacoes">
                    <p>Nome: ${usuarioLogado.nome} ${usuarioLogado.sobrenome}</p>
                    <p>Email: ${usuarioLogado.email}</p>
                    <p>Nível de acesso: ${usuarioLogado.tipo}</p>
                </div>

                <div id="mudarSenha">

					<div>
	                    <button id="btnMostrarUpdateSenha" data-trigger-dialog="dialogMenuAlterarSenha">Alterar Senha</button>
	                    
	                    <div class="dialog" id="dialogMenuAlterarSenha">
	                    	
	                    	<h2>Complete os espaços abaixo para alterar a sua senha:</h2>
	                    	    
	                        <form:form modelAttribute="usuario" action="${alterarSenha}" method="post">
	                            
	                            <label for="inputSenhaAntiga" >
	                                    <input type="password" required="required" placeholder="Senha antiga" id="inputSenhaAntiga" name="senhaAntiga"/>
	                            </label>
	                            
	                            
	                            <label>
	                                <form:password placeholder="Nova senha" required="true" path="senha" maxlength="20" />
	                            </label>
	                               
	                               
	                            <label for="inputConfirmaNovaSenha">
	                                    <input type="password" required="required" placeholder="Confirme a sua nova senha" id="inputConfirmaNovaSenha" name="confirmacaoNovaSenha"/>
	                            </label>
	                            
	                            <form:errors class="spanErroSolto" path="senha"></form:errors>
	                            <button type="submit" id="btnAlterarSenha">Salvar</button>
	                        </form:form>
	
	                    </div>
					</div>
                </div>
            </div>
        </body>

        </html>