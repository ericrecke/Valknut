package service;

import model.Producto;

import java.util.List;
import java.util.Optional;
import service.dao.ProductoDAO;

public class ProductoService {
    private static ProductoService instancia;
    private final ProductoDAO productoDAO;

    // Constructor privado para Singleton
    private ProductoService() {
        this.productoDAO = new ProductoDAO();
    }

    // Singleton
    public static ProductoService getInstancia() {
        if (instancia == null) {
            instancia = new ProductoService();
        }
        return instancia;
    }

    // Obtener todos los productos desde la base de datos
    public List<Producto> obtenerTodos() {
        return productoDAO.listar();
    }

    // Buscar un producto por ID
    public Optional<Producto> buscarPorId(int id) {
        return obtenerTodos().stream()
                .filter(producto -> producto.obtenerId() == id)
                .findFirst();
    }

    // Buscar un producto por nombre
    public Optional<Producto> buscarPorNombre(String nombre) {
        return obtenerTodos().stream()
                .filter(producto -> producto.obtenerNombre().equalsIgnoreCase(nombre))
                .findFirst();
    }

    // Agregar un nuevo producto
    public void agregarProducto(Producto producto) {
        productoDAO.crear(producto);
    }

    // Actualizar un producto existente
    public void actualizarProducto(Producto producto) {
        productoDAO.modificar(producto);
    }

    // Eliminar un producto por ID
    public void eliminarProducto(int id) {
        productoDAO.eliminar(id);
    }
}
