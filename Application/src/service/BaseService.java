package service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class BaseService<T> {
    protected List<T> dataStore = new ArrayList<>(); // Simulación de almacenamiento en memoria
    
    public void guardar(T entidad) {
        dataStore.add(entidad);
    }
    
    public void modificar(int id,T entidad) {
        if (id >= 0 && id < dataStore.size()) {
            dataStore.set(id, entidad);
        }
    }

    public void eliminar(T entidad) {
        dataStore.remove(entidad);
    }

    public Optional<T> buscarPorId(int id) {
        // Este método debe ser implementado en las subclases, ya que depende del tipo de entidad
        throw new UnsupportedOperationException("Método buscarPorId no implementado");
    }

    public List<T> listar() {
    	return dataStore;
    	//return new ArrayList<>(dataStore);
    }
}
