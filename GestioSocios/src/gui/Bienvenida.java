package gui;

import javax.swing.*;

/**
 * Created by ratadp on 22/05/14.
 */
public class Bienvenida extends Estilo {
    private JFrame frame;

    public Bienvenida() {
        super();
        this.frame = new JFrame("Bienvenido");

        ejecutar();
    }

    private void ejecutar() {
        JPanel trabajo = new JPanel();
        trabajo.setBackground(getColorFondo());
        trabajo.setLayout(new BoxLayout(trabajo,BoxLayout.X_AXIS));
        trabajo.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));


        String path = "/resources/logo.png";
        JLabel lLogo = new JLabel(new ImageIcon(getClass().getResource(path)));
        trabajo.add(lLogo);

        JPanel izq = new JPanel();
        izq.setLayout(new BoxLayout(izq,BoxLayout.Y_AXIS));
        izq.setBackground(getColorFondo());

        JLabel welcome = new JLabel("<html><font face='courier'><h1>¡Bienvenido!</h1></font></html>");
        welcome.setFont(getSansS());
        izq.add(welcome);

        JLabel titulo = new JLabel("<html><h2>Gestión de la peña</h2></html>");
        titulo.setFont(getMono());
        izq.add(titulo);
        JLabel mensaje = new JLabel("<html>Aplicación para gestionar los socios de la peña El Masclet.</html>");
        mensaje.setFont(getMono());
        izq.add(mensaje);
        trabajo.add(izq);

        // Opciones de ventana
        frame.setContentPane(trabajo);
//        frame.pack();
        frame.setSize(600, 250);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void cerrarVentana() {
        frame.dispose();
    }

    public static void main(String[] args) {
        new Bienvenida();
    }
}
