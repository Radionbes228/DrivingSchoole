<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Создание</title>
</head>
<body>
    <form action="/groups/create" method="post">
        <label>
            Номер группы
            <input name="groupNumber" type="number" min="1">
        </label><br>
        <label>
            Инструктор
            <select name="instructorId">
                <#if instructors?has_content>
                    <#list instructors as instructor>
                        <option value="${instructor.id}">${instructor.name}</option>
                    </#list>
                <#else>
                    <option>Инструкторов нет</option>
                </#if>

            </select>
        </label><br>
        <input type="submit" value="Сохранить">
    </form>
</body>
</html>