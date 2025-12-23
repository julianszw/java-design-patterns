package ui.panels;

import domain.entity.Subject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Panel para gestionar materias (CRUD).
 */
public class SubjectPanel extends JPanel {
    
    private List<Subject> subjects = new ArrayList<>();
    private DefaultTableModel tableModel;
    private JTable table;
    
    private JTextField nameField;
    private JTextField codeField;
    private JSpinner creditsSpinner;
    private JTextField searchField;
    
    public SubjectPanel() {
        
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
        panel.setBorder(BorderFactory.createTitledBorder("Crear Materia"));
        
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
        
        // Campo código
        gbc.gridx = 2;
        gbc.weightx = 0;
        panel.add(new JLabel("Código:"), gbc);
        
        gbc.gridx = 3;
        gbc.weightx = 0.5;
        codeField = new JTextField(10);
        panel.add(codeField, gbc);
        
        // Spinner créditos
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        panel.add(new JLabel("Créditos:"), gbc);
        
        gbc.gridx = 1;
        gbc.weightx = 0.3;
        creditsSpinner = new JSpinner(new SpinnerNumberModel(3, 1, 10, 1));
        panel.add(creditsSpinner, gbc);
        
        // Botón crear
        gbc.gridx = 2;
        gbc.gridy = 0;
        gbc.gridheight = 2;
        gbc.weightx = 0;
        JButton createButton = new JButton("Crear Materia");
        createButton.setBackground(new Color(46, 125, 50));
        createButton.setForeground(Color.WHITE);
        createButton.addActionListener(e -> createSubject());
        panel.add(createButton, gbc);
        
        gbc.gridheight = 1;
        
        return panel;
    }
    
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Lista de Materias"));
        
        // Tabla
        String[] columnNames = {"ID", "Nombre", "Código", "Créditos"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getColumnModel().getColumn(0).setPreferredWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(250);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(80);
        
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Panel de búsqueda
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(new JLabel("Buscar:"));
        searchField = new JTextField(20);
        searchField.addActionListener(e -> searchSubjects());
        searchPanel.add(searchField);
        
        JButton searchButton = new JButton("Buscar");
        searchButton.addActionListener(e -> searchSubjects());
        searchPanel.add(searchButton);
        
        JButton showAllButton = new JButton("Mostrar Todos");
        showAllButton.addActionListener(e -> refreshTable());
        searchPanel.add(showAllButton);
        
        panel.add(searchPanel, BorderLayout.NORTH);
        
        return panel;
    }
    
    private JPanel createActionPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        
        JButton updateButton = new JButton("Actualizar Nombre");
        updateButton.setBackground(new Color(25, 118, 210));
        updateButton.setForeground(Color.WHITE);
        updateButton.addActionListener(e -> updateSubject());
        panel.add(updateButton);
        
        JButton deleteButton = new JButton("Eliminar Seleccionado");
        deleteButton.setBackground(new Color(211, 47, 47));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.addActionListener(e -> deleteSubject());
        panel.add(deleteButton);
        
        JButton refreshButton = new JButton("Refrescar");
        refreshButton.addActionListener(e -> refreshTable());
        panel.add(refreshButton);
        
        return panel;
    }
    
    private void createSubject() {
        String name = nameField.getText().trim();
        String code = codeField.getText().trim();
        int credits = (Integer) creditsSpinner.getValue();
        
        if (name.isEmpty() || code.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "El nombre y código no pueden estar vacíos", 
                "Error de Validación", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            Subject subject = new Subject(name, code, credits);
            
            subjects.add(subject);
            refreshTable();
            nameField.setText("");
            codeField.setText("");
            creditsSpinner.setValue(3);
            
            JOptionPane.showMessageDialog(this, 
                "Materia creada exitosamente", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, 
                "Error al crear materia: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateSubject() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Por favor selecciona una materia de la tabla", 
                "No hay selección", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Subject subject = subjects.get(selectedRow);
        String newName = JOptionPane.showInputDialog(this, 
            "Ingresa el nuevo nombre para la materia:", 
            subject.getName());
        
        if (newName != null && !newName.trim().isEmpty()) {
            try {
                subject.updateName(newName.trim());
                refreshTable();
                
                JOptionPane.showMessageDialog(this, 
                    "Materia actualizada exitosamente", 
                    "Éxito", 
                    JOptionPane.INFORMATION_MESSAGE);
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, 
                    "Error al actualizar materia: " + e.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void deleteSubject() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Por favor selecciona una materia de la tabla", 
                "No hay selección", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        Subject subject = subjects.get(selectedRow);
        int confirm = JOptionPane.showConfirmDialog(this, 
            "¿Estás seguro de eliminar la materia: " + subject.getName() + "?", 
            "Confirmar Eliminación", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            subject.delete();
            subjects.remove(selectedRow);
            refreshTable();
            
            JOptionPane.showMessageDialog(this, 
                "Materia eliminada exitosamente", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void searchSubjects() {
        String searchTerm = searchField.getText().trim().toLowerCase();
        
        if (searchTerm.isEmpty()) {
            refreshTable();
            return;
        }
        
        tableModel.setRowCount(0);
        for (Subject subject : subjects) {
            if (subject.getName().toLowerCase().contains(searchTerm) ||
                subject.getCode().toLowerCase().contains(searchTerm)) {
                tableModel.addRow(new Object[]{
                    subject.getId(), 
                    subject.getName(), 
                    subject.getCode(), 
                    subject.getCredits()
                });
            }
        }
    }
    
    private void refreshTable() {
        tableModel.setRowCount(0);
        for (Subject subject : subjects) {
            tableModel.addRow(new Object[]{
                subject.getId(), 
                subject.getName(), 
                subject.getCode(), 
                subject.getCredits()
            });
        }
    }
    
    public List<Subject> getSubjects() {
        return subjects;
    }
}

