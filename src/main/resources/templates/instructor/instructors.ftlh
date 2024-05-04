<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Инструкторы</title>
</head>
<body>

<form action="instructors/create" method="get"><input type="submit" value="Добавить инструктора"></form>
<div style="overflow: auto">
    <#if instructors?has_content>
        <#list instructors as instructor>
                <a href="/instructors/${instructor.id}">${instructor.name}</a>
        </#list>
    <#else>
        Инструкторов нет
    </#if>
</div>

</body>
</html>