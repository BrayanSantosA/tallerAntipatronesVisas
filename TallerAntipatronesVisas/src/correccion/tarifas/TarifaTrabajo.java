package correccion.tarifas;

public class TarifaTrabajo extends TarifaPorFactor {
    private static final double FACTOR_TRABAJO = 1.5;

    public TarifaTrabajo(PrecioBase precioBase) {
        super(precioBase);
    }

    @Override
    protected double factor() {
        return FACTOR_TRABAJO;
    }
}
