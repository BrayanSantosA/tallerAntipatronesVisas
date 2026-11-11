package codigoinicial;

/** ANTIPATRON: Acoplamiento fuerte. Crea y llama a mano a cada servicio (y se olvida de Pagos). */
public class SolicitudAcoplada {
    private String estado = "ACTIVA";
    private CorreoServicio correo = new CorreoServicio();
    private CitasServicio citas = new CitasServicio();
    private PagosServicio pagos = new PagosServicio();

    public void cancelar() {
        estado = "CANCELADA";
        correo.avisarCancelacion();
        citas.liberarCupo();
        // Un viernes alguien olvido: pagos.reembolsar();
    }

    public String getEstado() { return estado; }
    public PagosServicio getPagos() { return pagos; }
}
