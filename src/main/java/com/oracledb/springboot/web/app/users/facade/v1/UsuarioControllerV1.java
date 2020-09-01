package com.oracledb.springboot.web.app.users.facade.v1;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.oracledb.springboot.web.app.users.dao.entity.Usuario;
import com.oracledb.springboot.web.app.users.dao.v1.IUsuarioDAOV1;

@RestController
@RequestMapping("/users")
public class UsuarioControllerV1 {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioControllerV1.class);

    @Autowired
	private IUsuarioDAOV1 dao;
    
    private BCryptPasswordEncoder encoder = passwordEncoder();

    @PostMapping("/sign-up")
    public void signUp(@RequestBody Usuario user) {
        user.setPassword(encoder.encode(user.getPassword()));
        LOGGER.info(user.toString());
        dao.save(user);
    }
    
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}

}