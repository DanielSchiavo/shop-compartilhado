package br.com.danielschiavo.repository.cliente;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.danielschiavo.shop.model.cliente.Cliente;

public interface ClienteUtilidadeRepository extends JpaRepository<Cliente, Long>{
	

}
