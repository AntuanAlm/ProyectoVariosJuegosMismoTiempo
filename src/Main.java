import java.util.Scanner;

public class Main {
    private static Jugador[] jugadores = new Jugador[2];
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println(".:.:. Bienvenidos al proyecto de Varios Juegos al Mismo Tiempo .:.:.");

        establecerJugadores();
        seleccionarPrimerJugador();
        int numeroDeJuegos = cantidadDeJuegos();

        ClaseAbstractaJuegos[] juegos = new ClaseAbstractaJuegos[numeroDeJuegos];

        for (int i = 0; i < juegos.length; i++) {

        //    System.out.println("Juego número " + (i + 1) + " N en Raya.");
            System.out.println("Introduce el tipo de juego que queréis jugar.");
            System.out.println("1.- NRaya\n2.- Juego de Números Aleatorios.");
            int opcion = sc.nextInt();
            switch (opcion){
                case 1:
                    System.out.println("Habeis elegido el juego NRaya.");
                    juegos[i] = new Juego();
                    break;
                case 2:
                    System.out.println("Habeis elegido el juego de Números Aleatorios.");
                    juegos[i] = new JuegoNumerosAleatorios();
            }
        }

        int contador = 0;
        int ronda = 0;
        do{
            if (ronda == 0) {
                System.out.println("Bienvenido al juego número " + (contador + 1) + ", de " + juegos.length + " juegos !!");
                juegos[contador].Start(jugadores);
            } else {
                juegos[contador].Jugar(jugadores);
            }

            if (contador < juegos.length - 1) {
                contador++;
            } else {
                contador = 0;
                ronda++;
            }
        }while (!juegos[contador].isTerminado());
    }


    private static void establecerJugadores() {
        for (int i = 0; i < 2; i++) {
            System.out.println("Introduce el nombre del jugador " + (i + 1));
            String nombre = sc.nextLine();
            jugadores[i] = new Jugador(nombre);
        }
    }

    private static void seleccionarPrimerJugador() {
        System.out.println("El jugador 1 es : " + jugadores[0].getNombre());
        System.out.println("El jugador 2 es : " + jugadores[1].getNombre());
        System.out.println("¿Qué jugador queréis que empiece?");
        System.out.println("1. " + jugadores[0].getNombre());
        System.out.println("2. " + jugadores[1].getNombre());

        int opcion = sc.nextInt();

        while (opcion < 1 || opcion > 2) {
            System.out.println("Elige un jugador válido.");
            opcion = sc.nextInt();
        }

        switch (opcion) {
            case 1:
                System.out.println(jugadores[0].getNombre() + " empieza primero.");
                jugadores[0].setTurno(true);
                jugadores[0].setFicha(1);
                jugadores[1].setFicha(2);
                break;
            case 2:
                System.out.println(jugadores[1].getNombre() + " empieza primero.");
                jugadores[1].setTurno(true);
                jugadores[1].setFicha(1);
                jugadores[0].setFicha(2);
                break;
        }
    }

    private static int cantidadDeJuegos() {
        System.out.println("¿Cuántos juegos quieres jugar?");
        int numeroDeJuegos = sc.nextInt();

        return numeroDeJuegos;
    }
}
