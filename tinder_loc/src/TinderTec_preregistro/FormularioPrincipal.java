package TinderTec_preregistro;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

/**
 * Clase FormularioPrincipal - Formulario donde se capturan los datos del usuario
 * Contiene todos los campos solicitados para el perfil
 */
public class FormularioPrincipal extends JFrame {
    
    // Componentes del formulario
    private JTextField nombreField;
    private JTextField nacionalidadField;
    private JComboBox<String> orientacionCombo;
    private JTextArea descripcionArea;
    private JSpinner edadSpinner;
    private JTextField salarioField;
    private JTextField pesoField;
    private JTextField carreraField;
    private JTextField alturaField;
    private JCheckBox rockCheck, popCheck, jazzCheck, electronicaCheck;
    private JTextField ubicacionField;
    private JComboBox<String> estadoCivilCombo;
    private JComboBox<String> religionCombo;
    private JButton guardarButton;
    private JButton cerrarSesionButton;
    private JTextArea resumenArea;
    
    public FormularioPrincipal() {
        initializeComponents();
        setupLayout();
        setupEvents();
        actualizarResumen(); // Inicializar resumen
    }
    
    /**
     * Inicializa todos los componentes de la interfaz
     */
    private void initializeComponents() {
        setTitle("TinderTec - Perfil de Usuario");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 800);
        setLocationRelativeTo(null);
        
        // Configurar colores rosados y corales
        getContentPane().setBackground(new Color(255, 228, 225)); // Misty rose
        
        // Inicializar nuevos componentes
        nombreField = new JTextField(15);
        nacionalidadField = new JTextField(15);
        
        String[] orientaciones = {"Seleccionar...", "Heterosexual", "Homosexual", "Bisexual", "Pansexual", "Asexual", "Otro"};
        orientacionCombo = new JComboBox<>(orientaciones);
        
        descripcionArea = new JTextArea(3, 15);
        descripcionArea.setLineWrap(true);
        descripcionArea.setWrapStyleWord(true);
        descripcionArea.setBorder(BorderFactory.createLoweredBevelBorder());
        
        // Componentes existentes
        edadSpinner = new JSpinner(new SpinnerNumberModel(18, 18, 100, 1));
        salarioField = new JTextField(15);
        pesoField = new JTextField(15);
        carreraField = new JTextField(15);
        alturaField = new JTextField(15);
        
        // Checkboxes para preferencias musicales
        rockCheck = new JCheckBox("Rock");
        popCheck = new JCheckBox("Pop");
        jazzCheck = new JCheckBox("Jazz");
        electronicaCheck = new JCheckBox("Electrónica");
        
        ubicacionField = new JTextField(15);
        
        // ComboBoxes
        String[] estadosCiviles = {"Seleccionar...", "Soltero/a", "Casado/a", "Divorciado/a", "Viudo/a"};
        estadoCivilCombo = new JComboBox<>(estadosCiviles);
        
        String[] religiones = {"Seleccionar...", "Católica", "Protestante", "Judía", "Musulmana", "Budista", "Otra", "Ninguna"};
        religionCombo = new JComboBox<>(religiones);
        
        // Botones
        guardarButton = new JButton("Guardar Perfil");
        guardarButton.setBackground(new Color(255, 105, 97)); // Light coral
        guardarButton.setForeground(Color.WHITE);
        
        cerrarSesionButton = new JButton("Cerrar Sesión");
        cerrarSesionButton.setBackground(new Color(205, 92, 92)); // Indian red
        cerrarSesionButton.setForeground(Color.WHITE);
        
        // Área de resumen
        resumenArea = new JTextArea(10, 40);
        resumenArea.setEditable(false);
        resumenArea.setBackground(new Color(255, 240, 245)); // Lavender blush
        resumenArea.setBorder(BorderFactory.createTitledBorder("Resumen del Perfil"));
    }
    
    /**
     * Configura el layout de la ventana
     */
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Panel principal con scroll
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(255, 228, 225));
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Título
        JLabel titleLabel = new JLabel("Completa tu Perfil");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(new Color(139, 69, 19));
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(15, 0, 20, 0);
        mainPanel.add(titleLabel, gbc);
        
        gbc.gridwidth = 1;
        gbc.insets = new Insets(5, 10, 5, 5);
        
        // Nuevos campos en las primeras filas
        // Fila 1: Nombre y Nacionalidad
        addLabelAndComponent(mainPanel, "Nombre*:", nombreField, gbc, 0, 1);
        addLabelAndComponent(mainPanel, "Nacionalidad*:", nacionalidadField, gbc, 2, 1);
        
        // Fila 2: Orientación Sexual y Edad
        addLabelAndComponent(mainPanel, "Orientación Sexual*:", orientacionCombo, gbc, 0, 2);
        addLabelAndComponent(mainPanel, "Límite de edad*:", edadSpinner, gbc, 2, 2);
        
        // Fila 3: Salario y Peso
        addLabelAndComponent(mainPanel, "Salario*:", salarioField, gbc, 0, 3);
        addLabelAndComponent(mainPanel, "Peso (kg)*:", pesoField, gbc, 2, 3);
        
        // Fila 4: Carrera y Altura
        addLabelAndComponent(mainPanel, "Carrera*:", carreraField, gbc, 0, 4);
        addLabelAndComponent(mainPanel, "Altura (cm)*:", alturaField, gbc, 2, 4);
        
        // Fila 5: Estado Civil y Religión
        addLabelAndComponent(mainPanel, "Estado Civil*:", estadoCivilCombo, gbc, 0, 5);
        addLabelAndComponent(mainPanel, "Religión*:", religionCombo, gbc, 2, 5);
        
        // Fila 6: Ubicación
        addLabelAndComponent(mainPanel, "Ubicación*:", ubicacionField, gbc, 0, 6);
        
        // Fila 7: Descripción
        gbc.gridx = 0; gbc.gridy = 7;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        JLabel descripcionLabel = new JLabel("Descripción*:");
        descripcionLabel.setForeground(new Color(139, 69, 19));
        mainPanel.add(descripcionLabel, gbc);
        
        gbc.gridx = 1; gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.BOTH;
        JScrollPane descripcionScroll = new JScrollPane(descripcionArea);
        descripcionScroll.setPreferredSize(new Dimension(250, 70));
        mainPanel.add(descripcionScroll, gbc);
        
        // Preferencias musicales
        gbc.gridx = 0; gbc.gridy = 8;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.fill = GridBagConstraints.NONE;
        JLabel musicLabel = new JLabel("Preferencias musicales*:");
        musicLabel.setForeground(new Color(139, 69, 19));
        mainPanel.add(musicLabel, gbc);
        
        JPanel musicPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        musicPanel.setBackground(new Color(255, 228, 225));
        musicPanel.add(rockCheck);
        musicPanel.add(popCheck);
        musicPanel.add(jazzCheck);
        musicPanel.add(electronicaCheck);
        
        gbc.gridx = 1; gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(musicPanel, gbc);
        
        // Nota de campos obligatorios
        gbc.gridx = 0; gbc.gridy = 9;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(10, 10, 10, 10);
        JLabel notaLabel = new JLabel("* Campos obligatorios");
        notaLabel.setForeground(Color.RED);
        notaLabel.setFont(new Font("Arial", Font.ITALIC, 11));
        mainPanel.add(notaLabel, gbc);
        
        // Botones
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBackground(new Color(255, 228, 225));
        buttonPanel.add(guardarButton);
        buttonPanel.add(cerrarSesionButton);
        
        gbc.gridx = 0; gbc.gridy = 10;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(20, 0, 15, 0);
        mainPanel.add(buttonPanel, gbc);
        
        // Scroll para el panel principal
        JScrollPane scrollPane = new JScrollPane(mainPanel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, BorderLayout.CENTER);
        
        // Área de resumen en la parte inferior
        add(new JScrollPane(resumenArea), BorderLayout.SOUTH);
    }
    
    /**
     * Método auxiliar para agregar label y componente al layout
     */
    private void addLabelAndComponent(JPanel panel, String labelText, JComponent component, 
                                    GridBagConstraints gbc, int x, int y) {
        gbc.gridx = x; gbc.gridy = y;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE;
        
        JLabel label = new JLabel(labelText);
        label.setForeground(new Color(139, 69, 19));
        panel.add(label, gbc);
        
        gbc.gridx = x + 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(component, gbc);
    }
    
    /**
     * Configura los eventos de los componentes
     */
    private void setupEvents() {
        // Evento del botón guardar con validación
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (validarFormulario()) {
                    actualizarResumen();
                    JOptionPane.showMessageDialog(FormularioPrincipal.this, 
                        "¡Perfil guardado exitosamente!",
                        "Guardado", 
                        JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(FormularioPrincipal.this,
                        "Por favor, complete todos los campos obligatorios.",
                        "Error - Campos incompletos",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        
        // Evento del botón cerrar sesión
        cerrarSesionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int option = JOptionPane.showConfirmDialog(FormularioPrincipal.this,
                    "¿Estás seguro de que deseas cerrar sesión?",
                    "Cerrar Sesión",
                    JOptionPane.YES_NO_OPTION);
                
                if (option == JOptionPane.YES_OPTION) {
                    new Login().setVisible(true);
                    dispose();
                }
            }
        });
        
        // Document Listeners para campos de texto (actualización en tiempo real)
        DocumentListener docListener = new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) { actualizarResumen(); }
            @Override
            public void removeUpdate(DocumentEvent e) { actualizarResumen(); }
            @Override
            public void changedUpdate(DocumentEvent e) { actualizarResumen(); }
        };
        
        nombreField.getDocument().addDocumentListener(docListener);
        nacionalidadField.getDocument().addDocumentListener(docListener);
        salarioField.getDocument().addDocumentListener(docListener);
        pesoField.getDocument().addDocumentListener(docListener);
        carreraField.getDocument().addDocumentListener(docListener);
        alturaField.getDocument().addDocumentListener(docListener);
        ubicacionField.getDocument().addDocumentListener(docListener);
        descripcionArea.getDocument().addDocumentListener(docListener);
        
        // Change Listener para el spinner
        edadSpinner.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                actualizarResumen();
            }
        });
        
        // Eventos para checkboxes
        ItemListener checkboxListener = new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                actualizarResumen();
            }
        };
        
        rockCheck.addItemListener(checkboxListener);
        popCheck.addItemListener(checkboxListener);
        jazzCheck.addItemListener(checkboxListener);
        electronicaCheck.addItemListener(checkboxListener);
        
        // Listener para los combobox
        ActionListener comboListener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actualizarResumen();
            }
        };
        
        orientacionCombo.addActionListener(comboListener);
        estadoCivilCombo.addActionListener(comboListener);
        religionCombo.addActionListener(comboListener);
    }
    
    /**
     * Valida que todos los campos obligatorios estén completos
     */
    private boolean validarFormulario() {
        // Validar campos de texto
        if (nombreField.getText().trim().isEmpty()) return false;
        if (nacionalidadField.getText().trim().isEmpty()) return false;
        if (salarioField.getText().trim().isEmpty()) return false;
        if (pesoField.getText().trim().isEmpty()) return false;
        if (carreraField.getText().trim().isEmpty()) return false;
        if (alturaField.getText().trim().isEmpty()) return false;
        if (ubicacionField.getText().trim().isEmpty()) return false;
        if (descripcionArea.getText().trim().isEmpty()) return false;
        
        // Validar combobox
        if (orientacionCombo.getSelectedIndex() <= 0) return false;
        if (estadoCivilCombo.getSelectedIndex() <= 0) return false;
        if (religionCombo.getSelectedIndex() <= 0) return false;
        
        // Validar que al menos una preferencia musical esté seleccionada
        if (!rockCheck.isSelected() && !popCheck.isSelected() && 
            !jazzCheck.isSelected() && !electronicaCheck.isSelected()) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Actualiza el área de resumen con los datos actuales
     */
    private void actualizarResumen() {
        StringBuilder resumen = new StringBuilder("RESUMEN DEL PERFIL:\n\n");
        
        if (!nombreField.getText().trim().isEmpty()) {
            resumen.append("Nombre: ").append(nombreField.getText().trim()).append("\n");
        }
        
        if (!nacionalidadField.getText().trim().isEmpty()) {
            resumen.append("Nacionalidad: ").append(nacionalidadField.getText().trim()).append("\n");
        }
        
        if (orientacionCombo.getSelectedIndex() > 0) {
            resumen.append("Orientación Sexual: ").append(orientacionCombo.getSelectedItem()).append("\n");
        }
        
        resumen.append("Edad límite: ").append(edadSpinner.getValue()).append(" años\n");
        
        if (!salarioField.getText().trim().isEmpty()) {
            resumen.append("Salario: $").append(salarioField.getText().trim()).append("\n");
        }
        
        if (!pesoField.getText().trim().isEmpty()) {
            resumen.append("Peso: ").append(pesoField.getText().trim()).append(" kg\n");
        }
        
        if (!carreraField.getText().trim().isEmpty()) {
            resumen.append("Carrera: ").append(carreraField.getText().trim()).append("\n");
        }
        
        if (!alturaField.getText().trim().isEmpty()) {
            resumen.append("Altura: ").append(alturaField.getText().trim()).append(" cm\n");
        }
        
        if (!ubicacionField.getText().trim().isEmpty()) {
            resumen.append("Ubicación: ").append(ubicacionField.getText().trim()).append("\n");
        }
        
        if (estadoCivilCombo.getSelectedIndex() > 0) {
            resumen.append("Estado Civil: ").append(estadoCivilCombo.getSelectedItem()).append("\n");
        }
        
        if (religionCombo.getSelectedIndex() > 0) {
            resumen.append("Religión: ").append(religionCombo.getSelectedItem()).append("\n");
        }
        
        if (!descripcionArea.getText().trim().isEmpty()) {
            resumen.append("Descripción: ").append(descripcionArea.getText().trim()).append("\n");
        }
        
        // Preferencias musicales
        StringBuilder musica = new StringBuilder();
        if (rockCheck.isSelected()) musica.append("Rock, ");
        if (popCheck.isSelected()) musica.append("Pop, ");
        if (jazzCheck.isSelected()) musica.append("Jazz, ");
        if (electronicaCheck.isSelected()) musica.append("Electrónica, ");
        
        if (musica.length() > 0) {
            musica.setLength(musica.length() - 2); // Quitar última coma
            resumen.append("Preferencias musicales: ").append(musica).append("\n");
        }
        
        resumenArea.setText(resumen.toString());
    }
}