package TinderTec_preregistro;
import javax.swing.*;

// Importación de las tres clases principales
import TinderTec_preregistro.Login;
import TinderTec_preregistro.Registro;
import TinderTec_preregistro.FormularioPrincipal;



/**
 * Clase Index - Punto de entrada principal de la aplicación TinderTec_preregistro
 * Configura el Look and Feel y lanza la ventana de Login
 * 
 *
 */
public class index {
    
    /**
     * 
     * el look and feel es para actualizar el tema mas moderno , es parte del framework de swing(una funcionalidad)
     */
    public static void main(String[] args) {
        // Configurar el Look and Feel del sistema para una mejor apariencia
        try {
            UIManager.setLookAndFeel(UIManager.getLookAndFeel());
        } catch (Exception e) {
            // Si hay error con el Look and Feel, usar el por defecto
            System.out.println("No se pudo configurar el Look and Feel del sistema: " + e.getMessage());
        }
        
        // Configurar la aplicación para que se ejecute en el Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
           
            public void run() {
                inicializarAplicacion();
            }
        });
    }
    
    /**
     * Inicializa la aplicación creando y mostrando la ventana principal (Login)
     */
    private static void inicializarAplicacion() {
        try {
            // Crear y mostrar la ventana de Login (pantalla inicial)
            Login loginWindow = new Login();
            loginWindow.setVisible(true);
            
            System.out.println("Ventana de Login creada y mostrada exitosamente.");
            
        } catch (Exception e) {
            // Manejo de errores durante la inicialización
            System.err.println("Error al inicializar la aplicación: " + e.getMessage());
            e.printStackTrace();
            
            // Mostrar mensaje de error al usuario
            JOptionPane.showMessageDialog(null, 
                "Error al iniciar la aplicación: " + e.getMessage(),
                "Error de Inicialización", 
                JOptionPane.ERROR_MESSAGE);
            
            // Terminar la aplicación si hay un error crítico
            System.exit(1);
        }
    }
    
    /**
     * Método auxiliar para mostrar información sobre la aplicación
     */
    public static void mostrarInformacion() {
        String info = "TinderTec - Preregistro v1.0\n\n" +
                     "Una aplicación de registro y formulario desarrollada en Java Swing\n\n" +
                     "Características:\n" +
                     "• Sistema de Login y Registro\n" +
                     "• Formulario completo de perfil de usuario\n" +
                     "• Interfaz gráfica con colores rosados y corales\n" +
                     "• Navegación dinámica entre ventanas\n\n" +
                     "Desarrollado por:\n" +
                     "Hernandez Gonzalez Miguel Angel, Reyes Calva Cesar David, Montalbo Castro Pedro Carlos, Reyes Alvarez Octavio Armando, Lopez Godinez Alejandro\r\n"
                     + "";
        
        JOptionPane.showMessageDialog(null, info, "Acerca de TinderTec", JOptionPane.INFORMATION_MESSAGE);
    }
}