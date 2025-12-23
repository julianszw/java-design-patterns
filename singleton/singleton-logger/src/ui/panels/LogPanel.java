package ui.panels;

import util.Logger;
import util.Logger.LogLevel;
import util.Logger.LogListener;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;

/**
 * Panel para visualizar logs en tiempo real con colores según el nivel.
 */
public class LogPanel extends JPanel implements LogListener {
    
    private JTextPane logTextPane;
    private StyledDocument document;
    
    private Style infoStyle;
    private Style warnStyle;
    private Style errorStyle;
    
    public LogPanel() {
        setLayout(new BorderLayout());
        setBorder(BorderFactory.createTitledBorder("Registro de Logs"));
        
        // Crear el área de texto para logs
        logTextPane = new JTextPane();
        logTextPane.setEditable(false);
        logTextPane.setFont(new Font("Monospaced", Font.PLAIN, 12));
        document = logTextPane.getStyledDocument();
        
        // Configurar estilos de colores
        setupStyles();
        
        // Scroll pane
        JScrollPane scrollPane = new JScrollPane(logTextPane);
        scrollPane.setPreferredSize(new Dimension(600, 200));
        add(scrollPane, BorderLayout.CENTER);
        
        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton clearButton = new JButton("Limpiar Logs");
        clearButton.addActionListener(e -> clearLogs());
        buttonPanel.add(clearButton);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Registrar este panel como listener del Logger
        Logger.getInstance().addLogListener(this);
    }
    
    private void setupStyles() {
        // Estilo INFO (azul)
        infoStyle = logTextPane.addStyle("INFO", null);
        StyleConstants.setForeground(infoStyle, new Color(0, 100, 200));
        
        // Estilo WARN (naranja)
        warnStyle = logTextPane.addStyle("WARN", null);
        StyleConstants.setForeground(warnStyle, new Color(255, 140, 0));
        StyleConstants.setBold(warnStyle, true);
        
        // Estilo ERROR (rojo)
        errorStyle = logTextPane.addStyle("ERROR", null);
        StyleConstants.setForeground(errorStyle, Color.RED);
        StyleConstants.setBold(errorStyle, true);
    }
    
    @Override
    public void onLog(String message, LogLevel level) {
        // Ejecutar en el Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            try {
                Style style = getStyleForLevel(level);
                document.insertString(document.getLength(), message + "\n", style);
                
                // Auto-scroll al final
                logTextPane.setCaretPosition(document.getLength());
            } catch (BadLocationException e) {
                e.printStackTrace();
            }
        });
    }
    
    private Style getStyleForLevel(LogLevel level) {
        switch (level) {
            case INFO:
                return infoStyle;
            case WARN:
                return warnStyle;
            case ERROR:
                return errorStyle;
            default:
                return infoStyle;
        }
    }
    
    private void clearLogs() {
        try {
            document.remove(0, document.getLength());
        } catch (BadLocationException e) {
            e.printStackTrace();
        }
    }
}

