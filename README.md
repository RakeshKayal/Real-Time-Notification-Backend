# 🚀 Real-Time Communication System

A production-inspired real-time messaging platform built using **Spring Boot**, **WebSocket/STOMP**, **JWT Authentication**, and **MySQL**.

The system supports secure one-to-one messaging, group chats, friend requests, offline message delivery, and real-time notifications using a hybrid **REST + WebSocket architecture**.

---

## 📌 Features

### Real-Time Communication

* One-to-one messaging using WebSocket/STOMP
* Group chat with topic-based broadcasting
* Instant message delivery for online users

### User Management

* User registration and authentication
* Friend request workflow
* Friend acceptance and relationship management

### Offline Messaging

* Messages persisted in MySQL
* Undelivered messages automatically synchronized when users reconnect

### Security

* JWT-based authentication
* BCrypt password hashing (strength 12)
* Custom WebSocket Handshake Interceptor
* Server-side sender and receiver validation

### Reliability

* Persistent message storage
* Chat history retrieval
* Duplicate subscription prevention
* Active connection tracking

---

## 🏗 Architecture

The system follows a hybrid architecture:

* **REST APIs** handle authentication, friend requests, chat history, and offline synchronization.
* **WebSocket/STOMP** handles real-time messaging and notifications.

```text
Client (React)
      │
      ├────────────── REST API ──────────────► Spring Boot
      │                                         │
      │                                         ▼
      │                                      MySQL
      │
      └────────── WebSocket/STOMP ──────────► Active User Sessions
```

---

## 🔄 Authentication Flow

```text
User Login
    │
    ▼
Spring Security
    │
    ▼
JWT Generated
    │
    ▼
JWT Returned To Client
    │
    ▼
Client Connects To WebSocket
    │
    ▼
Custom Handshake Interceptor
    │
    ├── Validate JWT
    ├── Extract User Identity
    └── Attach User To WebSocket Session
```

Only authenticated users are allowed to establish WebSocket connections.

---

## 💬 Private Messaging Flow

```text
Sender
   │
   ▼
WebSocket Message
   │
   ▼
Spring Controller
   │
   ├── Validate Sender
   ├── Validate Receiver
   ├── Validate Friendship
   └── Persist Message
   │
   ▼
SimpMessagingTemplate
   │
   ▼
Receiver Session
```

If the receiver is offline:

```text
Persist Message
      │
      ▼
Mark As Undelivered
      │
      ▼
Fetch On Reconnect
```

---

## 👥 Group Messaging Flow

```text
User A
User B
User C
   │
   ▼
/topic/group/{groupId}
   │
   ▼
Spring Boot Broadcast
   │
   ▼
All Group Members Receive Message
```

---

## 🗄 Database Design

| Table             | Purpose                 |
| ----------------- | ----------------------- |
| user_registration | User accounts           |
| messages          | Direct messages         |
| group_chats       | Group information       |
| group_memberships | User-group mapping      |
| group_messages    | Group conversations     |
| friend_requests   | Friend request workflow |

---

## ⚖ Engineering Decisions & Trade-offs

### Why WebSocket Instead Of Polling?

#### Polling

Pros:

* Simple implementation

Cons:

* High latency
* Unnecessary HTTP requests
* Increased server load

#### WebSocket

Pros:

* Persistent connection
* Real-time server push
* Lower latency

Cons:

* More complex connection lifecycle

Decision:
WebSocket was chosen because chat applications require low-latency communication and efficient server push.

---

### Why Hybrid REST + WebSocket?

Not every operation benefits from WebSocket.

REST is used for:

* Login
* Registration
* Friend Requests
* Chat History
* Offline Synchronization

WebSocket is used for:

* Live Messaging
* Notifications
* Group Broadcasting

This separation keeps the system maintainable and easier to scale.

---

### Why JWT Instead Of Server Sessions?

Pros:

* Stateless authentication
* Better scalability
* Reduced server memory usage

Cons:

* Token revocation complexity

Decision:
JWT aligns well with distributed architectures and modern REST APIs.

---

## 🚧 Challenges Faced

### 1. Message Duplication

Problem:
Users occasionally received duplicate messages.

Root Cause:
Multiple subscriptions created during reconnection.

Solution:

* Centralized WebSocket management
* Proper subscription cleanup
* Single active connection per user

---

### 2. Incorrect Message Routing

Problem:
Private messages appeared in group channels.

Root Cause:
Improper destination separation.

Solution:

```text
/user/queue/private
/topic/group/{groupId}
```

Strict routing boundaries eliminated message leakage.

---

### 3. Securing WebSocket Connections

Problem:
Spring Security does not automatically secure WebSocket traffic.

Solution:
Implemented a custom Handshake Interceptor that validates JWT tokens before connection establishment.

---

## 🔒 Security Considerations

Implemented protections against:

* Unauthorized WebSocket connections
* Password theft
* Message spoofing
* User impersonation
* Unauthorized direct messaging

Security measures:

* BCrypt password hashing
* JWT authentication
* Custom Handshake Interceptor
* Server-side sender validation
* Friendship verification before message delivery

---

## 📊 Load Testing

Load testing was performed using Apache JMeter.

### Configuration

* 100+ concurrent users
* WebSocket messaging scenarios
* Group chat scenarios
* Direct messaging scenarios

### Results

| Metric           | Result           |
| ---------------- | ---------------- |
| Throughput       | ~45 Requests/sec |
| Error Rate       | 0%               |
| Concurrent Users | 100+             |
| Message Loss     | None Observed    |

### Observation

The application remained stable under moderate concurrent load. The primary bottleneck was database persistence rather than WebSocket message delivery.

---

## 📈 Scalability Considerations

### Current Architecture

* Single Spring Boot instance
* In-memory WebSocket session tracking

### Limitations

* Not horizontally scalable
* Session metadata exists only on one node

### Future Enhancements

* Redis Pub/Sub
* RabbitMQ
* Kafka Event Streaming
* Distributed WebSocket Nodes
* Load Balancing

---

## 🛠 Tech Stack

| Layer               | Technology        |
| ------------------- | ----------------- |
| Frontend            | React             |
| Backend             | Spring Boot       |
| Security            | Spring Security   |
| Authentication      | JWT               |
| Password Hashing    | BCrypt            |
| Real-Time Messaging | WebSocket + STOMP |
| Database            | MySQL             |
| ORM                 | Spring Data JPA   |
| Build Tool          | Maven             |
| Testing             | JMeter            |

---

## 📂 Project Structure

```text
src/
├── Config/
├── Controller/
├── JWTConfig/
├── MessageModel_StructureModel/
├── Model/
├── Repo/
├── Service/
├── SocketConfig/
└── RealTimeCommunicationApplication.java
```

---

## 🚀 Getting Started

### Prerequisites

* Java 17+
* Maven
* MySQL 8+

### Clone Repository

```bash
git clone https://github.com/YourUsername/real-time-communication-system.git
```

### Configure Database

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/chat_db
spring.datasource.username=root
spring.datasource.password=password
```

### Run Application

```bash
./mvnw spring-boot:run
```

---

## 🔮 Future Roadmap

* Read Receipts
* Typing Indicators
* User Presence Tracking
* Push Notifications
* Media/File Sharing
* End-to-End Encryption
* Redis-based Distributed Messaging
* Kafka Event Processing

```
```
