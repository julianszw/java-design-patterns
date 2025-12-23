package notifications.data;

import notifications.model.NotificationType;
import java.time.LocalDateTime;

public class PaymentReminderData {
    private final String userId;
    private final String orderId;
    private final String eventName;
    private final double remainingAmount;
    private final LocalDateTime dueDate;
    private final NotificationType notificationType;
    
    public PaymentReminderData(String userId, String orderId, String eventName, 
                               double remainingAmount, LocalDateTime dueDate, NotificationType notificationType) {
        this.userId = userId;
        this.orderId = orderId;
        this.eventName = eventName;
        this.remainingAmount = remainingAmount;
        this.dueDate = dueDate;
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
    
    public double getRemainingAmount() {
        return remainingAmount;
    }
    
    public LocalDateTime getDueDate() {
        return dueDate;
    }
    
    public NotificationType getNotificationType() {
        return notificationType;
    }
}

