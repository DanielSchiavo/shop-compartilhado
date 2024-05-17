package br.com.danielschiavo.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

import br.com.danielschiavo.shop.model.filestorage.ArquivoInfoDTO;

@FeignClient(name = "file-storage-perfil-service", url = "http://localhost:8080/shop-filestorage/cliente/filestorage/perfil")
public interface FileStoragePerfilComumServiceClient {
	
	@GetMapping("/{nomeFotoPerfil}")
	ArquivoInfoDTO getFotoPerfil(@PathVariable("nomeFotoPerfil") String nomeFotoPerfil, @RequestHeader("Authorization") String token);
	
	@DeleteMapping("/{nomeFotoPerfil}")
	ArquivoInfoDTO deletarFotoPerfil(@PathVariable("nomeFotoPerfil") String nomeFotoPerfil, @RequestHeader("Authorization") String token);

}
