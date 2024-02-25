# Reto Ratatouille

Este reto representa una simulación básica de un restaurante donde se gestionan las mesas, los camareros, los clientes y los menús disponibles.

## Funcionalidades

- **Gestión de Mesas**: Cada mesa puede ser ocupada por clientes, quienes realizan pedidos de comida y eventualmente desocupan la mesa.

- **Gestión de Camareros**: Los camareros se encargan de servir los platos a las mesas, recoger los pedidos y reaccionar a eventos como la presencia de una rata en el restaurante.

- **Menús y Comida**: El restaurante ofrece una variedad de menús compuestos por primeros platos, segundos platos y postres, que los clientes pueden elegir al ocupar una mesa.

## Estructura del Reto


- **Clases Base**:
  
    - `Mesa`: Modela las mesas del restaurante, incluyendo su ocupación y gestión de pedidos.
    - `Camarero`: Gestiona las acciones y movimientos de los camareros dentro del restaurante.
    - `Comida`: Administra los menús disponibles y genera pedidos aleatorios para los clientes.
- **Clases de gestion**:
   
  - `Restaurante`: Representa el restaurante en su totalidad y coordina todas las operaciones.
