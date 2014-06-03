package modelo;

import modelo.clasesNegocio.GestorSocio;
import modelo.clasesNegocio.Pago;

import java.util.Calendar;

/**
 * Created by ratadp on 3/06/14.
 */
public interface ModificarModelo {
    public void addSocio(String dni, String nombre, String apellido, int anyo, Calendar ingreso);

    public void actualizarSocio(String dni, String nombre, String apellido, int anyo, Calendar ingreso);

    public void removeSocio(String dni);

    public void addPago(String dni, Pago pago);

    public void guardarDatos();

    public GestorSocio cargarDatos();
}
