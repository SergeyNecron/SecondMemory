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

    <%--@elvariable id="type" type="ru.secondmemory.model.CardType"--%>
    <form method="post" action="cards?type=${type}">
        <table>
            <jsp:useBean id="card" type="ru.secondmemory.dto.CardListDto" scope="request"/>
            <tr>
                <td>
                    <dl>
                        <dt>Ключ:</dt>
                        <dd><input type="text" value="${card.key}" name="key" size=40 required class="card"></dd>
                    </dl>
                </td>
            </tr>
            <tr>
                <td>
                    <dl>
                        <dt>Значение:</dt>
                        <dd>
                            <input type="text" value="${card.value}" name="value" size=40 required class="card">
                        </dd>
                    </dl>
                </td>
            </tr>
            <tr>
                <td>
                    <dl>
                        <dt>Значения(перечислить через запятую):</dt>
                        <dd><textarea rows="35" cols="100" name="extra" required class="card">${card.extra}</textarea>
                        </dd>
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
