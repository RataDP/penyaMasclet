package vista;

import java.util.Calendar;

/**
 * Created by ratadp on 3/06/14.
 */
public interface PreguntarVista {
    public String getDNI();

    public String getNombre();

    public String getApellido();

    public int getAnyo();

    public Calendar getIngreso();

    public String getDNIBorrar();

    public String getDescripcionPago();

    public double getCantidadPago();

    public Calendar getFechaPago();
}
