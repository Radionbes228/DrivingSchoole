<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Неделя</title>
</head>
<body>
    <label>
        Год: ${year}
    </label><br>
    <label>
        ${schedule.startWeek}
    </label><br>
    <label>
        ${schedule.endWeek}
    </label><br>
    <a href="/schedules/update/${schedule.id}" methods="get">Редактировать</a>
    <a href="/schedules" methods="get">На главную</a>
</body>
</html>