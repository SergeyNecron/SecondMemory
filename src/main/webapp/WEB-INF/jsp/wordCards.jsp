<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <title>${name}</title>
</head>
<body>
<section>
    <h3><a href="cards">Назад</a></h3>
    <hr/>
    <%--@elvariable id="name" type="java.lang.String"--%>
    <h2>${name}</h2>
    <table class="table">
        <tr>
            <td><b>Добавить карточку</b></td>
            <td></td>
            <td><a href="resume?action=add"><img src="img/add.png"></a></td>
            <td></td>
        </tr>
        <%--@elvariable id="cards" type="java.util.List"--%>
        <c:forEach items="${cards}" var="card">
            <jsp:useBean id="card" type="ru.secondmemory.dto.CardDto"/>
            <tr>
                <td><a href="cards?action=study&id=${card.key}">${card.key}</a></td>
                <td>${card.value}</td>
                <td><a href="cards?action=update&id=${card.key}"><img src="img/update.png"></a></td>
                <td><a href="cards?action=delete&id=${card.key}"><img src="img/delete.png"></a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>