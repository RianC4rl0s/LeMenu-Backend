package br.edu.ufersa.LeMenu.service;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import br.edu.ufersa.LeMenu.model.User;
import br.edu.ufersa.LeMenu.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	@Transactional
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return userRepository
				.findByLogin(username)
				.map(this::mapToUserDetails)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with that email " + username));
	}

	private UserDetails mapToUserDetails(User user) {
		Collection<SimpleGrantedAuthority> roles = user.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getName()))
				.collect(Collectors.toList());
		
		return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(), roles);
	}

	@Override
	public Optional<User> save(User user) {
		var userWithEmail = userRepository.findByLogin(user.getLogin());
			
		if(userWithEmail.isPresent() && !userWithEmail.get().equals(user)) 
			throw new RuntimeException("Email already in use");
		
		String encodedPassword = encoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
		
		return Optional.of(userRepository.save(user));
	}

	@Override
	public User findByLogin(String login) {
		return userRepository.findByLogin(login)
				.orElseThrow(() -> new UsernameNotFoundException("User not found with that login " + login));
	}

	
}
