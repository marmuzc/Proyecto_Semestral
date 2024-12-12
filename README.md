# proyecto_semestral
Sistema de reserva de asientos de autobús - Grupo 3. 

Integrantes: Mariel Muñoz Castillo, Jhostian San Martin Morales 

 

*Imagenes incluidas en la carpeta use_case, interfaz_img, uml 

 

Patrones en la parte lógica:  

Singleton  

El patrón singleton nos garantiza una única instancia, y nosotros queríamos que hubiera solo un administrador que se encargara de la gestión de los recorridos. Así el administrador puede ser básicamente el núcleo de todo el sistema. Usando singleton logramos que en todo el código siempre se use la misma instancia del administrador, facilitando y simplificando la inicialización de Administrador, evitando duplicaciones, y se controla mejor la lista de recorridos, evitando inconsistencias. 

Se encuentra en la clase: Administrador 

 

Simple Factory 

Este patrón se emplea para encapsular la lógica en la creación de objetos, de forma que sea más fácil de mantener el código y también es más fácil crear múltiples objetos que se encuentren relacionado, en nuestro caso, para los asientos, los buses y los recorridos. De esta forma, el factory se encarga de la creación y las otras clases del comportamiento principal 

Se encuentra en las clases: CrearAsientosFactory, CrearBusFactory, CrearRecorridoFactory 

 

Decisiones tomadas: 

Durante el proyecto, comenzamos con decidir de qué forma queríamos implementar la interfaz gráfica, pues queríamos que se viera funcional y simple. Esto fue lo que menos nos costó de hacer. Luego de definir la interfaz, comenzamos a pensar en el funcionamiento interno. 

Claramente, necesitábamos tener de clases buses, sus respectivos asientos y también los recorridos. Para el caso de buses y asientos, seria definir sus pisos y asignarles cierta cantidad de asientos, los cuales definimos con un enum con un precio por el costo de la categoría del dicho asiento. Luego, tuvimos que tomar una decisión con el tema de los recorridos, si es que hacerlos ya con un enum, lo cual sería fácil de implementar, pero no nos parecía del todo lógico, pues resultaba demasiado rígido, deberíamos tener ya ciudades definidas, horas, fechas, etc.  

Es por eso que tomamos la decisión de que sería mejor la creación de un administrador que pudiera encargarse de la creación de los recorridos, teniendo más libertad para elegir las ciudades de origen/destino, los precios que tomarían cada recorrido y también los buses que se añadirían a este, se podría decidir el número de piso, etc.  

Luego de lograr esta implementación, nos dimos cuenta de que se nos hacía más simple también implementar nuestro Administrador con un patrón de diseño Singleton, por las razones que mencionamos anteriormente, igual que implementar un simple Factory para la creación de objetos relacionados. 

 

Problemas encontrados: 

 

Hablando de problemas encontrados, la mayor parte de los problemas los tuvimos al hacer la interfaz gráfica, pues si bien ya teníamos el diseño pensado desde antes, se nos hizo un poco complicado recrearlo y que quedara fiel al prototipo, así que hay algunas cosas que cambiamos. En general estos cambios se dieron también por la rigidez de java swing. 

Además, durante el desarrollo, nos dimos cuenta de varias funcionalidades necesarias en las que no habíamos pensado con anterioridad, como la implementación de una contraseña para el administrador, y nos surgieron complicaciones implementando funcionalidades que el programa debía tener, como la función para poder eliminar una reserva, que finalmente, por falta de tiempo, no pudimos implementar. 

También tuvimos varios problemas con el tema de los plazos, pero eso es netamente por una dificultad al dividir nuestros tiempos y enfocarnos en el proyecto. Aparte de esto, tuvimos también los típicos problemas lógicos, pues, en primer momento, no teníamos mucha seguridad de si crear nosotros los recorridos y asignarles un precio a través del administrador o crearlos con un enum y así, teniendo ciertos kilómetros de viaje, calcular un precio para dichos recorridos. Otra cosa que no pudimos implementar, debido a que nos enfocamos en crear la interfaz gráfica fueron más patrones de diseño, pues cuando ya teníamos todo listo en esta parte, recordamos que podíamos implementar alguno, por ejemplo, builder, pero se nos hizo complicado pues ya teníamos practicamente todo el código completado y añadir nuevas cosas nos comenzó a generar demasiados errores. Finalmente, al decidir tener un administrador, nos pareció lo más correcto que el precio fuera algo que el administrador asignara, al igual que los orígenes/destinos, etc. 

MODO DE USO RAPIDO ANTES DE EMPEZAR: 

Contraseña admin: admin123 

Para hacer funcionar el programa, ejecutar el Main de la carpeta GUI, con ruta: “proyecto_semestral\proyecto_semestral\src\main\java\GUI”. Una vez abierto el programa, se mostrará la pestaña de comprar pasajes, la que no es utilizable sin antes crear un recorrido, para esto se debe ir a la pestaña de crear recorrido e ingresar la contraseña “admin123”, luego se eligen la ciudad de origen y destino, el precio base (a este precio se le sumará el precio por asiento, que varía dependiendo del tipo de asiento) y se ingresa la fecha y la hora en el formato especificado, habiendo hecho esto, se puede crear un recorrido. Luego, en la pestaña de comprar pasaje, se selecciona un recorrido creado y se tienen dos opciones, o comprar un pasaje directamente desde esta pestaña, ingresando el número de asiento y apretando el botón de comprar asiento, o, la otra opción, es ver los asientos en detalle y apretar el asiento que se quiere comprar, lo que mostrará sus detalles y permitirá comprarlo. 