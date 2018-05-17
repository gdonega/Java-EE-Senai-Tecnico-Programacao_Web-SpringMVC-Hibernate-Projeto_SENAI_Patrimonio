<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- Res --%>
<c:url value="/assets/images" var="images"/>
<c:url value="/assets/css" var="css"/>
<c:url value="/assets/js" var="js"/>

<%-- Links --%>
<c:url value="/app/item/form" var="formSalvar"/>
<c:url value="/app/adm/item/excluir" var="excluirItem"/>
<c:url value="${caminhoImagem}" var="imagePath"/>
<c:url value="/app/item/movimentacoes" var="verItem"/>
<c:url value="/app/adm/patrimonio/excluirFoto" var="excluirFoto"/>

<!DOCTYPE html>
<html>
<head>

    <c:import url="../templates/head.jsp"/>
    <link rel="stylesheet" href="${css}/lista_estilo.css">
    <link rel="stylesheet" href="${css}/lista_item_patrimonio.css">
    <title>Lista Itens - SENAI PATRIMONIO</title>

</head>
<body>
<c:import url="../templates/nav.jsp"/>
<div class="conteudoDaPag">

    <h1>Lista de Itens de patrimonio</h1>

    <div class="infoPatrimonio">
        <div class="imgInfo">
            <img src="${imagePath}">
            <div>
                <a href="${excluirFoto}?id=${patrimonio.id}">Excluir Foto</a>
            </div>
        </div>

        <div>
            <p>#: ${patrimonio.id}</p>
            <p>Nome: ${patrimonio.nome}</p>
            <p>Categoria: ${patrimonio.categoria.nome}</p>
            <p>Data de cadastro: ${patrimonio.dt_cadastro}</p>
            <p>Usuario Castrante nome: ${patrimonio.cadastrante.nome}</p>
            <p>Usuario Castrante email: ${patrimonio.cadastrante.email}</p>
        </div>

    </div>


    <table>

        <thead>
        <tr>
            <th>#</th>
            <th>Ambiente Atual</th>
            <th class="mobileNaoMostrar">Nome do cadastrante</th>
            <th class="mobileNaoMostrar">Ultima Movimentação</th>
            <c:if test="${usuarioLogado.admConfirm }">
                <th class="mobileNaoMostrar">Excluir</th>
            </c:if>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${itens_patrimonios }" var="ip">
            <tr>
                <td><a href="${verItem}?id=${ip.id}"><c:out value="${ip.id } " escapeXml="true"/></a></td>
                <td><c:out value="${ip.ambienteAtual.nome }" escapeXml="true"/></td>
                <td class="mobileNaoMostrar"><c:out value="${ip.cadastrante.nome }" escapeXml="true"/></td>
                <td class="mobileNaoMostrar"><c:out value="${ip.dataMovimentacao }" escapeXml="true"/></td>

                <c:if test="${usuarioLogado.admConfirm }">

                    <td class="mobileNaoMostrar">
                        <a href="${excluirItem }?id=${ip.id}">Excluir</a>
                    </td>
                </c:if>

            </tr>

        </c:forEach>
        <td colspan="7" align="center">
            <a href="${formSalvar }?idPatrimonio=${patrimonio.id}">Add +</a>
        </td>
        </tbody>

    </table>


</div>

</body>
</html>