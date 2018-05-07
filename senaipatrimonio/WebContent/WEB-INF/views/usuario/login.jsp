<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
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
	<c:import url="../templates/head.jsp" />
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

			<div id="superior">
					<div id="superior_sonOne">
						<img src="${images }/figura_login_superior_direito_circulo.svg" id="img_superior_direito_circulo">
					</div>
					<img src="${images }/figura_login_superior_esquerdo_traco.svg" id="img_superior_esquerdo_traco">
			</div>

			<div id="meio">
				<h1 >Bem vindo ao sistema <br> SENAI patrimonio</h1>
			</div>
			
			
			<div id="inferior">
				<div id="inferior_sonOne">
					<img src="${images }/figura_login_direita_seta.svg" id="img_direita_seta">
					<h2>Faça o login</h2>
				</div>
				<img src="${images }/figura_login_inferior_direito_espiral.svg" id="img_inferior_direito_espiral">
			</div>

		</div>
	</div>
</body>

</html>