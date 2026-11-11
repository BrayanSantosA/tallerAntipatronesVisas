package correccion.tramite;

/** Unica responsabilidad: cobrar la tarifa. */
public class CobradorDeTarifa {
    private static final String FORMATO = "[CobradorDeTarifa] Cobrando $%.1f a %s%n";

    public void cobrar(String nombre, double precio) {
        System.out.printf(FORMATO, precio, nombre);
    }
}
