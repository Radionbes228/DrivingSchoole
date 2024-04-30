<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
    <form method="post" action="/students/create">
        <label>
            ФИО
            <input type="text" name="name"><br>
        </label>
        <label>
            Email
            <input type="text" name="email"><br>
        </label>
        <label>
            Пароль
            <input type="text" name="password"><br>
        </label>
        <label>
            Номер телефона
            <input type="text" name="phoneNumber"><br>
        </label>
        <label>
            Возраст
            <input type="text" name="age"><br>
        </label>
        <label>
            Группа
            <select name="group_id">
                <#if groups?has_content>
                    <#list groups as group>
                        <option value="${group.id}">${group.groupNumber}</option>
                    </#list>
                <#else>
                    <option value="null">Группы не созданы</option>
                </#if>
            </select><br>
        </label>
        <label hidden="hidden">
            <input hidden="hidden" name="roles" value="${role}">
        </label>
        <label>
            Статус обучения
            <select name="isStudying">
                <option value="true">Учиться</option>
                <option value="false">Успешно закончил</option>
            </select><br>
        </label>

        <input type="submit" value="Сохранить">
    </form>
</body>
</html>