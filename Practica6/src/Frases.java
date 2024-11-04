import java.util.ArrayList;
import java.util.Random;

public class Frases {
    private ArrayList<String> listaFrases;

    public Frases() {
        listaFrases = new ArrayList<>();
        incluirFrases();
    }

    private void incluirFrases() {
        listaFrases.add("la luna brilla hoy");
        listaFrases.add("las estrellas son hermosas");
        listaFrases.add("el viento sopla suave");
        listaFrases.add("los pajaros cantan felices");
        listaFrases.add("el rio fluye tranquilo");
        listaFrases.add("las flores son bellas");
        listaFrases.add("los arboles dan sombra");
        listaFrases.add("la lluvia cae lentamente");
        listaFrases.add("el mar esta sereno");
        listaFrases.add("las nubes cubren cielo");
        listaFrases.add("la tarde es hermosa");
        listaFrases.add("el fuego arde calido");
        listaFrases.add("la vida es hermosa");
        listaFrases.add("los animales corren libres");
        listaFrases.add("la musica suena alegre");
        listaFrases.add("las luces brillan intensamente");
        listaFrases.add("el tiempo pasa rapido");
        listaFrases.add("la brisa es fresca");
        listaFrases.add("las olas rompen suaves");
        listaFrases.add("el bosque es profundo");
        listaFrases.add("las hojas caen despacio");
        listaFrases.add("el sol ilumina todo");
        listaFrases.add("el cielo esta despejado");
        listaFrases.add("los peces nadan juntos");
        listaFrases.add("el amanecer es bello");
        listaFrases.add("las montanas se elevan altas");
        listaFrases.add("las aves vuelan libres");
        listaFrases.add("las estrellas brillan claras");
        listaFrases.add("el desierto es vasto");
        listaFrases.add("el campo es sereno");
        listaFrases.add("las nubes flotan ligeras");
        listaFrases.add("el jardin florece colorido");
        listaFrases.add("la brisa es fresca");
        listaFrases.add("las olas rompen suaves");
        listaFrases.add("el bosque es profundo");
        listaFrases.add("las hojas caen despacio");
        listaFrases.add("el sol ilumina todo");
        listaFrases.add("el cielo esta despejado");
        listaFrases.add("los peces nadan juntos");
        listaFrases.add("el amanecer es bello");
        listaFrases.add("las montanas se elevan altas");
        listaFrases.add("las aves vuelan libres");
        listaFrases.add("las estrellas brillan claras");
        listaFrases.add("el desierto es vasto");
        listaFrases.add("el campo es sereno");
        listaFrases.add("las nubes flotan ligeras");
        listaFrases.add("el jardin florece colorido");
        listaFrases.add("el sol se oculta lento");
        listaFrases.add("las hojas susurran suaves");
        listaFrases.add("el cielo brilla claro");
    }

    public String elegirFrase() {
        Random random = new Random();
        int indice = random.nextInt(listaFrases.size());
        return listaFrases.get(indice);
    }
}