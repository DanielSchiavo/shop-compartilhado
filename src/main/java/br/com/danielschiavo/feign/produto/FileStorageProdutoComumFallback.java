package br.com.danielschiavo.feign.produto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import br.com.danielschiavo.shop.model.filestorage.ArquivoInfoDTO;

@Component
public class FileStorageProdutoComumFallback implements FileStorageProdutoComumServiceClient {

	@Override
	public ArquivoInfoDTO pegarArquivoProduto(String arquivo) {
		return ArquivoInfoDTO.comErro(arquivo, "Não foi possivel recuperar os bytes desse arquivo");
	}

	@Override
	public List<ArquivoInfoDTO> pegarArquivosProduto(List<String> arquivos) {
		List<ArquivoInfoDTO> listaArquivos = new ArrayList<>();
		arquivos.forEach(arq -> {
			ArquivoInfoDTO comErro = ArquivoInfoDTO.comErro(arq, "Não foi possivel recuperar os bytes desse arquivo");
			listaArquivos.add(comErro);
		});
		return listaArquivos;
	}
}