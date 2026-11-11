package correccion.solicitudes;

public class AvisoACaja implements ObservadorDeSolicitud {
    private static final String FORMATO = "[AvisoACaja] Reembolso procesado para %s%n";
    private int reembolsos = 0;

    @Override
    public void alCancelarse(SolicitudDeVisa solicitud) {
        reembolsos++;
        System.out.printf(FORMATO, solicitud.getViajero());
    }

    public int getReembolsos() {
        return reembolsos;
    }
}
