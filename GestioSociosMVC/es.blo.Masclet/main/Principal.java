package main;

import controlador.ImplementacionControlador;
import modelo.ImplementacionModelo;
import vista.ImplementacionVista;

import javax.swing.*;

/**
 * Created by ratadp on 3/06/14.
 */
public class Principal {
    public static void main(String[] args) {
        ImplementacionControlador controlador = new ImplementacionControlador();
        ImplementacionVista vista = new ImplementacionVista();
        ImplementacionModelo modelo = new ImplementacionModelo();

        controlador.setModelo(modelo);
        controlador.setVista(vista);
        modelo.setVista(vista);
        vista.setModelo(modelo);
        vista.setControlador(controlador);
        try
        {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch(Exception e){
        }
        vista.crearGUI();
    }
}
