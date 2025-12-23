package notifications.model;

public class EmailNotification extends Notification {
    
    @Override
    public void send() {
        System.out.println("📧 Enviando notificación por EMAIL");
    }
}

