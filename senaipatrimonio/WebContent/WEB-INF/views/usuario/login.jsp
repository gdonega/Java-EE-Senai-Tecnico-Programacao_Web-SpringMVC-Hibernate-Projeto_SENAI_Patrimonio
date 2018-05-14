<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:url value="/assets/images" var="images" />
<c:url value="/assets/css" var="css" />

<c:url value="/usuario/logar" var="logar"/>

<!DOCTYPE html>
<html>

<head>
	<title>Login - SENAI PATRIMONIO</title>
	<meta name="viewport" content="width=device-width, initial-scale=1">
<%-- Css/JS links --%>
<c:url value="/assets/css" var="css" />
<c:url value="/assets/images" var="images" />

<%-- Css/JS imports --%>
<link rel="stylesheet" href="${css}/estilo.css">
<link rel="icon" href="${images}/logo.svg">

	<link rel="stylesheet" href="${css}/loginEstilo.css">
</head>

<body>

	<div id="telaDeLogin">

		<div id="lateral">
			<div id="logoLateral">
				<img src="${images }/logo.svg">
			</div>

			<div id="formLateral">
				<form:form modelAttribute="usuario" action="${logar}" method="post">
					<label> 
						<form:input placeholder="Email" required="true" path="email" type="email" maxlength="120" />
						<form:errors path="email"></form:errors>
					</label>

					<label> 
						<form:password placeholder="Senha" required="true" path="senha" maxlength="20" />
						<form:errors path="senha"></form:errors>
					</label>
					<button type="submit">ENTRAR</button>

				</form:form>
			</div>
		</div>

		<div id="tela">

		</div>
	</div>
</body>

</html>