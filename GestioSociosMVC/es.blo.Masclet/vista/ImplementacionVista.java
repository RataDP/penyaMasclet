package vista;

import controlador.Controlador;
import modelo.PreguntarModelo;
import vista.gui.Bienvenida;
import vista.gui.Estilo;
import vista.gui.VentanaPrincipal;

import javax.swing.*;
import java.io.File;
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
    private Estilo estilo;
    private VentanaPrincipal ventanaPrincipal;

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
        welcome.ejecutar();
        try {
            sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                gui();
            }
        });
    }

    private void gui() {
        welcome.cerrarVentana();
        frame = venPrincipal();
        ventanaPrincipal.ejecutar();

    }

    private JFrame venPrincipal() {
        ventanaPrincipal = new VentanaPrincipal(frame,modelo,controlador);
        return ventanaPrincipal.getFrame();
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
    public void socioActualizado() {
        JOptionPane.showMessageDialog(frame, "Socio actualizado.");
    }

    @Override
    public void ficheroNoEncontrado() {
        JOptionPane.showMessageDialog(frame,"Fichero de datos, basedatos.bin, no encontrado.","Fichero no encontrado", JOptionPane.WARNING_MESSAGE);
    }

    @Override
    public void ficheroCargado() {
        String nombre = getFileCargar().getName();
        JOptionPane.showMessageDialog(frame,"Fichero, " + nombre + ", cargado", "Fichero cargado", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void ficheroGuardado() {
        String nombre = getFileGuardar().getName();
        JOptionPane.showMessageDialog(frame,"Fichero guardado, con nombre " + nombre, "Fichero guardado", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void ficheroTexto() {
        JOptionPane.showMessageDialog(frame,"Fichero exportado", "Fichero exportado", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public String getDNINuevo() {
        return ventanaPrincipal.getDNINuevo();
    }

    @Override
    public String getNombreNuevo() {
        return ventanaPrincipal.getNombreNuevo();
    }

    @Override
    public String getApellidoNuevo() {
        return ventanaPrincipal.getApellidoNuevo();
    }

    @Override
    public int getAnyoNuevo() {
        return ventanaPrincipal.getAnyoNuevo();
    }

    @Override
    public Calendar getIngresoNuevo() {
        return ventanaPrincipal.getIngresoNuevo();
    }

    @Override
    public String getDNIBorrar() {
        return ventanaPrincipal.getDNIBorrado();
    }

    @Override
    public String getDNIPago() {
        return ventanaPrincipal.getDNIPago();
    }

    @Override
    public String getDescripcionPago() {
        return ventanaPrincipal.getDescripcionPago();
    }

    @Override
    public double getCantidadPago() {
        return ventanaPrincipal.getImportePago();
    }

    @Override
    public Calendar getFechaPago() {
        return ventanaPrincipal.getFechaPago();
    }

    @Override
    public String getDnI() {
        return ventanaPrincipal.getDNI();
    }

    @Override
    public String getNombre() {
        return ventanaPrincipal.getNombre();
    }

    @Override
    public String getApellido() {
        return ventanaPrincipal.getApellido();
    }

    @Override
    public int getAnyo() {
        return ventanaPrincipal.getAnyo();
    }

    @Override
    public Calendar getIngreso() {
        return ventanaPrincipal.getIngreso();
    }

    @Override
    public File getFileGuardar() {
        return ventanaPrincipal.getFileGuardar();
    }

    @Override
    public File getFileCargar() {
        return ventanaPrincipal.getFileCargar();
    }

    @Override
    public String getSocioExportarPago() {
        return ventanaPrincipal.getDNIPago();
    }
}
