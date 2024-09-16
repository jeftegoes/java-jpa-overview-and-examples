# JPA overview and examples <!-- omit in toc -->

## Contents <!-- omit in toc -->

- [1. What is Hibernate?](#1-what-is-hibernate)
  - [1.1. Benefits of Hibernate](#11-benefits-of-hibernate)
  - [1.2. Object-To-Relational Mapping (ORM)](#12-object-to-relational-mapping-orm)
- [2. What is JPA?](#2-what-is-jpa)
  - [2.1. JPA - Vendor Implementations](#21-jpa---vendor-implementations)
  - [2.2. What are Benefits of JPA](#22-what-are-benefits-of-jpa)
  - [2.3. Saving a Java Object with JPA](#23-saving-a-java-object-with-jpa)
  - [2.4. Retrieving a Java Object with JPA](#24-retrieving-a-java-object-with-jpa)
  - [2.5. Querying for Java Objects](#25-querying-for-java-objects)
  - [2.6. Hibernate / JPA and JDBC](#26-hibernate--jpa-and-jdbc)
  - [2.7. Setting up Project with Spring Initialzr](#27-setting-up-project-with-spring-initialzr)
- [3. Entity Class](#3-entity-class)
  - [3.1. @Column - Optional](#31-column---optional)
  - [3.2. Primary Key](#32-primary-key)
  - [3.3. MySQL - Auto Increment](#33-mysql---auto-increment)
    - [3.3.1. JPA Identity - Primary Key](#331-jpa-identity---primary-key)
  - [3.4. ID Generation Strategies](#34-id-generation-strategies)
    - [3.4.1. Custom Strategies](#341-custom-strategies)
- [4. DAO - Data Access Object](#4-dao---data-access-object)
  - [4.1. JPA Entity Manager](#41-jpa-entity-manager)
  - [4.2. What about JpaRepository???](#42-what-about-jparepository)
    - [4.2.1. Which One EntityManager or JpaRepository?](#421-which-one-entitymanager-or-jparepository)
    - [4.2.2. My Recommendation](#422-my-recommendation)
- [5. Spring @Transactional](#5-spring-transactional)
- [6. Spring @Repository](#6-spring-repository)
- [7. JPA Query Language (JPQL)](#7-jpa-query-language-jpql)
  - [7.1. Retrieving all Students](#71-retrieving-all-students)
  - [7.2. JPQL - Named Parameters](#72-jpql---named-parameters)
- [8. Create database tables](#8-create-database-tables)
  - [8.1. Configuration](#81-configuration)
  - [8.2. application.properties](#82-applicationproperties)
  - [8.3. Basic Projects](#83-basic-projects)
  - [8.4. Use Case](#84-use-case)
  - [8.5. Recommendation](#85-recommendation)

# 1. What is Hibernate?

- A framework for persisting / saving Java objects in a database.
- [Hibernate](www.hibernate.org/orm).

## 1.1. Benefits of Hibernate

- Hibernate handles all of the low-level SQL.
- Minimizes the amount of JDBC (Java Database Connectivity) code you have to develop.
- Hibernate provides the **Object-to-Relational Mapping (ORM)**.
  ![Hibernate](/Images/Hibernate.png)

## 1.2. Object-To-Relational Mapping (ORM)

- The developer defines mapping between Java class and database table.
  ![ORM](/Images/ORM.png)

# 2. What is JPA?

- **J**akarta **P**ersistence **A**PI (JPA)... _previously known as Java Persistence API_.
  - Standard API for Object-to-Relational-Mapping (ORM).
- **Only a specification**
  - Defines a set of interfaces.
  - Requires an implementation to be usable.

## 2.1. JPA - Vendor Implementations

![Vendor implementations](/Images/VendorImplementations.png)

## 2.2. What are Benefits of JPA

- By having a standard API, you are not locked to vendor's implementation.
- Maintain portable, flexible code by coding to JPA spec (interfaces).
- Can theoretically switch vendor implementations.
  - For example, if Vendor ABC stops supporting their product.
  - You could switch to Vendor XYZ without vendor lock in.

## 2.3. Saving a Java Object with JPA

```java
  @Override
  @Transactional
  public void save(Book book) {
      entityManager.persist(book);
  }
```

## 2.4. Retrieving a Java Object with JPA

```java
  @Override
  public Book findById(int id) {
      return entityManager.find(Book.class, id);
  }

  @Override
  public List<Book> findAll() {
      return entityManager.createQuery("select s FROM Book s ORDER BY s.name ASC", Book.class).getResultList();
  }
```

## 2.5. Querying for Java Objects

```java
  @Override
  public List<Book> findAll() {
      return entityManager.createQuery("select s FROM Book s ORDER BY s.name ASC", Book.class).getResultList();
  }
```

## 2.6. Hibernate / JPA and JDBC

- How does Hibernate / JPA relate to JDBC?
  - Hibernate / JPA uses JDBC for all database communications.
    ![JPA and JDBC](/Images/JpaJDBC.png)
- In Spring Boot, Hibernate is the default implementation of JPA.
- **EntityManager** is main component for creating queries etc...
- **EntityManager** is from Jakarta Persistence API (JPA).
- Based on configs, Spring Boot will automatically create the beans:
  - **DataSource**, **EntityManager**, **...**
- You can then inject these into your app, for example your **DAO**.

## 2.7. Setting up Project with Spring Initialzr

- At Spring Initializr website, [start.spring.io](https://start.spring.io/)
- **Add dependencies**
  - **MySQL Driver:** `mysql-connector-j`
  - **Spring Data JPA:** `spring-boot-starter-data-jpa`
- Spring Boot will automatically configure your data source for us.
- DB connection info from `application.properties`
  ```
    spring.datasource.url=jdbc:mysql://localhost:3306/school
    spring.datasource.username=root
    spring.datasource.password=Master@123
  ```
  - **No need to give JDBC driver class name Spring Boot will automatically detect it based on URL.**

# 3. Entity Class

- Java class that is mapped to a database table.
- At a minimum, the Entity class.
  - Must be annotated with `@Entity`
  - **Must have a public or protected no-argument constructor.**
    - The class can have other constructors.

```java
  @Entity
  @Table(name = "book")
  public class Book {
    public Book() {

    }
  }
```

## 3.1. @Column - Optional

- Actually, the use of `@Column` is optional.
- If not specified, the column name is the same name as Java field.
- In general, I don't recommend this approach.
  - If you refactor the Java code, then it will not match existing database columns.
  - This is a breaking change and you will need to update database column.
- Same applies to `@Table`, database table name is same as the class.

```java
  @Column(name = "name")
  private String name;
```

## 3.2. Primary Key

- Uniquely identifies each row in a table.
- Must be a unique value.
- Cannot contain NULL values.

## 3.3. MySQL - Auto Increment

```sql
  CREATE TABLE `book` (
    `id` int NOT NULL AUTO_INCREMENT,
    `author` varchar(255) DEFAULT NULL,
    `name` varchar(255) DEFAULT NULL,
    `price` float DEFAULT NULL,
    PRIMARY KEY (`id`)
  )
```

### 3.3.1. JPA Identity - Primary Key

```java
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private int id;
```

## 3.4. ID Generation Strategies

| Name                      | Description                                                                         |
| ------------------------- | ----------------------------------------------------------------------------------- |
| `GenerationType.AUTO`     | Pick an appropriate strategy for the particular database.                           |
| `GenerationType.IDENTITY` | Assign primary keys using database identity column.                                 |
| `GenerationType.SEQUENCE` | Assign primary keys using a database sequence.                                      |
| `GenerationType.TABLE`    | Assign primary keys using an underlying database table to ensure uniqueness.        |
| `GenerationType.UUID`     | Assign primary keys using a globally unique identifier (UUID) to ensure uniqueness. |

### 3.4.1. Custom Strategies

- You can define your own CUSTOM generation strategy.
- Create implementation of `org.hibernate.id.IdentifierGenerator`
- Override the method: **public Serializable generate(...)**

# 4. DAO - Data Access Object

- Responsible for interfacing with the database.
- This is a common design pattern: **Data Access Object (DAO)**.
  ![DAO](/Images/DAO.png)

## 4.1. JPA Entity Manager

- Our DAO needs a JPA Entity Manager.
  - JPA Entity Manager is the main component for saving/retrieving entities.
- Our JPA Entity Manager needs a Data Source.
  ![Entity Manager](/Images/EntityManager.png)
- The Data Source defines database connection info.
- JPA Entity Manager and Data Source are automatically created by Spring Boot.
  - Based on the file: `application.properties` (JDBC URL, user id, password, etc ...)
- We can autowire/inject the JPA Entity Manager into our Student DAO

## 4.2. What about JpaRepository???

- Spring Data JPA has a `JpaRepository` interface.
- This provides JPA database access with **minimal coding**.
- **In Simple Terms**
  - If you need **low-level control and flexibility**, use `EntityManager`
  - If you want **high-level of abstraction**, use `JpaRepository`

### 4.2.1. Which One EntityManager or JpaRepository?

| Entity Manager                                                                                       | JPA Repository                                                                                       |
| ---------------------------------------------------------------------------------------------------- | ---------------------------------------------------------------------------------------------------- |
| Need low-level control over the database operations and want to write custom queries                 | Provides commonly used CRUD operations out of the box, reducing the amount of code you need to write |
| Provides low-level access to JPA and work directly with JPA entities                                 | Additional features such as pagination, sorting                                                      |
| Complex queries that required advanced features such as native SQL queries or stored procedure calls | Generate queries based on method names                                                               |
| When you have custom requirements that are not easily handled by higher-level abstractions           | Can also create custom queries using @Query                                                          |

### 4.2.2. My Recommendation

- Choice depends on the application requirements and developer preference.
- You can also use both in the same project.
- For learning purposes, start with `EntityManager` then learn `JpaRepository`.
  - This will help you understand the low-level coding behind the scenes.
- Knowing BOTH `EntityManager` and `JpaRepository` will help you on future projects.

# 5. Spring @Transactional

- Spring provides an `@Transactional` annotation.
- **Automagically** begin and end a transaction for your JPA code.
  - No need for you to explicitly do this in your code.

# 6. Spring @Repository

- Spring provides the @Repository annotation
- Applied to DAO implementations
- Spring will automatically register the DAO implementation
- thanks to component-scanning
- Spring also provides translation of any JDBC related exceptions

# 7. JPA Query Language (JPQL)

- Query language for retrieving objects.
- Similar in concept to SQL.
  - `where`, `like`, `order by`, `join`, `in`, etc...
- However, JPQL is based on **entity name** and **entity fields**.

## 7.1. Retrieving all Students

```java
  @Override
  public List<Book> findAll() {
      return entityManager.createQuery("select s FROM Book s ORDER BY s.name ASC", Book.class).getResultList();
  }
```

- **Note: this is NOT the name of the database table.**
- All JPQL syntax is based on entity name and entity fields

## 7.2. JPQL - Named Parameters

```java
  @Override
  @Transactional
  public int delete(int id) {
      Query query = entityManager.createQuery("DELETE FROM Book WHERE id = :id");

      query.setParameter("id", id);

      return query.executeUpdate();
  }
```

# 8. Create database tables

- JPA/Hibernate provides an option to automagically create database tables.
- Creates tables based on Java code with JPA/Hibernate annotations.
- Useful for development and testing.
  ![Database creation process](/Images/DatabaseCreationProcess.png)

## 8.1. Configuration

- In Spring Boot configuration file: `application.properties`.
  - `spring.jpa.hibernate.ddl-auto=create`
- When you run your app, JPA/Hibernate will **drop** tables then **create** them.
- Based on the JPA/Hibernate annotations in your Java code.

## 8.2. application.properties

| Property Value | Property Description                                                                                                 |
| -------------- | -------------------------------------------------------------------------------------------------------------------- |
| `none`         | No action will be performed.                                                                                         |
| `create-only`  | Database tables are only created.                                                                                    |
| `drop`         | Database tables are dropped.                                                                                         |
| `create`       | Database tables are dropped followed by database tables creation.                                                    |
| `create-drop`  | Database tables are dropped followed by database tables creation. On application shutdown, drop the database tables. |
| `validate`     | Validate the database tables schema.                                                                                 |
| `update`       | Update the database tables schema.                                                                                   |

- **When database tables are dropped, all data is lost.**

## 8.3. Basic Projects

- For basic projects, can use auto configuration.
  - `spring.jpa.hibernate.ddl-auto=create`
- Database tables are **dropped** first and then **created** from scratch.
- If you want to create tables once ... and then keep data, use: `update`.
- However, will ALTER database schema based on latest code updates.
- Be VERY careful here... only use for basic projects.
- Don't do this on Production databases!!!
- You don't want to **drop** your Production data.
  - **All data is deleted!!!**
- Instead for Production, you should have DBAs run SQL scripts.

## 8.4. Use Case

- `spring.jpa.hibernate.ddl-auto=create`
- Automatic table generation is useful for.
- Database integration testing with in-memory databases.
- Basic, small hobby projects.

## 8.5. Recommendation

- In general, I don't recommend auto generation for enterprise, real-time projects.
  - You can VERY easily drop PRODUCTION data if you are not careful.
- I recommend SQL scripts.
  - Corporate DBAs prefer SQL scripts for governance and code review.
  - The SQL scripts can be customized and fine-tuned for complex database designs.
  - The SQL scripts can be version-controlled.
  - Can also work with schema migration tools such as [liquibase](https://www.liquibase.com/) and [flyway](https://www.red-gate.com/products/flyway/community/).
