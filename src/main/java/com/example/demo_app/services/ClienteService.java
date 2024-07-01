package com.example.demo_app.services;

import java.util.List;
import java.util.Optional;
import com.example.demo_app.dto.Cliente;

public interface ClienteService {

	List<Cliente> getAllClientes();
	Optional<Cliente> getClientesById(Long id);
	Optional<Cliente> createClientes(Cliente cliente);
	Cliente updateClientes(Long id, Cliente cliente);
	void deleteClienteById(Long id);
	
	
}
