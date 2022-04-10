package com.mv.cafedamanha.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import com.mv.cafedamanha.dao.ColaboradorDAO;
import com.mv.cafedamanha.model.Colaborador;

import lombok.Data;

@Data
@ViewScoped
@ManagedBean(name ="lista")
public class ListaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7L;
	
	private Colaborador c;
	private List<Colaborador> colabs;
	private ColaboradorDAO colaboradorDAO;

	@PostConstruct
	public void init() {
		
		c = new Colaborador();
		colaboradorDAO = new ColaboradorDAO();
		colabs = colaboradorDAO.selectAll();
		
	}
	
	
	
}
