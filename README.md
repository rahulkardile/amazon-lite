# 🛒 Amazon Lite Backend

A scalable backend system built with **Spring Boot** that simulates a real-world e-commerce platform.  
It includes **JWT authentication, Redis caching, and transactional order processing**, designed with production-grade architecture.

---

## 🚀 Features

### 🔐 Authentication & Security
- JWT-based authentication
- Stateless session management
- Role-based access (USER / ADMIN ready)

---

### 📦 Product Module
- Create, fetch, and delete products
- Redis caching:
  - `getById`
  - `getAll`
- Automatic cache invalidation on write operations

---

### 🛒 Order Module
- Multi-item order support
- Transactional order processing (`@Transactional`)
- Stock validation and deduction
- User-specific order history
- Clean DTO-based responses

---

### ⚡ Caching (Redis)
- Cache-aside pattern
- TTL-based expiration
- JSON serialization with type safety
- Handles real-world issues like:
  - Serialization errors
  - Cache invalidation
  - Collection caching

---

## ⚙️ Tech Stack

- **Backend:** Spring Boot  
- **Security:** Spring Security + JWT  
- **Database:** PostgreSQL  
- **ORM:** Spring Data JPA (Hibernate)  
- **Caching:** Redis  
- **Containerization:** Docker  
- **Messaging (Planned):** Kafka  

---

## 🧱 Architecture
Controller → Service → Repository → Database
↓
Redis Cache


- Clean layered architecture  
- DTO pattern for API responses  
- Centralized exception handling  

---

## 🧑‍💻 Getting Started

### 📌 Prerequisites

- Java 17+
- Maven
- Docker (for PostgreSQL & Redis)

---

### 🐳 Run PostgreSQL & Redis

```bash
# PostgreSQL
docker run -d -p 5432:5432 \
-e POSTGRES_DB=**** \
-e POSTGRES_USER=**** \
-e POSTGRES_PASSWORD=**** \
postgres:15

# Redis
docker run -d -p 6379:6379 redis