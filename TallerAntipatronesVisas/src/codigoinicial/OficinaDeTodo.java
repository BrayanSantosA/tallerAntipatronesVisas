package codigoinicial;

/** ANTIPATRON: God Object. Valida, cobra, guarda y notifica en una sola clase ("Ramiro"). */
public class OficinaDeTodo {
    private int tareasEjecutadas = 0;

    public boolean procesar(String nombre, String pasaporte, double precio) {
        if (pasaporte == null || pasaporte.length() < 6) {
            System.out.println("[OficinaDeTodo] Pasaporte invalido");
            return false;
        }
        tareasEjecutadas++;
        System.out.println("[OficinaDeTodo] Valida el pasaporte de " + nombre);
        tareasEjecutadas++;
        System.out.println("[OficinaDeTodo] Cobrando $" + precio + " a " + nombre);
        tareasEjecutadas++;
        System.out.println("[OficinaDeTodo] Guardando expediente de " + nombre);
        tareasEjecutadas++;
        System.out.println("[OficinaDeTodo] Correo para " + nombre + ": su visa va en camino");
        return true;
    }

    public int getTareasEjecutadas() {
        return tareasEjecutadas;
    }
}
