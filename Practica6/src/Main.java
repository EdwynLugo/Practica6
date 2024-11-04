import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Ahorcado juego = new Ahorcado();
        juego.parametrosIniciales();
        juego.turnos();
        scanner.close();
    }
}