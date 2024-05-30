package br.com.danielschiavo.feign.produto;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.danielschiavo.shop.model.filestorage.ArquivoInfoDTO;

@FeignClient(name = "file-storage-produto-service",
			 url = "${filestorage.service.client.url}",
			 fallback = FileStorageProdutoComumFallback.class)
public interface FileStorageProdutoComumServiceClient {
	
	@GetMapping("/publico/produto/{nomesArquivos}")
	List<ArquivoInfoDTO> pegarArquivosProduto(@RequestParam("arquivo") List<String> arquivos);

}
