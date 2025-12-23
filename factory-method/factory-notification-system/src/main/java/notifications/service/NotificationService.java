package notifications.service;

import notifications.factory.NotificationCreator;
import notifications.factory.EmailNotificationCreator;
import notifications.factory.SMSNotificationCreator;
import notifications.factory.PushNotificationCreator;
import notifications.model.Notification;
import notifications.model.NotificationType;
import notifications.data.*;
import notifications.message.FormattedMessage;
import notifications.message.MessageFormatter;

import java.util.Map;
import java.util.List;

public class NotificationService {
    
    private final NotificationHistoryService historyService;
    private final MessageFormatter messageFormatter;
    
    public NotificationService() {
        this.historyService = new NotificationHistoryService();
        this.messageFormatter = new MessageFormatter();
    }
    
    public void sendPurchaseConfirmation(PurchaseConfirmationData data) {
        FormattedMessage formatted = messageFormatter.formatPurchaseConfirmation(data);
        sendNotification(data.getUserId(), data.getNotificationType(), formatted);
    }
    
    public void sendTicketWithQR(TicketWithQRData data) {
        FormattedMessage formatted = messageFormatter.formatTicketWithQR(data);
        sendNotification(data.getUserId(), data.getNotificationType(), formatted);
    }
    
    public void sendEventReminder(EventReminderData data) {
        FormattedMessage formatted = messageFormatter.formatEventReminder(data);
        sendNotification(data.getUserId(), data.getNotificationType(), formatted);
    }
    
    public void sendEventCancellation(EventCancellationData data) {
        FormattedMessage formatted = messageFormatter.formatEventCancellation(data);
        sendNotification(data.getUserId(), data.getNotificationType(), formatted);
    }
    
    public void sendEventDateChange(EventDateChangeData data) {
        FormattedMessage formatted = messageFormatter.formatEventDateChange(data);
        sendNotification(data.getUserId(), data.getNotificationType(), formatted);
    }
    
    public void sendPaymentReminder(PaymentReminderData data) {
        FormattedMessage formatted = messageFormatter.formatPaymentReminder(data);
        sendNotification(data.getUserId(), data.getNotificationType(), formatted);
    }
    
    public void sendRefundConfirmation(RefundConfirmationData data) {
        FormattedMessage formatted = messageFormatter.formatRefundConfirmation(data);
        sendNotification(data.getUserId(), data.getNotificationType(), formatted);
    }
    
    public void sendVenueChange(VenueChangeData data) {
        FormattedMessage formatted = messageFormatter.formatVenueChange(data);
        sendNotification(data.getUserId(), data.getNotificationType(), formatted);
    }
    
    public void sendWaitlistNotification(WaitlistNotificationData data) {
        FormattedMessage formatted = messageFormatter.formatWaitlistNotification(data);
        sendNotification(data.getUserId(), data.getNotificationType(), formatted);
    }
    
    public void sendTicketAvailableFromWaitlist(TicketAvailableFromWaitlistData data) {
        FormattedMessage formatted = messageFormatter.formatTicketAvailableFromWaitlist(data);
        sendNotification(data.getUserId(), data.getNotificationType(), formatted);
    }
    
    private void sendNotification(String userId, NotificationType notificationType, FormattedMessage message) {
        // Crear la notificación usando el factory
        NotificationCreator creator = getNotificationCreator(notificationType);
        Notification notification = creator.createNotification();
        
        // Enviar la notificación
        notification.send();
        
        // Registrar en el historial
        historyService.addToHistory(userId, message.getSubject(), message.getContent());
        
        System.out.println("═══════════════════════════════════════════════");
        System.out.println("📨 Notificación enviada a usuario: " + userId);
        System.out.println("Asunto: " + message.getSubject());
        System.out.println("───────────────────────────────────────────────");
        System.out.println(message.getContent());
        System.out.println("═══════════════════════════════════════════════\n");
    }
    
    private NotificationCreator getNotificationCreator(NotificationType type) {
        return switch (type) {
            case EMAIL -> new EmailNotificationCreator();
            case SMS -> new SMSNotificationCreator();
            case PUSH -> new PushNotificationCreator();
        };
    }
    
    public List<String> getNotificationHistory(String userId) {
        return historyService.getNotificationHistory(userId);
    }
    
    public void clearNotificationHistory(String userId) {
        historyService.clearNotificationHistory(userId);
    }
    
    public Map<String, Integer> getNotificationStats() {
        return historyService.getNotificationStats();
    }
}

