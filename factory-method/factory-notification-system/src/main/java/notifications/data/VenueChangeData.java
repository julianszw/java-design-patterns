package notifications.data;

import notifications.model.NotificationType;

public class VenueChangeData {
    private final String userId;
    private final String eventName;
    private final String oldVenue;
    private final String newVenue;
    private final NotificationType notificationType;
    
    public VenueChangeData(String userId, String eventName, String oldVenue, 
                           String newVenue, NotificationType notificationType) {
        this.userId = userId;
        this.eventName = eventName;
        this.oldVenue = oldVenue;
        this.newVenue = newVenue;
        this.notificationType = notificationType;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public String getEventName() {
        return eventName;
    }
    
    public String getOldVenue() {
        return oldVenue;
    }
    
    public String getNewVenue() {
        return newVenue;
    }
    
    public NotificationType getNotificationType() {
        return notificationType;
    }
}

