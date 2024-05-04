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

    <h4>Имя: ${instructor.name}</h4><br>
    <h4>Почта: ${instructor.email}</h4><br>
    <h4>Возраст: ${instructor.age}</h4><br>
    <h4>Номер телефона: ${instructor.phoneNumber}</h4><br>
    <form action="/instructors/update/${instructor.id}" method="get"><input value="Редактировать" type="submit"></form>
    <form action="/instructors" method="get"><input value="Главная" type="submit"></form>

</body>
</html>