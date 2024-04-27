<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>

<form action="/auth/schedule" method="post" class="ui-form">
    <div style="display: flex">
        <#list weeks as day>
            <div class="form-row">
                <label for="${day}Schedule">${day}</label><br>
                <#list 8..18 as i>
                    ${i}:00
                    <select id="${day}Schedule" name="${day + i}">
                        <#if students?has_content>
                            <option value="0">Свободно</option>
                            <#list students as student>
                                <option value=${student.id} >${student.name}</option>
                            </#list>
                        <#else>
                            <option value="0">Нету учеников</option>
                        </#if>
                    </select><br>
                </#list>
            </div>
        </#list>
    </div>


    <p><input type="submit" value="Войти"></p>
</form>

</body>
</html>