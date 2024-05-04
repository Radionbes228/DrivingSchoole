<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Создать</title>
</head>
<body>

    <form action="/schedules/create" method="post">
        <label>
            Год: ${year}
            <input hidden="hidden" name="year" type="text" value="${year}" readonly>
        </label><br>
        <label>
            Дата начала учебной недели
            <input name="startWeek" type="date">
        </label><br>
        <label>
            Дата конца учебной недели
            <input name="endWeek" type="date">
        </label><br>
        <input type="submit" value="Сохранить">
    </form>
    <a href="/schedules" methods="get">На главную</a>
</body>
</html>