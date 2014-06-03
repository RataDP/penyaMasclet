package vista;

import controlador.Controlador;
import modelo.PreguntarModelo;
import vista.gui.Bienvenida;

import javax.swing.*;
import java.util.Calendar;

import static java.lang.Thread.sleep;

/**
 * Created by ratadp on 3/06/14.
 */
public class ImplementacionVista implements InformarVista, PreguntarVista {
    private PreguntarModelo modelo;
    private Controlador controlador;
    private JFrame frame;
    private Bienvenida welcome;

    public ImplementacionVista() {
        super();
        this.frame = new JFrame("Mascletet");
        this.welcome = new Bienvenida();
    }

    public void setModelo(PreguntarModelo modelo) {
        this.modelo = modelo;
    }

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public void crearGUI() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                gui();
            }
        });
    }

    private void gui() {
        try {
            welcome.ejecutar();
            sleep(5000);
            welcome.cerrarVentana();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void socioAnyadido() {
        JOptionPane.showMessageDialog(frame, "Socio añadido correctamente.");
    }

    @Override
    public void socioNoAnyadido() {
        JOptionPane.showMessageDialog(frame,"ERROR 001: El socio no se ha podido añadir.","Socio adicción insatisfactoria",JOptionPane.ERROR_MESSAGE);
    }

    @Override
    public void socioBorrado() {
        JOptionPane.showMessageDialog(frame, "Socio borrado correctamente.");
    }

    @Override
    public void socioNoBorrado() {
        JOptionPane.showMessageDialog(frame,"ERROR 002: El socio no se ha podido borrar.","Socio borrado insatisfactorio",JOptionPane.ERROR_MESSAGE);

    }

    @Override
    public void socioNoEncontrado() {
        JOptionPane.showMessageDialog(frame,"El socio no se ha encontrado.","Socio no encontrado", JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public void pagoAnyadido() {
        JOptionPane.showMessageDialog(frame, "Pago añadido correctamente.");
    }

    @Override
    public void bdVacia() {
        JOptionPane.showMessageDialog(frame,"La base de datos esta vacía.","Base de datos vacía", JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public String getDNI() {
        return null;
    }

    @Override
    public String getNombre() {
        return null;
    }

    @Override
    public String getApellido() {
        return null;
    }

    @Override
    public int getAnyo() {
        return 0;
    }

    @Override
    public Calendar getIngreso() {
        return null;
    }

    @Override
    public String getDNIBorrar() {
        return null;
    }

    @Override
    public String getDescripcionPago() {
        return null;
    }

    @Override
    public double getCantidadPago() {
        return 0;
    }

    @Override
    public Calendar getFechaPago() {
        return null;
    }
}
