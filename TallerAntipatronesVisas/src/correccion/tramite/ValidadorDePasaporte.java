package correccion.tramite;

/** Unica responsabilidad: decidir si un pasaporte es valido. */
public class ValidadorDePasaporte {
    private static final int LONGITUD_MINIMA = 6;
    private static final String ETIQUETA = "[ValidadorDePasaporte] ";
    private static final String MSG_VALIDO = "Pasaporte valido";
    private static final String MSG_INVALIDO = "Pasaporte invalido";

    public boolean esValido(String pasaporte) {
        boolean valido = pasaporte != null && pasaporte.length() >= LONGITUD_MINIMA;
        System.out.println(ETIQUETA + (valido ? MSG_VALIDO : MSG_INVALIDO));
        return valido;
    }
}
