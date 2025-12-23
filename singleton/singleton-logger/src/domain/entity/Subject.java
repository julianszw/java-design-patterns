package domain.entity;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.ToString;
import util.IdGenerator;
import util.LoggerHelper;
import util.ValidationPatterns;

/**
 * Entidad Subject que representa una materia/asignatura.
 * Implementa operaciones CRUD completas con logging automático.
 */
@Getter
@ToString
public class Subject {

    private long id;
    
    @NotBlank(message = "{subject.name.notBlank}")
    @Size(min = 2, max = 100, message = "{subject.name.size}")
    @Pattern(regexp = ValidationPatterns.ALPHANUMERIC_SPANISH, message = "{subject.name.pattern}")
    private String name;
    
    @NotBlank(message = "{subject.code.notBlank}")
    @Size(min = 5, max = 10, message = "{subject.code.size}")
    @Pattern(regexp = ValidationPatterns.SUBJECT_CODE, message = "{subject.code.pattern}")
    private String code;
    
    @NotNull(message = "{subject.credits.notNull}")
    @Min(value = 1, message = "{subject.credits.min}")
    @Max(value = 20, message = "{subject.credits.max}")
    private int credits;

    // CREATE
    public Subject(String name, String code, int credits) {
        this.id = IdGenerator.generateId(this.getClass());
        this.setName(name);
        this.setCode(code);
        this.setCredits(credits);
        LoggerHelper.createSubject(this.name, this.code);
    }

    // UPDATE
    public void updateName(String newName) {
        String oldName = this.name;
        this.setName(newName);
        LoggerHelper.updateSubject(oldName, this.name);
    }

    public void updateCode(String newCode) {
        String oldCode = this.code;
        this.setCode(newCode);
        LoggerHelper.updateSubject(this.name + " (code)", oldCode + " -> " + newCode);
    }

    public void updateCredits(int newCredits) {
        int oldCredits = this.credits;
        this.setCredits(newCredits);
        LoggerHelper.updateSubject(this.name + " (credits)", 
            oldCredits + " -> " + newCredits);
    }

    // DELETE
    public void delete() {
        LoggerHelper.deleteSubject(this.name, this.code);
    }

    // Métodos privados de asignación
    private void setName(String name) {
        this.name = name;
    }

    private void setCode(String code) {
        // Convertir a mayúsculas para normalización
        this.code = code != null ? code.toUpperCase() : null;
    }

    private void setCredits(int credits) {
        this.credits = credits;
    }
}

