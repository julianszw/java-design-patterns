package notifications.message;

import notifications.data.*;
import notifications.template.NotificationTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class MessageFormatter {
    
    private final DateTimeFormatter dateFormatter;
    
    public MessageFormatter() {
        this.dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    }
    
    public FormattedMessage formatPurchaseConfirmation(PurchaseConfirmationData data) {
        NotificationTemplate template = NotificationTemplate.PURCHASE_CONFIRMATION;
        String content = template.format(
            data.getOrderId(),
            data.getEventName(),
            data.getTicketQuantity(),
            data.getTotalAmount(),
            LocalDateTime.now().format(dateFormatter)
        );
        return new FormattedMessage(template.getSubject(), content);
    }
    
    public FormattedMessage formatTicketWithQR(TicketWithQRData data) {
        NotificationTemplate template = NotificationTemplate.TICKET_WITH_QR;
        String content = template.format(
            data.getEventName(),
            data.getEventDate().format(dateFormatter),
            data.getQrCode(),
            data.getOrderId()
        );
        return new FormattedMessage(template.getSubject(), content);
    }
    
    public FormattedMessage formatEventReminder(EventReminderData data) {
        NotificationTemplate template = NotificationTemplate.EVENT_REMINDER;
        String content = template.format(
            data.getEventName(),
            data.getEventDate().format(dateFormatter),
            data.getVenue()
        );
        return new FormattedMessage(template.getSubject(), content);
    }
    
    public FormattedMessage formatEventCancellation(EventCancellationData data) {
        NotificationTemplate template = NotificationTemplate.EVENT_CANCELLATION;
        String content = template.format(
            data.getEventName(),
            data.getOrderId(),
            data.getRefundAmount()
        );
        return new FormattedMessage(template.getSubject(), content);
    }
    
    public FormattedMessage formatEventDateChange(EventDateChangeData data) {
        NotificationTemplate template = NotificationTemplate.EVENT_DATE_CHANGE;
        String content = template.format(
            data.getEventName(),
            data.getOldDate().format(dateFormatter),
            data.getNewDate().format(dateFormatter)
        );
        return new FormattedMessage(template.getSubject(), content);
    }
    
    public FormattedMessage formatPaymentReminder(PaymentReminderData data) {
        NotificationTemplate template = NotificationTemplate.PAYMENT_REMINDER;
        String content = template.format(
            data.getOrderId(),
            data.getEventName(),
            data.getRemainingAmount(),
            data.getDueDate().format(dateFormatter)
        );
        return new FormattedMessage(template.getSubject(), content);
    }
    
    public FormattedMessage formatRefundConfirmation(RefundConfirmationData data) {
        NotificationTemplate template = NotificationTemplate.REFUND_CONFIRMATION;
        String content = template.format(
            data.getOrderId(),
            data.getRefundAmount(),
            LocalDateTime.now().format(dateFormatter)
        );
        return new FormattedMessage(template.getSubject(), content);
    }
    
    public FormattedMessage formatVenueChange(VenueChangeData data) {
        NotificationTemplate template = NotificationTemplate.VENUE_CHANGE;
        String content = template.format(
            data.getEventName(),
            data.getOldVenue(),
            data.getNewVenue()
        );
        return new FormattedMessage(template.getSubject(), content);
    }
    
    public FormattedMessage formatWaitlistNotification(WaitlistNotificationData data) {
        NotificationTemplate template = NotificationTemplate.WAITLIST_NOTIFICATION;
        String content = template.format(
            data.getEventName(),
            data.getPosition()
        );
        return new FormattedMessage(template.getSubject(), content);
    }
    
    public FormattedMessage formatTicketAvailableFromWaitlist(TicketAvailableFromWaitlistData data) {
        NotificationTemplate template = NotificationTemplate.TICKET_AVAILABLE_FROM_WAITLIST;
        String content = template.format(
            data.getEventName(),
            data.getAvailableTickets(),
            data.getExpirationTime().format(dateFormatter)
        );
        return new FormattedMessage(template.getSubject(), content);
    }
}

