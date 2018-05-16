<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- Res --%>
<c:url value="/assets/images" var="images"/>
<c:url value="/assets/css" var="css"/>
<c:url value="/assets/js" var="js"/>

<%-- Links --%>
<c:url value="${caminhoImagem}" var="imagePath"/>
<c:url value="/app/movimentacao/nova" var="novaMov"/>


<!DOCTYPE html>
<html>
<head>

    <c:import url="../templates/head.jsp"/>
    <link rel="stylesheet" href="${css}/lista_estilo.css">
    <link rel="stylesheet" href="${css}/lista_item_patrimonio.css">
    <link rel="stylesheet" href="${css}/lista_form_estilo.css">
    <title>Item patrimonio - ${itemPatrimonio.patrimonio.nome} - SENAI PATRIMONIO</title>

</head>
<body>
<c:import url="../templates/nav.jsp"/>
<div class="conteudoDaPag">

    <h1>Lista de ${itemPatrimonio.patrimonio.nome}</h1>

    <div class="infoPatrimonio">
        <div>
            <img src="${imagePath}">
        </div>

        <div>
            <p>#: ${itemPatrimonio.id}</p>
            <p>Patrimonio: ${itemPatrimonio.patrimonio.nome}</p>
            <p>Ambiente Atual: ${itemPatrimonio.ambienteAtual.nome}</p>
            <p>Cadastrante: ${itemPatrimonio.cadastrante.nome}</p>
        </div>

    </div>

    <div class="divAll">

        <div class="divList">
        <table>

            <thead>
            <tr>
                <th>Origem</th>
                <th>Destino</th>
                <th>Usuario que realizou</th>
                <th>Data que ocorreu</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${movimentacoes }" var="m">
                <tr>
                    <td><c:out value="${m.ambienteOriginal.nome }" escapeXml="true"/></td>
                    <td><c:out value="${m.ambienteNovo.nome }" escapeXml="true"/></td>
                    <td><c:out value="${m.executou.nome }" escapeXml="true"/></td>
                    <td><c:out value="${m.dataDaMovimentacao }" escapeXml="true"/></td>
                </tr>

            </c:forEach>
        </table>
        </div>

            <div class="divForm">
                <h2>Nova Movimentação</h2>
                <form:form modelAttribute="movimentacao" action="${novaMov}" method="post" >
                	<form:hidden path="itemMovido.id" value="${itemPatrimonio.id }" />
                	<form:hidden path="executou.id" value="${usuarioLogado.id }" />
                	<form:hidden path="ambienteOriginal.id" value="${itemPatrimonio.ambienteAtual.id }" />
                
                    <label>
                        <form:select path="ambienteNovo.id">
                            <form:option value=""></form:option>
                            <form:options items="${ambientes}" itemLabel="nome" itemValue="id"/>
                        </form:select>
                        <form:errors path="ambienteNovo"></form:errors>
                    </label>
                    <button type="submit">Add+</button>
                </form:form>
            </div>

    </div>

</div>

</body>
</html>