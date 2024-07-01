package com.example.demo_app.controllers;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo_app.dto.Cliente;
import com.example.demo_app.services.ClienteServiceImpl;


@RestController
@RequestMapping("/api/clientes")
@CrossOrigin(origins = "*")
public class ClienteController {
	
	@Autowired
	public ClienteServiceImpl clienteServiceImpl;
	
	
	
	@GetMapping("/all")
	public List<Cliente> getAllClientes(){
		return clienteServiceImpl.getAllClientes();
	}
	
	@GetMapping("/{id}")
	public Cliente getClienteById(@PathVariable(name="id") Long id) {
		Optional<Cliente> cliente = clienteServiceImpl.getClientesById(id);
		if(cliente.isPresent()) {
			return cliente.get();
		} else {
			throw new NoSuchElementException();
		}
	}
	
	
	@PostMapping("/create")
	public Optional<Cliente> createClientes(@RequestBody Cliente cliente){
		Optional<Cliente> newCliente = clienteServiceImpl.createClientes(cliente);
		if(newCliente.isPresent()) {
			return newCliente;
		} else {
		
			throw new NoSuchElementException();
		}

	}
	

	
	@PutMapping("/update/{id}")
	public Cliente updateClientes(@PathVariable(name="id")Long id, @RequestBody Cliente cliente) {
		Cliente updateCliente = clienteServiceImpl.updateClientes(id, cliente);
		return updateCliente;
	}
	
	@DeleteMapping("/delete/{id}")
	public void deleteCliente(@PathVariable(name="id") Long id) {
		clienteServiceImpl.deleteClienteById(id);
	}
	
	
	
	
	
}
