<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<c:url value="/assets/images" var="images"/>
<c:url value="/assets/css" var="css"/>
<c:url value="/assets/js" var="js"/>


<c:url value="/app/item/salvar" var="salvarItem"/>
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
    
        <h1>Novo Item</h1>
    


    <div class="divForForm">
        <form:form class="formUser" modelAttribute="itemPatrimonio" action="${salvarItem}" method="post"
                   enctype="multipart/form-data">
            <label>
                <form:hidden path="id"/>
            </label>
            
            <label>
            	<form:hidden path="patrimonio.id" />
            </label>

            <label> Ambiente
                <form:select path="ambienteAtual.id">
                    <form:options items="${ambientes}" itemLabel="nome" itemValue="id"/>
                </form:select>
            </label>
            <form:errors path="ambienteAtual" class="spanErroSolto"></form:errors>

            <label>Foto
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