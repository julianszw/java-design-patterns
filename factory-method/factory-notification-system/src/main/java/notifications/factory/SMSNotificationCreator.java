package notifications.factory;

import notifications.model.SMSNotification;
import notifications.model.Notification;

public class SMSNotificationCreator implements NotificationCreator {
    
    @Override
    public Notification createNotification() {
        return new SMSNotification();
    }
}

