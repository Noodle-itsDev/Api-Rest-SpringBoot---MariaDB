package com.example.demo_app.services;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo_app.dao.ClienteRepository;
import com.example.demo_app.dto.Cliente;




@Service
public class ClienteServiceImpl implements ClienteService{

	@Autowired
	public ClienteRepository clienteRepository;
	Logger logger = LoggerFactory.getLogger(ClienteServiceImpl.class);
	

	@Override
	public List<Cliente> getAllClientes() {
		List<Cliente> clientes = clienteRepository.findAll();
		return clientes;
	}

	@Override
	public Optional<Cliente> getClientesById(Long id) {
		Optional<Cliente> clientes = clienteRepository.findById(id);
		if(clientes.isPresent()) {
			logger.info("Cliente con id " + id + " encontrado.");
			return clientes;
		} else {
			logger.error("Cliente con id " + id + "no encontrado.");
			throw new NoSuchElementException("Cliente incorrecto");
		}

	}

	@Override 
	public Optional<Cliente> createClientes(Cliente cliente) {
		Optional<Cliente> optCliente = Optional.of(cliente);
		
		if(optCliente.isPresent()) {
			clienteRepository.save(cliente);
			logger.info("Se ha creado el cliente correctamente");
			return optCliente;
		} else {
			logger.error("No se han recibido los datos");
			throw new NoSuchElementException("No se han recibido los datos");
		}
		
	}

	
	@Override
	public Cliente updateClientes(Long id, Cliente cliente) {
		Optional<Cliente> optCliente = clienteRepository.findById(id);
		
		if(optCliente.isPresent()) {
			Cliente clienteUpdated = optCliente.get();
			clienteUpdated.setNombre(cliente.getNombre());
			clienteUpdated.setApellidos(cliente.getApellidos());
			clienteUpdated.setDni(cliente.getDni());
			clienteUpdated.setMascota(cliente.getMascota());
			clienteRepository.save(clienteUpdated);
			logger.info("Cliente modificado con éxito.");
			return clienteUpdated;
		} else {
			logger.error("Cliente a modificar no encontrado");
			throw new NoSuchElementException("Cliente a modificar no encontrado.");
		}

	}

	@Override
	public void deleteClienteById(Long id) {
		
		Optional<Cliente> deltCliente = clienteRepository.findById(id);
		
		if(deltCliente.isPresent()) {
			Cliente clienteTrust = deltCliente.get();
			clienteRepository.deleteById(clienteTrust.getId());
			logger.info("Cliente eliminado con éxito.");
		} else {
			logger.error("Cliente a eliminar no encontrado");
		}
		
		
	}
	
}
