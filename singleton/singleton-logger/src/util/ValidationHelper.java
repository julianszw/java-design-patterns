package util;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Helper para realizar validaciones usando Bean Validation.
 * Proporciona métodos utilitarios para validar entidades y obtener mensajes de error formateados.
 */
public final class ValidationHelper {
    
    private static final Validator validator;
    
    static {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }
    
    private ValidationHelper() {
        // Clase de utilidad: evita instanciación
    }
    
    /**
     * Valida un objeto y retorna el conjunto de violaciones encontradas.
     * 
     * @param object El objeto a validar
     * @param <T> El tipo del objeto
     * @return Set con las violaciones encontradas (vacío si no hay errores)
     */
    public static <T> Set<ConstraintViolation<T>> validate(T object) {
        return validator.validate(object);
    }
    
    /**
     * Valida un objeto y lanza una excepción si hay violaciones.
     * 
     * @param object El objeto a validar
     * @param <T> El tipo del objeto
     * @throws IllegalArgumentException Si hay violaciones de validación
     */
    public static <T> void validateAndThrow(T object) {
        Set<ConstraintViolation<T>> violations = validate(object);
        
        if (!violations.isEmpty()) {
            String errorMessage = formatViolations(violations);
            throw new IllegalArgumentException(errorMessage);
        }
    }
    
    /**
     * Valida un campo específico de un objeto.
     * 
     * @param object El objeto a validar
     * @param propertyName El nombre del campo a validar
     * @param <T> El tipo del objeto
     * @return Set con las violaciones encontradas en ese campo
     */
    public static <T> Set<ConstraintViolation<T>> validateProperty(T object, String propertyName) {
        return validator.validateProperty(object, propertyName);
    }
    
    /**
     * Verifica si un objeto es válido según sus anotaciones de validación.
     * 
     * @param object El objeto a verificar
     * @param <T> El tipo del objeto
     * @return true si el objeto es válido, false en caso contrario
     */
    public static <T> boolean isValid(T object) {
        return validate(object).isEmpty();
    }
    
    /**
     * Formatea las violaciones de validación en un mensaje legible.
     * 
     * @param violations Las violaciones a formatear
     * @param <T> El tipo del objeto
     * @return String con los mensajes de error formateados
     */
    public static <T> String formatViolations(Set<ConstraintViolation<T>> violations) {
        return violations.stream()
                .map(violation -> violation.getPropertyPath() + ": " + violation.getMessage())
                .collect(Collectors.joining("\n"));
    }
    
    /**
     * Formatea las violaciones de validación como una lista HTML.
     * Útil para mostrar en interfaces gráficas.
     * 
     * @param violations Las violaciones a formatear
     * @param <T> El tipo del objeto
     * @return String con los mensajes en formato HTML
     */
    public static <T> String formatViolationsAsHtml(Set<ConstraintViolation<T>> violations) {
        if (violations.isEmpty()) {
            return "";
        }
        
        StringBuilder html = new StringBuilder("<html><body><ul>");
        for (ConstraintViolation<T> violation : violations) {
            html.append("<li><b>")
                .append(violation.getPropertyPath())
                .append(":</b> ")
                .append(violation.getMessage())
                .append("</li>");
        }
        html.append("</ul></body></html>");
        
        return html.toString();
    }
    
    /**
     * Obtiene el validador singleton para uso avanzado.
     * 
     * @return El validador de Bean Validation
     */
    public static Validator getValidator() {
        return validator;
    }
}

