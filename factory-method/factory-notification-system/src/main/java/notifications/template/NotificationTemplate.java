package notifications.template;

public enum NotificationTemplate {
    
    PURCHASE_CONFIRMATION(
        "✅ Confirmación de Compra",
        """
        ✓ Compra confirmada
        Orden: %s
        Evento: %s
        Entradas: %d
        Total: $%.2f
        Fecha: %s
        """
    ),
    
    TICKET_WITH_QR(
        "🎫 Entrada Digital",
        """
        🎫 Tu entrada está lista
        Evento: %s
        Fecha: %s
        Código QR: %s
        Orden: %s
        
        Presenta este código en la entrada del evento.
        """
    ),
    
    EVENT_REMINDER(
        "⏰ Recordatorio de Evento",
        """
        ⏰ Recordatorio de Evento
        El evento '%s' se acerca!
        Fecha: %s
        Lugar: %s
        
        No olvides tu entrada y llega con anticipación.
        """
    ),
    
    EVENT_CANCELLATION(
        "❌ Cancelación de Evento",
        """
        ❌ Evento Cancelado
        Evento: %s
        Orden: %s
        Reembolso: $%.2f
        
        Lamentamos informarte que el evento ha sido cancelado. El reembolso será procesado en 5-7 días hábiles.
        """
    ),
    
    EVENT_DATE_CHANGE(
        "📅 Cambio de Fecha",
        """
        📅 Cambio de Fecha
        Evento: %s
        Fecha anterior: %s
        Nueva fecha: %s
        
        Si no puedes asistir en la nueva fecha, puedes solicitar un reembolso.
        """
    ),
    
    PAYMENT_REMINDER(
        "💳 Recordatorio de Pago",
        """
        💳 Pago Pendiente
        Orden: %s
        Evento: %s
        Monto pendiente: $%.2f
        Fecha límite: %s
        
        Complete su pago para confirmar su reserva.
        """
    ),
    
    REFUND_CONFIRMATION(
        "💰 Reembolso Confirmado",
        """
        💰 Reembolso Procesado
        Orden: %s
        Monto: $%.2f
        Fecha: %s
        
        El reembolso ha sido procesado y se verá reflejado en tu cuenta en breve.
        """
    ),
    
    VENUE_CHANGE(
        "📍 Cambio de Lugar",
        """
        📍 Cambio de Lugar
        Evento: %s
        Lugar anterior: %s
        Nuevo lugar: %s
        
        Por favor toma nota del cambio de ubicación.
        """
    ),
    
    WAITLIST_NOTIFICATION(
        "⏳ Lista de Espera",
        """
        ⏳ Lista de Espera
        Evento: %s
        Posición: %d
        
        Has sido agregado a la lista de espera. Te notificaremos si hay entradas disponibles.
        """
    ),
    
    TICKET_AVAILABLE_FROM_WAITLIST(
        "🎉 Entradas Disponibles",
        """
        🎉 ¡Entradas Disponibles!
        Evento: %s
        Entradas disponibles: %d
        Expira: %s
        
        ¡Apresúrate! Tienes tiempo limitado para completar tu compra.
        """
    );
    
    private final String subject;
    private final String messageTemplate;
    
    NotificationTemplate(String subject, String messageTemplate) {
        this.subject = subject;
        this.messageTemplate = messageTemplate;
    }
    
    public String getSubject() {
        return subject;
    }
    
    public String getMessageTemplate() {
        return messageTemplate;
    }
    
    /**
     * Formatea el mensaje con los parámetros proporcionados
     */
    public String format(Object... args) {
        return String.format(messageTemplate, args);
    }
}

