package correccion.solicitudes;

public class AvisoPorCorreo implements ObservadorDeSolicitud {
    private static final String FORMATO = "[AvisoPorCorreo] Correo de cancelacion enviado a %s%n";

    @Override
    public void alCancelarse(SolicitudDeVisa solicitud) {
        System.out.printf(FORMATO, solicitud.getViajero());
    }
}
