<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" type="text/css" href="/static/default_style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

    <title>Document</title>
</head>
<body>

<div class="container text-center">
    <div class="row align-items-center">
        <div class="col" style="margin: 0 auto; width: 800px">
            <h2>Расписание</h2>
            <form action="/student-weeks" method="post">
                <label>
                    <select name="id_week" class="form-select">
                        <#if weeks?has_content>
                            <#list weeks as week>
                                <option value="${week.id}">${week.startWeek}</option>
                            </#list>
                        </#if>
                    </select>
                </label>
                <input type="submit" value="Применить">
            </form>
            <h6>${week.startWeek} - ${week.endWeek}</h6>
        </div>
    </div>
</div>

<div class="container text-center">
    <div class="row align-items-center">
        <div class="col">
            <span class="input-group-text" id="basic-addon1">Понедельник</span>
            <#if schedule_preview?has_content>
                <#list schedule_preview as preview>
                    <#if preview.dayOfWeek == "Понедельник">
                        <div class="list-group-item list-group-item-action" style="margin-right: 10px">
                            <div class="container text-center">
                                <div class="row align-items-left">
                                    <div class="col ">
                                        ${preview.time}
                                    </div>
                                    <div class="col">
                                        <h6 style="text-align: left">${preview.nameStudent}</h6>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </#if>
                </#list>
            <#else>
                <h6>Свободно</h6>
            </#if>

        </div>
        <div class="col">
            <span class="input-group-text" id="basic-addon1">Вторник</span>
            <#if schedule_preview?has_content>
                <#list schedule_preview as preview>
                    <#if preview.dayOfWeek == "Вторник">
                        <div class="list-group-item list-group-item-action" style="margin-right: 10px">
                            <div class="container text-center">
                                <div class="row align-items-left">
                                    <div class="col">
                                        ${preview.time}
                                    </div>
                                    <div class="col">
                                        <h6 style="text-align: left">${preview.nameStudent}</h6>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </#if>
                </#list>
            <#else>
                <h6>Свободно</h6>
            </#if>
        </div>
        <div class="col">
            <span class="input-group-text" id="basic-addon1">Среда</span>
            <#if schedule_preview?has_content>
                <#list schedule_preview as preview>
                    <#if preview.dayOfWeek == "Среда">
                        <div class="list-group-item list-group-item-action" style="margin-right: 10px">
                            <div class="container text-center">
                                <div class="row align-items-left">
                                    <div class="col">
                                        ${preview.time}
                                    </div>
                                    <div class="col">
                                        <h6 style="text-align: left">${preview.nameStudent}</h6>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </#if>
                </#list>
            <#else>
                <h6>Свободно</h6>
            </#if>
        </div>
        <div class="col">
            <span class="input-group-text" id="basic-addon1">Четверг</span>
            <#if schedule_preview?has_content>
                <#list schedule_preview as preview>
                    <#if preview.dayOfWeek == "Четверг">
                        <div class="list-group-item list-group-item-action" style="margin-right: 10px">
                            <div class="container text-center">
                                <div class="row align-items-left">
                                    <div class="col">
                                        ${preview.time}
                                    </div>
                                    <div class="col">
                                        <h6 style="text-align: left">${preview.nameStudent}</h6>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </#if>
                </#list>
            <#else>
                <h6>Свободно</h6>
            </#if>
        </div>
        <div class="col">
            <span class="input-group-text" id="basic-addon1">Пятница</span>
            <#if schedule_preview?has_content>
                <#list schedule_preview as preview>
                    <#if preview.dayOfWeek == "Пятница">
                        <div class="list-group-item list-group-item-action" style="margin-right: 10px">
                            <div class="container text-center">
                                <div class="row align-items-left">
                                    <div class="col">
                                        ${preview.time}
                                    </div>
                                    <div class="col">
                                        <h6 style="text-align: left">${preview.nameStudent}</h6>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </#if>
                </#list>
            <#else>
                <h6>Свободно</h6>
            </#if>
        </div>
    </div>
</div>



















<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
</body>
</html>