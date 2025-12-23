package domain.entity;

import jakarta.validation.constraints.*;
import lombok.Getter;
import util.IdGenerator;
import util.ValidationPatterns;
import java.time.LocalDate;

/**
 * Clase base User que representa un usuario del sistema.
 * Contiene atributos comunes para Professor y Student.
 */
@Getter
public abstract class User {

    private long id;
    
    @NotBlank(message = "{user.personalId.notBlank}")
    @Size(min = 6, max = 20, message = "{user.personalId.size}")
    @Pattern(regexp = ValidationPatterns.PERSONAL_ID, message = "{user.personalId.pattern}")
    private String personalId;
    
    @NotBlank(message = "{user.email.notBlank}")
    @Email(message = "{user.email.email}")
    @Size(max = 100, message = "{user.email.size}")
    private String email;
    
    @NotNull(message = "{user.enrollmentDate.notNull}")
    @PastOrPresent(message = "{user.enrollmentDate.past}")
    private LocalDate enrollmentDate;

    // Constructor protegido para ser usado por subclases
    protected User(String personalId, String email, LocalDate enrollmentDate) {
        this.id = IdGenerator.generateId(this.getClass());
        this.setPersonalId(personalId);
        this.setEmail(email);
        this.setEnrollmentDate(enrollmentDate);
    }

    // Métodos protegidos para actualización desde subclases
    protected void setEmail(String email) {
        this.email = email;
    }

    protected void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    protected void setEnrollmentDate(LocalDate enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }
}

