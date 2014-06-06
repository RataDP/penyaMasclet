package modelo.clasesNegocio;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by ratadp on 3/06/14.
 */
public class Socio implements Serializable {
    private static final long serialVersionUID = 4504814708893891930L;
    private String nombre,apellido,dni;
    private int anyoNacimiento;
    private Calendar fechaIngreso;
    private List<Pago> pagos;

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
        this.pagos = new ArrayList<>();
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public void setAnyoNacimiento(int anyoNacimiento) {
        this.anyoNacimiento = anyoNacimiento;
    }

    public void setFechaIngreso(Calendar fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
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

    public List<Pago> getPagos() {
        return pagos;
    }

    public void addPago(Pago pago) {
        pagos.add(pago);
    }
}
