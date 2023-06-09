package com.trabalhoFinal.apiEcommerce.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.trabalhoFinal.apiEcommerce.dto.MessageDTO;
import com.trabalhoFinal.apiEcommerce.entities.User;
import com.trabalhoFinal.apiEcommerce.exceptions.UserNotFoundException;
import com.trabalhoFinal.apiEcommerce.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	UserRepository userRepository;
	
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	public User getUserById(Integer id) {
		return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
	}
	
	public MessageDTO deleteById(Integer id) {
		userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
		
		userRepository.deleteById(id);
		return new MessageDTO("Usuário deletado com sucesso");
	}
	
}
