<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <title>${type.title}</title>
</head>
<body>
<section>
    <h3><a href="cards">Назад</a></h3>
    <hr/>
    <%--@elvariable id="type" type="ru.secondmemory.model.CardType"--%>
    <h2>${type.title}</h2>
    <table class="table">
        <tr>
            <%--@elvariable id="action" type="java.lang.String"--%>
            <c:if test="${action==null}">
                <td><b>Добавить карточку</b></td>
                <td></td>
                <td><a href="cards?action=add&type=${type}"><img src="img/add.png"></a></td>
                <td></td>
            </c:if>
            <c:if test="${action=='add'}">
                <form method="post" action="cards?type=${type}">
                        <%--@elvariable id="newCard" type="ru.secondmemory.dto.CardDto"--%>
                    <td><input type="text" value="${newCard.key}" name="key" required></td>
                    <td><input type="text" value="${newCard.value}" name="value" required></td>
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
                <c:if test="${card.key != key}">
                    <td><a href="cards?action=study&key=${card.key}">${card.key}</a></td>
                    <td>${card.value}</td>
                    <td><a href="cards?type=${type}&action=update&key=${card.key}"><img src="img/update.png"></a></td>
                    <td><a href="cards?type=${type}&action=delete&key=${card.key}"><img src="img/delete.png"></a></td>
                </c:if>
                    <%--@elvariable id="key" type="java.lang.String"--%>
                <c:if test="${action=='update' && card.key == key}">
                    <form method="post" action="cards?type=${type}">
                            <%--@elvariable id="newCard" type="ru.secondmemory.dto.CardDto"--%>
                        <td><input type="text" value="${newCard.key}" name="key" required></td>
                        <td><input type="text" value="${newCard.value}" name="value" required></td>
                        <td>
                            <button type="submit">Сохранить</button>
                        </td>
                        <td>
                            <button onclick="window.history.back()" type="button">Cancel</button>
                        </td>
                    </form>
                </c:if>
            </tr>
        </c:forEach>
    </table>
</section>
</body>
</html>