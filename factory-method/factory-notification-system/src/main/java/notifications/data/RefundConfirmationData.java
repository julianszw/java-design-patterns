package notifications.data;

import notifications.model.NotificationType;

public class RefundConfirmationData {
    private final String userId;
    private final String orderId;
    private final double refundAmount;
    private final NotificationType notificationType;
    
    public RefundConfirmationData(String userId, String orderId, double refundAmount, 
                                  NotificationType notificationType) {
        this.userId = userId;
        this.orderId = orderId;
        this.refundAmount = refundAmount;
        this.notificationType = notificationType;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public String getOrderId() {
        return orderId;
    }
    
    public double getRefundAmount() {
        return refundAmount;
    }
    
    public NotificationType getNotificationType() {
        return notificationType;
    }
}

