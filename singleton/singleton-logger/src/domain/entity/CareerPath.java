package domain.entity;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.ToString;
import util.IdGenerator;
import util.LoggerHelper;
import util.ValidationPatterns;
import java.util.ArrayList;

/**
 * Entidad CareerPath que representa una carrera o plan de estudios.
 * Implementa operaciones CRUD completas con logging automático.
 */
@Getter
@ToString
public class CareerPath {

    private long id;
    
    @NotBlank(message = "{career.name.notBlank}")
    @Size(min = 2, max = 100, message = "{career.name.size}")
    @Pattern(regexp = ValidationPatterns.ALPHANUMERIC_SPANISH, message = "{career.name.pattern}")
    private String name;
    
    @NotBlank(message = "{career.code.notBlank}")
    @Size(min = 6, max = 10, message = "{career.code.size}")
    @Pattern(regexp = ValidationPatterns.CAREER_CODE, message = "{career.code.pattern}")
    private String code;
    
    @Getter(lombok.AccessLevel.NONE) // Custom getter for defensive copy
    private ArrayList<Subject> subjects;

    // CREATE
    public CareerPath(String name, String code) {
        this.id = IdGenerator.generateId(this.getClass());
        this.setName(name);
        this.setCode(code);
        this.subjects = new ArrayList<>();
        LoggerHelper.createCareerPath(this.name, this.code);
    }

    // READ - Custom getter for defensive copy
    public ArrayList<Subject> getSubjects() {
        return new ArrayList<>(subjects);
    }

    // UPDATE
    public void updateName(String newName) {
        String oldName = this.name;
        this.setName(newName);
        LoggerHelper.updateCareerPath(oldName, this.name);
    }

    public void updateCode(String newCode) {
        String oldCode = this.code;
        this.setCode(newCode);
        LoggerHelper.updateCareerPath(this.name + " (code)", oldCode + " -> " + newCode);
    }

    // Gestión de materias
    public void addSubject(Subject subject) {
        if (subject == null) {
            throw new IllegalArgumentException("Subject cannot be null");
        }
        subjects.add(subject);
        LoggerHelper.updateCareerPath(this.name, "Added subject: " + subject.getName());
    }

    public void removeSubject(Subject subject) {
        if (subject == null) {
            throw new IllegalArgumentException("Subject cannot be null");
        }
        if (subjects.remove(subject)) {
            LoggerHelper.updateCareerPath(this.name, "Removed subject: " + subject.getName());
        }
    }

    // DELETE
    public void delete() {
        LoggerHelper.deleteCareerPath(this.name, this.code);
    }

    // Métodos privados de asignación
    private void setName(String name) {
        this.name = name;
    }

    private void setCode(String code) {
        // Convertir a mayúsculas para normalización
        this.code = code != null ? code.toUpperCase() : null;
    }
}

