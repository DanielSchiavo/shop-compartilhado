package br.com.danielschiavo.service.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.danielschiavo.repository.produto.CategoriaRepository;
import br.com.danielschiavo.shop.model.ValidacaoException;
import br.com.danielschiavo.shop.model.produto.categoria.Categoria;

@Service
public class CategoriaUtilidadeService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria verificarSeExisteCategoriaPorId(Long idCategoria) {
		return categoriaRepository.findById(idCategoria).orElseThrow(() -> new ValidacaoException("Não existe categoria com o id " + idCategoria));
	}
	
}
