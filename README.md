# 🧩 Harmony

A command-line CRUD code generator for Spring Boot, based on customizable templates.

This project aims to simplify and automate the generation of Spring components (Entity, Repository, Service, Controller) from a single command. It significantly saves time during prototyping or when creating structured applications.

---

## 🚀 Features

- Generate an entity with custom fields
- Automatically creates:
    - `Entity` with `@Entity` and `@Table`
    - `Repository` with `findByName` and `existsByName` methods
    - `Service` with `getAll`, `getById`, and `crupdate` logic
    - REST `Controller` with `GET` and `PUT` endpoints
- Simple templating system using `.tpl` files
- CLI powered by [Picocli](https://picocli.info/)

---

## 📦 Installation

### 1. Clone the repository

```bash
git clone https://github.com/your-account/SpringCrudGen.git
cd SpringCrudGen
```

### 2. Build the project

```bash
mvn package
```

The generated JAR will be located at:

```
target/SpringCrudGen-1.0-SNAPSHOT-jar-with-dependencies.jar
```

---

## 🛠 Usage

```bash
java -jar target/SpringCrudGen-1.0-SNAPSHOT-jar-with-dependencies.jar \
  Category com.example categories category name:String code:String
```

**Arguments**

| Position | Description                        | Example       |
|----------|------------------------------------|---------------|
| 0        | Entity class name                  | `Category`    |
| 1        | Base package                       | `com.example` |
| 2        | REST endpoint                      | `categories`  |
| 3        | Database table name                | `category`    |
| 4..*     | Entity fields (`name:Type`)        | `name:String` |

✅ **Example Output**

Files are generated in the `output/` folder:

```
output/
├── Category.java
├── CategoryRepository.java
├── CategoryService.java
└── CategoryController.java
```

---

## 🧱 Template Structure

Template files (`.tpl`) are located in:

```
src/main/resources/templates
```

They use double curly braces for variables:

```java
package {{basePackage}}.model;

@Entity
@Table(name = "{{tableName}}")
public class {{ClassName}} {
    @Id
    private String id;

{{fields}}
}
```

Fields are generated via the `TemplateEngine.generateFields()` method.

---

## 📅 Roadmap

### 🔜 Short-term (CLI)

- Basic generation (Entity, Repo, Service, Controller)
- JPA relationships:
    - `@ManyToOne`
    - `@OneToMany`
    - `@OneToOne`
- Generate directly inside an existing Spring project
- Validation annotations (`@NotNull`, `@Size`, etc.)
- Automatic generation of exceptions (e.g., `NotFoundException`)

### 🖥️ Mid-term (GUI)

- JavaFX or Swing interface
- Visual form to input CRUD parameters
- Visual selection of entity relationships

### 🧠 Long-term (Visual Programming)

- Drag & drop entity design interface
- UML-style relationship visualization
- Generate a full Spring Boot project from an OpenAPI (Swagger) specification
- Live generation of Spring Boot code + SQL schema
- Full project export (including `pom.xml`, `application.yml`, etc.)
