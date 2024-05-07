package br.com.danielschiavo.infra.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import br.com.danielschiavo.shop.model.cliente.Cliente;


@Service
public class UsuarioAutenticadoService {
	
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}
	
    public Cliente getCliente() {
        Authentication authentication = getAuthentication();
        return (Cliente) authentication.getPrincipal();
    }
    
    public String getTokenComBearer() {
    	var jwtAuthenticationToken = (JwtAuthenticationToken) getAuthentication();
    	return jwtAuthenticationToken.getToken();
    }
}
