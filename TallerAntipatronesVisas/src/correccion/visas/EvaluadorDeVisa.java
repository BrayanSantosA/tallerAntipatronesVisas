package correccion.visas;

/** Evalua sin condicionales por tipo: cada TipoVisa conoce su propio rango. */
public class EvaluadorDeVisa {
    public ResultadoEvaluacion evaluar(TipoVisa tipo, int edad) {
        return tipo.admiteEdad(edad) ? ResultadoEvaluacion.APROBADA : ResultadoEvaluacion.RECHAZADA;
    }
}
