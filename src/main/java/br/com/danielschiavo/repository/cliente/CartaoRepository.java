package br.com.danielschiavo.repository.cliente;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.danielschiavo.shop.model.cliente.Cliente;
import br.com.danielschiavo.shop.model.cliente.cartao.Cartao;
import br.com.danielschiavo.shop.model.cliente.cartao.TipoCartao;

public interface CartaoRepository extends JpaRepository<Cartao, Long>{

	Optional<Cartao> findByNumeroCartaoAndTipoCartaoAndCliente(String numeroCartao, TipoCartao tipoCartao, Cliente cliente);

	Optional<Cartao> findByIdAndCliente(Long idCartao, Cliente cliente);

	List<Cartao> findAllByCliente(Cliente cliente);
}
