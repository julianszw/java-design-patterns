package notifications.data;

import notifications.model.NotificationType;
import java.time.LocalDateTime;

public class EventReminderData {
    private final String userId;
    private final String eventName;
    private final LocalDateTime eventDate;
    private final String venue;
    private final NotificationType notificationType;
    
    public EventReminderData(String userId, String eventName, LocalDateTime eventDate, 
                             String venue, NotificationType notificationType) {
        this.userId = userId;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.venue = venue;
        this.notificationType = notificationType;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public String getEventName() {
        return eventName;
    }
    
    public LocalDateTime getEventDate() {
        return eventDate;
    }
    
    public String getVenue() {
        return venue;
    }
    
    public NotificationType getNotificationType() {
        return notificationType;
    }
}

