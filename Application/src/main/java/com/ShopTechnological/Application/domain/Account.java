package com.ShopTechnological.Application.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.ShopTechnological.Application.CovertStringtoDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Account implements Serializable{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String username;
	private String password;
	private String fullname;
	private String phonenumber;
	@Convert(converter = CovertStringtoDate.class)
	private String birthday;
	private String sex;
	private String email;
	private String address;
	@ManyToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	@JoinTable(name = "role_account",
			joinColumns = @JoinColumn(name="id_account"),
	     inverseJoinColumns = @JoinColumn(name="id_role")
	)
	private List<Role> roles=new ArrayList<Role>();
}
