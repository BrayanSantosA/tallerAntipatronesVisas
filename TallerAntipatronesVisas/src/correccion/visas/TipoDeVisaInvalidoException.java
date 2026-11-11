package correccion.visas;

public class TipoDeVisaInvalidoException extends RuntimeException {
    public TipoDeVisaInvalidoException(String mensaje) {
        super(mensaje);
    }
}
