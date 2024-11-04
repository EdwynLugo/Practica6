import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Interfaz extends JFrame {
    private String DireccionElementos = "F:/Elementos";
    private ArrayList<ElementosInterfaz> elementos;
    private String textoPrincipal = "";
    private String textoSecundario = "";
    private String frase = "";
    private String puntos = "";
    private Ahorcado ahorcado;

    public Interfaz(Ahorcado ahorcado) {
        setTitle("Practica 6 - Ahorcado");
        setSize(1366, 768);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        elementos = new ArrayList<>();
        this.ahorcado = ahorcado;

        String direccionFondoTexto = DireccionElementos + "/FondoTexto.png";
        elementos.add(new ElementosInterfaz(direccionFondoTexto, 230, 90, 900, 220, "Fondo"));

        String direccionPuntos = DireccionElementos + "/Puntos.png";
        elementos.add(new ElementosInterfaz(direccionPuntos, 845, 115, 230, 50, "Fondo"));

        String direccionFondoTeclado = DireccionElementos + "/FondoTeclado.png";
        elementos.add(new ElementosInterfaz(direccionFondoTeclado, 230, 330, 900, 370, "Fondo"));

        int posicionX = 285;
        int posicionY = 360;

        for (int numero = 1; numero <= 9; numero++) {
            String direccionImagen = DireccionElementos + "/" + numero + ".png";
            elementos.add(new ElementosInterfaz(direccionImagen, posicionX, posicionY, String.valueOf(numero)));
            posicionX += 80;
        }

        String direccionCero = DireccionElementos + "/0.png";
        elementos.add(new ElementosInterfaz(direccionCero, posicionX, posicionY, "0"));

        String[] qwerty = {
                "Q", "W", "E", "R", "T", "Y", "U", "I", "O", "P",
                "A", "S", "D", "F", "G", "H", "J", "K", "L", "Borrar",
                "Z", "X", "C", "V", "B", "N", "M", "Espacio", "Enter"
        };

        posicionX -= 720;
        posicionY += 80;

        label:
        for (String letra : qwerty) {
            String direccionImagen = DireccionElementos + "/" + letra + ".png";

            switch (letra) {
                case "A":
                    posicionX -= 800;
                    posicionY += 80;
                    break;
                case "Borrar":
                    elementos.add(new ElementosInterfaz(direccionImagen, posicionX, posicionY, "Borrar"));
                    continue;
                case "Z":
                    posicionX -= 720;
                    posicionY += 80;
                    break;
                case "Enter":
                    elementos.add(new ElementosInterfaz(direccionImagen, posicionX, posicionY, 150, 70, "Enter"));
                    break label;
            }
            elementos.add(new ElementosInterfaz(direccionImagen, posicionX, posicionY, letra));
            posicionX += 80;
        }

        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(new Color(220, 220, 220));
                g.fillRect(0, 0, getWidth(), getHeight());

                for (ElementosInterfaz letra : elementos) {
                    if ("Fondo".equals(letra.getValor())) {
                        letra.paintComponent(g);
                    }
                }

                Font fontTurno = new Font("Arial", Font.BOLD, 30);
                Font fontPuntos = new Font("Arial", Font.BOLD, 28);
                Font fontMensaje = new Font("Arial", Font.BOLD, 30);
                Font fontFrase = new Font("Arial", Font.BOLD, 40);

                int xTextos = 300;
                int yTextoPrincipal = 150;
                
                int xPuntos = 960;
                int yPuntos = 150;
                
                int yTextoSecundario = 210;
                int yFrase = 280;

                int desplazamientoSombra = 1;

                g.setFont(fontTurno);
                g.setColor(Color.BLACK);
                g.drawString(textoPrincipal, xTextos - desplazamientoSombra, yTextoPrincipal - desplazamientoSombra);
                g.drawString(textoPrincipal, xTextos + desplazamientoSombra, yTextoPrincipal - desplazamientoSombra);
                g.drawString(textoPrincipal, xTextos - desplazamientoSombra, yTextoPrincipal + desplazamientoSombra);
                g.drawString(textoPrincipal, xTextos + desplazamientoSombra, yTextoPrincipal + desplazamientoSombra);
                g.setColor(Color.WHITE);
                g.drawString(textoPrincipal, xTextos, yTextoPrincipal);

                g.setFont(fontMensaje);
                g.setColor(Color.BLACK);
                g.drawString(textoSecundario, xTextos - desplazamientoSombra, yTextoSecundario - desplazamientoSombra);
                g.drawString(textoSecundario, xTextos + desplazamientoSombra, yTextoSecundario - desplazamientoSombra);
                g.drawString(textoSecundario, xTextos - desplazamientoSombra, yTextoSecundario + desplazamientoSombra);
                g.drawString(textoSecundario, xTextos + desplazamientoSombra, yTextoSecundario + desplazamientoSombra);
                g.setColor(Color.WHITE);
                g.drawString(textoSecundario, xTextos, yTextoSecundario);

                g.setFont(fontFrase);
                g.setColor(Color.BLACK);
                g.drawString(frase, xTextos - desplazamientoSombra, yFrase - desplazamientoSombra);
                g.drawString(frase, xTextos + desplazamientoSombra, yFrase - desplazamientoSombra);
                g.drawString(frase, xTextos - desplazamientoSombra, yFrase + desplazamientoSombra);
                g.drawString(frase, xTextos + desplazamientoSombra, yFrase + desplazamientoSombra);
                g.setColor(Color.WHITE);
                g.drawString(frase, xTextos, yFrase);

                g.setFont(fontPuntos);
                g.setColor(Color.BLACK);
                g.drawString(puntos, xPuntos - desplazamientoSombra, yPuntos - desplazamientoSombra);
                g.drawString(puntos, xPuntos + desplazamientoSombra, yPuntos - desplazamientoSombra);
                g.drawString(puntos, xPuntos - desplazamientoSombra, yPuntos + desplazamientoSombra);
                g.drawString(puntos, xPuntos + desplazamientoSombra, yPuntos + desplazamientoSombra);
                g.setColor(Color.WHITE);
                g.drawString(puntos, xPuntos, yPuntos);


                for (ElementosInterfaz letra : elementos) {
                    if (!"Fondo".equals(letra.getValor())) {
                        letra.paintComponent(g);
                    }
                }
            }

        };

        panel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();

                elementos.stream()
                        .filter(tecla -> !"Fondo".equals(tecla.getValor()))
                        .filter(tecla -> x >= tecla.getPosicionX() && x <= tecla.getPosicionX() + tecla.getAncho() &&
                                y >= tecla.getPosicionY() && y <= tecla.getPosicionY() + tecla.getAlto())
                        .findFirst()
                        .ifPresent(tecla -> ahorcado.setTeclaPresionada(tecla.getValor()));
            }
        });
        add(panel);
    }

    public void setTextoPrincipal(String textoPrincipal) {
        this.textoPrincipal = textoPrincipal;
        repaint();
    }

    public void setTextoSecundario(String textoSecundario) {
        this.textoSecundario = textoSecundario;
        repaint();
    }

    public void setFrase(String frase) {
        this.frase = frase;
        repaint();
    }

    public void setPuntos(String puntos) {
        this.puntos = puntos;
        repaint();
    }
}
