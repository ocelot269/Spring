package org.formacio.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Repository;


@Entity
@Table(name = "t_factures")
public class Factura {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "fac_id")
	private Long id;
	
	@OneToMany
	@JoinColumn(name = "fac_client")
	private Client client;
	
	@OneToMany(cascade=CascadeType.ALL)
	@JoinColumn(name="lin_factura")
	private Set<LiniaFactura> linies = new HashSet<>();
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Set<LiniaFactura> getLinies() {
		return linies;
	}

	public void setLinies(Set<LiniaFactura> linies) {
		this.linies = linies;
	}
	
	
}
