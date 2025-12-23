package ui.panels;

import domain.entity.Student;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Panel para gestionar estudiantes (CRUD).
 */
public class StudentPanel extends JPanel {
    
    private List<Student> students = new ArrayList<>();
    private DefaultTableModel tableModel;
    private JTable table;
    
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField personalIdField;
    private JTextField enrollmentDateField;
    private JTextField searchField;
    
    public StudentPanel() {
        
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
        panel.setBorder(BorderFactory.createTitledBorder("Registrar Estudiante"));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        // Campo nombre
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Nombre:"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        firstNameField = new JTextField(15);
        panel.add(firstNameField, gbc);
        
        // Campo apellido
        gbc.gridx = 2;
        gbc.weightx = 0;
        panel.add(new JLabel("Apellido:"), gbc);
        
        gbc.gridx = 3;
        gbc.weightx = 1.0;
        lastNameField = new JTextField(15);
        panel.add(lastNameField, gbc);
        
        // Campo email
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        panel.add(new JLabel("Email:"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        emailField = new JTextField(20);
        panel.add(emailField, gbc);
        
        // Campo Personal ID
        gbc.gridx = 2;
        gbc.weightx = 0;
        panel.add(new JLabel("ID Personal:"), gbc);
        
        gbc.gridx = 3;
        gbc.weightx = 1.0;
        personalIdField = new JTextField(15);
        panel.add(personalIdField, gbc);
        
        // Campo Fecha de Inscripción
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        panel.add(new JLabel("Fecha Inscripción (YYYY-MM-DD):"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        enrollmentDateField = new JTextField(15);
        enrollmentDateField.setText(LocalDate.now().toString());
        panel.add(enrollmentDateField, gbc);
        
        // Botón crear
        gbc.gridx = 4;
        gbc.gridy = 0;
        gbc.gridheight = 3;
        gbc.weightx = 0;
        JButton createButton = new JButton("Registrar");
        createButton.setBackground(new Color(46, 125, 50));
        createButton.setForeground(Color.WHITE);
        createButton.addActionListener(e -> createStudent());
        panel.add(createButton, gbc);
        
        gbc.gridheight = 1;
        
        return panel;
    }
    
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Lista de Estudiantes"));
        
        // Tabla
        String[] columnNames = {"ID", "Nombre Completo", "Email", "ID Personal", "Fecha Inscripción"};
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
        searchField.addActionListener(e -> searchStudents());
        searchPanel.add(searchField);
        
        JButton searchButton = new JButton("Buscar");
        searchButton.addActionListener(e -> searchStudents());
        searchPanel.add(searchButton);
        
        JButton showAllButton = new JButton("Mostrar Todos");
        showAllButton.addActionListener(e -> refreshTable());
        searchPanel.add(showAllButton);
        
        panel.add(searchPanel, BorderLayout.NORTH);
        
        return panel;
    }
    
    private JPanel createActionPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        
        JButton modifyButton = new JButton("Modificar");
        modifyButton.setBackground(new Color(25, 118, 210));
        modifyButton.setForeground(Color.WHITE);
        modifyButton.addActionListener(e -> modifyStudent());
        panel.add(modifyButton);
        
        JButton deleteButton = new JButton("Eliminar Seleccionado");
        deleteButton.setBackground(new Color(211, 47, 47));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.addActionListener(e -> deleteStudent());
        panel.add(deleteButton);
        
        JButton refreshButton = new JButton("Refrescar");
        refreshButton.addActionListener(e -> refreshTable());
        panel.add(refreshButton);
        
        return panel;
    }
    
    private void createStudent() {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String email = emailField.getText().trim();
        String personalId = personalIdField.getText().trim();
        String enrollmentDateStr = enrollmentDateField.getText().trim();
        
        if (firstName.isEmpty() || lastName.isEmpty() || email.isEmpty() || personalId.isEmpty() || enrollmentDateStr.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Todos los campos son obligatorios", 
                "Error de Validación", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            LocalDate enrollmentDate = LocalDate.parse(enrollmentDateStr, DateTimeFormatter.ISO_LOCAL_DATE);
            Student student = new Student(firstName, lastName, email, personalId, enrollmentDate);
            
            students.add(student);
            refreshTable();
            firstNameField.setText("");
            lastNameField.setText("");
            emailField.setText("");
            personalIdField.setText("");
            enrollmentDateField.setText(LocalDate.now().toString());
            
            JOptionPane.showMessageDialog(this, 
                "Estudiante registrado exitosamente", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
        } catch (DateTimeParseException e) {
            JOptionPane.showMessageDialog(this, 
                "Formato de fecha inválido. Use YYYY-MM-DD", 
                "Error de Formato", 
                JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, 
                "Error al registrar estudiante: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void modifyStudent() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Por favor selecciona un estudiante de la tabla", 
                "No hay selección", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Student student = students.get(selectedRow);
        
        // Crear diálogo personalizado
        JDialog dialog = new JDialog((Frame) SwingUtilities.getWindowAncestor(this), 
            "Modificar Estudiante", true);
        dialog.setLayout(new BorderLayout(10, 10));
        dialog.setSize(500, 350);
        dialog.setLocationRelativeTo(this);
        
        // Panel de formulario
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.WEST;
        
        // Campos del formulario
        JTextField editFirstName = new JTextField(student.getFirstName(), 20);
        JTextField editLastName = new JTextField(student.getLastName(), 20);
        JTextField editEmail = new JTextField(student.getEmail(), 20);
        JTextField editPersonalId = new JTextField(student.getPersonalId(), 20);
        JTextField editEnrollmentDate = new JTextField(student.getEnrollmentDate().toString(), 20);
        
        // Nombre
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;
        formPanel.add(new JLabel("Nombre:"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        formPanel.add(editFirstName, gbc);
        
        // Apellido
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        formPanel.add(new JLabel("Apellido:"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        formPanel.add(editLastName, gbc);
        
        // Email
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        formPanel.add(new JLabel("Email:"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        formPanel.add(editEmail, gbc);
        
        // ID Personal
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        formPanel.add(new JLabel("ID Personal:"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        formPanel.add(editPersonalId, gbc);
        
        // Fecha de Inscripción
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0;
        formPanel.add(new JLabel("Fecha Inscripción (YYYY-MM-DD):"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        formPanel.add(editEnrollmentDate, gbc);
        
        dialog.add(formPanel, BorderLayout.CENTER);
        
        // Panel de botones
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        
        JButton saveButton = new JButton("Guardar Cambios");
        saveButton.setBackground(new Color(46, 125, 50));
        saveButton.setForeground(Color.WHITE);
        saveButton.addActionListener(e -> {
            try {
                String newFirstName = editFirstName.getText().trim();
                String newLastName = editLastName.getText().trim();
                String newEmail = editEmail.getText().trim();
                String newPersonalId = editPersonalId.getText().trim();
                String newEnrollmentDateStr = editEnrollmentDate.getText().trim();
                
                if (newFirstName.isEmpty() || newLastName.isEmpty() || newEmail.isEmpty() || 
                    newPersonalId.isEmpty() || newEnrollmentDateStr.isEmpty()) {
                    JOptionPane.showMessageDialog(dialog, 
                        "Todos los campos son obligatorios", 
                        "Error de Validación", 
                        JOptionPane.ERROR_MESSAGE);
                    return;
                }
                
                LocalDate newEnrollmentDate = LocalDate.parse(newEnrollmentDateStr, DateTimeFormatter.ISO_LOCAL_DATE);
                
                // Actualizar campos del estudiante
                if (!newFirstName.equals(student.getFirstName())) {
                    student.updateFirstName(newFirstName);
                }
                if (!newLastName.equals(student.getLastName())) {
                    student.updateLastName(newLastName);
                }
                if (!newEmail.equals(student.getEmail())) {
                    student.updateEmail(newEmail);
                }
                if (!newPersonalId.equals(student.getPersonalId())) {
                    student.updatePersonalId(newPersonalId);
                }
                if (!newEnrollmentDate.equals(student.getEnrollmentDate())) {
                    student.updateEnrollmentDate(newEnrollmentDate);
                }
                
                refreshTable();
                dialog.dispose();
                
                JOptionPane.showMessageDialog(this, 
                    "Estudiante modificado exitosamente", 
                    "Éxito", 
                    JOptionPane.INFORMATION_MESSAGE);
                    
            } catch (DateTimeParseException ex) {
                JOptionPane.showMessageDialog(dialog, 
                    "Formato de fecha inválido. Use YYYY-MM-DD", 
                    "Error de Formato", 
                    JOptionPane.ERROR_MESSAGE);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(dialog, 
                    "Error al modificar estudiante: " + ex.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        });
        buttonPanel.add(saveButton);
        
        JButton cancelButton = new JButton("Cancelar");
        cancelButton.addActionListener(e -> dialog.dispose());
        buttonPanel.add(cancelButton);
        
        dialog.add(buttonPanel, BorderLayout.SOUTH);
        
        dialog.setVisible(true);
    }
    
    private void deleteStudent() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Por favor selecciona un estudiante de la tabla", 
                "No hay selección", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Student student = students.get(selectedRow);
        int confirm = JOptionPane.showConfirmDialog(this, 
            "¿Estás seguro de eliminar al estudiante: " + student.getFullName() + "?", 
            "Confirmar Eliminación", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            student.delete();
            students.remove(selectedRow);
            refreshTable();
            
            JOptionPane.showMessageDialog(this, 
                "Estudiante eliminado exitosamente", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void searchStudents() {
        String searchTerm = searchField.getText().trim().toLowerCase();
        
        if (searchTerm.isEmpty()) {
            refreshTable();
            return;
        }
        
        tableModel.setRowCount(0);
        for (Student student : students) {
            if (student.getFullName().toLowerCase().contains(searchTerm) ||
                student.getEmail().toLowerCase().contains(searchTerm) ||
                student.getPersonalId().toLowerCase().contains(searchTerm)) {
                tableModel.addRow(new Object[]{
                    student.getId(), 
                    student.getFullName(), 
                    student.getEmail(), 
                    student.getPersonalId(),
                    student.getEnrollmentDate()
                });
            }
        }
    }
    
    private void refreshTable() {
        tableModel.setRowCount(0);
        for (Student student : students) {
            tableModel.addRow(new Object[]{
                student.getId(), 
                student.getFullName(), 
                student.getEmail(), 
                student.getPersonalId(),
                student.getEnrollmentDate()
            });
        }
    }
    
    public List<Student> getStudents() {
        return students;
    }
}

