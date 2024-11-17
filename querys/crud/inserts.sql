--INICIO INSERTS
INSERT INTO [dbo].[Usuarios] (nombre, apellido, email, password, sexo, rol) VALUES ('Juan', 'Perez', 'admin@valknut.com', 'admin123', 'MASCULINO', 'ADMINISTRADOR');
INSERT INTO [dbo].[Usuarios] (nombre, apellido, email, password, sexo, rol) VALUES ('Juan', 'Perez', 'juan.perez@valknut.com', '123456', 'MASCULINO', 'EMPLEADO');
--
INSERT INTO [dbo].[Productos] (nombre, descripcion, precio, tipo) VALUES ('Pizza Margarita', 'Pizza clasica con tomate, albahaca y mozzarella', 8.99, 'COMIDA');
--
INSERT INTO [dbo].[Clientes] (nombre, apellido, telefono, direccion, localidad) VALUES ('Mateo', 'Garcia', '555-1234', 'Calle Falsa 123', 'Ciudad Pizzeria');
--
INSERT INTO [dbo].[Pedidos] (id_usuario, id_cliente, costoTotal, formaPago, fechaInicio, fechafin, estado) VALUES (1, 1, 25.50, 'EFECTIVO', '2024-10-01', '2024-10-01', 'CREADO');
--
INSERT INTO [dbo].[PedidoProducto] (id_pedido, id_producto, cantidad) VALUES (1, 1, 2); -- Pedido 1, Producto 1, con cantidad 2
--FIN INSERTS
