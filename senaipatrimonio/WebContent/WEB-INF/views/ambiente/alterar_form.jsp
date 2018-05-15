<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%-- Urls de Res --%>
<c:url value="/assets/images" var="images"/>
<c:url value="/assets/css" var="css"/>
<c:url value="/assets/js" var="js"/>

<%--Urls de navegação --%>
<c:url value="/app/adm/ambiente/alterar" var="alterarAmbiente"/>

<!DOCTYPE html>
<html>
<head>
    <c:import url="../templates/head.jsp"/>
    <title>Alterar ambiente - SENAI PATRIMONIO</title>
</head>
<body>
<c:import url="../templates/nav.jsp"/>


<div class="conteudoDaPag">
    <h1>Alterar Ambiente</h1>

    <form:form modelAttribute="ambiente" action="${alterarAmbiente}" method="post">
        <label>
            <form:hidden path="id"/>
        </label>

        <label>
            <form:input placeholder="${nomeOriginal}" path="nome" type="text" required="required"/>
            <form:errors path="nome"></form:errors>
        </label>
        <button type="submit">Atualizar</button>
    </form:form>
</div>
</body>
</html>