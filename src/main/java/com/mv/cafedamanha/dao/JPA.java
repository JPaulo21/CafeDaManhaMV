package com.mv.cafedamanha.dao;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JPA {
	
	private static EntityManagerFactory emf;
	
	public static EntityManagerFactory getEntityManagerFactory() {
		if(emf == null) {
			emf = Persistence.createEntityManagerFactory("CafeDaManha.MV");
		}
		
		return emf;
	}

}
