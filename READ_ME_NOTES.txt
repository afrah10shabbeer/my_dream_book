🌟 Notes
1️⃣ Database (DB)

Purpose:

Store and manage application data permanently.

Examples: Dream places, user accounts, favorites.

Used in project:

MySQL (or any relational DB supported by Spring Data JPA)

How it works:

Entities (@Entity) define the data structure.

Repositories (JpaRepository) handle DB operations.

Hibernate converts Java objects → SQL → DB rows.

Think of it as: “The memory of your application.”

2️⃣ Thymeleaf

Purpose:

Templating engine for rendering dynamic HTML.

Collects user input via forms.

Displays database content on the browser.

How it works:

th:action, th:value, th:each connect HTML → Spring Boot objects.

Spring Boot injects data from controller into Thymeleaf template.

Think of it as: “The view layer, showing data to users and collecting input.”

3️⃣ User Registration & Login

Purpose:

Allow users to create accounts and securely login.

Store credentials safely in DB (passwords usually hashed).

Enable personalized features like marking favorites.

How it works:

Registration Form → Controller → Service → Repository → DB

Login Form → Spring Security (or custom auth) → Validates user → Session

Key points:

Spring Boot + Spring Security is commonly used for authentication.

Passwords are never stored in plain text (BCrypt recommended).

Users can have sessions, access control, and roles.

4️⃣ Frameworks Used
Framework / Library	Purpose
Spring Boot	Main framework to create REST & web apps quickly
Spring Data JPA	Simplifies DB operations, ORM with Hibernate
Hibernate	Converts Java objects ↔ SQL tables (ORM)
Thymeleaf	Template engine for dynamic HTML
Spring Security	Handles authentication & authorization (optional for login)
Spring MVC	Web layer: controllers, routing, HTTP requests
MySQL / H2	Relational database for storing data
5️⃣ Full Flow of Application (Including Users)
User → HTML form (Thymeleaf) → Controller → Service → Repository → Database
Database → Repository → Service → Controller → HTML (Thymeleaf)


Example:

Add dream place → appear in list

User registration → saved in DB → can login

Favorites → stored per user

✅ Key Takeaways:

DB = persistent storage

Thymeleaf = dynamic front-end

Service = business logic

Controller = handles requests & responses

Spring Boot = backbone framework

Spring Security = secure login & roles
