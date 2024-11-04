import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

class Ahorcado {

    private ArrayList<Jugador> jugadores;
    private int turnoActual;
    private int puntosObjetivo;
    private boolean rondaActiva;
    private HashMap<String, Integer> puntuaciones;
    private Interfaz interfaz;
    private String teclaPresionada;

    public Ahorcado() {
        jugadores = new ArrayList<>();
        puntuaciones = new HashMap<>();
        turnoActual = 0;
        rondaActiva = true;
        interfaz = new Interfaz(this);
        interfaz.setVisible(true);
    }

    public void parametrosIniciales() {
        int numeroJugadores = 0;

        while (numeroJugadores < 2 || numeroJugadores > 4) {
            interfaz.setTextoPrincipal("¿Cuantos jugadores? (de 2 a 4)");
            esperarEntrada();

            try {
                numeroJugadores = Integer.parseInt(teclaPresionada);
                if (numeroJugadores < 2 || numeroJugadores > 4) {
                    interfaz.setTextoSecundario("Ingrese un numero valido de jugadores.");
                    teclaPresionada = null;
                }
            } catch (NumberFormatException e) {
                interfaz.setTextoSecundario("Entrada invalida. Debe ser un numero.");
                teclaPresionada = null;
            }
        }

        teclaPresionada = null;

        for (int i = 1; i <= numeroJugadores; i++) {
            interfaz.setTextoPrincipal("Nombre para el jugador " + i + ": ");
            String nombre = capturarEntradaTexto();
            Jugador jugador = new Jugador(nombre);
            jugadores.add(jugador);
            puntuaciones.put(nombre, 0);
            teclaPresionada = null;
        }

        boolean puntosValidos = false;
        while (!puntosValidos) {
            interfaz.setTextoPrincipal("Ingrese el objetivo de puntos: ");
            String puntosTexto = capturarEntradaTexto();

            try {
                puntosObjetivo = Integer.parseInt(puntosTexto);
                if (puntosObjetivo > 0) {
                    puntosValidos = true;
                } else {
                    interfaz.setTextoSecundario("Ingrese un numero valido de puntos (mayor a 0).");
                    delay(1500);
                }
            } catch (NumberFormatException e) {
                interfaz.setTextoSecundario("Entrada invalida. Debe ser un numero.");
                delay(1500);
            }
        }
    }

    public void turnos() {
        turnoActual = 0;
        while (rondaActiva) {
            String frase = new Frases().elegirFrase();
            HashSet<Character> letrasAdivinadas = new HashSet<>();
            StringBuilder censura = new StringBuilder();

            for (char c : frase.toCharArray()) {
                if (c == ' ') {
                    censura.append(' ');
                } else {
                    censura.append('_');
                }
            }

            boolean rondaTerminada = false;

            while (!rondaTerminada) {
                Jugador jugador = jugadores.get(turnoActual);
                interfaz.setTextoPrincipal("Turno del jugador '" + jugador.getNombre() + "'");
                interfaz.setPuntos(puntuaciones.get(jugador.getNombre()) + "/" + puntosObjetivo);
                interfaz.setFrase("Frase: " + censura);

                String entrada = capturarEntradaTexto().toLowerCase();

                if (entrada.length() == 1) {
                    char letra = entrada.charAt(0);
                    if (letrasAdivinadas.contains(letra)) {
                        interfaz.setTextoSecundario("Ya se ha ingresado '" + letra + "' antes, pierdes 3 puntos.");
                        puntuaciones.put(jugador.getNombre(), puntuaciones.get(jugador.getNombre()) - 3);
                    } else {
                        letrasAdivinadas.add(letra);
                        int contador = 0;

                        for (int i = 0; i < frase.length(); i++) {
                            if (frase.toLowerCase().charAt(i) == letra) {
                                censura.setCharAt(i, letra);
                                contador++;
                            }
                        }

                        if (contador > 0) {
                            String vezOVeces = (contador == 1) ? "vez" : "veces";
                            interfaz.setTextoSecundario("La frase contiene la letra '" + letra + "' " + contador + " " + vezOVeces + ".");
                            puntuaciones.put(jugador.getNombre(), puntuaciones.get(jugador.getNombre()) + (3 * contador));
                        } else {
                            interfaz.setTextoSecundario("La frase NO contiene la letra '" + letra + "', pierdes 1 punto.");
                            puntuaciones.put(jugador.getNombre(), puntuaciones.get(jugador.getNombre()) - 1);
                        }
                    }
                } else {
                    if (entrada.equals(frase.toLowerCase())) {
                        int puntosPorFrase = 5 + contarLetras(frase);
                        interfaz.setTextoSecundario("¡'" + jugador.getNombre() + "' adivino la frase, gana " + puntosPorFrase + " puntos!");
                        puntuaciones.put(jugador.getNombre(), puntuaciones.get(jugador.getNombre()) + puntosPorFrase);
                        delay(1000);
                        rondaTerminada = true;
                    } else {
                        interfaz.setTextoSecundario("Las frases NO son iguales, pierdes 5 puntos.");
                        puntuaciones.put(jugador.getNombre(), puntuaciones.get(jugador.getNombre()) - 5);
                    }
                }

                if (frase.equals(censura.toString())) {
                    int puntosPorFrase = 5;
                    interfaz.setTextoSecundario("¡'" + jugador.getNombre() + "' revelo la frase, gana 5 puntos!");
                    puntuaciones.put(jugador.getNombre(), puntuaciones.get(jugador.getNombre()) + puntosPorFrase);
                    delay(1000);
                    rondaTerminada = true;
                }

                if (puntuaciones.get(jugador.getNombre()) >= puntosObjetivo) {
                    interfaz.setTextoSecundario("¡El jugador '" + jugador.getNombre() + "' ha ganado el juego!");
                    rondaActiva = false;
                    delay(5000);
                    System.exit(0);
                    break;
                }
                delay(1000);
                turnoActual = (turnoActual + 1) % jugadores.size();
            }
        }
    }

    private void esperarEntrada() {
        while (teclaPresionada == null) {
            delay(100);
        }
    }

    private String capturarEntradaTexto() {
        StringBuilder entrada = new StringBuilder();
        interfaz.setTextoSecundario("Ingrese su entrada: ");
        delay(1000);

        Object bloqueo = new Object();
        boolean[] mostrarCursor = {true};

        Thread cursor = new Thread(() -> {
            while (true) {
                synchronized (bloqueo) {
                    interfaz.setTextoSecundario("" + entrada + (mostrarCursor[0] ? "|" : " "));
                    mostrarCursor[0] = !mostrarCursor[0];
                }
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        });

        cursor.start();

        while (true) {
            esperarEntrada();

            synchronized (bloqueo) {
                interfaz.setTextoSecundario(entrada + "|");
            }

            if ("Enter".equals(teclaPresionada)) {
                if (!entrada.isEmpty()) {
                    break;
                }
            } else if ("Borrar".equals(teclaPresionada)) {
                if (!entrada.isEmpty()) {
                    entrada.deleteCharAt(entrada.length() - 1);
                }
            } else {
                entrada.append(teclaPresionada);
            }

            synchronized (bloqueo) {
                interfaz.setTextoSecundario("" + entrada);
            }
            teclaPresionada = null;
        }

        cursor.interrupt();
        try {
            cursor.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        teclaPresionada = null;
        return entrada.toString();
    }

    private void delay(int milliseconds) {
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private int contarLetras(String frase) {
        int contador = 0;
        for (char c : frase.toCharArray()) {
            if (c != ' ') {
                contador++;
            }
        }
        return contador;
    }

    public void setTeclaPresionada(String teclaPresionada) {
        this.teclaPresionada = teclaPresionada;
        if (teclaPresionada.equals("Espacio")) {
            this.teclaPresionada = " ";
        }
    }

}