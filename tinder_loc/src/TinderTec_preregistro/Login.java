package TinderTec_preregistro;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase Login - Pantalla principal de inicio de sesión
 * Permite al usuario iniciar sesión o ir al registro
 */
public class Login extends JFrame {
    
    private JTextField usuarioField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton registroButton;
    
    public Login() {
        initializeComponents();
        setupLayout();
        setupEvents();
    }
    
    /**
     * Inicializa todos los componentes de la interfaz
     */
    private void initializeComponents() {
        setTitle("TinderTec - Iniciar Sesión");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 350);
        setLocationRelativeTo(null);
        
        // Configurar colores rosados y corales
        getContentPane().setBackground(new Color(255, 182, 193)); // Light pink
        
        usuarioField = new JTextField(15);
        passwordField = new JPasswordField(15);
        
        loginButton = new JButton("Iniciar Sesión");
        loginButton.setBackground(new Color(255, 127, 80)); // Coral
        loginButton.setForeground(Color.WHITE);
        
        registroButton = new JButton("¿No tienes cuenta? Regístrate");
        registroButton.setBackground(new Color(240, 128, 128)); // Light coral
        registroButton.setForeground(Color.WHITE);
    }
    
    /**
     * Configura el layout de la ventana
     */
    private void setupLayout() {
        setLayout(new BorderLayout());
        
        // Panel principal
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(new Color(255, 182, 193));
        GridBagConstraints gbc = new GridBagConstraints();
        
        // Título
        JLabel titleLabel = new JLabel("TinderTec");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(new Color(139, 69, 19)); // Saddle brown
        gbc.gridx = 0; gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 0, 30, 0);
        mainPanel.add(titleLabel, gbc);
        
        // Usuario
        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 1;
        gbc.insets = new Insets(5, 10, 5, 5);
        JLabel userLabel = new JLabel("Usuario:");
        userLabel.setForeground(new Color(139, 69, 19));
        mainPanel.add(userLabel, gbc);
        
        gbc.gridx = 1;
        mainPanel.add(usuarioField, gbc);
        
        // Contraseña
        gbc.gridx = 0; gbc.gridy = 2;
        JLabel passLabel = new JLabel("Contraseña:");
        passLabel.setForeground(new Color(139, 69, 19));
        mainPanel.add(passLabel, gbc);
        
        gbc.gridx = 1;
        mainPanel.add(passwordField, gbc);
        
        // Botones
        gbc.gridx = 0; gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(20, 0, 10, 0);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        mainPanel.add(loginButton, gbc);
        
        gbc.gridy = 4;
        gbc.insets = new Insets(5, 0, 20, 0);
        mainPanel.add(registroButton, gbc);
        
        add(mainPanel, BorderLayout.CENTER);
        
        // Panel de créditos en la parte inferior
        JPanel creditPanel = createCreditPanel();
        add(creditPanel, BorderLayout.SOUTH);
    }
    
    /**
     * Crea el panel de créditos con animación de scroll horizontal
     */
    private JPanel createCreditPanel() {
        JPanel creditPanel = new JPanel();
        creditPanel.setLayout(new BorderLayout());
        creditPanel.setBackground(new Color(255, 160, 122)); // Light salmon
        creditPanel.setPreferredSize(new Dimension(400, 30));
        
        // Texto largo de créditos
        String creditText = "Creado por Hernandez Gonzalez Miguel Angel, Reyes Calva Cesar David, Montalbo Castro Pedro Carlos, Reyes Alvarez Octavio Armando, Lopez Godinez Alejandro ";
        
        JLabel creditLabel = new JLabel(creditText);
        creditLabel.setFont(new Font("Arial", Font.ITALIC, 11));
        creditLabel.setForeground(new Color(139, 69, 19));
        
        creditPanel.add(creditLabel, BorderLayout.CENTER);
        
        // Iniciar la animación de scroll
        startScrollAnimation(creditLabel, creditPanel);
        
        return creditPanel;
    }
    
    /**
     * Inicia la animación de scroll horizontal para los créditos
     */
    private void startScrollAnimation(JLabel label, JPanel panel) {
        Timer scrollTimer = new Timer(50, new ActionListener() {
            private int xPosition = panel.getWidth(); // Empezar desde la derecha
            
       
            public void actionPerformed(ActionEvent e) {
                // Calcular el ancho del texto
                FontMetrics fm = label.getFontMetrics(label.getFont());
                int textWidth = fm.stringWidth(label.getText());
                
                // Mover el texto hacia la izquierda
                xPosition -= 2;
                
                // Si el texto salió completamente por la izquierda, reiniciar desde la derecha
                if (xPosition < -textWidth) {
                    xPosition = panel.getWidth();
                }
                
                // Actualizar la posición del label
                label.setBounds(xPosition, 5, textWidth + 20, 20);
                panel.repaint();
            }
        });
        
        // Configurar el panel para layout absoluto
        panel.setLayout(null);
        
        // Iniciar el timer
        scrollTimer.start();
    }
    
    /**
     * Configura los eventos de los botones
     */
    private void setupEvents() {
        // Evento del botón login - va al formulario principal
        loginButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                // Mostrar mensaje de bienvenida
                String usuario = usuarioField.getText().trim();
                if (usuario.isEmpty()) {
                    usuario = "Usuario";
                }
                
                JOptionPane.showMessageDialog(Login.this, 
                    "¡Bienvenido " + usuario + "! Redirigiendo al formulario...",
                    "Acceso Exitoso", 
                    JOptionPane.INFORMATION_MESSAGE);
                
                // Abrir formulario principal y cerrar login
                new FormularioPrincipal().setVisible(true);
                dispose();
            }
        });
        
        // Evento del botón registro - va a la pantalla de registro
        registroButton.addActionListener(new ActionListener() {
           
            public void actionPerformed(ActionEvent e) {
                new Registro().setVisible(true);
                dispose();
            }
        });
        
        // Evento Enter en el campo de contraseña
        passwordField.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e) {
                loginButton.doClick(); // Simula click en login
            }
        });
    }
}