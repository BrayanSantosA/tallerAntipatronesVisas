package correccion.tramite;

/** Unica responsabilidad: coordinar el orden del tramite. No hace el trabajo, lo delega. */
public class TramiteDeVisa {
    private final ValidadorDePasaporte validador;
    private final CobradorDeTarifa cobrador;
    private final RepositorioDeExpedientes repositorio;
    private final NotificadorDeVisa notificador;

    public TramiteDeVisa(ValidadorDePasaporte validador, CobradorDeTarifa cobrador,
                         RepositorioDeExpedientes repositorio, NotificadorDeVisa notificador) {
        this.validador = validador;
        this.cobrador = cobrador;
        this.repositorio = repositorio;
        this.notificador = notificador;
    }

    public boolean tramitar(String nombre, String pasaporte, double precio) {
        if (!validador.esValido(pasaporte)) {
            return false;
        }
        cobrador.cobrar(nombre, precio);
        repositorio.guardar(nombre);
        notificador.avisarVisaEnCamino(nombre);
        return true;
    }
}
