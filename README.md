# Certant-Challenge

## Develop by Joaquin Labrador

## EndPoints

### Rutas http://localhost:8080 para autos

* "GET" - "/cars" - Retorna todos los autos
* "GET" - "/cars/{carLicensePlate}" - Retorna un auto por su patente
* "POST" - "/cars" - Crea un auto
* "PUT" - "/cars - Actualiza un auto por su patente, en el body se debe enviar el auto con los datos a actualizar
* "DELETE" - "/cars/{carLicensePlate}" - Elimina un auto por su patente
* **Caso de Uso: • La lista de autos aptos, condicionales y rechazados.**
  "GET" - "/carsByStatus/{status}" - Retorna todos los autos por el estado de su inspección de la VTV

### Rutas http://localhost:8080 para clientes

* "GET" - "/clients" - Retorna todos los clientes
* "GET" - "/clients/{dni}" - Retorna un cliente por su dni
* "POST" - "/clients" - Crea un cliente
* "PUT" - "/clients - Actualiza un cliente por su dni, en el body se debe enviar el cliente con los datos a actualizar
* "DELETE" - "/clients/{dni}" - Elimina un cliente por su dni

### Rutas http://localhost:8080 para inspecciones

* "GET" - "/inspections" - Retorna todas las inspecciones
* "GET" - "/inspections/{id}" - Retorna una inspeccion por su id
* "POST" - "/inspections" - Crea una inspeccion
* "PUT" - "/inspections - Actualiza una inspeccion por su id, en el body se debe enviar la inspeccion con los datos a
  actualizar
* "DELETE" - "/inspections/{id}" - Elimina una inspeccion por su id

### Rutas http://localhost:8080 para inspectores

* "GET" - "/inspectors" - Retorna todos los inspectores
* "GET" - "/inspectors/{id}" - Retorna un inspector por su id
* "POST" - "/inspectors" - Crea un inspector
* "PUT" - "/inspectors - Actualiza un inspector por su id, en el body se debe enviar el inspector con los datos a
  actualizar
* "DELETE" - "/inspectors/{id}" - Elimina un inspector por su id

# Base de datos:

La base de datos utilizada para este proyecto es MySQL, se debe crear una base de datos con el nombre "vtv-dv"
y en el archivo "application.properties" se debe configurar el usuario y contraseña de la base de datos.

````aplication.properties
spring.datasource.url=jdbc:mysql://localhost:3306/vtv-db
spring.datasource.username=root
````

# Diagrama de entidad relacion:

* 1 a N Coches con Inspecciones 
* 1 a N Clientes con Coches 
* 1 a N Inspectores con Inspecciones
* N a N Coches con Inspectores **Tabla intermedia Inspecciones**

        