package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ratadp on 22/05/14.
 */
public class Principal extends Estilo {
    private JFrame frame;

    public Principal(){
        super();
        frame = new JFrame("App de la Peña El Masclet");
        ejecutar();
    }

    private void ejecutar() {
        JPanel trabajo = new JPanel();
        trabajo.setLayout(new BoxLayout(trabajo,BoxLayout.X_AXIS));
        trabajo.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        trabajo.setBackground(getColorFondo());

        JPanel der = new JPanel();
        der.setBackground(getColorFondo());
        der.setBorder(BorderFactory.createTitledBorder("Lista de socios"));
//        JList<String> listaNombre = new JList<String>(controlador.getListaNombre());
        String[] data = cogerNombres();
        JList<String> listaNombre = new JList<String>(data);
        listaNombre.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        listaNombre.setLayoutOrientation(JList.VERTICAL);
        JScrollPane scroll = new JScrollPane(listaNombre);
        scroll.setPreferredSize(new Dimension(200, 330));
        der.add(scroll);
        trabajo.add(der);

        JPanel izq = new JPanel(new BorderLayout());
        izq.setBackground(getColorFondo());
        izq.setBorder(BorderFactory.createTitledBorder("Información Socio"));
        JPanel centro = new JPanel(new GridBagLayout());
        centro.setBackground(getColorFondo());
        GridBagConstraints constraints = new GridBagConstraints();
        JLabel lNom = new JLabel("Nombre:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        centro.add(lNom,constraints);
        JTextField tNom = new JTextField(15);
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
        JTextField tApellido = new JTextField(15);
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
        JTextField tDni = new JTextField(15);
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
        JCheckBox bMenor = new JCheckBox();
        constraints.gridx = 1;
        constraints.gridy = 3;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        bMenor.setActionCommand("menor");
        centro.add(bMenor,constraints);
        JLabel lAlta = new JLabel("Fecha de alta");
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        centro.add(lAlta,constraints);
        JTextField tAlta = new JTextField(15);
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        tAlta.setActionCommand("alta");
        centro.add(tAlta,constraints);
        izq.add(centro,BorderLayout.CENTER);

        JButton bPagos = new JButton("Gestión de pagos");
        bPagos.setActionCommand("pagos");
        izq.add(bPagos, BorderLayout.SOUTH);

        trabajo.add(izq);

        frame.setContentPane(trabajo);
//        frame.pack();
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private String[] cogerNombres() {
        String[] devolver = {"Pepito Grillo", "Abeja Maya", "Jack Sparrow", "NyssadiNim", "Teemo Hitler", "Lindo Gatito","Silvester Stalone","Piolin","Pato Lucas", "Palote Palote"};
        return devolver;
    }

    public static void main(String[] args) {
        new Principal();
    }
}
