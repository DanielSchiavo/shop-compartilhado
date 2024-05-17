package br.com.danielschiavo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import br.com.danielschiavo.shop.model.cliente.cartao.Cartao;


@FeignClient(name = "cartao-service", url = "http://localhost:8080/shop-user/cliente/cartao")
public interface CartaoComumServiceClient {
	
	@GetMapping("/{cartaoId}")
	Cartao pegarCartao(@PathVariable("cartaoId") Long cartaoId, @RequestHeader("Authorization") String token);

}
