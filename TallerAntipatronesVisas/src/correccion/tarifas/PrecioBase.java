package correccion.tarifas;

/** La formula comun (meses * base + cargo fijo) escrita una sola vez. */
public class PrecioBase {
    private final ConfiguracionDeTarifas configuracion;

    public PrecioBase(ConfiguracionDeTarifas configuracion) {
        this.configuracion = configuracion;
    }

    public double calcular(int meses) {
        return configuracion.getTarifaBasePorMes() * meses + configuracion.getCargoFijo();
    }
}
