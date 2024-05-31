<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" type="text/css" href="/static/css/default_style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

    <title>Document</title>
</head>
<body>

<nav class="navbar navbar-expand-lg bg-body-tertiary">
    <div class="container-fluid">
        <h4 class="navbar-brand">Группа ${id_group}</h4>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <form method="get" action="/student/group/${id_group}/${select_week}">
                        <input class="nav-link active" aria-current="page" type="submit"
                               value="Список студентов группы">
                    </form>
                </li>
            </ul>
        </div>
    </div>
</nav>


<div class="container text-center">
    <div class="row align-items-center" style="display: inline">
        <div class="col">
            <span class="input-group-text" id="basic-addon1">Понедельник
                <a href="/instructor/record/${id_group}/1/${select_week}"><input class="btn btn-outline-primary" type="button" value="Записать"></a>
            </span>
            <#if record_of_weeks?has_content>
                <#list record_of_weeks as record>
                    <#if record.dayOfWeek == "Понедельник" && record.groupId == id_group>
                        <div class="list-group-item list-group-item-action" style="margin-right: 10px">
                            <div class="container text-center">
                                <div class="row align-items-left">
                                    <div class="col ">
                                        ${record.time}
                                    </div>
                                    <div class="col">
                                        <h6 style="text-align: left">${record.firstName} ${record.name}</h6>
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
            <span class="input-group-text" id="basic-addon1">Вторник
                <a href="/instructor/record/${id_group}/2/${select_week}"><input class="btn btn-outline-primary" type="button" value="Записать"></a>
            </span>
            <#if record_of_weeks?has_content>
                <#list record_of_weeks as record>
                    <#if record.dayOfWeek == "Вторник" && record.groupId == id_group>
                        <div class="list-group-item list-group-item-action" style="margin-right: 10px">
                            <div class="container text-center">
                                <div class="row align-items-left">
                                    <div class="col">
                                        ${record.time}
                                    </div>
                                    <div class="col">
                                        <h6 style="text-align: left">${record.firstName} ${record.name}</h6>
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
            <span class="input-group-text" id="basic-addon1">Среда
                <a href="/instructor/record/${id_group}/3/${select_week}"><input class="btn btn-outline-primary" type="button" value="Записать"></a>
            </span>
            <#if record_of_weeks?has_content>
                <#list record_of_weeks as record>
                    <#if record.dayOfWeek == "Среда" && record.groupId == id_group>
                        <div class="list-group-item list-group-item-action" style="margin-right: 10px">
                            <div class="container text-center">
                                <div class="row align-items-left">
                                    <div class="col">
                                        ${record.time}
                                    </div>
                                    <div class="col">
                                        <h6 style="text-align: left">${record.firstName} ${record.name}</h6>
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
            <span class="input-group-text" id="basic-addon1">Четверг
                <a href="/instructor/record/${id_group}/4/${select_week}"><input class="btn btn-outline-primary" type="button" value="Записать"></a>
            </span>
            <#if record_of_weeks?has_content>
                <#list record_of_weeks as record>
                    <#if record.dayOfWeek == "Четверг" && record.groupId == id_group>
                        <div class="list-group-item list-group-item-action" style="margin-right: 10px">
                            <div class="container text-center">
                                <div class="row align-items-left">
                                    <div class="col">
                                        ${record.time}
                                    </div>
                                    <div class="col">
                                        <h6 style="text-align: left">${record.firstName} ${record.name}</h6>
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
            <span class="input-group-text" id="basic-addon1">Пятница
                <a href="/instructor/record/${id_group}/5/${select_week}"><input class="btn btn-outline-primary" type="button" value="Записать"></a>
            </span>
            <#if record_of_weeks?has_content>
                <#list record_of_weeks as record>
                    <#if record.dayOfWeek == "Пятница" && record.groupId == id_group>
                        <div class="list-group-item list-group-item-action" style="margin-right: 10px">
                            <div class="container text-center">
                                <div class="row align-items-left">
                                    <div class="col">
                                        ${record.time}
                                    </div>
                                    <div class="col">
                                        <h6 style="text-align: left">${record.firstName} ${record.name}</h6>
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


<div class="container text-center">
    <div class="row align-items-center">
        <div class="col">
            <a href="/instructor/profile/${select_week}"><input class="btn btn-outline-primary" type="button" value="Назад"></a>
        </div>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
        integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
        crossorigin="anonymous"></script>


</body>
</html>