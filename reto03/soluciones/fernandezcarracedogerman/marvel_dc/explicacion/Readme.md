<table>
<tr>
<td><img src="jl_peq.png"></td>
<td align="center"><strong style="font-size:35px;">Super Hero DAWleaks</td>
<td><img src="av_peq.png"></td>
</tr>
</table>

## Solución de: Germán Fernández Carracedo
### GitHub: germangfc

<div style="text-align: justify">

## Solución dada
Se ha optado por una solución diseñada mediante una arquitectura Modelo Vista Controlador, con dos repositorios distintos, uno para cada base de datos (que implementan un mismo interfaz genérico de operaciones), dos controladores que llevan la lógica de cada repositorio (y que a su vez implementan un mismo interfaz genérico de operaciones) y una interfaz de usuario, que comunica con cada controlador para solicitar y enviar información a los repositorios.

También se ha implementado una clase Validador, para controlar que la entrada de datos del usuario sea correcta.

Además, se ha implementado un sistema de errores adaptado al dominio, concretamente dos sealed classes: una para errores relacionados con los datos de los superhéroes y otro relacionado con las operaciones propias de los repositorios.

### Pendiente de Implementar
#### Validaciones
Pendiente implementar el validador.

#### Tests
Pendientes de implementar.

#### Password
Debemos considerar dónde se almacenará la contraseña. Nos piden una única contraseña para el interfaz del sistema, y por tanto, el mismo nos serviría para acceder a las dos bases de datos.
Sin embargo, nuestro sistema guarda las bases de datos en dos repositorios separados, por lo que tendríamos que pensar una solución. He pensado dos opciones:
- Replicar la contraseña (la misma además) en ambos repositorios y luego comparar con una de ellas al verificar la entrada.
- Hacer un repositorio + controlador propios, solo para guardar la contraseña, y mantener el mismo modelo Repositorio-Controlador-Interfaz, para solicitarla al usuario, pasarla a través del controlador al repositorio y verificarla allí.
Así, la contraseña del sistema no "viajaría" fuera de su repositorio e incrementaríamos la seguridad.

La segunda opción me parece más lógica y es la que está pendiente de implementar.


</div>
