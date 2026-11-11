package correccion.tarifas;

/** Esqueleto comun: precio base por el factor propio de cada tipo de visa. */
public abstract class TarifaPorFactor implements EstrategiaDeTarifa {
    private final PrecioBase precioBase;

    protected TarifaPorFactor(PrecioBase precioBase) {
        this.precioBase = precioBase;
    }

    protected abstract double factor();

    @Override
    public double calcular(int meses) {
        return precioBase.calcular(meses) * factor();
    }
}
