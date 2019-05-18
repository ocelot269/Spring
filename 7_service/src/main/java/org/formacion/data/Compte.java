package org.formacion.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="T_COMPTES")
public class Compte {

	@Id
	@Column(name="COM_CLIENT")
	private String client;
	
	@Column(name="COM_QTAT")
	private int quantitat;

	public String getClient() {
		return client;
	}

	public void setClient(String client) {
		this.client = client;
	}

	public int getQuantitat() {
		return quantitat;
	}

	public void setQuantitat(int quantitat) {
		this.quantitat = quantitat;
	}
	
    public String toString() {
    	     return client + ": " + quantitat;
    }
}
