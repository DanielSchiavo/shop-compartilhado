package br.com.danielschiavo.repository.produto;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.danielschiavo.shop.model.produto.subcategoria.SubCategoria;

public interface SubCategoriaUtilidadeRepository extends JpaRepository<SubCategoria, Long>{
	
}
