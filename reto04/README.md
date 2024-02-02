# Restaurante La Ratatouille

## Germán Fernández

<table>
<tr>
<td><img src="https://ih1.redbubble.net/image.3369554001.7681/bg,f8f8f8-flat,750x,075,f-pad,750x1000,f8f8f8.jpg"></td>
<td align="center"><strong style="font-size:35px;">Restaurante <i>La Ratatouille</i></strong><br>
La rata con mejor gusto culinario de toda Francia ha decidido montar su propio restaurante en París. Ayúdale a ponerlo en marcha, ¡él bastante tiene con cocinar y controlar al pobre Lingüini!</td>
</tr>
</table>

<div style="text-align: justify">

### Objetivo
Nuestro trabajo será simular un turno completo en el restaurante de Remy. Tendremos que mostrar todo el proceso para varios clientes, desde que entran y se sientan hasta que terminan de comer y se marchan, para así encontrar los puntos débiles que pueda tener nuestra idea.

Nuestro restaurante tendrá tres zonas: 
- El salón, con tres mesas, donde se sentarán los clientes que entren. 
- La cocina, donde estará Remy preparando los menús.
- Zona intermedia, donde transitarán los camareros en su locura.

Nos han pedido que mostremos en pantalla las tres zonas, el salón en una sola fila la parte superior, la cocina en una sola fila en la parte inferior, y en el medio, 4 filas para el movimiento de los camareros. 

Habrá exactamente 3 camareros, que tienen asignada su propia mesa, y se moverán en su "pasillo", para no interrumpirse con el resto.

| Mesa 1 | Mesa 2 | Mesa 3 |
| ----------- | ----------- | ----------- |
| &nbsp; | &nbsp; | &nbsp; |
| &nbsp; | &nbsp; | &nbsp; |
| &nbsp; | &nbsp; | Cam 3 |
| &nbsp; | Cam 2 | &nbsp; |
| Cam 1 | &nbsp; | &nbsp; |
| <------- | Cocina | -------> |

El ciclo de un cliente sería:
1. Entra y se sienta. En ese momento, el camarero de su mesa se moverá hasta el cliente para preguntarle qué menú ha elegido, entre los 5 disponibles. El camarero se mueve una casilla por segundo.
2. Con la nota, el camarero va a la cocina a comunicársela al cocinero y quedará esperando a que este termine de preparar la comanda.
3. El cocinero tardará en preparar el plato 3 segundos, y avisará de que tiene el plato disponible. Hay que tener en cuenta que el plato preparado corresponde a una mesa concreta.
4. El camarero irá comprobando si hay platos disponibles y coger el suyo (y no el de otra mesa). Cuando lo tenga, se lo llevará a su mesa.
5. Una vez que lleve el plato al cliente, volverá a su posición junto a la cocina, para esperar al siguiente cliente.
6. El cliente tardará 5 segundos en comer, se levantará y se marchará (tras haber pagado religiosamente el precio del menú).

Se mostrará la simulación en turnos de 1 segundo y durará hasta que hayan entrado y salido 10 clientes.

Tendremos 5 menús distintos. Son cerrados, con primer plato, segundo y postre, con un precio y una valoración por parte de los clientes, por lo que hay mas probabilidad de que el cliente elija a los que tengan mas valoración.

Los clientes entrarán en el restaurante cada cierto tiempo aleatorio, siempre y cuando haya mesa libre.
Son muy exigentes, por lo que si pasan mas de 15 segundos desde que pidieron, protestarán, y si pasan otros 5 mas, gritarán enfadados y se irán.

Como buena familia de roedores, los primos de Remy han acampado en el callejón trasero del restaurante, y de vez en cuando hacen una incursión dentro, a ver si pillan algún manjar que haya caído al suelo.
Aleatoriamente (20%), puede aparecer una rata en la zona donde se mueven los camareros (en uno de los 3 pasillos y en cualquiera de sus filas). Si ocurre que aparece justo a los pies de uno de nuestros camareros, habrá una probabilidad del 10% de que este se asuste y caiga al suelo. En ese caso, si llevaba un plato preparado, tendrá que volver a la cocina y pedirlo de nuevo.

Con una probabilidad menor (5%), la rata se despistará y aparecerá en la fila junto a las mesas de los clientes; estos se pondrán hechos una furia y se largarán sin pagar un euro.


En cada turno y al final de la simulación, debemos mostrar:
- Número de clientes que han entrado hoy
- Número de clientes que se han marchado descontentos
- Recaudación final

</div>
