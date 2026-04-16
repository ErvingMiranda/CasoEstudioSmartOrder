# CasoEstudioSmartOrder

Proyecto en Java desarrollado para resolver el caso de estudio de **SmartOrder** aplicando Programación Orientada a Objetos y una organización basada en principios **SOLID**.

## Descripción

SmartOrder es un sistema de consola que permite administrar:

- clientes
- productos
- pedidos
- pagos
- reportes de ventas

El sistema fue diseñado para ser más ordenado, mantenible y extensible, separando claramente los modelos, la lógica del negocio, el acceso a datos y la interacción con el usuario.

## Funcionalidades principales

### Clientes
- Registrar clientes con nombre, teléfono y dirección
- Consultar clientes registrados
- Editar clientes
- Eliminar clientes

### Productos
- Registrar productos con nombre, precio y disponibilidad
- Consultar productos registrados
- Editar productos
- Eliminar productos

### Pedidos
- Crear pedidos asociados a un cliente
- Agregar múltiples productos a un pedido
- Eliminar productos de un pedido antes de pagarlo
- Calcular el total automáticamente
- Eliminar pedidos

### Pagos
- Procesar pagos de pedidos
- Métodos de pago actuales:
    - efectivo
    - tarjeta
- Posibilidad de agregar nuevos métodos de pago en el futuro

### Reportes
- Mostrar cantidad de pedidos realizados
- Mostrar total de ventas

## Estructura del proyecto

El proyecto está organizado en paquetes según la responsabilidad de cada clase:

- `models`: entidades principales del sistema
- `interfaces`: contratos para repositorios y métodos de pago
- `repository`: almacenamiento en memoria
- `services`: lógica del negocio
- `payments`: implementaciones concretas de pago
- `views`: menú e interacción por consola
- `utils`: utilidades compartidas
- `Main`: punto de entrada del programa

## Cómo funciona

Al iniciar el programa, se muestra un menú principal con estas opciones:

- Administrar clientes
- Administrar productos
- Administrar pedidos
- Ver reportes
- Salir

Cada módulo permite realizar sus operaciones correspondientes.

### Flujo general de uso
1. Registrar clientes
2. Registrar productos
3. Crear un pedido seleccionando un cliente
4. Agregar uno o más productos al pedido
5. Si se desea, eliminar productos antes de finalizar
6. Seleccionar método de pago
7. Ver reportes de ventas y pedidos

## Clases principales

### Modelos
- `Cliente`: representa a un cliente
- `Producto`: representa un producto disponible para la venta
- `ItemPedido`: representa un producto junto con su cantidad dentro de un pedido
- `Pedido`: representa un pedido realizado por un cliente
- `ResultadoPago`: representa el resultado de un pago

### Interfaces
- `MetodoPago`: contrato para procesar pagos
- `RepositorioClientes`: contrato para manejo de clientes
- `RepositorioProductos`: contrato para manejo de productos
- `RepositorioPedidos`: contrato para manejo de pedidos

### Servicios
- `ServicioClientes`: lógica relacionada con clientes
- `ServicioProductos`: lógica relacionada con productos
- `ServicioPedidos`: lógica relacionada con pedidos, pagos y reportes

## Uso de identificadores automáticos

El sistema genera identificadores automáticamente usando una clase utilitaria llamada `GeneradorId`, que crea IDs cortos basados en UUID.

Esto evita que el usuario tenga que escribir manualmente el identificador de cada cliente, producto o pedido.

## Aplicación de SOLID

En el proyecto se aplican los principios SOLID de la siguiente manera:

- **Single Responsibility Principle**: cada clase tiene una responsabilidad específica
- **Open/Closed Principle**: se pueden agregar nuevos métodos de pago sin modificar la lógica principal
- **Liskov Substitution Principle**: cualquier implementación de `MetodoPago` puede usarse en lugar de otra
- **Interface Segregation Principle**: las interfaces están separadas según su propósito
- **Dependency Inversion Principle**: los servicios dependen de abstracciones y no de implementaciones concretas

## Requerimientos técnicos cumplidos

- Uso de clases bien definidas
- Uso de encapsulación
- Uso de abstracción
- Uso de interfaces
- Uso de clase estática de utilidad (`GeneradorId`)
- Organización clara del código

## Ejecución

Para ejecutar el proyecto:

1. Abrir el proyecto en IntelliJ IDEA o en otro IDE compatible con Java
2. Ejecutar la clase `Main`
3. Interactuar con el sistema desde el menú en consola

## Posibles mejoras futuras

- Persistencia en archivos o base de datos
- Interfaz gráfica
- Validaciones más avanzadas
- Reportes más detallados
- Nuevos métodos de pago