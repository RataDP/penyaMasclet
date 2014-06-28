package vista.gui;

import javax.swing.*;

/**
 * Created by ratadp on 07/06/14.
 */
public class About extends Estilo {
    private JFrame frame;

    public About() {
        super();
        this.frame = new JFrame("Sobre Mascletet");
    }

    public void ejecutar() {
        JPanel trabajo = new JPanel();
        trabajo.setBackground(getColorFondo());
        trabajo.setLayout(new BoxLayout(trabajo,BoxLayout.X_AXIS));
        trabajo.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));


        String path = "/resources/underconstruction.png";
        JLabel lLogo = new JLabel(new ImageIcon(getClass().getResource(path)));
        trabajo.add(lLogo);

        JPanel izq = new JPanel();
        izq.setLayout(new BoxLayout(izq,BoxLayout.Y_AXIS));
        izq.setBackground(getColorFondo());

        JLabel nombreApp = new JLabel("<html><center><font face='courier'><h1>Mascletet</h1></font></center></html>");
        nombreApp.setFont(getSansS());
        izq.add(nombreApp);

        JLabel titulo = new JLabel("<html><b>Versión: 0.02</b><br>" +
                "Desarrollado por <i>Borja Blasco García</i> (RataDP)<br>" +
                "Programa para la gestión de socios de la Peña El Masclet y sus pagos.<br><br>" +
                "La aplicación aún esta siendo desarrollada para mejorar ciertos aspectos y añadir funcionalidades.</html>");
        titulo.setFont(getMono());
        izq.add(titulo);
        trabajo.add(izq);

        // Opciones de ventana
        frame.setContentPane(trabajo);
//        frame.pack();
        frame.setSize(675, 250);
        frame.setLocation(375,175);
        frame.setResizable(false);
        frame.setIconImage(getIcon());
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new About().ejecutar();
    }
}
