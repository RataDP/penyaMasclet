package vista.gui;

import modelo.ImplementacionModelo;
import modelo.PreguntarModelo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by ratadp on 7/06/14.
 */
public class SociosTotales extends Estilo {
    private JDialog dialog;
    private PreguntarModelo modelo;
    private JButton volver;

    public SociosTotales(JFrame frame, PreguntarModelo modelo) {
        super();
        this.dialog = new JDialog(frame, "Socios totales");
        this.modelo = modelo;
        ejecutar();

    }

    private void ejecutar() {
        JPanel trabajo = new JPanel(new BorderLayout());
        trabajo.setBackground(getColorFondo());
        trabajo.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        JPanel centro = new JPanel(new FlowLayout());
        centro.setBackground(getColorFondo());
        JLabel letra = new JLabel("Número totales de socios:");
        letra.setFont(getSansS());
        centro.add(letra);
        String numeroSocios = Integer.toString(modelo.size());
        JTextField numero = new JTextField(numeroSocios,10);
        numero.setEditable(false);
        numero.setFont(getMono());
        centro.add(numero);
        trabajo.add(centro, BorderLayout.CENTER);

        JPanel sur = new JPanel(new BorderLayout());
        sur.setBackground(getColorFondo());
        volver = new JButton("Volver");
        sur.add(volver,BorderLayout.EAST);
        trabajo.add(sur,BorderLayout.SOUTH);

        volver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == (JButton) volver)
                    dialog.dispose();
            }
        });

        dialog.setContentPane(trabajo);
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.pack();
        dialog.setLocation(375, 175);
        dialog.setResizable(false);
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        new SociosTotales(new JFrame(), new ImplementacionModelo());
    }
}
