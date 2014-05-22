package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ratadp on 22/05/14.
 */
public class Ventana extends Estilo {
    private JFrame frame;

    public Ventana() {
        super();
        this.frame = new JFrame("App de la penya El Masclet");
        ejecutar();
    }

    private void ejecutar() {
        Bienvenida welcome = new Bienvenida();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("fallo sleep");
        }
        welcome.cerrarVentana();
        new Principal();
    }
    public static void main(String[] args) {
        new Ventana();
    }
}
