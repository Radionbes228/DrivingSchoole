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

    <form action="schedules/update" method="post">
        <label>
            <input name="year" type="number" value="${year}" readonly>
        </label><br>
        <label>
            <input name="start_week" type="date" value="${schedule.startWeek}">
        </label><br>
        <label>
            <input name="end_week" type="date" value="${schedule.endWeek}">
        </label><br>
        <input type="submit" value="Сохранить">
    </form>
    <a href="/schedules" methods="get">На главную</a>


</body>
</html>