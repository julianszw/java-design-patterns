package notifications.data;

import notifications.model.NotificationType;

public class WaitlistNotificationData {
    private final String userId;
    private final String eventName;
    private final int position;
    private final NotificationType notificationType;
    
    public WaitlistNotificationData(String userId, String eventName, int position, 
                                    NotificationType notificationType) {
        this.userId = userId;
        this.eventName = eventName;
        this.position = position;
        this.notificationType = notificationType;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public String getEventName() {
        return eventName;
    }
    
    public int getPosition() {
        return position;
    }
    
    public NotificationType getNotificationType() {
        return notificationType;
    }
}

