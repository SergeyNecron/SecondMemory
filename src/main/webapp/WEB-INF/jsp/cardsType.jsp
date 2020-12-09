<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <title>Мои категории</title>
</head>
<body>
<section>
    <h3><a href="cards?action=home">Назад</a></h3>
    <hr/>
    <table class="table">
        <thead>
        <tr>
            <th><h2>Мои категории:</h2></th>
        </tr>
        </thead>
        <%--@elvariable id="cardsType" type="java.util.List"--%>
        <c:forEach items="${cardsType}" var="cardType">
            <jsp:useBean id="cardType" type="ru.secondmemory.model.CardType"/>
            <tr>
                <td><a href="cards?action=${cardType}">
                        ${cardType.title}</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>