<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%-- Urls de Res --%>
<c:url value="/assets/images" var="images" />
<c:url value="/assets/css" var="css" />
<c:url value="/assets/js" var="js" />

<%--Urls de navegação --%>
<c:url value="/app/adm/categoria/alterar" var="alterarCategoria" />
<c:url value="/app/adm/categoria/excluir" var="excluirCategoria"/>
<c:url value="/app/adm/categoria/salvar" var="salvarCategoria"/>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<c:import url="../templates/head.jsp" />
	<link rel="stylesheet" href="${css}/lista_estilo.css">
	<link rel="stylesheet" href="${css}/lista_form_estilo.css">
	<title>Lista Categoria - SENAI PATRIMONIO</title>
</head>
<body>
	<c:import url="../templates/nav.jsp" />
	
		<div class="conteudoDaPag">
		<h1>Lista de Categorias</h1>
		<div class="divAll">
			<div class="divList">
				<table>
					<thead>
						<tr>
							<th>#</th>
							<th>Nome</th>
							<c:if test="${usuarioLogado.admConfirm }">
								<th>Editar</th>
								<th>Excluir</th>
							</c:if>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${categorias }" var="c">
							<tr>
								<td><c:out value="${c.id } " escapeXml="true" /></td>
								<td> <c:out value="${c.nome }" escapeXml="true" /></td>
								<c:if test="${usuarioLogado.admConfirm }">		
									<td>
										<a href="${alterarCategoria }?id=${c.id}">Editar</a>
									</td>
									<td>
										<a href="${excluirCategoria }?id=${c.id}">Excluir</a>
									</td>
								</c:if>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
			<c:if test="${usuarioLogado.admConfirm }">	
				<div class="divForm">
					<form:form modelAttribute="categoria" action="${salvarCategoria}" method="post">
						<label>
							<form:input  placeholder="Nova Categoria" path="nome" type="text" required="required" id="nome" />
							<form:errors path="nome"></form:errors>
						</label>
						<button type="submit">Add+</button>	
					</form:form>
				</div>
			</c:if>
			
		</div>
	</div>

</body>
</html>