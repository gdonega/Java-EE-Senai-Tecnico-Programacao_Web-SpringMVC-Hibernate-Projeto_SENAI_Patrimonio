<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url value="/assets/images" var="images" />
<c:url value="/assets/css" var="css" />
<c:url value="/assets/js" var="js" />
<c:url value="/app/adm/usuario/form" var="formUsuario" />
<c:url value="/app/adm/usuario/excluir" var="excluirUsuario" />
<c:url value="/app/adm/usuario/lista" var="listaUsuario" />


<!DOCTYPE html>
<html>

<head>
	<c:import url="../templates/head.jsp" />
	<link rel="stylesheet" href="${css}/lista_estilo.css">
	<title>Lista Usuarios - SENAI PATRIMONIO</title>
</head>

<body>
	<c:import url="../templates/nav.jsp" />
	<div class="conteudoDaPag">

		<h1>Lista de Usuarios</h1>

		<form  action="${listaUsuario}" method="get" title="Selecione um tipo de filtro">				
			<form:select path="tiposBusca" name="filtro" >
				 <form:option value=""> --Selecione um tipo de filtragem--</form:option>
				 <form:option  value=""> Todos os Usuarios</form:option>
				 <form:options  items="${tiposBusca}"></form:options>
			</form:select>

			
			<button type="submit">filtrar</button>		
		</form>

		<table>

			<thead>
				<tr>
					<th>#</th>
					<th>Nome</th>
					<th>Sobrenome</th>
					<th>E-mail</th>
					<th>Tipo</th>
					<th>Editar</th>
					<th>Excluir</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${usuarios }" var="u">
					<tr>
						<td><c:out value="${u.id } " escapeXml="true" /></td>
						<td> <c:out value="${u.nome }" escapeXml="true" /></td>
						<td> <c:out value="${u.sobrenome }" escapeXml="true" /></td>
						<td> <c:out value="${u.email }" escapeXml="true" /></td>
						<td><c:out value="${u.tipo }" escapeXml="true" /></td>
						<td>
							<a href="${formUsuario }?id=${u.id}">Editar</a>
						</td>
						<c:if test="${ u.id ne usuarioLogado.id }">
							<td>
								<a href="${excluirUsuario }?id=${u.id}">Excluir</a>
							</td>
						</c:if>
				</tr>

				</c:forEach>
				<td colspan="7" align="center">
					<a href="${formUsuario }">Add +</a>
				</td>
			</tbody>

		</table>
	</div>
</body>

</html>