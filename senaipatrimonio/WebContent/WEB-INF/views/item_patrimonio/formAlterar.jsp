<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%-- Urls de Res --%>
<c:url value="/assets/images" var="images"/>
<c:url value="/assets/css" var="css"/>
<c:url value="/assets/js" var="js"/>

<%--Urls de navega��o --%>
<c:url value="/app/adm/item/alterarFoto" var="salvarItem"/>
<!DOCTYPE html>
<html>

<head>
    <c:import url="../templates/head.jsp"/>
    <link rel="stylesheet" href="${css}/separado_form_estilo.css">
    <title>Patrimonio - SENAI PATRIMONIO</title>
</head>

<body>
<c:import url="../templates/nav.jsp"/>
<div class="conteudoDaPag">
   
        <h1>Editar Item</h1>
   


    <div class="divForForm">
        <form:form class="formUser" modelAttribute="itemPatrimonio" action="${salvarItem}" method="post"
                   enctype="multipart/form-data">
            <label>
                <form:hidden path="id"/>
            </label>
           
            <label class="uploadFoto">Foto
                <input type="file" name="fotoItem">
            </label>


            <div class="flexDivCenter">
                <button class="btn" type="submit">Salvar</button>
            </div>
        </form:form>
    </div>

</div>


</body>

</html>