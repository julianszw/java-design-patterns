package notifications.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class NotificationHistoryService {
    
    private final Map<String, List<String>> notificationHistory;
    private final DateTimeFormatter formatter;
    
    public NotificationHistoryService() {
        this.notificationHistory = new HashMap<>();
        this.formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    }
    
    public void addToHistory(String userId, String subject, String message) {
        String logEntry = String.format("[%s] %s - %s", 
            LocalDateTime.now().format(formatter), 
            subject, 
            message.substring(0, Math.min(50, message.length())));
        
        notificationHistory.computeIfAbsent(userId, k -> new ArrayList<>()).add(logEntry);
    }
    
    public List<String> getNotificationHistory(String userId) {
        return notificationHistory.getOrDefault(userId, new ArrayList<>());
    }
    
    public void clearNotificationHistory(String userId) {
        notificationHistory.remove(userId);
    }
    
    public Map<String, Integer> getNotificationStats() {
        Map<String, Integer> stats = new HashMap<>();
        notificationHistory.forEach((userId, notifications) -> 
            stats.put(userId, notifications.size())
        );
        return stats;
    }
}

