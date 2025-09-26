package TinderTec_preregistro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase Registro - Pantalla de registro de nuevos usuarios
 * Permite registrarse y regresar al login
 */
public class Registro extends JFrame {
    
    private JTextField usuarioField;
    private JTextField emailField;
    private JPasswordField passwordField;
    private JPasswordField confirmPasswordField;
    private JButton registrarButton;
    private JButton volverLoginButton;
    
    public Registro() {
        initializeComponents();
        setupLayout();
        setupEvents();
    }
    
    /**
     * Inicializa todos los componentes de la interfaz
     */
    private void initializeComponents() {
        setTitle("TinderTec - Registro");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(450, 400);
        setLocationRelativeTo(null);
        
        // Configurar colores rosados y corales
        getContentPane().setBackground(new Color(255, 192, 203)); // Pink
        
        usuarioField = new JTextField(15);
        emailField = new JTextField(15);
        passwordField = new JPasswordField(15);
        confirmPasswordField = new JPasswordField(15);
        
        registrarButton = new JButton("Registrarse");
        registrarButton.setBackground(new Color(255, 99, 71)); // Tomato
        registrarButton.setForeground(Color.WHITE);
        
        volverLoginButton = new JButton("¿Ya tienes cuenta? Iniciar Sesión");
        volverLoginButton.setBackground(new Color(250, 128, 114)); // Salmon
        volverLoginButton.setForeground(Color.WHITE);
    }
    
    /**
     * Configura el layout de la ventana
     */
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Panel principal
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(255, 192, 203));
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Título
        JLabel titleLabel = new JLabel("Crear Cuenta en TinderTec");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setForeground(new Color(139, 69, 19));
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 0, 30, 0);
        mainPanel.add(titleLabel, gbc);
        
        // Usuario
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.insets = new Insets(5, 10, 5, 5);
        gbc.anchor = GridBagConstraints.EAST;
        JLabel userLabel = new JLabel("Nombre de usuario:");
        userLabel.setForeground(new Color(139, 69, 19));
        mainPanel.add(userLabel, gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(usuarioField, gbc);
        
        // Email
        gbc.gridx = 0; gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setForeground(new Color(139, 69, 19));
        mainPanel.add(emailLabel, gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(emailField, gbc);
        
        // Contraseña
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel passLabel = new JLabel("Contraseña:");
        passLabel.setForeground(new Color(139, 69, 19));
        mainPanel.add(passLabel, gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(passwordField, gbc);
        
        // Confirmar contraseña
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        JLabel confirmLabel = new JLabel("Confirmar contraseña:");
        confirmLabel.setForeground(new Color(139, 69, 19));
        mainPanel.add(confirmLabel, gbc);
        
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        mainPanel.add(confirmPasswordField, gbc);
        
        // Botones
        gbc.gridx = 0; gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(25, 0, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(registrarButton, gbc);
        
        gbc.gridy = 6;
        gbc.insets = new Insets(5, 0, 20, 0);
        mainPanel.add(volverLoginButton, gbc);
        
        add(mainPanel, BorderLayout.CENTER);
    }
    
    /**
     * Configura los eventos de los botones
     */
    private void setupEvents() {
        // Evento del botón registrar
        registrarButton.addActionListener(new ActionListener() {
           
            public void actionPerformed(ActionEvent e) {
                if (validarCampos()) {
                    JOptionPane.showMessageDialog(Registro.this, 
                        "¡Registro exitoso! Ahora puedes iniciar sesión.",
                        "Registro Completado", 
                        JOptionPane.INFORMATION_MESSAGE);
                    
                    // Volver al login
                    new Login().setVisible(true);
                    dispose();
                }
            }
        });
        
        // Evento del botón volver al login
        volverLoginButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                new Login().setVisible(true);
                dispose();
            }
        });
        
        // Evento Enter en el último campo
        confirmPasswordField.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                registrarButton.doClick();
            }
        });
    }
    
    /**
     * Valida que todos los campos estén llenos y las contraseñas coincidan
     */
    private boolean validarCampos() {
        String usuario = usuarioField.getText().trim();
        String email = emailField.getText().trim();
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        
        if (usuario.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Por favor, completa todos los campos.",
                "Campos Incompletos", 
                JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        if (!password.equals(confirmPassword)) {
            JOptionPane.showMessageDialog(this, 
                "Las contraseñas no coinciden.",
                "Error de Contraseña", 
                JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        return true;
    }
}