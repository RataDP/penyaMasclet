package modelo;

import modelo.clasesNegocio.GestorSocio;
import modelo.clasesNegocio.Pago;
import modelo.clasesNegocio.Socio;
import vista.InformarVista;

import java.util.*;

/**
 * Created by ratadp on 3/06/14.
 */
public class ImplementacionModelo implements PreguntarModelo, ModificarModelo {
    private GestorSocio gS;
    private InformarVista vista;

    public ImplementacionModelo() {
        super();
        this.gS = new GestorSocio();
        rellenarModelo();
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
        System.out.println(dni);
        if (gS.removeSocio(dni))
            vista.socioBorrado();
        else
            vista.socioNoBorrado();
    }

    @Override
    public void addPago(String dni, Pago pago) {
        if (!gS.isSocio(dni))
            vista.socioNoEncontrado();
        else {
            Socio socio = gS.getSocio(dni);
            socio.addPago(pago);
            vista.pagoAnyadido();
        }

    }

    @Override
    public void guardarDatos() {
//        ObjectOutputStream oos=null;
//        try {
//            try {
//                FileOutputStream fos = new FileOutputStream("basedatos.bin");
//                oos = new ObjectOutputStream(fos);
//                oos.writeObject(gS);
//            }
//            finally {
//                oos.close();
//            }
//        }
//        catch(FileNotFoundException exc) {
//            System.out.println("El fichero no existe.");
//            exc.printStackTrace();
//        }
//        catch(IOException exc) {
//            exc.printStackTrace();
//        }
    }

    @Override
    public GestorSocio cargarDatos() {
        return null;
//        ObjectInputStream ois = null;
//        GestorSocio gestor = null;
//        try{
//            try {
//                FileInputStream fis = new FileInputStream("basedatos.bin");
//                ois = new ObjectInputStream(fis);
//                gestor = (GestorSocio)ois.readObject();
//            }
//            finally {
//                if(ois != null) ois.close();
//            }
//        }
//        catch(FileNotFoundException exc) {
//            System.err.println("Fichero de datos no existe. Se crea una nueva base de datos.");
//        }
//        catch(IOException exc) {
//            exc.printStackTrace();
//        }
//        catch(ClassNotFoundException exc) {
//            exc.printStackTrace();
//        }
//        if (gestor == null)
//            return new GestorSocio();
//        return gestor;
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
            Set<Pago> pagos = socio.getPagos();
            Iterator<Pago> iter = pagos.iterator();
            while (iter.hasNext())
                lista.add(iter.next());
        }
        return lista;
    }
}
