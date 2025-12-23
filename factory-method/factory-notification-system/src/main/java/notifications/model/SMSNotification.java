package notifications.model;

public class SMSNotification extends Notification {
    
    @Override
    public void send() {
        System.out.println("📱 Enviando notificación por SMS");
    }
}

