package gui;

import controlador.Controlador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ratadp on 23/05/14.
 */
public class NuevoSocio extends Estilo {
    JDialog frame;
    Controlador controlador;
    JTextField tNom,tApellido,tDni;
    JCheckBox bMenor;
    JButton bGuardar, bVolver;
    DefaultListModel<String> lista;

    public NuevoSocio(JFrame frame, Controlador con, DefaultListModel<String> lista) {
        super();
        this.frame = new JDialog(frame,"Nuevo socio",true);
        this.controlador = con;
        this.lista = lista;
        ejecutar();
    }

    private void ejecutar() {
        JPanel trabajo = new JPanel(new BorderLayout());
        trabajo.setBackground(getColorFondo());
        trabajo.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        JPanel centro = new JPanel(new GridBagLayout());
        centro.setBackground(getColorFondo());
        centro.setBorder(BorderFactory.createTitledBorder("Nuevo Socio"));
        GridBagConstraints constraints = new GridBagConstraints();
        JLabel lNom = new JLabel("Nombre:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        centro.add(lNom,constraints);
        tNom = new JTextField(15);
        constraints.gridx = 1;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        tNom.setActionCommand("nombre");
        centro.add(tNom,constraints);
        JLabel lApellido = new JLabel("Apellido:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        centro.add(lApellido,constraints);
        tApellido = new JTextField(15);
        constraints.gridx = 1;
        constraints.gridy = 1;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        tApellido.setActionCommand("apellido");
        centro.add(tApellido,constraints);
        JLabel lDni = new JLabel("DNI:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        centro.add(lDni,constraints);
        tDni = new JTextField(15);
        constraints.gridx = 1;
        constraints.gridy = 2;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        tDni.setActionCommand("dni");
        centro.add(tDni,constraints);
        JLabel lMenor = new JLabel("Menor de edad:");
        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        centro.add(lMenor,constraints);
        bMenor = new JCheckBox();
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        bMenor.setActionCommand("menor");
        centro.add(bMenor,constraints);
        trabajo.add(centro, BorderLayout.CENTER);

        JPanel sur = new JPanel(new BorderLayout());
        sur.setBackground(getColorFondo());
        sur.setBorder(BorderFactory.createEmptyBorder(5,0,0,0));
        bGuardar = new JButton("Guardar");
        bGuardar.setActionCommand("guardar");
        sur.add(bGuardar,BorderLayout.WEST);
        bVolver = new JButton("Volver");
        bVolver.setActionCommand("volver");
        sur.add(bVolver, BorderLayout.EAST);
        trabajo.add(sur,BorderLayout.SOUTH);

        //LISTENER
        Listener listener = new Listener();
        bGuardar.addActionListener(listener);
        bVolver.addActionListener(listener);

        //Opciones ventana
        frame.setContentPane(trabajo);
        frame.setBackground(getColorFondo());
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.pack();
//        frame.setSize(300,300);
        frame.setVisible(true);
    }

    private class Listener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String action = e.getActionCommand();
            if (action.equals("guardar")) {
                controlador.nuevoSocio(tDni.getText(), tNom.getText(), tApellido.getText(), bMenor.isSelected());
                lista.addElement(tNom.getText() + " " + tApellido.getText());
                frame.dispose();
            } else if (action.equals("volver")) {
                frame.dispose();
            }
        }
    }

    public static void main(String[] args) {
        new NuevoSocio(new JFrame(),new Controlador(), new DefaultListModel<>());
    }
}
