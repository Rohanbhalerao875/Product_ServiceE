# Microservices Documentation

## Overview
This project consists of two interconnected microservices:
1. Product Service (ProductService1)
2. User Service (Authentication Service)

## Technology Stack
- **Framework**: Spring Boot 3.4.0
- **Language**: Java 17
- **Primary Database**: MySQL (AWS RDS)
- **Cache**: Redis (AWS ElastiCache)
- **Message Broker**: Apache Kafka
- **Build Tool**: Maven
- **Database Migration**: Flyway
- **Containerization**: Docker
- **Container Orchestration**: Kubernetes
- **Cloud Platform**: AWS

## Cloud Architecture

### AWS Services Integration
1. **AWS RDS (MySQL)**
   - Multi-AZ deployment for high availability
   - Read replicas for read scaling
   - Automated backups and point-in-time recovery
   ```properties
   spring.datasource.url=jdbc:mysql://database-1.cro2a6y8o2c2.us-east-1.rds.amazonaws.com/productservicnov24batch
   ```

2. **AWS ElastiCache (Redis)**
   - Cache implementation for frequently accessed data
   - Session management
   - Configuration:
   ```properties
   spring.cache.type=redis
   spring.redis.host=${REDIS_HOST}
   spring.redis.port=6379
   ```

3. **Amazon MSK (Managed Kafka)**
   - Event-driven architecture
   - Inter-service communication
   - Real-time analytics
   ```properties
   spring.kafka.bootstrap-servers=${KAFKA_BROKERS}
   spring.kafka.consumer.group-id=product-service-group
   ```

4. **AWS ECS/EKS**
   - Container orchestration
   - Auto-scaling
   - Load balancing

### Scalability Features

#### 1. Horizontal Scaling
- **Kubernetes Deployment**
  ```yaml
  apiVersion: apps/v1
  kind: Deployment
  metadata:
    name: product-service
  spec:
    replicas: 1
    selector:
      matchLabels:
        app: product-service
  ```

#### 2. Caching Strategy
- **Redis Implementation**
  ```java
  @Cacheable(value = "products", key = "#id")
  public Product getProductById(Long id) {
      // Database lookup
  }
  ```

#### 3. Message Queue Patterns
- **Kafka Topics**
  - product-updates
  - user-events
  - inventory-changes

## Product Service (ProductService1)

### Core Components

#### 1. Models
- **BaseModel**
  - Abstract base class with common fields (id, title)
  - Used as a parent class for other entities

- **Product**
  - Extends BaseModel
  - Fields:
    - description (String)
    - price (Double)
    - quantity (int)
    - category (Many-to-One relationship)

- **Category**
  - Extends BaseModel
  - Fields:
    - description (String)
    - One-to-Many relationship with Products

#### 2. Controllers
- **ProductController** (`/Products` endpoint)
  - `GET /Products/{id}`: Retrieve product by ID
  - `GET /Products`: Get all products
  - `POST /Products`: Create new product
  - `PUT /Products/{id}`: Update existing product

#### 3. Services
- **ProductService** (Interface)
  - Defines core product operations

- **FakeStoreProductService**
  - Implementation using FakeStore API
  - External API integration using RestTemplate
  - Handles product data transformation

- **SelfProductService**
  - Primary implementation (@Primary)
  - Handles database operations using JPA repositories
  - Implements product CRUD operations

#### 4. Exception Handling
- **ProductNotFoundException**
  - Custom exception for product not found scenarios
  - Includes error code and message

- **ExceptionHandler**
  - Global exception handling using @RestControllerAdvice
  - Standardized error responses

### Database Configuration
```properties
spring.jpa.hibernate.ddl-auto=update
spring.datasource.url=jdbc:mysql://database-1.cro2a6y8o2c2.us-east-1.rds.amazonaws.com/productservicnov24batch
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
server.port=9090
```

### Database Schema
- Implemented using Flyway migrations
- Tables:
  - product
  - category
  - Various user-related tables for different inheritance strategies

## User Service (Authentication Service)

### User Management
The service implements three different JPA inheritance strategies for user management:

#### 1. Single Table Strategy
- **Base User Class**
  - Common fields: id, name, email, password
  - Discriminator column for user type

- **User Types**
  - Student (DiscriminatorValue="1")
  - Mentor (DiscriminatorValue="2")
  - Instructor

#### 2. Joined Table Strategy
- Separate tables for each user type
- Foreign key relationships to base user table
- Tables:
  - jt_user (base)
  - jt_student
  - jt_mentor
  - jt_instructor

#### 3. Table Per Class Strategy
- Independent tables for each user type
- Complete set of columns in each table
- Tables:
  - tpc_user
  - tpc_student
  - tpc_mentor
  - tpc_instructor

### Authentication & Authorization
- Token-based authentication
- TokenService for validation
- Integration with Product Service for authorization

### DTOs
- **UserDto**
  - name
  - email
  - roleDtoList

- **RoleDto**
  - value (String)

## Integration Between Services

### Token Validation Flow
1. Product Service receives request
2. TokenService validates token with User Service
3. User Service validates and returns user details
4. Product Service proceeds with the request if validated

### Security Considerations
- Token-based authentication
- Role-based access control
- Service-to-service communication using RestTemplate

## Development Guidelines

### Adding New Features
1. Create appropriate models/entities
2. Implement repository interfaces
3. Create service layer implementation
4. Add controller endpoints
5. Add exception handling
6. Update documentation

### Best Practices
- Use appropriate inheritance strategy based on requirements
- Implement proper exception handling
- Follow REST API conventions
- Use DTOs for data transfer
- Maintain separation of concerns

## Future Enhancements
1. Implement OAuth2 resource server (commented dependency available)
2. Add comprehensive test coverage
3. Implement caching mechanisms
4. Add API rate limiting
5. Implement circuit breakers for service communication

## Testing
- JUnit test cases available
- MockMvc for controller testing
- Mockito for service layer testing

## Deployment
The services are configured to run on different ports:
- Product Service: 9090
- User Service: 8080 (default)

## Dependencies
Major dependencies include:
- spring-boot-starter-web
- spring-boot-starter-data-jpa
- spring-boot-starter-data-jdbc
- mysql-connector-j
- flyway-core
- flyway-mysql
- lombok (for development)

## Docker Configuration

### Dockerfile
```dockerfile
FROM openjdk:17-jdk-slim
WORKDIR /app
COPY target/*.jar app.jar
EXPOSE 9090
ENTRYPOINT ["java","-jar","app.jar"]
```

### Docker Compose
```yaml
version: '3.8'
services:
  product-service:
    build: .
    ports:
      - "9090:9090"
    environment:
      - SPRING_PROFILES_ACTIVE=prod
      - REDIS_HOST=redis
      - KAFKA_BROKERS=kafka:9092
    depends_on:
      - redis
      - kafka

  redis:
    image: redis:6.2-alpine
    ports:
      - "6379:6379"

  kafka:
    image: confluentinc/cp-kafka:latest
    environment:
      KAFKA_ADVERTISED_LISTENERS: PLAINTEXT://kafka:9092
```

## Kubernetes Deployment

### Kubernetes Manifests

#### 1. Deployment
```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: product-service
spec:
  replicas: 1
  selector:
    matchLabels:
      app: product-service
  template:
    metadata:
      labels:
        app: product-service
    spec:
      containers:
      - name: product-service
        image: product-service:latest
        ports:
        - containerPort: 9090
        env:
        - name: SPRING_PROFILES_ACTIVE
          value: "prod"
        resources:
          requests:
            memory: "512Mi"
            cpu: "500m"
          limits:
            memory: "1Gi"
            cpu: "1000m"
```

#### 2. Service
```yaml
apiVersion: v1
kind: Service
metadata:
  name: product-service
spec:
  type: LoadBalancer
  ports:
  - port: 80
    targetPort: 9090
  selector:
    app: product-service
```

#### 3. HorizontalPodAutoscaler
```yaml
apiVersion: autoscaling/v2
kind: HorizontalPodAutoscaler
metadata:
  name: product-service-hpa
spec:
  scaleTargetRef:
    apiVersion: apps/v1
    kind: Deployment
    name: product-service
  minReplicas: 3
  maxReplicas: 10
  metrics:
  - type: Resource
    resource:
      name: cpu
      target:
        type: Utilization
        averageUtilization: 80
```

## Scalability Patterns

### 1. Circuit Breaker Pattern
```java
@CircuitBreaker(name = "productService", fallbackMethod = "fallbackMethod")
public Product getProductById(Long id) {
    // Service call
}
```

### 2. Cache-Aside Pattern
```java
@Cacheable(value = "products", key = "#id")
public Product getProductById(Long id) {
    // Database lookup
}
```

### 3. Event Sourcing
```java
@KafkaListener(topics = "product-events")
public void handleProductEvent(ProductEvent event) {
    // Handle event
}
```

## Monitoring and Observability

### 1. Prometheus & Grafana Integration
```yaml
management:
  endpoints:
    web:
      exposure:
        include: prometheus,health,metrics
```

### 2. Distributed Tracing
```properties
spring.zipkin.baseUrl=http://zipkin:9411
spring.sleuth.sampler.probability=1.0
```

## High Availability Setup

### 1. Database
- Multi-AZ RDS deployment
- Read replicas for read scaling
- Automated failover

### 2. Cache
- Redis cluster with replicas
- Cross-AZ deployment
- Automatic failover

### 3. Kafka
- Multi-broker setup
- Topic replication
- Partition distribution

## Performance Optimization

### 1. Connection Pooling
```properties
spring.datasource.hikari.maximum-pool-size=20
spring.datasource.hikari.minimum-idle=5
```

### 2. Caching Strategy
- Application-level caching (Redis)
- Database query caching
- HTTP response caching

### 3. Async Processing
```java
@Async
public CompletableFuture<Product> processProductAsync() {
    // Async processing
}
```

## CI/CD Pipeline

### GitHub Actions Workflow
```yaml
name: CI/CD Pipeline
on:
  push:
    branches: [ main ]

jobs:
  build:
    runs-on: ubuntu-latest
    steps:
    - uses: actions/checkout@v2
    - name: Build and Test
      run: mvn clean package
    - name: Build Docker image
      run: docker build -t product-service .
    - name: Deploy to EKS
      run: kubectl apply -f k8s/
```

## Disaster Recovery

### Backup Strategy
- Daily automated RDS snapshots
- Cross-region replication
- Regular backup testing

### Recovery Procedures
1. Database failover
2. Cache rebuild
3. Service redeployment
4. Data consistency verification

## Security Measures

### 1. Network Security
- VPC configuration
- Security groups
- Network ACLs

### 2. Application Security
- JWT token validation
- Role-based access control
- API gateway integration

### 3. Data Security
- Encryption at rest
- Encryption in transit
- Regular security audits 