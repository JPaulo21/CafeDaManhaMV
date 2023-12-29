package com.mv.cafedamanha.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.mv.cafedamanha.dao.ColaboradorDAO;
import com.mv.cafedamanha.dao.ItemDAO;
import com.mv.cafedamanha.model.Colaborador;
import com.mv.cafedamanha.model.Item;

import lombok.Data;

@Data
@SessionScoped
@ManagedBean(name ="cadastro")
public class ColaboradorController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5L;
	
	private Colaborador c;
	private List<Colaborador> colabs;
	private Item item;
	private List<Item> itens;
	private ColaboradorDAO colaboradorDAO;
	private ItemDAO itemDAO;
	private List<Item> items;

	@PostConstruct
	public void init() {
		
		c = new Colaborador();
		itens = new ArrayList<Item>();
		colaboradorDAO = new ColaboradorDAO();
		item = new Item();
		itemDAO = new ItemDAO();
		colabs = colaboradorDAO.selectAll();
		
	}
	
	public String cadastrar() {
		
		String caminho = "";
		
		if(validar())
			caminho = "cadastro";
			
		return caminho;
	}
	
	public boolean validar() {
		
		boolean estaValido = false;
		boolean val = false;
		
		for(Colaborador aux:colabs) {
			
			if(aux.getCpf().equals(c.getCpf()))
				val=true;
		}
		
		
		try {
			
			if (c.getNome().trim().isEmpty()) {
				
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro Colaborador", "Nome vazio!"));
				
			} else if (c.getCpf().trim().isEmpty() || !c.getCpf().matches("\\d\\d\\d\\.\\d\\d\\d\\.\\d\\d\\d\\-\\d\\d")) {
				
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro Colaborador", "CPF Inválido!\nSiga o padrão XXX.XXX.XXX-XX"));
			
			} else if (val){
				
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", "CPF já cadastrado!"));
			} else {
				
				if(itens.isEmpty()) {
					
					FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Aviso", "Nenhum item cadastrado"));

				}
				
				c.setItens(itens);
				colaboradorDAO.cadastrar(c);
				estaValido = true;
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Sucesso", "Cadastro  realizado"));
				FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
				FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
				
			}
			
		} catch(Exception e) {
		
			c.setItens(null);
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro", e.getMessage()));
		}
		
		
		return estaValido;
	}
	
	public void deletar(Item i) {
		
		this.itens.remove(i);
	}
	
	public void addItem(){	
		
		boolean val = false;
		for(Item aux:itemDAO.obterTodosItens()) {
			
			if(aux.getNome().equalsIgnoreCase(item.getNome()))
				val = true;
				
		}
		
		boolean val2 = false;
		for(Item aux:itens) {
			
			if(aux.getNome().equalsIgnoreCase(item.getNome()))
				val = true;
				
		}
		
		if(item.getNome().trim().isEmpty()) {
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro Item", "Nome vazio"));
		} else if(val || val2) {
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Erro Item", "Item já cadastrado"));
		} else {
		
			item.setC(c);
			itens.add(item);
			
			this.item = new Item();
		}
	}
	
	
}
