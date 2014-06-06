package vista.gui;

import com.toedter.calendar.JDateChooser;
import controlador.Controlador;
import modelo.PreguntarModelo;
import modelo.clasesNegocio.Socio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Calendar;
import java.util.List;

/**
 * Created by ratadp on 3/06/14.
 */
public class VentanaPrincipal extends Estilo {
    private JFrame frame;
    private PreguntarModelo modelo;
    private Controlador controlador;
    private JTextField tNom,tApellido,tDni,tAnyo;
    private JDateChooser tAlta;
    private List<Socio> listaSocios;
    private DefaultListModel<String> listaModelo;
    private int index;
    private String dniBorrado;
    private boolean seleccionado;
    private NuevoSocio nuevoSocio;
    private Pagos ventanaPagos;

    public VentanaPrincipal(JFrame frame, PreguntarModelo modelo, Controlador controlador) {
        this.frame = frame;
        this.modelo = modelo;
        this.controlador = controlador;
        this.listaSocios = modelo.listSocios();
        this.seleccionado = false;
        ejecutar();
    }

    public void ejecutar() {
        JPanel trabajo = new JPanel();
        trabajo.setLayout(new BorderLayout());
//        trabajo.setLayout(new BoxLayout(trabajo,BoxLayout.X_AXIS));
        trabajo.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        trabajo.setBackground(getColorFondo());
        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box,BoxLayout.X_AXIS));
        box.setBackground(getColorFondo());

        JPanel der = new JPanel();
        der.setBackground(getColorFondo());
        der.setBorder(BorderFactory.createTitledBorder("Lista de socios"));
        //Lista
        listaModelo = new DefaultListModel<>();
        rellenarLista();
        JList<String> jList = new JList<>(listaModelo);
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList.setLayoutOrientation(JList.VERTICAL);
        jList.setFont(getMono());
        JScrollPane scroll = new JScrollPane(jList);
        scroll.setPreferredSize(new Dimension(230, 315));
        der.add(scroll);
        box.add(der);

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
        centro.add(tNom,constraints);
        JLabel lApellido = new JLabel("Apellido:");
        constraints.gridx = 0;
        constraints.gridy = 1;
        centro.add(lApellido,constraints);
        tApellido = new JTextField(15);
        constraints.gridx = 1;
        constraints.gridy = 1;
        centro.add(tApellido,constraints);
        JLabel lDni = new JLabel("DNI:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        centro.add(lDni,constraints);
        tDni = new JTextField(15);
        tDni.setEditable(false);
        constraints.gridx = 1;
        constraints.gridy = 2;
        centro.add(tDni,constraints);
        JLabel lAnyo = new JLabel("Año de Nacimiento:");
        constraints.gridx = 0;
        constraints.gridy = 3;
        centro.add(lAnyo,constraints);
        tAnyo = new JTextField(15);
        constraints.gridx = 1;
        constraints.gridy = 3;
        centro.add(tAnyo,constraints);
        JLabel lAlta = new JLabel("Fecha de alta");
        constraints.gridx = 0;
        constraints.gridy = 4;
        centro.add(lAlta,constraints);
        tAlta = new JDateChooser("dd / MM / yy","## / ## / ##",' ');
        constraints.gridx = 1;
        constraints.gridy = 4;
        centro.add(tAlta,constraints);
        izq.add(centro,BorderLayout.CENTER);

        JPanel sur = new JPanel(new BorderLayout());
        sur.setBackground(getColorFondo());
        JButton bPagos = new JButton("Gestión de pagos");
        bPagos.setActionCommand("pagos");
        sur.add(bPagos, BorderLayout.EAST);
        JButton bActualizar = new JButton("Actualizar");
        bActualizar.setActionCommand("actualizar");
        sur.add(bActualizar,BorderLayout.WEST);
        izq.add(sur,BorderLayout.SOUTH);
        box.add(izq);

        trabajo.add(box,BorderLayout.CENTER);
        JButton bSalir = new JButton("Salir");
        bSalir.setActionCommand("salir");
        trabajo.add(bSalir,BorderLayout.SOUTH);

        //MENU
        JMenuBar menuBar = new JMenuBar();
        JMenu menuCl = new JMenu("Gestión Socios");
        menuCl.getAccessibleContext().setAccessibleDescription("Menú para la gestión de socios");
        menuBar.add(menuCl);
        JMenuItem menuNuevoSocio = new JMenuItem("Nuevo socio");
        menuNuevoSocio.setActionCommand("nuevosocio");
        menuCl.add(menuNuevoSocio);
        JMenuItem menuBorrarSocio = new JMenuItem("Borrar socio");
        menuBorrarSocio.setActionCommand("borrarsocio");
        menuCl.add(menuBorrarSocio);
        frame.setJMenuBar(menuBar);

        //LISTENERS
        // MOUSE LISTENER
        ListenerRaton listenerRaton = new ListenerRaton();
        jList.addMouseListener(listenerRaton);
        // ACTION LISTENER
        ListenerAccion listenerAccion = new ListenerAccion();
        bPagos.addActionListener(listenerAccion);
        bActualizar.addActionListener(listenerAccion);
        bSalir.addActionListener(listenerAccion);
        menuNuevoSocio.addActionListener(listenerAccion);
        menuBorrarSocio.addActionListener(listenerAccion);


        frame.setContentPane(trabajo);
//        frame.pack();
        frame.setSize(600, 423);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private void rellenarLista() {
        for (Socio socio: listaSocios) {
            String nombreCompleto = socio.getNombre() + " " + socio.getApellido();
            listaModelo.addElement(nombreCompleto);
        }
    }

    private void actualizarListaModelo(int indice) {
        Socio actualizado = listaSocios.get(indice);
        String nuevoNombre = actualizado.getNombre() + " " + actualizado.getApellido();
        listaModelo.set(indice,nuevoNombre);
    }

    private void borrarDeLista(int indice) {
        Socio borrar = listaSocios.get(indice);
        String nombreBorrar = borrar.getNombre() + " " + borrar.getApellido();
        listaModelo.removeElement(nombreBorrar);
    }

//    private void anyadirListaModelo(String dni) {
//        Socio nuevo = modelo.getSocio(dni);
//        String nombreNuevo = nuevo.getNombre() + " " + nuevo.getApellido();
//        listaModelo.addElement(nombreNuevo);
//    }

    public JFrame getFrame() {
        return frame;
    }

    public String getDNI() {
        return tDni.getText();
    }

    public String getNombre() {
        return tNom.getText();
    }

    public String getApellido() {
        return tApellido.getText();
    }

    public int getAnyo() {
        return Integer.parseInt(tAnyo.getText());
    }

    public Calendar getIngreso() {
        return tAlta.getCalendar();
    }

    public String getDNIBorrado() {
        return dniBorrado;
    }

    public String getDNINuevo() {
        return nuevoSocio.getDni();
    }

    public String getNombreNuevo() {
        return nuevoSocio.getNombre();
    }

    public String getApellidoNuevo() {
        return nuevoSocio.getApellido();
    }

    public int getAnyoNuevo() {
        return nuevoSocio.getAnyo();
    }

    public Calendar getIngresoNuevo() {
        return nuevoSocio.getIngreso();
    }

    public String getDescripcionPago() {
        return ventanaPagos.getDescripcion();
    }

    public double getImportePago() {
        return ventanaPagos.getImporte();
    }

    public Calendar getFechaPago() {
        return ventanaPagos.getFecha();
    }

    public String getDNIPago() {
        return ventanaPagos.getDNI();
    }

    private class ListenerRaton extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            JList theList = (JList) mouseEvent.getSource();
            if (mouseEvent.getClickCount() == 1) {
                index = theList.locationToIndex(mouseEvent.getPoint());
                if (index >= 0) {
                    Socio socio = listaSocios.get(index);
                    tNom.setText(socio.getNombre());
                    tApellido.setText(socio.getApellido());
                    tDni.setText(socio.getDni());
                    tAnyo.setText(String.valueOf(socio.getAnyoNacimiento()));
                    tAlta.setCalendar(socio.getFechaIngreso());
                    seleccionado = true;
                }
            }
        }

    }
    private class ListenerAccion implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "actualizar":
                    controlador.actualizarSocio();
                    actualizarListaModelo(index);
                    System.out.println(e.getActionCommand());
                    break;
                case "pagos":
                    System.out.println(e.getActionCommand());
                    ventanaPagos = new Pagos(controlador,listaSocios.get(index));
                    break;
                case "salir":
                    System.out.println(e.getActionCommand());
                    controlador.guardarDatos();
                    frame.dispose();
                    break;
                case "nuevosocio":
                    System.out.println(e.getActionCommand());
                    nuevoSocio = new NuevoSocio(frame,controlador, modelo, listaModelo, listaSocios);
                    break;
                case "borrarsocio":
                    System.out.println(e.getActionCommand() + index);
                    if (seleccionado) {
                        borrarDeLista(index);
                        Socio socio = listaSocios.get(index);
                        dniBorrado = socio.getDni();
                        controlador.borrarSocio();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Socio no seleccionado", "Socio no seleccionado", JOptionPane.WARNING_MESSAGE);
                        System.out.println("no seleccionado");
                    }
                    break;
            }
        }

    }

}
