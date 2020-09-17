package com.oracledb.springboot.web.app.users.dao.entity;

import lombok.Data;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Table(name = "roles")
@Data
public class Role implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String authority;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private Usuario user;

}
