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
public class Ventana extends Estilo {
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
    private DefaultListModel<String> modeloLista;

    public Ventana(Controlador controlador){
        super();
        frame = new JFrame("App de la Peña El Masclet");
        this.con = controlador;
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
        //JLista
        modeloLista = new DefaultListModel<String>();
        jList = new JList<String>(modeloLista);
        listaSocio = con.getListSocios();
        data = con.getVectorNombres(listaSocio);
        for (int i =0;i<data.length;i++)
            modeloLista.addElement(data[i]);
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList.setLayoutOrientation(JList.VERTICAL);
        jList.setFont(getMono());
        JScrollPane scroll = new JScrollPane(jList);
        scroll.setPreferredSize(new Dimension(230, 315));
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

        //Menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Socios");
        menu.getAccessibleContext().setAccessibleDescription("Descripción");
        menuBar.add(menu);
        JMenuItem menuNuevoSocio = new JMenuItem("Nuevo socio");
        menuNuevoSocio.setActionCommand("nuevosocio");
        menu.add(menuNuevoSocio);
        frame.setJMenuBar(menuBar);

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
        menuNuevoSocio.addActionListener(listenerAccion);

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
            String dni;
            switch(s) {
                case "nombre":
                    dni = socio.getDni();
                    System.out.println(e.getActionCommand());
                    if (!tNom.getText().equals(socio.getNombre())) {
                        con.setNombre(dni, tNom.getText());
                        String nombre = tNom.getText() + " " + tApellido.getText();
                        modeloLista.set(indice, nombre);
                    }
                    break;
                case "apellido":
                    dni = socio.getDni();
                    System.out.println(e.getActionCommand());
                    if (!tApellido.getText().equals(socio.getApellido())) {
                        con.setApellido(dni, tApellido.getText());
                        String nombre = tNom.getText() +" "+ tApellido.getText();
                        modeloLista.set(indice,nombre);
                    }
                    break;
                case "menor":
                    dni = socio.getDni();
                    System.out.println(e.getActionCommand());
                    con.setMenor(dni,bMenor.isSelected());
                    break;
                case "nuevosocio":
                    System.out.println(e.getActionCommand());
                    new NuevoSocio(frame,con, modeloLista);
                    break;
            }
        }
    }


    public static void main(String[] args) {
        new Ventana(new Controlador());
    }
}
