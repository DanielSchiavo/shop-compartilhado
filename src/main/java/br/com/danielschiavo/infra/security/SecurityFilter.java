package br.com.danielschiavo.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import br.com.danielschiavo.repository.cliente.ClienteRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter{
	
	@Autowired
	private TokenJWTService tokenJWTService;
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response
			, FilterChain filterChain) throws ServletException, IOException {
		
		String tokenComBearer = request.getHeader("Authorization");
		
		String tokenSemBearer = null;
		if (tokenComBearer != null) {
			tokenSemBearer = tokenComBearer.replace("Bearer ", "");
		}
		
		if (tokenSemBearer != null) {
			Long subject = tokenJWTService.getClaimIdJWT(tokenSemBearer);
			UserDetails client = clienteRepository.buscarPorId(subject);
			var authentication = new JwtAuthenticationToken(client, null, tokenComBearer, client.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		filterChain.doFilter(request, response);
	}
}
