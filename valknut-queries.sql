--INICIO INSERTS
INSERT INTO [dbo].[Usuarios] (nombre, apellido, email, password, sexo, rol) VALUES ('Juan', 'Pérez', 'juan.perez@valknut.com', '123456', 'MASCULINO', 'ADMINISTRADOR');
--
INSERT INTO [dbo].[Productos] (nombre, descripcion, precio, tipo) VALUES ('Pizza Margarita', 'Pizza clásica con tomate, albahaca y mozzarella', 8.99, 'COMIDA');
--
INSERT INTO [dbo].[Clientes] (nombre, apellido, telefono, direccion, localidad) VALUES ('Mateo', 'García', '555-1234', 'Calle Falsa 123', 'Ciudad Pizzería');
--
INSERT INTO [dbo].[Pedidos] (id_usuario, id_cliente, costoTotal, formaPago, fechaInicio, fechafin, estado) VALUES (1, 1, 25.50, 'EFECTIVO', '2024-10-01', '2024-10-01', 'CREADO');
--
INSERT INTO [dbo].[PedidoProducto] (id_pedido, id_producto, cantidad) VALUES (1, 1, 2); -- Pedido 1, Producto 1, con cantidad 2
--FIN INSERTS

--INICIO SELECTS
SELECT * FROM [dbo].[Usuarios];
--
SELECT * FROM [dbo].[Productos];
--
SELECT * FROM [dbo].[Clientes];
--
SELECT * FROM [dbo].[Pedidos];
--
SELECT * FROM [dbo].[PedidoProducto];
--FIN SELECTS

--INICIO DELETE
--
DELETE FROM [dbo].[Usuarios];
--
DELETE FROM [dbo].[Productos];
--
DELETE FROM [dbo].[Clientes];
--
DELETE FROM [dbo].[Pedidos];
--
DELETE FROM [dbo].[PedidoProducto];
--FIN DELETE
 

