package service;

import model.EnumTipoProducto;
import model.Producto;

import java.math.BigDecimal;
import java.util.Optional;

public class ProductoService extends BaseService<Producto> {
    private static ProductoService instancia;

    public static ProductoService getInstancia() {
        if (instancia == null) {
            instancia = new ProductoService();
        }
        return instancia;
    }
    
	public ProductoService(){
		//Agregamos valores hardcoded por ahora.
	    dataStore.add(new Producto(1, "Pizza Margarita", "Pizza clásica con tomate, muzarella y albahaca", new BigDecimal("12.50"), EnumTipoProducto.COMIDA));
	    dataStore.add(new Producto(2, "Pizza Peperoni", "Pizza con muzarella y rodajas de pepperoni", new BigDecimal("14.00"), EnumTipoProducto.COMIDA));        
	    dataStore.add(new Producto(3, "Pizza Vegetariana", "Pizza con vegetales frescos y muzarella", new BigDecimal("13.50"), EnumTipoProducto.COMIDA));        
	    dataStore.add(new Producto(4, "Bebida Cola", "Bebida gaseosa sabor cola de 500ml", new BigDecimal("2.50"), EnumTipoProducto.BEBIDA));        
	    dataStore.add(new Producto(5, "Agua Mineral", "Agua mineral sin gas de 500ml", new BigDecimal("1.50"), EnumTipoProducto.BEBIDA));        
	}
	
    @Override
    public Optional<Producto> buscarPorId(int id) {
        // Buscar el producto en la lista en memoria usando el ID
        return dataStore.stream().filter(producto -> producto.obtenerId() == id).findFirst();
    }

    // Método para buscar un producto por nombre
    public Optional<Producto> buscarPorNombre(String nombre) {
        return dataStore.stream().filter(producto -> producto.obtenerNombre().equalsIgnoreCase(nombre)).findFirst();
    }

}
