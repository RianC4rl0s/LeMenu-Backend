package br.edu.ufersa.LeMenu.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetailsService;

import br.edu.ufersa.LeMenu.model.User;


public interface UserService extends UserDetailsService {
	Optional<User> save(User user);
	User findByLogin(String login);
}
