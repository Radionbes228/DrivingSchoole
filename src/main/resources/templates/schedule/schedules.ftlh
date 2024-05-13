<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" type="text/css" href="/static/default_style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

    <title>История расписания</title>
</head>
<body>

<div class="container text-center" style="padding-right: 150px">
    <div class="row align-items-left">
        <div class="col">
            <a href="/schedules"><button class="btn btn-outline-primary">Назад</button></a>
        </div>
    </div>
</div>

<div style="margin: 0 auto; overflow: scroll; width: 800px; height: 600px">
    <div class="list-group">
        <#if schedules?has_content>
            <#list schedules as schedule>
                <div style="display: flex">
                    <div class="list-group-item list-group-item-action" style="margin-right: 10px">
                        <div class="container text-center">
                            <div class="row align-items-left">
                                <div class="col">
                                    <h6 style="text-align: left">${schedule.startWeek}</h6>
                                </div>
                                <div class="col">
                                    <h6 style="text-align: left">${schedule.endWeek}</h6>
                                </div>
                                <div class="col">
                                    <h6 style="text-align: left">Год: ${schedule.year}</h6>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
        <#else>
            <p class="h3">Недели не добавлены</p>
        </#if>
    </div>
</div>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
</body>
</html>