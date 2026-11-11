package correccion.tarifas;

import correccion.visas.TipoVisa;
import java.util.EnumMap;
import java.util.Map;

/** Asocia cada TipoVisa con su estrategia. Un tipo nuevo se registra aqui, en una linea. */
public class CatalogoDeTarifas {
    private final Map<TipoVisa, EstrategiaDeTarifa> estrategias = new EnumMap<>(TipoVisa.class);

    public CatalogoDeTarifas(ConfiguracionDeTarifas configuracion) {
        PrecioBase base = new PrecioBase(configuracion);
        estrategias.put(TipoVisa.TURISMO, new TarifaTurismo(base));
        estrategias.put(TipoVisa.ESTUDIANTE, new TarifaEstudio(base));
        estrategias.put(TipoVisa.TRABAJO, new TarifaTrabajo(base));
    }

    public double precio(TipoVisa tipo, int meses) {
        return estrategias.get(tipo).calcular(meses);
    }
}
