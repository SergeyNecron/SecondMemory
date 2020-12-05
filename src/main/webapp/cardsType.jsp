<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style> table, .table td {
    border: 2px solid green;
    padding: 8px;
}</style>

<html>
<head>
    <title>Мои категории</title>
</head>
<body>
<section>
    <h3><a href="index.html">Home</a></h3>
    <hr/>
    <h2>Мои категории:</h2>
    <table class="table">
        <thead>
        <tr>
            <th>Учить:</th>
        </tr>
        </thead>
        <%--@elvariable id="cards" type="java.util.List"--%>
        <c:forEach items="${cards}" var="card">
            <jsp:useBean id="card" type="ru.secondmemory.model.CardType"/>
            <tr>
                <td>${card.title}</td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>