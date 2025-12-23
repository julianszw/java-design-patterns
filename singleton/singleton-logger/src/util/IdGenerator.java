package util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Generador de IDs único y thread-safe para entidades.
 * Utiliza un contador por tipo de entidad.
 */
public class IdGenerator {
    private static final ConcurrentHashMap<String, AtomicLong> counters = new ConcurrentHashMap<>();

    private IdGenerator() {
        // Clase de utilidad: evita instanciación
    }

    /**
     * Genera un ID único e incremental para la clase de entidad especificada.
     */
    public static long generateId(Class<?> entityClass) {
        return counters
                .computeIfAbsent(entityClass.getSimpleName(), k -> new AtomicLong(0))
                .incrementAndGet();
    }
}

