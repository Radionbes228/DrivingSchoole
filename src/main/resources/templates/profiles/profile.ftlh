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

<nav class="navbar bg-body-tertiary fixed-top">
    <div class="container-fluid">
        <#if user_info.role == "ROLE_STUDENT">
            <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#left"
                    aria-controls="offcanvasNavbar" aria-label="Toggle navigation">
                Профиль ${user_info.name}
            </button>
            <a href="/logout"><button class="navbar-toggler" type="button" data-bs-toggle="offcanvas"
                                      aria-controls="offcanvasNavbar" aria-label="Toggle navigation">
                    Выйти
                </button></a>
        <#elseif user_info.role == "ROLE_INSTRUCTOR">
            <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#left"
                    aria-controls="offcanvasNavbar" aria-label="Toggle navigation">
                Профиль ${user_info.name}(инструктор)
            </button>
            <a href="/logout"><button class="navbar-toggler" type="button" data-bs-toggle="offcanvas"
                                      aria-controls="offcanvasNavbar" aria-label="Toggle navigation">
                    Выйти
                </button></a>
        <#elseif user_info.role == "ROLE_ADMIN">
        <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#left"
                aria-controls="offcanvasNavbar" aria-label="Toggle navigation">
            Профиль ${user_info.name}(админ)
        </button>
            <a href="/logout"><button class="navbar-toggler" type="button" data-bs-toggle="offcanvas"
                                      aria-controls="offcanvasNavbar" aria-label="Toggle navigation">
                    Выйти
                </button></a>
        </#if>

        <button class="navbar-toggler" type="button" data-bs-toggle="offcanvas" data-bs-target="#right"
                aria-controls="offcanvasNavbar" aria-label="Toggle navigation">
            Навигация
        </button>

        <#if user_info.role == "ROLE_STUDENT">
            <div class="offcanvas offcanvas-start" tabindex="-1" id="left" aria-labelledby="offcanvasNavbarLabel">
                <div class="offcanvas-header">
                    <h5 class="offcanvas-title" id="offcanvasNavbarLabel">Профиль</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
                </div>
                <div class="offcanvas-body">
                    <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
                        <li class="nav-item">
                            <span class="input-group-text" id="name">Имя</span>
                            <h6 class="nav-link active" aria-current="page"
                                aria-describedby="name">${user_info.firstName} ${user_info.name}</h6>
                        </li>
                        <li class="nav-item">
                            <span class="input-group-text" id="email">Почта</span>
                            <h6 class="nav-link" aria-describedby="email">${user_info.email}</h6>
                        </li>
                        <li class="nav-item">
                            <span class="input-group-text" id="phoneNumber">Номер телефона</span>
                            <h6 class="nav-link" aria-describedby="phoneNumber">${user_info.phoneNumber}</h6>
                        </li>
                        <li class="nav-item">
                            <span class="input-group-text" id="ahe">Возраст</span>
                            <h6 class="nav-link" aria-describedby="age">Год рождения: ${user_info.birthday}</h6>
                        </li>
                        <li class="nav-item">
                            <span class="input-group-text" id="ahe">Возраст</span>
                            <h6 class="nav-link" aria-describedby="age">Группа: ${user_info.group.groupNumber}</h6>
                        </li>
                    </ul>
                </div>
            </div>



            <div class="offcanvas offcanvas-end" tabindex="-1" id="right" aria-labelledby="offcanvasNavbarLabel">
                <div class="offcanvas-header">
                    <h5 class="offcanvas-title" id="offcanvasNavbarLabel">Навигация</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
                </div>
                <div class="offcanvas-body">
                    <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
                        <li class="nav-item">
                            <form method="get" action="/student/group/${user_info.group.id}/${select_week}">
                                <input class="nav-link active" aria-current="page" type="submit"
                                       value="Список студентов группы">
                            </form>
                        </li>
                        <li class="nav-item">
                            <form action="/instructor/list/${select_week}" method="get">
                                <input class="nav-link active" aria-current="page" type="submit" value="Список инструкторов">
                            </form>
                        </li>
                    </ul>
                </div>
            </div>

        <#elseif user_info.role == "ROLE_INSTRUCTOR">
            <div class="offcanvas offcanvas-start" tabindex="-1" id="left" aria-labelledby="offcanvasNavbarLabel">
                <div class="offcanvas-header">
                    <h5 class="offcanvas-title" id="offcanvasNavbarLabel">Профиль</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
                </div>
                <div class="offcanvas-body">
                    <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
                        <li class="nav-item">
                            <span class="input-group-text" id="name">Имя</span>
                            <h6 class="nav-link active" aria-current="page"
                                aria-describedby="name">${user_info.firstName} ${user_info.name}</h6>
                        </li>
                        <li class="nav-item">
                            <span class="input-group-text" id="email">Почта</span>
                            <h6 class="nav-link" aria-describedby="email">${user_info.email}</h6>
                        </li>
                        <li class="nav-item">
                            <span class="input-group-text" id="phoneNumber">Номер телефона</span>
                            <h6 class="nav-link" aria-describedby="phoneNumber">${user_info.phoneNumber}</h6>
                        </li>
                        <li class="nav-item">
                            <span class="input-group-text" id="ahe">Возраст</span>
                            <h6 class="nav-link" aria-describedby="age">Год рождения: ${user_info.birthday}</h6>
                        </li>
                    </ul>
                </div>
            </div>



            <div class="offcanvas offcanvas-end" tabindex="-1" id="right" aria-labelledby="offcanvasNavbarLabel">
                <div class="offcanvas-header">
                    <h5 class="offcanvas-title" id="offcanvasNavbarLabel">Навигация</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
                </div>
                <div class="offcanvas-body">
                    <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                               aria-expanded="false">
                                Список курированных групп
                            </a>
                            <ul class="dropdown-menu">
                                <#if user_info.groups?has_content>
                                    <#list user_info.groups as group>
                                        <li><a class="dropdown-item"
                                               href="/group/schedule/${group.id}/${select_week}">Группа: ${group.groupNumber}</a>
                                        </li>
                                    </#list>
                                <#else>
                                    <li><p class="dropdown-item">Курированных групп нет</p></li>
                                </#if>
                            </ul>
                        </li>
                        <li class="nav-item">
                            <form action="/instructor/list/${select_week}" method="get">
                                <input class="nav-link active" aria-current="page" type="submit" value="Список инструкторов">
                            </form>
                        </li>
                    </ul>
                </div>
            </div>

        <#elseif user_info.role == "ROLE_ADMIN">
            <div class="offcanvas offcanvas-start" tabindex="-1" id="left" aria-labelledby="offcanvasNavbarLabel">
                <div class="offcanvas-header">
                    <h5 class="offcanvas-title" id="offcanvasNavbarLabel">Профиль</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
                </div>
                <div class="offcanvas-body">
                    <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
                        <li class="nav-item">
                            <span class="input-group-text" id="name">Имя</span>
                            <h6 class="nav-link active" aria-current="page"
                                aria-describedby="name">${user_info.firstName} ${user_info.name}</h6>
                        </li>
                        <li class="nav-item">
                            <span class="input-group-text" id="email">Почта</span>
                            <h6 class="nav-link" aria-describedby="email">${user_info.email}</h6>
                        </li>
                        <li class="nav-item">
                            <span class="input-group-text" id="phoneNumber">Номер телефона</span>
                            <h6 class="nav-link" aria-describedby="phoneNumber">${user_info.phoneNumber}</h6>
                        </li>
                        <li class="nav-item">
                            <span class="input-group-text" id="ahe">Возраст</span>
                            <h6 class="nav-link" aria-describedby="age">Год рождения: ${user_info.birthday}</h6>
                        </li>
                    </ul>
                </div>
            </div>



            <div class="offcanvas offcanvas-end" tabindex="-1" id="right" aria-labelledby="offcanvasNavbarLabel">
                <div class="offcanvas-header">
                    <h5 class="offcanvas-title" id="offcanvasNavbarLabel">Навигация</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
                </div>
                <div class="offcanvas-body">
                    <ul class="navbar-nav justify-content-end flex-grow-1 pe-3">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown"
                               aria-expanded="false">
                                Список групп
                            </a>
                            <ul class="dropdown-menu">
                                <#if groups?has_content>
                                    <#list groups as group>
                                        <li>
                                            <form action="/student/group/${group.id}/${select_week}" method="get">
                                                <input class="dropdown-item" aria-current="page" type="submit" value="Группа: ${group.groupNumber}">
                                            </form>
                                        </li>
                                    </#list>
                                <#else>
                                    <li><p class="dropdown-item">Групп нет</p></li>
                                </#if>
                            </ul>
                        </li>
                        <li class="nav-item">
                            <form action="/instructor/list/${select_week}" method="get">
                                <input class="nav-link active" aria-current="page" type="submit" value="Список инструкторов">
                            </form>
                        </li>
                        <li class="nav-item">
                            <form action="/student/all/${select_week}" method="get">
                                <input class="nav-link active" aria-current="page" type="submit" value="Список всех студентов">
                            </form>
                        </li>
                        <li class="nav-item">
                            <form action="/student/create/${select_week}" method="get">
                                <input class="nav-link active" aria-current="page" type="submit" value="Создать студента">
                            </form>
                        </li>
                        <li class="nav-item">
                            <form action="/instructor/create/${select_week}" method="get">
                                <input class="nav-link active" aria-current="page" type="submit" value="Создать инструктора">
                            </form>
                        </li>
                        <li class="nav-item">
                            <form action="/group/create/${select_week}" method="get">
                                <input class="nav-link active" aria-current="page" type="submit" value="Создать группу">
                            </form>
                        </li>
                    </ul>
                </div>
            </div>
        </#if>
    </div>
</nav>

<#if user_info.role == "ROLE_STUDENT">
    <div class="container text-center" style="margin-top: 50px">
        <div class="row align-items-center">
            <div class="col" style="margin: 0 auto; width: 800px">
                <h2>Расписание</h2>
                <div class="dropdown">
                    <button class="btn btn-dark dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                        ${select_week_info.startDateOfWeek}
                    </button>

                    <ul class="dropdown-menu dropdown-menu-dark" style="overflow: scroll; height: 300px;">
                        <#list weeks as week>
                            <li><a class="dropdown-item" href="/student/profile/${week.id}">${week.startDateOfWeek}</a></li>
                        </#list>
                    </ul>
                </div>
            </div>
        </div>
    </div>
<#elseif user_info.role == "ROLE_ADMIN">
        <div class="container text-center" style="margin-top: 50px">
            <div class="row align-items-center">
                <div class="col" style="margin: 0 auto; width: 800px">
                    <h2>Расписание</h2>
                    <div class="dropdown">
                        <button class="btn btn-dark dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                            ${select_week_info.startDateOfWeek}
                        </button>

                        <ul class="dropdown-menu dropdown-menu-dark" style="overflow: scroll; height: 300px;">
                            <#list weeks as week>
                                <li><a class="dropdown-item" href="/admin/profile/${week.id}">${week.startDateOfWeek}</a></li>
                            </#list>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    <#elseif user_info.role == "ROLE_INSTRUCTOR">
        <div class="container text-center" style="margin-top: 50px">
            <div class="row align-items-center">
                <div class="col" style="margin: 0 auto; width: 800px">
                    <h2>Расписание</h2>
                    <div class="dropdown">
                        <button class="btn btn-dark dropdown-toggle" data-bs-toggle="dropdown" aria-expanded="false">
                            ${select_week_info.startDateOfWeek}
                        </button>

                        <ul class="dropdown-menu dropdown-menu-dark" style="overflow: scroll; height: 300px;">
                            <#list weeks as week>
                                <li><a class="dropdown-item" href="/instructor/profile/${week.id}">${week.startDateOfWeek}</a></li>
                            </#list>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
</#if>

<#if user_info.role == "ROLE_STUDENT">
<div class="container text-center">
    <div class="row align-items-center" style="display: inline">
        <div class="col">
            <span class="input-group-text" id="basic-addon1">Понедельник</span>
            <#if record_of_weeks?has_content>
                <#list record_of_weeks as record>
                    <#if record.dayOfWeek == "Понедельник" && record.groupId == user_info.group.id>
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
            <span class="input-group-text" id="basic-addon1">Вторник</span>
            <#if record_of_weeks?has_content>
                <#list record_of_weeks as record>
                    <#if record.dayOfWeek == "Вторник" && record.groupId == user_info.group.id>
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
            <span class="input-group-text" id="basic-addon1">Среда</span>
            <#if record_of_weeks?has_content>
                <#list record_of_weeks as record>
                    <#if record.dayOfWeek == "Среда" && record.groupId == user_info.group.id>
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
            <span class="input-group-text" id="basic-addon1">Четверг</span>
            <#if record_of_weeks?has_content>
                <#list record_of_weeks as record>
                    <#if record.dayOfWeek == "Четверг" && record.groupId == user_info.group.id>
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
            <span class="input-group-text" id="basic-addon1">Пятница</span>
            <#if record_of_weeks?has_content>
                <#list record_of_weeks as record>
                    <#if record.dayOfWeek == "Пятница" && record.groupId == user_info.group.id>
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
<#else>
    <div class="container text-center">
        <div class="row align-items-center" style="display: inline">
            <div class="col">
                <span class="input-group-text" id="basic-addon1">Понедельник</span>
                <#if record_of_weeks?has_content>
                    <#list record_of_weeks as record>
                        <#if record.dayOfWeek == "Понедельник">
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
                <span class="input-group-text" id="basic-addon1">Вторник</span>
                <#if record_of_weeks?has_content>
                    <#list record_of_weeks as record>
                        <#if record.dayOfWeek == "Вторник">
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
                <span class="input-group-text" id="basic-addon1">Среда</span>
                <#if record_of_weeks?has_content>
                    <#list record_of_weeks as record>
                        <#if record.dayOfWeek == "Среда">
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
                <span class="input-group-text" id="basic-addon1">Четверг</span>
                <#if record_of_weeks?has_content>
                    <#list record_of_weeks as record>
                        <#if record.dayOfWeek == "Четверг">
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
                <span class="input-group-text" id="basic-addon1">Пятница</span>
                <#if record_of_weeks?has_content>
                    <#list record_of_weeks as record>
                        <#if record.dayOfWeek == "Пятница">
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
</#if>

<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
        integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js"
        integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy"
        crossorigin="anonymous"></script>

</body>
</html>