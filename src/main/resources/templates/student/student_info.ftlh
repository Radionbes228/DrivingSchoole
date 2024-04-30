<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Info</title>
</head>
<body>
    <div>
        <h4>${student.id}</h4><br>
        <h4>${student.name}</h4><br>
        <h4>${student.age}</h4><br>
        <h4>${student.email}</h4><br>
        <h4>${group}</h4><br>
        <h4>${student.phoneNumber}</h4><br>
        <h4>${student.isStudying}</h4><br>
        <a href="students/update/${student.id}" methods="get">Редактировать</a>
    </div>
</body>
</html>