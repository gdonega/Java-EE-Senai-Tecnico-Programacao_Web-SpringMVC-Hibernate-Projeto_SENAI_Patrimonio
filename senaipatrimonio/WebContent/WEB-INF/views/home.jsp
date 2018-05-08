<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url value="/assets/images" var="images" />
<c:url value="/assets/css" var="css" />

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home - SENAI PATRIMONIO</title>
<link rel="stylesheet" href="${css}/HomeEstilo.css">
<c:import url="templates/head.jsp" />
</head>
<c:import url="templates/header.jsp" />
<body>
<h1> Bem Vindo, ${usuarioLogado.nome} ${usuarioLogado.sobrenome}</h1>



</body>
</html>