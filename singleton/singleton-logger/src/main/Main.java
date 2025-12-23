package main;

import ui.MainFrame;

import javax.swing.*;

/**
 * Clase principal para lanzar la aplicación GUI del sistema de registro académico.
 * Sistema de gestión para estudiantes, materias, profesores y carreras universitarias.
 */
public class Main {
    public static void main(String[] args) {
        // Ejecutar la GUI en el Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}

