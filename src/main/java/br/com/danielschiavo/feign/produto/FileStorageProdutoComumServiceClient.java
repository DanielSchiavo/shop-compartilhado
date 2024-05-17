package br.com.danielschiavo.feign.produto;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.danielschiavo.shop.model.filestorage.ArquivoInfoDTO;

@FeignClient(name = "file-storage-produto-service",
			 url = "http://localhost:8080/shop-filestorage/publico/filestorage/produto",
			 fallback = FileStorageProdutoComumFallback.class)
public interface FileStorageProdutoComumServiceClient {
	
	@GetMapping("/{arquivo}")
	ArquivoInfoDTO pegarArquivoProduto(@PathVariable("arquivo") String arquivo);
	
	@GetMapping
	List<ArquivoInfoDTO> pegarArquivosProduto(@RequestParam("arquivo") List<String> arquivos);

}
