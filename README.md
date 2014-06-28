Penya El Masclet
============

![logo](/GestioSocios/src/resources/logo.png)

Aplicación para la gestión de los socios de la Penya El Masclet.
La aplicación esta compilada con ~~Java8~~ Java7.

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
- [x] Funcionamiento básico de la aplicación.
- [x] Interfaz para el guardado y carga de ficheros.
- [x] Opciones sobre el listado.
    - [x] Listado de menores.
    - [x] Número total de socios.
- [x] Añadir icono a la aplicación.
- [x] Funcionamiento completo de la aplicación.
- [ ] Programa completo!

============
##Aspeto
En el módulo hay una clase abstracta de la cual extienden todas las ventanas, tiene 3 métodos, una con el color usado de fondo y dos tipos de fonts, proximamente añadire más para mejorar la apariencia de la aplicación.
```java
public abstract class Estilo {
    public Estilo();
    public Color getColorFondo();
    public Font getSansS();
    public Font getMono();
    public Image getIcon();
}
```
