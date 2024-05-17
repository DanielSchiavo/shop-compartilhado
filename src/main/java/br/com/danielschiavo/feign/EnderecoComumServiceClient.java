package br.com.danielschiavo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import br.com.danielschiavo.shop.model.cliente.endereco.Endereco;


@FeignClient(name = "endereco-service", url = "http://localhost:8080/shop-user/cliente/endereco")
public interface EnderecoComumServiceClient {
	
	@GetMapping("/{enderecoId}")
	Endereco pegarEnderecoDoClientePorId(@PathVariable("enderecoId") Long enderecoId, @RequestHeader("Authorization") String token);

}
