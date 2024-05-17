package br.com.danielschiavo.mapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.coyote.BadRequestException;
import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import br.com.danielschiavo.feign.produto.FileStorageProdutoComumFallback;
import br.com.danielschiavo.feign.produto.FileStorageProdutoComumServiceClient;
import br.com.danielschiavo.service.produto.ProdutoUtilidadeService;
import br.com.danielschiavo.shop.model.filestorage.ArquivoInfoDTO;
import br.com.danielschiavo.shop.model.pedido.dto.ProdutoPedidoDTO;
import br.com.danielschiavo.shop.model.produto.Produto;
import br.com.danielschiavo.shop.model.produto.dto.DetalharProdutoDTO;
import br.com.danielschiavo.shop.model.produto.dto.MostrarProdutosDTO;

@Mapper(componentModel = "spring")
public abstract class ProdutoComumMapper {
	
	@Mapping(target = "primeiraImagem", expression = "java(primeiraImagem(produto, fileStorageProdutoComumServiceClient, produtoUtilidadeService))")
	public abstract MostrarProdutosDTO produtoParaMostrarProdutosDTO(Produto produto, @Context FileStorageProdutoComumServiceClient fileStorageProdutoComumServiceClient, @Context ProdutoUtilidadeService produtoUtilidadeService);
	
    public byte[] primeiraImagem(Produto produto, @Context FileStorageProdutoComumServiceClient fileStorageProdutoComumServiceClient, @Context ProdutoUtilidadeService produtoUtilidadeService) {
        String primeiraImagem = produtoUtilidadeService.pegarNomePrimeiraImagem(produto);
        
		return fileStorageProdutoComumServiceClient.pegarArquivoProduto(primeiraImagem).bytesArquivo();
    }
    
    public String primeiraImagem(Produto produto, ProdutoUtilidadeService produtoUtilidadeService) {
        return produtoUtilidadeService.pegarNomePrimeiraImagem(produto);
    }
    
    @Mapping(target = "subCategoria", source = "subCategoriaId")
    @Mapping(target = "arquivos", ignore = true)
    public abstract DetalharProdutoDTO produtoParaDetalharProdutoDTO(Produto produto, @Context FileStorageProdutoComumServiceClient fileStorageProdutoService, @Context ProdutoUtilidadeService produtoUtilidadeService);
    
    @AfterMapping
    public void arquivosProduto(@MappingTarget DetalharProdutoDTO.DetalharProdutoDTOBuilder dto, Produto produto, @Context FileStorageProdutoComumServiceClient fileStorageProdutoService, @Context ProdutoUtilidadeService produtoUtilidadeService) {
        List<String> nomeTodosArquivos = produtoUtilidadeService.pegarNomeTodosArquivos(produto);
        try {
        	List<ArquivoInfoDTO> todosArquivos = fileStorageProdutoService.pegarArquivosProduto(nomeTodosArquivos);
        	dto.arquivos(todosArquivos);
		} catch (Exception e) {
		    Throwable causaRaiz = e.getCause();
		    if (causaRaiz instanceof NotFoundException) {
				System.out.println("FILE STORAGE PRODUTO - FORA DO AR - ERRO 404");
		    } else if (causaRaiz instanceof BadRequestException) {
		    	System.out.println("FILE STORAGE PRODUTO - BAD REQUEST - ERRO 400");
		    }
		    dto.arquivos(new FileStorageProdutoComumFallback().pegarArquivosProduto(nomeTodosArquivos));
		}
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
