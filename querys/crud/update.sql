--INICIO UPDATES
UPDATE Usuarios
SET nombre = 'Juan',
    apellido = 'Pérez',
    email = 'juan.perez@ejemplo.com',
    password = 'nuevoPassword123',
    sexo = 'Masculino',
    rol = 'Administrador'
WHERE id = 1;
--
UPDATE Productos
SET nombre = 'Pizza Margherita',
    descripcion = 'Pizza clásica con tomate, mozzarella y albahaca',
    precio = 9.99,
    tipo = 'Comida'
WHERE id = 1;
--
UPDATE Clientes
SET nombre = 'Ana',
    apellido = 'Martínez',
    telefono = '123456789',
    direccion = 'Calle Falsa 123',
    localidad = 'Madrid'
WHERE id = 1;
--
UPDATE Pedidos
SET id_usuario = 1,
    id_cliente = 1,
    costoTotal = 29.99,
    formaPago = 'Tarjeta de crédito',
    fechaInicio = '2024-10-01',
    fechafin = '2024-10-02',
    estado = 'Completado'
WHERE id = 1;
--
UPDATE PedidoProducto
SET id_pedido = 1,
    id_producto = 2
WHERE id_pedido = 1 AND id_producto = 1;
--FIN UPDATES
