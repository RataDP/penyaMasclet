package gui;

import controlador.Controlador;

import java.io.*;

/**
 * Created by ratadp on 22/05/14.
 */
public class Main {
    private Controlador controlador;

    public Main() {
        super();
        ejecutar();
    }

    private void ejecutar() {
//        cargarDatos();
//        if (controlador == null)
        controlador = new Controlador();
        Bienvenida welcome = new Bienvenida();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            System.out.println("fallo sleep");
        }
        welcome.cerrarVentana();
        new Ventana(controlador);
//        guardarDatos();
    }

    private void guardarDatos() {
        ObjectOutputStream oos=null;
        try {
            try {
                FileOutputStream fos = new FileOutputStream("basedatos.bin");
                oos = new ObjectOutputStream(fos);
                oos.writeObject(controlador);
            }
            finally {
                oos.close();
            }
        }
        catch(FileNotFoundException exc) {
            System.out.println("El fichero no existe.");
            exc.printStackTrace();
        }
        catch(IOException exc) {
            exc.printStackTrace();
        }

    }

    private void cargarDatos() {
        ObjectInputStream ois = null;
        controlador = null;
        try{
            try {
                FileInputStream fis = new FileInputStream("basedatos.bin");
                ois = new ObjectInputStream(fis);
                controlador = (Controlador)ois.readObject();
            }
            finally {
                if(ois != null) ois.close();
            }
        }
        catch(FileNotFoundException exc) {
            System.err.println("Fichero de datos no existe. Se crea una nueva base de datos.");
        }
        catch(IOException exc) {
            exc.printStackTrace();
        }
        catch(ClassNotFoundException exc) {
            exc.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
