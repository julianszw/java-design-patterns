package notifications.data;

import notifications.model.NotificationType;
import java.time.LocalDateTime;

public class EventDateChangeData {
    private final String userId;
    private final String eventName;
    private final LocalDateTime oldDate;
    private final LocalDateTime newDate;
    private final NotificationType notificationType;
    
    public EventDateChangeData(String userId, String eventName, LocalDateTime oldDate, 
                               LocalDateTime newDate, NotificationType notificationType) {
        this.userId = userId;
        this.eventName = eventName;
        this.oldDate = oldDate;
        this.newDate = newDate;
        this.notificationType = notificationType;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public String getEventName() {
        return eventName;
    }
    
    public LocalDateTime getOldDate() {
        return oldDate;
    }
    
    public LocalDateTime getNewDate() {
        return newDate;
    }
    
    public NotificationType getNotificationType() {
        return notificationType;
    }
}

