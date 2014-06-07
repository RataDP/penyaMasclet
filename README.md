Penya El Masclet
============

![logo](/GestioSocios/src/resources/logo.png)

Aplicación para la gestión de los socios de la Penya El Masclet.

============
##Task
- [x] Diseño básico de toda la aplicación.
- [x] Mensaje de bienvenida.
- [x] Apariencia básica de la lista y modificación de socios. 
- [x] MenuBar de la lista y modificación de socios.
- [x] Apariencia completa de la lista y modificación de socios.
- [x] Guardado y cargado de datos básicos.
- [x] Básico sobre la gestión de pagos.
- [x] Apariencia completa de gestión de pagos.
- [ ] Interfaz para el guardado y carga de ficheros.
- [ ] Funcionamiento completo de la aplicación.
- [ ] Añadir años a la lista de socios.
- [ ] Funcionamiento de los años.
- [ ] Programa completo!

============
##Aspeto
En el módulo hay una clase abstracta de la cual extienden todas las ventanas, tiene 3 métodos, una con el color usado de fondo y dos tipos de fonts, proximamente añadire más para mejorar la apariencia de la aplicación.
```java
public abstract class Estilo {
    private Color color;
    private Font sansS;
    private Font mono;

    public Estilo() {
        this.color = new Color(246,246,246);
        this.mono = new Font("Monospaced",Font.PLAIN,10);
        this.sansS = new Font("SansSerif",Font.PLAIN,10);
    }

    public Color getColorFondo() {
        return color;
    }

    public Font getSansS() {
        return sansS;
    }

    public Font getMono() {
        return mono;
    }
}
```
