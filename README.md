# Job Portal

A full-stack Job Portal web application built using **React, Spring Boot, Spring Security, JWT, and PostgreSQL**. The application provides separate dashboards and functionality for Candidates, Recruiters, and Administrators.

## Overview

The Job Portal connects candidates and recruiters through a centralized platform. Candidates can search and apply for jobs, recruiters can create and manage job postings and applications, and administrators can manage users, jobs, and job categories.

The frontend is built with React and Vite, while the backend is developed using Spring Boot REST APIs. Authentication and authorization are implemented using JWT and Spring Security. PostgreSQL is used for persistent data storage.

## Key Features

### Candidate

- User registration and login
- JWT authentication
- Browse available jobs
- Search jobs by title
- Filter jobs by location
- Filter jobs by job type
- Filter jobs by experience level
- Filter jobs by category
- View detailed job information
- Save jobs
- Apply for jobs
- Add cover letter while applying
- Add resume URL
- Track applications
- View application status
- Manage candidate profile

### Recruiter

- Recruiter registration and login
- Recruiter dashboard
- Create job postings
- Edit job postings
- Delete job postings
- Update job status
- ACTIVE / PAUSED / CLOSED job statuses
- View applications
- View candidate information
- View candidate resume
- Update application status

### Admin

- Admin dashboard
- View users
- View jobs
- Delete jobs
- Manage job categories
- Add categories
- Delete categories
- View user roles
- View application statistics

### Security

- JWT-based authentication
- Role-based authorization
- Protected routes
- Protected REST APIs
- Spring Security
- Stateless authentication
- CORS configuration

## Tech Stack

- **Frontend:** React, Vite, JavaScript, HTML, CSS, React Router, Axios
- **Backend:** Java, Spring Boot, Spring Security, Spring Data JPA, Hibernate
- **Authentication:** JWT
- **Database:** PostgreSQL
- **Database Hosting:** Neon PostgreSQL
- **Application Hosting:** Render

## Live Demo

**Live Application:**  
https://job-portal-g7x2.onrender.com/


## Project Structure

    job-portal/
    │
    │   ├── src/
    │   │   └── main/
    │   │       ├── java/
    │   │       │   └── com/project/jobportal/
    │   │       │       ├── config/
    │   │       │       ├── controller/
    │   │       │       ├── entity/
    │   │       │       ├── repository/
    │   │       │       ├── security/
    │   │       │       └── service/
    │   │       │
    │   │       └── resources/
    │   │           ├── static/
    |   |           |   └──frontend/
    │   │           └── application.properties
    │   │
    │   └── pom.xml
    │
    └── README.md

## REST APIs

### Authentication

    POST /api/auth/register
    POST /api/auth/login

### Jobs

    GET    /api/jobs
    GET    /api/jobs/{id}
    GET    /api/jobs/search?title={title}
    GET    /api/jobs/location?location={location}
    GET    /api/jobs/type?jobType={jobType}
    GET    /api/jobs/experience?experienceLevel={experienceLevel}
    GET    /api/jobs/status?status={status}
    GET    /api/jobs/company/{companyId}
    GET    /api/jobs/category/{categoryId}
    GET    /api/jobs/company/{companyId}/status?status={status}
    POST   /api/jobs?companyId={companyId}&categoryId={categoryId}
    PUT    /api/jobs/{id}?companyId={companyId}&categoryId={categoryId}
    PUT    /api/jobs/{id}/status?status={status}
    DELETE /api/jobs/{id}

### Applications

    GET    /api/applications
    POST   /api/applications
    GET    /api/applications/{id}
    PUT    /api/applications/{id}/status
    DELETE /api/applications/{id}

### Saved Jobs

    GET    /api/saved-jobs
    POST   /api/saved-jobs
    DELETE /api/saved-jobs/{id}

### Candidate Profiles

    GET    /api/candidate-profiles/user/{userId}
    POST   /api/candidate-profiles
    PUT    /api/candidate-profiles/{id}

### Categories

    GET    /api/categories
    POST   /api/categories
    DELETE /api/categories/{id}

### Users

    GET    /api/users
    GET    /api/users/{id}
    POST   /api/users
    PUT    /api/users/{id}
    DELETE /api/users/{id}

### Admin

    GET    /api/admin/users
    GET    /api/admin/jobs
    GET    /api/admin/categories

## Authentication

The application uses JWT-based authentication.

After successful login, the backend generates a JWT token. The frontend sends the token with protected requests using the Authorization header.

    Authorization: Bearer <JWT_TOKEN>

### User Roles

    CANDIDATE
        └── Candidate Dashboard

    RECRUITER
        └── Recruiter Dashboard

    ADMIN
        └── Admin Dashboard

## API Example

### Create Job

    POST /api/jobs?companyId=1&categoryId=5
    Authorization: Bearer <JWT_TOKEN>
    Content-Type: application/json

Request body:

    {
      "title": "Java Developer",
      "description": "Develop and maintain Java applications.",
      "location": "Bangalore",
      "jobType": "FULL_TIME",
      "experienceLevel": "FRESHER",
      "salary": 500000,
      "status": "ACTIVE"
    }

### Update Job Status

    PUT /api/jobs/10/status?status=PAUSED
    Authorization: Bearer <JWT_TOKEN>

Available job statuses:

    ACTIVE
    PAUSED
    CLOSED

## Running Locally

### Backend

    cd backend
    mvn spring-boot:run

### Frontend

    cd frontend
    npm install
    npm run dev

Frontend:

    http://localhost:5173

Backend:

    http://localhost:8080

## Environment Variables

Example:

    DB_URL=jdbc:postgresql://YOUR_DATABASE_HOST/YOUR_DATABASE?sslmode=require
    DB_USERNAME=YOUR_DATABASE_USERNAME
    DB_PASSWORD=YOUR_DATABASE_PASSWORD
    JWT_SECRET=YOUR_JWT_SECRET

## Deployment

The application is deployed using Render with Neon PostgreSQL.

    React Frontend
          │
          ▼
    Spring Boot Backend
          │
          ▼
    Neon PostgreSQL

The React production build can be placed inside the Spring Boot `static` directory so the frontend and backend can be deployed as a single application.

## Author

**Darshan JK**

B.E. Computer Science and Engineering

GitHub: https://github.com/Darshan-jk
