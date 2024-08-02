<h1>Техническое задание</h1>
Реализовать api приложения тайм-трекера (система для фиксирования времени, затраченного на работу) на языке Java/Kotlin.

Требования:

1. Организация структуры данных в базе данных PostgreSQL (user, record, project и т.д.).
2. Реализация CRUD операций посредством Spring Boot.
3. Миграции структуры данных (Liquibase).
4. Авторизация запросов посредством Spring Security и JWT.
5. Разделение на роли в Spring Security (User может трекать время, Admin добавлять user и проекты для трекинга и т.д.).
6. Сборка приложения с помощью maven.
7. Развёртывание api и базы данных в Docker с использованием Docker compose.
8. Комментирование кода.
9. Чистый код и использование SOLID принципов.


<h2>Руководство пользователя</h2> 
<h3>Первоначальный админ</h3>
   "login": "admin",
   "password": "c8p6"

<h3>Эндпоинты</h3>
UserController(ROLE ADMIN)
Создание пользователя (POST /api/users)<br>
{<br>
"username": "user1",
"password": "password1",
"role": "ROLE_USER"<br>
}<br>
Получение всех пользователей (GET /api/users)<br>
{}<br>
Получение пользователя по ID (GET /api/users/{id})<br>
{}<br>
Обновление пользователя (PUT /api/users/{id})<br>

{<br>
"username": "updatedUser1",
"password": "updatedPassword1",
"role": "ROLE_USER"<br>
}<br>
Удаление пользователя (DELETE /api/users/{id})<br>
{}<br>
2. ProjectController(ROLE ADMIN)<br>
   Создание проекта (POST /api/projects)<br>
<br>
{
"name": "Project 1",
"description": "Description for Project 1"<br>
}<br>
Получение всех проектов (GET /api/projects)<br>
{}<br>
Получение проекта по ID (GET /api/projects/{id})<br>
{}<br>
Обновление проекта (PUT /api/projects/{id})<br>
{<br>
"name": "Updated Project 1",
"description": "Updated description for Project 1"<br>
}<br>
Удаление проекта (DELETE /api/projects/{id})<br>
{}<br>
3. RecordController(ROLE ADMIN and USER)<br>
   Создание записи (POST /api/records)<br>
   {<br>
   "userId": 1,
   "projectId": 1,
   "startTime": "2023-01-01T08:00:00",
   "endTime": "2023-01-01T12:00:00",
   "description": "Worked on project 1"<br>
   }<br>
   Получение всех записей (GET /api/records)<br>
   {}<br>
   Получение записи по ID (GET /api/records/{id})<br>
   {}<br>
   Обновление записи (PUT /api/records/{id})<br>
   {<br>
   "userId": 1,
   "projectId": 1,
   "startTime": "2023-01-01T09:00:00",
   "endTime": "2023-01-01T13:00:00",
   "description": "Updated work on project 1"<br>
   }<br>
   Удаление записи (DELETE /api/records/{id})<br>

{}<br>
4. AuthenticationController<br>
   Аутентификация пользователя (POST /authenticate)<br>

{<br>
"username": "user1",
"password": "password1"<br>
}<br>
Регистрация пользователя (POST /register)<br>

{<br>
"username": "newuser",
"password": "newpassword",
"role": "ROLE_USER"<br>
}<br>
