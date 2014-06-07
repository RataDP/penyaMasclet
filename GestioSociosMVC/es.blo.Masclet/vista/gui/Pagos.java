package vista.gui;

import com.toedter.calendar.JDateChooser;
import controlador.Controlador;
import modelo.clasesNegocio.Pago;
import modelo.clasesNegocio.Socio;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by ratadp on 6/06/14.
 */
public class Pagos extends Estilo{
    private JFrame frame;
    private Controlador controlador;
    private Socio socio;
    private DefaultListModel<String> pagosModel;
    private JDateChooser tFecha;
    private JTextField tPrecio,tDescripcion;


    public Pagos(Controlador controlador, Socio socio) {
        super();
        this.frame = new JFrame("Gestión de pagos");
        this.controlador = controlador;
        this.socio = socio;
        ejecutar();
    }

    private void ejecutar() {
        JPanel trabajo = new JPanel(new BorderLayout());
        trabajo.setBackground(getColorFondo());
        trabajo.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));

        JPanel box = new JPanel();
        box.setLayout(new BoxLayout(box,BoxLayout.X_AXIS));
        box.setBackground(getColorFondo());
        box.setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
        trabajo.add(box, BorderLayout.CENTER);

        JPanel derechaBox = new JPanel(new BorderLayout());
        derechaBox.setBackground(getColorFondo());
        derechaBox.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        JPanel centro = new JPanel(new GridBagLayout());
        centro.setBackground(getColorFondo());
        GridBagConstraints constraints = new GridBagConstraints();
        JLabel lSocio = new JLabel("Socio:");
        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.gridheight = 1;
        constraints.gridwidth = 1;
        centro.add(lSocio,constraints);
        JLabel lNombre = new JLabel("<html><h3>" + socio.getNombre() + " " + socio.getApellido() + "</h3></html>");
        lNombre.setFont(getSansS());
        constraints.gridx = 1;
        constraints.gridy = 0;
        centro.add(lNombre,constraints);
        JLabel lDescripcion = new JLabel("Descripción: ");
        constraints.gridx = 0;
        constraints.gridy = 1;
        centro.add(lDescripcion,constraints);
        tDescripcion = new JTextField(15);
        constraints.gridx = 1;
        constraints.gridy = 1;
        centro.add(tDescripcion,constraints);
        JLabel lPrecio = new JLabel("Cantidad:");
        constraints.gridx = 0;
        constraints.gridy = 2;
        centro.add(lPrecio,constraints);
        tPrecio = new JTextField(15);
        constraints.gridx = 1;
        constraints.gridy = 2;
        centro.add(tPrecio,constraints);
        JLabel lFecha = new JLabel("Fecha:");
        constraints.gridx = 0;
        constraints.gridy = 3;
        centro.add(lFecha,constraints);
        tFecha = new JDateChooser(new Date(),"dd/MM/yy");
        constraints.gridx = 1;
        constraints.gridy = 3;
        centro.add(tFecha,constraints);
        derechaBox.add(centro, BorderLayout.CENTER);
        JButton bGuardar = new JButton("Guardar");
        bGuardar.setActionCommand("guardar");
        derechaBox.add(bGuardar, BorderLayout.SOUTH);

        box.add(derechaBox);

        pagosModel = new DefaultListModel<>();
        rellenarLista();
        JList<String> jList = new JList<>(pagosModel);
        jList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jList.setLayoutOrientation(JList.VERTICAL);
        jList.setFont(getMono());
        JScrollPane scroll = new JScrollPane(jList);
        box.add(scroll);

        JPanel sur = new JPanel(new BorderLayout());
        sur.setBackground(getColorFondo());
        JButton bSalir = new JButton("Salir");
        bSalir.setActionCommand("salir");
        sur.add(bSalir,BorderLayout.EAST);
        trabajo.add(sur,BorderLayout.SOUTH);

        // LISTENER
        AccionListener listener = new AccionListener();
        bGuardar.addActionListener(listener);
        bSalir.addActionListener(listener);

        frame.setContentPane(trabajo);
        frame.pack();
        frame.setLocation(165,65);
//        frame.setSize(575,300);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
    }

    private void rellenarLista() {
        java.util.List<Pago> pagos = socio.getPagos();
        String str;
        for (Pago pago: pagos) {
            Calendar fecha = pago.getFecha();
            String fechastr = fecha.get(Calendar.DAY_OF_MONTH) + "/" + fecha.get(Calendar.MONTH) + "/" + fecha.get(Calendar.YEAR);
            str = pago.getDescripcion() + ", cantidad: " + pago.getImporte() + "€, realizado el " + fechastr;
            pagosModel.addElement(str);
        }

    }

    private void aumentarLista() {
        String str = tDescripcion.getText() + ", cantidad: " + tPrecio.getText() + "€, realidado el ";
        Calendar fecha = tFecha.getCalendar();
        String fechaStr = fecha.get(Calendar.DAY_OF_MONTH) + "/" + fecha.get(Calendar.MONTH) + "/" + fecha.get(Calendar.YEAR);
        str += fechaStr;
        pagosModel.addElement(str);
    }

    public String getDNI() {
        return socio.getDni();
    }

    private class AccionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            switch (e.getActionCommand()) {
                case "salir":
                    frame.dispose();
                    break;
                case "guardar":
                    controlador.nuevoPago();
                    aumentarLista();
                    break;
            }
        }
    }

    public String getDescripcion() {
        return tDescripcion.getText();
    }

    public double getImporte() {
        return Double.parseDouble(tPrecio.getText());
    }

    public Calendar getFecha() {
        return tFecha.getCalendar();
    }
}
