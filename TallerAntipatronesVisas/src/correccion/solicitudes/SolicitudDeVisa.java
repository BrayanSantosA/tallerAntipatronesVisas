package correccion.solicitudes;

import correccion.visas.TipoVisa;
import java.util.ArrayList;
import java.util.List;

/** Subject: avisa a sus observadores sin saber quienes son ni cuantos hay. */
public class SolicitudDeVisa {
    private static final String ERROR_YA_CANCELADA = "La solicitud de %s ya estaba cancelada";

    private final String viajero;
    private final TipoVisa tipo;
    private final List<ObservadorDeSolicitud> observadores = new ArrayList<>();
    private EstadoSolicitud estado = EstadoSolicitud.ACTIVA;

    public SolicitudDeVisa(String viajero, TipoVisa tipo) {
        this.viajero = viajero;
        this.tipo = tipo;
    }

    public void suscribir(ObservadorDeSolicitud observador) {
        observadores.add(observador);
    }

    public void cancelar() {
        if (estado == EstadoSolicitud.CANCELADA) {
            throw new IllegalStateException(String.format(ERROR_YA_CANCELADA, viajero));
        }
        estado = EstadoSolicitud.CANCELADA;
        for (ObservadorDeSolicitud observador : observadores) {
            observador.alCancelarse(this);
        }
    }

    public String getViajero() { return viajero; }
    public TipoVisa getTipo() { return tipo; }
    public EstadoSolicitud getEstado() { return estado; }
}
