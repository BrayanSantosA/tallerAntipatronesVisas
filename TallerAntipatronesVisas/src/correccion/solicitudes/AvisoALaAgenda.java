package correccion.solicitudes;

public class AvisoALaAgenda implements ObservadorDeSolicitud {
    private static final String FORMATO = "[AvisoALaAgenda] Cupo liberado para %s%n";

    @Override
    public void alCancelarse(SolicitudDeVisa solicitud) {
        System.out.printf(FORMATO, solicitud.getViajero());
    }
}
