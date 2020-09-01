package com.oracledb.springboot.web.app.users.dao.v1;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oracledb.springboot.web.app.users.dao.entity.Usuario;

public interface IUsuarioDAOV1 extends JpaRepository<Usuario, Integer> {

	public Usuario findByUsername(String username);

}
