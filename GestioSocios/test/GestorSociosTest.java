import modelo.GestorSocios;
import modelo.Socio;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class GestorSociosTest {
    GestorSocios gS;
    Socio prueba;

    @Before
    public void setUp() {
        this.gS = new GestorSocios();
        prueba = new Socio("00000","Prueba","Prueba",true);
        gS.addSocio(prueba);
    }

    @Test
    public void testAddSocio() throws Exception {
        Socio nuevo = new Socio("12345678K","Pepito","Palotes",true);
        assertThat(gS.size(),is(1));
        gS.addSocio(nuevo);
        assertThat(gS.size(), is(2));
    }

    @Test
    public void testRemoveSocio() throws Exception {
        assertThat(gS.size(), is(1));
        gS.removeSocio("00000");
        assertThat(gS.isEmpty(), is(true));
    }

    @Test
    public void testListSocios() throws Exception {
        List<Socio> lista = gS.listSocios();
        assertThat(lista.size(),is(1));
        Socio nuevo = new Socio("12345678K","Pepito","Palotes",true);
        gS.addSocio(nuevo);
        lista = gS.listSocios();
        assertThat(lista.size(), is(2));
    }

    @Test
    public void testGetSocio() throws Exception {
        assertThat(gS.getSocio("00000"), is(prueba));
        Socio nuevo = new Socio("12345678K","Pepito","Palotes",true);
        gS.addSocio(nuevo);
        assertThat(gS.getSocio("12345678K"), is(nuevo));

    }

    @Test
    public void testIsSocio() throws Exception {
        assertThat(gS.isSocio("00000"), is(true));
        assertThat(gS.isSocio("00001"), is(false));
        assertThat(gS.isSocio("12345678K"), is(false));
        Socio nuevo = new Socio("12345678K","Pepito","Palotes",true);
        gS.addSocio(nuevo);
        assertThat(gS.isSocio("12345678K"), is(true));
        gS.removeSocio("12345678K");
        assertThat(gS.isSocio("12345678K"), is(false));
    }
}