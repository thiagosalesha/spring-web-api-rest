package br.com.salesha.forum.controller;

import javax.validation.Valid;

import org.springframework.security.core.Authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.salesha.forum.config.security.TokenService;
import br.com.salesha.forum.controller.form.LoginForm;
import br.com.salesha.forum.dto.TokenDTO;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {
	
	@Autowired
	private AuthenticationManager authManager;
	
	@Autowired
	private TokenService tokenService;
	
	@PostMapping
	public ResponseEntity<TokenDTO> autenticar(@RequestBody @Valid LoginForm form){
		
		UsernamePasswordAuthenticationToken dadosLogin = form.converter();
		
		try {
			Authentication authenticate = authManager.authenticate(dadosLogin);
			String token = tokenService.gerarToken(authenticate);
			return ResponseEntity.ok(new TokenDTO(token, "Bearer"));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
		

	}

}
