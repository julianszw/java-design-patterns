package util;

/**
 * Helper para logging de operaciones CRUD de entidades.
 * Centraliza los mensajes de log usando el Logger singleton.
 */
public final class LoggerHelper {

    private static final Logger logger = Logger.getInstance();

    private LoggerHelper() {
        // Clase de utilidad: evita instanciación
    }

    // --- Student ---
    public static void createStudent(String name, String email) {
        logger.info("Student created: " + name + " (Email: " + email + ")");
    }

    public static void updateStudent(String identifier, String changes) {
        logger.info("Student updated: " + identifier + " - " + changes);
    }

    public static void deleteStudent(String name, String email) {
        logger.info("Student deleted: " + name + " (Email: " + email + ")");
    }

    // --- Subject ---
    public static void createSubject(String name, String code) {
        logger.info("Subject created: " + name + " (Code: " + code + ")");
    }

    public static void updateSubject(String identifier, String changes) {
        logger.info("Subject updated: " + identifier + " - " + changes);
    }

    public static void deleteSubject(String name, String code) {
        logger.info("Subject deleted: " + name + " (Code: " + code + ")");
    }

    // --- Professor ---
    public static void createProfessor(String name, String department) {
        logger.info("Professor created: " + name + " (Department: " + department + ")");
    }

    public static void updateProfessor(String identifier, String changes) {
        logger.info("Professor updated: " + identifier + " - " + changes);
    }

    public static void deleteProfessor(String name, String department) {
        logger.info("Professor deleted: " + name + " (Department: " + department + ")");
    }

    // --- CareerPath ---
    public static void createCareerPath(String name, String code) {
        logger.info("Career Path created: " + name + " (Code: " + code + ")");
    }

    public static void updateCareerPath(String identifier, String changes) {
        logger.info("Career Path updated: " + identifier + " - " + changes);
    }

    public static void deleteCareerPath(String name, String code) {
        logger.info("Career Path deleted: " + name + " (Code: " + code + ")");
    }

    // --- Métodos de logging para warnings y errores ---
    public static void warnDuplicateOperation(String entityType, String identifier, String operation) {
        logger.warn("Duplicate " + operation + " attempted on " + entityType + ": " + identifier);
    }

    public static void warnInvalidValue(String entityType, String field, String value) {
        logger.warn("Invalid " + field + " value for " + entityType + ": '" + value + "'");
    }

    public static void warnOperationOnDeletedEntity(String entityType, String identifier) {
        logger.warn("Operation attempted on deleted " + entityType + ": " + identifier);
    }

    public static void errorCreationFailed(String entityType, String reason) {
        logger.error("Failed to create " + entityType + ": " + reason);
    }

    public static void errorUpdateFailed(String entityType, String identifier, String reason) {
        logger.error("Failed to update " + entityType + " (" + identifier + "): " + reason);
    }

    public static void errorDeletionFailed(String entityType, String identifier, String reason) {
        logger.error("Failed to delete " + entityType + " (" + identifier + "): " + reason);
    }

    public static void errorValidationFailed(String entityType, String field, String value) {
        logger.error("Validation failed for " + entityType + ": " + field + " cannot be '" + value + "'");
    }
}

