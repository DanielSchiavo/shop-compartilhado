package br.com.danielschiavo.service.produto;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.danielschiavo.repository.produto.ProdutoRepository;
import br.com.danielschiavo.shop.model.ValidacaoException;
import br.com.danielschiavo.shop.model.produto.Produto;
import lombok.Setter;

@Service
@Setter
public class ProdutoUtilidadeService {

	@Autowired
	private ProdutoRepository produtoRepository;

	public Produto pegarProdutoPorIdEAtivoTrue(Long id) {
		return produtoRepository.findByIdAndAtivoTrue(id).orElseThrow(() -> new ValidacaoException("Não existe um produto ativo com o id " + id));
	}
	
	public Produto pegarProdutoPorId(Long id) {
		return produtoRepository.findById(id).orElseThrow(() -> new ValidacaoException("Não existe um produto com o id " + id));
	}
	
	public String pegarNomePrimeiraImagem(Produto produto) {
		return produto.getArquivosProduto().stream().filter(ap -> ap.getPosicao() == (byte) 0).findFirst().get().getNome();
	}
	
	public List<String> pegarNomeTodosArquivos(Produto produto) {
		return produto.getArquivosProduto().stream().map(ap -> ap.getNome()).collect(Collectors.toList());
	}
	
}
