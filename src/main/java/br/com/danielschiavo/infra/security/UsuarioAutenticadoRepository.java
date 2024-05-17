package br.com.danielschiavo.infra.security;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import br.com.danielschiavo.shop.model.cliente.Cliente;

@Repository
public interface UsuarioAutenticadoRepository extends JpaRepository<Cliente, Long>{
	
	@Query("SELECT c FROM Cliente c WHERE c.id = :id")
	UserDetails buscarPorId(Long id);

	@Query("SELECT c FROM Cliente c WHERE c.email = :login OR c.celular = :login OR c.cpf = :login")
	Optional<UserDetails> findByEmailOrCelularOrCpf(String login);

}
