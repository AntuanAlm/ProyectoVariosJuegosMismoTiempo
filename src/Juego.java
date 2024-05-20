import java.util.Scanner;

public class Juego extends ClaseAbstractaJuegos {
    private boolean terminado = false;
    private Scanner sc = new Scanner(System.in);
    private int turno;
    private Tablerito tablerito;

    @Override
    public void Start(Jugador[] jugadores) {
        turno = 0;
        tablerito = crearTablero();
        imprimirTablero(tablerito);
        Jugar(jugadores);
    }

    private Tablerito crearTablero() {
        System.out.println("Introduce el tamaño del tablero con el que quieres jugar: ");
        int fila = sc.nextInt();

        while (fila <= 1) {
            System.out.println("Introduce un tamaño de tablero posible, para que jueguen dos jugadores");
            fila = sc.nextInt();
        }

        return new Tablerito(fila);
    }

    private void imprimirTablero(Tablerito tablero) {
        tablero.imprimirTablero();
    }

    public int cambiarTurno() {
        return (turno == 0) ? 1 : 0;
    }

    public void setTurno(int turno) {
        this.turno = turno;
    }

    @Override
    public void Jugar(Jugador[] jugadores) {
        Jugador[] turnoJugadores = new Jugador[2];

        if (jugadores[0].getTurno()) {
            turnoJugadores[0] = jugadores[0];
            turnoJugadores[1] = jugadores[1];
        } else {
            turnoJugadores[0] = jugadores[1];
            turnoJugadores[1] = jugadores[0];
        }

        if (turno % 2 == 0) {
            System.out.println("Turno de " + turnoJugadores[0].getNombre());
            tablerito.imprimirTablero();
            introducirFicha(turnoJugadores[0], tablerito);
            tablerito.imprimirTablero();
            terminado = comprobarGanador(tablerito.getContenidoTablero(), turnoJugadores[0]);
            if (terminado) {
                System.out.println("Ha ganado " + turnoJugadores[0]);
                return;
            }

        } else {
            System.out.println("Turno de " + turnoJugadores[1].getNombre());
            tablerito.imprimirTablero();
            introducirFicha(turnoJugadores[1], tablerito);
            tablerito.imprimirTablero();
            terminado = comprobarGanador(tablerito.getContenidoTablero(), turnoJugadores[1]);
            if (terminado) {
                System.out.println("Ha ganado " + turnoJugadores[1]);
                return;
            }
        }
        turno = cambiarTurno();
    }

    private void introducirFicha(Jugador jugador, Tablerito tablerito) {
        while (true) {
            System.out.println("Introduce una posición en la fila en la que quieres poner tu ficha");
            int fila = sc.nextInt();

            System.out.println("Introduce una posición en la columna en la que quieres poner tu ficha");
            int columna = sc.nextInt();

            if (tablerito.comprobarPoscioValida(fila, columna)) {
                tablerito.introducirFicha(fila, columna, jugador);
                return;
            }
            System.out.println("Posiciones no válidas, introducelas otra vez.");
        }
    }

    public boolean comprobarGanador(String[][] tablero, Jugador jugador) {
        if (comprobarHorizontal(tablero, jugador)) {
            return true;
        } else if (comprobarVertical(tablero, jugador)) {
            return true;
        } else if (comprobarDiagonal(tablero, jugador)) {
            return true;
        } else if (comprobarDiagonalInversa(tablero, jugador)) {
            return true;
        }

        return false;
    }

    private boolean comprobarHorizontal(String[][] tablero, Jugador jugador) {
        boolean comprobar = false;
        for (int i = 0; i < tablero.length; i++) { // Este itera sobre la filas
            for (int j = 0; j < tablero[i].length - 1; j++) { // Este itera sobre las fila de esa columna
                String posicionActual = tablero[i][j];
                String posicionSiguiente = tablero[i][j + 1];
                if (!posicionActual.equals(posicionSiguiente) ||
                        posicionActual.equals("")) {
                    comprobar = false;
                    break;
                } else {
                    comprobar = true;
                }
                if (j == tablero.length - 2 && comprobar) {
                    return true;
                }
            }
        }
        return comprobar;
    }

    private boolean comprobarVertical(String[][] tablero, Jugador jugador) {
        boolean comprobar = false;
        for (int i = 0; i < tablero.length; i++) { // Este itera sobre la filas
            for (int j = 0; j < tablero[i].length - 1; j++) { // Este itera sobre las fila de esa columna
                String posicionActual = tablero[j][i];
                String posicionSiguiente = tablero[j + 1][i];
                if (!posicionActual.equals(posicionSiguiente) ||
                        posicionActual.equals("")) {
                    comprobar = false;
                    break;
                } else {
                    comprobar = true;
                }
                if (j == tablero.length - 2 && comprobar) {
                    return true;
                }
            }
        }
        return comprobar;
    }

    private boolean comprobarDiagonal(String[][] tablero, Jugador jugador) {
        boolean comprobar = false;

        for (int i = 0; i < tablero.length; i++) { // Este itera sobre la filas
            for (int j = 0; j < tablero[i].length - 1; j++) { // Este itera sobre las fila de esa columna
                String posicionActual = tablero[i][j];
                while (i < tablero.length - 1) {
                    String posicionSiguiente = tablero[i + 1][j + 1];
                    if (!posicionActual.equals(posicionSiguiente) ||
                            posicionActual.equals("")) {
                        comprobar = false;
                        break;
                    } else {
                        comprobar = true;
                    }
                    if (j == tablero.length - 2 && comprobar) {
                        return true;
                    }
                    i++;
                }
            }
        }
        // --- --- --- --- ---
        for (int i = 0; i < tablero.length; i++) { // Este itera sobre la filas
            for (int j = 0; j < tablero[i].length - 1; j++) { // Este itera sobre las fila de esa columna
                String posicionActual = tablero[j][i];
                while (i < tablero.length - 1) {
                    String posicionSiguiente = tablero[j + 1][i + 1];
                    if (!posicionActual.equals(posicionSiguiente) ||
                            posicionActual.equals("")) {
                        comprobar = false;
                        break;
                    } else {
                        comprobar = true;
                    }
                    if (j == tablero.length - 2 && comprobar) {
                        return true;
                    }
                    i++;
                }
            }
        }

        return comprobar;
    }

    private boolean comprobarDiagonalInversa(String[][] tablero, Jugador jugador) {
        boolean comprobar = false;

        for (int i = 0; i < tablero.length; i++) { // Este itera sobre la filas
            for (int j = tablero[i].length - 1; j > 0; j--) { // Este itera sobre las fila de esa columna
                String posicionActual = tablero[i][j];
                while (i < tablero.length - 1 && j > 0) {
                    String posicionSiguiente = tablero[i + 1][j - 1];
                    if (!posicionActual.equals(posicionSiguiente) ||
                            posicionActual.equals("")) {
                        comprobar = false;
                        break;
                    } else {
                        comprobar = true;
                    }
                    if (j == tablero.length - 2 && comprobar) {
                        return true;
                    }
                    i++;
                    j--;
                }
            }
        }
        return comprobar;
    }

    @Override
    public boolean isTerminado() {
        return terminado;
    }
}

