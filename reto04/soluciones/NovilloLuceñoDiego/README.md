#Mi solución del reto 4

##Clases:
Mi proyecto tiene 10 clases:

- <b>Controlador</b>: Es el objeto que controla el funcionamiento del restaurante <br>
- <b>Restaurant</b>: Es la clase que controla el funcionamiento de todo lo que tiene que ver con la matriz dentro de la clase "Restaurant" tiene un <b>Menu</b><br>
- <b>Table</b>: Contiene una serie de estados que determinan su funcionamiento: Incluyendo: <b>Mood</b> y <b>Order</b> (que puede ser nulo)<br>
- <b>Order</b>: tiene un UUID y un <b>MenuItem</b><br>
- <b>Server</b>: Se encarga de todo lo que tiene con el camarero. Tiene una serie de estados que dictan su funcionamiento y contiene, al igual que la clase Table, un "Order" que puede ser nulo<br>
- <b>Mood</b>: Es un enum class que sirve para saber el funcionamiento de <b>Table</b><br>
- <b>Kitchen</b>: Es la clase que contiene los platos que están listos para recoger y contiene un <b>Chef</b><br>
- <b>Chef</b>: Es la clase que se encarga de los platos. Tiene un pedido que está preparando de la clase <b>Order</b> (que puede ser nulo cuando no está preparando nada)<br>
- <b>Menu</b>: Contiene una lista de <b>MenuItems</b> <br>
- <b>MenuItem</b>: Tiene un UUID, un nombre, un precio y una puntuación.<br>
