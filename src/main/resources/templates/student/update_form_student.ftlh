<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" type="text/css" href="/static/default_style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
    <title>Update student</title>
</head>
<body>


    <div class="container text-center">
        <div class="row align-items-center">
            <div class="col">
                <form method="post" action="/students/update" class="form">
                    <div style="max-width: 700px; margin: 0 auto; align-content: center">
                        <p class="h3">Создание студента</p>
                        <div hidden="hidden">
                            <input name="id" type="text" value="${student.id}">
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="basic-addon1">ФИО</span>
                            <input name="name" type="text" class="form-control" aria-label="Username" aria-describedby="basic-addon1" value="${student.name}">
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="basic-addon1">Почта</span>
                            <input name="email" type="email" class="form-control"  aria-label="Username" aria-describedby="basic-addon1" value="${student.email}">
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="basic-addon1">Номер телефона</span>
                            <input name="phoneNumber" type="number" class="form-control" aria-label="Username" aria-describedby="basic-addon1" value="${student.phoneNumber}">
                        </div>
                        <div class="input-group mb-3">
                            <span class="input-group-text" id="basic-addon1">Возраст</span>
                            <input name="age" type="number" min="16" max="120" class="form-control" aria-label="Username" aria-describedby="basic-addon1" value="${student.age}">
                        </div>

                        <div class="form-floating">
                            <select name="group_id" class="form-select form-select-sm" id="floatingSelect" aria-label="Floating label select example">
                                <#if groups?has_content>
                                    <#list groups as group>
                                        <#if student.group_id == group.groupNumber>
                                            <option selected value="${group.id}">${group.groupNumber}</option>
                                        <#else>
                                            <option value="${group.id}">${group.groupNumber}</option>
                                        </#if>
                                    </#list>
                                <#else>
                                    <option value="null">Группы не созданы</option>
                                </#if>
                            </select>
                            <label for="floatingSelect">Группа</label>
                        </div>
                        <label hidden="hidden">
                            <input hidden="hidden" name="roles" value="${student.getRole}">
                        </label>
                        <div class="row mb-3">
                            <div class="col-sm-10" style="text-align: start">
                                <div class="form-check form-switch">
                                    <#if student.isStudying == true>
                                        <input name="isStudying" class="form-check-input" type="checkbox" role="switch" id="radio1" value="true" checked="checked">
                                        <input name="isStudying" class="form-check-input" type="hidden" id="radio1" value="false">
                                    <#else>
                                        <input name="isStudying" class="form-check-input" type="checkbox" role="switch" id="radio1" value="true">
                                        <input name="isStudying" class="form-check-input" type="hidden" id="radio1" value="false">
                                    </#if>
                                    <label class="form-check-label" for="radio1">
                                        Закончил обучение?
                                    </label>
                                </div>
                            </div>
                        </div>

                        <div class="container text-center" >
                            <div class="row align-items-center">
                                <div class="col">
                                    <input class="btn btn-outline-primary" type="submit" value="Изменить">
                                </div>
                                <div class="col">
                                    <a href="/students"><input class="btn btn-outline-primary" type="button" value="Назад"></a>
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