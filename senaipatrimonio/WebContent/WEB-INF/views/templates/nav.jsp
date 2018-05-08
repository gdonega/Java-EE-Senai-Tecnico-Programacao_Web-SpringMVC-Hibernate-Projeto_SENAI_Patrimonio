<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
        <c:url value="/assets/images" var="images" />
        <div class="headerNav">

            <nav>
                <ul class="topNav">
                    <li>
                        <a href="https://google.com">
                            <img class="imgUserFake" src="${images }/logged_user_icon.svg">
                        </a>
                    </li>
                </ul>

                <ul class="centerNav">
                    <li>
                        <img src="${images }/property_icon.svg">
                    </li>
                    <li>
                        <img src="${images }/user_icon.svg">
                    </li>
                    <li>
                        <img src="${images }/categoria_icon.svg">
                    </li>
                    <li>
                        <img src="${images }/enviroment_icon.svg">
                    </li>
                </ul>

                <ul class="bottomNav">

                </ul>
            </nav>

        </div>