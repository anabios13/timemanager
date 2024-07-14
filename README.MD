Первоначальный админ
   "login": "admin",
   "password": "c8p6"

Эндпоинты
UserController(ROLE ADMIN)
Создание пользователя (POST /api/users)
{
"username": "user1",
"password": "password1",
"role": "ROLE_USER"
}
Получение всех пользователей (GET /api/users)
{}
Получение пользователя по ID (GET /api/users/{id})
{}
Обновление пользователя (PUT /api/users/{id})

{
"username": "updatedUser1",
"password": "updatedPassword1",
"role": "ROLE_USER"
}
Удаление пользователя (DELETE /api/users/{id})
{}
2. ProjectController(ROLE ADMIN)
   Создание проекта (POST /api/projects)

{
"name": "Project 1",
"description": "Description for Project 1"
}
Получение всех проектов (GET /api/projects)
{}
Получение проекта по ID (GET /api/projects/{id})
{}
Обновление проекта (PUT /api/projects/{id})
{
"name": "Updated Project 1",
"description": "Updated description for Project 1"
}
Удаление проекта (DELETE /api/projects/{id})
{}
3. RecordController(ROLE ADMIN and USER)
   Создание записи (POST /api/records)
   {
   "userId": 1,
   "projectId": 1,
   "startTime": "2023-01-01T08:00:00",
   "endTime": "2023-01-01T12:00:00",
   "description": "Worked on project 1"
   }
   Получение всех записей (GET /api/records)
   {}
   Получение записи по ID (GET /api/records/{id})
   {}
   Обновление записи (PUT /api/records/{id})
   {
   "userId": 1,
   "projectId": 1,
   "startTime": "2023-01-01T09:00:00",
   "endTime": "2023-01-01T13:00:00",
   "description": "Updated work on project 1"
   }
   Удаление записи (DELETE /api/records/{id})

{}
4. AuthenticationController
   Аутентификация пользователя (POST /authenticate)

{
"username": "user1",
"password": "password1"
}
Регистрация пользователя (POST /register)

{
"username": "newuser",
"password": "newpassword",
"role": "ROLE_USER"
}
