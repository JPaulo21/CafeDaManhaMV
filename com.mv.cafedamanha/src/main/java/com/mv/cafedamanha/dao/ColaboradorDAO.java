package com.mv.cafedamanha.dao;

import java.util.List;

import javax.persistence.EntityManager;

import com.mv.cafedamanha.model.Colaborador;

public class ColaboradorDAO {
	
	private EntityManager em;
	
	public ColaboradorDAO() {
		
		em = JPA.getEntityManagerFactory().createEntityManager();
		
	}
	
	//===================================================
	
	public void cadastrar(Colaborador c) throws Exception {
		em = JPA.getEntityManagerFactory().createEntityManager();
		
		try{
			em.getTransaction().begin();
			c.setIdColaborador(null);
			em.persist(c);
			em.getTransaction().commit();
		} catch (Exception e) {
			
			em.getTransaction().rollback();
			throw new Exception(e); //Exception("Colaborador: dado já existente!");
		}
		
		em.close();
	}
	
	//===================================================
	
	public void atualizar(Colaborador c) throws Exception {
		em = JPA.getEntityManagerFactory().createEntityManager();
		
		try {
			em.getTransaction().begin();
			em.merge(c);
			em.getTransaction().commit();
		} catch (Exception e) {
			
			em.getTransaction().rollback();
			throw new Exception(e); //Exception("Colaborador: dado já existente!");
		}
		
		em.close();
		
	}
	
	//===================================================

	public void deletar(Colaborador c) {
		em = JPA.getEntityManagerFactory().createEntityManager();
		
		em.getTransaction().begin();
		em.remove(c);
		em.getTransaction().commit();
		
		em.close();
		
	}
	
	//=====================================================

	@SuppressWarnings("unchecked")
	public List<Colaborador> selectAll() {
		List<Colaborador> colaboradores = null;
		
		try {
		
			colaboradores = em.createQuery("FROM Colaborador").getResultList();
		} catch(Exception e) {
			
			e.printStackTrace();
		} finally {
			
			em.close();
		}	
		
		return colaboradores;
	}
	
	//====================================
	
	public Colaborador seletetWhereId(Integer id) {
		
		Colaborador c = em.find(Colaborador.class, id);
		em.close();
		
		return c;
	}
	
	//======================================
	
	public Colaborador selectWhereCPF(String cpf) {
		em = JPA.getEntityManagerFactory().createEntityManager();
		
		Colaborador c = em.createQuery("SELECT c FROM Colaborador c WHERE cpf = :cpf ", Colaborador.class).setParameter("cpf", cpf).getSingleResult();		
		em.close();
		
		return c;
	}
	
}