package br.com.danielschiavo.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import br.com.danielschiavo.service.produto.ProdutoUtilidadeService;
import br.com.danielschiavo.shop.model.filestorage.ArquivoInfoDTO;
import br.com.danielschiavo.shop.model.pedido.dto.ProdutoPedidoDTO;
import br.com.danielschiavo.shop.model.produto.Produto;
import br.com.danielschiavo.shop.model.produto.dto.DetalharProdutoDTO;
import br.com.danielschiavo.shop.model.produto.dto.MostrarProdutosDTO;

@Mapper(componentModel = "spring")
public abstract class ProdutoComumMapper {
	
	@Mapping(target = "primeiraImagem", expression = "java(primeiraImagem(produto, produtoUtilidadeService))")
	public abstract MostrarProdutosDTO produtoParaMostrarProdutosDTO(Produto produto, @Context ProdutoUtilidadeService produtoUtilidadeService);
	
    public String primeiraImagem(Produto produto, ProdutoUtilidadeService produtoUtilidadeService) {
        return produtoUtilidadeService.pegarNomePrimeiraImagem(produto);
    }
    
    @Mapping(target = "subCategoria", source = "subCategoriaId")
    @Mapping(target = "arquivos", ignore = true)
    public abstract DetalharProdutoDTO produtoParaDetalharProdutoDTO(Produto produto, @Context ProdutoUtilidadeService produtoUtilidadeService);
    
    @AfterMapping
    public void arquivosProduto(@MappingTarget DetalharProdutoDTO.DetalharProdutoDTOBuilder dto, Produto produto, @Context ProdutoUtilidadeService produtoUtilidadeService) {
    	List<ArquivoInfoDTO> arquivosMapeados = produto.getArquivosProduto().stream()
    													.map(arquivo -> ArquivoInfoDTO.comNomeEPosicao(arquivo.getNome(), arquivo.getPosicao()))
    													.collect(Collectors.toList());

		dto.arquivos(arquivosMapeados);
    }

	public List<ProdutoPedidoDTO> listaProdutosParaListaProdutosPedidoDTO(List<Produto> produtos, ProdutoUtilidadeService produtoUtilidadeService) {
        List<ProdutoPedidoDTO> resultado = new ArrayList<>();
        for (Produto produto : produtos) {
            resultado.add(produtoParaProdutoPedidoDTO(produto, produtoUtilidadeService));
        }
        return resultado;
	}
	
	@Mapping(target = "primeiraImagem", expression = "java(primeiraImagem(produto, produtoUtilidadeService))")
	@Mapping(target = "produtoId", source = "produto.id")
	public abstract ProdutoPedidoDTO produtoParaProdutoPedidoDTO(Produto produto, ProdutoUtilidadeService produtoUtilidadeService);
}
