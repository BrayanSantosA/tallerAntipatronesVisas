package correccion.tarifas;

public class TarifaEstudio extends TarifaPorFactor {
    private static final double FACTOR_ESTUDIO = 0.5;

    public TarifaEstudio(PrecioBase precioBase) {
        super(precioBase);
    }

    @Override
    protected double factor() {
        return FACTOR_ESTUDIO;
    }
}
