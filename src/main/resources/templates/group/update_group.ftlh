<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" type="text/css" href="/static/default_style.css">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">

    <title>Document</title>
</head>
<body>



<div class="container text-center">
    <div class="row align-items-center">
        <div class="col">
            <form method="post" action="/groups/update" class="form">
                <div style="max-width: 700px; margin: 0 auto; align-content: center">
                    <p class="h3">Редактирование группы</p>
                    <div hidden="hidden">
                        <input name="id" type="text" value="${group.id}">
                    </div>
                    <div class="input-group mb-3">
                        <span class="input-group-text" id="basic-addon1">Номер группы: </span>
                        <input name="groupNumber" type="text" class="form-control" aria-describedby="basic-addon1" value="${group.groupNumber}">
                    </div>
                    <div class="input-group mb-3">
                        <div class="form-floating">
                            <select name="instructorId" class="form-select" id="floatingSelect" aria-label="Floating label select example">
                                <#if instructors?has_content>
                                    <#list instructors as instructor>
                                        <#if instructor.id == group.instructorId>
                                            <option selected value="${instructor.id}">${instructor.name}</option>
                                        <#else>
                                            <option value="${instructor.id}">${instructor.name}</option>
                                        </#if>
                                    </#list>
                                <#else>
                                    <option value="null">Группы не созданы</option>
                                </#if>
                            </select>
                            <label for="floatingSelect">Группа</label>
                        </div>
                    </div>



                    <div class="container text-center" >
                        <div class="row align-items-center">
                            <div class="col">
                                <input class="btn btn-outline-primary" type="submit" value="Изменить">
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

<div class="container text-center">
    <div class="row align-items-center">
        <div class="col">
            <div class="container text-center" style="padding-right: 150px">
                <div class="row align-items-left">
                    <div class="col">
                        <p>ФИО</p>
                    </div>
                    <div class="col">
                        <p>Почта</p>
                    </div>
                    <div class="col">
                        <p>Номер телефона</p>
                    </div>
                    <div class="col">
                        <p>Группа</p>
                    </div>
                    <div class="col">
                        <p>Возраст</p>
                    </div>
                </div>
            </div>


            <div class="list-group">
                <#if students?has_content>
                    <#list students as student>
                        <div style="display: flex">
                            <div style="margin: auto 5px;">
                                <#if student.isStudying == true>
                                    <svg height="35px" width="35px" version="1.1" id="Layer_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="-30.72 -30.72 573.44 573.44" xml:space="preserve" fill="#000000" transform="rotate(0)" stroke="#000000" stroke-width="5.12"><g id="SVGRepo_bgCarrier" stroke-width="0"></g><g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round" stroke="#000000" stroke-width="8.192"> <g> <path style="fill:#2ae536;" d="M255.996,512c-68.38,0-132.667-26.629-181.02-74.98c-5.669-5.669-5.669-14.862,0-20.533 c5.669-5.669,14.862-5.669,20.533,0c42.867,42.869,99.863,66.476,160.488,66.476s117.62-23.608,160.488-66.476 C459.351,373.62,482.96,316.624,482.96,256s-23.608-117.62-66.476-160.488c-42.869-42.869-99.863-66.476-160.488-66.476 S138.376,52.644,95.509,95.512c-57.256,57.256-79.728,141.45-58.65,219.728c2.085,7.742-2.501,15.708-10.244,17.793 c-7.738,2.085-15.708-2.501-17.793-10.244C-2.68,280.078-2.935,234.853,8.086,192.005C19.44,147.853,42.572,107.387,74.977,74.98 C123.328,26.629,187.616,0,255.996,0s132.667,26.629,181.02,74.98c48.352,48.352,74.98,112.639,74.98,181.02 s-26.629,132.667-74.98,181.02C388.663,485.371,324.376,512,255.996,512z"></path> <path style="fill:#2ae536;" d="M255.996,446.518c-105.052,0-190.518-85.466-190.518-190.518S150.944,65.482,255.996,65.482 c67.966,0,131.273,36.627,165.218,95.591c4,6.948,1.61,15.825-5.338,19.826c-6.947,4.001-15.825,1.61-19.826-5.338 c-28.778-49.988-82.443-81.041-140.054-81.041c-89.042,0-161.482,72.44-161.482,161.482s72.44,161.482,161.482,161.482 S417.478,345.042,417.478,256c0-8.018,6.5-14.518,14.518-14.518s14.518,6.5,14.518,14.518 C446.514,361.052,361.048,446.518,255.996,446.518z"></path> </g> <circle style="fill:#2f9d54;" cx="255.995" cy="255.996" r="105.981"></circle> <path style="fill:#2ae536;" d="M255.996,376.499c-66.444,0-120.499-54.055-120.499-120.499s54.055-120.499,120.499-120.499 S376.495,189.556,376.495,256S322.439,376.499,255.996,376.499z M255.996,164.537c-50.433,0-91.463,41.031-91.463,91.463 s41.031,91.463,91.463,91.463s91.463-41.031,91.463-91.463S306.429,164.537,255.996,164.537z"></path> </g><g id="SVGRepo_iconCarrier"> <g> <path style="fill:#2ae536;" d="M255.996,512c-68.38,0-132.667-26.629-181.02-74.98c-5.669-5.669-5.669-14.862,0-20.533 c5.669-5.669,14.862-5.669,20.533,0c42.867,42.869,99.863,66.476,160.488,66.476s117.62-23.608,160.488-66.476 C459.351,373.62,482.96,316.624,482.96,256s-23.608-117.62-66.476-160.488c-42.869-42.869-99.863-66.476-160.488-66.476 S138.376,52.644,95.509,95.512c-57.256,57.256-79.728,141.45-58.65,219.728c2.085,7.742-2.501,15.708-10.244,17.793 c-7.738,2.085-15.708-2.501-17.793-10.244C-2.68,280.078-2.935,234.853,8.086,192.005C19.44,147.853,42.572,107.387,74.977,74.98 C123.328,26.629,187.616,0,255.996,0s132.667,26.629,181.02,74.98c48.352,48.352,74.98,112.639,74.98,181.02 s-26.629,132.667-74.98,181.02C388.663,485.371,324.376,512,255.996,512z"></path> <path style="fill:#2ae536;" d="M255.996,446.518c-105.052,0-190.518-85.466-190.518-190.518S150.944,65.482,255.996,65.482 c67.966,0,131.273,36.627,165.218,95.591c4,6.948,1.61,15.825-5.338,19.826c-6.947,4.001-15.825,1.61-19.826-5.338 c-28.778-49.988-82.443-81.041-140.054-81.041c-89.042,0-161.482,72.44-161.482,161.482s72.44,161.482,161.482,161.482 S417.478,345.042,417.478,256c0-8.018,6.5-14.518,14.518-14.518s14.518,6.5,14.518,14.518 C446.514,361.052,361.048,446.518,255.996,446.518z"></path> </g> <circle style="fill:#2f9d54;" cx="255.995" cy="255.996" r="105.981"></circle> <path style="fill:#2ae536;" d="M255.996,376.499c-66.444,0-120.499-54.055-120.499-120.499s54.055-120.499,120.499-120.499 S376.495,189.556,376.495,256S322.439,376.499,255.996,376.499z M255.996,164.537c-50.433,0-91.463,41.031-91.463,91.463 s41.031,91.463,91.463,91.463s91.463-41.031,91.463-91.463S306.429,164.537,255.996,164.537z"></path> </g></svg>
                                <#else>
                                    <svg height="35px" width="35px" version="1.1" id="Layer_1" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" viewBox="-30.72 -30.72 573.44 573.44" xml:space="preserve" fill="#000000" transform="rotate(0)" stroke="#000000" stroke-width="3.072"><g id="SVGRepo_bgCarrier" stroke-width="0"></g><g id="SVGRepo_tracerCarrier" stroke-linecap="round" stroke-linejoin="round" stroke="#000000" stroke-width="3.072"> <g> <path style="fill:#ff0000;" d="M255.996,512c-68.38,0-132.667-26.629-181.02-74.98c-5.669-5.669-5.669-14.862,0-20.533 c5.669-5.669,14.862-5.669,20.533,0c42.867,42.869,99.863,66.476,160.488,66.476s117.62-23.608,160.488-66.476 C459.351,373.62,482.96,316.624,482.96,256s-23.608-117.62-66.476-160.488c-42.869-42.869-99.863-66.476-160.488-66.476 S138.376,52.644,95.509,95.512c-57.256,57.256-79.728,141.45-58.65,219.728c2.085,7.742-2.501,15.708-10.244,17.793 c-7.738,2.085-15.708-2.501-17.793-10.244C-2.68,280.078-2.935,234.853,8.086,192.005C19.44,147.853,42.572,107.387,74.977,74.98 C123.328,26.629,187.616,0,255.996,0s132.667,26.629,181.02,74.98c48.352,48.352,74.98,112.639,74.98,181.02 s-26.629,132.667-74.98,181.02C388.663,485.371,324.376,512,255.996,512z"></path> <path style="fill:#ff0000;" d="M255.996,446.518c-105.052,0-190.518-85.466-190.518-190.518S150.944,65.482,255.996,65.482 c67.966,0,131.273,36.627,165.218,95.591c4,6.948,1.61,15.825-5.338,19.826c-6.947,4.001-15.825,1.61-19.826-5.338 c-28.778-49.988-82.443-81.041-140.054-81.041c-89.042,0-161.482,72.44-161.482,161.482s72.44,161.482,161.482,161.482 S417.478,345.042,417.478,256c0-8.018,6.5-14.518,14.518-14.518s14.518,6.5,14.518,14.518 C446.514,361.052,361.048,446.518,255.996,446.518z"></path> </g> <circle style="fill:#c57d7d;" cx="255.995" cy="255.996" r="105.981"></circle> <path style="fill:#ff0000;" d="M255.996,376.499c-66.444,0-120.499-54.055-120.499-120.499s54.055-120.499,120.499-120.499 S376.495,189.556,376.495,256S322.439,376.499,255.996,376.499z M255.996,164.537c-50.433,0-91.463,41.031-91.463,91.463 s41.031,91.463,91.463,91.463s91.463-41.031,91.463-91.463S306.429,164.537,255.996,164.537z"></path> </g><g id="SVGRepo_iconCarrier"> <g> <path style="fill:#ff0000;" d="M255.996,512c-68.38,0-132.667-26.629-181.02-74.98c-5.669-5.669-5.669-14.862,0-20.533 c5.669-5.669,14.862-5.669,20.533,0c42.867,42.869,99.863,66.476,160.488,66.476s117.62-23.608,160.488-66.476 C459.351,373.62,482.96,316.624,482.96,256s-23.608-117.62-66.476-160.488c-42.869-42.869-99.863-66.476-160.488-66.476 S138.376,52.644,95.509,95.512c-57.256,57.256-79.728,141.45-58.65,219.728c2.085,7.742-2.501,15.708-10.244,17.793 c-7.738,2.085-15.708-2.501-17.793-10.244C-2.68,280.078-2.935,234.853,8.086,192.005C19.44,147.853,42.572,107.387,74.977,74.98 C123.328,26.629,187.616,0,255.996,0s132.667,26.629,181.02,74.98c48.352,48.352,74.98,112.639,74.98,181.02 s-26.629,132.667-74.98,181.02C388.663,485.371,324.376,512,255.996,512z"></path> <path style="fill:#ff0000;" d="M255.996,446.518c-105.052,0-190.518-85.466-190.518-190.518S150.944,65.482,255.996,65.482 c67.966,0,131.273,36.627,165.218,95.591c4,6.948,1.61,15.825-5.338,19.826c-6.947,4.001-15.825,1.61-19.826-5.338 c-28.778-49.988-82.443-81.041-140.054-81.041c-89.042,0-161.482,72.44-161.482,161.482s72.44,161.482,161.482,161.482 S417.478,345.042,417.478,256c0-8.018,6.5-14.518,14.518-14.518s14.518,6.5,14.518,14.518 C446.514,361.052,361.048,446.518,255.996,446.518z"></path> </g> <circle style="fill:#c57d7d;" cx="255.995" cy="255.996" r="105.981"></circle> <path style="fill:#ff0000;" d="M255.996,376.499c-66.444,0-120.499-54.055-120.499-120.499s54.055-120.499,120.499-120.499 S376.495,189.556,376.495,256S322.439,376.499,255.996,376.499z M255.996,164.537c-50.433,0-91.463,41.031-91.463,91.463 s41.031,91.463,91.463,91.463s91.463-41.031,91.463-91.463S306.429,164.537,255.996,164.537z"></path> </g></svg>
                                </#if>
                            </div>
                            <div class="list-group-item list-group-item-action" style="margin-right: 10px">
                                <div class="container text-center">
                                    <div class="row align-items-left">
                                        <div class="col">
                                            <h6 style="text-align: left">${student.name}</h6>
                                        </div>
                                        <div class="col">
                                            <h6 style="text-align: left">${student.email}</h6>
                                        </div>
                                        <div class="col">
                                            <h6 style="text-align: left">${student.phoneNumber}</h6>
                                        </div>
                                        <div class="col">
                                            <h6>${group.groupNumber}</h6>
                                        </div>
                                        <div class="col">
                                            <h6>${student.age} лет</h6>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div>
                                <a href="/students/update/${student.id}"><button class="btn btn-outline-primary">Изменить</button></a>
                            </div>
                        </div>
                    </#list>
                <#else>
                    <p class="h3">Студентов нету</p>
                </#if>
            </div>
        </div>
    </div>
</div>


<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.min.js" integrity="sha384-0pUGZvbkm6XF6gxjEnlmuGrJXVbNuzT9qBBavbLwCsOGabYfZo0T0to5eqruptLy" crossorigin="anonymous"></script>
</body>
</html>