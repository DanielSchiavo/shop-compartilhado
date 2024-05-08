package br.com.danielschiavo.service.cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.danielschiavo.repository.cliente.CarrinhoRepository;
import br.com.danielschiavo.shop.model.ValidacaoException;
import br.com.danielschiavo.shop.model.cliente.Cliente;
import br.com.danielschiavo.shop.model.cliente.carrinho.Carrinho;

@Service
public class CarrinhoUtilidadeService {

	@Autowired
	private CarrinhoRepository carrinhoRepository;
	
	public Carrinho pegarCarrinho(Cliente cliente) {
		Carrinho carrinho = carrinhoRepository.findByCliente(cliente).get();
		boolean empty = carrinho.getItemsCarrinho().isEmpty();
		if (empty == false) {
			return carrinho;
		} else {
			throw new ValidacaoException("Não existe um carrinho para o cliente de ID número " + cliente.getId());
		}
	}

	public boolean deletarItemsCarrinhoAposPedidoGerado(List<Long> ids, Cliente cliente) {
		Carrinho carrinho = pegarCarrinho(cliente);
		int linhasAfetadas = carrinhoRepository.deletarItemsCarrinhoPorListaDeIds(ids, carrinho.getClienteId());
		return linhasAfetadas > 0;
	}

}
