package vista.gui;

import controlador.Controlador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ratadp on 3/06/14.
 */
public class BorrarSocio extends Estilo {
    private JDialog dialog;
    private JTextField tDni;
    private String dni;
    private Controlador controlador;

    public BorrarSocio(JFrame frame, Controlador controlador) {
        this.dialog = new JDialog(frame,"Borrar socio");
        this.controlador = controlador;
        ejecutar();
    }

    private void ejecutar() {
        JPanel trabajo = new JPanel(new BorderLayout());
        trabajo.setBackground(getColorFondo());
        trabajo.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        JPanel center = new JPanel(new FlowLayout());
        JLabel lDni = new JLabel("DNI:");
        center.add(lDni);
        tDni = new JTextField(20);
        tDni.setActionCommand("escritoDNI");
        center.add(tDni);
        trabajo.add(center,BorderLayout.CENTER);

        JButton bSalir = new JButton("Salir");
        bSalir.setActionCommand("salir");
        trabajo.add(bSalir,BorderLayout.SOUTH);

        // LISTENER
        ListenerAccion listener = new ListenerAccion();
        tDni.addActionListener(listener);
        bSalir.addActionListener(listener);


        dialog.setContentPane(trabajo);
        dialog.pack();
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }

    private class ListenerAccion implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "salir":
                    dialog.dispose();
                    break;
                case "escritoDNI":
                    dni = tDni.getText();
                    controlador.borrarSocio();
                    dialog.dispose();
            }
        }
    }

    public String getDNI() {
        return dni;
    }
}
