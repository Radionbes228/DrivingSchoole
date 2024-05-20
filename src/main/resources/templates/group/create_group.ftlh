<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" type="text/css" href="/static/css/default_style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

    <title>Создание</title>
</head>
<body>

<div class="container text-center">
    <div class="row align-items-center">
        <div class="col">
            <form method="post" action="/groups/create" class="form">
                <div style="max-width: 700px; margin: 0 auto; align-content: center">
                    <p class="h3">Создание группы</p>
                    <div class="input-group mb-3">
                        <span class="input-group-text" id="basic-addon1">Номер группы</span>
                        <input name="group_number" type="text" class="form-control" aria-label="Username" aria-describedby="basic-addon1">
                    </div>

                    <div class="form-floating">
                        <select name="instructorId" class="form-select" id="floatingSelect" aria-label="Floating label select example">
                            <#if instructors?has_content>
                                <#list instructors as instructor>
                                    <option value="${instructor.id}">${instructor.name}</option>
                                </#list>
                            <#else>
                                <option value="null">Группы не созданы</option>
                            </#if>
                        </select>
                        <label for="floatingSelect">Группа</label>
                    </div>

                    <div class="container text-center" style="margin-top: 5px">
                        <div class="row align-items-center">
                            <div class="col">
                                <input class="btn btn-outline-primary" type="submit" value="Создать">
                            </div>
                            <div class="col">
                                <a href="/groups"><input class="btn btn-outline-primary" type="button" value="Назад"></a>
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