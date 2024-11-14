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
  - [4.2. What about JpaRepository?](#42-what-about-jparepository)
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
- [9. Advanced Mappings](#9-advanced-mappings)
  - [9.1. One-to-One](#91-one-to-one)
  - [9.2. One-to-Many](#92-one-to-many)
    - [9.2.1. Many-to-One](#921-many-to-one)
  - [9.3. Many-to-Many](#93-many-to-many)
- [10. Important Database Concepts](#10-important-database-concepts)
  - [10.1. Primary Key and Foreign Key](#101-primary-key-and-foreign-key)
  - [10.2. Cascade](#102-cascade)
    - [10.2.1. Cascade Types](#1021-cascade-types)
  - [10.3. Fetch Types: Eager vs Lazy Loading](#103-fetch-types-eager-vs-lazy-loading)
  - [10.4. Fetch](#104-fetch)
  - [10.5. Default Fetch Types](#105-default-fetch-types)
  - [10.6. Overriding Default Fetch Type](#106-overriding-default-fetch-type)
    - [10.6.1. Best Practice](#1061-best-practice)
  - [10.7. Uni-Directional](#107-uni-directional)
  - [10.8. Bi-Directional](#108-bi-directional)
- [11. Entity Lifecycle](#11-entity-lifecycle)
  - [11.1. Entity Lifecycle - session method calls](#111-entity-lifecycle---session-method-calls)
- [12. More on mappedBy](#12-more-on-mappedby)
- [13. Fetch Type](#13-fetch-type)
- [14. @JoinTable](#14-jointable)
  - [14.1. More on "inverse"](#141-more-on-inverse)
- [15. Common Errors](#15-common-errors)

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

## 4.2. What about JpaRepository?

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

# 9. Advanced Mappings

- One-to-One.
- One-to-Many, Many-to-One.
- Many-to-Many.

## 9.1. One-to-One

- An `Teacher` can have an `Teacher Detail` entity.
- Similar to an **Teacher Profile**.
  ![One-to-One](/Images/OneToOne.png)

## 9.2. One-to-Many

- An `Teacher` can have many `Subjects`.
  ![One-to-Many](/Images/OneToMany.png)
- Bi-Directional

### 9.2.1. Many-to-One

- Inverse / opposite of One-to-Many.

## 9.3. Many-to-Many

- A `Subject` can have many `Students`.
- A `Student` can have many `Subjects`.
  ![Many-to-Many](/Images/ManyToMany.png)

# 10. Important Database Concepts

- Primary key and foreign key.
- Cascade.

## 10.1. Primary Key and Foreign Key

- **Primary key:** Identify a unique row in a table.
- **Foreign key**
  - Link tables together a field in one table that refers to **primary key** in another table.
- Main purpose is to preserve relationship between tables.
  - Referential Integrity.
- Prevents operations that would destroy relationship.
- Ensures only valid data is inserted into the foreign key column.
  - Can only contain valid reference to primary key in other table.

![Foreign Key](/Images/ForeignKey.png)

## 10.2. Cascade

- You can `cascade` operations.
- Apply the same operation to related entities.
- If we delete an `teacher`, we should also delete their `teacher_detail`.
- This is known as **CASCADE DELETE**.
  ![Cascade Delete](/Images/CascadeDelete.png)
- **Cascade delete DEPENDS on the use case.**
- Developer can configure cascading.

### 10.2.1. Cascade Types

| Cascade Type | Description                                                                                   |
| ------------ | --------------------------------------------------------------------------------------------- |
| PERSIST      | If entity is persisted / saved, related entity will also be persisted.                        |
| REMOVE       | If entity is removed / deleted, related entity will also be deleted.                          |
| REFRESH      | If entity is refreshed, related entity will also be refreshed.                                |
| DETACH       | If entity is detached (not associated w/ session), then related entity will also be detached. |
| MERGE        | If entity is merged, then related entity will also be merged.                                 |
| ALL          | All of above cascade types.                                                                   |

## 10.3. Fetch Types: Eager vs Lazy Loading

- **Eager:** Will retrieve everything.
  - Eager loading will load all dependent entities.
    - Load `teacher` and all of their `subjects` at once.
- **Lazy:** Will retrieve on request.

## 10.4. Fetch

- When we define the mapping relationship.
  - We can specify the fetch type: **EAGER** or **LAZY**.
- **Example**
  ```java
    @Entity
    @Table(name="teacher")
    public class Teacher {
      ...
      @OneToMany(fetch=FetchType.LAZY, mappedBy="teacher")
      private List<Subject> subjects;
      ...
    }
  ```

## 10.5. Default Fetch Types

| Mapping     | Default Fetch Type |
| ----------- | ------------------ |
| @OneToOne   | FetchType.EAGER    |
| @OneToMany  | FetchType.LAZY     |
| @ManyToOne  | FetchType.EAGER    |
| @ManyToMany | FetchType.LAZY     |

## 10.6. Overriding Default Fetch Type

- Specifying the fetch type, overrides the defaults:
  ```java
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="teacher_id")
    private Teacer teacher;
  ```
- When you lazy load, the data is only retrieved on demand.
- However, this requires an open JPA/Hibernate session.
- Need an connection to database to retrieve data.
- **If the Hibernate session is closed.**
  - And you attempt to retrieve lazy data.
  - Hibernate will throw an exception.

### 10.6.1. Best Practice

- Only load data when absolutely needed.
- Prefer **Lazy loading** instead of **Eager loading**.

## 10.7. Uni-Directional

![Uni-Directional](/Images/UniDirectional.png)

- If we load an `TeacherDetail`.
  - Then we'd like to get the associated Teacher.
  - Can't do this with current uni-directional relationship.
  - **Bi-Directional** relationship is the solution.
- We can start with `TeacherDetail` and make it back to the Teacher.

## 10.8. Bi-Directional

![Bi-Directional](/Images/BiDirectional.png)

# 11. Entity Lifecycle

| Operations | Description                                                                        |
| ---------- | ---------------------------------------------------------------------------------- |
| Detach     | If entity is detached, it is not associated with a Hibernate session.              |
| Merge      | If instance is detached from session, then merge will reattach to session.         |
| Persist    | Transitions new instances to managed state. Next flush / commit will save in db.   |
| Remove     | Transitions managed entity to be removed. Next flush / commit will delete from db. |
| Refresh    | Reload / synch object with data from db. Prevents stale data.                      |

## 11.1. Entity Lifecycle - session method calls

- Search about...

# 12. More on mappedBy

- `mappedBy` tells JPA/Hibernate:

  - Look at the `teacherDetail` property in the `Teacher` class.
  - Use information from the `Teacher` class `@JoinColumn`.
  - To help find associated teacher.
  - **Example**
    ```java
      public class Teacher {
        ...
        @OneToOne(cascade=CascadeType.ALL)
        @JoinColumn(name="teacher_detail_id")
        private TeacherDetail teacherDetail;
        ...
      }
    ```

# 13. Fetch Type

# 14. @JoinTable

- `@JoinTable` tells Hibernate:
  - Look at the `student_id` column in the `subject_student` table.
  - For other side (inverse), look at the `subject_id` column in the `subject_student` table.
  - Use this information to find relationship between student and subject.

## 14.1. More on "inverse"

- In this context, we are defining the relationship in the `Student` class.
- The `Subject` class is on the **other side** ... so it is considered the **inverse**.
- "Inverse" refers to the **other side** of the relationship.

# 15. Common Errors

- **Error:** `org.hibernate.TransientPropertyValueException: object references an unsaved transient instance - save the transient instance before flushing : com.example.real_jpa_entity_relationships.models.Teacher.teacherDetail -> com.example.real_jpa_entity_relationships.models.TeacherDetail`
  - **Solution:** Forgot `CascadeType.ALL` or `CascadeType.PERSIST`.
- **Error:** Infinite recursion stackoverflow problem, this happens as it's going to convert the Java Object into a JSON Object.
  - **Solution**
  ```java
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Subject> subjects;
  ```
