package com.educandoweb.course.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "tb_user") // adicionado name para evitar conflitos com palavras reservadas do sql
public class User implements Serializable{
		
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Auto incremento do id no banco de dados
	private Long id;
	private String name;
	private String email;
	private String phone;
	private String password;
	
	@JsonIgnore  // evita looping em uma associação de mão dupla
	@OneToMany(mappedBy = "client") // associação um pra muitos na classe Order esta mapeado pelo atributo client
	private List<Order> orders = new ArrayList<>(); 
	
//	(Lazy loading)
//	Em uma associação MUITOS para 1, se for carregado um obj do lado do MUITOS o obj do lado do 1 vem automaticamente//
//	Isso não acontece do lado do 1 para MUITOS, se for carregado um obj que tem uma associação para MUITOS do outro lado o jpa não carrega os objetos do lado do MUITOS por padrão//
//
//	Quando se tem uma associação para MUITOS o JPA não carrega os objetos para MUITOS(Para não estourar a memoria, trafego do computador, ele faz entao o lazy load)
//
//	Obs: ele só carrega se os objetos forem acionados do lado para MUITOS, ai sim o JPA carrega
//
//	ex:
//	@OneToMany(mappedBy = "client") tipo de associação:(um para muitos)  // nesse exemplo ele só carrega se os objetos forem acionados do lado para MUITOS, ai sim o JPA carrega
//	private List<Order> orders = new ArrayList<>(); 
//
//	@ManyToOne    		         
//	@JoinColumn(name = "client_id") tipo de associação(muitos para um) 
//	private User client;
	//jackson serializa o json, solicita os pedidos no banco de dados ai sim o JPA busca
	
	public User() {
	}

	public User(Long id, String name, String email, String phone, String password) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.phone = phone;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Order> getOrders() {
		return orders;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
