package br.com.danielschiavo.repository.produto;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.danielschiavo.shop.model.produto.categoria.Categoria;

public interface CategoriaUtilidadeRepository extends JpaRepository <Categoria, Long> {

}
