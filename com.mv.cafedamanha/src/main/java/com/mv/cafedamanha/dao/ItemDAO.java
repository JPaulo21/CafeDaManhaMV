package com.mv.cafedamanha.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.mv.cafedamanha.model.Item;

public class ItemDAO {

	
	private EntityManager em;
	

	
	public void cadastrar(Item item) throws Exception {
		em = JPA.getEntityManagerFactory().createEntityManager();
		
		try{
			em.getTransaction().begin();
			em.persist(item);
			em.getTransaction().commit();
		} catch (Exception e) {
			
			em.getTransaction().rollback();
			throw new Exception("Item: dado já existente!");
		}
		
		em.close();
		
	}
	
	//===========================================
	
	public List<Item> obterItensColaborador(String cpf){
		em = JPA.getEntityManagerFactory().createEntityManager();
		List<Item> itens = null;
		
		//String JPQL ="SELECT i WHERE Item i";
		
		
		
		return itens;
	}
	
	//============================================
	
	public List<Item> obterTodosItens(){
		em = JPA.getEntityManagerFactory().createEntityManager();
		
		List<Item> itens = em.createQuery("FROM Item", Item.class).getResultList();
		
		em.close();
		
		return itens;
	}
   
}
