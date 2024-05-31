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
    <title>Info</title>
</head>
<body>
<div style="margin: 0 auto; width: 500px">
    <div class="container text-center" style="margin-top: 50px">
        <div class="row align-items-center">
            <div class="col">
                <span class="input-group-text" style="text-align: left">Группа: ${group.groupNumber}</span>
            </div>
            <div class="col">
                <span class="input-group-text"
                      style="text-align: left">${instructor.firstName} ${instructor.name}</span>
            </div>
             <#if role == "ROLE_ADMIN">
                 <div class="col">
                     <a href="/group/update/${group.id}/${select_week}"><input class="btn btn-outline-primary" type="button" value="Изменить"></a>
                 </div>
                 <div class="col">
                     <form action="/group/delete" method="post">
                         <input name="group_id" hidden="hidden" type="text" value="${group.id}">
                         <input name="id_week" hidden="hidden" type="text" value="${select_week}">
                         <input class="btn btn-outline-primary" type="submit" value="Удалить">
                     </form>
                 </div>
             </#if>
        </div>
    </div>
</div>

<div style="margin: 0 auto; overflow: auto">
    <#if students?has_content>
        <#list students as student>
            <div class="list-group-item list-group-item-action">
                <div class="container text-center">
                    <div class="row align-items-left">
                        <div class="col">
                            <#if student.lastName??>
                                <h6 style="text-align: left">${student.firstName} ${student.name} ${student.lastName}</h6>
                            <#else>
                                <h6 style="text-align: left">${student.firstName} ${student.name}</h6>
                            </#if>
                        </div>
                        <div class="col">
                            <h6 style="text-align: left">${student.email}</h6>
                        </div>
                        <div class="col">
                            <h6 style="text-align: left">${student.phoneNumber}</h6>
                        </div>
                        <div class="col">
                            <h6 style="text-align: left">${student.birthday}</h6>
                        </div>
                    </div>
                </div>
            </div>
        </#list>
    <#else>
        Студентов нету
    </#if>
</div>
<div class="container text-center">
    <div class="row align-items-center">
        <div class="col">
            <#if role == "ROLE_STUDENT">
                <#if select_week?has_content>
                    <a href="/student/profile/${select_week}"><input class="btn btn-outline-primary" type="button"
                                                                     value="Назад"></a>
                <#else>
                    <a href="#"><input class="btn btn-outline-primary" type="button" value="Назад"></a>
                </#if>
            <#elseif role == "ROLE_INSTRUCTOR">
                <a href="/group/schedule/${group.id}/${select_week}"><input class="btn btn-outline-primary" type="button"
                                                                 value="Назад"></a>
            <#elseif role == "ROLE_ADMIN">
                <a href="/admin/profile/${select_week}"><input class="btn btn-outline-primary" type="button" value="Назад"></a>
            </#if>
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