package app;

import java.util.ArrayList;
import java.util.List;


public class TablaDeAntipatrones {
    private static final String FORMATO_FILA = "| %-22s | %-48s | %-44s |%n";
    private static final String LINEA = "+" + "-".repeat(24) + "+" + "-".repeat(50) + "+" + "-".repeat(46) + "+";

    private record Fila(String antipatron, String sintoma, String solucion) { }

    private final List<Fila> filas = new ArrayList<>();

    public void registrar(String antipatron, String sintoma, String solucion) {
        filas.add(new Fila(antipatron, sintoma, solucion));
    }

    public int capturados() {
        return filas.size();
    }

    public void imprimir() {
        System.out.println(LINEA);
        System.out.printf(FORMATO_FILA, "Antipatron", "Sintoma (evidencia)", "Solucion aplicada");
        System.out.println(LINEA);
        for (Fila f : filas) {
            System.out.printf(FORMATO_FILA, f.antipatron(), f.sintoma(), f.solucion());
        }
        System.out.println(LINEA);
    }
}
