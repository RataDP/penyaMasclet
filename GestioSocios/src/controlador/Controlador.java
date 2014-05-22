package controlador;

import modelo.GestorSocios;
import modelo.Socio;

import java.util.List;

/**
 * Created by ratadp on 22/05/14.
 */
public class Controlador {
    private GestorSocios gS;

    public Controlador() {
        super();
        this.gS = new GestorSocios();
        rellenarGestor();
    }

    private void rellenarGestor() {
        String iStr;
        boolean edad;
        for (int i=0;i<30;i++) {
            iStr = "" + i;
            if (i%3 == 0)
                edad = false;
            else edad = true;
            Socio nuevo = new Socio("dni"+iStr,iStr,"apellido "+iStr,edad);
            gS.addSocio(nuevo);
        }
    }

    public String[] getVectorNombres(List<Socio> listaSocios) {
        String[] vectorNombre = new String[listaSocios.size()];
        String nombre, apellido;
        for (int i=0; i<listaSocios.size();i++) {
            nombre = listaSocios.get(i).getNombre();
            apellido = listaSocios.get(i).getApellido();
            vectorNombre[i] = nombre + " " + apellido;
        }
        return vectorNombre;
    }

    public List<Socio> getListSocios() {
        return gS.listSocios();
    }

    public void setNombre(String dni, String nombre) {
        Socio socio = gS.getSocio(dni);
        socio.setNombre(nombre);
    }

    public void setApellido(String dni, String apellido) {
        Socio socio = gS.getSocio(dni);
        socio.setApellido(apellido);
    }

    public void setMenor(String dni, boolean boleano) {
        Socio socio = gS.getSocio(dni);
        socio.setMayorEdad(boleano);
    }
}
