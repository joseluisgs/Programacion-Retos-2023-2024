Para este programa lo primero que cree fue la clase Parrilla para que las escuderias pudieran heredar de esta y asi poder crear
la matriz que seria el circuito, cada circuito hereda de su escuderia una funcion que es la caracteristica de su escuderia,
cada piloto tiene su propio estado heredado de parrilla.

Una vez conseguido el movimiento que no deja de ser aumentar en 1 la columna ya que la fila siempre es la misma se implementa el pitstop
que no es mas que un thread sleep al llegar a la columna 5 este metodo luego sera refactorizado para extraer la parte de la parada en
otro llamado esperar para el concepto de mala estrategia de aston martin y ferrari, para la caracteristica de red bull simplemente le 
implementamos la funcion de moverse de nuevo, para mercedes nos creamos la funcion contraria a pilotar() y asi hacerlos retroceder en 
caso de que se cumpla, la ultima en implementar es la de fallo de fiabilidad de Ferrari para la cual le indicamos que el piloto deje de moverse para el resto de la carrera, esta nos servira tambien para la casuistica de accidente, para contar las vueltas tenemos dos maneras, la primera es que al llegar a la columna 9 y volver a la 0 o en el caso de DNF nos fijara las vueltas de ese piloto directamente a 3 para que no tengamos problemas de bucles infinitos, la casuistica de la meteorologia vuelve a ser el metodo de pitstop con la duracion deseada.