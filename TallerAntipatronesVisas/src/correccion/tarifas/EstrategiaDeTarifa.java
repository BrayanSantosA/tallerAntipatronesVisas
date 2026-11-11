package correccion.tarifas;

/** Strategy: cada tipo de visa decide como ajusta el precio base. */
public interface EstrategiaDeTarifa {
    double calcular(int meses);
}
