package modelo;

import java.util.*;

/**
 * Created by ratadp on 22/05/14.
 */
public class GestorSocios {
    private Map<String,Socio> socios;

    public GestorSocios() {
        super();
        this.socios = new TreeMap<String, Socio>();
    }

    public boolean isEmpty () {
        return socios.isEmpty();
    }

     public void addSocio (Socio socio) {
         socios.put(socio.getDni(),socio);
     }

    public void removeSocio (String dni) {
        if (!isEmpty())
            socios.remove(dni);
    }

    public List<Socio> listSocios() {
        List<Socio> lista = new ArrayList<Socio>();
        if (!isEmpty()) {
            Set<String> conjuntoDNI = socios.keySet();
            Iterator<String> iter = conjuntoDNI.iterator();
            while (iter.hasNext()) {
                String dni = iter.next();
                lista.add(socios.get(dni));
            }
        }
        return lista;
    }

    public Socio getSocio(String dni) {
        if (!isEmpty())
            return socios.get(dni);
        return null;
    }

    public boolean isSocio(String dni) {
        return socios.containsKey(dni);
    }

    public int size() {
        return socios.size();
    }
}
