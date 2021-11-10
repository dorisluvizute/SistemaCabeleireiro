package br.com.unip.mvc.SistemaCabeleireiro.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.unip.mvc.SistemaCabeleireiro.model.entity.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long>{

	List<Cliente> findAll();
	
	List<Cliente> findById(Integer idCliente);
	
	List<Cliente> findByNome(String nomeCliente);
}
