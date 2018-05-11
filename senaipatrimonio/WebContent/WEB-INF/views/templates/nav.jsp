<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
		<c:url value="/assets/images" var="images" />
		<c:url value="/assets/js" var="js" />

		<%-- urls das imagens --%>
			<c:url value="/app/home" var="homePage" />
			<c:url value="/app/usuario/logout" var="logout" />
			<c:url value="/app/usuario/info" var="usuarioInfo" />
			<c:url value="/app/adm/usuario/lista" var="usuarioLink" />

			<script src="${js}/nav.js"></script>

			<div class="headerNav">

				<nav>
					<ul class="topNav">
						<li>
							<a href="${homePage }">
								<img class="imgUserFake" src="${images }/home_icon.svg">
							</a>
						</li>
					</ul>

					<ul class="centerNav">
						<li>
							<img src="${images }/property_icon.svg">
						</li>
						<li>
							<img src="${images }/categoria_icon.svg">
						</li>
						<li>
							<img src="${images }/enviroment_icon.svg">
						</li>
						<c:if test="${usuarioLogado.admConfirm }">
							<li>
								<a href="${usuarioLink }">
									<img src="${images }/user_icon.svg">
								</a>
							</li>
						</c:if>
					</ul>

					<ul class="bottomNav">
						<li id="logoutButton">
							<!-- <a class="btnClickLogout">-->
							<a class="btnClickLogout" href="${logout }"> 
								<img src="${images }/logout.svg">
							</a>
						</li>

						<li id="userButton">
							<a href="${usuarioInfo }">
								<img src="${images }/logged_user_icon.svg">
							</a>
						</li>


					</ul>
				</nav>

			</div>

			<div class="btnMobile">
				
				<a id= "abrirNav">
					<img class="imgUserFake" src="${images }/lista_icon.svg">
				</a>
				
			</div>