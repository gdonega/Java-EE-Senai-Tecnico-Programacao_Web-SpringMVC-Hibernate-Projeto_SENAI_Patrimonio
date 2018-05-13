<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<c:url value="/assets/images" var="images" />
<c:url value="/assets/css" var="css" />
<c:url value="/assets/js" var="js" />
<c:url value="/app/adm/patrimonio/salvar" var="salvarPatrimonio" />


<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<c:import url="../templates/head.jsp" />
	<link rel="stylesheet" href="${css}/separado_form_estilo.css">
	<title>Patrimonio - SENAI PATRIMONIO</title>
</head>

<body>
	<c:import url="../templates/nav.jsp" />
	<div class="conteudoDaPag">
		<c:if test="${ empty usuario.id }">
			<h1>Novo Usuario</h1>
		</c:if>
		<c:if test="${ not empty usuario.id }">
			<h1>Editar Usuario</h1>
		</c:if>


		<div class="divForForm">
			<form:form class="formUser" modelAttribute="patrimonio" action="${salvarUsuario}" method="post" style="color: white">
				<label> 
					<form:hidden path="id" />
				</label>

				<label> Nome
					<form:input path="nome" type="text" required="required" id="nome" />
				</label>
					<form:errors path="nome" class="spanErroSolto"></form:errors>

				<label>
					<form:select path="categoria" name="categoria">
						<form:options items="${categorias}" itemLabel="nome" itemValue="id" />
					</form:select>
				</label>
					<form:errors path="categoria" class="spanErroSolto"></form:errors>
				<div class="flexDivCenter">
					<button class="btn" type="submit">Salvar</button>
				</div>
			</form:form>
		</div>

	</div>


</body>

</html>