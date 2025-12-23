package notifications.model;

public class PushNotification extends Notification {
    
    @Override
    public void send() {
        System.out.println("🔔 Enviando notificación PUSH");
    }
}

