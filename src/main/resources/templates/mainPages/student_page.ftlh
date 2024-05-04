<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" type="text/css" href="/static/css/create_student.css">
    <title>Document</title>
</head>
<body>

<form class="form">
    <p class="title">Register </p>
    <p class="message">Signup now and get full access to our app. </p>

    <label>
        ФИО
        <input type="text" name="name" class="input"><br>
    </label>
    <label>
        Email
        <input type="text" name="email" class="input"><br>
    </label>
    <label>
        Пароль
        <input type="text" name="password" class="input"><br>
    </label>
    <label>
        Номер телефона
        <input type="text" name="phoneNumber" class="input"><br>
    </label>
    <label>
        Возраст
        <input type="text" name="age" class="input"><br>
    </label>
    <label>
        Группа
        <select name="group_id" class="input">
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
        <select name="isStudying" class="input">
            <option value="true">Учиться</option>
            <option value="false">Успешно закончил</option>
        </select><br>
    </label>

    <button class="submit">Submit</button>
    <p class="signin">Already have an acount ? <a href="#">Signin</a> </p>
</form>

</body>
</html>