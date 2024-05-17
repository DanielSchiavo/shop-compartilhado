package br.com.danielschiavo.service.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.danielschiavo.repository.cliente.ClienteUtilidadeRepository;
import br.com.danielschiavo.shop.model.ValidacaoException;
import br.com.danielschiavo.shop.model.cliente.Cliente;
import lombok.Setter;

@Service
@Setter
public class ClienteUtilidadeService {
	
	@Autowired
	private ClienteUtilidadeRepository clienteRepository;
	
	public Cliente pegarClientePorId(Long id) {
		return clienteRepository.findById(id).orElseThrow(() -> new ValidacaoException("Não existe um cliente com o id " + id));
	}
	
}
