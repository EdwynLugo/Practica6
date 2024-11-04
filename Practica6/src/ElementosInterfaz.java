import javax.swing.*;
import java.awt.*;

public class ElementosInterfaz extends JPanel {
    private String direccionImagen;
    private int posicionX;
    private int posicionY;
    private int ancho;
    private int alto;
    private String valor;

    public ElementosInterfaz(String direccionImagen, int posicionX, int posicionY, String valor) {
        this.direccionImagen = direccionImagen;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.ancho = 70;
        this.alto = 70;
        this.valor = valor;
    }

    public ElementosInterfaz(String direccionImagen, int posicionX, int posicionY, int ancho, int alto, String valor) {
        this.direccionImagen = direccionImagen;
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.ancho = ancho;
        this.alto = alto;
        this.valor = valor;
    }

    public int getPosicionX() {
        return posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public int getAncho() {
        return ancho;
    }

    public int getAlto() {
        return alto;
    }

    public String getValor() {
        return valor;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Image img = new ImageIcon(direccionImagen).getImage();
        g.drawImage(img, posicionX, posicionY, ancho, alto, this);
    }
}
