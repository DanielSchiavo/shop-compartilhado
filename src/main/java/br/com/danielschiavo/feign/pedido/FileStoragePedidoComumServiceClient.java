package br.com.danielschiavo.feign.pedido;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import br.com.danielschiavo.shop.model.filestorage.ArquivoInfoDTO;
import br.com.danielschiavo.shop.model.filestorage.PersistirOuRecuperarImagemPedidoDTO;

@FeignClient(name = "file-storage-pedido-service", url = "${filestorage.service.client.url}")
public interface FileStoragePedidoComumServiceClient {
	
	@GetMapping("/cliente/pedido/{nomeImagemPedido}")
	ArquivoInfoDTO pegarImagemPedido(@PathVariable("nomeImagemPedido") String nomeImagemPedido, @RequestHeader("Authorization") String token);
	
	@PostMapping("/cliente/pedido")
	ArquivoInfoDTO persistirOuRecuperarImagemPedido(@RequestBody PersistirOuRecuperarImagemPedidoDTO persistirOuRecuperarImagemPedidoDTO, @RequestHeader("Authorization") String token);

}
