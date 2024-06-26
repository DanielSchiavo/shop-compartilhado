package br.com.danielschiavo.mapper.pedido;

import java.util.ArrayList;
import java.util.List;

import org.mapstruct.Context;
import org.mapstruct.Mapper;

import br.com.danielschiavo.shop.model.filestorage.ArquivoInfoDTO;
import br.com.danielschiavo.shop.model.pedido.Pedido;
import br.com.danielschiavo.shop.model.pedido.dto.MostrarProdutoDoPedidoDTO;
import br.com.danielschiavo.shop.services.filestorage.FileStoragePedidoService;

@Mapper(componentModel = "spring")
public abstract class PedidoMapper {

	public List<MostrarProdutoDoPedidoDTO> pedidoParaMostrarProdutoDoPedidoDTO(Pedido pedido, @Context FileStoragePedidoService fileService) {
		List<MostrarProdutoDoPedidoDTO> lista = new ArrayList<>();
		pedido.getItemsPedido().forEach(itemPedido -> {
			ArquivoInfoDTO arquivoInfoDTO = fileService.pegarImagemPedidoPorNome(itemPedido.getPrimeiraImagem());
			lista.add(new MostrarProdutoDoPedidoDTO(itemPedido, arquivoInfoDTO.bytesArquivo()));
		});
		return lista;
	}
		
}
