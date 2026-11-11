# La Oficina de Visas del Caos


## Cómo compilar y ejecutar
Desde la carpeta `tallerAntipatronesVisas`:
```
mkdir out
javac -d out $(find src -name '*.java')
java -cp out app.Main
```


## Cacería de antipatrones
| Fragmento | Antipatrón | Síntoma que lo delata | Consecuencia en el consulado |
|---|---|---|---|
| 1. `OficinaDeTodo` | **God Object** | Una sola clase valida el pasaporte, cobra, guarda el expediente y envía el correo. | Si Ramiro falta o esa clase falla, todo el consulado se detiene; no se puede probar ni cambiar una tarea sin tocar las demás. |
| 2. `CalculadoraDeTarifas` | **Copy-Paste Programming** | `50 * meses` y `+ 10` repetidos en tres métodos. | Al subir la tarifa se corrigieron dos copias y la tercera quedó con el precio viejo: cobros inconsistentes. |
| 3. `EvaluadorMagico` | **Magic Numbers** | `tipo == 3`, `edad > 17`, `< 66`, `0.5`: números sin nombre. | Nadie confirma qué significan; un tipo inválido (9) se rechaza en silencio y los cambios de regla son riesgosos. |
| 4. `SolicitudAcoplada` | **Acoplamiento fuerte** | Crea con `new` los tres servicios y los llama uno por uno dentro de `cancelar()`. | Un viernes se olvidaron de la caja y a un viajero se le perdió el reembolso; agregar un aviso obliga a editar la solicitud. |
1.
<img width="605" height="202" alt="image" src="https://github.com/user-attachments/assets/5c9e54d2-976e-4635-b60c-485c83321c49" />
2.
<img width="378" height="308" alt="image" src="https://github.com/user-attachments/assets/c2f66fdd-a1eb-4989-a731-9c08c3e6e170" />
3.
<img width="572" height="171" alt="image" src="https://github.com/user-attachments/assets/f4927313-7a5d-4e2f-8bf6-55296b619b3b" />
4.
<img width="537" height="227" alt="image" src="https://github.com/user-attachments/assets/aefb824f-5c5c-47d8-871d-6d6e02de8802" />


## Parte 3. Salida en consola de los 5 casos

<img width="777" height="769" alt="image" src="https://github.com/user-attachments/assets/bb6d5def-44c6-4beb-936b-ebc8bae116d9" />
<img width="814" height="237" alt="image" src="https://github.com/user-attachments/assets/bbe23cd7-0991-4d24-9bec-179d59842dc7" />


## ¿Cómo la solución evita volver a caer en los antipatrones?
Cada clase nueva tiene una sola razón para cambiar (`ValidadorDePasaporte`, `CobradorDeTarifa`, etc.), y `TramiteDeVisa` solo coordina el orden sin hacer el trabajo, así que no nace un nuevo Ramiro. La fórmula de precio vive una vez en `PrecioBase` y la tarifa base en `ConfiguracionDeTarifas`: subirla es un único cambio, y cada tipo de visa solo aporta su factor mediante Strategy. Los códigos y rangos de edad ahora tienen nombre en el enum `TipoVisa` y en `ReglasDeEdad`, y un tipo desconocido lanza un error con la lista de tipos válidos en lugar de rechazarse en silencio. Con Observer, `SolicitudDeVisa` no conoce a quién avisar: agregar un aviso nuevo es crear un observador y suscribirlo, sin tocar la solicitud, y ya no se puede "olvidar" la caja.
