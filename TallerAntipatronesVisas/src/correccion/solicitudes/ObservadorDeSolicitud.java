package correccion.solicitudes;

/** Observer: quien quiera enterarse de una cancelacion implementa esta interfaz. */
public interface ObservadorDeSolicitud {
    void alCancelarse(SolicitudDeVisa solicitud);
}
