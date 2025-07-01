# 🏦 Bank Management REST API

This is a backend project built using **Spring Boot** to manage bank accounts. The RESTful API supports creating bank accounts, deposit/withdraw transactions, deleting accounts, and listing all accounts with pagination.

---

## 🚀 Features

- Create new bank account
- View account by ID
- View all accounts (with pagination)
- Deposit and withdraw money
- Delete account
- Global exception handling

---

## 📦 Technologies Used

- Java 17
- Spring Boot
- Spring Data JPA
- MySQL
- Maven

---

## 📁 Folder Structure

```
src/
 └── main/
      ├── controller/
      ├── services/
      ├── repository/
      └── entity/
```

---

## ⚙️ Configuration

In `application.properties`, configure your MySQL database:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/bankdb
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

---

## 📬 API Endpoints

| Method | Endpoint | Description |
|--------|----------|-------------|
| POST   | `/bank` | Create new account |
| GET    | `/bank/{id}` | Get account by ID |
| GET    | `/bank` | Get all accounts |
| GET    | `/bank/paginated?page=0&size=5` | Paginated account listing |
| PUT    | `/bank/{id}/deposit?amount=1000` | Deposit money |
| PUT    | `/bank/{id}/withdraw?amount=500` | Withdraw money |
| DELETE | `/bank/{id}` | Delete account |

---

## 🔥 Planned Improvements

- Swagger/OpenAPI documentation
- Role-based authentication with Spring Security
- Transaction history tracking
- Unit testing with JUnit + Mockito
- Docker support

---

## 👤 Author

**Nikhil Tiwari**  
🔗 [GitHub Profile](https://github.com/nikk-dev05)

---

## 🧠 Contributing

Feel free to fork this repo, raise issues, or submit pull requests to contribute.
