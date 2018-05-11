<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url value="/assets/images" var="images" />
<c:url value="/assets/css" var="css" />
<c:url value="/assets/js" var="js" />
<c:url value="/app/adm/usuario/form" var="formUsuario" />
<c:url value="/app/adm/usuario/excluir" var="excluirUsuario" />


<!DOCTYPE html>
<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<c:import url="../templates/head.jsp" />
	<link rel="stylesheet" href="${css}/usuario_lista_estilo.css">
	<title>Lista Usuarios - SENAI PATRIMONIO</title>
</head>

<body>
	<c:import url="../templates/nav.jsp" />
	<div class="conteudoDaPag">

		<h1>Lista de Usuarios</h1>

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
					<c:if test="${ u.id ne usuarioLogado.id }">
						<tr>
							<td>${u.id }</td>
							<td>${u.nome }</td>
							<td>${u.sobrenome }</td>
							<td>${u.email }</td>
							<td>${u.tipo }</td>
							<td>
								<a href="${formUsuario }?id=${u.id}">Editar</a>
							</td>
							<td>
								<a href="${excluirUsuario }?id=${u.id}">Excluir</a>
							</td>
						</tr>

					</c:if>
				</c:forEach>
				<td colspan="7" align="center">
					<a href="${formUsuario }">Add +</a>
				</td>
			</tbody>

		</table>
	</div>
</body>

</html>