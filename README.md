# ToDo App - Framework Layer Architecture Project

## Overview
ToDo App is a mobile-based task management application developed as a project for the Framework Layer Architecture (FLA) course.

This project focuses on implementing software engineering principles, object-oriented programming concepts, layered architecture, and multiple software design patterns within an Android application.

The application allows users to:
- Register and login
- Create tasks
- Update tasks
- Delete tasks
- Search tasks
- Manage task priorities

---

# Technologies Used

- Android Studio
- Kotlin / Java
- XML Layout
- REST API
- Retrofit
- MVVM Architecture
- Material Design

---

# Architecture

This project uses a layered architecture approach:

```text
Presentation Layer
↓
Business Logic Layer
↓
Data Access Layer
```

Here is detail flow architecture:

```text
DTO
↓
Mapper (Adapter)
↓
Domain Model
↓
Repository
↓
Use Case
↓
ViewModel
↓
UI State
↓
XML/UI
```

