# Simulador de Carrera de Fórmula 1

## Descripción
Este programa simula una carrera de Fórmula 1 en un circuito virtual. Se utiliza el lenguaje de programación Kotlin para implementar la lógica del juego. La simulación incluye la participación de varios pilotos, cada uno asociado a una escudería, como Red Bull, Mercedes, Aston Martin, y Ferrari.

## Estructura del Programa
### Clases Principales
- **Carrera**: La clase Carrera representa la competición completa y contiene la lógica principal del juego. Se encarga de colocar los pilotos en la parrilla de salida, avanzarlos por el circuito en cada vuelta, gestionar acciones específicas de los pilotos y controlar las condiciones meteorológicas.

- **Piloto**: La clase base Piloto modela las características generales de un piloto, como nombre, probabilidad de accidente, posición en el circuito, vuelta actual, entre otros. Contiene métodos para realizar acciones comunes a todos los pilotos, como pit stops y movimientos normales.

### Escuderías y Pilotos
- **RedBull, Mercedes, AstonMartin, Ferrari**: Estas son interfaces que representan las escuderías Red Bull, Mercedes, Aston Martin y Ferrari, respectivamente. Cada escudería tiene asociadas acciones específicas que pueden realizar sus pilotos durante la carrera.

### Pilotos Específicos
Se han implementado clases para pilotos específicos asociados a cada escudería:
- **ChecoPerez (Red Bull)**
- **FernandoAlonso (Aston Martin)**
- **Hamilton (Mercedes)**
- **LanceStroll (Aston Martin)**
- **LeClerc (Aston Martin, Ferrari)**
- **MaxVerstappen (Red Bull)**
- **Russel (Mercedes)**
- **Sainz (Aston Martin, Ferrari)**
  Cada piloto tiene características únicas y puede realizar acciones específicas de su escudería durante la carrera.

## Acciones y Eventos
- **Realizar Vuelta Rápida**: Algunos pilotos, como los de Red Bull, pueden realizar una vuelta rápida durante la carrera. Esta acción está condicionada por factores aleatorios y la disponibilidad de espacio en el circuito.

- **Mala Estrategia**: Algunos pilotos, como los de Aston Martin y Ferrari, pueden cometer errores estratégicos y detenerse por 2 segundos durante la carrera.

- **Error Mercedes**: Los pilotos de Mercedes pueden cometer un error durante la carrera, activando el safety car y retrocediendo una posición.

- **Problemas de Fiabilidad**: Algunos pilotos, como LeClerc, pueden experimentar problemas de fiabilidad que los obligan a abandonar la carrera.

## Condiciones Meteorológicas
Se simulan cambios climáticos, como lluvia, que afectan la estrategia de pit stops.

## Ejecución del Programa
Para ejecutar la simulación, se crea una instancia de la clase Carrera y se llama al método iniciarCarrera(). Durante la ejecución, se muestra la posición de los pilotos en el circuito en cada vuelta y se informan eventos especiales.