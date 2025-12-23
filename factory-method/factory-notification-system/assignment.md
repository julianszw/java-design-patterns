隆Excelente idea! Aqu铆 tienes una consigna bien detallada para practicar el **Factory Method**:

## 馃彮 Consigna: Sistema de Notificaciones con Factory Method

### 馃搵 Contexto
Eres parte de un equipo desarrollando un sistema que necesita enviar diferentes tipos de notificaciones a los usuarios. El sistema debe ser extensible para agregar nuevos tipos de notificaciones en el futuro sin modificar el c贸digo existente.

### 馃幆 Objetivo
Implementar un sistema de notificaciones usando el patr贸n **Factory Method** que permita crear diferentes tipos de notificaciones de manera flexible.

### 馃帹 Requisitos

#### 1. **Tipos de Notificaci贸n a Implementar:**
- **EmailNotification:** Env铆a notificaciones por correo electr贸nico
- **SMSNotification:** Env铆a notificaciones por mensaje de texto
- **PushNotification:** Env铆a notificaciones push a dispositivos m贸viles

#### 2. **Estructura de Clases:**
```java
// Producto abstracto
Notification

// Productos concretos
EmailNotification
SMSNotification  
PushNotification

// Creador abstracto
NotificationCreator

// Creadores concretos
EmailNotificationCreator
SMSNotificationCreator
PushNotificationCreator
```

#### 3. **Funcionalidades:**
Cada notificaci贸n debe tener:
- M茅todo `send(message: String)` que muestre por consola el tipo de notificaci贸n y el mensaje
- Informaci贸n espec铆fica del tipo (ej: email destino, n煤mero telef贸nico, token del dispositivo)

### 馃殌 Caracter铆sticas a Implementar

#### **Aplicaci贸n de Consola:**
```
=== SISTEMA DE NOTIFICACIONES ===
1. Enviar notificaci贸n por Email
2. Enviar notificaci贸n por SMS  
3. Enviar notificaci贸n Push
4. Salir

Seleccione una opci贸n: 1
Ingrese el mensaje: "Su pedido ha sido enviado"
Ingrese el email destino: "usuario@ejemplo.com"
鉁?Notificaci贸n por Email enviada a usuario@ejemplo.com: "Su pedido ha sido enviado"
```

### 馃敡 Requisitos T茅cnicos del Patr贸n

1. **Factory Method:** El m茅todo `createNotification()` debe ser implementado en cada creador concreto
2. **Acoplamiento:** El c贸digo cliente debe trabajar con la interfaz `Notification`, no con implementaciones concretas
3. **Extensibilidad:** Debe ser f谩cil agregar un nuevo tipo de notificaci贸n (ej: `SlackNotification`)

### 馃挕 Criterios de 脡xito

- [ ] El patr贸n Factory Method est谩 correctamente implementado
- [ ] Se pueden agregar nuevos tipos de notificaci贸n sin modificar el c贸digo existente
- [ ] El c贸digo cliente no conoce las clases concretas de notificaci贸n
- [ ] La aplicaci贸n funciona correctamente en consola
- [ ] El c贸digo es claro y bien estructurado

### 馃И Ejemplo de Salida Esperada
```
=== SISTEMA DE NOTIFICACIONES ===
1. Enviar notificaci贸n por Email
2. Enviar notificaci贸n por SMS
3. Enviar notificaci贸n Push
4. Salir

Seleccione una opci贸n: 2
Ingrese el mensaje: "C贸digo de verificaci贸n: 123456"
Ingrese el n煤mero telef贸nico: "+1234567890"
馃摫 SMS enviado a +1234567890: "C贸digo de verificaci贸n: 123456"
```

### 馃専 Desaf铆o Adicional (Opcional)
- Agregar un nuevo tipo de notificaci贸n: `SlackNotification`
- Implementar un sistema de logs que registre cada notificaci贸n enviada
- Permitir enviar notificaciones a m煤ltiples destinatarios