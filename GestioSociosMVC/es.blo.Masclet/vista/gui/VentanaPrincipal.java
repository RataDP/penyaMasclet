package vista.gui;

import com.toedter.calendar.JDateChooser;
import controlador.Controlador;
import modelo.PreguntarModelo;
import modelo.clasesNegocio.Socio;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
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
    private JMenuItem menuCargarArchivo, menuGuardarComoArchivo, menuGuardarArchivo;
    private File fileGuardar, fileCargar;
    private FileNameExtensionFilter filtro;

    public VentanaPrincipal(JFrame frame, PreguntarModelo modelo, Controlador controlador) {
        this.frame = frame;
        this.modelo = modelo;
        this.controlador = controlador;
        this.listaSocios = modelo.listSocios();
        this.seleccionado = false;
        this.filtro = new FileNameExtensionFilter("Archivo Binario (.bin)", "bin");
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

        // PANEL LISTA
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

        // PANEL DETALLES SOCIO
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
        tAlta = new JDateChooser("dd/MM/yy","##/##/##",' ');
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
//        JButton bSalir = new JButton("Salir");
//        bSalir.setActionCommand("salir");
//        trabajo.add(bSalir,BorderLayout.SOUTH);

        // MENU
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(getColorFondo());
        frame.setJMenuBar(menuBar);

        JMenu menuArchivo = new JMenu("<html><u>A</u>rchivo</html>");
        menuArchivo.getAccessibleContext().setAccessibleDescription("Menú para la gestión del programa.");
        menuArchivo.setMnemonic(KeyEvent.VK_A);
        menuBar.add(menuArchivo);
        menuGuardarArchivo = new JMenuItem("Guardar...");
        menuGuardarArchivo.setActionCommand("guardar");
        menuGuardarArchivo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, Event.CTRL_MASK));
        menuGuardarArchivo.setMnemonic(KeyEvent.VK_G);
        menuArchivo.add(menuGuardarArchivo);
        menuGuardarComoArchivo = new JMenuItem("Guardar como...");
        menuGuardarComoArchivo.setActionCommand("guardarcomo");
        menuArchivo.add(menuGuardarComoArchivo);
        menuCargarArchivo = new JMenuItem("Cargar...");
        menuCargarArchivo.setActionCommand("cargar");
        menuArchivo.add(menuCargarArchivo);
        menuArchivo.addSeparator();
        JMenuItem menuSalir = new JMenuItem("Salir");
        menuSalir.setActionCommand("salir");
        menuSalir.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F4, Event.ALT_MASK));
        menuSalir.setMnemonic(KeyEvent.VK_F4);
        menuArchivo.add(menuSalir);

        JMenu menuCl = new JMenu("<html><u>S</u>ocios</html>");
        menuCl.getAccessibleContext().setAccessibleDescription("Menú para la gestión de socios.");
        menuCl.setMnemonic(KeyEvent.VK_S);
        menuBar.add(menuCl);
        JMenuItem menuNuevoSocio = new JMenuItem("<html><u>N</u>uevo socio</html>");
        menuNuevoSocio.setActionCommand("nuevosocio");
        menuNuevoSocio.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, Event.CTRL_MASK | Event.SHIFT_MASK));
        menuNuevoSocio.setMnemonic(KeyEvent.VK_N);
        menuCl.add(menuNuevoSocio);
        JMenuItem menuBorrarSocio = new JMenuItem("<html><u>B</u>orrar socio</html>");
        menuBorrarSocio.setActionCommand("borrarsocio");
        menuBorrarSocio.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_B, Event.CTRL_MASK | Event.SHIFT_MASK));
        menuBorrarSocio.setMnemonic(KeyEvent.VK_B);
        menuCl.add(menuBorrarSocio);

        JMenu menuListados = new JMenu("<html><u>L</u>istados</html>");
        menuListados.getAccessibleContext().setAccessibleDescription("Menú para la elección de diferentes listados.");
        menuListados.setMnemonic(KeyEvent.VK_L);
        JMenuItem mSociosTotales = new JMenuItem("Socios totales");
        mSociosTotales.setActionCommand("sociostotales");
        menuListados.add(mSociosTotales);
        JMenuItem mListMenores = new JMenuItem("Listado Menores");
        mListMenores.setActionCommand("listadomenores");
        menuListados.add(mListMenores);
        menuBar.add(menuListados);

        JMenu menuAyuda = new JMenu("<html>A<u>y</u>uda</html>");
        menuAyuda.getAccessibleContext().setAccessibleDescription("Menú de ayuda.");
        menuAyuda.setMnemonic(KeyEvent.VK_Y);
        menuBar.add(menuAyuda);
        JMenuItem menuAbout = new JMenuItem("Sobre");
        menuAbout.setActionCommand("sobre");
        menuAyuda.add(menuAbout);


        //LISTENERS
        // MOUSE LISTENER
        ListenerRaton listenerRaton = new ListenerRaton();
        jList.addMouseListener(listenerRaton);
        // ACTION LISTENER
        ListenerAccion listenerAccion = new ListenerAccion();
        bPagos.addActionListener(listenerAccion);
        bActualizar.addActionListener(listenerAccion);
//        bSalir.addActionListener(listenerAccion);
        menuGuardarArchivo.addActionListener(listenerAccion);
        menuGuardarComoArchivo.addActionListener(listenerAccion);
        menuCargarArchivo.addActionListener(listenerAccion);
        menuSalir.addActionListener(listenerAccion);
        menuNuevoSocio.addActionListener(listenerAccion);
        menuBorrarSocio.addActionListener(listenerAccion);
        menuAbout.addActionListener(listenerAccion);
        mListMenores.addActionListener(listenerAccion);
        mSociosTotales.addActionListener(listenerAccion);


        frame.setContentPane(trabajo);
        frame.pack();
//        frame.setSize(650, 450);
        frame.setLocation(150, 50);
        frame.setResizable(false);
        frame.setIconImage(getIcon());
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

    private void vaciarLista() {
        listaModelo.clear();
    }

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

    public File getFileGuardar() {
        return fileGuardar;
    }

    public File getFileCargar() {
        return fileCargar;
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
                    break;
                case "pagos":
                    ventanaPagos = new Pagos(controlador,listaSocios.get(index));
                    break;
                case "salir":
                    controlador.guardarDatos();
                    frame.dispose();
                    break;
                case "nuevosocio":
                    nuevoSocio = new NuevoSocio(frame,controlador, modelo, listaModelo, listaSocios);
                    break;
                case "borrarsocio":
                    if (seleccionado) {
                        borrarDeLista(index);
                        Socio socio = listaSocios.get(index);
                        dniBorrado = socio.getDni();
                        controlador.borrarSocio();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Socio no seleccionado", "Socio no seleccionado", JOptionPane.WARNING_MESSAGE);
                    }
                    break;
                case "sobre":
                    new About().ejecutar();
                    break;
                case "sociostotales":
                    new SociosTotales(frame,modelo);
                    break;
                case "listadomenores":
                    new ListadoMenores(frame,modelo);
                    break;
                case "guardarcomo":
                    JFileChooser guardarArchivo = new JFileChooser();
                    guardarArchivo.setFileFilter(filtro);
                    int seleccionG = guardarArchivo.showSaveDialog(menuGuardarComoArchivo);
                    if (seleccionG == JFileChooser.APPROVE_OPTION) {
                        fileGuardar = guardarArchivo.getSelectedFile();
                        controlador.guardarArchivo();
                    }
                    break;
                case "guardar":
                    controlador.guardarDatos();
                    break;
                case "cargar":
                    JFileChooser cargarArchivo = new JFileChooser();
                    cargarArchivo.setFileFilter(filtro);
                    int seleccionC = cargarArchivo.showOpenDialog(menuCargarArchivo);
                    if (seleccionC == JFileChooser.APPROVE_OPTION) {
                        fileCargar = cargarArchivo.getSelectedFile();
                        controlador.cargarArchivo();
                        listaSocios = modelo.listSocios();
                        vaciarLista();
                        rellenarLista();
                        frame.repaint();
                    }
                    break;
            }
        }

    }

}
