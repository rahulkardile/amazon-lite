# 🛒 Amazon Lite Backend

A production-grade backend system built with **Spring Boot**, simulating a real-world e-commerce platform.  
It implements **JWT authentication, Redis caching, transactional order processing, and Kafka-based event-driven architecture**.

---

## 🚀 Features

### 🔐 Authentication & Security
- JWT-based authentication
- Stateless session management
- Role-based authorization (USER / ADMIN ready)
- Custom security filter (JWT filter)

---

### 📦 Product Module
- Create, fetch, and delete products
- Redis caching:
  - `getById`
  - `getAll`
- Cache eviction on write operations
- Optimized read-heavy performance

---

### 🛒 Order Module
- Multi-item order support
- Transactional order processing (`@Transactional`)
- Stock validation and deduction
- User-specific order history
- DTO-based response mapping

---

### ⚡ Caching (Redis)
- Cache-aside pattern
- TTL-based expiration
- JSON serialization with proper type handling
- Handles:
  - Serialization pitfalls
  - Cache invalidation
  - Collection caching challenges

---

### 📡 Kafka (Event-Driven Architecture)
- Order creation triggers Kafka event
- Producer publishes `OrderCreatedEvent`
- Consumer listens and processes asynchronously
- Decouples services for scalability

---

## ⚙️ Tech Stack

- **Backend:** Spring Boot  
- **Security:** Spring Security + JWT  
- **Database:** PostgreSQL  
- **ORM:** Spring Data JPA (Hibernate)  
- **Caching:** Redis  
- **Messaging:** Apache Kafka  
- **Containerization:** Docker  

---

## 🧱 Architecture
Controller → Service → Repository → Database → Redis Cache → Kafka (Events)

- Clean layered architecture  
- DTO pattern (no entity exposure)  
- Centralized exception handling  
- Event-driven extensibility  

---

## 🧑‍💻 Getting Started

### 📌 Prerequisites

- Java 17+
- Maven
- Docker

---

## 🐳 Run Services (Docker)

### PostgreSQL

```bash
docker run -d -p 5432:5432 \
-e POSTGRES_DB=amazon_lite \
-e POSTGRES_USER=postgres \
-e POSTGRES_PASSWORD=postgres \
postgres:15
```
Redis
```bash
docker run -d -p 6379:6379 redis 
```

Kafka (KRaft Mode)
```bash
docker run -d \
--name kafka \
-p 9092:9092 \
-p 9093:9093 \
-e KAFKA_NODE_ID=1 \
-e KAFKA_PROCESS_ROLES=broker,controller \
-e KAFKA_LISTENERS=PLAINTEXT://0.0.0.0:9092,CONTROLLER://0.0.0.0:9093 \
-e KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://localhost:9092 \
-e KAFKA_CONTROLLER_LISTENER_NAMES=CONTROLLER \
-e KAFKA_CONTROLLER_QUORUM_VOTERS=1@localhost:9093 \
-e KAFKA_OFFSETS_TOPIC_REPLICATION_FACTOR=1 \
-e KAFKA_TRANSACTION_STATE_LOG_REPLICATION_FACTOR=1 \
-e KAFKA_TRANSACTION_STATE_LOG_MIN_ISR=1 \
-e KAFKA_GROUP_INITIAL_REBALANCE_DELAY_MS=0 \
confluentinc/cp-kafka:latest
```

Kafka (KRaft Mode)

```bash

docker exec -it kafka bash

kafka-topics --create \
--topic order-created \
--bootstrap-server localhost:9092 \
--partitions 1 \
--replication-factor 1
```