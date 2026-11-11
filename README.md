Taller: La Oficina de Visas del Caos

Tema: Antipatrones de diseño de software y cómo salir de ellos con patrones Integrante(s): (escriba aquí su nombre)

Cómo compilar y ejecutar

Desde la carpeta tallerAntipatronesVisas:

mkdir out
javac -d out $(find src -name '*.java')
java -cp out app.Main
Estructura de paquetes
tallerAntipatronesVisas/
  src/
    codigoinicial/            código heredado (los 4 antipatrones, a propósito)
    correccion/
      tramite/                God Object -> responsabilidad única
      tarifas/                Copy-Paste -> Strategy
      visas/                  Magic Numbers -> enum + constantes + error claro
      solicitudes/            Acoplamiento fuerte -> Observer
    app/                      Main (5 casos) y TablaDeAntipatrones
Parte 1. Cacería de antipatrones
Fragmento	Antipatrón	Síntoma que lo delata	Consecuencia en el consulado
1. OficinaDeTodo	God Object	Una sola clase valida el pasaporte, cobra, guarda el expediente y envía el correo.	Si Ramiro falta o esa clase falla, todo el consulado se detiene; no se puede probar ni cambiar una tarea sin tocar las demás.
2. CalculadoraDeTarifas	Copy-Paste Programming	50 * meses y + 10 repetidos en tres métodos.	Al subir la tarifa se corrigieron dos copias y la tercera quedó con el precio viejo: cobros inconsistentes.
3. EvaluadorMagico	Magic Numbers	tipo == 3, edad > 17, < 66, 0.5: números sin nombre.	Nadie confirma qué significan; un tipo inválido (9) se rechaza en silencio y los cambios de regla son riesgosos.
4. SolicitudAcoplada	Acoplamiento fuerte	Crea con new los tres servicios y los llama uno por uno dentro de cancelar().	Un viernes se olvidaron de la caja y a un viajero se le perdió el reembolso; agregar un aviso obliga a editar la solicitud.
Parte 3. Salida en consola de los 5 casos
<!-- Pegue aquí la captura de pantalla de la ejecución de app.Main. Guarde la imagen como salida_consola.png junto a este README. -->

Salida en consola de los 5 casos de prueba

(Espacio para la captura: ejecute app.Main y pegue la imagen completa, desde el Caso 1 hasta la tabla final.)

Cómo la solución evita volver a caer en los antipatrones

Cada clase nueva tiene una sola razón para cambiar (ValidadorDePasaporte, CobradorDeTarifa, etc.), y TramiteDeVisa solo coordina el orden sin hacer el trabajo, así que no nace un nuevo Ramiro. La fórmula de precio vive una vez en PrecioBase y la tarifa base en ConfiguracionDeTarifas: subirla es un único cambio, y cada tipo de visa solo aporta su factor mediante Strategy. Los códigos y rangos de edad ahora tienen nombre en el enum TipoVisa y en ReglasDeEdad, y un tipo desconocido lanza un error con la lista de tipos válidos en lugar de rechazarse en silencio. Con Observer, SolicitudDeVisa no conoce a quién avisar: agregar un aviso nuevo es crear un observador y suscribirlo, sin tocar la solicitud, y ya no se puede "olvidar" la caja.
