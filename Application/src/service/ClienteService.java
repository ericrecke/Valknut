package service;

import model.Cliente;

import java.util.Optional;

public class ClienteService extends BaseService<Cliente> {
    private static ClienteService instancia;

    public static ClienteService getInstancia() {
        if (instancia == null) {
            instancia = new ClienteService();
        }
        return instancia;
    }
    
	public ClienteService(){
		//Agregamos valores hardcoded por ahora.
	    dataStore.add(new Cliente(1, "Juan", "Perez", "11345687", "Calle false 123", "Caseros"));
	    dataStore.add(new Cliente(2, "Mateo", "Gonzales", "11445687", "Calle martinez 123", "San Martin"));        
	}
	
    @Override
    public Optional<Cliente> buscarPorId(int id) {
        return dataStore.stream().filter(cliente -> cliente.obtenerId() == id).findFirst();
    }

    public Optional<Cliente> buscarPorDireccion(String direccion) {
        return dataStore.stream().filter(cliente -> cliente.obtenerDireccion().equalsIgnoreCase(direccion)).findFirst();
    }
}
