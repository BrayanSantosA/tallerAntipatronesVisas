package correccion.tramite;

/** Unica responsabilidad: avisar por correo que la visa va en camino. */
public class NotificadorDeVisa {
    private static final String FORMATO = "[NotificadorDeVisa] Correo para %s: su visa va en camino%n";

    public void avisarVisaEnCamino(String nombre) {
        System.out.printf(FORMATO, nombre);
    }
}
