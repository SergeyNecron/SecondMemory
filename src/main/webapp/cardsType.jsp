<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style> table, .table td {
    border: 2px solid green;
    padding: 8px;
    text-align: center
}</style>

<html>
<head>
    <title>Мои категории</title>
</head>
<body>
<section>
    <h3><a href="index.html">Назад</a></h3>
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
                <td><a href="cards?cardType=${cardType}">
                        ${cardType.title}</a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>