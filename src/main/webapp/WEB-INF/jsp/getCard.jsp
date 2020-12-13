<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <title>Просмотр</title>
</head>
<body>
<section>
    <h3><a href="cards?type=${type}">Назад</a></h3>
    <hr>
    <%--@elvariable id="type" type="ru.secondmemory.model.CardType"--%>
    <table>
        <jsp:useBean id="card" type="ru.secondmemory.dto.CardDto" scope="request"/>
        <tr>
            <td>
                <dl>
                    <dd class="card">${card.key}</dd>
                </dl>
            </td>
        </tr>
        <tr>
            <td>
                <dl>
                    <dd class="card">${card.value}</dd>
                    </dd>
                </dl>
            </td>
        </tr>
    </table>

</section>
</body>
</html>
