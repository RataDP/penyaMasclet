package modelo;

import modelo.clasesNegocio.Pago;
import modelo.clasesNegocio.Socio;

import java.util.List;

/**
 * Created by ratadp on 3/06/14.
 */
public interface PreguntarModelo {
    public boolean isEmpty();

    public int size();

    public boolean isSocio(String dni);

    public Socio getSocio(String dni);

    public List<Socio> listSocios();

    public List<Pago> listPagosSocio(String dni);
}
