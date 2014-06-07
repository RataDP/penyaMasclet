package vista.gui;

import modelo.ImplementacionModelo;
import modelo.PreguntarModelo;
import modelo.clasesNegocio.Socio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Created by ratadp on 7/06/14.
 */
public class ListadoMenores extends Estilo {
    private JDialog dialog;
    private PreguntarModelo modelo;
    private Calendar fechaActual;
    private int nMenores, nMenoresNoSeguros;

    public ListadoMenores(JFrame frame, PreguntarModelo modelo) {
        this.dialog = new JDialog(frame,"Listado de menores");
        this.modelo = modelo;
        this.fechaActual = new GregorianCalendar();
        ejecutar();

    }

    private void ejecutar() {
        JPanel trabajo = new JPanel(new BorderLayout());
        trabajo.setBackground(getColorFondo());
        trabajo.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        JPanel centro = new JPanel();
        centro.setLayout(new BoxLayout(centro,BoxLayout.Y_AXIS));
        centro.setBackground(getColorFondo());
        JList<String> jListMenores = new JList<>(rellenarLista());
        jListMenores.setLayoutOrientation(JList.VERTICAL);
        jListMenores.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jListMenores.setFont(getMono());
        JScrollPane scroll = new JScrollPane(jListMenores);
        scroll.setPreferredSize(new Dimension(230,315));
        centro.add(scroll, BorderLayout.CENTER);
        JLabel numero = new JLabel("<html>Número de menores: " + nMenores + " (<i>" + nMenoresNoSeguros + "</i>)");
        numero.setFont(getMono());
        centro.add(numero,BorderLayout.SOUTH);
        trabajo.add(centro,BorderLayout.CENTER);

        JPanel sur = new JPanel(new BorderLayout());
        sur.setBackground(getColorFondo());
        sur.setBorder(BorderFactory.createEmptyBorder());
        JButton volver = new JButton("Volver");
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
        dialog.setLocation(375,175);
        dialog.setResizable(false);
        dialog.setVisible(true);
    }

    private String[] rellenarLista() {
        java.util.List<Socio> menores = modelo.listSociosMenores(fechaActual.get(Calendar.YEAR));
        String[] vecMenores = new String[menores.size()];
        for (int i=0; i<menores.size();i++) {
            Socio menor = menores.get(i);
            if (fechaActual.get(Calendar.YEAR) - menor.getAnyoNacimiento() == 18) {
                vecMenores[i] = menor.getNombre() + " " + menor.getApellido() + " - " + menor.getAnyoNacimiento() + " **";
                nMenoresNoSeguros += 1;
            }
            else {
                vecMenores[i] = menor.getNombre() + " " + menor.getApellido() + " - " + menor.getAnyoNacimiento();
                nMenores += 1;
            }
        }
        return vecMenores;
    }

    public static void main(String[] args) {
        new ListadoMenores(new JFrame(),new ImplementacionModelo());
    }
}
