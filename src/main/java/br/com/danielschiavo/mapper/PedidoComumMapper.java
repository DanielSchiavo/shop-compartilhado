package br.com.danielschiavo.mapper;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Context;
import org.mapstruct.Mapper;

import br.com.danielschiavo.feign.pedido.FileStoragePedidoComumServiceClient;
import br.com.danielschiavo.shop.model.filestorage.ArquivoInfoDTO;
import br.com.danielschiavo.shop.model.pedido.Pedido;
import br.com.danielschiavo.shop.model.pedido.dto.MostrarProdutoDoPedidoDTO;

@Mapper(componentModel = "spring")
public abstract class PedidoComumMapper {

	public List<MostrarProdutoDoPedidoDTO> pedidoParaMostrarProdutoDoPedidoDTO(Pedido pedido, @Context FileStoragePedidoComumServiceClient fileService, @Context String tokenComBearer) {
		List<MostrarProdutoDoPedidoDTO> lista = new ArrayList<>();
		pedido.getItemsPedido().forEach(itemPedido -> {
			ArquivoInfoDTO arquivoInfoDTO = fileService.pegarImagemPedido(itemPedido.getPrimeiraImagem(), tokenComBearer);
			lista.add(new MostrarProdutoDoPedidoDTO(itemPedido, arquivoInfoDTO.bytesArquivo()));
		});
		return lista;
	}
		
}
