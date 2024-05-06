package br.com.danielschiavo.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.danielschiavo.repository.cliente.ClienteRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        return clienteRepository.findByEmailOrCelularOrCpf(login)
            .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado: " + login));
    }
}
