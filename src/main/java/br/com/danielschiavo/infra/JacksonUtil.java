package br.com.danielschiavo.infra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.danielschiavo.shop.model.ValidacaoException;

public class JacksonUtil {
	
	private static final String mensagemJsonProcessingException = "Erro ao processar objeto para JSON";
	
    public static Object deserializar(String json, Class<?> clazz) throws ValidacaoException {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

            return objectMapper.readValue(json, clazz);
        } catch (JsonMappingException e) {
            throw new ValidacaoException("Erro ao mapear json");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new ValidacaoException(mensagemJsonProcessingException);
        }
    }

}
