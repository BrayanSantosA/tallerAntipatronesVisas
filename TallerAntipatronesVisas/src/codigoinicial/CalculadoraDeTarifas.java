package codigoinicial;

/**
 * ANTIPATRON: Copy-Paste Programming. La misma formula esta copiada tres veces.
 * (En el original el 50 era un literal dentro de cada metodo; aqui son tres campos
 * independientes solo para poder demostrar cuantos lugares hay que editar.)
 */
public class CalculadoraDeTarifas {
    private double baseTurista = 50;
    private double baseEstudiante = 50;
    private double baseTrabajo = 50;
    private int lugaresEditados = 0;

    public void editarBaseTurista(double nueva) { baseTurista = nueva; lugaresEditados++; }
    public void editarBaseEstudiante(double nueva) { baseEstudiante = nueva; lugaresEditados++; }
    public void editarBaseTrabajo(double nueva) { baseTrabajo = nueva; lugaresEditados++; }
    public int getLugaresEditados() { return lugaresEditados; }

    public double visaTurista(int meses) {
        double total = baseTurista * meses;
        total = total + 10;
        return total;
    }

    public double visaEstudiante(int meses) {
        double total = baseEstudiante * meses;
        total = total + 10;
        return total * 0.5;
    }

    public double visaTrabajo(int meses) {
        double total = baseTrabajo * meses;
        total = total + 10;
        return total * 1.5;
    }
}
