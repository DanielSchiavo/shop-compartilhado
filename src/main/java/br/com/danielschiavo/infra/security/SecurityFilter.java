package br.com.danielschiavo.infra.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
@ComponentScan(basePackages = "br.com.danielschiavo.infra.security")
public class SecurityFilter extends OncePerRequestFilter{
	
	@Autowired
	private TokenJWTService tokenJWTService;
	
	@Autowired
	private UsuarioAutenticadoRepository usuarioAutenticadoRepository;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response
			, FilterChain filterChain) throws ServletException, IOException {
		
		String tokenComBearer = request.getHeader("Authorization");
		
		String tokenSemBearer = null;
		if (tokenComBearer != null) {
			tokenSemBearer = tokenComBearer.replace("Bearer ", "");
		}
		
		if (tokenSemBearer != null) {
			Long subject = tokenJWTService.verificarTokenEPegarIdUsuario(tokenSemBearer);
			//Fazer essa consulta no banco de dados é necessaria para buscar as Roles dos usuarios para verificar a permissão dos mesmos nos endpoints
			UserDetails client = usuarioAutenticadoRepository.buscarPorId(subject);
			var authentication = new JwtAuthenticationToken(client, null, tokenComBearer, client.getAuthorities());
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		filterChain.doFilter(request, response);
	}
}
