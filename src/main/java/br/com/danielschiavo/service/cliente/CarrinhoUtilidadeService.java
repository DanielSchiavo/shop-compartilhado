package br.com.danielschiavo.service.cliente;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.danielschiavo.repository.cliente.CarrinhoUtilidadeRepository;
import br.com.danielschiavo.shop.model.cliente.Cliente;
import br.com.danielschiavo.shop.model.cliente.carrinho.Carrinho;
import br.com.danielschiavo.shop.model.cliente.carrinho.CarrinhoNaoExisteException;
import br.com.danielschiavo.shop.model.cliente.carrinho.itemcarrinho.ItemCarrinhoException;

@Service
public class CarrinhoUtilidadeService {

	@Autowired
	private CarrinhoUtilidadeRepository carrinhoRepository;
	
	public Carrinho pegarCarrinho(Cliente cliente) {
		return carrinhoRepository.findByCliente(cliente).orElseThrow(() -> {throw new CarrinhoNaoExisteException("Não existe carrinho para esse cliente");});
	}

	public void verificarSeTemItemsNoCarrinho(Cliente cliente, Carrinho carrinho) {
		boolean empty = carrinho.getItemsCarrinho().isEmpty();
		if (empty == true) {
			throw new ItemCarrinhoException("O cliente não tem items no carrinho no momento");
		}
	}

	public String deletarItemsCarrinhoAposPedidoGerado(List<Long> ids, Cliente cliente) {
		Carrinho carrinho = pegarCarrinho(cliente);
		verificarSeTemItemsNoCarrinho(cliente, carrinho);
		int linhasAfetadas = carrinhoRepository.deletarItemsCarrinhoPorListaDeIds(ids, carrinho.getClienteId());
		if (linhasAfetadas > 0) {
			return "Item removido com sucesso";
		} else {
			return "Não existe um produto no carrinho com esse ID";
		}
	}

}
