package domain.entity;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.ToString;
import util.LoggerHelper;
import util.ValidationPatterns;
import java.time.LocalDate;

/**
 * Entidad Professor que representa un profesor.
 * Implementa operaciones CRUD completas con logging automático.
 */
@Getter
@ToString(callSuper = true)
public class Professor extends User {

    @NotBlank(message = "{professor.firstName.notBlank}")
    @Size(min = 2, max = 100, message = "{professor.firstName.size}")
    @Pattern(regexp = ValidationPatterns.ALPHABETIC_SPANISH, message = "{professor.firstName.pattern}")
    private String name;

    // CREATE
    public Professor(String name, String email, String personalId, LocalDate enrollmentDate) {
        super(personalId, email, enrollmentDate);
        this.setName(name);
        LoggerHelper.createProfessor(this.name, this.getEmail());
    }

    // UPDATE
    public void updateName(String newName) {
        String oldName = this.name;
        this.setName(newName);
        LoggerHelper.updateProfessor(oldName, this.name);
    }

    public void updateEmail(String newEmail) {
        String oldEmail = this.getEmail();
        this.setEmail(newEmail);
        LoggerHelper.updateProfessor(this.name + " (email)", oldEmail + " -> " + newEmail);
    }

    public void updatePersonalId(String newPersonalId) {
        String oldPersonalId = this.getPersonalId();
        this.setPersonalId(newPersonalId);
        LoggerHelper.updateProfessor(this.name + " (personal ID)", oldPersonalId + " -> " + newPersonalId);
    }

    public void updateEnrollmentDate(LocalDate newEnrollmentDate) {
        LocalDate oldDate = this.getEnrollmentDate();
        this.setEnrollmentDate(newEnrollmentDate);
        LoggerHelper.updateProfessor(this.name + " (enrollment date)", oldDate + " -> " + newEnrollmentDate);
    }

    // DELETE
    public void delete() {
        LoggerHelper.deleteProfessor(this.name, this.getEmail());
    }

    // Métodos privados de asignación
    private void setName(String name) {
        this.name = name;
    }
}

