package correccion.visas;

/** Enum con constantes: el codigo legado y el rango de edad de cada tipo viven aqui. */
public enum TipoVisa {
    TURISMO(1, ReglasDeEdad.EDAD_MAYORIA, ReglasDeEdad.SIN_LIMITE),
    ESTUDIANTE(2, ReglasDeEdad.EDAD_MINIMA_ESTUDIANTE, ReglasDeEdad.EDAD_MAXIMA_ESTUDIANTE),
    TRABAJO(3, ReglasDeEdad.EDAD_MAYORIA, ReglasDeEdad.EDAD_MAXIMA_TRABAJO);

    private static final String FORMATO_VALIDOS = "%d=%s";
    private static final String SEPARADOR = ", ";
    private static final String FORMATO_ERROR = "Tipo de visa desconocido: %d. Tipos validos: %s";

    private final int codigoLegado;
    private final int edadMinima;
    private final int edadMaxima;

    TipoVisa(int codigoLegado, int edadMinima, int edadMaxima) {
        this.codigoLegado = codigoLegado;
        this.edadMinima = edadMinima;
        this.edadMaxima = edadMaxima;
    }

    public boolean admiteEdad(int edad) {
        return edad >= edadMinima && edad <= edadMaxima;
    }

    public static TipoVisa desdeCodigo(int codigo) {
        for (TipoVisa tipo : values()) {
            if (tipo.codigoLegado == codigo) {
                return tipo;
            }
        }
        throw new TipoDeVisaInvalidoException(String.format(FORMATO_ERROR, codigo, listarValidos()));
    }

    private static String listarValidos() {
        StringBuilder validos = new StringBuilder();
        for (TipoVisa tipo : values()) {
            if (validos.length() > 0) {
                validos.append(SEPARADOR);
            }
            validos.append(String.format(FORMATO_VALIDOS, tipo.codigoLegado, tipo.name()));
        }
        return validos.toString();
    }
}
