## Spring boot Project

- Mapper Pattern
- Builder Pattern
- DTO Pattern
- Spring Security
  - Basic Auth
  - CORS Config
- SOLID principles
- Unit Tests
  - MockBean
  - MockMVC
  - Mockito

### **Lancer l'application**

```
mvn spring-boot:run
```

### **Lancer les tests**

```
mvn -P run-all-tests
```

### **Tester l’API**

```
curl --location --request GET 'http://localhost:8080/api/v1/clients/1' \
--header 'Authorization: Basic ZXpvcWNfdXNlcjplem9xY19zZWNyZXQ='
```
