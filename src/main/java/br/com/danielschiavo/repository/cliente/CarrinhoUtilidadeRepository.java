package br.com.danielschiavo.repository.cliente;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.com.danielschiavo.shop.model.cliente.Cliente;
import br.com.danielschiavo.shop.model.cliente.carrinho.Carrinho;

@Repository
public interface CarrinhoUtilidadeRepository extends JpaRepository <Carrinho, Long>{

	Optional<Carrinho> findByCliente(Cliente cliente);
	
    @Modifying
    @Query("DELETE FROM ItemCarrinho ic WHERE ic.produtoId IN :ids AND ic.carrinho.clienteId = :carrinhoId")
	int deletarItemsCarrinhoPorListaDeIds(List<Long> ids, Long carrinhoId);

}
