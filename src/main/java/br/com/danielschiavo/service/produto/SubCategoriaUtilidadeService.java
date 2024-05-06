package br.com.danielschiavo.service.produto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.danielschiavo.repository.produto.SubCategoriaRepository;
import br.com.danielschiavo.shop.model.ValidacaoException;
import br.com.danielschiavo.shop.model.produto.subcategoria.SubCategoria;

@Service
public class SubCategoriaUtilidadeService {

	@Autowired
	private SubCategoriaRepository subCategoriaRepository;
	
	public SubCategoria verificarSeExisteSubCategoriaPorId(Long idSubCategoria) {
		return subCategoriaRepository.findById(idSubCategoria).orElseThrow(() -> new ValidacaoException("Não existe categoria com o id " + idSubCategoria));
	}

}
