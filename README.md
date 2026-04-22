# 📘 featurevote-bff (Backend)

## ⚙️ FeatureVote Backend (BFF)

- Backend-for-Frontend (BFF) service for the FeatureVote platform.  
- Handles business logic, API orchestration, and database interactions.

---

## 🌐 Live API
👉 https://featurevote-bff.onrender.com

---

## 🧠 Overview

The backend powers the FeatureVote system by:

- Managing boards and feedback
- Handling voting logic
- Persisting data in PostgreSQL
- Exposing REST APIs for the frontend

This follows a **BFF (Backend-for-Frontend)** pattern to simplify frontend integration.

---

## 🏗️ Architecture
<img width="226" height="378" alt="Screenshot 2026-04-22 at 14 26 43" src="https://github.com/user-attachments/assets/adceaf70-556d-4e17-b843-67d5e7b02843" />

---

## ⚙️ Tech Stack

- **Java 17**
- **Spring Boot**
- **Spring Data JPA**
- **PostgreSQL**
- **Maven**

---

## 📂 Project Structure

src/main/java/
- ├── controller/
- ├── service/
- ├── repository/
- ├── domain/
- └── FeatureVoteApplication.java

---

## 🗄️ Database

- PostgreSQL (Render managed)
- Entities:
  - Boards
  - Feedback
  - Votes
  - Users

---
## 🛠️ Running Locally
1. Clone repo
- git clone https://github.com/bitopi2000/featurevote-bff.git
- cd featurevote-bff
2. Run application
- mvn spring-boot:run

2. Runs on:
- http://localhost:8080
  
## 🚀 Deployment
- Hosted on Render
- Connected to managed PostgreSQL database
- Uses environment variables for DB config

## 📘 API Testing
- Use tools like:
  - Postman
  - Example:
    - http://localhost:8080/api/boards/list
      
## ✨ Features
- RESTful API design
- Database persistence
- Scalable layered architecture
- BFF pattern for frontend optimization
 
## 🔮 Future Enhancements
- JWT Authentication (Spring Security)
- Swagger / OpenAPI documentation
- WebSocket real-time updates


