package notifications.data;

import notifications.model.NotificationType;
import java.time.LocalDateTime;

public class TicketAvailableFromWaitlistData {
    private final String userId;
    private final String eventName;
    private final int availableTickets;
    private final LocalDateTime expirationTime;
    private final NotificationType notificationType;
    
    public TicketAvailableFromWaitlistData(String userId, String eventName, int availableTickets, 
                                           LocalDateTime expirationTime, NotificationType notificationType) {
        this.userId = userId;
        this.eventName = eventName;
        this.availableTickets = availableTickets;
        this.expirationTime = expirationTime;
        this.notificationType = notificationType;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public String getEventName() {
        return eventName;
    }
    
    public int getAvailableTickets() {
        return availableTickets;
    }
    
    public LocalDateTime getExpirationTime() {
        return expirationTime;
    }
    
    public NotificationType getNotificationType() {
        return notificationType;
    }
}

