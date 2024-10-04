USE [Valknut]
GO

/****** Object:  Table [dbo].[PedidoProducto]    Script Date: 4/10/2024 19:42:40 ******/
SET ANSI_NULLS ON
GO

SET QUOTED_IDENTIFIER ON
GO

CREATE TABLE [dbo].[PedidoProducto](
	[id_pedido] [int] NOT NULL,
	[id_producto] [int] NOT NULL,
	[cantidad] [int] NOT NULL,
 CONSTRAINT [PK_PedidoProducto] PRIMARY KEY CLUSTERED 
(
	[id_pedido] ASC,
	[id_producto] ASC
)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON, OPTIMIZE_FOR_SEQUENTIAL_KEY = OFF) ON [PRIMARY]
) ON [PRIMARY]
GO

ALTER TABLE [dbo].[PedidoProducto]  WITH CHECK ADD  CONSTRAINT [FK_PedidoProducto_PedidoProducto] FOREIGN KEY([id_pedido])
REFERENCES [dbo].[Pedidos] ([id])
GO

ALTER TABLE [dbo].[PedidoProducto] CHECK CONSTRAINT [FK_PedidoProducto_PedidoProducto]
GO

ALTER TABLE [dbo].[PedidoProducto]  WITH CHECK ADD  CONSTRAINT [FK_PedidoProducto_Productos] FOREIGN KEY([id_producto])
REFERENCES [dbo].[Productos] ([id])
GO

ALTER TABLE [dbo].[PedidoProducto] CHECK CONSTRAINT [FK_PedidoProducto_Productos]
GO

