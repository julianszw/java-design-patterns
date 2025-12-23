# 📋 Guía de Validación con Bean Validation

## 📚 Descripción

Este proyecto utiliza **Bean Validation API (JSR 380)** con **Hibernate Validator** para validar las entidades del sistema académico.

## 🎯 Componentes

### 1. Clase `ValidationPatterns`

Ubicación: `src/util/ValidationPatterns.java`

Contiene todas las expresiones regulares (regex) utilizadas en las validaciones:

```java
// Nombres y apellidos (con acentos y ñ)
ValidationPatterns.ALPHABETIC_SPANISH

// Texto alfanumérico (con acentos)
ValidationPatterns.ALPHANUMERIC_SPANISH

// Códigos de materia (ej: MAT-101)
ValidationPatterns.SUBJECT_CODE

// Códigos de carrera (ej: COMP-01)
ValidationPatterns.CAREER_CODE

// ID Personal (6-20 caracteres alfanuméricos)
ValidationPatterns.PERSONAL_ID

// Emails, teléfonos, fechas, etc.
```

### 2. Archivo `ValidationMessages.properties`

Ubicación: `src/main/resources/ValidationMessages.properties`

Contiene todos los mensajes de error de validación en español. Los mensajes son referenciados en las anotaciones usando llaves:

```properties
student.firstName.notBlank=El nombre del estudiante no puede estar vacío
student.firstName.size=El nombre debe tener entre {min} y {max} caracteres
student.firstName.pattern=El nombre solo puede contener letras y espacios
```

### 3. Clase `ValidationHelper`

Ubicación: `src/util/ValidationHelper.java`

Proporciona métodos utilitarios para validar entidades:

```java
// Validar y lanzar excepción si hay errores
ValidationHelper.validateAndThrow(student);

// Validar y obtener violaciones
Set<ConstraintViolation<Student>> violations = ValidationHelper.validate(student);

// Verificar si un objeto es válido
boolean isValid = ValidationHelper.isValid(student);

// Formatear violaciones como texto
String errors = ValidationHelper.formatViolations(violations);

// Formatear violaciones como HTML (para GUI)
String htmlErrors = ValidationHelper.formatViolationsAsHtml(violations);
```

## 🔧 Anotaciones Disponibles

### En las entidades

Todas las entidades (`Student`, `Professor`, `Subject`, `CareerPath`, `User`) tienen anotaciones de validación:

#### Student
```java
@NotBlank(message = "{student.firstName.notBlank}")
@Size(min = 2, max = 50, message = "{student.firstName.size}")
@Pattern(regexp = ValidationPatterns.ALPHABETIC_SPANISH, 
         message = "{student.firstName.pattern}")
private String firstName;
```

#### Subject
```java
@NotBlank(message = "{subject.code.notBlank}")
@Size(min = 5, max = 10, message = "{subject.code.size}")
@Pattern(regexp = ValidationPatterns.SUBJECT_CODE, 
         message = "{subject.code.pattern}")
private String code;

@Min(value = 1, message = "{subject.credits.min}")
@Max(value = 20, message = "{subject.credits.max}")
private int credits;
```

#### User (clase base)
```java
@Email(message = "{user.email.email}")
private String email;

@PastOrPresent(message = "{user.enrollmentDate.past}")
private LocalDate enrollmentDate;
```

## 💡 Ejemplos de Uso

### Ejemplo 1: Validación Manual en Constructor

```java
public Student(String firstName, String lastName, String email, 
               String personalId, LocalDate enrollmentDate) {
    super(personalId, email, enrollmentDate);
    this.firstName = firstName;
    this.lastName = lastName;
    
    // Validar el objeto completo
    ValidationHelper.validateAndThrow(this);
    
    LoggerHelper.createStudent(this.getFullName(), this.getEmail());
}
```

### Ejemplo 2: Validación antes de Actualizar

```java
public void updateFirstName(String newFirstName) {
    String oldValue = this.firstName;
    this.firstName = newFirstName;
    
    try {
        // Validar solo el campo firstName
        Set<ConstraintViolation<Student>> violations = 
            ValidationHelper.validateProperty(this, "firstName");
        
        if (!violations.isEmpty()) {
            this.firstName = oldValue; // Revertir cambio
            throw new IllegalArgumentException(
                ValidationHelper.formatViolations(violations)
            );
        }
    } catch (Exception e) {
        this.firstName = oldValue; // Revertir en caso de error
        throw e;
    }
    
    LoggerHelper.updateStudent(oldValue, newFirstName);
}
```

### Ejemplo 3: Validación en la UI (Swing)

```java
private void createStudent() {
    try {
        Student student = new Student(
            firstNameField.getText().trim(),
            lastNameField.getText().trim(),
            emailField.getText().trim(),
            personalIdField.getText().trim(),
            LocalDate.parse(enrollmentDateField.getText())
        );
        
        // Validar antes de agregar
        Set<ConstraintViolation<Student>> violations = 
            ValidationHelper.validate(student);
        
        if (!violations.isEmpty()) {
            String htmlErrors = ValidationHelper.formatViolationsAsHtml(violations);
            JOptionPane.showMessageDialog(this, 
                htmlErrors,
                "Errores de Validación", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        students.add(student);
        refreshTable();
        
    } catch (Exception e) {
        JOptionPane.showMessageDialog(this, 
            e.getMessage(),
            "Error", 
            JOptionPane.ERROR_MESSAGE);
    }
}
```

### Ejemplo 4: Validación Programática Completa

```java
public void processStudentData(Student student) {
    // Validar
    Set<ConstraintViolation<Student>> violations = 
        ValidationHelper.validate(student);
    
    if (!violations.isEmpty()) {
        // Procesar cada violación
        for (ConstraintViolation<Student> violation : violations) {
            String field = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            Object invalidValue = violation.getInvalidValue();
            
            System.err.println("Campo: " + field);
            System.err.println("Mensaje: " + message);
            System.err.println("Valor inválido: " + invalidValue);
        }
        
        // Lanzar excepción con todos los errores
        throw new IllegalArgumentException(
            ValidationHelper.formatViolations(violations)
        );
    }
    
    // Si llega aquí, el objeto es válido
    // Procesar...
}
```

## 📝 Agregar Nuevas Validaciones

### 1. Agregar nuevo patrón en `ValidationPatterns.java`:

```java
public static final String NEW_PATTERN = "^[regex]$";
```

### 2. Agregar mensaje en `ValidationMessages.properties`:

```properties
entity.field.constraint=Mensaje de error aquí
```

### 3. Usar en la entidad:

```java
@Pattern(regexp = ValidationPatterns.NEW_PATTERN, 
         message = "{entity.field.constraint}")
private String field;
```

## 🌍 Internacionalización (i18n)

Para agregar mensajes en otros idiomas, crea archivos adicionales:

- `ValidationMessages_en.properties` (inglés)
- `ValidationMessages_pt.properties` (portugués)

El framework seleccionará automáticamente el archivo según el Locale del sistema.

## 📚 Anotaciones Más Comunes

| Anotación | Descripción | Ejemplo |
|-----------|-------------|---------|
| `@NotNull` | No puede ser null | `@NotNull private String name;` |
| `@NotBlank` | No puede ser null, vacío o solo espacios | `@NotBlank private String email;` |
| `@Email` | Debe ser un email válido | `@Email private String email;` |
| `@Pattern` | Debe coincidir con el regex | `@Pattern(regexp="[A-Z]+")` |
| `@Size` | Tamaño de String/Collection | `@Size(min=2, max=50)` |
| `@Min` / `@Max` | Valores numéricos | `@Min(1) @Max(100)` |
| `@Past` / `@Future` | Fechas pasadas/futuras | `@Past private LocalDate birth;` |
| `@PastOrPresent` | Fecha pasada o actual | `@PastOrPresent private LocalDate enrollment;` |

## 🔗 Referencias

- [Bean Validation Specification (JSR 380)](https://beanvalidation.org/)
- [Hibernate Validator Documentation](https://hibernate.org/validator/)
- [Jakarta Bean Validation](https://jakarta.ee/specifications/bean-validation/)

