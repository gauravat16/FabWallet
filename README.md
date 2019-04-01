# FabWallet

## Requires
* MySQL
* Environment Variables  (You can set them in run configuration of your IDE as well)
  * ${MYSQL-USER}
  * ${MYSQL-PASSWORD}

 This project uses **Flyway** to setup the database, and inserts some basic test data as well.
 To view all the API(s) visit - http://localhost:8080/swagger-ui.html

## Project structure - 

  * **Flyway** is used to create a database and insert test data.
  * **Mysql** is used as the database.
  * To avoid **dirty reads** while transacting we have isolated the transaction to **READ_COMMITTED**.
  * **JPA** is used to map and save entities.
  * All exceptions (custom and existing) are handled by **@ControllerAdvice** and **@ExceptionHandler**
  * Security has been handeled by **Spring security** and password is hashed by **BCrypt**.
  * **Tests** are written for controllers.
  * To provide a better API documentaion with **swagger**.
