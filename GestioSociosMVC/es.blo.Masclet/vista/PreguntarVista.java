package vista;

import java.io.File;
import java.util.Calendar;

/**
 * Created by ratadp on 3/06/14.
 */
public interface PreguntarVista {
    public String getDNINuevo();

    public String getNombreNuevo();

    public String getApellidoNuevo();

    public int getAnyoNuevo();

    public Calendar getIngresoNuevo();

    public String getDNIBorrar();

    public String getDNIPago();

    public String getDescripcionPago();

    public double getCantidadPago();

    public Calendar getFechaPago();

    public String getDnI();

    public String getNombre();

    public String getApellido();

    public int getAnyo();

    public Calendar getIngreso();

    public File getFileGuardar();

    public File getFileCargar();

    public String getSocioExportarPago();
}
