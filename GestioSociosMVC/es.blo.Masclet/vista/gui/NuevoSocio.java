package vista.gui;

import com.toedter.calendar.JDateChooser;
import controlador.Controlador;
import modelo.PreguntarModelo;
import modelo.clasesNegocio.Socio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.List;

/**
 * Created by ratadp on 3/06/14.
 */
public class NuevoSocio extends Estilo{
    private JDialog dialog;
    private Controlador controlador;
    private PreguntarModelo modelo;
    private JTextField tDni,tNombre,tApellido,tAnyo;
    private JDateChooser tIngreso;
    private java.util.List<Socio> listaSocio;
    private DefaultListModel<String> listaModel;

    public NuevoSocio(JFrame frame, Controlador controlador, PreguntarModelo modelo, DefaultListModel<String> lista, List<Socio> listaSocio) {
        dialog = new JDialog(frame, "Nuevo socio");
        this.controlador = controlador;
        this.modelo = modelo;
        this.listaModel = lista;
        this.listaSocio = listaSocio;
        ejecutar();
    }

    private void ejecutar() {
        JPanel trabajo = new JPanel(new BorderLayout());
        trabajo.setBackground(getColorFondo());
        trabajo.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        JPanel center = new JPanel(new GridLayout(5,2));
        center.setBackground(getColorFondo());
        JLabel lDni = new JLabel("DNI:");
        center.add(lDni);
        tDni = new JTextField(20);
        center.add(tDni);
        JLabel lNombre = new JLabel("Nombre:");
        center.add(lNombre);
        tNombre = new JTextField(20);
        center.add(tNombre);
        JLabel lApellido = new JLabel("Apellido:");
        center.add(lApellido);
        tApellido = new JTextField(20);
        center.add(tApellido);
        JLabel lAnyo = new JLabel("Año de nacimiento:");
        center.add(lAnyo);
        tAnyo = new JTextField(20);
        center.add(tAnyo);
        JLabel lIngreso = new JLabel("Fecha de ingreso:");
        center.add(lIngreso);
        tIngreso = new JDateChooser("dd/MM/yy","##/##/##",'_');
        center.add(tIngreso);
        trabajo.add(center);

        JPanel sur = new JPanel(new BorderLayout());
        sur.setBackground(getColorFondo());
        JButton bSalir = new JButton("Salir");
        bSalir.setActionCommand("salir");
        sur.add(bSalir,BorderLayout.EAST);
        JButton bCrear = new JButton("Crear");
        bCrear.setActionCommand("crear");
        sur.add(bCrear,BorderLayout.WEST);
        trabajo.add(sur,BorderLayout.SOUTH);

        // LISTENER
        ListenerAccion listener = new ListenerAccion();
        bCrear.addActionListener(listener);
        bSalir.addActionListener(listener);


        dialog.setContentPane(trabajo);
        dialog.pack();
        dialog.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        dialog.setVisible(true);
    }

    private class ListenerAccion implements ActionListener {
        public ListenerAccion() {
            super();
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            switch(e.getActionCommand()) {
                case "salir":
                    dialog.dispose();
                    break;
                case "crear":
                    controlador.nuevoSocio();
                    Socio nuevo = modelo.getSocio(getDni());
                    listaSocio.add(nuevo);
                    String nombreNuevo = nuevo.getNombre() + " " + nuevo.getApellido();
                    listaModel.addElement(nombreNuevo);
                    dialog.dispose();
                    break;
            }
        }
    }

    public String getDni() {
        return tDni.getText();
    }

    public String getNombre() {
        return tNombre.getText();
    }

    public String getApellido() {
        return tApellido.getText();
    }

    public int getAnyo() {
        return Integer.parseInt(tAnyo.getText());
    }

    public Calendar getIngreso() {
        return tIngreso.getCalendar();
    }
}
