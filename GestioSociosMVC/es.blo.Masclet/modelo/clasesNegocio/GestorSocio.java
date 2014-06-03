package modelo.clasesNegocio;

import java.io.Serializable;
import java.util.*;

/**
 * Created by ratadp on 3/06/14.
 */
public class GestorSocio implements Serializable {
    private static final long serialVersionUID = -4536383989861777129L;
    private Map<String, Socio> mapaSocios;

    public GestorSocio() {
        super();
        this.mapaSocios = new TreeMap<>();
    }

    public boolean isEmpty() {
        return mapaSocios.isEmpty();
    }

    public int size() {
        return mapaSocios.size();
    }

    public boolean addSocio(Socio socio) {
        Socio comprueba = mapaSocios.put(socio.getDni(),socio);
        if (comprueba.equals(socio))
            return true;
        return false;
    }

    public boolean removeSocio(String dni) {
        if (!isEmpty()) {
            mapaSocios.remove(dni);
            if (mapaSocios.containsKey(dni))
                return false;
            return true;
        }
        return false;
    }

    public List<Socio> listSocios() {
        List<Socio> lista = new ArrayList<Socio>();
        if (!isEmpty()) {
            Set<String> conjuntoDNI = mapaSocios.keySet();
            Iterator<String> iter = conjuntoDNI.iterator();
            while (iter.hasNext()) {
                String dni = iter.next();
                lista.add(mapaSocios.get(dni));
            }
        }
        return lista;
    }

    public boolean isSocio(String dni) {
        return mapaSocios.containsKey(dni);
    }

    public Socio getSocio(String dni) {
        Socio valorRetorno = new Socio();
        if (!isEmpty())
            if(isSocio(dni))
                return mapaSocios.get(dni);
        return valorRetorno;
    }
}
