package br.com.danielschiavo.repository.produto;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.danielschiavo.shop.model.produto.Produto;

public interface ProdutoUtilidadeRepository extends JpaRepository<Produto, Long> {

	Optional<Produto> findByIdAndAtivoTrue(Long id);

}
