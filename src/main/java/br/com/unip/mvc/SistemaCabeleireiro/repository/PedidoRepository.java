package br.com.unip.mvc.SistemaCabeleireiro.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;

import br.com.unip.mvc.SistemaCabeleireiro.model.Pedido;

@Repository
public class PedidoRepository {

	@PersistenceContext
	private EntityManager entityManager;	
	
	
	public List<Pedido> recuperaTodosOsPedidos(){

		Query query = entityManager.createQuery("select p from Pedido p", Pedido.class);
		List resultList = query.getResultList();
		return resultList;
	}
	
	
	public List<Pedido> recuperaPorNome(String nome){

		Query query = entityManager.createQuery("select p from Pedido p where p.nome = " + nome,  Pedido.class);
		List resultList = query.getResultList();
		return resultList;
	}
}
