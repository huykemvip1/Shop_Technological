package com.ShopTechnological.Application.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "memoriesjwt")
public class MemoriesJwt implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String username;
	private String token;
	private String url;
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name = "rolesjwt",
	    joinColumns = @JoinColumn(name = "id_jwt"),
	    inverseJoinColumns = @JoinColumn(name = "id_role")
	)
	private List<Role> roles=new ArrayList<Role>();
}
