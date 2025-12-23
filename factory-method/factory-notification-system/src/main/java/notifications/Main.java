package notifications;

import notifications.service.NotificationService;
import notifications.data.*;
import notifications.model.NotificationType;
import notifications.util.ConsoleUtil;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        NotificationService notificationService = new NotificationService();
        
        ConsoleUtil.printHeader("SISTEMA DE NOTIFICACIONES - DEMO");
        
        // 1. Confirmación de compra
        ConsoleUtil.printSectionTitle("1. Confirmación de Compra");
        PurchaseConfirmationData purchaseData = new PurchaseConfirmationData(
            "user123",
            "ORD-2024-001",
            "Concierto de Rock 2024",
            2,
            150.00,
            NotificationType.EMAIL
        );
        notificationService.sendPurchaseConfirmation(purchaseData);
        
        // 2. Entrada con código QR
        ConsoleUtil.printSectionTitle("2. Entrada Digital con QR");
        TicketWithQRData ticketData = new TicketWithQRData(
            "user123",
            "ORD-2024-001",
            "Concierto de Rock 2024",
            LocalDateTime.of(2024, 12, 31, 20, 0),
            "QR-ABC123XYZ",
            NotificationType.PUSH
        );
        notificationService.sendTicketWithQR(ticketData);
        
        // 3. Recordatorio de evento
        ConsoleUtil.printSectionTitle("3. Recordatorio de Evento");
        EventReminderData reminderData = new EventReminderData(
            "user123",
            "Concierto de Rock 2024",
            LocalDateTime.of(2024, 12, 31, 20, 0),
            "Estadio Nacional",
            NotificationType.SMS
        );
        notificationService.sendEventReminder(reminderData);
        
        // 4. Cambio de fecha
        ConsoleUtil.printSectionTitle("4. Cambio de Fecha del Evento");
        EventDateChangeData dateChangeData = new EventDateChangeData(
            "user456",
            "Festival de Jazz",
            LocalDateTime.of(2024, 11, 15, 19, 0),
            LocalDateTime.of(2024, 11, 22, 19, 0),
            NotificationType.EMAIL
        );
        notificationService.sendEventDateChange(dateChangeData);
        
        // 5. Recordatorio de pago
        ConsoleUtil.printSectionTitle("5. Recordatorio de Pago");
        PaymentReminderData paymentData = new PaymentReminderData(
            "user789",
            "ORD-2024-002",
            "Teatro Musical 2024",
            75.00,
            LocalDateTime.of(2024, 11, 30, 23, 59),
            NotificationType.PUSH
        );
        notificationService.sendPaymentReminder(paymentData);
        
        // 6. Lista de espera
        ConsoleUtil.printSectionTitle("6. Lista de Espera");
        WaitlistNotificationData waitlistData = new WaitlistNotificationData(
            "user999",
            "Partido Final Champions",
            5,
            NotificationType.EMAIL
        );
        notificationService.sendWaitlistNotification(waitlistData);
        
        // 7. Entradas disponibles desde lista de espera
        ConsoleUtil.printSectionTitle("7. Entradas Disponibles");
        TicketAvailableFromWaitlistData availableData = new TicketAvailableFromWaitlistData(
            "user999",
            "Partido Final Champions",
            2,
            LocalDateTime.now().plusHours(24),
            NotificationType.PUSH
        );
        notificationService.sendTicketAvailableFromWaitlist(availableData);
        
        // 8. Cancelación de evento
        ConsoleUtil.printSectionTitle("8. Cancelación de Evento");
        EventCancellationData cancellationData = new EventCancellationData(
            "user111",
            "ORD-2024-003",
            "Concierto de Pop",
            120.00,
            NotificationType.EMAIL
        );
        notificationService.sendEventCancellation(cancellationData);
        
        // 9. Confirmación de reembolso
        ConsoleUtil.printSectionTitle("9. Confirmación de Reembolso");
        RefundConfirmationData refundData = new RefundConfirmationData(
            "user111",
            "ORD-2024-003",
            120.00,
            NotificationType.EMAIL
        );
        notificationService.sendRefundConfirmation(refundData);
        
        // 10. Cambio de lugar
        ConsoleUtil.printSectionTitle("10. Cambio de Lugar");
        VenueChangeData venueData = new VenueChangeData(
            "user222",
            "Stand-up Comedy Night",
            "Teatro Principal",
            "Auditorio Nacional",
            NotificationType.SMS
        );
        notificationService.sendVenueChange(venueData);
        
        // Mostrar estadísticas
        ConsoleUtil.printHeader("ESTADÍSTICAS DEL SISTEMA");
        System.out.println("Notificaciones enviadas por usuario:");
        notificationService.getNotificationStats().forEach((userId, count) -> 
            System.out.println("  " + userId + ": " + count + " notificaciones")
        );
        
        ConsoleUtil.printFooter("FIN DE LA DEMO");
    }
}

