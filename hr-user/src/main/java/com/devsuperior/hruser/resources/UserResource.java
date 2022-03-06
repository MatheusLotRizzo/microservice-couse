package com.devsuperior.hruser.resources;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsuperior.hruser.entities.User;
import com.devsuperior.hruser.repositories.UserRepository;

@RefreshScope
@RestController
@RequestMapping(value = "/users")
public class UserResource {
	private static Logger logger = LoggerFactory.getLogger(UserResource.class);
	
	@Autowired
	private UserRepository repository;
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id){
		
		logger.info("Localizando Usuário com o id: " + id);
		
		User user = repository.findById(id).get();
		return ResponseEntity.ok(user);
	}
	
	@GetMapping()
	public ResponseEntity<List<User>> findAll(){
		logger.info("Listando Todos Usuários");
		
		List<User> list = repository.findAll();
		return ResponseEntity.ok(list);
	}
	
	@GetMapping(value = "/search")
	public ResponseEntity<User> findById(@RequestParam String email){
		
		logger.info("Localizando Usuário com o email: " + email);
		
		User user = repository.findByEmail(email);
		return ResponseEntity.ok(user);
	}
}
