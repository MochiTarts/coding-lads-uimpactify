package com.utsc.project_coding_lads.domain;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = Role.TABLE_NAME)
public class Role extends BaseDataEntity {

	public static final String TABLE_NAME = "ROLE";
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "getRole")
	private Set<User> users;
	
	private String name;
	
	public Role() {}
	
	public Role(String name) {
		super();
		this.name = name;
	}

	@Column(name="name", nullable = false, length = 32)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
}