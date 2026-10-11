# Vehicle Service Center

Spring Boot REST API for customers registering vehicles and tracking service jobs.

## Stack
- Java 17, Spring Boot 3, Maven
- Spring Web, Spring Data JPA, Bean Validation
- MySQL 8

## Run
1. Create the database by running `database_setup.sql` in MySQL Workbench.
2. Set database credentials in `src/main/resources/application.properties` (default username `root`, blank password).
3. From this directory run:
   ```bash
   mvn clean test
   mvn spring-boot:run
   ```
4. Health check: `GET http://localhost:8080/api/health`

## Main endpoints
- `POST /api/customers` create customer
- `GET /api/customers` list customers
- `GET /api/customers/{id}` customer with nested vehicles
- `POST /api/vehicles` register a vehicle for an existing customer
- `GET /api/vehicles/{id}` vehicle and service history
- `POST /api/service-requests` book a service
- `GET /api/service-requests` list jobs (optional `?status=BOOKED`)
- `GET /api/service-requests/{id}` job detail
- `PATCH /api/service-requests/{id}/status` update job status
- `PATCH /api/service-requests/{id}/mechanic/{mechanicId}` assign mechanic
- `POST /api/service-requests/{id}/parts/{partId}?quantity=1` add a part
- `POST /api/service-requests/{id}/invoice` generate invoice
- `GET /api/invoices/{id}` view invoice

## Sample request bodies

Create customer:
```json
{"name":"Arun Kumar","email":"arun@example.com","phone":"9876543210"}
```

Register vehicle:
```json
{"customerId":1,"registrationNumber":"TN38AB1234","make":"Honda","model":"City","year":2022}
```

Book service:
```json
{"vehicleId":1,"description":"Engine oil change and general service","scheduledAt":"2026-10-15T10:00:00"}
```

Update job status:
```json
{"status":"IN_PROGRESS","notes":"Inspection started"}
```

## Workflow
`BOOKED -> INSPECTION -> IN_PROGRESS -> WAITING_FOR_PARTS -> COMPLETED`
A job may also be marked `CANCELLED`. Status updates are validated to prevent invalid transitions.

## Notes
This is a backend REST API; it does not include a browser frontend, authentication, payment gateway, or email/SMS service. Add authentication and authorization before exposing customer or mechanic management endpoints publicly.
