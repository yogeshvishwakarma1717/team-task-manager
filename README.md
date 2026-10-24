# Team Task Manager

A web-based task management application that helps teams organize, assign, track, and manage tasks using a Kanban board.

## Features

- **Task Management:** Create and delete tasks with titles and descriptions.
- **Task Assignment:** Assign tasks to team members.
- **Kanban Board:** Organize tasks into To Do, In Progress, and Done columns.
- **Status Updates:** Change task status as work progresses.
- **Priority Management:** Set task priorities to Low, Medium, or High.
- **Due Dates:** Set deadlines and identify overdue tasks.
- **Filtering:** Filter tasks by status and team member.
- **Activity Log:** Record task status changes.

## Technologies Used

### Backend
- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Maven
- H2 Database

### Frontend
- HTML
- CSS
- JavaScript

### Tools
- IntelliJ IDEA
- Visual Studio Code
- Postman
- Git and GitHub

## Project Structure

```text
team-task-manager/
├── src/
│   └── main/
│       ├── java/
│       └── resources/
├── frontend/
│   ├── index.html
│   ├── style.css
│   └── script.js
├── pom.xml
├── mvnw
└── mvnw.cmd
```

## How to Run the Project

### 1. Start the Backend

Make sure Java is installed, then open the project root folder in a terminal.

On Windows, run:

```bash
.\mvnw.cmd spring-boot:run
```

The backend should start at:

`http://localhost:8080`

### 2. Open the Frontend

Open the `frontend` folder in Visual Studio Code and launch `index.html` using the Live Server extension.

The frontend communicates with the backend API at:

`http://localhost:8080/api`

Keep the backend running while using the frontend.

## API Endpoints

| Method | Endpoint | Purpose |
|---|---|---|
| GET | `/api/tasks` | Retrieve tasks |
| POST | `/api/tasks` | Create a task |
| GET | `/api/tasks/{id}` | Retrieve a task |
| PUT | `/api/tasks/{id}` | Update a task |
| PATCH | `/api/tasks/{id}/status?status=IN_PROGRESS` | Change task status |
| GET | `/api/tasks/{id}/overdue` | Check whether a task is overdue |
| DELETE | `/api/tasks/{id}` | Delete a task |
| GET | `/api/members` | Retrieve team members |
| GET | `/api/activity-logs` | Retrieve activity logs |

## Future Improvements

- Task editing functionality
- User authentication and authorization
- Notifications for approaching deadlines
- Comments on tasks
- Team workload summaries
- Cloud deployment

## Author

Developed as a team task management project using Java Spring Boot and a web-based frontend.

