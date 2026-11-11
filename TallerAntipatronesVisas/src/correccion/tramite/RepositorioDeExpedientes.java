package correccion.tramite;

/** Unica responsabilidad: guardar el expediente. */
public class RepositorioDeExpedientes {
    private static final String FORMATO = "[RepositorioDeExpedientes] Guardando expediente de %s%n";

    public void guardar(String nombre) {
        System.out.printf(FORMATO, nombre);
    }
}
