<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Информация о группе</title>
</head>
<body>
    <h4>Группа: ${group.groupNumber}</h4><br>
    <h4>Инструктор: ${instructor.name}</h4><br>
    <h4>Количество учеников: ${count}</h4><br>
    <a href="/groups/update/${group.id}">Редактировать</a>
</body>
</html>