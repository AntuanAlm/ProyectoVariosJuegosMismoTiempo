public class Tablerito {
    public static String[][] contenidoTablero;

    Tablerito(int tablero) {
        contenidoTablero = createTablero(tablero);
    }
    public String[][] getContenidoTablero() {
        return contenidoTablero;
    }

    private String[][] createTablero(int tablero) {
        String[][] tableroAux = new String[tablero][tablero];
        for (int i = 0; i < tableroAux.length; i++) {
            for (int j = 0; j < tableroAux[i].length; j++) {
                tableroAux[i][j] = "";
            }
        }
        return tableroAux;
    }

    public void imprimirTablero() {
        String contenido = "";
        for (int i = 0; i < contenidoTablero.length; i++) {
            for (int j = 0; j < contenidoTablero[i].length; j++) {

                contenido = contenidoTablero[i][j];

                switch (contenido) {
                    case "1":
                        contenido = "X";
                        break;
                    case "2":
                        contenido = "O";
                        break;
                    default:
                        contenido = "*";
                        break;
                }

                System.out.print(contenido + " ");
            }
            System.out.println();
        }
    }


    public boolean comprobarPoscioValida(int fila, int columna) {
        if (fila < 1 ||
                fila > contenidoTablero.length ||
                columna < 1 ||
                columna > contenidoTablero.length ||
                contenidoTablero[fila -1 ][columna -1 ] == null) {
            return false;
        }

        return contenidoTablero[fila - 1][columna - 1].equals("");
    }

    public void introducirFicha(int fila, int columna, Jugador jugador) {
        contenidoTablero[fila - 1][columna - 1] = jugador.getFicha() + "";
    }
}

