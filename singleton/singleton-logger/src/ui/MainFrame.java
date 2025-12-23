package ui;

import ui.panels.StudentPanel;
import ui.panels.SubjectPanel;
import ui.panels.ProfessorPanel;
import ui.panels.CareerPathPanel;
import ui.panels.LogPanel;

import javax.swing.*;
import java.awt.*;

/**
 * Ventana principal de la aplicación con gestión de entidades y logs.
 */
public class MainFrame extends JFrame {
    
    private JTabbedPane tabbedPane;
    private StudentPanel studentPanel;
    private SubjectPanel subjectPanel;
    private ProfessorPanel professorPanel;
    private CareerPathPanel careerPathPanel;
    private LogPanel logPanel;
    
    public MainFrame() {
        setTitle("Sistema de Registro Académico - SingletonLogger");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 750);
        setLocationRelativeTo(null);
        
        // Configurar Look and Feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        initComponents();
    }
    
    private void initComponents() {
        setLayout(new BorderLayout());
        
        // Crear panel de bienvenida en la parte superior
        add(createHeaderPanel(), BorderLayout.NORTH);
        
        // Crear panel principal con pestañas
        tabbedPane = new JTabbedPane();
        
        // Crear paneles
        careerPathPanel = new CareerPathPanel();
        professorPanel = new ProfessorPanel();
        studentPanel = new StudentPanel();
        subjectPanel = new SubjectPanel();
        logPanel = new LogPanel();
        
        // Agregar pestañas con iconos (usando iconos Unicode)
        tabbedPane.addTab("🎓 Estudiantes", studentPanel);
        tabbedPane.addTab("📚 Materias", subjectPanel);
        tabbedPane.addTab("👨‍🏫 Profesores", professorPanel);
        tabbedPane.addTab("🎯 Carreras", careerPathPanel);
        tabbedPane.addTab("📋 Logs", logPanel);
        
        // Listener para refrescar datos dependientes cuando se cambia de pestaña (si es necesario)
        // No hay dependencias entre paneles actualmente
        
        add(tabbedPane, BorderLayout.CENTER);
        
        // Panel de pie de página
        add(createFooterPanel(), BorderLayout.SOUTH);
    }
    
    private JPanel createHeaderPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(33, 150, 243));
        panel.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        panel.setLayout(new BorderLayout());
        
        JLabel titleLabel = new JLabel("Sistema de Registro Académico");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        panel.add(titleLabel, BorderLayout.WEST);
        
        JLabel subtitleLabel = new JLabel("Gestión de Estudiantes, Materias, Profesores y Carreras | Singleton Logger");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitleLabel.setForeground(new Color(200, 230, 255));
        panel.add(subtitleLabel, BorderLayout.SOUTH);
        
        return panel;
    }
    
    private JPanel createFooterPanel() {
        JPanel panel = new JPanel();
        panel.setBackground(new Color(240, 240, 240));
        panel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        panel.setLayout(new FlowLayout(FlowLayout.LEFT));
        
        JLabel footerLabel = new JLabel("© 2024 - Sistema Educativo | Patrón Singleton | Java Swing GUI");
        footerLabel.setFont(new Font("Arial", Font.PLAIN, 11));
        footerLabel.setForeground(Color.GRAY);
        panel.add(footerLabel);
        
        return panel;
    }
    
    public static void main(String[] args) {
        // Ejecutar la GUI en el Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}

