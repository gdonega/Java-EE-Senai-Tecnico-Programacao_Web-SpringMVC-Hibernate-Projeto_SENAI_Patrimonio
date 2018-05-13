<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%-- Urls de Res --%>
<c:url value="/assets/images" var="images" />
<c:url value="/assets/css" var="css" />
<c:url value="/assets/js" var="js" />

<%--Urls de navegação --%>
<c:url value="/app/adm/ambiente/alterar" var="alterarAmbiente" />
<c:url value="/app/adm/ambiente/excluir" var="excluirAmbiente"/>
<c:url value="/app/adm/ambiente/salvar" var="salvarAmbiente"/>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<c:import url="../templates/head.jsp" />
	<link rel="stylesheet" href="${css}/lista_estilo.css">
	<link rel="stylesheet" href="${css}/lista_form_estilo.css">
	<title>Lista Ambientes - SENAI PATRIMONIO</title>
</head>
<body>
	<c:import url="../templates/nav.jsp" />
	
	
	<div class="conteudoDaPag">
		<h1>Lista de Ambientes</h1>
		<div class="divAll">
			<div class="divList">
				<table>
					<thead>
						<tr>
							<th>#</th>
							<th>Nome</th>
							<th>Editar</th>
							<th>Excluir</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${ambientes }" var="a">
							<tr>
								<td><c:out value="${a.id } " escapeXml="true" /></td>
								<td> <c:out value="${a.nome }" escapeXml="true" /></td>
								<c:if test="${usuarioLogado.admConfirm }">		
									<td>
										<a href="${alterarAmbiente }?id=${a.id}">Editar</a>
									</td>
									<td>
										<a href="${excluirAmbiente }?id=${a.id}">Excluir</a>
									</td>
								</c:if>
						</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
			
			<c:if test="${usuarioLogado.admConfirm }">	
				<div class="divForm">
					<form:form modelAttribute="ambiente" action="${salvarAmbiente}" method="post">
						<label>
							<form:input  placeholder="Novo Ambiente" path="nome" type="text" required="required" id="nome" />
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