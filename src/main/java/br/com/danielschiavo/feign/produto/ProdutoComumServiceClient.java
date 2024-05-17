package br.com.danielschiavo.feign.produto;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.danielschiavo.shop.model.pedido.dto.ProdutoPedidoDTO;


@FeignClient(name = "produto-service", url = "http://localhost:8080/shop-user/publico/produto")
public interface ProdutoComumServiceClient {
	
	@GetMapping("/{produtosId}/pedido")
	List<ProdutoPedidoDTO> pegarPrimeiraImagemEVerificarSeEstaAtivo(@PathVariable("produtosId") List<Long> produtosId);

}
