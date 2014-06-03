package modelo.clasesNegocio;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;
import java.util.TreeSet;

/**
 * Created by ratadp on 3/06/14.
 */
public class Socio implements Serializable {
    private static final long serialVersionUID = 4504814708893891930L;
    private String nombre,apellido,dni;
    private int anyoNacimiento;
    private Calendar fechaIngreso;
    private Set<Pago> pagos;

    public Socio() {
        super();
    }

    public Socio(String dni,String nombre,String apellido, int anyo, Calendar fechaIngreso) {
        super();
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.anyoNacimiento = anyo;
        this.fechaIngreso = fechaIngreso;
        this.pagos = new TreeSet<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getDni() {
        return dni;
    }

    public int getAnyoNacimiento() {
        return anyoNacimiento;
    }

    public Calendar getFechaIngreso() {
        return fechaIngreso;
    }

    public Set<Pago> getPagos() {
        return pagos;
    }

    public void addPago(Pago pago) {
        pagos.add(pago);
    }
}
