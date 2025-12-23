package domain.entity;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.ToString;
import util.LoggerHelper;
import util.ValidationPatterns;
import java.time.LocalDate;

/**
 * Entidad Student que representa un estudiante.
 * Implementa operaciones CRUD completas con logging automático.
 */
@Getter
@ToString(callSuper = true)
public class Student extends User {

    @NotBlank(message = "{student.firstName.notBlank}")
    @Size(min = 2, max = 50, message = "{student.firstName.size}")
    @Pattern(regexp = ValidationPatterns.ALPHABETIC_SPANISH, message = "{student.firstName.pattern}")
    private String firstName;
    
    @NotBlank(message = "{student.lastName.notBlank}")
    @Size(min = 2, max = 50, message = "{student.lastName.size}")
    @Pattern(regexp = ValidationPatterns.ALPHABETIC_SPANISH, message = "{student.lastName.pattern}")
    private String lastName;

    // CREATE
    public Student(String firstName, String lastName, String email, String personalId, LocalDate enrollmentDate) {
        super(personalId, email, enrollmentDate);
        this.setFirstName(firstName);
        this.setLastName(lastName);
        LoggerHelper.createStudent(this.firstName + " " + this.lastName, this.getEmail());
    }

    // READ
    public String getFullName() {
        return firstName + " " + lastName;
    }

    // UPDATE
    public void updateFirstName(String newFirstName) {
        String oldName = this.getFullName();
        this.setFirstName(newFirstName);
        LoggerHelper.updateStudent(oldName, this.getFullName());
    }

    public void updateLastName(String newLastName) {
        String oldName = this.getFullName();
        this.setLastName(newLastName);
        LoggerHelper.updateStudent(oldName, this.getFullName());
    }

    public void updateEmail(String newEmail) {
        String oldEmail = this.getEmail();
        this.setEmail(newEmail);
        LoggerHelper.updateStudent(this.getFullName() + " (email)", oldEmail + " -> " + newEmail);
    }

    public void updatePersonalId(String newPersonalId) {
        String oldPersonalId = this.getPersonalId();
        this.setPersonalId(newPersonalId);
        LoggerHelper.updateStudent(this.getFullName() + " (personal ID)", oldPersonalId + " -> " + newPersonalId);
    }

    public void updateEnrollmentDate(LocalDate newEnrollmentDate) {
        LocalDate oldDate = this.getEnrollmentDate();
        this.setEnrollmentDate(newEnrollmentDate);
        LoggerHelper.updateStudent(this.getFullName() + " (enrollment date)", oldDate + " -> " + newEnrollmentDate);
    }

    // DELETE
    public void delete() {
        LoggerHelper.deleteStudent(this.getFullName(), this.getEmail());
    }

    // Métodos privados de asignación
    private void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    private void setLastName(String lastName) {
        this.lastName = lastName;
    }
}

