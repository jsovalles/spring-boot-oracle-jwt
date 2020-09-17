package com.oracledb.springboot.web.app.users.facade.v1;

import com.oracledb.springboot.web.app.users.dao.entity.Role;
import com.oracledb.springboot.web.app.users.dao.entity.Usuario;
import com.oracledb.springboot.web.app.users.dao.v1.IUsuarioDAOV1;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin({"http://localhost:4200","https://jsovalles.github.io"})
public class UsuarioControllerV1 {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UsuarioControllerV1.class);

    @Autowired
	private IUsuarioDAOV1 dao;

    @Autowired
    private BCryptPasswordEncoder encoder;

    @PostMapping("/sign-up")
    public void signUp(@RequestBody Usuario user) {
        user.setPassword(encoder.encode(user.getPassword()));

        //Mapping for a user role
        List<Role> roles = new ArrayList<>();
        Role userRole = new Role();
        userRole.setAuthority("ROLE_USER");
        userRole.setUser(user);
        roles.add(userRole);
        user.setRoles(roles);

        dao.save(user);
    }
    


}