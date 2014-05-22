import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by ratadp on 22/05/14.
 */
public class Socio {
    private String nombre;
    private String apellido;
    private boolean mayorEdad;
    private String dni;
    private Calendar fechaIngreso;
    private Set<Pago> pagos;

    public Socio() {
        super();
    }

    public Socio(String dni, String nombre, String apellido, boolean mayorEdad, Calendar fechaIngreso) {
        super();
        this.nombre = nombre;
        this.apellido = apellido;
        this.mayorEdad = mayorEdad;
        this.dni = dni;
        this.fechaIngreso = fechaIngreso;
        this.pagos = new HashSet<Pago>();
    }

    public Socio(String dni, String nombre, String apellido, boolean mayorEdad ) {
        super();
        this.nombre = nombre;
        this.apellido = apellido;
        this.mayorEdad = mayorEdad;
        this.dni = dni;
        this.fechaIngreso = new GregorianCalendar();
        this.pagos = new HashSet<Pago>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public boolean isMayorEdad() {
        return mayorEdad;
    }

    public void setMayorEdad(boolean mayorEdad) {
        this.mayorEdad = mayorEdad;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Calendar getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Calendar fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Set<Pago> getPagos() {
        return pagos;
    }
}
