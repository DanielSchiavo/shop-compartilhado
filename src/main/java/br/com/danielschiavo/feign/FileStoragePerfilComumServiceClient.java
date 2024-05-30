package br.com.danielschiavo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import br.com.danielschiavo.shop.model.filestorage.ArquivoInfoDTO;

@FeignClient(name = "file-storage-perfil-service", url = "${filestorage.service.client.url}")
public interface FileStoragePerfilComumServiceClient {
	
	@DeleteMapping("/cliente/perfil/{nomeFotoPerfil}")
	ArquivoInfoDTO deletarFotoPerfil(@PathVariable("nomeFotoPerfil") String nomeFotoPerfil, @RequestHeader("Authorization") String token);
	
	@GetMapping("/cliente/perfil/{nomeFotoPerfil}")
	ArquivoInfoDTO getFotoPerfil(@PathVariable("nomeFotoPerfil") String nomeFotoPerfil, @RequestHeader("Authorization") String token);

}
