package ui.panels;

import domain.entity.Professor;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Panel para gestionar profesores (CRUD).
 */
public class ProfessorPanel extends JPanel {
    
    private List<Professor> professors = new ArrayList<>();
    private DefaultTableModel tableModel;
    private JTable table;
    
    private JTextField nameField;
    private JTextField emailField;
    private JTextField personalIdField;
    private JTextField enrollmentDateField;
    private JTextField searchField;
    
    public ProfessorPanel() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        
        // Panel superior con formulario
        add(createFormPanel(), BorderLayout.NORTH);
        
        // Panel central con tabla
        add(createTablePanel(), BorderLayout.CENTER);
        
        // Panel inferior con botones de acción
        add(createActionPanel(), BorderLayout.SOUTH);
    }
    
    private JPanel createFormPanel() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Crear Profesor"));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Campo nombre
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Nombre:"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        nameField = new JTextField(20);
        panel.add(nameField, gbc);
        
        // Campo email
        gbc.gridx = 2;
        gbc.weightx = 0;
        panel.add(new JLabel("Email:"), gbc);
        
        gbc.gridx = 3;
        gbc.weightx = 1.0;
        emailField = new JTextField(20);
        panel.add(emailField, gbc);
        
        // Campo Personal ID
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        panel.add(new JLabel("ID Personal:"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        personalIdField = new JTextField(20);
        panel.add(personalIdField, gbc);
        
        // Campo Fecha de Inscripción
        gbc.gridx = 2;
        gbc.weightx = 0;
        panel.add(new JLabel("Fecha Inscripción (YYYY-MM-DD):"), gbc);
        
        gbc.gridx = 3;
        gbc.weightx = 1.0;
        enrollmentDateField = new JTextField(15);
        enrollmentDateField.setText(LocalDate.now().toString());
        panel.add(enrollmentDateField, gbc);
        
        // Botón crear
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.weightx = 0;
        JButton createButton = new JButton("Crear Profesor");
        createButton.setBackground(new Color(46, 125, 50));
        createButton.setForeground(Color.WHITE);
        createButton.addActionListener(e -> createProfessor());
        panel.add(createButton, gbc);
        
        gbc.gridheight = 1;
        
        return panel;
    }
    
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Lista de Profesores"));
        
        // Tabla
        String[] columnNames = {"ID", "Nombre", "Email", "ID Personal", "Fecha Inscripción"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getColumnModel().getColumn(0).setPreferredWidth(60);
        table.getColumnModel().getColumn(1).setPreferredWidth(180);
        table.getColumnModel().getColumn(2).setPreferredWidth(200);
        table.getColumnModel().getColumn(3).setPreferredWidth(120);
        table.getColumnModel().getColumn(4).setPreferredWidth(120);
        
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Panel de búsqueda
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(new JLabel("Buscar:"));
        searchField = new JTextField(20);
        searchField.addActionListener(e -> searchProfessors());
        searchPanel.add(searchField);
        
        JButton searchButton = new JButton("Buscar");
        searchButton.addActionListener(e -> searchProfessors());
        searchPanel.add(searchButton);
        
        JButton showAllButton = new JButton("Mostrar Todos");
        showAllButton.addActionListener(e -> refreshTable());
        searchPanel.add(showAllButton);
        
        panel.add(searchPanel, BorderLayout.NORTH);
        
        return panel;
    }
    
    private JPanel createActionPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        
        JButton updateEmailButton = new JButton("Actualizar Email");
        updateEmailButton.setBackground(new Color(25, 118, 210));
        updateEmailButton.setForeground(Color.WHITE);
        updateEmailButton.addActionListener(e -> updateProfessorEmail());
        panel.add(updateEmailButton);
        
        JButton deleteButton = new JButton("Eliminar Seleccionado");
        deleteButton.setBackground(new Color(211, 47, 47));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.addActionListener(e -> deleteProfessor());
        panel.add(deleteButton);
        
        JButton refreshButton = new JButton("Refrescar");
        refreshButton.addActionListener(e -> refreshTable());
        panel.add(refreshButton);
        
        return panel;
    }
    
    private void createProfessor() {
        String name = nameField.getText().trim();
        String email = emailField.getText().trim();
        String personalId = personalIdField.getText().trim();
        String enrollmentDateStr = enrollmentDateField.getText().trim();
        
        if (name.isEmpty() || email.isEmpty() || personalId.isEmpty() || enrollmentDateStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Todos los campos son obligatorios", 
                "Error de Validación", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            LocalDate enrollmentDate = LocalDate.parse(enrollmentDateStr, DateTimeFormatter.ISO_LOCAL_DATE);
            Professor professor = new Professor(name, email, personalId, enrollmentDate);
            professors.add(professor);
            refreshTable();
            nameField.setText("");
            emailField.setText("");
            personalIdField.setText("");
            enrollmentDateField.setText(LocalDate.now().toString());
            
            JOptionPane.showMessageDialog(this, 
                "Profesor creado exitosamente", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, 
                "Formato de fecha inválido. Use YYYY-MM-DD", 
                "Error de Formato", 
                JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, 
                "Error al crear profesor: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateProfessorEmail() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Por favor selecciona un profesor de la tabla", 
                "No hay selección", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Professor professor = professors.get(selectedRow);
        String newEmail = JOptionPane.showInputDialog(this, 
            "Ingresa el nuevo email para " + professor.getName() + ":", 
            professor.getEmail());
        
        if (newEmail != null && !newEmail.trim().isEmpty()) {
            try {
                professor.updateEmail(newEmail.trim());
                refreshTable();
                
                JOptionPane.showMessageDialog(this, 
                    "Email actualizado exitosamente", 
                    "Éxito", 
                    JOptionPane.INFORMATION_MESSAGE);
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, 
                    "Error al actualizar email: " + e.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void deleteProfessor() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Por favor selecciona un profesor de la tabla", 
                "No hay selección", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Professor professor = professors.get(selectedRow);
        int confirm = JOptionPane.showConfirmDialog(this, 
            "¿Estás seguro de eliminar al profesor: " + professor.getName() + "?", 
            "Confirmar Eliminación", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            professor.delete();
            professors.remove(selectedRow);
            refreshTable();
            
            JOptionPane.showMessageDialog(this, 
                "Profesor eliminado exitosamente", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void searchProfessors() {
        String searchTerm = searchField.getText().trim().toLowerCase();
        
        if (searchTerm.isEmpty()) {
            refreshTable();
            return;
        }
        
        tableModel.setRowCount(0);
        for (Professor professor : professors) {
            if (professor.getName().toLowerCase().contains(searchTerm) ||
                professor.getEmail().toLowerCase().contains(searchTerm) ||
                professor.getPersonalId().toLowerCase().contains(searchTerm)) {
                tableModel.addRow(new Object[]{
                    professor.getId(), 
                    professor.getName(), 
                    professor.getEmail(), 
                    professor.getPersonalId(),
                    professor.getEnrollmentDate()
                });
            }
        }
    }
    
    private void refreshTable() {
        tableModel.setRowCount(0);
        for (Professor professor : professors) {
            tableModel.addRow(new Object[]{
                professor.getId(), 
                professor.getName(), 
                professor.getEmail(), 
                professor.getPersonalId(),
                professor.getEnrollmentDate()
            });
        }
    }
    
    public List<Professor> getProfessors() {
        return professors;
    }
}

