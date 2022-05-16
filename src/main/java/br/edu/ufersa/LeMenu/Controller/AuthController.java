package br.edu.ufersa.LeMenu.Controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import br.edu.ufersa.LeMenu.model.Role;
import br.edu.ufersa.LeMenu.model.User;
import br.edu.ufersa.LeMenu.service.UserService;
@RestController
@RequestMapping("token")
public class AuthController {

	@Autowired
	private UserService userService;

	@PostMapping("refresh")
	public ResponseEntity<Map<String, String>> refreshToken(HttpServletRequest request) {
		String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			String refreshToken = authorizationHeader.substring("Bearer ".length());
			Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
			JWTVerifier verifier = JWT.require(algorithm).build();
			DecodedJWT decodedJWT = verifier.verify(refreshToken);

			String email = decodedJWT.getSubject();

			User user = userService.findByLogin(email);

			String accessToken = JWT.create().withSubject(user.getLogin())
					.withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
					.withIssuer(request.getRequestURL().toString())
					.withClaim("roles", user.getRoles().stream().map(Role::getName).collect(Collectors.toList()))
					.sign(algorithm);

			Map<String, String> tokens = new HashMap<>();
			tokens.put("accessToken", accessToken);
			tokens.put("refreshToken", refreshToken);

			return ResponseEntity.ok(tokens);
		} else {
			throw new RuntimeException("Refresh token is missing");
		}
	}
	
	@GetMapping("data")
	public ResponseEntity<Map<String, Object>> getAuthenticated(HttpServletRequest request) {
		String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
		
		if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
			String refreshToken = authorizationHeader.substring("Bearer ".length());
			Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
			JWTVerifier verifier = JWT.require(algorithm).build();
			DecodedJWT decodedJWT = verifier.verify(refreshToken);

			String email = decodedJWT.getSubject();
			User user = userService.findByLogin(email);	
			
			Role role = user.getRoles().get(0);
			
			Map<String, Object> response = new HashMap<>();
			response.put("id", user.getId());
			response.put("name", user.getLogin());
			response.put("email", user.getLogin());
			response.put("roles", role.getName());

			return ResponseEntity.ok(response);
		} else {
			throw new RuntimeException("Refresh token is missing");
		}
	}
	
}
