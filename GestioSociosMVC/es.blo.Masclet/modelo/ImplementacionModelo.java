package modelo;

import modelo.clasesNegocio.GestorSocio;
import modelo.clasesNegocio.Pago;
import modelo.clasesNegocio.Socio;
import vista.InformarVista;

import java.io.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * Created by ratadp on 3/06/14.
 */
public class ImplementacionModelo implements PreguntarModelo, ModificarModelo, Serializable {
    private static final long serialVersionUID = -8985753006142654544L;
    private GestorSocio gS;
    private InformarVista vista;
    private File archivoAnterior;

    public ImplementacionModelo() {
        super();
        this.gS = cargarDatos();
        this.archivoAnterior = null;
    }

    public void setVista(InformarVista vista) {
        this.vista = vista;
    }

    private void rellenarModelo() {
        String dni,nombre,apellido;
        int anyo = 1960;
        for (int i=0;i<20;i++)  {
            dni = "dni" + i;
            nombre = "nombre";
            apellido = String.valueOf(i);
            anyo += i;
            Socio nuevo = new Socio(dni,nombre,apellido,anyo,new GregorianCalendar());
            gS.addSocio(nuevo);
        }
    }

    @Override
    public void addSocio(String dni, String nombre, String apellido, int anyo, Calendar ingreso) {
        Socio nuevo = new Socio(dni,nombre,apellido,anyo,ingreso);
        if (gS.addSocio(nuevo))
            vista.socioAnyadido();
        else
            vista.socioNoAnyadido();
    }

    @Override
    public void actualizarSocio(String dni, String nombre, String apellido, int anyo, Calendar ingreso) {
        Socio socio = gS.getSocio(dni);
        socio.setNombre(nombre);
        socio.setApellido(apellido);
        socio.setAnyoNacimiento(anyo);
        socio.setFechaIngreso(ingreso);
        vista.socioActualizado();
    }

    @Override
    public void removeSocio(String dni) {
        if (gS.removeSocio(dni))
            vista.socioBorrado();
        else
            vista.socioNoBorrado();
    }

    @Override
    public void addPago(String dni, String descripcion, double importe, Calendar realizacion) {
        if (!gS.isSocio(dni))
            vista.socioNoEncontrado();
        else {
            Socio socio = gS.getSocio(dni);
            Pago pago = new Pago(descripcion,importe,realizacion);
            socio.addPago(pago);
            vista.pagoAnyadido();
        }
    }

    @Override
    public void guardarDatos() {
        ObjectOutputStream oos=null;
        try {
            try {
                FileOutputStream fos;
                if ( archivoAnterior == null )
                    fos = new FileOutputStream("basedatos.bin");
                else
                    fos = new FileOutputStream(archivoAnterior);
                oos = new ObjectOutputStream(fos);
                oos.writeObject(gS);

            }
            finally {
                oos.close();
            }
        }
        catch(FileNotFoundException exc) {
            vista.ficheroNoEncontrado();
            exc.printStackTrace();
        }
        catch(IOException exc) {
            exc.printStackTrace();
        }
    }

    @Override
    public GestorSocio cargarDatos() {
        ObjectInputStream ois = null;
        GestorSocio gestor = null;
        try{
            try {
                FileInputStream fis = new FileInputStream("basedatos.bin");
                ois = new ObjectInputStream(fis);
                gestor = (GestorSocio)ois.readObject();
            }
            finally {
                if(ois != null) ois.close();
            }
        }
        catch(FileNotFoundException exc) {
            System.err.println("Fichero no encontrado.");
        }
        catch(IOException exc) {
            exc.printStackTrace();
        }
        catch(ClassNotFoundException exc) {
            System.out.println("ClassNotFound");
            exc.printStackTrace();
        }
        if (gestor == null)
            return new GestorSocio();
        return gestor;
    }

    @Override
    public void guardarArchivo(File archivo) {
        ObjectOutputStream oos=null;
        try {
            try {
                archivoAnterior = archivo;
                FileOutputStream fos = new FileOutputStream(archivo + ".bin");
                oos = new ObjectOutputStream(fos);
                oos.writeObject(gS);
            }
            finally {
                oos.close();
            }
        }
        catch(FileNotFoundException exc) {
            vista.ficheroNoEncontrado();
            exc.printStackTrace();
        }
        catch(IOException exc) {
            exc.printStackTrace();
        }
        vista.ficheroGuardado();
    }

    @Override
    public void cargarArchivo(File archivo) {
        ObjectInputStream ois = null;
        GestorSocio gestor = null;
        try{
            try {
                archivoAnterior = archivo;
                FileInputStream fis = new FileInputStream(archivo);
                ois = new ObjectInputStream(fis);
                gestor = (GestorSocio)ois.readObject();
            }
            finally {
                if(ois != null) ois.close();
                if ( gestor != null)
                    gS = gestor;
            }
        }
        catch(FileNotFoundException exc) {
            System.err.println("Fichero no encontrado.");
        }
        catch(IOException exc) {
            exc.printStackTrace();
        }
        catch(ClassNotFoundException exc) {
            System.out.println("ClassNotFound");
            exc.printStackTrace();
        }
        vista.ficheroCargado();
    }

    @Override
    public void exportarPagos(String dni) {
        PrintWriter outputStream;
        Socio socio = gS.getSocio(dni);
        List<Pago> lista = socio.getPagos();
        try {
            outputStream = new PrintWriter("Pagos" + dni + ".txt");
        double importeTotal = 0.0;
        StringBuilder sb = new StringBuilder("Lista de pagos del socio (" + dni + ") " + socio.getNombre() + " " + socio.getApellido() +"\n\r");
        for (Pago pago: lista) {
            sb.append("\t" + pago + "\n\r");
            importeTotal += pago.getImporte();
        }
        sb.append("Con " + lista.size() + " pagos, con un total recaudado de " + importeTotal + "€");
        outputStream.println(sb.toString());
        outputStream.close();
        } catch (FileNotFoundException e) {
            System.err.println("fichero no existe");
        }
        vista.ficheroTexto();
    }

    @Override
    public boolean isEmpty() {
        if (gS.isEmpty()) {
            vista.bdVacia();
            return true;
        }
        else return false;
    }

    @Override
    public int size() {
       return gS.size();
    }

    @Override
    public boolean isSocio(String dni) {
        if (!isEmpty()) {
            if (gS.isSocio(dni))
                return true;
            else
                vista.socioNoEncontrado();
        }
        return false;
    }

    @Override
    public boolean isSocioSinAviso(String dni) {
        if (!gS.isEmpty())
            return gS.isSocio(dni);
        return false;
    }

    @Override
    public Socio getSocio(String dni) {
        if (isSocio(dni))
            return gS.getSocio(dni);
        return new Socio();
    }

    @Override
    public List<Socio> listSocios() {
        List<Socio> lista = gS.listSocios();
        if (!isEmpty())
            return lista;
        return lista;
    }

    @Override
    public List<Pago> listPagosSocio(String dni) {
        List<Pago> lista = new ArrayList<>();
        if (!isEmpty()) {
            Socio socio = getSocio(dni);
            List<Pago> pagos = socio.getPagos();
            for (Pago pago: pagos)
                lista.add(pago);
        }
        return lista;
    }

    @Override
    public List<Socio> listSociosMenores(int anyoActual) {
        List<Socio> lista = new ArrayList<>();
        if (!isEmpty()) {
            List<Socio> socios = gS.listSocios();
            for (Socio socio: socios) {
                if (anyoActual - socio.getAnyoNacimiento() <=18)
                    lista.add(socio);
            }
        }
        return lista;
    }
}
