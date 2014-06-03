package modelo.clasesNegocio;

import java.io.Serializable;
import java.util.Calendar;

/**
 * Created by ratadp on 3/06/14.
 */
public class Pago implements Serializable {
    private static final long serialVersionUID = 5580800658942168904L;
    private String descripcion;
    private double importe;
    private Calendar fecha;

    public Pago(String descripcion, double importe, Calendar fecha) {
        super();
        this.descripcion = descripcion;
        this.importe = importe;
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public double getImporte() {
        return importe;
    }

    public Calendar getFecha() {
        return fecha;
    }
}
