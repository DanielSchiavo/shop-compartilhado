package br.com.danielschiavo.service.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.danielschiavo.repository.cliente.ClienteRepository;
import br.com.danielschiavo.shop.model.ValidacaoException;
import br.com.danielschiavo.shop.model.cliente.Cliente;
import lombok.Setter;

@Service
@Setter
public class ClienteUtilidadeService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	public Cliente verificarSeClienteExistePorId(Long id) {
		return clienteRepository.findById(id).orElseThrow(() -> new ValidacaoException("Não existe um cliente com o id " + id));
	}
	
}
