package app;

import codigoinicial.*;
import correccion.solicitudes.*;
import correccion.tarifas.*;
import correccion.tramite.*;
import correccion.visas.*;

public class Main {
    private static final String NOMBRE = "Ana Torres";
    private static final String PASAPORTE = "AB123456";
    private static final int MESES = 3;
    private static final double BASE_NUEVA = 60;
    private static final int EDAD_PRUEBA = 20;
    private static final int CODIGO_ESTUDIANTE = 2;
    private static final int CODIGO_INVALIDO = 9;

    private static final TablaDeAntipatrones TABLA = new TablaDeAntipatrones();

    public static void main(String[] args) {
        caso1GodObject();
        caso2CopyPaste();
        caso3MagicNumbers();
        caso4Acoplamiento();
        caso5Integracion();
    }

    private static void titulo(String texto) {
        System.out.println();
        System.out.println("=== " + texto + " ===");
    }

    private static void caso1GodObject() {
        titulo("CASO 1 - God Object");
        OficinaDeTodo ramiro = new OficinaDeTodo();
        System.out.println("--- Version HEREDADA ---");
        boolean aprobadaLegada = ramiro.procesar(NOMBRE, PASAPORTE, 50);
        System.out.println("--- Version RESCATADA ---");
        TramiteDeVisa tramite = new TramiteDeVisa(new ValidadorDePasaporte(), new CobradorDeTarifa(),
                new RepositorioDeExpedientes(), new NotificadorDeVisa());
        boolean aprobadaNueva = tramite.tramitar(NOMBRE, PASAPORTE, 50);
        System.out.println("Ambas aprueban: " + (aprobadaLegada && aprobadaNueva));
        System.out.println("Tareas hechas por OficinaDeTodo (1 sola clase): " + ramiro.getTareasEjecutadas());
        if (aprobadaLegada && aprobadaNueva && ramiro.getTareasEjecutadas() > 1) {
            TABLA.registrar("God Object", "OficinaDeTodo hace " + ramiro.getTareasEjecutadas() + " tareas sola",
                    "Responsabilidad unica: 1 clase por tarea");
        }
    }

    private static void caso2CopyPaste() {
        titulo("CASO 2 - Copy-Paste Programming");
        CalculadoraDeTarifas clonada = new CalculadoraDeTarifas();
        ConfiguracionDeTarifas config = new ConfiguracionDeTarifas();
        CatalogoDeTarifas strategy = new CatalogoDeTarifas(config);

        clonada.editarBaseTurista(BASE_NUEVA);
        clonada.editarBaseEstudiante(BASE_NUEVA);
        clonada.editarBaseTrabajo(BASE_NUEVA);
        config.cambiarTarifaBase(BASE_NUEVA);

        System.out.println("Base 50 -> 60. Lugares editados en la CLONADA: " + clonada.getLugaresEditados());
        System.out.println("Lugares editados en STRATEGY: " + config.getEdicionesRealizadas());
        double[] legado = {clonada.visaTurista(MESES), clonada.visaEstudiante(MESES), clonada.visaTrabajo(MESES)};
        double[] nuevo = {strategy.precio(TipoVisa.TURISMO, MESES), strategy.precio(TipoVisa.ESTUDIANTE, MESES),
                strategy.precio(TipoVisa.TRABAJO, MESES)};
        boolean coinciden = true;
        for (int i = 0; i < legado.length; i++) {
            coinciden &= legado[i] == nuevo[i];
        }
        System.out.printf("Turismo: %.1f vs %.1f | Estudio: %.1f vs %.1f | Trabajo: %.1f vs %.1f%n",
                legado[0], nuevo[0], legado[1], nuevo[1], legado[2], nuevo[2]);
        System.out.println("Precios coinciden: " + coinciden);

        CalculadoraDeTarifas descuidada = new CalculadoraDeTarifas();
        descuidada.editarBaseTurista(BASE_NUEVA);
        descuidada.editarBaseEstudiante(BASE_NUEVA); // se "olvido" la de trabajo
        System.out.printf("Si se olvida 1 copia, visa de trabajo cuesta %.1f en vez de %.1f%n",
                descuidada.visaTrabajo(MESES), nuevo[2]);
        if (coinciden && clonada.getLugaresEditados() == 3 && config.getEdicionesRealizadas() == 1) {
            TABLA.registrar("Copy-Paste Programming", "Subir la base obliga a editar 3 lugares",
                    "Strategy + PrecioBase: 1 solo lugar");
        }
    }

    private static void caso3MagicNumbers() {
        titulo("CASO 3 - Magic Numbers");
        String legado = new EvaluadorMagico().evaluar(CODIGO_ESTUDIANTE, EDAD_PRUEBA);
        ResultadoEvaluacion nuevo = new EvaluadorDeVisa().evaluar(TipoVisa.ESTUDIANTE, EDAD_PRUEBA);
        System.out.println("tipo 2, edad 20 -> HEREDADA: " + legado + " | RESCATADA (ESTUDIANTE): " + nuevo);
        String silencio = new EvaluadorMagico().evaluar(CODIGO_INVALIDO, EDAD_PRUEBA);
        System.out.println("tipo 9 -> HEREDADA: " + silencio + " (sin explicacion)");
        boolean mensajeClaro = false;
        try {
            TipoVisa.desdeCodigo(CODIGO_INVALIDO);
        } catch (TipoDeVisaInvalidoException e) {
            System.out.println("tipo 9 -> RESCATADA: " + e.getMessage());
            mensajeClaro = true;
        }
        if (legado.equals(nuevo.name()) && mensajeClaro) {
            TABLA.registrar("Magic Numbers", "tipo == 3, edad > 17: nadie sabe que significan",
                    "Enum TipoVisa + ReglasDeEdad + error claro");
        }
    }

    private static void caso4Acoplamiento() {
        titulo("CASO 4 - Acoplamiento fuerte");
        System.out.println("--- Version HEREDADA ---");
        SolicitudAcoplada legada = new SolicitudAcoplada();
        legada.cancelar();
        boolean reembolsoLegado = legada.getPagos().getReembolsos() > 0;
        System.out.println("Estado: " + legada.getEstado() + " | Reembolso procesado: " + reembolsoLegado
                + (reembolsoLegado ? "" : "  <-- FALLO: se perdio el reembolso"));

        System.out.println("--- Version RESCATADA (Observer) ---");
        SolicitudDeVisa nueva = new SolicitudDeVisa(NOMBRE, TipoVisa.TURISMO);
        AvisoACaja caja = new AvisoACaja();
        nueva.suscribir(new AvisoPorCorreo());
        nueva.suscribir(new AvisoALaAgenda());
        nueva.suscribir(caja);
        nueva.cancelar();
        boolean reembolsoNuevo = caja.getReembolsos() > 0;
        System.out.println("Estado: " + nueva.getEstado() + " | Reembolso procesado: " + reembolsoNuevo);
        if (!reembolsoLegado && reembolsoNuevo) {
            TABLA.registrar("Acoplamiento fuerte", "cancelar() llama a mano; se olvido Pagos",
                    "Observer: la solicitud no conoce a nadie");
        }
    }

    private static void caso5Integracion() {
        titulo("CASO 5 - Integracion (viaje completo, version nueva)");
        SolicitudDeVisa solicitud = new SolicitudDeVisa(NOMBRE, TipoVisa.TRABAJO);
        AvisoACaja caja = new AvisoACaja();
        solicitud.suscribir(new AvisoPorCorreo());
        solicitud.suscribir(new AvisoALaAgenda());
        solicitud.suscribir(caja);
        System.out.println("1. Solicitud creada: " + solicitud.getTipo() + " (" + solicitud.getEstado() + ")");

        ResultadoEvaluacion resultado = new EvaluadorDeVisa().evaluar(solicitud.getTipo(), EDAD_PRUEBA);
        System.out.println("2. Evaluacion (edad " + EDAD_PRUEBA + "): " + resultado);

        double precio = new CatalogoDeTarifas(new ConfiguracionDeTarifas()).precio(solicitud.getTipo(), MESES);
        System.out.println("3. Cobro:");
        TramiteDeVisa tramite = new TramiteDeVisa(new ValidadorDePasaporte(), new CobradorDeTarifa(),
                new RepositorioDeExpedientes(), new NotificadorDeVisa());
        boolean ok = resultado == ResultadoEvaluacion.APROBADA && tramite.tramitar(NOMBRE, PASAPORTE, precio);

        System.out.println("4. Cancelacion:");
        solicitud.cancelar();
        System.out.println("Todo funciona en conjunto: " + (ok && caja.getReembolsos() == 1
                && solicitud.getEstado() == EstadoSolicitud.CANCELADA));

        System.out.println();
        System.out.println("Antipatrones capturados: " + TABLA.capturados() + " de 4");
        TABLA.imprimir();
    }
}
