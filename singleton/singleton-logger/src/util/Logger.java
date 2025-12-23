package util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * Singleton Logger para centralizar el logging de la aplicación.
 * Implementa el patrón Singleton con sincronización thread-safe.
 * Soporta listeners para notificar a la GUI de nuevos logs.
 */
public class Logger {
    private static Logger instance;
    private List<LogListener> listeners = new ArrayList<>();
    private static final DateTimeFormatter TIMESTAMP_FORMATTER = 
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private Logger() {
        System.out.println("Logger inicializado");
    }

    /**
     * Obtiene la única instancia del Logger (Singleton thread-safe).
     */
    public static synchronized Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
        }
        return instance;
    }

    /**
     * Registra un listener para recibir notificaciones de logs.
     */
    public void addLogListener(LogListener listener) {
        listeners.add(listener);
    }

    /**
     * Elimina un listener.
     */
    public void removeLogListener(LogListener listener) {
        listeners.remove(listener);
    }

    /**
     * Obtiene el timestamp actual formateado.
     */
    private String getCurrentTimestamp() {
        return LocalDateTime.now().format(TIMESTAMP_FORMATTER);
    }

    public void info(String message) {
        String logMessage = "[" + getCurrentTimestamp() + "] [INFO] " + message;
        System.out.println(logMessage);
        notifyListeners(logMessage, LogLevel.INFO);
    }

    public void warn(String message) {
        String logMessage = "[" + getCurrentTimestamp() + "] [WARN] " + message;
        System.out.println(logMessage);
        notifyListeners(logMessage, LogLevel.WARN);
    }

    public void error(String message) {
        String logMessage = "[" + getCurrentTimestamp() + "] [ERROR] " + message;
        System.err.println(logMessage);
        notifyListeners(logMessage, LogLevel.ERROR);
    }

    private void notifyListeners(String message, LogLevel level) {
        for (LogListener listener : listeners) {
            listener.onLog(message, level);
        }
    }

    /**
     * Interface para listeners de log.
     */
    public interface LogListener {
        void onLog(String message, LogLevel level);
    }

    /**
     * Niveles de log.
     */
    public enum LogLevel {
        INFO, WARN, ERROR
    }
}

