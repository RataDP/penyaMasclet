import java.util.Calendar;

/**
 * Created by ratadp on 22/05/14.
 */
public class Pago {
    private String descripcion;
    private double importe;
    private Calendar fecha;

    public Pago() {
        super();
    }

    public Pago(String descripcion, double importe, Calendar fecha) {
        super();
        this.descripcion = descripcion;
        this.importe = importe;
        this.fecha = fecha;
    }
}
