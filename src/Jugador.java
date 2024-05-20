public class Jugador {
    private String nombre;

    private int ficha;

    private boolean turno;
    public Jugador(String nombre) {
        this.nombre = nombre;
        this.turno = false; // como ninguno tiene el turno, hasta que se elige es false.
    }

    public String getNombre() {
        return nombre;
    }

    //Getters

    public int getFicha() {
        return ficha;
    }
    public boolean getTurno() { return turno; }
    //Setters

    public boolean setTurno(boolean turno) {
        return this.turno = turno;}

    public void setFicha(int ficha) {
        this.ficha = ficha;
    }

    @Override
    public String toString() {
        return getNombre();
    }
}
