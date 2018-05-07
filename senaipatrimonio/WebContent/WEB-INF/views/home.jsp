<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:url value="/assets/images" var="images" />
<c:url value="/assets/css" var="css" />

<style>
	@keyframes fx-grow{
		from{
			transform : scale(0);
		}
		
		to{
			transform : scale(1);
		}
	}

	body{
		margin: 0px;
	}

	.dialog{
		background-color: rgba(30, 30, 30, 0.8);
		color: white;
		display: none;
		height: 100vh;
		width: 100vw;
		
		z-index: 99999999999;
	}
	
	.dialog.show{
		display: block;
		animation : fx-grow .2s;
	}
	
</style>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home - SENAI PATRIMONIO</title>
<link rel="stylesheet" href="${css}/HomeEstilo.css">
</head>
<c:import url="templates/header.jsp" />
<body>
<h1> Bem Vindo, ${usuarioLogado.nome} ${usuarioLogado.sobrenome}</h1>

	<div class="dialog" id="dialogMenuAlterarSenha">
		<h1>Teste</h1>
		<form>
			<input type="text">
		</form>
	</div>
	
	<a data-trigger-dialog="dialogMenuAlterarSenha">Link</a>
	
	<script>
		document.querySelectorAll("*[data-trigger-dialog]").forEach((e) => {
			e.addEventListener('click', () => {
				let id = e.getAttribute('data-trigger-dialog');
				document.querySelector("#" + id).classList.toggle('show');
				
				setTimeout(() => {
					document.querySelector("#" + id).classList.remove('show');	
				}, 3000);
			
			})
		});
	</script>
</body>
</html>