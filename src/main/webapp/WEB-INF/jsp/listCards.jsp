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
            <%--@elvariable id="newCard" type="ru.secondmemory.dto.CardDto"--%>
            <c:if test="${newCard==null}">
                <td><b>Добавить карточку</b></td>
                <td></td>
                <td><a href="cards?action=add&type=${type}"><img src="img/add.png"></a></td>
                <td></td>
            </c:if>
            <c:if test="${newCard!=null}">
                <form method="post" action="cards?type=${type}">
                    <td><input type="text" value="${card.key}" name="key" required></td>
                    <td><input type="text" value="${card.value}" name="value" required></td>
                    <td>
                        <button type="submit">Сохранить</button>
                    </td>
                    <td>
                        <button onclick="window.history.back()" type="button">Cancel</button>
                    </td>
                </form>
            </c:if>
        </tr>
        <%--@elvariable id="cards" type="java.util.List"--%>
        <c:forEach items="${cards}" var="card">
            <jsp:useBean id="card" type="ru.secondmemory.dto.CardDto"/>
            <tr>
                <td><a href="cards?action=study&key=${card.key}">${card.key}</a></td>
                <td>${card.value}</td>
                <td><a href="cards?type=${type}&action=update&key=${card.key}"><img src="img/update.png"></a></td>
                <td><a href="cards?type=${type}&action=delete&key=${card.key}"><img src="img/delete.png"></a></td>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>