package vista.gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by ratadp on 3/06/14.
 */
public class Estilo {
    private Color color;
    private Font sansS;
    private Font mono;
    private Image icono;

    public Estilo() {
        this.color = new Color(246,246,246);
        this.mono = new Font("Monospaced",Font.PLAIN,10);
        this.sansS = new Font("SansSerif",Font.PLAIN,10);
        this.icono = new ImageIcon(getClass().getResource("/resources/icon.png")).getImage();
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

    public Image getIcon() {
        return icono;
    }
}