<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>История расписания</title>
</head>
<body>
<form action="schedules/create" method="get"><input type="submit" value="Создать неделю"></form>
    <#if schedules?has_content>
        <div style="overflow: auto">
             <#list schedules as schedule>
               <a href="/schedules/${schedule.id}">${schedule.startWeek} - ${schedule.endWeek} Год: ${schedule.year}</a><br>
            </#list>
        </div>
    <#else>
        Нету недель для расписания!
    </#if>
</body>
</html>