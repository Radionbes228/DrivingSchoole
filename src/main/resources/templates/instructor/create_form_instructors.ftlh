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

    <form action="/instructors/create" method="post">
        <label>
            ФИО
            <input type="text" name="name">
        </label><br>
        <label>
            Почта
            <input type="text" name="email">
        </label><br>
        <label>
            Пароль
            <input type="text" name="password">
        </label><br>
        <label>
            Возраст
            <input type="number" name="age">
        </label><br>
        <label>
            Номер телефона
            <input type="number" name="phoneNumber">
        </label><br>
        <label hidden="hidden">
            <input type="text" name="role" value="${role}">
        </label><br>
        <input type="submit" value="Сохранить">
    </form>

</body>
</html>