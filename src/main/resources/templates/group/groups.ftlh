<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Группы</title>
</head>
<body>

    <div style="overflow: auto">
        <#if groups?has_content >
            <#list groups as group>
                <a href="groups/${group.id}">Группа: ${group.groupNumber}</a>
            </#list>
        <#else>
            Групп нету
        </#if>

    </div>

</body>
</html>