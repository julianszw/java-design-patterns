package notifications.data;

import notifications.model.NotificationType;
import java.time.LocalDateTime;

public class TicketWithQRData {
    private final String userId;
    private final String orderId;
    private final String eventName;
    private final LocalDateTime eventDate;
    private final String qrCode;
    private final NotificationType notificationType;
    
    public TicketWithQRData(String userId, String orderId, String eventName, 
                            LocalDateTime eventDate, String qrCode, NotificationType notificationType) {
        this.userId = userId;
        this.orderId = orderId;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.qrCode = qrCode;
        this.notificationType = notificationType;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public String getOrderId() {
        return orderId;
    }
    
    public String getEventName() {
        return eventName;
    }
    
    public LocalDateTime getEventDate() {
        return eventDate;
    }
    
    public String getQrCode() {
        return qrCode;
    }
    
    public NotificationType getNotificationType() {
        return notificationType;
    }
}

