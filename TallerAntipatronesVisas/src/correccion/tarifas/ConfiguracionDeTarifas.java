package correccion.tarifas;

/** Unico lugar donde vive la tarifa base y el cargo fijo. */
public class ConfiguracionDeTarifas {
    private static final double TARIFA_BASE_INICIAL = 50;
    private static final double CARGO_FIJO = 10;

    private double tarifaBasePorMes = TARIFA_BASE_INICIAL;
    private int edicionesRealizadas = 0;

    public double getTarifaBasePorMes() { return tarifaBasePorMes; }
    public double getCargoFijo() { return CARGO_FIJO; }
    public int getEdicionesRealizadas() { return edicionesRealizadas; }

    public void cambiarTarifaBase(double nueva) {
        tarifaBasePorMes = nueva;
        edicionesRealizadas++;
    }
}
