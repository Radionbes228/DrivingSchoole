<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" type="text/css" href="/static/css/default_style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

    <title>Document</title>
</head>
<body>


<div class="container text-center">
    <div class="row align-items-center">
        <div class="col">
            <form method="post" action="/instructor/record" class="form">
                <div style="max-width: 700px; margin: 0 auto; align-content: center">
                    <p class="h3">Запись студента</p>
                    <div class="input-group mb-3">
                        <span class="input-group-text" id="basic-addon1">Неделя</span>
                        <input class="form-control" type="text" aria-label="basic-addon1" value="${week.startDateOfWeek} - ${week.lastDateOfWeek}">
                        <input hidden name="weekId" type="text" value="${week.id}">
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text" id="basic-addon1">День недели</span>
                        <input class="form-control" type="text" aria-label="basic-addon1" value="${day.dayName}" readonly>
                        <input hidden name="dayOfDayId" type="text" value="${day.id}">
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text" id="basic-addon1">Студент</span>
                        <select name="studentId" class="form-select" id="floatingSelect" aria-label="Floating label select example">
                            <#if students?has_content>
                                <#list students as student>
                                    <option value="${student.id}">${student.name}</option>
                                </#list>
                            <#else>
                                <option value="null">Пусто</option>
                            </#if>
                        </select>
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text" id="basic-addon1">Время</span>
                        <select name="timeId" class="form-select" id="floatingSelect" aria-label="Floating label select example">
                            <#if times?has_content>
                                <#list times as time>
                                    <option value="${time.id}">${time.time}</option>
                                </#list>
                            <#else>
                                <option value="null">Пусто</option>
                            </#if>
                        </select>
                    </div>

                    <div class="container text-center">
                        <div class="row align-items-center">
                            <div class="col">
                                <input class="btn btn-outline-primary" type="submit" value="Создать">
                            </div>
                            <div class="col">
                                <a href="/instructor/profile/${week.id}"><input class="btn btn-outline-primary" type="button" value="Назад"></a>
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
</body>
</html>