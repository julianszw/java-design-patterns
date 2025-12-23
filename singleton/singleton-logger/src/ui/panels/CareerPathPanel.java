package ui.panels;

import domain.entity.CareerPath;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Panel para gestionar carreras (CRUD).
 */
public class CareerPathPanel extends JPanel {
    
    private List<CareerPath> careerPaths = new ArrayList<>();
    private DefaultTableModel tableModel;
    private JTable table;
    
    private JTextField nameField;
    private JTextField codeField;
    private JTextField searchField;
    
    public CareerPathPanel() {
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
        panel.setBorder(BorderFactory.createTitledBorder("Crear Carrera"));
        
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
        gbc.weightx = 0.3;
        codeField = new JTextField(10);
        panel.add(codeField, gbc);
        
        // Botón crear
        gbc.gridx = 4;
        gbc.weightx = 0;
        JButton createButton = new JButton("Crear Carrera");
        createButton.setBackground(new Color(46, 125, 50));
        createButton.setForeground(Color.WHITE);
        createButton.addActionListener(e -> createCareerPath());
        panel.add(createButton, gbc);
        
        return panel;
    }
    
    private JPanel createTablePanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Lista de Carreras"));
        
        // Tabla
        String[] columnNames = {"ID", "Nombre", "Código", "# Materias"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        
        table = new JTable(tableModel);
        table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.getColumnModel().getColumn(0).setPreferredWidth(80);
        table.getColumnModel().getColumn(1).setPreferredWidth(300);
        table.getColumnModel().getColumn(2).setPreferredWidth(100);
        table.getColumnModel().getColumn(3).setPreferredWidth(120);
        
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);
        
        // Panel de búsqueda
        JPanel searchPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        searchPanel.add(new JLabel("Buscar:"));
        searchField = new JTextField(20);
        searchField.addActionListener(e -> searchCareerPaths());
        searchPanel.add(searchField);
        
        JButton searchButton = new JButton("Buscar");
        searchButton.addActionListener(e -> searchCareerPaths());
        searchPanel.add(searchButton);
        
        JButton showAllButton = new JButton("Mostrar Todos");
        showAllButton.addActionListener(e -> refreshTable());
        searchPanel.add(showAllButton);
        
        panel.add(searchPanel, BorderLayout.NORTH);
        
        return panel;
    }
    
    private JPanel createActionPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        
        JButton updateButton = new JButton("Actualizar Seleccionado");
        updateButton.setBackground(new Color(25, 118, 210));
        updateButton.setForeground(Color.WHITE);
        updateButton.addActionListener(e -> updateCareerPath());
        panel.add(updateButton);
        
        JButton deleteButton = new JButton("Eliminar Seleccionado");
        deleteButton.setBackground(new Color(211, 47, 47));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.addActionListener(e -> deleteCareerPath());
        panel.add(deleteButton);
        
        JButton refreshButton = new JButton("Refrescar");
        refreshButton.addActionListener(e -> refreshTable());
        panel.add(refreshButton);
        
        return panel;
    }
    
    private void createCareerPath() {
        String name = nameField.getText().trim();
        String code = codeField.getText().trim();
        
        if (name.isEmpty() || code.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "El nombre y código no pueden estar vacíos", 
                "Error de Validación", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        try {
            CareerPath careerPath = new CareerPath(name, code);
            careerPaths.add(careerPath);
            refreshTable();
            nameField.setText("");
            codeField.setText("");
            
            JOptionPane.showMessageDialog(this, 
                "Carrera creada exitosamente", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
        } catch (IllegalArgumentException e) {
            JOptionPane.showMessageDialog(this, 
                "Error al crear carrera: " + e.getMessage(), 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void updateCareerPath() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Por favor selecciona una carrera de la tabla", 
                "No hay selección", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        CareerPath careerPath = careerPaths.get(selectedRow);
        String newName = JOptionPane.showInputDialog(this, 
            "Ingresa el nuevo nombre para la carrera:", 
            careerPath.getName());
        
        if (newName != null && !newName.trim().isEmpty()) {
            try {
                careerPath.updateName(newName.trim());
                refreshTable();
                
                JOptionPane.showMessageDialog(this, 
                    "Carrera actualizada exitosamente", 
                    "Éxito", 
                    JOptionPane.INFORMATION_MESSAGE);
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(this, 
                    "Error al actualizar carrera: " + e.getMessage(), 
                    "Error", 
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    
    private void deleteCareerPath() {
        int selectedRow = table.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, 
                "Por favor selecciona una carrera de la tabla", 
                "No hay selección", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        
        CareerPath careerPath = careerPaths.get(selectedRow);
        int confirm = JOptionPane.showConfirmDialog(this, 
            "¿Estás seguro de eliminar la carrera: " + careerPath.getName() + "?", 
            "Confirmar Eliminación", 
            JOptionPane.YES_NO_OPTION);
        
        if (confirm == JOptionPane.YES_OPTION) {
            careerPath.delete();
            careerPaths.remove(selectedRow);
            refreshTable();
            
            JOptionPane.showMessageDialog(this, 
                "Carrera eliminada exitosamente", 
                "Éxito", 
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    private void searchCareerPaths() {
        String searchTerm = searchField.getText().trim().toLowerCase();
        
        if (searchTerm.isEmpty()) {
            refreshTable();
            return;
        }
        
        tableModel.setRowCount(0);
        for (CareerPath cp : careerPaths) {
            if (cp.getName().toLowerCase().contains(searchTerm) ||
                cp.getCode().toLowerCase().contains(searchTerm)) {
                tableModel.addRow(new Object[]{
                    cp.getId(), 
                    cp.getName(), 
                    cp.getCode(), 
                    cp.getSubjects().size()
                });
            }
        }
    }
    
    private void refreshTable() {
        tableModel.setRowCount(0);
        for (CareerPath cp : careerPaths) {
            tableModel.addRow(new Object[]{
                cp.getId(), 
                cp.getName(), 
                cp.getCode(), 
                cp.getSubjects().size()
            });
        }
    }
    
    public List<CareerPath> getCareerPaths() {
        return careerPaths;
    }
}

