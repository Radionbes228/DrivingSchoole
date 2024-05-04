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

<form action="/instructors/update" method="post">
    <label hidden="hidden">
        <input value="${instructor.id}" type="number" name="id">
    </label>
    <label>
        <input value="${instructor.name}" type="text" name="name">
    </label><br>
    <label>
        <input value="${instructor.email}" type="text" name="email">
    </label><br>
    <label>
        <input value="${instructor.password}" type="text" name="password">
    </label><br>
    <label>
        <input value="${instructor.age}" type="number" name="age">
    </label><br>
    <label>
        <input value="${instructor.phoneNumber}" type="text" name="phoneNumber">
    </label><br>
    <label hidden="hidden">
        <input value="${instructor.role}" type="text" name="role">
    </label><br>
    <input type="submit" value="Сохранить">
</form>

</body>
</html>