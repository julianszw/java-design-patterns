package notifications.factory;

import notifications.model.PushNotification;
import notifications.model.Notification;

public class PushNotificationCreator implements NotificationCreator {
    
    @Override
    public Notification createNotification() {
        return new PushNotification();
    }
}

