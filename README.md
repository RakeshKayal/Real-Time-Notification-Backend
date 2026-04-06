# 🔴 Real-Time Communication System

A full-stack social messaging platform built with **Spring Boot** and **WebSocket/STOMP**, supporting real-time direct messaging, group chats, friend requests, and offline message delivery — with BCrypt-secured authentication and JWT-based session management.

##  Features

- **Real-time messaging** — Persistent bidirectional WebSocket connections using STOMP protocol
- **Offline message delivery** — Messages stored in MySQL when recipient is offline; delivered automatically on reconnect via REST endpoint
- **Group chat** — Create groups, manage memberships, send group messages
- **Direct messaging** — One-on-one real-time chat between users
- **Friend request system** — Send, accept, and manage friend connections
- **Message history** — Full chat history retrieval on login
- **Secure authentication** — BCrypt (cost factor 12) password hashing + JWT token-based session management
- **React frontend** — Responsive UI consuming both WebSocket and REST endpoints


##  Architecture

The system uses a **hybrid REST + WebSocket architecture**:

- **WebSocket/STOMP** handles real-time message delivery when both users are online
- **REST API** handles authentication, message history, and offline message delivery on reconnect
- **MySQL** persists all messages, users, groups, and friend relationships

Client (React)
    │
    ├── WebSocket (STOMP) ──► Spring Boot ──► Active user sessions
    │
    └── REST API ───────────► Spring Boot ──► MySQL (messages, users, groups)




##  Database Schema

| Table | Purpose |
|---|---|
| `user-registration` | User accounts with BCrypt-hashed passwords |
| `messages` | Direct messages with sender_id, receiver_id, content, sent_at |
| `group_chats` | Group chat rooms |
| `group_memberships` | Many-to-many: users ↔ groups |
| `group_messages` | Messages scoped to group chats |
| `friend_requests` | Friend connection management |



##  Tech Stack

| Layer | Technology |
|---|---|
| Backend | Java 17, Spring Boot |
| Real-time | WebSocket, STOMP |
| Auth | JWT, BCrypt (cost factor 12) |
| Database | MySQL |
| Frontend | React |
| Build | Maven |



##  Getting Started

### Prerequisites
- Java 17+
- MySQL 8+
- Node.js (for frontend)

### Backend Setup

```bash
# Clone the repository
git clone https://github.com/YourUsername/real-time-communication-system.git
cd real-time-communication-system

# Configure database in src/main/resources/application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/<your Db>
spring.datasource.username=your_username
spring.datasource.password=your_password

# Run the application
./mvnw spring-boot:run


### Frontend Setup

```bash
cd frontend
npm install
npm start
```
---

##  Security

- Passwords hashed using BCrypt with cost factor 12 — never stored in plain text
- All API endpoints secured with JWT bearer token authentication
- WebSocket connections authenticated via JWT during handshake

---

## 📂 Project Structure

```
real-time-communication-system/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/yourname/
│       │       ├── Config/                        # Spring Security & app config
│       │       ├── Controller/                    # REST API controllers
│       │       ├── JWTConfig/                     # JWT token generation & validation
│       │       ├── MessageModel_StructureModel/   # Message DTOs & request/response models
│       │       ├── Model/                         # JPA entity classes
│       │       ├── Repo/                          # Spring Data JPA repositories
│       │       ├── Respons/                       # Response wrapper classes
│       │       ├── Service/                       # Business logic layer
│       │       ├── SocketConfig/                  # WebSocket & STOMP broker configuration
│       │       └── RealTimeCommunicationApplication.java
│       └── resources/
│           └── application.properties
├── frontend/                                      # React application
├── pom.xml
└── README.md

