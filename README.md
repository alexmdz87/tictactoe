# tictactoe

Solución realizando un proyecto web desde cero, trabajando desde la creacion del arquetipo. Esta solucion es un monolitico con MVC, ya que no interactua con elementos externos. El back-end tiene todas las capas de un proyecto web, y puebas unitarias que se fueron realizando para validar la logica aplicada en la solución, la unica capa con la que no cuenta este proyecto es la capa de BD porque funciona con lógica de probabilidades a partir de cada accion(posicion) que se trasforman en valores. La base del proyecto se realizo desde cero para tener limpieza en el uso de las librerías seleccionadas para esta solución, definitivamente no es un proyecto reciclado, es nuevo cada hoja de codigo, de html y de configuracion basada en estandares de aplicaciones web. Se utilizó especificamente la version de Java 1.8 para el optimo manejo de Colecciones y uso de Lambdas.
Tambien se utilizó Maven para manejo de librerias y capas de codigo que permiten abrir y correr el proyecto en diferentes IDEs y Application Servers. El uso de primefaces solventa el manejo de Bean de Vista para que los datos del tablero de juego se mantengan hasta que la pantalla sea refrescada, adicionalmente proporciona una interfaz de usuario responsiva ya que se maneja librerias de boostrap para controlar diseño.
