<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- Res --%>
<c:url value="/assets/images" var="images"/>
<c:url value="/assets/css" var="css"/>
<c:url value="/assets/js" var="js"/>

<%-- Links --%>
<c:url value="/app/adm/patrimonio/form" var="formPatrimonio"/>
<c:url value="/app/adm/patrimonio/excluir" var="excluirPatrimonio"/>
<c:url value="/app/patrimonio/lista" var="listaPatrimonio"/>
<c:url value="/app/patrimonio/itens" var="itensPatrimonio"/>


<!DOCTYPE html>
<html>
<head>
    <c:import url="../templates/head.jsp"/>
    <link rel="stylesheet" href="${css}/lista_estilo.css">
    <title>Lista Patrimonios - SENAI PATRIMONIO</title>
</head>
<body>


<c:import url="../templates/nav.jsp"/>
<div class="conteudoDaPag">

    <h1>Lista de Patrimônios</h1>

    <form action="${listaCategorias}" method="get" title="Selecione um tipo de filtro">
        <form:select path="tiposBusca" name="filtro">
            <form:option value=""> --Selecione um tipo de filtragem--</form:option>
            <form:option value=""> Todos as Categorias</form:option>
            <form:options items="${tiposBusca}" itemLabel="nome" itemValue="id"/>
        </form:select>

        <button type="submit">filtrar</button>
    </form>
    <div>
        <table>

            <thead>
            <tr>
                <th class="mobileNaoMostrar">#</th>
                <th>Nome</th>
                <th class="mobileNaoMostrar">Categoria</th>
                <th class="mobileNaoMostrar">Data de cadastro</th>
                <c:if test="${usuarioLogado.admConfirm }">
                    <th>Editar</th>
                    <th>Excluir</th>
                </c:if>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${patrimonios }" var="p">
                <tr>
                    <td class="mobileNaoMostrar"><c:out value="${p.id } " escapeXml="true"/></td>
                    <td><a href="${itensPatrimonio}?id=${p.id}"><c:out value="${p.nome }" escapeXml="true"/></a></td>
                    <td class="mobileNaoMostrar"><c:out value="${p.categoria.nome }" escapeXml="true"/></td>
                    <td class="mobileNaoMostrar"><c:out value="${p.dt_cadastro }" escapeXml="true"/></td>

                    <c:if test="${usuarioLogado.admConfirm }">
                        <td>
                            <a href="${formPatrimonio }?id=${p.id}">Editar</a>
                        </td>

                        <td>
                            <a href="${excluirPatrimonio }?id=${p.id}">Excluir</a>
                        </td>
                    </c:if>

                </tr>

            </c:forEach>
            <c:if test="${usuarioLogado.admConfirm }">
                <td colspan="7" align="center">
                    <a href="${formPatrimonio }">Add +</a>
                </td>
            </c:if>
            </tbody>

        </table>
    </div>

</div>

</body>
</html>