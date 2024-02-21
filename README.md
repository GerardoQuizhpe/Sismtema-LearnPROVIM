# Sismtema-LearnPROVIM
El presente proyecto trata sobre, una manera de facilitar el desarrollo integral de habilidades en programación de cada usuario, proporcionando una experiencia de aprendizaje personalizada adaptativa mediante vídeos y música, que abarque estilo de aprendizaje y el cociente intelectual.
# Requitos y configuraciones para el funcionamiento del proyecto
* Crear una base de datos llamada "estudiantes" y luego importar la base de datos proporcionada
* Descargar archivo jar mysql para poder conectarse a la base de datos (https://downloads.mysql.com/archives/c-j/), y agregarlo a las librerías del proyecto
* Descargar archivo jar jade para poder hacer uso de los agentes (https://jade.tilab.com/), y agregarlo a las librerías del proyecto
* Configurar en las propiedades del proyecto la sección Run, y en el atributo Main Class agregar "jade.Boot" y el atributo Arguments agregar "-gui AgenteRecomendacion:learprovim.AgenteRecomendacion"
# Algoritmo Basado en contenido TF-IDF y similitud de coseno
Este enfoque calcula la similitud entre elementos claves(nivel) en función de las características de contenido (materias) y las preferencias del usuario.
* Cálculo de TF-IDF: Utiliza el algoritmo TF-IDF para calcular la importancia de cada palabra en cada materia, dando una representación vectorial de cada materia en función de sus palabras clave.
* Cálculo de similitud de coseno: Utiliza la similitud de coseno para calcular la similitud entre las características de las materias y las preferencias del usuario. Esto te permitirá determinar el nivel en el que se encuentra el usuario.
* Generación de recomendaciones: Utiliza la similitud del coseno calculada para generar recomendaciones para el usuario basado en el nivel del estudiante.
