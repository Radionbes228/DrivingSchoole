 <!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" type="text/css" href="/static/css/default_style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

    <title>Инструкторы</title>
</head>
<body>



<div class="container text-center">
    <div class="row align-items-center">
        <div class="col">
            <p class="h2">Список инструкторов</p>

            <div class="container text-center" style="padding-right: 150px">
                <div class="row align-items-center">
                    <div class="col">
                        <p>ФИО</p>
                    </div>
                    <div class="col">
                        <p>Почта</p>
                    </div>
                    <div class="col">
                        <p>Номер телефона</p>
                    </div>
                </div>
            </div>


            <div class="list-group">
                <#if instructors?has_content>
                    <#list instructors as instructor>
                        <div style="display: flex">
                            <div class="list-group-item list-group-item-action" style="margin-right: 10px">
                                <div class="container text-center">
                                    <div class="row align-items-left">
                                        <div class="col">
                                            <h6>${instructor.name}</h6>
                                        </div>
                                        <div class="col">
                                            <h6>${instructor.email}</h6>
                                        </div>
                                        <div class="col">
                                            <h6>${instructor.phoneNumber}</h6>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </#list>
                <#else>
                    <p class="h3">Инструкторов нет</p>
                </#if>
            </div>
        </div>
    </div>
</div>

    <div class="container text-center">
        <div class="row align-items-center">
            <div class="col">
                <#if role = "ROLE_STUDENT">
                    <#if select_week?has_content>
                        <a href="/student/profile/${select_week}"><input class="btn btn-outline-primary" type="button" value="Назад"></a>
                    <#else>
                        <a href="#"><input class="btn btn-outline-primary" type="button" value="Назад"></a>
                    </#if>
                <#elseif role = "ROLE_INSTRUCTOR">
                    <#if select_week?has_content>
                        <a href="/instructor/profile/${select_week}"><input class="btn btn-outline-primary" type="button" value="Назад"></a>
                    <#else>
                        <a href="#"><input class="btn btn-outline-primary" type="button" value="Назад"></a>
                    </#if>
                </#if>

            </div>
        </div>
    </div>



<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>

</body>
</html>