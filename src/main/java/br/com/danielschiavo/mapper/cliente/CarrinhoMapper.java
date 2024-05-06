package br.com.danielschiavo.mapper.cliente;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.mapstruct.AfterMapping;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import br.com.danielschiavo.service.produto.ProdutoUtilidadeService;
import br.com.danielschiavo.shop.model.cliente.carrinho.Carrinho;
import br.com.danielschiavo.shop.model.cliente.carrinho.MostrarCarrinhoClienteDTO;
import br.com.danielschiavo.shop.model.cliente.carrinho.itemcarrinho.ItemCarrinho;
import br.com.danielschiavo.shop.model.cliente.carrinho.itemcarrinho.MostrarItemCarrinhoClienteDTO;
import br.com.danielschiavo.shop.model.produto.Produto;

@Mapper(componentModel = "spring")
public abstract class CarrinhoMapper {

	@Mapping(target = "itemsCarrinho", ignore = true)
	@Mapping(target = "valorTotal", ignore = true) 
	public abstract MostrarCarrinhoClienteDTO carrinhoParaMostrarCarrinhoClienteDTO(Carrinho carrinho);
	
	@AfterMapping
    public void definirValorTotalMostrarCarrinhoClienteDTO(@MappingTarget MostrarCarrinhoClienteDTO.MostrarCarrinhoClienteDTOBuilder dto, @Context FileStorageProdutoService fileService, @Context ProdutoUtilidadeService produtoUtilidadeService, Carrinho carrinho, @Context List<Produto> produtos) {
		List<MostrarItemCarrinhoClienteDTO> lista = new ArrayList<>();
		for (int i = 0; i < produtos.size(); i++) {
			String nomePrimeiraImagem = produtoUtilidadeService.pegarNomePrimeiraImagem(produtos.get(i));
			byte[] imagemProduto = fileService.pegarArquivoProdutoPorNome(nomePrimeiraImagem).bytesArquivo();
			Produto produto = produtos.get(i);
			var item = new MostrarItemCarrinhoClienteDTO(
					produto.getId(),
					produto.getNome(),
					carrinho.getItemsCarrinho().get(i).getQuantidade(),
					produto.getPreco(),
					imagemProduto);
			lista.add(item);
		}
		BigDecimal valorTotal = BigDecimal.ZERO;
        for (ItemCarrinho item : carrinho.getItemsCarrinho()) {
            BigDecimal quantidade = new BigDecimal(item.getQuantidade());
            BigDecimal preco = item.getProduto().getPreco();
            valorTotal = valorTotal.add(quantidade.multiply(preco));
        }
        dto.itemsCarrinho(lista).valorTotal(valorTotal);
    }
}
