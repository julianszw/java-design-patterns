package notifications.data;

import notifications.model.NotificationType;

public class PurchaseConfirmationData {
    private final String userId;
    private final String orderId;
    private final String eventName;
    private final int ticketQuantity;
    private final double totalAmount;
    private final NotificationType notificationType;
    
    public PurchaseConfirmationData(String userId, String orderId, String eventName, 
                                    int ticketQuantity, double totalAmount, NotificationType notificationType) {
        this.userId = userId;
        this.orderId = orderId;
        this.eventName = eventName;
        this.ticketQuantity = ticketQuantity;
        this.totalAmount = totalAmount;
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
    
    public int getTicketQuantity() {
        return ticketQuantity;
    }
    
    public double getTotalAmount() {
        return totalAmount;
    }
    
    public NotificationType getNotificationType() {
        return notificationType;
    }
}

