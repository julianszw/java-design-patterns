package notifications.data;

import notifications.model.NotificationType;

public class EventCancellationData {
    private final String userId;
    private final String orderId;
    private final String eventName;
    private final double refundAmount;
    private final NotificationType notificationType;
    
    public EventCancellationData(String userId, String orderId, String eventName, 
                                 double refundAmount, NotificationType notificationType) {
        this.userId = userId;
        this.orderId = orderId;
        this.eventName = eventName;
        this.refundAmount = refundAmount;
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
    
    public double getRefundAmount() {
        return refundAmount;
    }
    
    public NotificationType getNotificationType() {
        return notificationType;
    }
}

