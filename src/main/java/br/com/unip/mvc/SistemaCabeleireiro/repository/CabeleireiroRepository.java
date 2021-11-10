package br.com.unip.mvc.SistemaCabeleireiro.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.unip.mvc.SistemaCabeleireiro.model.Pedido;
import br.com.unip.mvc.SistemaCabeleireiro.model.entity.Cabeleireiro;

@Repository
public interface CabeleireiroRepository extends JpaRepository<Cabeleireiro, Long>{

	List<Cabeleireiro> findAll();
	
	List<Cabeleireiro> findById(Integer idCabeleireiro);
	
	List<Cabeleireiro> findByNome(String nomeCabeleireiro);
}
