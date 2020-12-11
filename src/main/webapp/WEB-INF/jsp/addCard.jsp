<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <link rel="stylesheet" href="css/style.css">
    <title>Новая карточка</title>
</head>
<body>
<section>
    <h3><b>Новая карточка</b></h3>
    <hr>

    <%--@elvariable id="type" type="ru.secondmemory.model.CardType"--%>
    <form method="post" action="cards?type=${type}">
        <table class="card">
            <tr>
                <td>
                    <dl>
                        <dt>Ключ:</dt>
                        <dd>
                            <input type="text" size=40 name="key" required class="card">
                        </dd>
                    </dl>
                </td>
            </tr>
            <tr>
                <td>
                    <dl>
                        <dt>Значения(перечислить через запятую):</dt>
                        <dd>
                            <textarea rows="40" cols="100" name="value" required class="card"></textarea>
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
