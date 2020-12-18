<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <%--@elvariable id="type" type="ru.secondmemory.model.CardType"--%>
    <title>${type.title}</title>
</head>
<body>
<section>
    <h3><a href="cards">Назад</a></h3>
    <hr/>
    <h2>${type.title}</h2>
    <table class="table">
        <tr>
            <td><b>Добавить карточку</b></td>
            <td></td>
            <td></td>
            <td><a href="cards?action=add&type=${type}"><img src="img/add.png" alt="add"></a></td>
            <td></td>
        </tr>
        <%--@elvariable id="cards" type="java.util.List"--%>
        <c:forEach items="${cards}" var="card">
            <jsp:useBean id="card" type="ru.secondmemory.dto.CardListDto"/>
            <tr>
                <td><a href="cards?type=${type}&action=get&key=${card.key}">${card.key}</a></td>
                <td>${card.value}</td>
                <td width="200">${card.extra}</td>
                <td><a href="cards?type=${type}&action=update&key=${card.key}">
                    <img src="img/update.png" alt="update"></a></td>
                <td><a href="cards?type=${type}&action=delete&key=${card.key}">
                    <img src="img/delete.png" alt="delete"></a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>