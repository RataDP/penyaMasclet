package vista.gui;

import java.awt.*;

/**
 * Created by ratadp on 3/06/14.
 */
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