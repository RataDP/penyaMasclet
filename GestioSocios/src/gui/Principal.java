package gui;

import com.toedter.calendar.JDateChooser;
import controlador.Controlador;
import modelo.Socio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Created by ratadp on 22/05/14.
 */
public class Principal extends Estilo {
    private JFrame frame;
    private Controlador con;
    private JTextField tNom,tApellido,tDni;
    private JCheckBox bMenor;
    private JDateChooser tAlta;
    private List<Socio> listaSocio;
    private Socio socio;
    private JList<String> jList;
    private String[] data;
    private int indice;

    public Principal(){
        super();
        frame = new JFrame("App de la Peña El Masclet");
        this.con = new Controlador();
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
        listaSocio = con.getListSocios();
        data = con.getVectorNombres(listaSocio);
        jList = new JList<String>(data);
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList.setLayoutOrientation(JList.VERTICAL);
        jList.setFont(getMono());
        JScrollPane scroll = new JScrollPane(jList);
        scroll.setPreferredSize(new Dimension(230, 330));
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
        JLabel lAlta = new JLabel("Fecha de alta");
        constraints.gridx = 0;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        centro.add(lAlta,constraints);
        tAlta = new JDateChooser("dd / MM / yy","## / ## / ##",'_');
        constraints.gridx = 1;
        constraints.gridy = 4;
        constraints.gridwidth = 1;
        constraints.gridheight = 1;
        centro.add(tAlta,constraints);
        izq.add(centro,BorderLayout.CENTER);

        JButton bPagos = new JButton("Gestión de pagos");
        bPagos.setActionCommand("pagos");
        izq.add(bPagos, BorderLayout.SOUTH);

        trabajo.add(izq);

        //LISTENERS
            // MOUSE LISTENER
        ListenerRaton listenerRaton = new ListenerRaton();
        jList.addMouseListener(listenerRaton);
            // ACTION LISTENER
        ListenerAccion listenerAccion = new ListenerAccion();
        tNom.addActionListener(listenerAccion);
        tApellido.addActionListener(listenerAccion);
        tDni.addActionListener(listenerAccion);
        bMenor.addActionListener(listenerAccion);

        frame.setContentPane(trabajo);
//        frame.pack();
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private class ListenerRaton extends MouseAdapter{
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            JList theList = (JList) mouseEvent.getSource();
            if (mouseEvent.getClickCount() == 1) {
                int index = theList.locationToIndex(mouseEvent.getPoint());
                indice = index;
                if (index >= 0) {
                    socio = listaSocio.get(index);
                    tNom.setText(socio.getNombre());
                    tApellido.setText(socio.getApellido());
                    tDni.setText(socio.getDni());
                    tAlta.setCalendar(socio.getFechaIngreso());
                    if (socio.isMayorEdad())
                        bMenor.setSelected(true);
                    else bMenor.setSelected(false);
                }
            }
        }
    }

    private class ListenerAccion implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String s = e.getActionCommand();
            String dni = socio.getDni();
            if (s.equals("nombre")) {
                System.out.println(e.getActionCommand());
                if (!tNom.getText().equals(socio.getNombre())) {
                    con.setNombre(dni, tNom.getText());
                    data[indice] = tNom.getText() +" "+ tApellido.getText();
                }
                jList.repaint();
            } else if (s.equals("apellido")) {
                System.out.println(e.getActionCommand());
                if (!tApellido.getText().equals(socio.getApellido())) {
                    con.setApellido(dni, tApellido.getText());
                    data[indice] = tNom.getText() +" "+ tApellido.getText();
                }
                jList.repaint();
            } else if (s.equals("menor")) {
                System.out.println(e.getActionCommand());
                con.setMenor(dni,bMenor.isSelected());
            }
        }
    }

    public static void main(String[] args) {
        new Principal();
    }
}
