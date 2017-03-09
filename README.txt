Sistema de generación de pruebas a partir de patrones software (GoF)
=====================================================================

El objetivo del presente proyecto es el la **generación automática
de pruebas o esqueletos de pruebas, funcionales y no funcionales, para los patrones
de diseño de la ingeniería del software**, también conocidos como patrones GoF.

Para que dichas pruebas sean genéricas,
se abstrajeron de los efectos que el patrón puede tener más allá de su funcionalidad
explícita.
En base a esta abstracción, se ha construido un sistema
en dos capas que permite la generación automática de dichas pruebas. La primera capa del sistema identifica el
patrón (mediante marcadores sobre el código) y las caracterı́sticas necesarias de la implementación concreta (nombres de clases, métodos, etc.), y genera una representación genérica del patrón en XML. La segunda capa del sistema produce las pruebas de forma automatizada a partir de
dicha representación.

Se ha implementado soporte para la **detección de patrones presentes en código Java**, y la **generación de pruebas en JUnit**.
Sin embargo, gracias a la estructura en dos capas y la
representación intermedia diseñadas, el sistema está preparado para ser extendido y soportar
más patrones, lenguajes y tecnologías de prueba.

Uso del sisema
--------------

El sistema de generación de código puede ser ejecutado en su totalidad, o cada módulo por separado. Esto además puede hacerse desde línea de comandos o importándolo como librería en el código.

### Ejecución completa ###

Para ejecutar el sistema en su totalidad por línea de comandos, se deberá ejecutar el jar de la aplicación pasándole como parámetros la ruta donde empezar a buscar recursivamente el código, la ruta donde se desea se depositen las pruebas generadas y bajo qué espacio de nombres han de generarse.

En la alternativa desde código, tras importar la clase `PatternTestGenerator` del espacio de nombres `tfg.sw.patternTestGenerator.generator`, se deberá ejecutar el método estático `+execute (sourceCodeOriginPath: String, sourceCodeResultPath: String, resultPackage: String): void`, el cual recibe los mismos parámetros en el mismo orden.

### Ejecución por separado ###

Para el sistema de análisis de código y generación de la representación intermedia, los parámetros son la ruta donde buscar el código a analizar y el nombre completo (ruta + nombre de fichero) que se le quiere dar al fichero XML con dicha representación. Se ejecuta bien ejecutando el jar, bien importando la clase `Analyzer` del espacio de nombres `tfg.sw.analyzer.analyzer` y ejecutando el método estático `+execute (root: String, destiny: String): void`.
	
Para el sistema de generación de pruebas, los parámetros son el nombre completo (ruta + nombre de fichero) del fichero XML con la representación intermedia, la ruta donde depositar las pruebas generadas y el espacio de nombres bajo el que han de generarse, y se ejecuta bien ejecutando el jar, bien importando la clase `TestMaker` del espacio de nombres `tfg.sw.maker.maker` y ejecutando el método estático `+execute (definitionFile: String, destinyPath: String, destinyPackage: String): void`.