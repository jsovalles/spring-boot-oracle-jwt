package com.oracledb.springboot.web.app.users.dao.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "users")
@Data
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(unique = true)
	private String username;
	
	private String password;
	
	private boolean enabled;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	private List<Role> roles;

	// Persiting enable to be always true when we are creating a user
	@PrePersist
	void preInsert() {
		if (!this.isEnabled())
			this.setEnabled(true);
	}

}
