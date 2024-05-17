package br.com.danielschiavo.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import br.com.danielschiavo.shop.model.cliente.Cliente;
import br.com.danielschiavo.shop.model.cliente.dto.MostrarClienteDTO;
import br.com.danielschiavo.shop.model.filestorage.ArquivoInfoDTO;

@Mapper(componentModel = "spring")
public interface ClienteComumMapper {

	@Mapping(target = "fotoPerfil", source = "arquivoInfoDTO")
	public MostrarClienteDTO clienteParaMostrarClienteDTO(Cliente cliente, ArquivoInfoDTO arquivoInfoDTO);

}
