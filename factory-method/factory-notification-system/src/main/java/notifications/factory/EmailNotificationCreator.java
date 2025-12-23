package notifications.factory;

import notifications.model.EmailNotification;
import notifications.model.Notification;

public class EmailNotificationCreator implements NotificationCreator {
    
    @Override
    public Notification createNotification() {
        return new EmailNotification();
    }
}

