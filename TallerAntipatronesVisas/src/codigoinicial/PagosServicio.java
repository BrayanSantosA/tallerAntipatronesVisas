package codigoinicial;

public class PagosServicio {
    private int reembolsos = 0;

    public void reembolsar() {
        reembolsos++;
        System.out.println("[PagosServicio] Reembolso procesado");
    }

    public int getReembolsos() {
        return reembolsos;
    }
}
