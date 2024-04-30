<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Update student</title>
</head>
<body>
    <form method="post" action="/students/update">
        <label>
            <input type="text" name="name" value="${student.name}"><br>
        </label>
        <label>
            <input type="text" name="email" value="${student.email}"><br>
        </label>
        <label>
            <input type="text" name="password" value="${student.password}"><br>
        </label>
        <label>
            <input type="text" name="phoneNumber" value="${student.phoneNumber}"><br>
        </label>
        <label>
            <input type="text" name="age" value="${student.age}"><br>
        </label>
        <label>
            <select name="group_id">
                <option value="${group.id}">${group.groupNumber}</option>
                <#if groups?has_content>
                    <#list groups as group>
                        <option value="${group.id}">${group.groupNumber}</option>
                    </#list>
                <#else>
                    <option value="null">Группы не созданы</option>
                </#if>
            </select>
        </label>
        <label>
            <select name="isStudying">
                <option value="true">Учиться</option>
                <option value="false">Успешно закончил</option>
            </select>
        </label>

        <input type="submit" value="Сохранить">
    </form>
</body>
</html>