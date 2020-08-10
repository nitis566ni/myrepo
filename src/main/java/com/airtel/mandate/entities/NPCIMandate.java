package com.airtel.mandate.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "TEMP_MANDATES")
public class NPCIMandate {

	@Id
	@GeneratedValue
	private Long id;
	@Column(length=10485760)
	private String mandateInfo;

	public NPCIMandate() {

	}

	public NPCIMandate(String mandateInfo) {		
		this.mandateInfo = mandateInfo;
	}

	public Long getId() {
		return id;
	}

	public String getMandateInfo() {
		return mandateInfo;
	}

}
