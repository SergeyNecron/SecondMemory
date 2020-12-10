<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <title>Редактировать</title>
</head>
<body>
<section>
    <h3><b>Редактировать</b></h3>
    <hr>
    <jsp:useBean id="card" type="ru.secondmemory.dto.CardDto" scope="request"/>
    <form method="post" action="cards?type=${type}">
        <table>
            <tr>
                <td>
                    <dl>
                        <dt>Ключ:</dt>
                        <dd><input type="text" value="${card.key}" size=40 name="key" required></dd>
                    </dl>
                </td>

                <td>
                    <dl>
                        <dt>Значение:</dt>
                        <dd><input type="text" value="${card.value}" size=100 name="value" required></dd>
                    </dl>
                </td>
            </tr>
        </table>
        <br>
        <button type="submit">Сохранить</button>
        <button onclick="window.history.back()" type="button">Cancel</button>
    </form>
</section>
</body>
</html>
