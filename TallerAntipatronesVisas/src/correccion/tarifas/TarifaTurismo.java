package correccion.tarifas;

public class TarifaTurismo extends TarifaPorFactor {
    private static final double FACTOR_TURISMO = 1.0;

    public TarifaTurismo(PrecioBase precioBase) {
        super(precioBase);
    }

    @Override
    protected double factor() {
        return FACTOR_TURISMO;
    }
}
