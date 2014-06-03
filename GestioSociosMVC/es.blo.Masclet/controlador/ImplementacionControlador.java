package controlador;

import modelo.ModificarModelo;
import vista.PreguntarVista;

import java.util.Calendar;

/**
 * Created by ratadp on 3/06/14.
 */
public class ImplementacionControlador implements Controlador {
    private ModificarModelo modelo;
    private PreguntarVista vista;

    public ImplementacionControlador() {
        super();
    }

    public void setModelo(ModificarModelo modelo) {
        this.modelo = modelo;
    }

    public void setVista(PreguntarVista vista) {
        this.vista = vista;
    }

    @Override
    public void guardarDatos() {
        modelo.guardarDatos();
    }

    @Override
    public void cargarDatos() {
        modelo.cargarDatos();
    }

    @Override
    public void nuevoSocio() {
        String dni,nombre,apellido;
        int anyo;
        Calendar ingreso;
        dni = vista.getDNI();
        nombre = vista.getNombre();
        apellido = vista.getApellido();
        anyo = vista.getAnyo();
        ingreso = vista.getIngreso();
        modelo.addSocio(dni,nombre,apellido,anyo,ingreso);
    }

    @Override
    public void borrarSocio() {
        String dni = vista.getDNIBorrar();
        modelo.removeSocio(dni);
    }

    @Override
    public void nuevoPago() {
        String descripcion = vista.getDescripcionPago();
        double cantidad = vista.getCantidadPago();
        Calendar fecha = vista.getFechaPago();

    }
}