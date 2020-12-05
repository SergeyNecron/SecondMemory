<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style> table, .table td {
    border: 2px solid green;
    padding: 8px;
    text-align: center
}</style>

<html>
<head>
    <title>${name}</title>
</head>
<body>
<section>
    <h3><a href="cards">Назад</a></h3>
    <hr/>
    <%--@elvariable id="name" type="java.lang.String"--%>
    <h2>${name}</h2>
    <table class="table">
        <%--@elvariable id="cards" type="java.util.List"--%>
        <c:forEach items="${cards}" var="card">
            <jsp:useBean id="card" type="ru.secondmemory.dto.CardDto"/>
            <tr>
                <td>${card.key}</td>
                <td>${card.value}</td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>