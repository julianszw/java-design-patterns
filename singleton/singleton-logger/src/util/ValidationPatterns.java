package util;

/**
 * Constantes de patrones de validación (regex) para usar en anotaciones de Bean Validation.
 * Centraliza todas las expresiones regulares utilizadas en las validaciones del sistema.
 */
public final class ValidationPatterns {
    
    private ValidationPatterns() {
        // Clase de utilidad: evita instanciación
    }
    
    // ========== PATRONES DE NOMBRES Y TEXTO ==========
    
    /**
     * Patrón para nombres y apellidos que acepta letras (incluyendo acentos y ñ) y espacios.
     * Ejemplos válidos: "Juan", "María José", "José Luis", "Peña"
     */
    public static final String ALPHABETIC_SPANISH = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+$";
    
    /**
     * Patrón para nombres básicos solo con letras ASCII y espacios.
     * Ejemplos válidos: "John", "Mary Ann"
     */
    public static final String ALPHABETIC_BASIC = "^[a-zA-Z\\s]+$";
    
    /**
     * Patrón para texto con números, letras y espacios.
     * Ejemplos válidos: "Aula 101", "Edificio A", "Curso 2023"
     */
    public static final String ALPHANUMERIC_SPANISH = "^[a-zA-ZáéíóúÁÉÍÓÚñÑ0-9\\s]+$";
    
    // ========== PATRONES DE IDENTIFICACIÓN ==========
    
    /**
     * Patrón para ID personal genérico (6-20 caracteres alfanuméricos).
     * Ejemplos válidos: "ABC123", "ID12345678"
     */
    public static final String PERSONAL_ID = "^[A-Z0-9]{6,20}$";
    
    /**
     * Patrón para DNI argentino (7-8 dígitos).
     * Ejemplos válidos: "12345678", "1234567"
     */
    public static final String DNI_ARGENTINA = "^\\d{7,8}$";
    
    /**
     * Patrón para pasaporte (3 letras seguidas de 6 dígitos).
     * Ejemplos válidos: "ABC123456"
     */
    public static final String PASSPORT = "^[A-Z]{3}\\d{6}$";
    
    // ========== PATRONES DE CÓDIGOS ACADÉMICOS ==========
    
    /**
     * Patrón para código de materia (2-4 letras, guión, 3-4 dígitos).
     * Ejemplos válidos: "MAT-101", "COMP-1234", "FIS-001"
     */
    public static final String SUBJECT_CODE = "^[A-Z]{2,4}-\\d{3,4}$";
    
    /**
     * Patrón para código de carrera (3-5 letras, guión, 2 dígitos).
     * Ejemplos válidos: "COMP-01", "ING-05", "ADMIN-10"
     */
    public static final String CAREER_CODE = "^[A-Z]{3,5}-\\d{2}$";
    
    /**
     * Patrón para código de departamento (2-5 letras mayúsculas).
     * Ejemplos válidos: "CS", "MATH", "PHYS"
     */
    public static final String DEPARTMENT_CODE = "^[A-Z]{2,5}$";
    
    // ========== PATRONES DE EMAIL ==========
    
    /**
     * Patrón simple para email.
     * Ejemplos válidos: "usuario@ejemplo.com", "test.user@mail.co.ar"
     */
    public static final String EMAIL_SIMPLE = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    
    /**
     * Patrón para email académico (.edu o .edu.ar).
     * Ejemplos válidos: "estudiante@universidad.edu", "profesor@uni.edu.ar"
     */
    public static final String EMAIL_ACADEMIC = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.edu(\\.[a-z]{2})?$";
    
    // ========== PATRONES DE TELÉFONOS ==========
    
    /**
     * Patrón para teléfono argentino.
     * Ejemplos válidos: "+54 11 12345678", "11-12345678", "1112345678"
     */
    public static final String PHONE_ARGENTINA = "^\\+?54?[\\s-]?\\d{2,4}[\\s-]?\\d{6,8}$";
    
    /**
     * Patrón para teléfono internacional (formato E.164).
     * Ejemplos válidos: "+1234567890", "+541112345678"
     */
    public static final String PHONE_INTERNATIONAL = "^\\+?[1-9]\\d{1,14}$";
    
    // ========== PATRONES DE FECHAS Y HORAS ==========
    
    /**
     * Patrón para formato de fecha ISO (YYYY-MM-DD).
     * Ejemplos válidos: "2025-11-15", "2024-01-01"
     */
    public static final String DATE_ISO = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12]\\d|3[01])$";
    
    /**
     * Patrón para hora en formato 24h (HH:MM).
     * Ejemplos válidos: "09:30", "14:45", "23:59"
     */
    public static final String TIME_24H = "^([01]\\d|2[0-3]):[0-5]\\d$";
    
    // ========== DESCRIPCIONES (para documentación) ==========
    
    /**
     * Descripciones legibles de los patrones para uso en documentación o mensajes de error.
     */
    public static final class Description {
        public static final String ALPHABETIC_SPANISH = "Solo letras (incluye acentos y ñ) y espacios";
        public static final String PERSONAL_ID = "6-20 caracteres alfanuméricos en mayúsculas";
        public static final String SUBJECT_CODE = "Formato: 2-4 letras, guión, 3-4 dígitos (ej: MAT-101)";
        public static final String CAREER_CODE = "Formato: 3-5 letras, guión, 2 dígitos (ej: COMP-01)";
        public static final String EMAIL_SIMPLE = "Formato de email válido (ej: usuario@dominio.com)";
        public static final String PHONE_ARGENTINA = "Teléfono argentino con o sin código de país";
        public static final String DATE_ISO = "Formato: YYYY-MM-DD (ej: 2025-11-15)";
    }
}

