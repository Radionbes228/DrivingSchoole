<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Students</title>
</head>
<body>
<form action="/students/create" method="get"><input type="submit" value="Создать студента"></form>
    <div style="overflow: auto">
        <#if students?has_content>
            <#list students as student>
                <a href="/students/${student.id}" methods="get">${student.name}</a><br>
            </#list>
        <#else>
            Студентов нету
        </#if>

    </div>
</body>
</html>