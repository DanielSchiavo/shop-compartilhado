package br.com.danielschiavo.infra.feign;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import org.apache.commons.io.IOUtils;
import org.apache.coyote.BadRequestException;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

import feign.Response;
import feign.codec.ErrorDecoder;

public class CustomErrorDecoder implements ErrorDecoder {
	
    @Override
    public Exception decode(String methodKey, Response response) {
        switch (response.status()){
            case 400:
			try {
				System.out.println(" ERRO 400 ");
				var json = IOUtils.toString(response.body().asInputStream(), StandardCharsets.UTF_8);
				return new BadRequestException(json);
			} catch (IOException e) {
				e.printStackTrace();
			}
            case 404:
            	System.out.println(" ERRO 404 ");
                return new NotFoundException();
            case 403:
            	return new BadRequestException("Usuario não tem permissão para usar esse endpoint");
            default:
            	System.out.println(" OUTRO ERRO ");
                return new Exception("Generic error, " + response.toString() + ", status" + response.status() + ", URL" + response.request().url());
        }
    }
}