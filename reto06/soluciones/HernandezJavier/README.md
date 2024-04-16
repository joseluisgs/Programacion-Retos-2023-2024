![Zelda](https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/39fd3cf6-e264-4c96-a034-e9e469461bfc/d37gth5-78cda704-e367-4159-ae40-8adf596f841a.png?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7InBhdGgiOiJcL2ZcLzM5ZmQzY2Y2LWUyNjQtNGM5Ni1hMDM0LWU5ZTQ2OTQ2MWJmY1wvZDM3Z3RoNS03OGNkYTcwNC1lMzY3LTQxNTktYWU0MC04YWRmNTk2Zjg0MWEucG5nIn1dXSwiYXVkIjpbInVybjpzZXJ2aWNlOmZpbGUuZG93bmxvYWQiXX0.u4IlpYjk-Qf3QcOFzK_Ny14XDK_3z0GTu2MEVbZ5SoQ)

# Leyendas de Hyrule: El Repositorio de Personajes

Hyrule esta sumida en la oscuridad. Un mal antiguo, encarnado en la forma del temible Ganondorf, ha extendido su influencia por todo el reino. Los valientes héroes se alzan para enfrentarse a las fuerzas del mal y devolver la paz a la tierra.

En medio de esta lucha eterna, surgió la necesidad de una gestión eficiente de los héroes y enemigos que poblaban el vasto mundo de Hyrule. El sabio anciano de Kakariko propuso la creación de un Repositorio de Personajes, un lugar donde se registrarían los valientes guerreros y los siniestros enemigos que surcaban la tierra.

Los Bokoblins, los inquietantes secuaces de Ganondorf, se alzaron desde las profundidades de los bosques oscuros. Liderados por el malvado Bokoblin Rojo y su siniestra contraparte, el Bokoblin Azul, amenazaban la paz de Hyrule. Estos despiadados enemigos heredaron la esencia de la clase base "Enemigo", una plantilla que les otorgaba su naturaleza maligna.

Pero no todo estaba perdido. Link, el valiente héroe de la Trifuerza, se alzaba como una luz en la oscuridad. Con su espada en mano y su valentía inquebrantable, juró proteger a Hyrule hasta su último aliento y se convirtió en el primer héroe registrado en el Repositorio.

Con la creación del Repositorio, la gestión de los personajes se simplificó enormemente. Los valientes guerreros y los despiadados enemigos se registraban en el Repositorio, donde se mantenía un registro de sus nombres y puntos ataque. Desde los campos de batalla hasta los rincones más oscuros de los calabozos, cada acción quedaba grabada en la historia.

Pero la historia no estaría completa sin un registro adecuado de los héroes y villanos. La Princesa Zelda, en su sabiduría, encargó a los sabios de la aldea la creación de funciones para importar/exportar los datos del Repositorio a archivos CSV y JSON. Así, los relatos de los héroes y los enfrentamientos épicos se preservarían para las generaciones venideras, en todas las formas y formatos posibles.

Para complementar el Repositorio, se decide crear una base de datos que almacene los datos de los personajes. Esta base de datos permitirá un acceso más rápido y eficiente a la información, garantizando que las historias de los héroes de Hyrule se conserven por toda la eternidad.

## Objetivos:

1. _Registro de personajes:_ Crear un sistema para registrar tanto a héroes como a enemigos, incluyendo sus nombres, habilidades y atributos como el ataque y la edad.

2. _Exportación de datos:_ Desarrollar funciones para importar/exportar los datos del Repositorio a archivos CSV y JSON, asegurando que los relatos de los héroes y villanos puedan ser preservados en diferentes formatos. Se debe introducir el path válido conocido.

3. _Preservación de historias:_ Garantizar que las historias de los héroes de Hyrule se conserven por toda la eternidad mediante una base de datos que almacene de forma eficiente los datos de los personajes. Es importante conocer el momento de creación y de actualización, así como poder borrarlos lógicamente y fisicaménte.

4. 3. _Rapidez de respuestas:_ Usar una caché de 7 elementos para optimizar el acceso a base de datos al consultar un personaje.

### Consultas:

1. **Tres personajes más mayores:**

   - Seleccionar los tres personajes cuya edad sea la más alta.

2. **Agrupar personajes por tipo de arma:**

   - Agrupar los personajes (tanto héroes como enemigos) según el tipo de arma que utilicen.

3. **Personajes menores de edad:**

   - Seleccionar los personajes que sean menores de edad.

4. **Personajes con más ataque:**

   - Ordenar los personajes (tanto héroes como enemigos) por su atributo de ataque en orden descendente y mostrar los primeros.

5. **Mostrar a todos los enemigos:**

   - Mostrar una lista de todos los enemigos registrados en el Repositorio.

6. **Dos personajes con el ataque más fuerte:**
   - Seleccionar los dos personajes (ya sean héroes o enemigos) con el atributo de ataque más alto.

"_La sombra y la luz son dos lados de la misma moneda… Uno no puede existir sin el otro_." - Princesa Zelda
