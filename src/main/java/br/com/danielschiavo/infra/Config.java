package br.com.danielschiavo.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import br.com.danielschiavo.infra.security.UsuarioAutenticadoRepository;

@Configuration
@ComponentScan(basePackages = "br.com.danielschiavo.service")
public class Config {

//
//	@Bean
//	UsuarioAutenticadoRepository usuarioAutenticadoRepository(UsuarioAutenticadoRepository repository) {
//	    return repository;
//	}
//	
}
